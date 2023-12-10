package bebida;

import enumerador.TipoBebida;
import enumerador.TipoIngrediente;
import ingrediente.Ingrediente;
import util.Temporizador;

import java.math.BigDecimal;
import java.util.List;

import static util.Temporizador.DOIS_SEGUNDOS;

public class AguaQuente extends Bebida {

    public AguaQuente(BigDecimal valor, TipoBebida tipo) {
        super(valor, tipo);
    }

    @Override
    public void iniciarPreparacao() {

    }

    @Override
    public boolean isSatisfiedBy(TipoBebida tipoBebida) {
        return TipoBebida.AGUA_QUENTE.equals(tipoBebida);
    }

    @Override
    public boolean verificarSeTemEstoque(List<Ingrediente> ingredientes) {
        return !ingredientes.stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(TipoIngrediente.COPO))
                .map(Ingrediente::getQuantidadeAtual)
                .toList()
                .contains(QUANTIDADE_ZERADA);
    }

    @Override
    protected void executarPreparacao() {
        System.out.println("\nFervendo a água...");
        Temporizador.temporizador(DOIS_SEGUNDOS);
        System.out.println("Colocando a água quente no copo.\n");
    }
}
