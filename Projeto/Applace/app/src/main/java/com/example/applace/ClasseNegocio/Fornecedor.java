package com.example.applace.ClasseNegocio;

import java.util.ArrayList;

public class Fornecedor extends Usuario {
    ArrayList<Imovel> imoveis;

    public Fornecedor(String nome, String cpf, String email, String senha, String tipo) {
        super(nome, cpf, email, senha, tipo);
        imoveis = new ArrayList<Imovel>();
    }
    public ArrayList<Imovel> getImoveis() {
        return imoveis;
    }
    public void setImoveis(ArrayList<Imovel> imoveis) {
        this.imoveis = imoveis;
    }
}
