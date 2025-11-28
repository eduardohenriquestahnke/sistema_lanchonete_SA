package com.sistema.app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sistema.app.db.ConnectionFactory;
import com.sistema.app.model.dao.Role;
import com.sistema.app.model.dao.Usuario;

/**
 * Controller simplificado para autenticação usando tabela users.
 */
public class LoginController {

    public Usuario authenticate(String username, String password) {
        String sql = "SELECT id, username, password, role FROM users WHERE username = ? AND password = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setRole(Role.valueOf(rs.getString("role")));
                    return u;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro autenticar", e);
        }
        return null;
    }
}
