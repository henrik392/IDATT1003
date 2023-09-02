package oving4;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Oppg1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Valuta dollar = new Valuta("dollar", 10.74);
        Valuta euro = new Valuta("euro", 11.60);
        Valuta svenskeKroner = new Valuta("svenske kroner", 0.97);

        // Create map for valuta choice
        Map<Integer, Valuta> valutaMap = new HashMap<>();
        valutaMap.put(1, dollar);
        valutaMap.put(2, euro);
        valutaMap.put(3, svenskeKroner);

        while (true) {
            try {
                System.out.println("Velg valuta:");
                System.out.println("1: dollar");
                System.out.println("2: euro");
                System.out.println("3: svenske kroner");
                System.out.println("4: avslutt");

                int valutaNum = in.nextInt();

                if (valutaNum == 4) {
                    break;
                }

                if (!valutaMap.containsKey(valutaNum)) {
                    System.out.println("Invalid input.");
                    continue;
                }

                Valuta chosenValuta = valutaMap.get(valutaNum);

                System.out.println("Velg retning:");
                System.out.println("1: til NOK");
                System.out.println("2: til valuta");
                int choice = in.nextInt();

                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid input.");
                    continue;
                }

                System.out.println("Skriv inn mengde " + (choice == 1 ? chosenValuta.name : "NOK") + ":");
                double mengde = in.nextDouble();

                if (choice == 1) {
                    System.out
                            .println(mengde + " " + chosenValuta.name + " er " + chosenValuta.tilNok(mengde) + " NOK");
                } else {
                    System.out.println(mengde + " NOK er " + chosenValuta.tilValuta(mengde) + " " + chosenValuta.name);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
        }

        in.close();
    }
}

class Valuta {
    String name;
    double verdiINok;

    public Valuta(String name, double verdiINok) {
        this.name = name;
        this.verdiINok = verdiINok;
    }

    public double tilNok(double mengdeValuta) {
        return mengdeValuta * verdiINok;
    }

    public double tilValuta(double mengdeNok) {
        return mengdeNok / verdiINok;
    }
}