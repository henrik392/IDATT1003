package oving6;

import java.util.ArrayList;
import java.util.Scanner;

public class Oppg2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();
            if (input.equals("exit"))
                break;

            TekstAnalyse tekstAnalyse = new TekstAnalyse(input);
            // Generate test for `tekstAnalyse`
            System.out.println("Antall forskjellige bokstaver: " + tekstAnalyse.forskjelligeBokstaver());
            System.out.println("Totalt antall bokstaver: " + tekstAnalyse.getSumBokstaver());
            System.out.println("Prosent ikke-bokstaver: " + tekstAnalyse.prosentIkkeBokstav());
            System.out.println("Antall forekomster av bokstaven 'e': " + tekstAnalyse.antallAvBokstav('e'));
            System.out.println("Bokstavene som forekommer oftest: " + tekstAnalyse.bokstaverSomForekommerOftest());
        }

        in.close();
    }
}

class TekstAnalyse {
    String tekst;
    int[] antallTegn = new int[30];
    int antallBokstaver;
    int sumBokstaver = 0;

    public TekstAnalyse(String tekst) {
        this.tekst = tekst;
        this.antallBokstaver = antallTegn.length - 1;

        for (int i = 0; i < tekst.length(); i++) {
            char tegn = Character.toLowerCase(tekst.charAt(i));
            if ((int) (tegn - 'a') < 0
                    || (int) (tegn - 'a') > 28) {
                antallTegn[antallTegn.length - 1]++;
                continue;
            }

            antallTegn[tegn - 'a']++;
            sumBokstaver++;
        }
    }

    public int forskjelligeBokstaver() {
        int res = 0;
        for (int i = 0; i < antallBokstaver; i++) {
            if (antallTegn[i] > 0)
                res++;
        }

        return res;
    }

    public int getSumBokstaver() {
        return sumBokstaver;
    }

    public double prosentIkkeBokstav() {
        return antallTegn[antallBokstaver] / (double) tekst.length();
    }

    public int antallAvBokstav(char c) {
        return antallTegn[c - 'a'];
    }

    public ArrayList<Character> bokstaverSomForekommerOftest() {
        ArrayList<Character> res = new ArrayList<Character>();

        int maxFreq = 0;
        for (int i = 0; i < antallBokstaver; i++) {
            if (antallTegn[i] > maxFreq) {
                maxFreq = antallTegn[i];
                res.clear();
            }

            if (antallTegn[i] == maxFreq) {
                res.add((char) ('a' + i));
            }
        }

        return res;
    }

}