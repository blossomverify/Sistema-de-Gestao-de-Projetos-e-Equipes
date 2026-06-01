package model;

public class Usuario {

    private int id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private Perfil perfil;

    public Usuario(String nomeCompleto, String cpf, String email, String cargo, String login, String senha, Perfil perfil) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        definirSenha(senha);
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    // Usado apenas ao carregar do banco (senha já está criptografada)
    public void setSenha(String senhaJaCriptografada) {
        this.senha = senhaJaCriptografada;
    }

    public void definirSenha(String novaSenha) {
        if (novaSenha.length() >= 8) {
            this.senha = criptografar(novaSenha);
        } else {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    private String criptografar(String senha) {
        return "hash_seguro_" + senha;
    }
}
