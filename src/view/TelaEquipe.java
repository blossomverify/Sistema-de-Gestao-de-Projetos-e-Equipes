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
            controller.criarEquipe(txtNome.getText(), txtDescricao.getText());
            JOptionPane.showMessageDialog(this, "Equipe criada!");
        });

        JButton btnMembro = new JButton("Add Membro");
        btnMembro.addActionListener(e -> {
            String nomeEquipe = JOptionPane.showInputDialog("Nome da Equipe:");
            String loginUser = JOptionPane.showInputDialog("Login do Usuário:");
            
            Equipe eq = controller.listarEquipes().stream()
                .filter(team -> team.getNome().equalsIgnoreCase(nomeEquipe))
                .findFirst().orElse(null);
            Usuario user = usuarioController.buscarPorLogin(loginUser).orElse(null);
            
            if (eq != null && user != null) {
                controller.adicionarMembro(eq, user);
                JOptionPane.showMessageDialog(this, "Membro adicionado!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro: Equipe ou Usuário não encontrado.");
            }
        });

        add(btnCriar);
        add(btnMembro);
        setLocationRelativeTo(null);
    }
}
