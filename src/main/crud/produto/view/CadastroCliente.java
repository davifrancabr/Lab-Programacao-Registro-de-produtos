package main.crud.produto.view;

import main.crud.produto.model.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroCliente extends JFrame {
    private JTextField txtNome, txtEmail, txtTelefone;

    public CadastroCliente() {
        configurarTela();
        criarComponentes();
        setVisible(true);
    }

    private void configurarTela() {
        setTitle("👤 Cadastro do Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void criarComponentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Seus Dados", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        panel.add(criarFormulario(), BorderLayout.CENTER);

        panel.add(criarBotaoContinuar(), BorderLayout.SOUTH);

        add(panel);
    }

    private JPanel criarFormulario() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("👤 Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("📧 E-mail:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("📞 Telefone:"));
        txtTelefone = new JTextField();
        panel.add(txtTelefone);

        return panel;
    }

    private JButton criarBotaoContinuar() {
        JButton btnContinuar = new JButton("🛒 Continuar para o Cardápio");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 14));
        btnContinuar.setBackground(new Color(34, 139, 34));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFocusPainted(false);

        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continuarParaCardapio();
            }
        });

        return btnContinuar;
    }

    private void continuarParaCardapio() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "❌ Por favor, preencha pelo menos nome e e-mail!",
                    "Dados Incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, email, telefone);

        JOptionPane.showMessageDialog(this,
                "✅ Cadastro realizado!\nAgora vamos às compras!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        new LojaView(cliente);
        dispose();

        JOptionPane.showMessageDialog(null,
                "Bem vindo(a): " + cliente.getNome());
    }

    public  static void main(String[] args) {
        SwingUtilities.invokeLater(CadastroCliente::new);
    }

}