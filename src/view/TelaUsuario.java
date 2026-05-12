package view;

import controller.UsuarioController;
import model.Perfil;
import javax.swing.*;
import java.awt.*;

public class TelaUsuario extends JFrame {
    private UsuarioController controller;

    public TelaUsuario(UsuarioController controller) {
        this.controller = controller;
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
        add(new JLabel("CPF:"));
        add(txtCpf);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Cargo:"));
        add(txtCargo);
        add(new JLabel("Login:"));
        add(txtLogin);
        add(new JLabel("Senha:"));
        add(txtSenha);
        add(new JLabel("Perfil:"));
        add(comboPerfil);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                controller.cadastrarUsuario(
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtEmail.getText(),
                    txtCargo.getText(),
                    txtLogin.getText(),
                    new String(txtSenha.getPassword()),
                    (Perfil) comboPerfil.getSelectedItem()
                );
                JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        add(btnSalvar);
        setLocationRelativeTo(null);
    }
}
