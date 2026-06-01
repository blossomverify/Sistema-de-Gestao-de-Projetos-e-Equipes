package repository;

import model.Equipe;
import model.Usuario;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipeRepository {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void salvar(Equipe equipe) {
        String sqlEquipe = "INSERT INTO equipes (nome, descricao) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlEquipe, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, equipe.getNome());
            stmt.setString(2, equipe.getDescricao());
            stmt.executeUpdate();
            // Recupera o ID gerado automaticamente e define no objeto
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    equipe.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar equipe", e);
        }
    }

    // Corrigido: usa equipe_id e usuario_id (INT FK) em vez de nomes/logins que não existem
    public void adicionarMembroDB(int equipeId, int usuarioId) {
        String sqlMembro = "INSERT INTO equipe_membros (equipe_id, usuario_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlMembro)) {
            stmt.setInt(1, equipeId);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar membro na equipe", e);
        }
    }

    public List<Equipe> listarTodas() {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipes";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Equipe equipe = new Equipe(rs.getString("nome"), rs.getString("descricao"));
                equipe.setId(rs.getInt("id"));
                carregarMembros(equipe);
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar equipes", e);
        }
        return equipes;
    }

    // Corrigido: busca membros por equipe_id (INT FK) em vez de equipe_nome que não existe
    private void carregarMembros(Equipe equipe) {
        String sql = "SELECT u.login FROM equipe_membros em JOIN usuarios u ON em.usuario_id = u.id WHERE em.equipe_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, equipe.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarioRepository.buscarPorLogin(rs.getString("login"))
                            .ifPresent(equipe::adicionarMembro);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar membros da equipe", e);
        }
    }
}
