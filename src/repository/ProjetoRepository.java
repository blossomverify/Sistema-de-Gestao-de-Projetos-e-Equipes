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
        // Corrigido: usa gerente_id (INT FK) em vez de gerente_login que não existe
        String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_termino, status, gerente_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataTermino());
            stmt.setString(5, projeto.getStatus().name());
            stmt.setInt(6, projeto.getGerenteResponsavel().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar projeto", e);
        }
    }

    public List<Projeto> listarTodos() {
        List<Projeto> projetos = new ArrayList<>();
        // Corrigido: JOIN para buscar o gerente pelo ID correto
        String sql = "SELECT p.*, u.login as gerente_login FROM projetos p LEFT JOIN usuarios u ON p.gerente_id = u.id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario gerente = usuarioRepository.buscarPorLogin(rs.getString("gerente_login")).orElse(null);
                Projeto projeto = new Projeto(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("data_inicio"),
                        rs.getString("data_termino"),
                        StatusProjeto.valueOf(rs.getString("status")),
                        gerente
                );
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar projetos", e);
        }
        return projetos;
    }
}
