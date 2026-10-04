package recarga;

public class PilhaRecargas {
    private static class NO {
        public SessaoRecarga dado;
        public NO prox;
    }

    public static class Retorno {
        public SessaoRecarga item;
        public boolean ok;
    }

    private NO topo;

    public void INIT() {
        topo = null;
    }

    public boolean IsEmpty() {
        return topo == null;
    }

    public void PUSH(SessaoRecarga item) {
        NO novo = new NO();
        novo.dado = item;
        novo.prox = topo;
        topo = novo;
    }

    public Retorno POP() {
        Retorno saida = new Retorno();
        if (!IsEmpty()) {
            saida.item = topo.dado;
            topo = topo.prox;
            saida.ok = true;
        }
        else
            saida.ok = false;
        return saida;
    }

    public Retorno TOP() {
        Retorno saida = new Retorno();
        if (!IsEmpty()) {
            saida.item = topo.dado;
            saida.ok = true;
        }
        else
            saida.ok = false;
        return saida;
    }

    public void exibir() {
        if (IsEmpty()) {
            System.out.println("Histórico vazio");
        } else {
            NO aux = topo;
            while (aux != null) {
                aux.dado.exibirDados();
                aux = aux.prox;
            }
        }
    }
}