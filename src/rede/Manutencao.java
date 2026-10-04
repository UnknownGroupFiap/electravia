package rede;

import usuario.Funcionario;

public class Manutencao {
    private int id;
    private Estacao estacao;
    private Funcionario tecnico;
    private String abertura;
    private String encerramento;
    private String descricao;
    private String prioridade; 
    private String situacao; 

    public Manutencao() {
    }

    public Manutencao(int id, Estacao estacao, String descricao, String abertura, String prioridade) {
        this.id = id;
        this.estacao = estacao;
        this.descricao = descricao;
        this.abertura = abertura;
        this.prioridade = prioridade;
        this.situacao = "ABERTA";
    }

    public Manutencao(int id, Estacao estacao, String descricao, String abertura) {
        this.id = id;
        this.estacao = estacao;
        this.descricao = descricao;
        this.abertura = abertura;
        this.prioridade = "MEDIA";
        this.situacao = "ABERTA";
    }


    public void atribuir(Funcionario funcionario) {
        if (funcionario.getPerfil().equals("TECNICO")) {
            this.tecnico = funcionario;
            this.situacao = "EM_ATENDIMENTO";
            System.out.println("Ordem " + id + " atribuída a " + funcionario.getNome() + ".");
        } else {
            System.out.println(funcionario.getNome() + " não tem perfil de técnico e não pode assumir a ordem "
                    + id + ".");
        }
    }

    public void concluir(String encerramento) {
        if (situacao.equals("EM_ATENDIMENTO")) {
            this.encerramento = encerramento;
            this.situacao = "CONCLUIDA";
            System.out.println("Ordem " + id + " concluída às " + encerramento + ".");
        } else {
            System.out.println("Ordem " + id + " não está em atendimento.");
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Estacao getEstacao() { return estacao; }
    public void setEstacao(Estacao estacao) { this.estacao = estacao; }
    public Funcionario getTecnico() { return tecnico; }
    public void setTecnico(Funcionario tecnico) { this.tecnico = tecnico; }
    public String getAbertura() { return abertura; }
    public void setAbertura(String abertura) { this.abertura = abertura; }
    public String getEncerramento() { return encerramento; }
    public void setEncerramento(String encerramento) { this.encerramento = encerramento; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
}