import pagamento.*;
import recarga.*;
import rede.*;
import usuario.*;
import veiculo.*;

public class Main {
    public static void main(String[] args) {
        String hash = "hash-de-exemplo";
 
        TipoConector ccs2 = new TipoConector(1, "CCS2", true);
        TipoConector tipo2 = new TipoConector(2, "Tipo 2", false);

        ModeloVeiculo dolphin = new ModeloVeiculo(1, "BYD", "Dolphin Mini", 2025);
        dolphin.adicionarCompatibilidade(ccs2, 40);
        dolphin.adicionarCompatibilidade(tipo2, 6.6);
        ModeloVeiculo ora = new ModeloVeiculo(2, "GWM", "Ora 03", 2024);
        ora.adicionarCompatibilidade(ccs2, 64);
        ora.adicionarCompatibilidade(tipo2, 11);
        ModeloVeiculo hibrido = new ModeloVeiculo(3, "BYD", "Song Plus", 2024);
        hibrido.adicionarCompatibilidade(tipo2, 6.6);

        Cidade saoPaulo = new Cidade(1, "São Paulo", "SP");

        Estacao paulista = new Estacao(1, "ElectraVia Paulista", "Av. Paulista", "1000", "Bela Vista", saoPaulo,
                -23.5649, -46.6523);
        Conector pau1 = new Conector(1, "PAU-01", paulista, ccs2, 60, 2.39);
        Conector pau2 = new Conector(2, "PAU-02", paulista, ccs2, 60, 2.39);
        paulista.adicionarConector(pau1);
        paulista.adicionarConector(pau2);

        Estacao pinheiros = new Estacao(2, "ElectraVia Pinheiros", "Rua dos Pinheiros", "500", "Pinheiros",
                saoPaulo, -23.5672, -46.6920);
        Conector pin1 = new Conector(3, "PIN-01", pinheiros, tipo2, 7.4, 1.59);
        pinheiros.adicionarConector(pin1);

        Motorista marcos = new Motorista(1, "Marcos Tavares", "marcos@exemplo.com", hash, "11 90000-0001",
                "03/08/2026");
        Motorista bianca = new Motorista(2, "Bianca Souza", "bianca@exemplo.com", hash, "11 90000-0002",
                "10/08/2026");
        Motorista caio = new Motorista(3, "Caio Lima", "caio@exemplo.com", hash, "11 90000-0003", "15/09/2026");
        Motorista dani = new Motorista(4, "Dani Rocha", "dani@exemplo.com", hash, "11 90000-0004", "20/09/2026");
        Funcionario helena = new Funcionario(5, "Helena Marques", "helena@electravia.com", hash, "EV-001",
                "GESTOR");
        Funcionario rui = new Funcionario(6, "Rui Prado", "rui@electravia.com", hash, "EV-002", "TECNICO");
        Funcionario ana = new Funcionario(7, "Ana Dias", "ana@electravia.com", hash, "EV-003", "ADMINISTRADOR");

        Veiculo carroMarcos = new Veiculo(1, "Dolphin do app", marcos, dolphin);
        Veiculo carroBianca = new Veiculo(2, bianca, ora);
        Veiculo carroCaio = new Veiculo(3, caio, dolphin);
        Veiculo carroDani = new Veiculo(4, dani, hibrido);

        System.out.println("===== PLANOS =====");
        Plano frequente = new Plano(1, "Frequente", 49.90, 0.40);
        marcos.assinar(1, frequente, "01/09/2026");

        Usuario[] usuarios = {marcos, bianca, caio, dani, helena, rui, ana};
        Estacao[] rede = {paulista, pinheiros};

        System.out.println("\n===== USUÁRIOS E PERMISSÕES =====");
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i].exibirDados();
            System.out.println("Acessa faturamento: " + usuarios[i].podeAcessar("FATURAMENTO")
                    + " Acessa manutenção: " + usuarios[i].podeAcessar("MANUTENCAO")
                    + " Pode recarregar: " + usuarios[i].podeAcessar("RECARGA"));
        }

        System.out.println("\n===== RECARGAS ANTERIORES DO MARCOS =====");
        SessaoRecarga sessao1 = pin1.iniciarRecarga(1, carroMarcos, "29/09/2026 13:00");
        sessao1.encerrar("29/09/2026 14:35", 10.2);
        marcos.getHistorico().PUSH(sessao1);

        SessaoRecarga sessao2 = pau1.iniciarRecarga(2, carroMarcos, "30/09/2026 07:30");
        sessao2.encerrar("30/09/2026 08:05", 21.0);
        marcos.getHistorico().PUSH(sessao2);

        System.out.println("\n===== MANUTENÇÃO =====");
        pau2.registrarStatus("FALHA", "01/10/2026 07:40");
        Manutencao ordem = new Manutencao(1, paulista, "PAU-02 sem cabo (furto)", "01/10/2026 07:40", "ALTA");
        ordem.atribuir(helena);
        ordem.atribuir(rui);

        System.out.println("\n===== FILA DE ESPERA NA ESTAÇÃO PAULISTA =====");
        SessaoRecarga sessao3 = pau1.iniciarRecarga(3, carroMarcos, "01/10/2026 08:00");

        Veiculo[] chegando = {carroBianca, carroCaio, carroDani};
        for (int i = 0; i < chegando.length; i++) {
            String nome = chegando[i].getDono().getNome();
            if (!paulista.atende(chegando[i])) {
                System.out.println(nome + " não entra na fila: a estação não tem conector para "
                        + chegando[i].getApelido() + ".");
            } else if (paulista.buscarConectorLivre(chegando[i]) == null) {
                paulista.getFilaEspera().ENQUEUE(chegando[i]);
                System.out.println(nome + " entrou na fila. Veículos aguardando: "
                        + paulista.getFilaEspera().tamanho());
            }
        }
        System.out.println("Fila agora:");
        paulista.getFilaEspera().exibir();

        System.out.println("\n===== ENCERRAMENTO, CUSTO E PAGAMENTO =====");
        sessao3.encerrar("01/10/2026 08:38", 18.4);
        marcos.getHistorico().PUSH(sessao3);
        System.out.println("Custo: " + sessao3.getEnergiaKwh() + " kWh x (R$ " + sessao3.getPrecoKwhAplicado()
                + " - R$ " + sessao3.getDescontoKwhAplicado() + " de desconto do plano) = R$ "
                + sessao3.calcularCusto());

        Pagamento pagamentoRecarga = new PagamentoRecarga(1, sessao3, "PIX", "01/10/2026 08:39");
        pagamentoRecarga.aprovar("PSP-7F3A91");
        Pagamento pagamentoMensalidade = new PagamentoMensalidade(2, marcos.getAssinatura(), "10/2026",
                "CARTAO_CREDITO", "01/10/2026 00:05");
        pagamentoMensalidade.aprovar("PSP-7F3A12");
        Pagamento[] pagamentos = {pagamentoRecarga, pagamentoMensalidade};
        for (int i = 0; i < pagamentos.length; i++) {
            pagamentos[i].emitirComprovante();
        }

        System.out.println("\n===== CHAMANDO A FILA =====");
        FilaEspera.Retorno chamado = paulista.getFilaEspera().DEQUEUE();
        if (chamado.ok) {
            System.out.println("Chamado: " + chamado.item.getDono().getNome());
            SessaoRecarga sessao4 = pau1.iniciarRecarga(4, chamado.item, "01/10/2026 08:40");
            System.out.println("Fila agora:");
            paulista.getFilaEspera().exibir();
            sessao4.encerrar("01/10/2026 09:20", 30.0);
        }

        caio.desativar();
        chamado = paulista.getFilaEspera().DEQUEUE();
        if (chamado.ok) {
            System.out.println("Chamado: " + chamado.item.getDono().getNome());
            pau1.iniciarRecarga(5, chamado.item, "01/10/2026 09:22");
        }
        System.out.println("Fila vazia? " + paulista.getFilaEspera().IsEmpty());

        System.out.println("\n===== HISTÓRICO DE RECARGAS DO MARCOS =====");
        PilhaRecargas.Retorno res = marcos.getHistorico().TOP();
        if (res.ok) {
            System.out.println("Execução do TOP (recarga mais recente):");
            res.item.exibirDados();
        }
        System.out.println("Desempilhando com POP, da mais recente para a mais antiga:");
        double consumoTotal = 0;
        double gastoTotal = 0;
        do {
            res = marcos.getHistorico().POP();
            if (res.ok) {
                res.item.exibirDados();
                consumoTotal += res.item.getEnergiaKwh();
                gastoTotal += res.item.calcularCusto();
            }
        } while (res.ok);
        System.out.println("Consumo acumulado: " + Math.round(consumoTotal * 10) / 10.0 + " kWh Gasto: R$ "
                + Math.round(gastoTotal * 100) / 100.0);

        System.out.println("\n===== SITUAÇÃO DA REDE =====");
        ordem.concluir("01/10/2026 10:10");
        pau2.registrarStatus("LIVRE", "01/10/2026 10:10");
        for (int i = 0; i < rede.length; i++) {
            rede[i].exibirDados();
        }
        System.out.println(dani.getNome() + " pode usar a " + pinheiros.getNome() + "? "
                + pinheiros.atende(carroDani));
    }
}