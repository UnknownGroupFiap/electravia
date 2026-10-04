package rede;

import recarga.FilaEspera;
import veiculo.Veiculo;

public class Estacao {
    private int id;
    private String nome;
    private String logradouro;
    private String numero;
    private String bairro;
    private Cidade cidade;
    private double latitude;
    private double longitude;
    private String situacao; 
    private Conector[] conectores = new Conector[10];
    private int qtdConectores;
    private FilaEspera filaEspera;

    public Estacao() {
        filaEspera = new FilaEspera();
        filaEspera.INIT();
    }

    public Estacao(int id, String nome, String logradouro, String numero, String bairro, Cidade cidade,
                   double latitude, double longitude) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.latitude = latitude;
        this.longitude = longitude;
        this.situacao = "ATIVA";
        filaEspera = new FilaEspera();
        filaEspera.INIT();
    }

    public void adicionarConector(Conector conector) {
        if (qtdConectores == conectores.length) {
            System.out.println("Limite de conectores da estação " + nome + " atingido.");
        } else {
            conectores[qtdConectores] = conector;
            qtdConectores++;
        }
    }

    public boolean atende(Veiculo veiculo) {
        for (int i = 0; i < qtdConectores; i++) {
            if (veiculo.compativelCom(conectores[i])) {
                return true;
            }
        }
        return false;
    }

    public Conector buscarConectorLivre(Veiculo veiculo) {
        for (int i = 0; i < qtdConectores; i++) {
            if (conectores[i].estaLivre() && veiculo.compativelCom(conectores[i])) {
                return conectores[i];
            }
        }
        return null;
    }

    public void exibirDados() {
        System.out.println("Estação: " + nome + " Endereço: " + logradouro + ", " + numero + " - " + bairro
                + " - " + cidade.getNome() + "/" + cidade.getUf() + " Situação: " + situacao
                + " Conectores: " + qtdConectores + " Na fila: " + filaEspera.tamanho());
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
    public Conector[] getConectores() { return conectores; }
    public int getQtdConectores() { return qtdConectores; }
    public FilaEspera getFilaEspera() { return filaEspera; }
}