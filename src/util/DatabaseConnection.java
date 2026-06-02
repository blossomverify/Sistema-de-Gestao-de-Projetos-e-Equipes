package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/projeto_a3?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace(); // Garante que o erro completo apareça no console
            String mensagem = "Erro ao conectar ao banco de dados!";
            
            if (e.getSQLState().startsWith("08")) {
                mensagem = "Não foi possível conectar ao MySQL!\n\n" +
                           "Certifique-se de que:\n" +
                           "1. O XAMPP/MySQL está LIGADO.\n" +
                           "2. O servidor está rodando na porta 3306.";
            } else if ("28000".equals(e.getSQLState())) {
                mensagem = "Acesso negado!\n\n" +
                           "Verifique se o usuário 'root' e a senha estão corretos\n" +
                           "na classe DatabaseConnection.java.";
            } else if (e.getMessage().contains("Unknown database")) {
                mensagem = "Banco de dados 'projeto_a3' não encontrado!\n\n" +
                           "Importe o arquivo 'database.sql' no seu MySQL.";
            } else {
                mensagem += "\nErro técnico: " + e.getMessage();
            }

            JOptionPane.showMessageDialog(null, mensagem, "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
 