package rede;

import recarga.SessaoRecarga;
import usuario.Motorista;
import veiculo.Veiculo;

public class Conector {
    private int id;
    private String identificacao;
    private Estacao estacao;
    private TipoConector tipo;
    private double potenciaKw;
    private double precoKwh;
    private String estado; 
    private String ultimaComunicacao;

    public Conector() {
    }

    public Conector(int id, String identificacao, Estacao estacao, TipoConector tipo, double potenciaKw,
                    double precoKwh) {
        this.id = id;
        this.identificacao = identificacao;
        this.estacao = estacao;
        this.tipo = tipo;
        this.potenciaKw = potenciaKw;
        this.precoKwh = precoKwh;
        this.estado = "LIVRE";
    }

    public boolean estaLivre() {
        return estado.equals("LIVRE");
    }

    public void registrarStatus(String estado, String dataHora) {
        this.estado = estado;
        this.ultimaComunicacao = dataHora;
    }

    public SessaoRecarga iniciarRecarga(int idSessao, Veiculo veiculo, String inicio) {
        Motorista motorista = veiculo.getDono();
        if (!motorista.isAtivo()) {
            System.out.println("Recarga negada: a conta de " + motorista.getNome() + " está inativa.");
            return null;
        }
        if (!estaLivre()) {
            System.out.println("Recarga negada: o conector " + identificacao + " está " + estado + ".");
            return null;
        }
        if (!veiculo.compativelCom(this)) {
            System.out.println("Recarga negada: " + veiculo.getApelido() + " não aceita " + tipo.getNome() + ".");
            return null;
        }
        SessaoRecarga sessao = new SessaoRecarga(idSessao, veiculo, this, inicio, precoKwh,
                motorista.descontoAtual());
        registrarStatus("OCUPADO", inicio);
        System.out.println(motorista.getNome() + " iniciou a recarga no conector " + identificacao + " às "
                + inicio + ".");
        return sessao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getIdentificacao() { return identificacao; }
    public void setIdentificacao(String identificacao) { this.identificacao = identificacao; }
    public Estacao getEstacao() { return estacao; }
    public void setEstacao(Estacao estacao) { this.estacao = estacao; }
    public TipoConector getTipo() { return tipo; }
    public void setTipo(TipoConector tipo) { this.tipo = tipo; }
    public double getPotenciaKw() { return potenciaKw; }
    public void setPotenciaKw(double potenciaKw) { this.potenciaKw = potenciaKw; }
    public double getPrecoKwh() { return precoKwh; }
    public void setPrecoKwh(double precoKwh) { this.precoKwh = precoKwh; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getUltimaComunicacao() { return ultimaComunicacao; }
    public void setUltimaComunicacao(String ultimaComunicacao) { this.ultimaComunicacao = ultimaComunicacao; }
}