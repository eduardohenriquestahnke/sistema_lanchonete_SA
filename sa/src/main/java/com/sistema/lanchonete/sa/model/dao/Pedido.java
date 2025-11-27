package com.sistema.lanchonete.sa.model.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Pedido {
    private Integer idPedido;
    private Cliente cliente;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {}
    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }
    public void addItem(ItemPedido item) { itens.add(item); }

    public BigDecimal calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido it : itens) {
            BigDecimal qtd = new BigDecimal(it.getQuantidade());
            total = total.add(it.getPrecoUnit().multiply(qtd));
        }
        return total;
    }
}
