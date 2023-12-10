package bebida;

import enumerador.TipoBebida;
import enumerador.TipoIngrediente;
import ingrediente.Ingrediente;
import util.Temporizador;

import java.math.BigDecimal;
import java.util.List;

import static util.Temporizador.*;

public class Cha extends Bebida {

    public Cha(BigDecimal valor, TipoBebida tipo) {
        super(valor, tipo);
    }

    @Override
    public void iniciarPreparacao(List<Ingrediente> ingredientes, int nivelSelecionado) {
        removerPreenchimento(ingredientes, nivelSelecionado);
        executarPreparacao();
    }

    private void removerPreenchimento(List<Ingrediente> ingredientes, int nivelSelecionado) {
        ingredientes.stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(TipoIngrediente.CHA_DE_LIMAO))
                .forEach(Ingrediente::removerQuantidade);

        ingredientes.stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(TipoIngrediente.ACUCAR))
                .forEach(ingrediente -> ingrediente.removerQuantidade(nivelSelecionado));
    }

    @Override
    public boolean isSatisfiedBy(TipoBebida tipoBebida) {
        return TipoBebida.CHA.equals(tipoBebida);
    }

    @Override
    public boolean verificarSeTemEstoque(List<Ingrediente> ingredientes) {
        return !ingredientes.stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(TipoIngrediente.CHA_DE_LIMAO))
                .map(Ingrediente::getQuantidadeAtual)
                .toList()
                .contains(QUANTIDADE_ZERADA);
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
