package com.example.applace.ClasseNegocio;

import android.content.ContentValues;

public class Usuario {
    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String tipo;

    public Usuario(String nome, String cpf, String email, String senha, String tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public ContentValues getContentValues(){
        ContentValues contValues= new ContentValues();
        contValues.put("nome",this.nome);
        contValues.put("cpf",this.cpf);
        contValues.put("email",this.email);
        contValues.put("senha",this.senha);
        contValues.put("tipo",this.tipo);
        return contValues;
    }
}
