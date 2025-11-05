package com.sistema.lanchonete.sa.controller;

import java.util.List;

public class ProdutoController {

private final ProdutoService service = new ProdutoService();

public void salvar(String nome) {

        service.salvarProduto(nome); 

    }

    public List<Produto> listarTodos() {

        return service.buscarTodos();

    }

}
