package enumerador;

public enum TipoIngrediente {
    PO_DE_CAFE("Pó de café"),
    CHOCOLATE("Chocolate"),
    LEITE_EM_PO("Leite em pó"),
    CHA_DE_LIMAO("Chá de limão"),
    COPO("Copo"),
    ACUCAR("Açucar");

    private final String descricao;

    TipoIngrediente(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
