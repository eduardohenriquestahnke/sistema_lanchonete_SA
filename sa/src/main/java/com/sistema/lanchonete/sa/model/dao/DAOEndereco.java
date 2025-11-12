package com.sistema.lanchonete.sa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sistema.lanchonete.sa.model.entity.Endereco;
import com.sistema.lanchonete.sa.resources.ConnectionFactory;

public class DAOEndereco {

    public void saveEndereco(Endereco endereco){
        String sql = "INSERT INTO endereco(nm_pais, nm_estado, nm_cidade, nm_bairro, nm_rua, nr_numero) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, endereco.getNm_pais());
            stmt.setString(2, endereco.getNm_estado());
            stmt.setString(3, endereco.getNm_cidade());
            stmt.setString(4, endereco.getNm_bairro());
            stmt.setString(5, endereco.getNm_rua());
            stmt.setInt(6, endereco.getNr_numero());
            stmt.executeUpdate();
            System.out.println("Endereço Incluido com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gravar o endereço:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        
    }

    public List<Endereco> findAllEnderecos(){
        String sql = "select * from endereco";
        List<Endereco> enderecos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while( rs.next()){
                Endereco e = new Endereco();
                e.setId_endereco(rs.getInt("id endereco"));
                e.setNm_pais(rs.getString("nome pais"));
                e.setNm_estado(rs.getString("nome estado"));
                e.setNm_bairro(rs.getString("nome bairro"));
                e.setNm_rua(rs.getString("nome rua"));
                e.setNr_numero(rs.getInt("numero"));
                enderecos.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar enderecos \n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return enderecos;
    }

    public List<Endereco> findByIdEndereco(Endereco enderecoPorId){
        String sql = "select * from endereco where id_endereco = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Endereco> enderecos = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enderecoPorId.getId_endereco());
            rs = stmt.executeQuery();

            while( rs.next()){
                Endereco e = new Endereco();
                e.setId_endereco(rs.getInt("id endereco"));
                e.setNm_pais(rs.getString("nome pais"));
                e.setNm_estado(rs.getString("nome estado"));
                e.setNm_bairro(rs.getString("nome bairro"));
                e.setNm_rua(rs.getString("nome rua"));
                e.setNr_numero(rs.getInt("numero"));
                enderecos.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar endereço pelo ID: \n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return enderecos;
    }

    public void updateEndereco(Endereco endereco){
        String sql = "update endereco set nm_pais = ?, set nm_estado = ?, set nm_cidade, set nm_bairro = ?, set nm_rua = ?, set nr_numero = ? where id_endereco = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, endereco.getNm_pais());
            stmt.setString(2, endereco.getNm_estado());
            stmt.setString(3, endereco.getNm_cidade());
            stmt.setString(4, endereco.getNm_bairro());
            stmt.setString(5, endereco.getNm_rua());
            stmt.setInt(6, endereco.getNr_numero());
            stmt.executeUpdate();
            System.out.println("Endereço Alterado com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar o endereço:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }  
    
    public void deleteEndereco(Endereco endereco){
        String sql = "delete * from endereco where id_endereco = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, endereco.getId_endereco());
            stmt.executeUpdate();
            System.out.println("Endereco Excluido com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o endereco:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
}