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
             PreparedStatement stmt = conn.prepareStatement(sqlEquipe)) {
            stmt.setString(1, equipe.getNome());
            stmt.setString(2, equipe.getDescricao());
            stmt.executeUpdate();
            salvarMembros(equipe);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar equipe", e);
        }
    }

    private void salvarMembros(Equipe equipe) {
        String sqlMembro = "INSERT INTO equipe_membros (equipe_nome, usuario_login) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            for (Usuario membro : equipe.getMembros()) {
                try (PreparedStatement stmt = conn.prepareStatement(sqlMembro)) {
                    stmt.setString(1, equipe.getNome());
                    stmt.setString(2, membro.getLogin());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar membros da equipe", e);
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
                carregarMembros(equipe);
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar equipes", e);
        }
        return equipes;
    }

    private void carregarMembros(Equipe equipe) {
        String sql = "SELECT usuario_login FROM equipe_membros WHERE equipe_nome = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipe.getNome());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarioRepository.buscarPorLogin(rs.getString("usuario_login"))
                            .ifPresent(equipe::adicionarMembro);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar membros da equipe", e);
        }
    }
}
