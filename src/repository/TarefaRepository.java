package repository;

import model.Tarefa;
import model.Usuario;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void salvar(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, responsavel_login, data_inicio, data_termino, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getResponsavel().getLogin());
            stmt.setString(3, tarefa.getDataInicio());
            stmt.setString(4, tarefa.getDataTermino());
            stmt.setString(5, tarefa.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar tarefa", e);
        }
    }

    public List<Tarefa> listarTodas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario responsavel = usuarioRepository.buscarPorLogin(rs.getString("responsavel_login")).orElse(null);
                Tarefa tarefa = new Tarefa(
                        rs.getString("titulo"),
                        responsavel,
                        rs.getString("data_inicio"),
                        rs.getString("data_termino"),
                        rs.getString("status")
                );
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tarefas", e);
        }
        return tarefas;
    }
}
