package com.example.applace.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.applace.ClasseNegocio.Cliente;
import com.example.applace.ClasseNegocio.Fornecedor;
import com.example.applace.ClasseNegocio.Imovel;
import com.example.applace.ClasseNegocio.Usuario;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelp extends SQLiteOpenHelper {
        private  static  final String NOME_BANCO = "applace.db";
        private static final int VERSAO_BANCO = 1;
        private Context context;
        private SQLiteDatabase dbInstancia = null;
        private Usuario u;

        public DBHelp(@Nullable Context context) {
            super(context,NOME_BANCO,null,VERSAO_BANCO);
            this.context =context;
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE usuario(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "cpf TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "senha TEXT NOT NULL,"+
                    "tipo TEXT NOT NULL)"
            );
            db.execSQL("CREATE TABLE imovel(" +
                    "id INTEGER PRIMARY KEY  AUTOINCREMENT," +
                    "idFornecedorImovel INTERGER NOT NULL," +
                    "nome TEXT NOT NULL," +
                    "categoria TEXT NOT NULL," +
                    "valorAluguel double NOT NULL," +
                    "descricao TEXT NOT NULL," +
                    "statuss TEXT NOT NULL," +
                    "nomeArquivo TEXT NOT NULL," +
                    "imagem TEXT," +                                                        //um objeto que na verdade é um array de  bytes,BLOB
                    "dataDisponibilizado TEXT NOT NULL,"+
                    "foreign KEY (idFornecedorImovel) REFERENCES usuario(id))"
            );
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS usuario");
            db.execSQL("DROP TABLE IF EXISTS imovel");
            onCreate(db);
        }
        public void salvarImovel(Imovel imovel)throws SQLException {
            abrirEscritaDB();
            dbInstancia.insert("imovel",null,imovel.getContentValues());
            fecharDB();
        }
        public void atualizarImovel(Imovel imovel)throws SQLException {
            abrirEscritaDB();
            Integer id = imovel.getId();
            String[] auxWhere = new String[]{"" + id};
            abrirEscritaDB();
            dbInstancia.update("imovel", imovel.getContentValues(), "_id = ?",auxWhere);
            fecharDB();
        }
        public void salvarCliente(Cliente cliente)throws SQLException{
            abrirEscritaDB();
            dbInstancia.insert("usuario",null,cliente.getContentValues());
            fecharDB();
        }
        public void salvarFornecedor(Fornecedor fornecedor)throws SQLException{
            abrirEscritaDB();
            dbInstancia.insert("usuario",null,fornecedor.getContentValues());
            fecharDB();
        }
        public void excluirUsuario(Usuario usuario)throws SQLException{
            abrirEscritaDB();
            dbInstancia.delete("usuario","id = "+usuario.getId(),null);
            fecharDB();
        }
        public Usuario buscarUsuario(Usuario usuario)throws SQLException{
            SQLiteDatabase meuBanco = getReadableDatabase();
            String[] auxWhere = new String[]{"" +usuario.getEmail(),""+usuario.getSenha()};
            Cursor minhaConsulta=  meuBanco.rawQuery("SELECT id,nome,cpf,email,senha,tipo FROM usuario Where usuario.email = ?" +" and + usuario.senha = ?",auxWhere);
            minhaConsulta.moveToFirst();
            while (! minhaConsulta.isAfterLast()) {
                u = new Usuario(minhaConsulta.getString(1), minhaConsulta.getString(2), minhaConsulta.getString(3), minhaConsulta.getString(4), minhaConsulta.getString(5));
                Integer i=minhaConsulta.getInt(0);
                u.setId(i);
                if(usuario.getEmail().equals(u.getEmail())&& usuario.getSenha().equals(u.getSenha())){
                    meuBanco.close();
                    minhaConsulta.close();
                    return u;
                }
                minhaConsulta.moveToNext();
            }
            meuBanco.close();
            minhaConsulta.close();
            return null;
        }
        public ArrayList<Imovel> getImoveisDB(){
            ArrayList<Imovel> imoveis = new ArrayList<>();
            SQLiteDatabase meuBanco = getReadableDatabase();
            Cursor minhaConsulta= meuBanco.rawQuery("SELECT id,idFornecedorImovel,nome,categoria,valorAluguel,descricao,statuss,dataDisponibilizado FROM imovel", null);
            minhaConsulta.moveToFirst();
            while (! minhaConsulta.isAfterLast()) {
                Imovel atual = new Imovel(minhaConsulta.getString(2), minhaConsulta.getString(3),
                        Double.parseDouble(minhaConsulta.getString(4)),minhaConsulta.getString(5),minhaConsulta.getString(6),
                        minhaConsulta.getString(7));
                atual.setId(minhaConsulta.getInt(0));
                atual.setIdFornecedorImovel(minhaConsulta.getInt(1));

                imoveis.add(atual);
                minhaConsulta.moveToNext();
            }
            meuBanco.close();
            minhaConsulta.close();
            return imoveis;
        }
        //Garantir sigletonnn unica instancia
        public void abrirEscritaDB()throws SQLException{
            if(this.dbInstancia == null){
                this.dbInstancia=this.getWritableDatabase();
            }

        }
        public void abrirLeituraDB()throws SQLException{
            if(this.dbInstancia == null){
                this.dbInstancia=this.getReadableDatabase();
            }

        }
        public void fecharDB()throws SQLException{
            if(this.dbInstancia != null){
                if(this.dbInstancia.isOpen())
                    this.dbInstancia.close();
            }
        }
}















/*
* public byte[] trasformarImagembytes(){
            byte[] x=new byte[8000];
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Object imageBitmap;                                        // é uma imagem no formato bitmap que eu quero converter
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            byte imagemBytes[]=stream.toByteArray();                  //transformo em array de bytes
            return x;                                                //| retorno para um arrey de bites
        }


        public void trasformarDeVolta(byte[] xImagem){                            //arrei de bytes da foto
            ByteArrayInputStream imageStream = new ByteArrayOutputStream(xImagem);//converto arrey biteStream

            Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);         //tranformo em bitmap
            imagem.setImageBitmap(imageBitmap);                                   //setbitmap na imagem que quero exibir
        }
*
* */
/* Metodo para criar o nome de um arquivo */
/*
private File  arquivoFoto = null;
    private File criarArquivo() throw IOExeption{                                   //fazendo formato para nn ter duplicidade de imagem
        String timeStamp = new SimpleFormat("yyyyMMMdd_HHmmss").format(new Date());
}
 */










