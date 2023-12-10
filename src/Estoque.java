import enumerador.TipoIngrediente;
import ingrediente.*;
import ingrediente.Acucar;

import java.util.List;

public class Estoque {

    private static final List<Ingrediente> listaIngredientes = injetarIngredientes();

    public static List<Ingrediente> listaDeIngredientes() {
        return listaIngredientes;
    }

    private static List<Ingrediente> injetarIngredientes() {
        return listaIngredientes != null && listaIngredientes.size() > 0
                ? listaIngredientes
                : List.of(
                new PoDeCafe(5, 0, TipoIngrediente.PO_DE_CAFE),
                new Chocolate(3, 0, TipoIngrediente.CHOCOLATE),
                new LeiteEmPo(5, 0, TipoIngrediente.LEITE_EM_PO),
                new ChaDeLimao(2, 0, TipoIngrediente.CHA_DE_LIMAO),
                new Copo(20, 0, TipoIngrediente.COPO),
                new Acucar(100, 0, TipoIngrediente.ACUCAR)
        );
    }

    public static Acucar buscarAcucar() {
        return listaIngredientes.stream()
                .filter(ingrediente -> ingrediente.isSatisfiedBy(TipoIngrediente.ACUCAR))
                .findAny()
                .map(ingrediente -> (Acucar) ingrediente)
                .orElseThrow();
    }
}
