package util;

public class Temporizador {

    public static final int UM_SEGUNDO = 1;
    public static final int DOIS_SEGUNDOS = 2;
    public static final int TRES_SEGUNDOS = 3;

    public static void temporizador(int tempo) {
        try {
            Thread.sleep(tempo * 1000L);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
