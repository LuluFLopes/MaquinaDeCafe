import util.Temporizador;

public class Display {

    public static void mostraMensagemEstoqueInsuficiente() {
        System.out.println("\nItems no Estoque insuficiente, por favor abasteça a máquina e tente novamente.\n");
    }

    public static void mostraMaquinaDesligada() {
        System.out.println("\n------Maquina desligada------");
        System.out.println("\n1. Abrir o compartimento para reabastecer o estoque");
        System.out.println("2. Ligar Maquina\n");
    }

    public static void desligandoMaquina() {
        System.out.println("\nDesligando a Máquina...");
        Temporizador.temporizador(2);
    }

    public static void exibirMensagemParaContinuarOuSair() {
        System.out.println("\n1. Fechar compartimento");
        System.out.println("2. Continuar abastecendo\n");
    }

    public static void mostraPedirNumeroDoPedido() {
        System.out.println("\nDigite o número referente ao seu pedido.\n");
    }

    public static void mostraPerguntaDeConfirmacaoDoPedido() {
        System.out.println("\nConfirmar o pedido?");
        System.out.println("1. sim");
        System.out.println("2. não\n");
    }

    public static void mostraAgradecimentoParaPrepararPedido() {
        System.out.println("\nObrigado, agora iremos preparar o seu pedido\n");
    }

    public static void mostraOpcoesParaCobrarPedido() {
        System.out.println("\nPor favor escolha o valor/moeda para o pagamento.");
        System.out.println("1. " + "R$ 1.00");
        System.out.println("2. " + "R$ 2.00");
        System.out.println("3. " + "R$ 5.00");
        System.out.println("4. " + "R$ 10.00\n");
    }
}
