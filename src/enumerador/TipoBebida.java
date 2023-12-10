package enumerador;

public enum TipoBebida {
    CAFE("Café"),
    CAFE_COM_LEITE("Café com leite"),
    CAPUCCINO("Capuccino"),
    CHA("Chá"),
    AGUA_QUENTE("Água quente");

    private final String descricao;

    TipoBebida(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
