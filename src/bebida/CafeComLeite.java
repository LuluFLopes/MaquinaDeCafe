package bebida;

import enumerador.TipoBebida;
import util.Temporizador;

import java.math.BigDecimal;

import static util.Temporizador.UM_SEGUNDO;

public class CafeComLeite extends Bebidas {

    public CafeComLeite(BigDecimal valor, TipoBebida tipo) {
        super(valor, tipo);
    }

    @Override
    public void iniciarPreparacao() {

    }

    @Override
    protected void executarPreparacao() {
        System.out.println("\nFervendo a água...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Adicionando o filtro...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Colocando o pó de café no filtro...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Despejando a água quente no filtro...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Colocando o café no copo...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Adicionando o leite em pó...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Adicionando o açucar...\n");
    }
}
