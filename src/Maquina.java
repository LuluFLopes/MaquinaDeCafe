import bebida.Bebida;
import enumerador.TipoBebida;
import enumerador.TipoIngrediente;
import ingrediente.Ingrediente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Maquina {

    Scanner scanner = new Scanner(System.in);
    private int numeroMaximoOpcoesDeCobranca = 4;
    private int numeroMinimoOpcoesDeCobranca = 1;
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

        selecionarNivelAcucar(opcaoSelecionada, numeroPedido);
    }

    private void imprimirSeSelecionarInvalido() {
        System.out.println("\nVocê não selecionou nenhuma opção válida, portanto a opção padrão é: " + TipoBebida.CAFE.getDescricao());
        Display.mostraPerguntaDeConfirmacaoDoPedido();
    }

    private void imprimirSeNaoTemEstoque() {
        Display.mostraMensagemEstoqueInsuficiente();
        Display.desligandoMaquina();
        maquinaDesligada();
    }

    private void imprimirSeTemEstoque(Bebida bebida) {
        System.out.println("\nVocê selecionou: " + bebida.getTipo().getDescricao());
        Display.mostraPerguntaDeConfirmacaoDoPedido();
    }

    public void selecionarNivelAcucar(int opcaoSelecionada, int numeroDoPedido) {

        switch (opcaoSelecionada) {

            case 1:
                System.out.println();
                System.out.println("Por favor selecione o nível de Açucar, de "
                        + acucar.getNivelAcucarMinimo() + " a " + acucar.getNivelAcucarMaximo());
                System.out.println();
                break;

            case 2:
                pedirNumeroDoPedido();
                break;
            default:
                pedirNumeroDoPedido();
                break;
        }

        int nivelAcucarSelecionado = scanner.nextInt();
        if (acucar.getQuantidadeAtualAcucar() > 1 * nivelAcucarSelecionado) {
            acucar.selecionarNivelAcucar(nivelAcucarSelecionado, numeroDoPedido);
            opcoesCobrarPedido(numeroDoPedido, nivelAcucarSelecionado);
        } else {
            Display.mostraMensagemEstoqueInsuficiente();
            Display.desligandoMaquina();
            maquinaDesligada();
        }
    }

    public void opcoesCobrarPedido(int numeroDoPedido, int nivelAcucarSelecionado) {
        Display.mostraOpcoesParaCobrarPedido();
        int dinheiro = scanner.nextInt();
        cobrarPedido(dinheiro, numeroDoPedido, nivelAcucarSelecionado);
    }

    public void cobrarPedido(int dinheiro, int numeroPedido, int nivelAcucarSelecionado) {

        if (dinheiro <= numeroMaximoOpcoesDeCobranca && dinheiro >= numeroMinimoOpcoesDeCobranca) {
            switch (dinheiro) {
                case 1:
                    if (numeroPedido == 2 || numeroPedido == 3) {
                        System.out.println();
                        System.out.println("O valor para pagamento selecionado não é o suficiente para este pedido.");
                        System.out.println("Por favor tente novamente.");
                        pedirNumeroDoPedido();
                    } else if (numeroPedido == 5) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 1.00");
                    }
                    break;

                case 2:
                    if (numeroPedido == 1 || numeroPedido == 4) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 1.00");
                    } else if (numeroPedido == 5) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 2.00");
                    } else if (numeroPedido == 2) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 0.50");
                    }
                    break;

                case 3:
                    if (numeroPedido == 1 || numeroPedido == 4) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 4.00");
                    } else if (numeroPedido == 5) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 5.00");
                    } else if (numeroPedido == 2) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 3.50");
                    } else if (numeroPedido == 3) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 3.00");
                    }
                    break;

                case 4:
                    if (numeroPedido == 1 || numeroPedido == 4) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 9.00");
                    } else if (numeroPedido == 5) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 10.00");
                    } else if (numeroPedido == 2) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 8.50");
                    } else if (numeroPedido == 3) {
                        System.out.println();
                        System.out.println("Seu troco é: R$ 8.00");
                    }
                    break;
            }
        } else {
            System.out.println();
            System.out.println("Forma de pagamento inválida!");
            System.out.println("Por favor, tente novamente.");
            pedirNumeroDoPedido();
        }

        preparaPedido(numeroPedido, nivelAcucarSelecionado);
    }

    public void preparaPedido(int numeroPedido, int nivelAcucarSelecionado) {

        switch (numeroPedido) {

            case 2:
                Display.mostraAgradecimentoParaPrepararPedido();
                estoque.setQuantidadeAtualPoDeCafe(receita.consumoDeIngrediente);
                estoque.setQuantidadeAtualLeiteEmPo(receita.consumoDeIngrediente);
                estoque.setQuantidadeAtualCopo(receita.consumoDeIngrediente);
                acucar.setQuantidadeAtualAcucar(receita.consumoDeIngrediente * nivelAcucarSelecionado);
                receita.receitaDeCafeComLeite();
                entregaPedido();
                break;

            case 3:
                Display.mostraAgradecimentoParaPrepararPedido();
                estoque.setQuantidadeAtualPoDeCafe(receita.consumoDeIngrediente);
                estoque.setQuantidadeAtualLeiteEmPo(receita.consumoDeIngrediente);
                estoque.setQuantidadeAtualCopo(receita.consumoDeIngrediente);
                estoque.setQuantidadeAtualChocolate(receita.consumoDeIngrediente);
                acucar.setQuantidadeAtualAcucar(receita.consumoDeIngrediente * nivelAcucarSelecionado);

                receita.receitaDeCappucino();
                entregaPedido();
                break;

            case 4:
                Display.mostraAgradecimentoParaPrepararPedido();
                estoque.setQuantidadeAtualChaDeLimao(receita.consumoDeIngrediente);
                estoque.setQuantidadeAtualCopo(receita.consumoDeIngrediente);
                acucar.setQuantidadeAtualAcucar(receita.consumoDeIngrediente * nivelAcucarSelecionado);

                receita.receitaDeCha();
                entregaPedido();
                break;

            case 5:
                Display.mostraAgradecimentoParaPrepararPedido();
                estoque.setQuantidadeAtualCopo(receita.consumoDeIngrediente);

                receita.receitaDeAguaQuente();
                entregaPedido();
                break;

            default:
                Display.mostraAgradecimentoParaPrepararPedido();
                estoque.setQuantidadeAtualPoDeCafe(receita.consumoDeIngrediente);
                estoque.setQuantidadeAtualCopo(receita.consumoDeIngrediente);
                acucar.setQuantidadeAtualAcucar(receita.consumoDeIngrediente * nivelAcucarSelecionado);

                receita.receitaDeCafe();
                entregaPedido();
                break;
        }
    }

    public void entregaPedido() {
        try {
            System.out.println();
            System.out.println("Seu pedido está pronto!");
            Thread.sleep(2000);
            System.out.println("Obrigado, volte sempre!");
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

        Display.desligandoMaquina();
        maquinaDesligada();
    }

    private void imprimirOpcaoSelecionada(TipoIngrediente tipo) {
        System.out.println("\nVocê escolheu reabastecer: " + tipo.getDescricao());
        System.out.println("Insira a quantidade de unidades para reabastecer esse item.\n");
    }

    private void inicializarMaquina() {
        pedirNumeroDoPedido();
    }

    public static void main(String[] args) {

        Maquina maquina = new Maquina();
        maquina.maquinaDesligada();
    }
}