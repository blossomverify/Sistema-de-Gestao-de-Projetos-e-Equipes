package model;

public class Tarefa {

    private String titulo;
    private Usuario responsavel;
    private String dataInicio;
    private String dataTermino;
    private String status;

    public Tarefa(String titulo, Usuario responsavel, String dataInicio, String dataTermino, String status) {
        this.titulo = titulo;
        this.responsavel = responsavel;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.status = status;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
