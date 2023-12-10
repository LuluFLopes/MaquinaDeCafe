package bebida;

import enumerador.TipoBebida;

import java.math.BigDecimal;

public abstract class Bebidas {

    private BigDecimal valor;
    private TipoBebida tipo;

    public Bebidas(BigDecimal valor, TipoBebida tipo) {
        this.valor = valor;
        this.tipo = tipo;
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

    public abstract void iniciarPreparacao();

    protected abstract void executarPreparacao();

}


