package ingrediente;

import enumerador.TipoIngrediente;

public class LeiteEmPo extends Ingrediente {

    public LeiteEmPo(int limiteEstoque, int quantidadeAtual, TipoIngrediente tipo) {
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
        return tipo.equals(super.getTipo());
    }
}
