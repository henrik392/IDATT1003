package oving6;

public class Oppg1 {
    public static void main(String[] args) {
        java.util.Random random = new java.util.Random();
        int[] tall = new int[10];

        int n = 10000;
        int stjerneVerdi = n / 100;

        for (int i = 0; i < n; i++) {
            tall[random.nextInt(10)]++;
        }

        for (int i = 0; i < tall.length; i++) {
            System.out.print(i + "\t" + tall[i] + "\t");
            for (int j = 0; j < tall[i] / stjerneVerdi; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
