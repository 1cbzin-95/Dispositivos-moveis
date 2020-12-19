package com.example.applace.ClasseNegocio;

import android.content.ContentValues;
public class Imovel {
    private Integer id;
    private Integer idFornecedorImovel;
    private String nome;
    private String categoria;
    private double valorAluguel;
    private String descricao;
    private String statuss;
    private String nomeArquivo;
    private String imagem;
    private String dataDisponibilizado;

    public Imovel(String nome, String categoria, double valorAluguel, String descricao, String statuss, String dataDisponibilizado) {
        this.nome = nome;
        this.categoria = categoria;
        this.valorAluguel = valorAluguel;
        this.descricao = descricao;
        this.statuss = statuss;
        //this.nomeArquivo = nomeArquivo;
        this.dataDisponibilizado = dataDisponibilizado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatuss() {
        return statuss;
    }
    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getIdFornecedorImovel() {
        return idFornecedorImovel;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void setIdFornecedorImovel(Integer idFornecedorImovel) {
        this.idFornecedorImovel = idFornecedorImovel;
    }

    public String getDataDisponibilizado() {
        return dataDisponibilizado;
    }

    public void setDataDisponibilizado(String dataDisponibilizado) {
        this.dataDisponibilizado = dataDisponibilizado;
    }

    public ContentValues getContentValues(){
        ContentValues contValues= new ContentValues();
        contValues.put("idFornecedorImovel",this.idFornecedorImovel);
        contValues.put("nome",this.nome);
        contValues.put("categoria",this.categoria);
        contValues.put("valorAluguel",this.valorAluguel);
        contValues.put("descricao",this.descricao);
        contValues.put("statuss",this.statuss);
        contValues.put("nomeArquivo",this.nomeArquivo);
        contValues.put("dataDisponibilizado",this.dataDisponibilizado);
        return contValues;
    }

}
