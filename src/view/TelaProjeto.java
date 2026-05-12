package view;

import controller.ProjetoController;
import controller.UsuarioController;
import model.StatusProjeto;
import model.Usuario;
import javax.swing.*;
import java.awt.*;

public class TelaProjeto extends JFrame {
    private ProjetoController controller;
    private UsuarioController usuarioController;

    public TelaProjeto(ProjetoController controller, UsuarioController usuarioController) {
        this.controller = controller;
        this.usuarioController = usuarioController;
        setTitle("Gestão de Projetos");
        setSize(400, 400);
        setLayout(new GridLayout(7, 2));

        JTextField txtNome = new JTextField();
        JTextField txtDescricao = new JTextField();
        JTextField txtInicio = new JTextField();
        JTextField txtTermino = new JTextField();
        JComboBox<StatusProjeto> comboStatus = new JComboBox<>(StatusProjeto.values());
        JComboBox<String> comboGerente = new JComboBox<>();
        usuarioController.listarUsuarios().forEach(u -> comboGerente.addItem(u.getLogin()));

        add(new JLabel("Nome do Projeto:"));
        add(txtNome);
        add(new JLabel("Descrição:"));
        add(txtDescricao);
        add(new JLabel("Data Início:"));
        add(txtInicio);
        add(new JLabel("Data Término:"));
        add(txtTermino);
        add(new JLabel("Status:"));
        add(comboStatus);
        add(new JLabel("Gerente (Login):"));
        add(comboGerente);

        JButton btnSalvar = new JButton("Criar");
        btnSalvar.addActionListener(e -> {
            try {
                Usuario gerente = usuarioController.buscarPorLogin((String) comboGerente.getSelectedItem()).orElse(null);
                controller.criarProjeto(
                    txtNome.getText(),
                    txtDescricao.getText(),
                    txtInicio.getText(),
                    txtTermino.getText(),
                    (StatusProjeto) comboStatus.getSelectedItem(),
                    gerente
                );
                JOptionPane.showMessageDialog(this, "Projeto criado com sucesso!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        add(btnSalvar);
        setLocationRelativeTo(null);
    }
}
