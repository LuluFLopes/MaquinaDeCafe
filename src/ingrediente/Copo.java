package ingrediente;

import enumerador.TipoIngrediente;

public class Copo extends Ingrediente {

    public Copo(int limiteEstoque, int quantidadeAtual, TipoIngrediente tipo) {
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
        return TipoIngrediente.COPO.equals(tipo);
    }
}
