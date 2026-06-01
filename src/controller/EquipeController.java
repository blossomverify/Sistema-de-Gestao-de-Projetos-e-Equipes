package controller;

import model.Equipe;
import model.Usuario;
import repository.EquipeRepository;
import java.util.List;

public class EquipeController {
    private EquipeRepository repository;

    public EquipeController(EquipeRepository repository) {
        this.repository = repository;
    }

    public void criarEquipe(String nome, String descricao) {
        Equipe equipe = new Equipe(nome, descricao);
        repository.salvar(equipe);
    }

    public void adicionarMembro(Equipe equipe, Usuario usuario) {
        equipe.adicionarMembro(usuario);
        // Corrigido: agora persiste o membro no banco de dados
        repository.adicionarMembroDB(equipe.getId(), usuario.getId());
    }

    public List<Equipe> listarEquipes() {
        return repository.listarTodas();
    }
}
