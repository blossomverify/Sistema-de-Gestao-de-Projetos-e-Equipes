package controller;

import model.Perfil;
import model.Usuario;
import repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

public class UsuarioController {
    private UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrarUsuario(String nome, String cpf, String email, String cargo, String login, String senha, Perfil perfil) {
        Usuario novoUsuario = new Usuario(nome, cpf, email, cargo, login, senha, perfil);
        repository.salvar(novoUsuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.listarTodos();
    }

    public Optional<Usuario> buscarPorLogin(String login) {
        return repository.buscarPorLogin(login);
    }
}
