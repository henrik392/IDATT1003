package oving2;

import java.util.Scanner;

public class Oppg1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Hvilket år er det?: ");
        int aar = in.nextInt();

        System.out.println("År " + aar + " er " + (erSkuddAar(aar) ? "" : "ikke ") + "et skuddår.");

        in.close();
    }

    static boolean erSkuddAar(int aar) {
        if (aar % 100 == 0)
            return aar % 400 == 0;

        return aar % 4 == 0;
    }
}
