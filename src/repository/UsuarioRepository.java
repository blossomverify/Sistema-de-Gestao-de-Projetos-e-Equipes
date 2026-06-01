package repository;

import model.Perfil;
import model.Usuario;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome_completo, cpf, email, cargo, login, senha, perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCargo());
            stmt.setString(5, usuario.getLogin());
            stmt.setString(6, usuario.getSenha());
            stmt.setString(7, usuario.getPerfil().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário no banco de dados", e);
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Senha temporária válida para passar a validação do construtor
                Usuario usuario = new Usuario(
                        rs.getString("nome_completo"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("cargo"),
                        rs.getString("login"),
                        "placeholder12345",
                        Perfil.valueOf(rs.getString("perfil"))
                );
                // Sobrescreve com a senha já criptografada do banco (evita dupla criptografia)
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }
        return usuarios;
    }

    public Optional<Usuario> buscarPorLogin(String login) {
        String sql = "SELECT * FROM usuarios WHERE login = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Senha temporária válida para passar a validação do construtor
                    Usuario usuario = new Usuario(
                            rs.getString("nome_completo"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("cargo"),
                            rs.getString("login"),
                            "placeholder12345",
                            Perfil.valueOf(rs.getString("perfil"))
                    );
                    // Sobrescreve com a senha já criptografada do banco (evita dupla criptografia)
                    usuario.setSenha(rs.getString("senha"));
                    return Optional.of(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por login", e);
        }
        return Optional.empty();
    }
}
