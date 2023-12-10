package bebida;

import enumerador.TipoBebida;
import util.Temporizador;

import java.math.BigDecimal;

import static util.Temporizador.*;

public class Cha extends Bebidas {

    public Cha(BigDecimal valor, TipoBebida tipo) {
        super(valor, tipo);
    }

    @Override
    public void iniciarPreparacao() {

    }

    @Override
    protected void executarPreparacao() {
        System.out.println("\nFervendo a água...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Colocando o chá de limão na água...");
        Temporizador.temporizador(TRES_SEGUNDOS);
        System.out.println("Aguarde...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Colocando o chá no copo...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Adicionando o açucar...");
    }
}
