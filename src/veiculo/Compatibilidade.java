package veiculo;

import rede.TipoConector;

public class Compatibilidade {
    private ModeloVeiculo modelo;
    private TipoConector tipoConector;
    private double potenciaMaxKw;

    public Compatibilidade() {
    }

    public Compatibilidade(ModeloVeiculo modelo, TipoConector tipoConector, double potenciaMaxKw) {
        this.modelo = modelo;
        this.tipoConector = tipoConector;
        this.potenciaMaxKw = potenciaMaxKw;
    }

    public ModeloVeiculo getModelo() { return modelo; }
    public void setModelo(ModeloVeiculo modelo) { this.modelo = modelo; }
    public TipoConector getTipoConector() { return tipoConector; }
    public void setTipoConector(TipoConector tipoConector) { this.tipoConector = tipoConector; }
    public double getPotenciaMaxKw() { return potenciaMaxKw; }
    public void setPotenciaMaxKw(double potenciaMaxKw) { this.potenciaMaxKw = potenciaMaxKw; }
}