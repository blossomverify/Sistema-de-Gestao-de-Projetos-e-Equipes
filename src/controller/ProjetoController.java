package controller;

import model.Perfil;
import model.Projeto;
import model.StatusProjeto;
import model.Usuario;
import repository.ProjetoRepository;
import java.util.List;

public class ProjetoController {
    private ProjetoRepository repository;

    public ProjetoController(ProjetoRepository repository) {
        this.repository = repository;
    }

    public void criarProjeto(String nome, String descricao, String dataInicio, String dataTermino, StatusProjeto status, Usuario gerente) {
        if (gerente == null || (gerente.getPerfil() != Perfil.GERENTE && gerente.getPerfil() != Perfil.ADMINISTRADOR)) {
            throw new IllegalArgumentException("O projeto deve ter um gerente responsável válido.");
        }
        Projeto projeto = new Projeto(nome, descricao, dataInicio, dataTermino, status, gerente);
        repository.salvar(projeto);
    }

    public List<Projeto> listarProjetos() {
        return repository.listarTodos();
    }
}
