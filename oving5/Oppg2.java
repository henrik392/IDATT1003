package oving5;

public class Oppg2 {
    public static void main(String[] args) {
        MinRandom minRandom = new MinRandom();

        // Teste MinRandom
        for (int i = 0; i < 10; i++) {
            System.out.println(minRandom.nesteHeltall(0, 5));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(minRandom.nesteDesimaltall(0, 5));
        }
    }
}

class MinRandom {
    java.util.Random random = new java.util.Random();

    public int nesteHeltall(int nedre, int ovre) {
        return random.nextInt(ovre - nedre + 1) + nedre;
    }

    public double nesteDesimaltall(double nedre, double ovre) {
        return random.nextDouble() * (ovre - nedre) + nedre;
    }
}