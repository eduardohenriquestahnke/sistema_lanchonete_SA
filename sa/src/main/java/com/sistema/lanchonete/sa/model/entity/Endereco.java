package com.sistema.lanchonete.sa.model.entity;

public class Endereco {

    private int id_endereco;
    private String nm_pais;
    private String nm_estado;
    private String nm_cidade;
    private String nm_bairro;
    private String nm_rua;
    private int nr_numero;

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getNm_pais() {
        return nm_pais;
    }

    public void setNm_pais(String nm_pais) {
        this.nm_pais = nm_pais;
    }

    public String getNm_estado() {
        return nm_estado;
    }

    public void setNm_estado(String nm_estado) {
        this.nm_estado = nm_estado;
    }

    public String getNm_cidade() {
        return nm_cidade;
    }

    public void setNm_cidade(String nm_cidade) {
        this.nm_cidade = nm_cidade;
    }

    public String getNm_bairro() {
        return nm_bairro;
    }

    public void setNm_bairro(String nm_bairro) {
        this.nm_bairro = nm_bairro;
    }

    public String getNm_rua() {
        return nm_rua;
    }

    public void setNm_rua(String nm_rua) {
        this.nm_rua = nm_rua;
    }

    public int getNr_numero() {
        return nr_numero;
    }

    public void setNr_numero(int nr_numero) {
        this.nr_numero = nr_numero;
    }

}
