package com.sistema.lanchonete.sa.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sistema.lanchonete.sa.model.entity.Produto;
import com.sistema.lanchonete.sa.resources.ConnectionFactory;

public class DAOProduto {

    public void save(Produto produto){
        String sql = "INSERT INTO produto(ds_produto, nm_produto, vl_unitario) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getDescricaoProduto());
            stmt.setString(2, produto.getNomeProduto());
            stmt.setFloat(3, produto.getValorProduto());
            stmt.executeUpdate();
            System.out.println("Produto Incluido com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gravar o produto:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        
    }

    public List<Produto> findAllProdutos(){
        String sql = "select * from produto";
        List<Produto> produtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while( rs.next()){
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id Produto"));
                p.setNomeProduto(rs.getString("nome produto"));
                p.setDescricaoProduto(rs.getString("descrição produto"));
                p.setValorProduto(rs.getFloat("valor produto"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar produto \n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return produtos;
    }

    public List<Produto> findByIdProduto(Produto produtoPorId){
        String sql = "select * from produto where id_produto = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, produtoPorId.getIdProduto());
            rs = stmt.executeQuery();

            while( rs.next()){
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("id Produto"));
                p.setNomeProduto(rs.getString("nome produto"));
                p.setDescricaoProduto(rs.getString("descrição produto"));
                p.setValorProduto(rs.getFloat("valor produto"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto pelo ID: \n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return produtos;
    }

    public void updateProduto(Produto produto){
        String sql = "update produto set nm_produto = ?, ds_produto = ?, vl_produto = ? where id_produto = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getDescricaoProduto());
            stmt.setFloat(3, produto.getValorProduto());
            stmt.setInt(4, produto.getIdProduto());
            stmt.executeUpdate();
            System.out.println("Produto Alterado com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar o produto:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }  
    
    public void deleteProduto(Produto produto){
        String sql = "DELETE * FROM produto where id_produto = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, produto.getIdProduto());
            stmt.executeUpdate();
            System.out.println("Produto Excluido com sucesso.");
        
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o produto:\n", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

}
