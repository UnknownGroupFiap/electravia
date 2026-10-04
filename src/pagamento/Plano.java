package pagamento;

public class Plano {
    private int id;
    private String nome;
    private double mensalidade;
    private double descontoKwh;

    public Plano() {
    }

    public Plano(int id, String nome, double mensalidade, double descontoKwh) {
        this.id = id;
        this.nome = nome;
        this.mensalidade = mensalidade;
        this.descontoKwh = descontoKwh;
    }

    public Plano(int id, String nome, double mensalidade) {
        this.id = id;
        this.nome = nome;
        this.mensalidade = mensalidade;
        this.descontoKwh = 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getMensalidade() { return mensalidade; }
    public void setMensalidade(double mensalidade) { this.mensalidade = mensalidade; }
    public double getDescontoKwh() { return descontoKwh; }
    public void setDescontoKwh(double descontoKwh) { this.descontoKwh = descontoKwh; }
}