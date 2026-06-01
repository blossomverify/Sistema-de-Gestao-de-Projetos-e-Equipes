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
        JTextField txtInicio = new JTextField();
        JTextField txtTermino = new JTextField();
        JComboBox<String> comboStatus = new JComboBox<>(new String[]{"PENDENTE", "EM_ANDAMENTO", "CONCLUIDA"});
        JComboBox<String> comboResponsavel = new JComboBox<>();
        usuarioController.listarUsuarios().forEach(u -> comboResponsavel.addItem(u.getLogin()));

        add(new JLabel("Título:"));
        add(txtTitulo);
        add(new JLabel("Responsável (Login):"));
        add(comboResponsavel);
        add(new JLabel("Data Início:"));
        add(txtInicio);
        add(new JLabel("Data Término:"));
        add(txtTermino);
        add(new JLabel("Status:"));
        add(comboStatus);

        JButton btnSalvar = new JButton("Criar");
        btnSalvar.addActionListener(e -> {
            try {
                Usuario responsavel = usuarioController.buscarPorLogin((String) comboResponsavel.getSelectedItem()).orElse(null);
                controller.criarTarefa(
                    txtTitulo.getText(),
                    responsavel,
                    txtInicio.getText(),
                    txtTermino.getText(),
                    (String) comboStatus.getSelectedItem()
                );
                JOptionPane.showMessageDialog(this, "Tarefa criada!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        add(btnSalvar);
        setLocationRelativeTo(null);
    }
}
