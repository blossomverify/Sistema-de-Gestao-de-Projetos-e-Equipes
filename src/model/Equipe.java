package model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

    private String nome;
    private String descricao;
    private List<Usuario> membros;

    public Equipe(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public void adicionarMembro(Usuario usuario) {
        this.membros.add(usuario);
    }

    public void removerMembro(Usuario usuario) {
        this.membros.remove(usuario);
    }
}
