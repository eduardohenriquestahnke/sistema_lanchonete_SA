package com.sistema.app.controller;

import java.util.List;
import java.util.Optional;

import com.sistema.app.model.dao.Pedido;
import com.sistema.app.repository.PedidoRepository;
import com.sistema.app.service.PedidoService;

public class PedidoController {
    private final PedidoService service;
    public PedidoController() { this.service = new PedidoService(new PedidoRepository()); }
    public void criarPedido(Pedido p) { service.criarPedido(p); }
    public Optional<Pedido> buscarPorId(Integer id) { return service.buscarPorId(id); }
    public List<Pedido> listarTodos() { return service.listarTodos(); }
    public void atualizarStatus(Pedido p) { service.atualizarStatus(p); }
    public void excluir(Integer id) { service.excluir(id); }
}
