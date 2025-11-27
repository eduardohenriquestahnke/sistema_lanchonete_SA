package com.sistema.app.view;

import com.sistema.app.controller.ClienteController;
import com.sistema.app.controller.PedidoController;
import com.sistema.app.controller.ProdutoController;
import com.sistema.app.model.dao.Cliente;
import com.sistema.app.model.dao.ItemPedido;
import com.sistema.app.model.dao.Pedido;
import com.sistema.app.model.dao.Produto;
import com.sistema.app.model.dao.StatusPedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoView extends JPanel {
    private final PedidoController pedidoController = new PedidoController();
    private final ClienteController clienteController = new ClienteController();
    private final ProdutoController produtoController = new ProdutoController();

    private JComboBox<Cliente> cbClientes;
    private JComboBox<Produto> cbProdutos;
    private JTextField txtQtd;
    private DefaultTableModel modelItens;
    private JTable tableItens;
    private JLabel lblTotal;

    public PedidoView() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbClientes = new JComboBox<>();
        reloadClientes();
        north.add(new JLabel("Cliente:"));
        north.add(cbClientes);

        add(north, BorderLayout.NORTH);

        modelItens = new DefaultTableModel(new Object[]{"Produto", "Qtd", "Preço Unit", "Subtotal"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tableItens = new JTable(modelItens);
        add(new JScrollPane(tableItens), BorderLayout.CENTER);

        JPanel addPanel = new JPanel();
        cbProdutos = new JComboBox<>();
        reloadProdutos();
        txtQtd = new JTextField(4);
        JButton btnAdd = new JButton("Adicionar");
        btnAdd.addActionListener(e -> adicionarItem());
        addPanel.add(new JLabel("Produto:")); addPanel.add(cbProdutos);
        addPanel.add(new JLabel("Qtd:")); addPanel.add(txtQtd);
        addPanel.add(btnAdd);

        JPanel south = new JPanel(new BorderLayout());
        JPanel actions = new JPanel();
        JButton btnSalvar = new JButton("Salvar Pedido");
        btnSalvar.addActionListener(e -> salvarPedido());
        JButton btnRemover = new JButton("Remover Item");
        btnRemover.addActionListener(e -> {
            int sel = tableItens.getSelectedRow();
            if (sel >= 0) { modelItens.removeRow(sel); atualizarTotal(); }
        });
        actions.add(btnRemover); actions.add(btnSalvar);

        lblTotal = new JLabel("Total: R$ 0.00");
        south.add(addPanel, BorderLayout.NORTH);
        south.add(actions, BorderLayout.CENTER);
        south.add(lblTotal, BorderLayout.SOUTH);

        add(south, BorderLayout.SOUTH);
    }

    private void reloadClientes() {
        cbClientes.removeAllItems();
        List<Cliente> clientes = clienteController.listarTodos();
        for (Cliente c : clientes) cbClientes.addItem(c);
    }

    private void reloadProdutos() {
        cbProdutos.removeAllItems();
        List<Produto> produtos = produtoController.listarTodos();
        for (Produto p : produtos) cbProdutos.addItem(p);
    }

    private void adicionarItem() {
        Produto p = (Produto) cbProdutos.getSelectedItem();
        if (p == null) { JOptionPane.showMessageDialog(this, "Selecione um produto"); return; }
        int qtd;
        try { qtd = Integer.parseInt(txtQtd.getText().trim()); } catch (Exception e) { JOptionPane.showMessageDialog(this, "Qtd inválida"); return; }
        BigDecimal subtotal = p.getPreco().multiply(new BigDecimal(qtd));
        modelItens.addRow(new Object[]{p, qtd, p.getPreco(), subtotal});
        atualizarTotal();
    }

    private void atualizarTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < modelItens.getRowCount(); i++) {
            BigDecimal sub = (BigDecimal) modelItens.getValueAt(i, 3);
            total = total.add(sub);
        }
        lblTotal.setText("Total: R$ " + total.toPlainString());
    }

    private void salvarPedido() {
        Cliente c = (Cliente) cbClientes.getSelectedItem();
        if (c == null) { JOptionPane.showMessageDialog(this, "Selecione cliente"); return; }
        if (modelItens.getRowCount() == 0) { JOptionPane.showMessageDialog(this, "Adicione itens"); return; }
        Pedido pedido = new Pedido();
        pedido.setCliente(c);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.ABERTO);

        for (int i = 0; i < modelItens.getRowCount(); i++) {
            Produto p = (Produto) modelItens.getValueAt(i, 0);
            Integer qtd = (Integer) modelItens.getValueAt(i, 1);
            BigDecimal precoUnit = (BigDecimal) modelItens.getValueAt(i, 2);
            ItemPedido it = new ItemPedido();
            it.setProduto(p);
            it.setQuantidade(qtd);
            it.setPrecoUnit(precoUnit);
            pedido.addItem(it);
        }

        try {
            pedidoController.criarPedido(pedido);
            JOptionPane.showMessageDialog(this, "Pedido salvo com sucesso. ID: " + pedido.getIdPedido());
            modelItens.setRowCount(0);
            atualizarTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar pedido: " + e.getMessage());
        }
    }
}
