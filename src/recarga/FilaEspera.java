package recarga;

import veiculo.Veiculo;

public class FilaEspera {
    private static class NO {
        public Veiculo dado;
        public NO prox;
    }

    public static class Retorno {
        public Veiculo item;
        public boolean ok;
    }

    private NO ini;
    private NO fim;

    public void INIT() {
        ini = null;
        fim = null;
    }

    public boolean IsEmpty() {
        return (ini == null && fim == null);
    }

    public void ENQUEUE(Veiculo item) {
        NO novo = new NO();
        novo.dado = item;
        novo.prox = null;
        if (IsEmpty())
            ini = novo;
        else
            fim.prox = novo;
        fim = novo;
    }

    public Retorno DEQUEUE() {
        Retorno saida = new Retorno();
        if (!IsEmpty()) {
            saida.item = ini.dado;
            ini = ini.prox;
            if (ini == null) fim = null;
            saida.ok = true;
        }
        else
            saida.ok = false;
        return saida;
    }

    public int tamanho() {
        int qtd = 0;
        NO aux = ini;
        while (aux != null) {
            qtd++;
            aux = aux.prox;
        }
        return qtd;
    }

    public void exibir() {
        if (IsEmpty()) {
            System.out.println("Fila vazia");
        } else {
            int posicao = 1;
            NO aux = ini;
            while (aux != null) {
                System.out.println(posicao + "º - " + aux.dado.getDono().getNome() + " (" + aux.dado.getApelido()
                        + ")");
                posicao++;
                aux = aux.prox;
            }
        }
    }
}