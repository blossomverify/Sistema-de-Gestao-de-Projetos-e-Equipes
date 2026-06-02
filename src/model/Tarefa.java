package model;

public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private Usuario responsavel;
    private String dataInicio;
    private String dataTermino;
    private String status;

    public Tarefa(String titulo, String descricao, Usuario responsavel, String dataInicio, String dataTermino, String status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return this.dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
