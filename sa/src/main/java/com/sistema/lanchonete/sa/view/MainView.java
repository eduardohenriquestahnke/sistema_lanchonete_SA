package com.sistema.app.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.sistema.app.model.dao.Role;
import com.sistema.app.model.dao.Usuario;

public class MainView extends JFrame {
    private final Usuario user;
    private JTabbedPane tabs;

    public MainView(Usuario user) {
        this.user = user;
        init();
    }

    private void init() {
        setTitle("Sistema - " + user.getUsername() + " (" + user.getRole() + ")");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabs = new JTabbedPane();

        ProdutoView produtoView = new ProdutoView();
        ClienteView clienteView = new ClienteView();
        PedidoView pedidoView = new PedidoView();

        tabs.addTab("Home", new JLabel("<html><h2>Bem-vindo, " + user.getUsername() + "!</h2></html>"));
        tabs.addTab("Produtos", produtoView);
        tabs.addTab("Clientes", clienteView);
        tabs.addTab("Pedidos", pedidoView);

        applyPermissions();

        add(tabs, BorderLayout.CENTER);
    }

    private void applyPermissions() {
        Role r = user.getRole();
        // indices: 0=Home,1=Produtos,2=Clientes,3=Pedidos
        if (r == Role.CLIENTE) {
            tabs.setEnabledAt(1, false);
            tabs.setEnabledAt(2, false);
            tabs.setSelectedIndex(3);
        } else if (r == Role.FUNCIONARIO) {
            tabs.setEnabledAt(1, true);
            tabs.setEnabledAt(2, false);
            tabs.setEnabledAt(3, true);
            tabs.setSelectedIndex(1);
        } else if (r == Role.MASTER) {
            tabs.setEnabledAt(1, true);
            tabs.setEnabledAt(2, true);
            tabs.setEnabledAt(3, true);
            tabs.setSelectedIndex(1);
        }
    }
}
