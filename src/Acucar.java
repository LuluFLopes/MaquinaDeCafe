public class Acucar {

    private String acucar = "Açucar";
    private int limiteEstoqueAcucar = 100;
    private int quantidadeAtualAcucar;

    private int nivelAcucarPadrao = 3;
    private int nivelAcucarMaximo = 5;
    private int nivelAcucarMinimo = 0;
    private int numeroDaAguaQuente = 5;

    public void selecionarNivelAcucar (int nivelAcucarSelecionado, int numeroPedido) {

        if (nivelAcucarSelecionado <= nivelAcucarMaximo && nivelAcucarSelecionado >= nivelAcucarMinimo && numeroPedido != numeroDaAguaQuente) {
            System.out.println();
            System.out.println("O nível de açucar selecionado foi: " + nivelAcucarSelecionado);

        } else if (numeroPedido == numeroDaAguaQuente) {
            System.out.println();
            System.out.println("Na opção água quente não há açucar.");
        } else {
            System.out.println();
            System.out.println("Nível de açucar inválido.");
            System.out.println("O Nível de açucar será o padrão: " + getNivelAcucarPadrao());
        }
    }

    public int getNivelAcucarPadrao() {

        return nivelAcucarPadrao;
    }

    public int getNivelAcucarMaximo(){
        return nivelAcucarMaximo;
    }

    public int getNivelAcucarMinimo(){
        return nivelAcucarMinimo;
    }

    public String getAcucar() {
        return acucar;
    }

    public int getLimiteEstoqueAcucar() {
        return limiteEstoqueAcucar;
    }

    public int getQuantidadeAtualAcucar() {
        return quantidadeAtualAcucar;
    }

    public void setQuantidadeAtualAcucar(int quantidadeAtualAcucar) {
        if (quantidadeAtualAcucar <= getLimiteEstoqueAcucar() && quantidadeAtualAcucar + this.quantidadeAtualAcucar <= getLimiteEstoqueAcucar()){
            this.quantidadeAtualAcucar += quantidadeAtualAcucar;
        } else {
            System.out.println("Quantidade de " + getAcucar() + " abastecida inválida.");
            System.out.println();
            System.out.println("Por favor reabasteça entre 0 e " + getLimiteEstoqueAcucar() + " Unidades.");
        }
    }
}