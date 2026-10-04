package rede;

public class TipoConector {
    private int id;
    private String nome;
    private boolean correnteContinua;

    public TipoConector() {
    }

    public TipoConector(int id, String nome, boolean correnteContinua) {
        this.id = id;
        this.nome = nome;
        this.correnteContinua = correnteContinua;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public boolean isCorrenteContinua() { return correnteContinua; }
    public void setCorrenteContinua(boolean correnteContinua) { this.correnteContinua = correnteContinua; }
}