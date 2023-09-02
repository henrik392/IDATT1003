package oving2;

public class Oppg2 {
    public static void main(String[] args) {
        Kjottdeig kjottdeigA = new Kjottdeig('A', 450, 35.90);
        Kjottdeig kjottdeigB = new Kjottdeig('B', 500, 39.50);

        Kjottdeig billigstKjottdeig = kjottdeigA.prisPerKilo() < kjottdeigB.prisPerKilo() ? kjottdeigA : kjottdeigB;

        System.out.println("Kjøttdeig " + billigstKjottdeig.name + " er billigst.");
        System.out.println("Den veier " + billigstKjottdeig.vekt + " gram og koster " + billigstKjottdeig.pris
                + " kr. Altså " + billigstKjottdeig.prisPerKilo() + " kr/kg.");
    }
}

class Kjottdeig {
    char name;
    double pris;
    double vekt;

    public Kjottdeig(char name, double vekt, double pris) {
        this.name = name;
        this.vekt = vekt;
        this.pris = pris;
    }

    public double prisPerKilo() {
        return pris / vekt * 1000;
    }
}
