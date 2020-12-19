package com.example.applace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.applace.ClasseNegocio.Cliente;
import com.example.applace.db.DBHelp;
import com.example.applace.ClasseNegocio.Fornecedor;

public class ZformUsuario extends AppCompatActivity {
    private Cliente x1;
    private Fornecedor f1;
    private DBHelp db;
    
    private EditText et_nome;
    private EditText et_cpf;
    private EditText et_emailcadastro;
    private EditText et_senhacadastro;
    private EditText et_senhacadastro2;
    private Button bt_cadastrar;
    private RadioGroup rb_tipogroupuser;
    private RadioButton rb_cliente;
    private RadioButton rb_fornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zform_usuario);
        db = new DBHelp(this);
        configurarcomponentes();
        configurarbotoes();
    }
    private void configurarcomponentes() {
        et_nome=findViewById(R.id.et_nome);
        et_cpf=findViewById(R.id.et_cpf);
        et_emailcadastro=findViewById(R.id.et_emailcadastro);
        et_senhacadastro=findViewById(R.id.et_senhacadastro);
        et_senhacadastro2=findViewById(R.id.et_senhacadastro2);
        bt_cadastrar=findViewById(R.id.bt_cadastrar);
        rb_tipogroupuser=findViewById(R.id.rb_tipogroupuser);
        rb_cliente=findViewById(R.id.rb_cliente);
        rb_fornecedor=findViewById(R.id.rb_fornecedor);
    }
    private void configurarbotoes() {
        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarLogin = new Intent(getApplicationContext(),Autenticacao.class);
                String nome,cpf,email,senha;
                nome= et_nome.getText().toString();cpf=et_cpf.getText().toString();email=et_emailcadastro.getText().toString();senha=et_senhacadastro.getText().toString();
                if(rb_fornecedor.isChecked()){
                    try {
                        f1 = new  Fornecedor(nome,cpf,email,senha,"fornecedor");
                        Toast.makeText(getApplicationContext(),f1.getEmail().toString()+"Fornecedor", Toast.LENGTH_LONG).show();
                        db.salvarFornecedor(f1);
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
                    }
                }else{
                    x1=new Cliente(nome,cpf,email,senha,"cliente");
                    try {
                        db.salvarCliente(x1);
                        Toast.makeText(getApplicationContext(),x1.getEmail().toString()+"Cliente", Toast.LENGTH_LONG).show();
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
                    }
                }
                startActivity(voltarLogin);
                finish();
            }
        });
    }
}


/*try {
                    db.salvarTarefa(tarefa);//--------------------------------------------
                    Toast.makeText(getApplicationContext(), "Tarefa Cadastrada.", Toast.LENGTH_LONG).show();
                    finish();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Falha no Cadastro.", Toast.LENGTH_LONG).show();
                }*/