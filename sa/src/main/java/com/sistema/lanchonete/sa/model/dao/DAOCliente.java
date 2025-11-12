package com.sistema.lanchonete.sa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sistema.lanchonete.sa.model.entity.Cliente;
import com.sistema.lanchonete.sa.resources.ConnectionFactory;

public class DAOCliente {

    public void saveCliente(Cliente cliente){
        String sql = "insert into cliente(nm_cliente, dt_nascimento, nr_cpf, nr_telefone) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setDate(2, cliente.getDataNascimento());
            stmt.setString(3, cliente.getNumeroCpf());
            stmt.setString(4, cliente.getNumeroTelefone());
            stmt.executeUpdate();
            System.out.println("Cliente Incluido com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gravar o cliente:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        
    }

    public List<Cliente> findAllClientes(){
        String sql = "select * from cliente";
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while( rs.next()){
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id Cliente"));
                c.setNomeCliente(rs.getString("nome Cliente"));
                c.setDataNascimento(rs.getDate("data nascimento"));
                c.setNumeroCpf(rs.getString("numero Cpf"));
                c.setNumeroTelefone(rs.getString("numero telefone"));
                clientes.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar cliente \n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return clientes;
    }

    public List<Cliente> findByIdCliente(Cliente clientePorId){
        String sql = "select * from cliente where id_cliente = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clientePorId.getIdCliente());
            rs = stmt.executeQuery();

            while( rs.next()){
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id Cliente"));
                c.setNomeCliente(rs.getString("nome Cliente"));
                c.setDataNascimento(rs.getDate("data nascimento"));
                c.setNumeroCpf(rs.getString("numero Cpf"));
                c.setNumeroTelefone(rs.getString("numero telefone"));
                clientes.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente pelo ID: \n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return clientes;
    }

    public void updateCliente(Cliente cliente){
        String sql = "update cliente set nm_cliente = ?, dt_nascimento = ?, nr_cpf = ?, nr_telefone = ? where id_cliente = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setDate(2, cliente.getDataNascimento());
            stmt.setString(3, cliente.getNumeroCpf());
            stmt.setString(4, cliente.getNumeroTelefone());
            stmt.setInt(5, cliente.getIdCliente());
            stmt.executeUpdate();
            System.out.println("Cliente Alterado com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar o cliente:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }  
    
    public void deleteCliente(Cliente cliente){
        String sql = "DELETE * FROM cliente where id_cliente = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.executeUpdate();
            System.out.println("Cliente Excluido com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o cliente:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
}
