package pagamento;

import recarga.SessaoRecarga;

public class PagamentoRecarga extends Pagamento {
    private SessaoRecarga sessao;

    public PagamentoRecarga() {
    }

    public PagamentoRecarga(int id, SessaoRecarga sessao, String meio, String dataHora) {
        super(id, sessao.calcularCusto(), meio, dataHora);
        this.sessao = sessao;
    }

    @Override
    public String descricao() {
        return "Recarga de " + sessao.getEnergiaKwh() + " kWh em " + sessao.getConector().getEstacao().getNome();
    }

    public SessaoRecarga getSessao() { return sessao; }
    public void setSessao(SessaoRecarga sessao) { this.sessao = sessao; }
}