package oving3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Oppg2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                int a = in.nextInt();

                if (a == -1)
                    break;

                System.out.println(a + " is " + (isPrime(a) ? "" : "not ") + "prime.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Integers only.");
                in.next();
            }
        }

        in.close();
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }
}
