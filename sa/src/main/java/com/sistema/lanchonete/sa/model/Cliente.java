package com.sistema.lanchonete.sa.model;

import java.sql.Date;

public class Cliente {
    
    private int idCliente;
    private String nomeCliente;
    private Date dataNascimento;
    private String numeroCpf;
    private String numeroTelefone;

    public Cliente(){

    }

    public int getIdCliente(){
        return idCliente;
    }
    public void setIdCliente(int id){
        this.idCliente = idCliente;
    }

    public String getNomeCliente(){
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }

    public Date getDataNascimento(){
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroCpf(){
        return numeroCpf;
    }
    public void setNumeroCpf(String numeroCpf){
        this.numeroCpf = numeroCpf;
    }

    public String getNumeroTelefone(){
        return numeroTelefone;
    }
    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

}
