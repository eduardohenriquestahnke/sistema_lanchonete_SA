package com.sistema.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.sistema.app.controller.LoginController;
import com.sistema.app.model.dao.Usuario;

public class LoginView extends JFrame {
    private final LoginController controller = new LoginController();
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JLabel lblMsg;

    public LoginView() {
        init();
    }

    private void init() {
        setTitle("Login");
        setSize(360, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);

        c.gridx = 0; c.gridy = 0; panel.add(new JLabel("Usuário:"), c);
        c.gridx = 1; txtUser = new JTextField(15); panel.add(txtUser, c);
        c.gridx = 0; c.gridy = 1; panel.add(new JLabel("Senha:"), c);
        c.gridx = 1; txtPass = new JPasswordField(15); panel.add(txtPass, c);

        JButton btn = new JButton("Entrar");
        btn.addActionListener(e -> tryLogin());
        c.gridx = 1; c.gridy = 2; panel.add(btn, c);

        lblMsg = new JLabel(" ");
        lblMsg.setForeground(Color.RED);

        add(panel, BorderLayout.CENTER);
        add(lblMsg, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btn);
    }

    private void tryLogin() {
        String u = txtUser.getText().trim();
        String p = new String(txtPass.getPassword());
        Usuario user = (Usuario) controller.authenticate(u, p);
        if (user == null) {
            lblMsg.setText("Usuário/senha inválidos.");
            return;
        }
        SwingUtilities.invokeLater(() -> {
            MainView mv = new MainView(user);
            mv.setVisible(true);
            this.dispose();
        });
    }
}
