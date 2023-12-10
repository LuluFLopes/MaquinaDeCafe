import bebida.Bebida;
import enumerador.TipoBebida;
import enumerador.TipoIngrediente;
import ingrediente.Acucar;
import ingrediente.Ingrediente;
import util.Temporizador;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Maquina {

    Scanner scanner = new Scanner(System.in);
    private static final int PRIMEIRA_OPCAO_VALOR = 1;
    private static final int ULTIMA_OPCAO_VALOR = 4;
    private static final int ABRIR_COMPARTIMENTO = 1;
    private static final int FECHAR_COMPARTIMENTO = 1;
    private static final int LIGAR_MAQUINA = 2;

    public void quantidadeAtualEstoque() {
        Estoque.listaDeIngredientes()
                .forEach(Ingrediente::imprimirQuantidadeEmEstoque);
    }

    public void abastecerIngrediente(TipoIngrediente tipo, int novaQuantidade) {
        Estoque.listaDeIngredientes()
                .stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(tipo))
                .findAny()
                .orElseThrow()
                .alterarQuantidade(novaQuantidade);

        continuarAbastecendoOuSair();
        escolherItemParaReabastecer();
    }

    public void abastecerItemEscolhido(int opcaoSelecionada) {
        TipoIngrediente tipo = null;

        switch (opcaoSelecionada) {
            case 1 -> tipo = TipoIngrediente.PO_DE_CAFE;
            case 2 -> tipo = TipoIngrediente.CHOCOLATE;
            case 3 -> tipo = TipoIngrediente.LEITE_EM_PO;
            case 4 -> tipo = TipoIngrediente.CHA_DE_LIMAO;
            case 5 -> tipo = TipoIngrediente.COPO;
            case 6 -> tipo = TipoIngrediente.ACUCAR;
        }

        if (tipo != null) {
            imprimirOpcaoSelecionada(tipo);
            int novaQuantidade = scanner.nextInt();
            abastecerIngrediente(tipo, novaQuantidade);
        } else {
            System.out.println("\nNenhum item válido selecionado\n");
        }
    }

    public void escolherItemParaReabastecer() {
        System.out.println("Por favor escolha um item para reabastecer\n");
        Estoque.listaDeIngredientes().forEach(Ingrediente::listarOpcaoPorTipo);
        System.out.println();

        int opcaoSelecionada = scanner.nextInt();
        abastecerItemEscolhido(opcaoSelecionada);
    }

    public void maquinaDesligada() {
        Display.mostraMaquinaDesligada();
        quantidadeAtualEstoque();

        try {
            int opcaoSelecionada = scanner.nextInt();

            switch (opcaoSelecionada) {
                case ABRIR_COMPARTIMENTO -> escolherItemParaReabastecer();
                case LIGAR_MAQUINA -> inicializarMaquina();
                default -> maquinaDesligada();
            }
        } catch (InputMismatchException ex) {
            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida.\n");
                System.out.println("A Máquina foi desligada.");
                scanner.next();
                maquinaDesligada();
            }
        }
    }

    public void continuarAbastecendoOuSair() {
        Display.exibirMensagemParaContinuarOuSair();

        int opcaoSelecionada = scanner.nextInt();

        if (opcaoSelecionada == FECHAR_COMPARTIMENTO) {
            maquinaDesligada();
        } else {
            escolherItemParaReabastecer();
        }
    }

    public void pedirNumeroDoPedido() {
        Menu.listaBebidas.forEach(Bebida::imprimirOpcoes);
        Display.mostraPedirNumeroDoPedido();
        int opcaoSelecionada = scanner.nextInt();
        TipoBebida tipoBebida = null;

        switch (opcaoSelecionada) {
            case 1 -> tipoBebida = TipoBebida.CAFE;
            case 2 -> tipoBebida = TipoBebida.CAFE_COM_LEITE;
            case 3 -> tipoBebida = TipoBebida.CAPUCCINO;
            case 4 -> tipoBebida = TipoBebida.CHA;
            case 5 -> tipoBebida = TipoBebida.AGUA_QUENTE;
        }

        if (tipoBebida == null) {
            imprimirSeSelecionarInvalido();
            tipoBebida = TipoBebida.CAFE;
        }

        selecionarPedido(tipoBebida);
    }

    public void selecionarPedido(TipoBebida tipoBebida) {
        Menu.listaBebidas.stream()
                .filter(bebida -> bebida.isSatisfiedBy(tipoBebida))
                .filter(bebida -> bebida.verificarSeTemEstoque(Estoque.listaDeIngredientes()))
                .findAny()
                .ifPresentOrElse(this::imprimirSeTemEstoque, this::imprimirSeNaoTemEstoque);

        int opcaoSelecionada = scanner.nextInt();
        selecionarNivelAcucar(opcaoSelecionada, tipoBebida);
    }

    private void imprimirSeSelecionarInvalido() {
        System.out.println("\nVocê não selecionou nenhuma opção válida, portanto a opção padrão é: " + TipoBebida.CAFE.getDescricao());
        Display.mostraPerguntaDeConfirmacaoDoPedido();
    }

    private void imprimirSeNaoTemEstoque() {
        Display.mostraMensagemEstoqueInsuficiente();
        desligarMaquina();
    }

    private void imprimirSeTemEstoque(Bebida bebida) {
        System.out.println("\nVocê selecionou: " + bebida.getTipo().getDescricao());
        Display.mostraPerguntaDeConfirmacaoDoPedido();
    }

    public void selecionarNivelAcucar(int opcaoSelecionada, TipoBebida tipoBebida) {
        Acucar acucar = Estoque.buscarAcucar();

        if (opcaoSelecionada == 1) {
            imprimirOpcoesNivelAcucar(acucar);
            int nivelSelecionado = scanner.nextInt();
            if (acucar.getQuantidadeAtual() > 1 * nivelSelecionado) {
                acucar.selecionarNivelAcucar(nivelSelecionado, tipoBebida);
                selecionarOpcaoCobranca(tipoBebida, nivelSelecionado);
            } else {
                imprimirSeNaoTemEstoque();
            }
        } else {
            pedirNumeroDoPedido();
        }
    }

    private void imprimirOpcoesNivelAcucar(Acucar acucar) {
        System.out.println("\nPor favor selecione o nível de Açucar, de " + acucar.getNivelMaximo() + " a " + acucar.getNivelMinimo() + "\n");
    }

    public void selecionarOpcaoCobranca(TipoBebida tipoBebida, int nivelSelecionado) {
        Display.mostraOpcoesParaCobrarPedido();
        int valorInserido = scanner.nextInt();
        cobrarPedido(valorInserido, tipoBebida, nivelSelecionado);
    }

    public void cobrarPedido(int valorSelecionado, TipoBebida tipoBebida, int nivelSelecionado) {
        Bebida bebidaAtual = Menu.listaBebidas.stream()
                .filter(bebida -> bebida.isSatisfiedBy(tipoBebida))
                .findAny()
                .orElseThrow();

        if (validaSeValorSelecionadoEValido(valorSelecionado)) {
            try {
                switch (valorSelecionado) {
                    case 1 -> bebidaAtual.validarSeValorFoiSuficiente(new BigDecimal("1.0"));
                    case 2 -> bebidaAtual.validarSeValorFoiSuficiente(new BigDecimal("2.0"));
                    case 3 -> bebidaAtual.validarSeValorFoiSuficiente(new BigDecimal("5.0"));
                    case 4 -> bebidaAtual.validarSeValorFoiSuficiente(new BigDecimal("10.0"));
                }
            } catch (Exception ex) {
                selecionarOpcaoCobranca(tipoBebida, nivelSelecionado);
            }
        } else {
            imprimirFormaDePagamentoInvalida();
            selecionarOpcaoCobranca(tipoBebida, nivelSelecionado);
        }

        prepararPedido(bebidaAtual, nivelSelecionado);
    }

    private void imprimirFormaDePagamentoInvalida() {
        System.out.println("\nForma de pagamento inválida!");
        System.out.println("Por favor, tente novamente.\n");
    }

    private boolean validaSeValorSelecionadoEValido(int valorSelecionado) {
        return valorSelecionado >= PRIMEIRA_OPCAO_VALOR && valorSelecionado <= ULTIMA_OPCAO_VALOR;
    }

    public void prepararPedido(Bebida bebida, int nivelSelecionado) {
        bebida.iniciarPreparacao(Estoque.listaDeIngredientes(), nivelSelecionado);
        imprimirFinalizacaoDoPedido();
    }

    private void imprimirFinalizacaoDoPedido() {
        Display.mostraAgradecimentoParaPrepararPedido();
        entregaPedido();
    }

    public void entregaPedido() {
        imprimirMensagemPedidoPronto();
        desligarMaquina();
    }

    private void imprimirMensagemPedidoPronto() {
        System.out.println("\nSeu pedido está pronto!");
        Temporizador.temporizador(2);
        System.out.println("Obrigado, volte sempre!");
    }

    private void imprimirOpcaoSelecionada(TipoIngrediente tipo) {
        System.out.println("\nVocê escolheu reabastecer: " + tipo.getDescricao());
        System.out.println("Insira a quantidade de unidades para reabastecer esse item.\n");
    }

    private void inicializarMaquina() {
        pedirNumeroDoPedido();
    }

    private void desligarMaquina() {
        Display.desligandoMaquina();
        maquinaDesligada();
    }

    public static void main(String[] args) {
        Maquina maquina = new Maquina();
        maquina.maquinaDesligada();
    }
}