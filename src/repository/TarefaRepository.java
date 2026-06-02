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
        String sql = "INSERT INTO tarefas (titulo, descricao, responsavel_id, data_inicio, data_termino, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getResponsavel().getId());
            stmt.setString(4, tarefa.getDataInicio());
            stmt.setString(5, tarefa.getDataTermino());
            stmt.setString(6, tarefa.getStatus());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tarefa.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar tarefa", e);
        }
    }

    public List<Tarefa> listarTodas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT t.*, u.login as responsavel_login FROM tarefas t LEFT JOIN usuarios u ON t.responsavel_id = u.id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario responsavel = usuarioRepository.buscarPorLogin(rs.getString("responsavel_login")).orElse(null);
                Tarefa tarefa = new Tarefa(
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        responsavel,
                        rs.getString("data_inicio"),
                        rs.getString("data_termino"),
                        rs.getString("status")
                );
                tarefa.setId(rs.getInt("id"));
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tarefas", e);
        }
        return tarefas;
    }
}
