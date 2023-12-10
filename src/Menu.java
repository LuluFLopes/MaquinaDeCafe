import bebida.*;
import enumerador.TipoBebida;

import java.math.BigDecimal;
import java.util.List;

public class Menu {

    public static final List<Bebida> listaBebidas = injetarBebidas();

    private static List<Bebida> injetarBebidas() {
        return List.of(
                new Cafe(BigDecimal.ONE, TipoBebida.CAFE),
                new CafeComLeite(new BigDecimal("1.5"), TipoBebida.CAFE_COM_LEITE),
                new Cappucino(new BigDecimal("2.0"), TipoBebida.CAPUCCINO),
                new Cha(BigDecimal.ONE, TipoBebida.CHA),
                new AguaQuente(BigDecimal.ZERO, TipoBebida.AGUA_QUENTE)
        );
    }
}