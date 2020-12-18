package com.example.applace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.applace.db.DBHelp;
import com.example.applace.ClasseNegocio.Imovel;

public class ZformImovel extends AppCompatActivity {
    private DBHelp db;
    private EditText et_nome_cadastroImovel;
    private EditText et_categoria_cadastroImovel;
    private EditText et_valor_cadastroImovel;
    private EditText et_descricao_cadastroImovel;
    private EditText et_data;
    private ImageView imv_cadastrda;
    private Button imb_imCadastro;
    private Button bt_cadastrarImovel;

    private final int PERMISSAO_REQUEST=2;
    private final int GALERIA_IMAGENS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zform_imovel);
        db =new DBHelp(this);
        configComponentes();
        ouvirBotoeses();

        if (ContextCompat.checkSelfPermission(this,                                                 //Uuarioinformar permissão
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }
    }
    private void ouvirBotoeses() {
        bt_cadastrarImovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String nome, String categoria, double valorAluguel, String descricao, String statuss, String dataDisponibilizado*/
                double valor = Double.parseDouble(et_valor_cadastroImovel.getText().toString());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                Imovel im = new Imovel(et_nome_cadastroImovel.getText().toString(),et_categoria_cadastroImovel.getText().toString(),valor,
                        et_descricao_cadastroImovel.getText().toString(),"indisponivel",et_data.getText().toString());
                im.setIdFornecedorImovel(1);im.setNomeArquivo("algumacoisa.JPG");
                db.salvarImovel(im);
                startActivity(i);
                finish();
            }
        });
        imb_imCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALERIA_IMAGENS);
            }
        });
    }
    private void configComponentes() {
        et_nome_cadastroImovel=findViewById(R.id.et_nome_cadastroImovel);
        et_descricao_cadastroImovel=findViewById(R.id.et_descricao_cadastroImovel);
        et_categoria_cadastroImovel=findViewById(R.id.et_categoria_cadastroImovel);
        imb_imCadastro=findViewById(R.id.imb_imCadastro);
        bt_cadastrarImovel=findViewById(R.id.bt_cadastrarImovel);
        imv_cadastrda=findViewById(R.id.imv_cadastrda);
        et_valor_cadastroImovel=findViewById(R.id.et_valor_cadastroImovel);
        et_data = findViewById(R.id.et_data);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS) {
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            imv_cadastrda.setImageBitmap(thumbnail);
        }
    }
    public void onRequestPermissionsResult(int requestCode,                                         //quando ususario escolher
        String permissions[], int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // A permissão foi concedida. Pode continuar
            } else {
                    // A permissão foi negada. Precisa ver o que deve ser desabilitado
            }
        }
    }
}