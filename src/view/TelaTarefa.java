package view;

import controller.TarefaController;
import controller.UsuarioController;
import model.Usuario;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaTarefa extends JFrame {

    public TelaTarefa(TarefaController controller, UsuarioController usuarioController) {
        setTitle("Gestão de Tarefas");
        setSize(400, 450);
        setLayout(new GridLayout(7, 2));

        JTextField txtTitulo = new JTextField();
        JTextField txtDescricao = new JTextField();
        
        JFormattedTextField txtInicio = createDateField();
        JFormattedTextField txtTermino = createDateField();
        
        JComboBox<String> comboStatus = new JComboBox<>(new String[]{"PENDENTE", "EM_ANDAMENTO", "CONCLUIDA"});
        JComboBox<String> comboResponsavel = new JComboBox<>();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent e) {
                comboResponsavel.removeAllItems();
                usuarioController.listarUsuarios().forEach(u -> comboResponsavel.addItem(u.getLogin()));
            }
        });

        add(new JLabel("Título:"));
        add(txtTitulo);
        add(new JLabel("Descrição:"));
        add(txtDescricao);
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
            String descricao = txtDescricao.getText().trim();
            String inicio = txtInicio.getText().trim();
            String termino = txtTermino.getText().trim();

            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O Título da Tarefa é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Verifica se a data foi preenchida (removendo placeholders)
            String inicioLimpo = inicio.replace("_", "").replace("-", "");
            if (inicioLimpo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a Data de Início.\nExemplo: 2024-01-15", "Data inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (!inicio.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Data de Início incompleta ou inválida!\nUse o formato AAAA-MM-DD.", "Data inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String terminoLimpo = termino.replace("_", "").replace("-", "");
            String terminoFinal = terminoLimpo.isEmpty() ? null : termino;

            if (terminoFinal != null && !terminoFinal.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Data de Término incompleta ou inválida!\nUse o formato AAAA-MM-DD.", "Data inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (comboResponsavel.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "Não há usuários cadastrados para ser responsável.\nCadastre um usuário primeiro.", "Sem responsáveis disponíveis", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Usuario responsavel = usuarioController.buscarPorLogin((String) comboResponsavel.getSelectedItem()).orElse(null);
                controller.criarTarefa(
                    titulo,
                    descricao,
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

    private JFormattedTextField createDateField() {
        try {
            MaskFormatter mask = new MaskFormatter("####-##-##");
            mask.setPlaceholderCharacter('_');
            JFormattedTextField field = new JFormattedTextField(mask);
            return field;
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }
}
