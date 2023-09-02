package oving7;

import java.util.Arrays;

public class Oppg2 {
    public static void main(String[] args) {
        Tekstbehandling tb = new Tekstbehandling(
                "Hei, dette er en tekst. Den inneholder flere setninger. Denne setningen er lengre enn de andre setningene.");
        System.out.println(tb.getTekst());
        System.out.println(tb.antallOrd());
        System.out.println(tb.gjennomsnittOrdLengde());
        System.out.println(tb.ordPerPeriode());
        tb.skiftUtOrd("en", "to");
        System.out.println(tb.getTekst());
        tb.skiftUtOrd("to", "tre");
        System.out.println(tb.getCapitalizedText());
    }
}

class Tekstbehandling {
    private String tekst;

    public Tekstbehandling(String tekst) {
        this.tekst = tekst;
    }

    public int antallOrd() {
        return tekst.split(" ").length;
    }

    public double gjennomsnittOrdLengde() {
        int totalLength = 0;
        for (String word : tekst.split(" ")) {
            totalLength += word.length();
        }

        return (double) totalLength / antallOrd();
    }

    public double ordPerPeriode() {
        return antallOrd() / (double) tekst.split("[.!?]").length;
    }

    public void skiftUtOrd(String ord, String nyttOrd) {
        tekst = Arrays.stream(tekst.split(" ")).map(s -> s.equals(ord) ? nyttOrd : s).reduce("", (a, b) -> a + " " + b)
                .substring(1);
        // .replaceAll(ord, nyttOrd).join(" ");
    }

    public String getTekst() {
        return tekst;
    }

    public String getCapitalizedText() {
        return tekst.toUpperCase();
    }
}