package view;

import controller.EquipeController;
import controller.UsuarioController;
import model.Equipe;
import model.Usuario;
import javax.swing.*;
import java.awt.*;

public class TelaEquipe extends JFrame {

    public TelaEquipe(EquipeController controller, UsuarioController usuarioController) {
        setTitle("Gestão de Equipes");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        JTextField txtNome = new JTextField();
        JTextField txtDescricao = new JTextField();

        add(new JLabel("Nome da Equipe:"));
        add(txtNome);
        add(new JLabel("Descrição:"));
        add(txtDescricao);

        JButton btnCriar = new JButton("Criar Equipe");
        btnCriar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O Nome da Equipe é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                controller.criarEquipe(nome, descricao);
                JOptionPane.showMessageDialog(this, "Equipe criada com sucesso!");
                txtNome.setText("");
                txtDescricao.setText("");
            } catch (RuntimeException ex) {
                String cause = ex.getCause() != null ? ex.getCause().getMessage() : "";
                if (cause.contains("nome") || ex.getMessage().contains("Duplicate entry")) {
                    JOptionPane.showMessageDialog(this, "Esta equipe já está cadastrada no sistema.", "Equipe duplicada", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao criar equipe: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnMembro = new JButton("Add Membro");
        btnMembro.addActionListener(e -> {
            String nomeEquipe = JOptionPane.showInputDialog(this, "Nome da Equipe:");
            if (nomeEquipe == null) return; // Clicou em cancelar
            nomeEquipe = nomeEquipe.trim();
            if (nomeEquipe.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome da equipe não pode ser vazio.", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String loginUser = JOptionPane.showInputDialog(this, "Login do Usuário:");
            if (loginUser == null) return; // Clicou em cancelar
            loginUser = loginUser.trim();
            if (loginUser.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Login do usuário não pode ser vazio.", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                final String teamName = nomeEquipe;
                Equipe eq = controller.listarEquipes().stream()
                    .filter(team -> team.getNome().equalsIgnoreCase(teamName))
                    .findFirst().orElse(null);
                Usuario user = usuarioController.buscarPorLogin(loginUser).orElse(null);

                if (eq != null && user != null) {
                    controller.adicionarMembro(eq, user);
                    JOptionPane.showMessageDialog(this, "Membro adicionado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: Equipe ou Usuário não encontrado.", "Não encontrado", JOptionPane.ERROR_MESSAGE);
                }
            } catch (RuntimeException ex) {
                String cause = ex.getCause() != null ? ex.getCause().getMessage() : "";
                if (cause.contains("PRIMARY") || ex.getMessage().contains("Duplicate entry")) {
                    JOptionPane.showMessageDialog(this, "Este usuário já faz parte desta equipe.", "Membro duplicado", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar membro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(btnCriar);
        add(btnMembro);
        setLocationRelativeTo(null);
    }
}
