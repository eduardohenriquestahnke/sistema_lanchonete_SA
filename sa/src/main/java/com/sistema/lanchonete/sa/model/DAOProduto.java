package com.sistema.lanchonete.sa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
