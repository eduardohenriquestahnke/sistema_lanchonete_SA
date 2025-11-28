package com.sistema.app.controller;

import java.util.List;
import java.util.Optional;

import com.sistema.app.model.dao.Cliente;
import com.sistema.app.repository.ClienteRepository;
import com.sistema.app.service.ClienteService;

public class ClienteController {
    private final ClienteService service;
    public ClienteController() { this.service = new ClienteService(new ClienteRepository()); }
    public void salvar(Cliente c) { service.criarCliente(c); }
    public Optional<Cliente> buscarPorId(Integer id) { return service.buscarPorId(id); }
    public List<Cliente> listarTodos() { return service.listarTodos(); }
    public void atualizar(Cliente c) { service.atualizar(c); }
    public void excluir(Integer id) { service.excluir(id); }
}
