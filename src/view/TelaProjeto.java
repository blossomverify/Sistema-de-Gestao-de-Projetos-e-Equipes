package view;

import controller.ProjetoController;
import controller.UsuarioController;
import model.StatusProjeto;
import model.Usuario;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaProjeto extends JFrame {

    public TelaProjeto(ProjetoController controller, UsuarioController usuarioController) {
        setTitle("Gestão de Projetos");
        setSize(400, 400);
        setLayout(new GridLayout(7, 2));

        JTextField txtNome = new JTextField();
        JTextField txtDescricao = new JTextField();
        JFormattedTextField txtInicio = createDateField();
        JFormattedTextField txtTermino = createDateField();
        JComboBox<StatusProjeto> comboStatus = new JComboBox<>(StatusProjeto.values());
        JComboBox<String> comboGerente = new JComboBox<>();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent e) {
                comboGerente.removeAllItems();
                usuarioController.listarUsuarios().stream()
                    .filter(u -> u.getPerfil() == model.Perfil.GERENTE || u.getPerfil() == model.Perfil.ADMINISTRADOR)
                    .forEach(u -> comboGerente.addItem(u.getLogin()));
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
            String inicio = txtInicio.getText().trim();
            String termino = txtTermino.getText().trim();

            // Validações com mensagens personalizadas
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo Nome do Projeto é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
                return;
            }

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

            if (comboGerente.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "Não há usuários cadastrados para ser gerente.\nCadastre um usuário com perfil GERENTE ou ADMINISTRADOR primeiro.", "Sem gerentes disponíveis", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Validação rigorosa de data
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                
                java.util.Date dInicio = sdf.parse(inicio);
                if (terminoFinal != null) {
                    java.util.Date dTermino = sdf.parse(terminoFinal);
                    if (dTermino.before(dInicio)) {
                        JOptionPane.showMessageDialog(this, "A Data de Término não pode ser anterior à Data de Início.", "Sequência de datas inválida", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                Usuario gerente = usuarioController.buscarPorLogin((String) comboGerente.getSelectedItem()).orElse(null);
                controller.criarProjeto(nome, txtDescricao.getText().trim(), sdf.format(dInicio), terminoFinal != null ? sdf.format(sdf.parse(terminoFinal)) : null,
                        (StatusProjeto) comboStatus.getSelectedItem(), gerente);
                JOptionPane.showMessageDialog(this, "Projeto criado com sucesso!");
                dispose();
            } catch (java.text.ParseException ex) {
                JOptionPane.showMessageDialog(this, "Uma das datas informadas é inválida ou não existe (ex: 31 de fevereiro).\nVerifique o dia e o mês.", "Data inexistente", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Dados inválidos", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao criar projeto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
