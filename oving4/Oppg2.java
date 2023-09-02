package oving4;

import java.util.Scanner;

public class Oppg2 {
    public static void main(String[] args) {
        Spiller spiller1 = new Spiller("Spiller 1");
        Spiller spiller2 = new Spiller("Spiller 2");

        boolean erSpiller1 = true;

        System.out.println("Hit enter to play next, type \"exit\" to quit.");

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            if (input == "exit")
                break;

            Spiller spiller = erSpiller1 ? spiller1 : spiller2;

            System.out.println(spiller.navn + " kaster terningen");
            spiller.kastTerningen();

            if (spiller.erFerdig()) {
                System.out.println(spiller.navn + " vant! Du har 100 eller mer poeng");
                break;
            }

            erSpiller1 = !erSpiller1;
        }

        in.close();
    }
}

class Spiller {
    String navn;
    int sumPoeng = 0;
    java.util.Random terning = new java.util.Random();

    public Spiller(String navn) {
        this.navn = navn;
    }

    public int getSumPoeng() {
        return sumPoeng;
    }

    public void kastTerningen() {
        int terningKast = terning.nextInt(6) + 1;
        System.out.println(this.navn + " trilte terning " + terningKast);

        if (terningKast == 1) {
            System.out.println("Regelen sier at du må tilbake til 0...");
            this.sumPoeng = 0;
            return;
        }

        if (this.getSumPoeng() > 100) {
            this.sumPoeng -= terningKast;
            System.out.println("Du hadde over 100 poeng så du går tilbake til " + this.sumPoeng + " poeng");
        } else {
            this.sumPoeng += terningKast;
            System.out.println("Du har nå " + this.sumPoeng + " poeng!");
        }

    }

    public boolean erFerdig() {
        return this.sumPoeng == 100;
    }
}
