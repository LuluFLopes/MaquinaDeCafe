package ingrediente;

import enumerador.TipoIngrediente;

import static java.lang.String.format;

public abstract class Ingrediente {

    private final int limiteEstoque;
    private int quantidadeAtual;
    private final TipoIngrediente tipo;
    private static final String MENSAGEM_ESTOQUE_ATUAL = "%s, Quantidade atual disponível: %d";
    private static final String MENSAGEM_SALDO_ATUALIZADO = "Você agora possui: %d  unidades no estoque de %s";
    private static final String MENSAGEM_QUANTIDADE_INVALIDA = """
            Quantidade inválida.
            O limite é %d e você tem em estoque %d, ajuste a quantidade para dentro do permitido
            """;

    public Ingrediente(int limiteEstoque, int quantidadeAtual, TipoIngrediente tipo) {
        this.limiteEstoque = limiteEstoque;
        this.quantidadeAtual = quantidadeAtual;
        this.tipo = tipo;
    }

    public int getLimiteEstoque() {
        return limiteEstoque;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public TipoIngrediente getTipo() {
        return tipo;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public boolean validaSePodeAlterarQuantidade(int novaQuantidade) {
        return quantidadeAtual <= limiteEstoque
                && quantidadeAtual + novaQuantidade <= limiteEstoque;
    }

    public void enviarMensagemDeErroAoAlterarQuantidade() {
        System.out.println(format(MENSAGEM_QUANTIDADE_INVALIDA, this.limiteEstoque, this.quantidadeAtual));
    }

    public void imprimirQuantidadeEmEstoque() {
        System.out.println(format(MENSAGEM_ESTOQUE_ATUAL, getTipo().getDescricao(), getQuantidadeAtual()));
    }

    public void imprimirSaldoAtualizado() {
        System.out.println(format(MENSAGEM_SALDO_ATUALIZADO, getQuantidadeAtual(), getTipo().getDescricao()));
    }

    public void listarOpcaoPorTipo() {
        System.out.println(format("%d. %s, Maximo de unidades: %d", getTipo().ordinal() + 1, getTipo().getDescricao(), getLimiteEstoque()));
    }

    public abstract void alterarQuantidade(int novaQuantidade);

    public abstract boolean isSatisfiedBy(TipoIngrediente tipo);
}
