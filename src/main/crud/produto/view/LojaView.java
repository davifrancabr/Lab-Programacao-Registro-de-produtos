package main.crud.produto.view;

import main.crud.produto.model.ItemCarrinho;
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
        setTitle("🍔 Cardápio Digital - Bem-vindo!");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void criarComponentes() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("🍽️ Faça seu Pedido", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        add(criarPainelPrincipal(), BorderLayout.CENTER);
        add(criarBotaoFinalizar(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelPrincipal() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        panel.add(criarPainelCardapio());
        panel.add(criarPainelCarrinho());

        return panel;
    }

    private JPanel criarPainelCardapio() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("📋 Cardápio"));

        String[] colunas = {"Produto", "Descrição", "Preço"};
        modelCardapio = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaCardapio = new JTable(modelCardapio);
        tabelaCardapio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnAdicionar = new JButton("➕ Adicionar ao Carrinho");
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 13));
        btnAdicionar.addActionListener(e -> adicionarAoCarrinho());

        panel.add(new JScrollPane(tabelaCardapio), BorderLayout.CENTER);
        panel.add(btnAdicionar, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarPainelCarrinho() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("🛒 Seu Carrinho"));

        String[] colunas = {"Produto", "Qtd", "Preço Unit.", "Subtotal"};
        modelCarrinho = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaCarrinho = new JTable(modelCarrinho);
        tabelaCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lblTotal = new JLabel("Total: R$ 0,00", JLabel.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 5, 0));

        JButton btnAumentar = new JButton("➕");
        btnAumentar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAumentar.setToolTipText("Aumentar quantidade");
        btnAumentar.addActionListener(e -> alterarQuantidade(1));

        JButton btnDiminuir = new JButton("➖");
        btnDiminuir.setFont(new Font("Arial", Font.BOLD, 14));
        btnDiminuir.setToolTipText("Diminuir quantidade");
        btnDiminuir.addActionListener(e -> alterarQuantidade(-1));

        JButton btnRemover = new JButton("🗑️ Remover");
        btnRemover.setFont(new Font("Arial", Font.BOLD, 13));
        btnRemover.addActionListener(e -> removerDoCarrinho());

        painelBotoes.add(btnAumentar);
        painelBotoes.add(btnDiminuir);
        painelBotoes.add(btnRemover);

        panel.add(lblTotal, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabelaCarrinho), BorderLayout.CENTER);
        panel.add(painelBotoes, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarBotaoFinalizar() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton btnFinalizar = new JButton("✅ Finalizar Pedido");
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 16));
        btnFinalizar.setBackground(new Color(34, 139, 34));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setPreferredSize(new Dimension(200, 40));

        btnFinalizar.addActionListener(e -> finalizarPedido());

        panel.add(btnFinalizar);
        return panel;
    }

    private void carregarCardapio() {
        modelCardapio.setRowCount(0);
        for (Produto p : produtoController.listar()) {
            modelCardapio.addRow(new Object[]{
                    p.getNome(),
                    p.getDescricao(),
                    String.format("R$ %.2f", p.getPreco())
            });
        }
    }

    private void adicionarAoCarrinho() {
        int linha = tabelaCardapio.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Selecione um produto do cardápio!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produto produto = produtoController.listar().get(linha);

        // Solicita a quantidade
        String quantidadeStr = JOptionPane.showInputDialog(this,
                "Quantas unidades de " + produto.getNome() + "?",
                "1");

        if (quantidadeStr == null) {
            return; // Usuário cancelou
        }

        try {
            int quantidade = Integer.parseInt(quantidadeStr.trim());

            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this,
                        "❌ Quantidade deve ser maior que zero!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            carrinhoController.adicionarAoCarrinho(produto, quantidade);
            atualizarCarrinho();

            JOptionPane.showMessageDialog(this,
                    String.format("✅ %d x %s adicionado(s) ao carrinho!", quantidade, produto.getNome()),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Digite um número válido!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarQuantidade(int delta) {
        int linha = tabelaCarrinho.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Selecione um item do carrinho!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carrinho carrinho = carrinhoController.getCarrinhoAtual();
        ItemCarrinho item = carrinho.getItens().get(linha);
        int novaQuantidade = item.getQuantidade() + delta;

        if (novaQuantidade <= 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Deseja remover " + item.getProduto().getNome() + " do carrinho?",
                    "Confirmar Remoção",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                carrinhoController.removerDoCarrinho(item.getProduto().getId());
                atualizarCarrinho();
            }
        } else {
            carrinhoController.atualizarQuantidade(item.getProduto().getId(), novaQuantidade);
            atualizarCarrinho();
        }
    }

    private void removerDoCarrinho() {
        int linha = tabelaCarrinho.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Selecione um item do carrinho!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carrinho carrinho = carrinhoController.getCarrinhoAtual();
        ItemCarrinho item = carrinho.getItens().get(linha);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja remover " + item.getProduto().getNome() + " do carrinho?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            carrinhoController.removerDoCarrinho(item.getProduto().getId());
            atualizarCarrinho();

            JOptionPane.showMessageDialog(this,
                    "🗑️ Item removido do carrinho!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void atualizarCarrinho() {
        modelCarrinho.setRowCount(0);
        Carrinho carrinho = carrinhoController.getCarrinhoAtual();

        if (carrinho != null) {
            for (ItemCarrinho item : carrinho.getItens()) {
                modelCarrinho.addRow(new Object[]{
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        String.format("R$ %.2f", item.getProduto().getPreco()),
                        String.format("R$ %.2f", item.getSubtotal())
                });
            }
            lblTotal.setText(String.format("Total: R$ %.2f", carrinho.getTotal()));
        }
    }

    private void finalizarPedido() {
        Carrinho carrinho = carrinhoController.getCarrinhoAtual();
        if (carrinho == null || carrinho.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "🛒 Seu carrinho está vazio!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder resumo = new StringBuilder();
        resumo.append("Confirmar pedido?\n\n");
        resumo.append("Cliente: ").append(carrinho.getCliente().getNome()).append("\n\n");
        resumo.append("Itens:\n");

        for (ItemCarrinho item : carrinho.getItens()) {
            resumo.append(String.format("• %s x%d - R$ %.2f\n",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getSubtotal()));
        }

        resumo.append(String.format("\nTotal: R$ %.2f", carrinho.getTotal()));

        int confirm = JOptionPane.showConfirmDialog(this,
                resumo.toString(),
                "Finalizar Pedido",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            carrinhoController.finalizarPedido();

            JOptionPane.showMessageDialog(this,
                    "✅ Pedido confirmado com sucesso!\n📧 E-mail de confirmação enviado.\n\nObrigado pela preferência!",
                    "Pedido Finalizado",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();
        }
    }
    public static void main(String[] args) {

        Cliente clienteTeste = new Cliente("Teste", "teste@email.com", "8299999-9999");
        new LojaView(clienteTeste);
    }
}