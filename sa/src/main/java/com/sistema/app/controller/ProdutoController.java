package com.sistema.app.controller;

import java.util.List;
import java.util.Optional;

import com.sistema.app.model.dao.Produto;
import com.sistema.app.repository.ProdutoRepository;
import com.sistema.app.service.ProdutoService;

public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController() { this.service = new ProdutoService(new ProdutoRepository()); }
    public void salvar(Produto p) { service.criarProduto(p); }
    public Optional<Produto> buscarPorId(Integer id) { return service.buscarPorId(id); }
    public List<Produto> listarTodos() { return service.listarTodos(); }
    public void atualizar(Produto p) { service.atualizar(p); }
    public void excluir(Integer id) { service.excluir(id); }
}
