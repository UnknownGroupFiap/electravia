package veiculo;

import rede.Conector;
import usuario.Motorista;

public class Veiculo {
    private int id;
    private String apelido;
    private Motorista dono;
    private ModeloVeiculo modelo;

    public Veiculo() {
    }

    public Veiculo(int id, String apelido, Motorista dono, ModeloVeiculo modelo) {
        this.id = id;
        this.apelido = apelido;
        this.dono = dono;
        this.modelo = modelo;
    }

    public Veiculo(int id, Motorista dono, ModeloVeiculo modelo) {
        this.id = id;
        this.apelido = modelo.descricao();
        this.dono = dono;
        this.modelo = modelo;
    }

    public boolean compativelCom(Conector conector) {
        return modelo.aceita(conector.getTipo());
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }
    public Motorista getDono() { return dono; }
    public void setDono(Motorista dono) { this.dono = dono; }
    public ModeloVeiculo getModelo() { return modelo; }
    public void setModelo(ModeloVeiculo modelo) { this.modelo = modelo; }
}