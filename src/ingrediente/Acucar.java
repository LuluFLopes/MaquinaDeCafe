package ingrediente;

import enumerador.TipoIngrediente;

public class Acucar extends Ingrediente {

    private final int nivelPadrao = 3;
    private final int nivelMaximo = 5;
    private final int nivelMinimo = 0;

    public Acucar(int limiteEstoque, int quantidadeAtual, TipoIngrediente tipo) {
        super(limiteEstoque, quantidadeAtual, tipo);
    }

    @Override
    public void alterarQuantidade(int novaQuantidade) {
        if (super.validaSePodeAlterarQuantidade(novaQuantidade)) {
            super.setQuantidadeAtual(super.getQuantidadeAtual() + novaQuantidade);
            super.imprimirSaldoAtualizado();
        } else {
            super.enviarMensagemDeErroAoAlterarQuantidade();
        }
    }

    @Override
    public boolean isSatisfiedBy(TipoIngrediente tipo) {
        return TipoIngrediente.ACUCAR.equals(tipo);
    }

    public int getNivelPadrao() {
        return nivelPadrao;
    }

    public int getNivelMaximo() {
        return nivelMaximo;
    }

    public int getNivelMinimo() {
        return nivelMinimo;
    }
}
