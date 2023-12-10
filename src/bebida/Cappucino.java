package bebida;

import enumerador.TipoBebida;
import enumerador.TipoIngrediente;
import ingrediente.Ingrediente;
import util.Temporizador;

import java.math.BigDecimal;
import java.util.List;

import static util.Temporizador.UM_SEGUNDO;

public class Cappucino extends Bebida {

    public Cappucino(BigDecimal valor, TipoBebida tipo) {
        super(valor, tipo);
    }

    @Override
    public void iniciarPreparacao(List<Ingrediente> ingredientes, int nivelSelecionado) {
        removerIngredientes(ingredientes, nivelSelecionado);
        executarPreparacao();
    }

    private void removerIngredientes(List<Ingrediente> ingredientes, int nivelSelecionado) {
        ingredientes.stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(TipoIngrediente.PO_DE_CAFE)
                        || ingrediente.isSatisfiedBy(TipoIngrediente.COPO)
                        || ingrediente.isSatisfiedBy(TipoIngrediente.LEITE_EM_PO)
                        || ingrediente.isSatisfiedBy(TipoIngrediente.CHOCOLATE))
                .forEach(Ingrediente::removerQuantidade);

        ingredientes.stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(TipoIngrediente.ACUCAR))
                .forEach(ingrediente -> ingrediente.removerQuantidade(nivelSelecionado));
    }

    @Override
    public boolean isSatisfiedBy(TipoBebida tipoBebida) {
        return TipoBebida.CAPUCCINO.equals(tipoBebida);
    }

    @Override
    public boolean verificarSeTemEstoque(List<Ingrediente> ingredientes) {
        return !ingredientes.stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(TipoIngrediente.PO_DE_CAFE)
                        || ingrediente.isSatisfiedBy(TipoIngrediente.COPO)
                        || ingrediente.isSatisfiedBy(TipoIngrediente.LEITE_EM_PO)
                        || ingrediente.isSatisfiedBy(TipoIngrediente.CHOCOLATE))
                .map(Ingrediente::getQuantidadeAtual)
                .toList()
                .contains(QUANTIDADE_ZERADA);
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
        System.out.println("Adicionando o chocolate em pó...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Adicionando a canela em pó...");
        Temporizador.temporizador(UM_SEGUNDO);
        System.out.println("Adicionando o açucar...");
    }
}
