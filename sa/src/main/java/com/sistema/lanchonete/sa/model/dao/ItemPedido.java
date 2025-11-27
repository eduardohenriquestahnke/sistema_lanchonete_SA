package com.sistema.lanchonete.sa.model.dao;

import java.math.BigDecimal;

public class ItemPedido {
    private Integer idItem;
    private Integer idPedido;
    private Produto produto;
    private Integer quantidade;
    private BigDecimal precoUnit;

    public ItemPedido() {}
    public Integer getIdItem() { return idItem; }
    public void setIdItem(Integer idItem) { this.idItem = idItem; }
    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public BigDecimal getPrecoUnit() { return precoUnit; }
    public void setPrecoUnit(BigDecimal precoUnit) { this.precoUnit = precoUnit; }
}
