package com.sistema.lanchonete.sa.model.dao;

public class Usuario {
    private Integer id;
    private String username;
    private String password;
    private Role role;

    public Usuario() {}

    public Usuario(Integer id, String username, String password, Role role) {
        this.id = id; this.username = username; this.password = password; this.role = role;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
