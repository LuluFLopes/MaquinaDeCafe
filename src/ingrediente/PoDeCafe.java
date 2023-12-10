package ingrediente;

import enumerador.TipoIngrediente;

public class PoDeCafe extends Ingrediente {

    public PoDeCafe(int limiteEstoque, int quantidadeAtual, TipoIngrediente tipo) {
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
        return TipoIngrediente.PO_DE_CAFE.equals(tipo);
    }
}
