package com.sistema.app.view;

import com.sistema.app.controller.ProdutoController;
import com.sistema.app.model.dao.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

public class ProdutoView extends JPanel {
    private final ProdutoController controller = new ProdutoController();
    private JTable table;
    private DefaultTableModel model;

    public ProdutoView() {
        init();
        loadTable();
    }

    private void init() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Preço"}, 0) {
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

    private void openForm(Produto p) {
        JTextField nome = new JTextField();
        JTextArea desc = new JTextArea(4, 20);
        JTextField preco = new JTextField();
        if (p != null) {
            nome.setText(p.getNome());
            desc.setText(p.getDescricao());
            preco.setText(p.getPreco().toPlainString());
        }
        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Nome:")); panel.add(nome);
        panel.add(new JLabel("Descrição:")); panel.add(new JScrollPane(desc));
        panel.add(new JLabel("Preço:")); panel.add(preco);

        int option = JOptionPane.showConfirmDialog(this, panel, p==null? "Novo Produto":"Editar Produto", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                if (nome.getText().isBlank()) throw new IllegalArgumentException("Nome obrigatório");
                BigDecimal pr = new BigDecimal(preco.getText().trim());
                if (p == null) p = new Produto();
                p.setNome(nome.getText().trim());
                p.setDescricao(desc.getText().trim());
                p.setPreco(pr);
                if (p.getIdProduto() == null) controller.salvar(p);
                else controller.atualizar(p);
                loadTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void loadTable() {
        model.setRowCount(0);
        List<Produto> lista = controller.listarTodos();
        for (Produto p : lista) {
            Vector<Object> row = new Vector<>();
            row.add(p.getIdProduto());
            row.add(p.getNome());
            row.add(p.getDescricao());
            row.add(p.getPreco());
            model.addRow(row);
        }
    }
}
