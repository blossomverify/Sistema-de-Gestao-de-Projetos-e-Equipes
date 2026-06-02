package view;

import controller.ProjetoController;
import controller.UsuarioController;
import model.StatusProjeto;
import model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaProjeto extends JFrame {

    public TelaProjeto(ProjetoController controller, UsuarioController usuarioController) {
        setTitle("Gestão de Projetos");
        setSize(400, 400);
        setLayout(new GridLayout(7, 2));

        JTextField txtNome = new JTextField();
        JTextField txtDescricao = new JTextField();
        JTextField txtInicio = new JTextField("AAAA-MM-DD");
        JTextField txtTermino = new JTextField("AAAA-MM-DD");
        JComboBox<StatusProjeto> comboStatus = new JComboBox<>(StatusProjeto.values());
        JComboBox<String> comboGerente = new JComboBox<>();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent e) {
                comboGerente.removeAllItems();
                usuarioController.listarUsuarios().forEach(u -> comboGerente.addItem(u.getLogin()));
            }
        });

        add(new JLabel("Nome do Projeto:"));
        add(txtNome);
        add(new JLabel("Descrição:"));
        add(txtDescricao);
        add(new JLabel("Data Início (AAAA-MM-DD):"));
        add(txtInicio);
        add(new JLabel("Data Término (AAAA-MM-DD):"));
        add(txtTermino);
        add(new JLabel("Status:"));
        add(comboStatus);
        add(new JLabel("Gerente (Login):"));
        add(comboGerente);

        JButton btnSalvar = new JButton("Criar");
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String inicio = txtInicio.getText().trim().replace("/", "-");
            String termino = txtTermino.getText().trim().replace("/", "-");

            // Validações com mensagens personalizadas
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo Nome do Projeto é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (inicio.isEmpty() || inicio.equals("AAAA-MM-DD")) {
                JOptionPane.showMessageDialog(this, "Informe a Data de Início no formato AAAA-MM-DD.\nExemplo: 2024-01-15", "Data inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!inicio.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Data de Início inválida! Use o formato AAAA-MM-DD.\nExemplo: 2024-01-15", "Data inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!termino.isEmpty() && !termino.equals("AAAA-MM-DD") && !termino.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Data de Término inválida! Use o formato AAAA-MM-DD.\nExemplo: 2024-12-31", "Data inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (comboGerente.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "Não há usuários cadastrados para ser gerente.\nCadastre um usuário com perfil GERENTE ou ADMINISTRADOR primeiro.", "Sem gerentes disponíveis", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String terminoFinal = (termino.equals("AAAA-MM-DD") || termino.isEmpty()) ? null : termino;
                Usuario gerente = usuarioController.buscarPorLogin((String) comboGerente.getSelectedItem()).orElse(null);
                controller.criarProjeto(nome, txtDescricao.getText().trim(), inicio, terminoFinal,
                        (StatusProjeto) comboStatus.getSelectedItem(), gerente);
                JOptionPane.showMessageDialog(this, "Projeto criado com sucesso!");
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao criar projeto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(btnSalvar);
        setLocationRelativeTo(null);
    }
}
