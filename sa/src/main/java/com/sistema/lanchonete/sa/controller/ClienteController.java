package com.sistema.lanchonete.sa.controller;

import java.util.List;

public class ClienteController {

private final ClienteService service = new ClienteService();

    public void salvar(String nome, String cpf, Date dt_nascimento) {

        service.salvarCliente(nome, cpf, dt_nascimento); 

    }

    public List<Cliente> listarTodos() {

        return service.buscarTodos();

    }

}
