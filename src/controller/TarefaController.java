package controller;

import model.Tarefa;
import model.Usuario;
import repository.TarefaRepository;
import java.util.List;

public class TarefaController {
    private TarefaRepository repository;

    public TarefaController(TarefaRepository repository) {
        this.repository = repository;
    }

    public void criarTarefa(String titulo, String descricao, Usuario responsavel, String inicio, String termino, String status) {
        Tarefa tarefa = new Tarefa(titulo, descricao, responsavel, inicio, termino, status);
        repository.salvar(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return repository.listarTodas();
    }
}
