package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/gestao_projetos",
            "root",
            "123456"
        );
    }
}