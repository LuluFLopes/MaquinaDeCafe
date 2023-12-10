public class Menu {
    private String cafe = "Café";
    private String cafeComLeite = "Café com Leite";
    private String capuccino = "Capuccino";
    private String cha = "Chá";
    private String aguaQuente = "Água Quente";
    private double precoCafe = 1.00;
    private double precoCafeComLeite = 1.50;
    private double precoCapuccino = 2.00;
    private double precoCha = 1.00;
    private int precoAguaQuente = 0;

    public void mostraOpcoes() {
        System.out.println();
        System.out.println("Bem-vindo a Máquina de Café.");
        System.out.println();
        System.out.println("Essas são suas opções: ");
        System.out.println("1. " + getCafe() + " - R$ " + getPrecoCafe());
        System.out.println("2. " + getCafeComLeite() + " - R$ " + getPrecoCafeComLeite());
        System.out.println("3. " + getCapuccino() + " - R$ " + getPrecoCapuccino());
        System.out.println("4. " + getCha() + " - R$ " + getPrecoCha());
        System.out.println("5. " + getAguaQuente() + " - R$ " + getPrecoAguaQuente());
        System.out.println();
    }

    public String getCafe() {
        return cafe;
    }

    public String getCafeComLeite() {
        return cafeComLeite;
    }

    public String getCapuccino() {
        return capuccino;
    }

    public String getAguaQuente() {
        return aguaQuente;
    }

    public String getCha() {
        return cha;
    }

    public double getPrecoCafe() {
        return precoCafe;
    }

    public double getPrecoCafeComLeite() {
        return precoCafeComLeite;
    }

    public double getPrecoCapuccino() {
        return precoCapuccino;
    }

    public double getPrecoCha() {
        return precoCha;
    }

    public int getPrecoAguaQuente() {
        return precoAguaQuente;
    }
}