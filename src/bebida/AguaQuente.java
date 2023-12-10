package bebida;

import enumerador.TipoBebida;
import util.Temporizador;

import java.math.BigDecimal;

import static util.Temporizador.DOIS_SEGUNDOS;

public class AguaQuente extends Bebidas{

    public AguaQuente(BigDecimal valor, TipoBebida tipo) {
        super(valor, tipo);
    }

    @Override
    public void iniciarPreparacao() {

    }

    @Override
    protected void executarPreparacao(){
            System.out.println("\nFervendo a água...");
            Temporizador.temporizador(DOIS_SEGUNDOS);
            System.out.println("Colocando a água quente no copo.\n");
    }
}
