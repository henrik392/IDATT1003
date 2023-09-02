package oving3;

import java.util.Scanner;

public class Oppg1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Skriv inn to tall for å skrive ut tallene fra gangetabellen: ");

        int a = in.nextInt();
        int b = in.nextInt();

        for (int i = a; i <= b; i++) {
            System.out.println(i + "-gangen:");
            for (int j = 0; j < 10; j++) {
                printGange(i, j + 1);
            }
        }

        in.close();
    }

    static void printGange(int a, int b) {
        System.out.println(a + " x " + b + " = " + (a * b));
    }
}
