package usuario;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senhaHash;
    private boolean ativo;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senhaHash) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.ativo = true;
    }

    public void desativar() {
        ativo = false;
        System.out.println("Conta de " + nome + " desativada.");
    }

    public void reativar() {
        ativo = true;
        System.out.println("Conta de " + nome + " reativada.");
    }

    public boolean podeAcessar(String recurso) {
        return false;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome + " E-mail: " + email + " Ativo: " + ativo);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}