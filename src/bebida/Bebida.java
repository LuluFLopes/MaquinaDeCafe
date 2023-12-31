package bebida;

import enumerador.TipoBebida;
import ingrediente.Ingrediente;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;

public abstract class Bebida {

    private BigDecimal valor;
    private TipoBebida tipo;
    private static final String MENSAGEM_IMPRIMIR = "%d. %s - R$ %s";
    protected static final int QUANTIDADE_ZERADA = 0;
    private static final String MENSAGEM_VALOR_INSUFICIENTE = """
            O valor para pagamento selecionado não é o suficiente para este pedido.
            Por favor tente novamente.
            """;

    public Bebida(BigDecimal valor, TipoBebida tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public void imprimirOpcoes() {
        System.out.println(format(MENSAGEM_IMPRIMIR, tipo.ordinal() + 1, tipo.getDescricao(), getValor()));
    }

    public void validarSeValorFoiSuficiente(BigDecimal valorSelecionado) {
        if (getValor().compareTo(valorSelecionado) > 0) {
            System.out.println(MENSAGEM_VALOR_INSUFICIENTE);
            throw new IllegalArgumentException();
        } else {
            setValor(getValor().subtract(valorSelecionado));
        }
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoBebida getTipo() {
        return tipo;
    }

    public void setTipo(TipoBebida tipo) {
        this.tipo = tipo;
    }

    public abstract void iniciarPreparacao(List<Ingrediente> ingredientes, int nivelSelecionado);

    public abstract boolean isSatisfiedBy(TipoBebida tipoBebida);

    public abstract boolean verificarSeTemEstoque(List<Ingrediente> ingredientes);

    protected abstract void executarPreparacao();

}


