package com.example.applace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.applace.ClasseNegocio.Imovel;

import java.util.ArrayList;

public class AdapterImovel extends ArrayAdapter<Imovel> {
    private final Context context;
    private ArrayList<Imovel> imoveis;

    public AdapterImovel(@NonNull Context context,ArrayList<Imovel> imoveis) {
        super(context,R.layout.linha_imovel,imoveis);
        this.context = context;
        this.imoveis=imoveis;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater i =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha = i.inflate(R.layout.linha_imovel,parent,false);

        TextView nome = (TextView)linha.findViewById(R.id.tv_nomeFornecedor);
        TextView categoria = (TextView)linha.findViewById(R.id.tv_categoria);
        TextView descricao = (TextView)linha.findViewById(R.id.tv_descricao);
        TextView valor = (TextView)linha.findViewById(R.id.tv_valor);
        ImageView imagem = (ImageView)linha.findViewById(R.id.im_Imovel);
        nome.setText(imoveis.get(position).getNome());
        imagem.setImageResource(R.drawable.applacelogo);
        categoria.setText(imoveis.get(position).getCategoria());
        descricao.setText(imoveis.get(position).getDescricao());
        //valor.setText((double) imoveis.get(position).getValorAluguel());

        return linha;
    }
}
