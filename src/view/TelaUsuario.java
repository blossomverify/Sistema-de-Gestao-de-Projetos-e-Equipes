package view;

import controller.UsuarioController;
import model.Perfil;
import javax.swing.*;
import java.awt.*;

public class TelaUsuario extends JFrame {

    public TelaUsuario(UsuarioController controller) {
        setTitle("Gestão de Usuários");
        setSize(400, 500);
        setLayout(new GridLayout(9, 2));

        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtCargo = new JTextField();
        JTextField txtLogin = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        JComboBox<Perfil> comboPerfil = new JComboBox<>(Perfil.values());

        add(new JLabel("Nome Completo:"));
        add(txtNome);
        add(new JLabel("CPF (somente números):"));
        add(txtCpf);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Cargo:"));
        add(txtCargo);
        add(new JLabel("Login:"));
        add(txtLogin);
        add(new JLabel("Senha (mín. 8 caracteres):"));
        add(txtSenha);
        add(new JLabel("Perfil:"));
        add(comboPerfil);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String email = txtEmail.getText().trim();
            String cargo = txtCargo.getText().trim();
            String login = txtLogin.getText().trim();
            String senha = new String(txtSenha.getPassword());

            // Validações com mensagens personalizadas
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo Nome Completo é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo CPF é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!cpf.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(this, "CPF inválido! Digite apenas os 11 números, sem pontos ou traços.\nExemplo: 12345678901", "CPF inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo Email é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Email inválido! Verifique se contém '@' e um domínio.\nExemplo: nome@email.com", "Email inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cargo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo Cargo é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (login.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo Login é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (login.length() < 3) {
                JOptionPane.showMessageDialog(this, "O Login deve ter pelo menos 3 caracteres.", "Login inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (senha.length() < 8) {
                JOptionPane.showMessageDialog(this, "A Senha deve ter no mínimo 8 caracteres.", "Senha inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                controller.cadastrarUsuario(
                    nome, cpf, email, cargo, login, senha,
                    (Perfil) comboPerfil.getSelectedItem()
                );
                JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            } catch (RuntimeException ex) {
                String msg = ex.getMessage() != null ? ex.getMessage() : "";
                String cause = ex.getCause() != null ? ex.getCause().getMessage() : "";
                // Mensagens personalizadas para erros de banco
                if (cause.contains("cpf")) {
                    JOptionPane.showMessageDialog(this, "Este CPF já está cadastrado no sistema.", "CPF duplicado", JOptionPane.ERROR_MESSAGE);
                } else if (cause.contains("email")) {
                    JOptionPane.showMessageDialog(this, "Este e-mail já está cadastrado no sistema.", "Email duplicado", JOptionPane.ERROR_MESSAGE);
                } else if (cause.contains("login")) {
                    JOptionPane.showMessageDialog(this, "Este login já está em uso. Escolha outro.", "Login duplicado", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar: " + msg, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(btnSalvar);
        setLocationRelativeTo(null);
    }
}
