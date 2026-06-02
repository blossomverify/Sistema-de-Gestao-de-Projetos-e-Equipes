package repository;

import model.Projeto;
import model.StatusProjeto;
import model.Usuario;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoRepository {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void salvar(Projeto projeto) {
        String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_termino, status, gerente_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, java.sql.Date.valueOf(projeto.getDataInicio()));
            
            if (projeto.getDataTermino() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(projeto.getDataTermino()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }
            
            stmt.setString(5, projeto.getStatus().name());
            stmt.setInt(6, projeto.getGerenteResponsavel().getId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    projeto.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar projeto no banco de dados", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Formato de data inválido. Use AAAA-MM-DD.", e);
        }
    }

    public List<Projeto> listarTodos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT p.*, u.login as gerente_login FROM projetos p LEFT JOIN usuarios u ON p.gerente_id = u.id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario gerente = usuarioRepository.buscarPorLogin(rs.getString("gerente_login")).orElse(null);
                
                java.sql.Date dInicio = rs.getDate("data_inicio");
                java.sql.Date dTermino = rs.getDate("data_termino");
                
                Projeto projeto = new Projeto(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        dInicio != null ? dInicio.toString() : null,
                        dTermino != null ? dTermino.toString() : null,
                        StatusProjeto.valueOf(rs.getString("status")),
                        gerente
                );
                projeto.setId(rs.getInt("id"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar projetos", e);
        }
        return projetos;
    }
}
