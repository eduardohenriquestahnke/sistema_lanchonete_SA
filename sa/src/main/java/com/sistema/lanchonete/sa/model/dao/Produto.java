package com.sistema.lanchonete.sa.model.dao;

import java.math.BigDecimal;

public class Produto {
    private Integer idProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    public Produto() {}
    public Produto(Integer id, String nome, String descricao, BigDecimal preco) {
        this.idProduto = id; this.nome = nome; this.descricao = descricao; this.preco = preco;
    }
    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    @Override
    public String toString() {
        return nome + " (R$" + (preco != null ? preco.toPlainString() : "0.00") + ")";
    }
}
