package view;

import controller.TarefaController;
import controller.UsuarioController;
import model.Usuario;
import javax.swing.*;
import java.awt.*;

public class TelaTarefa extends JFrame {

    public TelaTarefa(TarefaController controller, UsuarioController usuarioController) {
        setTitle("Gestão de Tarefas");
        setSize(400, 400);
        setLayout(new GridLayout(6, 2));

        JTextField txtTitulo = new JTextField();
        JTextField txtInicio = new JTextField("AAAA-MM-DD");
        JTextField txtTermino = new JTextField("AAAA-MM-DD");
        JComboBox<String> comboStatus = new JComboBox<>(new String[]{"PENDENTE", "EM_ANDAMENTO", "CONCLUIDA"});
        JComboBox<String> comboResponsavel = new JComboBox<>();
        usuarioController.listarUsuarios().forEach(u -> comboResponsavel.addItem(u.getLogin()));

        add(new JLabel("Título:"));
        add(txtTitulo);
        add(new JLabel("Responsável (Login):"));
        add(comboResponsavel);
        add(new JLabel("Data Início (AAAA-MM-DD):"));
        add(txtInicio);
        add(new JLabel("Data Término (AAAA-MM-DD):"));
        add(txtTermino);
        add(new JLabel("Status:"));
        add(comboStatus);

        JButton btnSalvar = new JButton("Criar");
        btnSalvar.addActionListener(e -> {
            String titulo = txtTitulo.getText().trim();
            String inicio = txtInicio.getText().trim();
            String termino = txtTermino.getText().trim();

            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O Título da Tarefa é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
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
            if (comboResponsavel.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "Não há usuários cadastrados para ser responsável.\nCadastre um usuário primeiro.", "Sem responsáveis disponíveis", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String terminoFinal = (termino.equals("AAAA-MM-DD") || termino.isEmpty()) ? null : termino;
                Usuario responsavel = usuarioController.buscarPorLogin((String) comboResponsavel.getSelectedItem()).orElse(null);
                controller.criarTarefa(
                    titulo,
                    responsavel,
                    inicio,
                    terminoFinal,
                    (String) comboStatus.getSelectedItem()
                );
                JOptionPane.showMessageDialog(this, "Tarefa criada com sucesso!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao criar tarefa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(btnSalvar);
        setLocationRelativeTo(null);
    }
}
