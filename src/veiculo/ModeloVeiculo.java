package veiculo;

import rede.TipoConector;

public class ModeloVeiculo {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private Compatibilidade[] compatibilidades = new Compatibilidade[5];
    private int qtdCompatibilidades;

    public ModeloVeiculo() {
    }

    public ModeloVeiculo(int id, String marca, String modelo, int ano) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public void adicionarCompatibilidade(TipoConector tipo, double potenciaMaxKw) {
        if (qtdCompatibilidades == compatibilidades.length) {
            System.out.println("Limite de conectores do modelo " + modelo + " atingido.");
        } else {
            compatibilidades[qtdCompatibilidades] = new Compatibilidade(this, tipo, potenciaMaxKw);
            qtdCompatibilidades++;
        }
    }

    public boolean aceita(TipoConector tipo) {
        for (int i = 0; i < qtdCompatibilidades; i++) {
            if (compatibilidades[i].getTipoConector() == tipo) {
                return true;
            }
        }
        return false;
    }

    public String descricao() {
        return marca + " " + modelo + " " + ano;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public Compatibilidade[] getCompatibilidades() { return compatibilidades; }
    public int getQtdCompatibilidades() { return qtdCompatibilidades; }
}