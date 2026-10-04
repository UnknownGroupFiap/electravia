package pagamento;

public class Pagamento {
    private int id;
    private double valor;
    private String dataHora;
    private String meio; //PIX, CARTAO_CREDITO ou CARTAO_DEBITO
    private String situacao; 
    private String referenciaTransacao;

    public Pagamento() {
    }

    public Pagamento(int id, double valor, String meio, String dataHora) {
        this.id = id;
        this.valor = valor;
        this.meio = meio;
        this.dataHora = dataHora;
        this.situacao = "PENDENTE";
    }

    public void aprovar(String referenciaTransacao) {
        this.referenciaTransacao = referenciaTransacao;
        this.situacao = "APROVADO";
    }

    public void recusar() {
        this.situacao = "RECUSADO";
    }

    public String descricao() {
        return "Pagamento";
    }

    public void emitirComprovante() {
        if (situacao.equals("APROVADO")) {
            System.out.println("Comprovante " + id + " - " + descricao() + " - R$ " + valor + " - " + meio
                    + " - " + dataHora + " - ref. " + referenciaTransacao);
        } else {
            System.out.println("Pagamento " + id + " está " + situacao + ": sem comprovante.");
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
    public String getMeio() { return meio; }
    public void setMeio(String meio) { this.meio = meio; }
    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
    public String getReferenciaTransacao() { return referenciaTransacao; }
    public void setReferenciaTransacao(String referenciaTransacao) { this.referenciaTransacao = referenciaTransacao; }
}