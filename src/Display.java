public class Display {

    public void mostraMensagemEstoqueInsuficiente () {
        System.out.println();
        System.out.println("Items no Estoque insuficiente, por favor abasteça a máquina e tente novamente.");
        System.out.println();
    }

    public void mostraMaquinaDesligada () {
        System.out.println();
        System.out.println("------Maquina desligada------");
        System.out.println();
        System.out.println("1. Abrir o compartimento para reabastecer o estoque");
        System.out.println("2. Ligar Maquina");
        System.out.println();
    }

    public void desligandoMaquina () {
        try {
            System.out.println();
            System.out.println("Desligando a Máquina...");
            Thread.sleep(2000);
        }
        catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public void exibirMensagemParaContinuarOuSair(){
        System.out.println();
        System.out.println("1. Fechar compartimento");
        System.out.println("2. Continuar abastecendo");
        System.out.println();
    }

    public void mostraPedirNumeroDoPedido () {
        System.out.println("Digite o número referente ao seu pedido.");
        System.out.println();
    }

    public void mostraPerguntaDeConfirmacaoDoPedido () {
        System.out.println("Confirmar o pedido?");
        System.out.println("1. sim");
        System.out.println("2. não");
    }

    public void mostraAgradecimentoParaPrepararPedido () {
        System.out.println();
        System.out.println("Obrigado, agora iremos preparar o seu pedido");
        System.out.println();
    }

    public void mostraOpcoesParaCobrarPedido () {
        System.out.println();
        System.out.println("Por favor escolha o valor/moeda para o pagamento.");
        System.out.println("1. " + "R$ 1.00");
        System.out.println("2. " + "R$ 2.00");
        System.out.println("3. " + "R$ 5.00");
        System.out.println("4. " + "R$ 10.00");
        System.out.println();
    }
}
