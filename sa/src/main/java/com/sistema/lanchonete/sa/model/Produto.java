package com.sistema.lanchonete.sa.model;

public class Produto {
    private final int idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private Float valorProduto;

    public Produto() {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.valorProduto = valorProduto;
    } 

    public int getIdProduto(){
        return idProduto;
    }
    public void setIdProduto(int idProduto){
        this.idProduto = idProduto;
    }

    public String getNomeProduto(){
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto){
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto(){
        return descricaoProduto;
    }
    public void setDescricaoProduto(String descricaoProduto){
        this.descricaoProduto = descricaoProduto;
    }
    
    public Float getValorProduto(){
        return valorProduto;
    }
    public void setValorProduto(Float valorProduto){
        this.valorProduto = valorProduto;
    }

    
}