package com.example.applace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applace.db.DBHelp;
import com.example.applace.ClasseNegocio.Usuario;

public class Autenticacao extends AppCompatActivity {
    private EditText et_email;                                                                                                                                        // 5 criar variaveis globais para acessar de varios metodos
    private EditText et_senha;
    private Button bt_entrar;
    private Button bt_cadastrarSe;
    private DBHelp db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);
        ConfigurarComponents();  // 6 metodo que inicializa e conecta os elentos da classe com elemntos de layout xml
        OuvirBotoes();
        db=new DBHelp(this);
    }
    private void ConfigurarComponents() {
        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        bt_cadastrarSe = findViewById(R.id.bt_cadastrarSe);
    }
    private void OuvirBotoes() {
        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u =new Usuario("","",et_email.getText().toString(),et_senha.getText().toString(),"");
                u= db.buscarUsuario(u);
                if(u!=null){
                    Intent logar = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(logar);
                    finish();
                }
                else if(u==null){
                    Toast.makeText(getApplicationContext(), "email ou senha errada!", Toast.LENGTH_LONG).show();
                }
            }
        });
        bt_cadastrarSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParacadastro = new Intent(getApplicationContext(),ZformUsuario.class);
                startActivity(irParacadastro);
                finish();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}