package usuario;

import pagamento.Assinatura;
import pagamento.Plano;
import recarga.PilhaRecargas;

public class Motorista extends Usuario {
    private String telefone;
    private String dataAdesao;
    private Assinatura assinatura;
    private PilhaRecargas historico;

    public Motorista() {
        historico = new PilhaRecargas();
        historico.INIT();
    }

    public Motorista(int id, String nome, String email, String senhaHash, String telefone, String dataAdesao) {
        super(id, nome, email, senhaHash);
        this.telefone = telefone;
        this.dataAdesao = dataAdesao;
        historico = new PilhaRecargas();
        historico.INIT();
    }

    public void assinar(int idAssinatura, Plano plano, String dataInicio) {
        if (assinatura != null && assinatura.estaVigente()) {
            assinatura.encerrar(dataInicio);
        }
        assinatura = new Assinatura(idAssinatura, this, plano, dataInicio);
        System.out.println(getNome() + " aderiu ao plano " + plano.getNome() + ".");
    }

    public double descontoAtual() {
        if (assinatura != null && assinatura.estaVigente()) {
            return assinatura.getPlano().getDescontoKwh();
        }
        return 0;
    }

    @Override
    public boolean podeAcessar(String recurso) {
        if (!isAtivo()) {
            return false;
        }
        return recurso.equals("RECARGA") || recurso.equals("HISTORICO") || recurso.equals("STATUS");
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Tipo: MOTORISTA Telefone: " + telefone + " Adesão: " + dataAdesao);
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getDataAdesao() { return dataAdesao; }
    public void setDataAdesao(String dataAdesao) { this.dataAdesao = dataAdesao; }
    public Assinatura getAssinatura() { return assinatura; }
    public void setAssinatura(Assinatura assinatura) { this.assinatura = assinatura; }
    public PilhaRecargas getHistorico() { return historico; }
}