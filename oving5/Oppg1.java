package oving5;

public class Oppg1 {
    public static void main(String[] args) {
        testBrok();
    }

    public static void testBrok() {
        Brok brok1 = new Brok(2, 3);
        Brok brok2 = new Brok(43242, 2432432);

        // Test for addisjon
        brok1.adderMed(brok2);
        brok1.printBrok(); // Forventet output: 2497295/3648648

        // Test for subtraksjon
        brok1 = new Brok(2, 3);
        brok1.subtraherMed(brok2);
        brok1.printBrok(); // Forventet output: 2367569/3648648

        // Test for multiplikasjon
        brok1 = new Brok(2, 3);
        brok1.multipliserMed(brok2);
        brok1.printBrok(); // Forventet output: 7207/608108

        // Test for divisjon
        brok1 = new Brok(2, 3);
        brok1.dividerMed(brok2);
        brok1.printBrok(); // Forventet output: 2432432/64863
    }
}

class Brok {
    int teller;
    int nevner;

    public Brok(int teller, int nevner) {
        if (nevner == 0)
            throw new IllegalArgumentException("Nevner kan ikke være 0");

        this.teller = teller;
        this.nevner = nevner;
    }

    public Brok(int teller) {
        this.teller = teller;
        this.nevner = 1;
    }

    public void printBrok() {
        System.out.println(teller + (nevner == 1 ? "" : "/" + nevner));
    }

    public void adderMed(Brok brok) {
        this.teller = this.teller * brok.nevner + brok.teller * this.nevner;
        this.nevner *= brok.nevner;
        this.forkortBrok();
    }

    public void subtraherMed(Brok brok) {
        this.teller = this.teller * brok.nevner - brok.teller * this.nevner;
        this.nevner *= brok.nevner;
        this.forkortBrok();
    }

    public void multipliserMed(Brok brok) {
        this.teller *= brok.teller;
        this.nevner *= brok.nevner;
        this.forkortBrok();

    }

    public void dividerMed(Brok brok) {
        this.teller *= brok.nevner;
        this.nevner *= brok.teller;
        this.forkortBrok();
    }

    private void forkortBrok() {
        int storsteFellesFaktor = this.gcd(this.teller, this.nevner);

        this.teller /= storsteFellesFaktor;
        this.nevner /= storsteFellesFaktor;
    }

    private int gcd(int a, int b) {
        int minNum = Math.min(a, b);
        int maxNum = Math.max(a, b);
        if (maxNum % minNum == 0)
            return minNum;

        return gcd(minNum, maxNum % minNum);
    }
}