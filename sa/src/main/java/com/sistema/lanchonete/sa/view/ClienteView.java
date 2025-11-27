package com.sistema.app.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sistema.app.controller.ClienteController;
import com.sistema.app.model.dao.Cliente;

public class ClienteView extends JPanel {
    private final ClienteController controller = new ClienteController();
    private JTable table;
    private DefaultTableModel model;

    public ClienteView() {
        init();
        loadTable();
    }

    private void init() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Telefone", "Email"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(e -> openForm(null));
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel >= 0) {
                Integer id = (Integer) model.getValueAt(sel, 0);
                controller.buscarPorId(id).ifPresent(this::openForm);
            }
        });
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel >= 0) {
                Integer id = (Integer) model.getValueAt(sel, 0);
                if (JOptionPane.showConfirmDialog(this, "Confirma exclusão?") == JOptionPane.YES_OPTION) {
                    controller.excluir(id);
                    loadTable();
                }
            }
        });

        bottom.add(btnNovo); bottom.add(btnEditar); bottom.add(btnExcluir);
        add(bottom, BorderLayout.SOUTH);
    }

    private void openForm(Cliente c) {
        JTextField nome = new JTextField();
        JTextField tel = new JTextField();
        JTextField email = new JTextField();
        if (c != null) {
            nome.setText(c.getNome());
            tel.setText(c.getTelefone());
            email.setText(c.getEmail());
        }
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Nome:")); panel.add(nome);
        panel.add(new JLabel("Telefone:")); panel.add(tel);
        panel.add(new JLabel("Email:")); panel.add(email);

        int option = JOptionPane.showConfirmDialog(this, panel, c==null? "Novo Cliente":"Editar Cliente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                if (nome.getText().isBlank()) throw new IllegalArgumentException("Nome obrigatório");
                if (c == null) c = new Cliente();
                c.setNome(nome.getText().trim());
                c.setTelefone(tel.getText().trim());
                c.setEmail(email.getText().trim());
                if (c.getIdCliente() == null) controller.salvar(c);
                else controller.atualizar(c);
                loadTable();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void loadTable() {
        model.setRowCount(0);
        List<Cliente> lista = controller.listarTodos();
        for (Cliente c : lista) {
            Vector<Object> row = new Vector<>();
            row.add(c.getIdCliente());
            row.add(c.getNome());
            row.add(c.getTelefone());
            row.add(c.getEmail());
            model.addRow(row);
        }
    }
}
