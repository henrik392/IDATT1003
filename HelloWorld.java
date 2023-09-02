public class HelloWorld {
    public static void main(String[] args) {
        double tommer = 10;
        double centimeter = tommerTilCentimeter(tommer);
        System.out.println(tommer + " tommer er " + centimeter + " cm.");
    }

    static double tommerTilCentimeter(double tommer) {
        final double CM_PER_TOMME = 2.54;

        return tommer * CM_PER_TOMME;
    }
}