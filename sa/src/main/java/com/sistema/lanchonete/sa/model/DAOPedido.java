package com.sistema.lanchonete.sa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sistema.lanchonete.sa.resources.ConnectionFactory;

public class DAOPedido {

    public void savePedido(Pedido pedido){
        String sql = "INSERT INTO pedido(dt_pedido, vl_pedido, st_pedido) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, pedido.getDataPedido());
            stmt.setFloat(2, pedido.getValorPedido());
            stmt.setString(3, pedido.getSituacaoPedido());
            stmt.executeUpdate();
            System.out.println("Pedido Incluido com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gravar o pedido:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        
    }

    public List<Pedido> findAllPedidos(){
        String sql = "select * from pedido";
        List<Pedido> pedidos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while( rs.next()){
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id Pedido"));
                p.setDataPedido(rs.getDate("data pedido"));
                p.setValorPedido(rs.getFloat("valor pedido"));
                p.setSituacaoPedido(rs.getString("situação pedido"));
                pedidos.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar pedido \n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return pedidos;
    }

    public List<Pedido> findByIdPedido(Pedido pedidoPorId){
        String sql = "select * from pedido where id_pedido = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedidoPorId.getIdPedido());
            rs = stmt.executeQuery();

            while( rs.next()){
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id Pedido"));
                p.setDataPedido(rs.getDate("data pedido"));
                p.setValorPedido(rs.getFloat("valor pedido"));
                p.setSituacaoPedido(rs.getString("situação pedido"));
                pedidos.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto pelo ID: \n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return pedidos;
    }

    public void updatePedido(Pedido pedido){
        String sql = "update pedido set dt_pedido = ?, vl_pedido = ?, st_pedido = ? where id_pedido = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, pedido.getDataPedido());
            stmt.setFloat(2, pedido.getValorPedido());
            stmt.setString(3, pedido.getSituacaoPedido());
            stmt.setInt(4, pedido.getIdPedido());
            stmt.executeUpdate();
            System.out.println("Pedido Alterado com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar o pedido:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }  
    
    public void deletePedido(Pedido pedido){
        String sql = "delete * from pedido where id_pedido = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedido.getIdPedido());
            stmt.executeUpdate();
            System.out.println("Pedido Excluido com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o pedido:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
}
