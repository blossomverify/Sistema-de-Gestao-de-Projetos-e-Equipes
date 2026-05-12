package view;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    private TelaUsuario telaUsuario;
    private TelaProjeto telaProjeto;
    private TelaEquipe telaEquipe;
    private TelaTarefa telaTarefa;

    public MenuPrincipal(TelaUsuario telaUsuario, TelaProjeto telaProjeto, TelaEquipe telaEquipe, TelaTarefa telaTarefa) {
        this.telaUsuario = telaUsuario;
        this.telaProjeto = telaProjeto;
        this.telaEquipe = telaEquipe;
        this.telaTarefa = telaTarefa;

        setTitle("Sistema de Gestão - Projeto A3");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JButton btnUser = new JButton("Gestão de Usuários");
        JButton btnProj = new JButton("Gestão de Projetos");
        JButton btnTeam = new JButton("Gestão de Equipes");
        JButton btnTask = new JButton("Gestão de Tarefas");
        JButton btnExit = new JButton("Sair");

        btnUser.addActionListener(e -> telaUsuario.setVisible(true));
        btnProj.addActionListener(e -> telaProjeto.setVisible(true));
        btnTeam.addActionListener(e -> telaEquipe.setVisible(true));
        btnTask.addActionListener(e -> telaTarefa.setVisible(true));
        btnExit.addActionListener(e -> System.exit(0));

        add(btnUser);
        add(btnProj);
        add(btnTeam);
        add(btnTask);
        add(btnExit);

        setLocationRelativeTo(null);
    }
}
