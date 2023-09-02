package oving1;

public class Oppg1 {
    public static void main(String[] args) {
        double tommer = 10;
        System.out.println(tommer + " tommer er " + tommerTilCentimeter(tommer) + " cm.");
    }

    static double tommerTilCentimeter(double tommer) {
        final double CM_PER_TOMME = 2.54;

        return tommer * CM_PER_TOMME;
    }
}
