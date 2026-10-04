package recarga;

import rede.Conector;
import veiculo.Veiculo;

public class SessaoRecarga {
    private int id;
    private Veiculo veiculo;
    private Conector conector;
    private String inicio;
    private String termino;
    private double energiaKwh;
    private double precoKwhAplicado;
    private double descontoKwhAplicado;
    private String status; 

    public SessaoRecarga() {
    }

    public SessaoRecarga(int id, Veiculo veiculo, Conector conector, String inicio, double precoKwhAplicado,
                         double descontoKwhAplicado) {
        this.id = id;
        this.veiculo = veiculo;
        this.conector = conector;
        this.inicio = inicio;
        this.precoKwhAplicado = precoKwhAplicado;
        this.descontoKwhAplicado = descontoKwhAplicado;
        this.status = "EM_ANDAMENTO";
    }

    public void encerrar(String termino, double energiaKwh) {
        if (status.equals("CONCLUIDA")) {
            System.out.println("A sessão " + id + " já foi encerrada.");
        } else {
            this.termino = termino;
            this.energiaKwh = energiaKwh;
            this.status = "CONCLUIDA";
            conector.registrarStatus("LIVRE", termino);
            System.out.println(veiculo.getDono().getNome() + " encerrou a recarga às " + termino
                    + " e liberou o conector " + conector.getIdentificacao() + ".");
        }
    }

    public double calcularCusto() {
        double tarifa = precoKwhAplicado - descontoKwhAplicado;
        if (tarifa < 0) {
            tarifa = 0;
        }
        double custo = energiaKwh * tarifa;
        return Math.round(custo * 100) / 100.0;
    }

    public void exibirDados() {
        System.out.println("Sessão " + id + " Início: " + inicio + " Estação: " + conector.getEstacao().getNome()
                + " Energia: " + energiaKwh + " kWh Custo: R$ " + calcularCusto());
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public Conector getConector() { return conector; }
    public void setConector(Conector conector) { this.conector = conector; }
    public String getInicio() { return inicio; }
    public void setInicio(String inicio) { this.inicio = inicio; }
    public String getTermino() { return termino; }
    public void setTermino(String termino) { this.termino = termino; }
    public double getEnergiaKwh() { return energiaKwh; }
    public void setEnergiaKwh(double energiaKwh) { this.energiaKwh = energiaKwh; }
    public double getPrecoKwhAplicado() { return precoKwhAplicado; }
    public void setPrecoKwhAplicado(double precoKwhAplicado) { this.precoKwhAplicado = precoKwhAplicado; }
    public double getDescontoKwhAplicado() { return descontoKwhAplicado; }
    public void setDescontoKwhAplicado(double descontoKwhAplicado) { this.descontoKwhAplicado = descontoKwhAplicado; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}