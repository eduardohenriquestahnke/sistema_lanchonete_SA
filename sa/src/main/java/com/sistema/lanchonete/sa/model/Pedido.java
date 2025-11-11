package com.sistema.lanchonete.sa.model;

import java.sql.Date;

public class Pedido {
    private int idPedido;
    private Date dataPedido;
    private float valorPedido;
    private String situacaoPedido;

    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public Date getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }
    public float getValorPedido() {
        return valorPedido;
    }
    public void setValorPedido(float valorPedido) {
        this.valorPedido = valorPedido;
    }
    public String getSituacaoPedido() {
        return situacaoPedido;
    }
    public void setSituacaoPedido(String situacaoPedido) {
        this.situacaoPedido = situacaoPedido;
    }
    
}
