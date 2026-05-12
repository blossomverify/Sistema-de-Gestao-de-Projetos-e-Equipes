package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto_a3";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao MySQL!\n1. Verifique se o MySQL está ligado.\n2. Verifique se o banco 'projeto_a3' foi criado.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
