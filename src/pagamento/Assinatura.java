package pagamento;

import usuario.Motorista;

public class Assinatura {
    private int id;
    private Motorista motorista;
    private Plano plano;
    private String dataInicio;
    private String dataFim; 

    public Assinatura() {
    }

    public Assinatura(int id, Motorista motorista, Plano plano, String dataInicio) {
        this.id = id;
        this.motorista = motorista;
        this.plano = plano;
        this.dataInicio = dataInicio;
    }

    public boolean estaVigente() {
        return dataFim == null;
    }

    public void encerrar(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Motorista getMotorista() { return motorista; }
    public void setMotorista(Motorista motorista) { this.motorista = motorista; }
    public Plano getPlano() { return plano; }
    public void setPlano(Plano plano) { this.plano = plano; }
    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }
    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }
}