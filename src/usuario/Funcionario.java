package usuario;

public class Funcionario extends Usuario {
    private String matricula;
    private String perfil; 

    public Funcionario() {
    }

    public Funcionario(int id, String nome, String email, String senhaHash, String matricula, String perfil) {
        super(id, nome, email, senhaHash);
        this.matricula = matricula;
        this.perfil = perfil;
    }

    @Override
    public boolean podeAcessar(String recurso) {
        if (!isAtivo()) {
            return false;
        }
        if (perfil.equals("TECNICO")) {
            return recurso.equals("STATUS") || recurso.equals("MANUTENCAO");
        } else if (perfil.equals("GESTOR")) {
            return recurso.equals("STATUS") || recurso.equals("CADASTRO_REDE") || recurso.equals("FATURAMENTO");
        } else if (perfil.equals("ADMINISTRADOR")) {
            return recurso.equals("CONTAS");
        }
        return false;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Tipo: FUNCIONARIO Matrícula: " + matricula + " Perfil: " + perfil);
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
}