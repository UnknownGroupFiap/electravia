package pagamento;

public class PagamentoMensalidade extends Pagamento {
    private Assinatura assinatura;
    private String competencia; //mês de referência, por ex. 10/2026

    public PagamentoMensalidade() {
    }

    public PagamentoMensalidade(int id, Assinatura assinatura, String competencia, String meio, String dataHora) {
        super(id, assinatura.getPlano().getMensalidade(), meio, dataHora);
        this.assinatura = assinatura;
        this.competencia = competencia;
    }

    @Override
    public String descricao() {
        return "Mensalidade do plano " + assinatura.getPlano().getNome() + " (" + competencia + ")";
    }

    public Assinatura getAssinatura() { return assinatura; }
    public void setAssinatura(Assinatura assinatura) { this.assinatura = assinatura; }
    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }
}