package view;

import model.Usuario;
import repository.UsuarioRepository;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class TelaLogin extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnCadastrar;
    private UsuarioRepository usuarioRepository;
    private MenuPrincipal menuPrincipal;
    private TelaUsuario telaUsuario;

    public TelaLogin(MenuPrincipal menuPrincipal, TelaUsuario telaUsuario) {
        this.menuPrincipal = menuPrincipal;
        this.telaUsuario = telaUsuario;
        this.usuarioRepository = new UsuarioRepository();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Acesso ao Sistema");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("  Login:"));
        txtLogin = new JTextField();
        add(txtLogin);

        add(new JLabel("  Senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        btnCadastrar = new JButton("Criar Conta");
        add(btnCadastrar);

        btnEntrar = new JButton("Entrar");
        add(btnEntrar);

        btnEntrar.addActionListener((ActionEvent e) -> {
            efetuarLogin();
        });

        btnCadastrar.addActionListener((ActionEvent e) -> {
            telaUsuario.setVisible(true);
        });
    }

    private void efetuarLogin() {
        String loginDigitado = txtLogin.getText().trim();
        String senhaDigitada = new String(txtSenha.getPassword());

        if (loginDigitado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Login é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (senhaDigitada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Senha é obrigatório.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (loginDigitado.equals("admin") && senhaDigitada.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Acesso Administrador Temporário!");
            this.dispose();
            menuPrincipal.setVisible(true);
            return;
        }

        Optional<Usuario> usuarioOp = usuarioRepository.buscarPorLogin(loginDigitado);

        if (usuarioOp.isPresent()) {
            Usuario usuario = usuarioOp.get();
            String senhaDigitadaCriptografada = "hash_seguro_" + senhaDigitada;

            if (usuario.getSenha().equals(senhaDigitadaCriptografada)) {
                JOptionPane.showMessageDialog(this, "Bem-vindo(a), " + usuario.getNomeCompleto() + "!");
                this.dispose();
                menuPrincipal.setVisible(true); 
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
