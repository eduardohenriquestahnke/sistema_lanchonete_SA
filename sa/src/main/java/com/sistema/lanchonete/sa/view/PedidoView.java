package com.sistema.app.view;

import com.sistema.app.controller.ClienteController;
import com.sistema.app.controller.PedidoController;
import com.sistema.app.controller.ProdutoController;
import com.sistema.app.model.dao.*;

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
    private JComboBox<StatusPedido> cbStatusPedido;

    private JTextField txtQtd;
    private DefaultTableModel modelPedidos;
    private JTable tablePedidos;

    private DefaultTableModel modelItens;
    private JTable tableItens;
    private JLabel lblTotal;

    private Pedido pedidoAtual; // Pedido em edição ou novo

    public PedidoView() {
        init();
        reloadClientes();
        reloadProdutos();
    }

    private void init() {
        setLayout(new BorderLayout());

        // --------------------------
        // Painel superior: ações globais
        // --------------------------
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNovo = new JButton("Novo Pedido");
        btnNovo.addActionListener(e -> criarNovoPedido());

        JButton btnExcluirPedido = new JButton("Excluir Pedido");
        btnExcluirPedido.addActionListener(e -> excluirPedido());

        cbClientes = new JComboBox<>();
        cbClientes.addActionListener(e -> carregarPedidosCliente());

        top.add(new JLabel("Cliente:"));
        top.add(cbClientes);
        top.add(btnNovo);
        top.add(btnExcluirPedido);

        add(top, BorderLayout.NORTH);

        // --------------------------
        // SplitPane: pedidos (esquerda) / itens (direita)
        // --------------------------
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.3);

        // tabela de pedidos
        modelPedidos = new DefaultTableModel(
                new Object[]{"ID", "Data", "Status", "Total", "Cliente"},
                0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tablePedidos = new JTable(modelPedidos);
        tablePedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePedidos.getSelectionModel().addListSelectionListener(e -> carregarPedidoSelecionado());
        splitPane.setLeftComponent(new JScrollPane(tablePedidos));

        // painel de itens do pedido
        JPanel panelItens = new JPanel(new BorderLayout());

        modelItens = new DefaultTableModel(
                new Object[]{"Produto", "Qtd", "Preço Unit", "Subtotal"},
                0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tableItens = new JTable(modelItens);
        panelItens.add(new JScrollPane(tableItens), BorderLayout.CENTER);

        JPanel addPanel = new JPanel();
        cbProdutos = new JComboBox<>();
        txtQtd = new JTextField(4);
        JButton btnAdd = new JButton("Adicionar Item");
        btnAdd.addActionListener(e -> adicionarItem());
        JButton btnRemover = new JButton("Remover Item");
        btnRemover.addActionListener(e -> removerItem());

        addPanel.add(new JLabel("Produto:"));
        addPanel.add(cbProdutos);
        addPanel.add(new JLabel("Qtd:"));
        addPanel.add(txtQtd);
        addPanel.add(btnAdd);
        addPanel.add(btnRemover);

        panelItens.add(addPanel, BorderLayout.SOUTH);

        splitPane.setRightComponent(panelItens);

        add(splitPane, BorderLayout.CENTER);

        // --------------------------
        // Painel inferior: status + salvar + total
        // --------------------------
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbStatusPedido = new JComboBox<>(StatusPedido.values());
        JButton btnSalvar = new JButton("Salvar Pedido");
        btnSalvar.addActionListener(e -> salvarPedido());

        lblTotal = new JLabel("Total: R$ 0.00");

        bottom.add(new JLabel("Status:"));
        bottom.add(cbStatusPedido);
        bottom.add(btnSalvar);
        bottom.add(lblTotal);

        add(bottom, BorderLayout.SOUTH);
    }

    // --------------------------
    // CRUD: Novo Pedido
    // --------------------------
    private void criarNovoPedido() {
        pedidoAtual = new Pedido();
        Cliente c = (Cliente) cbClientes.getSelectedItem();
        if (c != null) pedidoAtual.setCliente(c);
        pedidoAtual.setDataPedido(LocalDateTime.now());
        pedidoAtual.setStatus(StatusPedido.ABERTO);

        modelItens.setRowCount(0);
        cbStatusPedido.setSelectedItem(pedidoAtual.getStatus());
        lblTotal.setText("Total: R$ 0.00");
        tablePedidos.clearSelection();
    }

    // --------------------------
    // CRUD: Excluir Pedido
    // --------------------------
    private void excluirPedido() {
        if (pedidoAtual == null || pedidoAtual.getIdPedido() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para excluir.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir o pedido " + pedidoAtual.getIdPedido() + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                pedidoController.excluir(pedidoAtual.getIdPedido());
                JOptionPane.showMessageDialog(this, "Pedido excluído com sucesso!");
                carregarPedidosCliente();
                pedidoAtual = null;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir pedido: " + e.getMessage());
            }
        }
    }

    // --------------------------
    // Recarrega clientes e produtos
    // --------------------------
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

    // --------------------------
    // Carrega pedidos do cliente selecionado
    // --------------------------
    private void carregarPedidosCliente() {
        Cliente c = (Cliente) cbClientes.getSelectedItem();
        if (c == null) return;

        modelPedidos.setRowCount(0);
        List<Pedido> pedidos = pedidoController.filtrar(null, String.valueOf(c.getIdCliente()), null);
        for (Pedido p : pedidos) {
            BigDecimal total = p.getItens().stream()
                    .map(it -> it.getPrecoUnit().multiply(new BigDecimal(it.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            modelPedidos.addRow(new Object[]{p.getIdPedido(), p.getDataPedido(), p.getStatus(), total, p.getCliente()});
        }

        modelItens.setRowCount(0);
        lblTotal.setText("Total: R$ 0.00");
        pedidoAtual = null;
        cbStatusPedido.setSelectedItem(StatusPedido.ABERTO);
    }

    // --------------------------
    // Carrega pedido selecionado na tabela
    // --------------------------
    private void carregarPedidoSelecionado() {
        int sel = tablePedidos.getSelectedRow();
        if (sel < 0) return;

        Integer idPedido = (Integer) modelPedidos.getValueAt(sel, 0);
        pedidoAtual = pedidoController.buscarPorId(idPedido).orElse(null);
        if (pedidoAtual == null) return;

        cbStatusPedido.setSelectedItem(pedidoAtual.getStatus());

        modelItens.setRowCount(0);
        for (ItemPedido it : pedidoAtual.getItens()) {
            BigDecimal subtotal = it.getPrecoUnit().multiply(new BigDecimal(it.getQuantidade()));
            modelItens.addRow(new Object[]{it.getProduto(), it.getQuantidade(), it.getPrecoUnit(), subtotal});
        }

        atualizarTotal();
    }

    // --------------------------
    // Adicionar/Remover itens
    // --------------------------
    private void adicionarItem() {
        if (pedidoAtual == null) {
            JOptionPane.showMessageDialog(this, "Selecione ou crie um pedido primeiro.");
            return;
        }

        Produto p = (Produto) cbProdutos.getSelectedItem();
        if (p == null) return;

        int qtd;
        try {
            qtd = Integer.parseInt(txtQtd.getText().trim());
            if (qtd <= 0) throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida.");
            return;
        }

        // Verifica duplicidade
        for (int i = 0; i < modelItens.getRowCount(); i++) {
            Produto prodExistente = (Produto) modelItens.getValueAt(i, 0);
            if (prodExistente.getIdProduto().equals(p.getIdProduto())) {
                JOptionPane.showMessageDialog(this, "Produto já adicionado.");
                return;
            }
        }

        BigDecimal subtotal = p.getPreco().multiply(new BigDecimal(qtd));
        modelItens.addRow(new Object[]{p, qtd, p.getPreco(), subtotal});
        atualizarTotal();
    }

    private void removerItem() {
        int sel = tableItens.getSelectedRow();
        if (sel >= 0) {
            modelItens.removeRow(sel);
            atualizarTotal();
        }
    }

    private void atualizarTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < modelItens.getRowCount(); i++) {
            total = total.add((BigDecimal) modelItens.getValueAt(i, 3));
        }
        lblTotal.setText("Total: R$ " + total.toPlainString());
    }

    // --------------------------
    // Salvar pedido (novo ou existente)
    // --------------------------
    private void salvarPedido() {
        if (pedidoAtual == null) {
            JOptionPane.showMessageDialog(this, "Crie ou selecione um pedido primeiro.");
            return;
        }

        if (modelItens.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Adicione ao menos um item.");
            return;
        }

        pedidoAtual.setStatus((StatusPedido) cbStatusPedido.getSelectedItem());
        pedidoAtual.getItens().clear();

        for (int i = 0; i < modelItens.getRowCount(); i++) {
            ItemPedido it = new ItemPedido();
            it.setProduto((Produto) modelItens.getValueAt(i, 0));
            it.setQuantidade((Integer) modelItens.getValueAt(i, 1));
            it.setPrecoUnit((BigDecimal) modelItens.getValueAt(i, 2));
            pedidoAtual.addItem(it);
        }

        try {
            if (pedidoAtual.getIdPedido() == null) {
                pedidoController.criarPedido(pedidoAtual);
                JOptionPane.showMessageDialog(this, "Pedido criado com sucesso! ID: " + pedidoAtual.getIdPedido());
            } else {
                pedidoController.atualizar(pedidoAtual);
                JOptionPane.showMessageDialog(this, "Pedido atualizado com sucesso!");
            }
            carregarPedidosCliente();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar pedido: " + e.getMessage());
        }
    }
}
