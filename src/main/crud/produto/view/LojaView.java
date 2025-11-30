package main.crud.produto.view;

import main.crud.produto.model.Produto;
import main.crud.produto.model.Carrinho;
import main.crud.produto.model.Cliente;
import main.crud.produto.controller.ProdutoController;
import main.crud.produto.controller.CarrinhoController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class LojaView extends JFrame {
    private CarrinhoController carrinhoController;
    private ProdutoController produtoController;
    private JTable tabelaCardapio, tabelaCarrinho;
    private DefaultTableModel modelCardapio, modelCarrinho;
    private JLabel lblTotal;

    public LojaView(Cliente cliente) {
        this.produtoController = new ProdutoController();
        this.carrinhoController = new CarrinhoController();
        this.carrinhoController.iniciarNovoCarrinho(cliente);

        configurarTela();
        criarComponentes();
        carregarCardapio();
        setVisible(true);
    }

    private void configurarTela() {
        setTitle(" Cardápio Digital - Bem-vindo!");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void criarComponentes() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Faça seu Pedido", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);


        add(criarPainelPrincipal(), BorderLayout.CENTER);

        add(criarBotaoFinalizar(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelPrincipal() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(criarPainelCardapio());

        panel.add(criarPainelCarrinho());

        return panel;
    }

    private JScrollPane criarPainelCardapio() {
        String[] colunas = {"Produto", "Descrição", "Preço"};
        modelCardapio = new DefaultTableModel(colunas, 0);
        tabelaCardapio = new JTable(modelCardapio);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Cardápio"));

        JButton btnAdicionar = new JButton("➕ Adicionar ao Carrinho");
        btnAdicionar.addActionListener(e -> adicionarAoCarrinho());

        panel.add(new JScrollPane(tabelaCardapio), BorderLayout.CENTER);
        panel.add(btnAdicionar, BorderLayout.SOUTH);

        return new JScrollPane(panel);
    }

    private JScrollPane criarPainelCarrinho() {
        String[] colunas = {"Produto", "Preço"};
        modelCarrinho = new DefaultTableModel(colunas, 0);
        tabelaCarrinho = new JTable(modelCarrinho);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("🛒 Seu Carrinho"));

        lblTotal = new JLabel("Total: R$ 0,00", JLabel.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnRemover = new JButton("🗑️ Remover");
        btnRemover.addActionListener(e -> removerDoCarrinho());

        JPanel panelBotoes = new JPanel(new GridLayout(1, 2));
        panelBotoes.add(btnRemover);

        panel.add(new JScrollPane(tabelaCarrinho), BorderLayout.CENTER);
        panel.add(lblTotal, BorderLayout.NORTH);
        panel.add(panelBotoes, BorderLayout.SOUTH);

        return new JScrollPane(panel);
    }

    private JPanel criarBotaoFinalizar() {
        JPanel panel = new JPanel();
        JButton btnFinalizar = new JButton("✅ Finalizar Pedido");
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 16));
        btnFinalizar.setBackground(new Color(34, 139, 34));
        btnFinalizar.setForeground(Color.WHITE);

        btnFinalizar.addActionListener(e -> finalizarPedido());

        panel.add(btnFinalizar);
        return panel;
    }

    private void carregarCardapio() {
        modelCardapio.setRowCount(0);
        for (Produto p : produtoController.listar()) {
            modelCardapio.addRow(new Object[]{
                    p.getNome(), p.getDescricao(), "R$ " + p.getPreco()
            });
        }
    }

    private void adicionarAoCarrinho() {
        int linha = tabelaCardapio.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto do cardápio!");
            return;
        }

        Produto produto = produtoController.listar().get(linha);
        carrinhoController.adicionarAoCarrinho(produto);
        atualizarCarrinho();

        JOptionPane.showMessageDialog(this, produto.getNome() + " adicionado ao carrinho! 🛒");
    }

    private void removerDoCarrinho() {
        int linha = tabelaCarrinho.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item do carrinho!");
            return;
        }

        carrinhoController.removerDoCarrinho(linha);
        atualizarCarrinho();
    }

    private void atualizarCarrinho() {
        modelCarrinho.setRowCount(0);
        Carrinho carrinho = carrinhoController.getCarrinhoAtual();

        if (carrinho != null) {
            for (Produto p : carrinho.getItens()) {
                modelCarrinho.addRow(new Object[]{
                        p.getNome(), "R$ " + p.getPreco()
                });
            }
            lblTotal.setText("Total: R$ " + carrinho.getTotal());
        }
    }

    private void finalizarPedido() {
        Carrinho carrinho = carrinhoController.getCarrinhoAtual();
        if (carrinho == null || carrinho.getItens().isEmpty()) {JOptionPane.showMessageDialog(this, "Seu carrinho está vazio! 🛒");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Confirmar pedido?\nTotal: R$ " + carrinho.getTotal(), "Finalizar Pedido", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {carrinhoController.finalizarPedido();
            JOptionPane.showMessageDialog(this, "✅ Pedido confirmado! Enviando e-mail...\nObrigado pela compra!");
            dispose();
        }
    }

    public static void main(String[] args) {

        Cliente clienteTeste = new Cliente("Teste", "teste@email.com", "8299999-9999");
        new LojaView(clienteTeste);
    }
}