package oving10;

import java.util.ArrayList;
import java.util.HashMap;

public class Oppg2 {
    public static void main(String[] args) {
        MenyRegister menyRegister = new MenyRegister();

        // Må kunne gjøre følgende:
        // • Å registrere en ny rett.
        // • Å finne en rett, gitt navnet.
        // • Å finne alle retter av en gitt type.
        // • Å registrere en ny meny som består av ett sett med retter.
        // • Å finne alle menyer med totalpris innenfor et gitt intervall.

        // Registrer retter direkte til meny
        ArrayList<Rett> voksenMenyRetter = new ArrayList<>();
        voksenMenyRetter.add(new Rett("Kjøttkaker", MatType.HOVEDRETT, "Kok kjøttkaker", 100.0));
        menyRegister.registrerMeny(new Meny("Voksen-meny", voksenMenyRetter));

        // Registrer meny først
        menyRegister.registrerMeny(new Meny("Barne-meny"));
        menyRegister.registrerMeny(new Meny("Gull-meny"));

        // Registrer retter til registeret og senere legge dem ut fra navn
        menyRegister.registrerRett(new Rett("Pølse", MatType.HOVEDRETT, "Kok pølse", 50.0));
        menyRegister.leggRettTilMeny("Barne-meny", "Pølse");

        // Legg til rett direkte fra `Rett` objekt
        menyRegister.leggRettTilMeny("Barne-meny",
                new Rett("Is", MatType.DESSERT, "Legg is i skål", 20.0));
        menyRegister.leggRettTilMeny("Gull-meny",
                new Rett("Gull-Kjøttkaker", MatType.HOVEDRETT, "Kok kjøttkaker og legg på gull", 300.0));
        menyRegister.leggRettTilMeny("Gull-meny",
                new Rett("Gull-Pølse", MatType.HOVEDRETT, "Kok pølse og legg på gull", 150.0));
        menyRegister.leggRettTilMeny("Gull-meny",
                new Rett("Gull-Is", MatType.DESSERT, "Legg gull på is", 100.0));

        // Legg til samme rett i to menyer
        // menyRegister.leggRettTilMeny("Voksen-meny", "Is");

        // Finn rett
        System.out.println("Finner rett Pølse:");
        System.out.println(menyRegister.finnRett("Pølse"));
        System.out.println("\n------------------\n");

        // Finn retter fra type
        System.out.println("Finner retter fra type HOVEDRETT:");
        menyRegister.finnRetterFraType(MatType.HOVEDRETT).forEach(rett -> System.out.println(rett.toString() + "\n"));
        System.out.println("\n------------------\n");

        // Finn alle menyer innenfor intervall
        System.out.println("Finner alle menyer innenfor pris-intervall 100-200:");
        menyRegister.finnMenyerInnenforPrisIntervall(100, 200).forEach(meny -> meny.skrivUt());
        System.out.println("\n------------------\n");

        // Skriv ut alle menyer
        System.out.println("Til slutt, skriver ut alle menyer:");
        menyRegister.skrivUtMenyer();
    }
}

enum MatType {
    FORRETT, HOVEDRETT, DESSERT
}

class Rett {
    private String navn;
    private MatType type;
    private String oppskrift;
    private double pris;

    public Rett(String navn, MatType type, String oppskrift, double pris) {
        this.navn = navn;
        this.type = type;
        this.oppskrift = oppskrift;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public MatType getType() {
        return type;
    }

    public String getOppskrift() {
        return oppskrift;
    }

    public double getPris() {
        return pris;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setOppskrift(String oppskrift) {
        this.oppskrift = oppskrift;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String toString() {
        return "Navn: " + navn + "\nType: " + type + "\nOppskrift: " + oppskrift + "\nPris: " + pris;
    }
}

class Meny {
    private String navn;
    private ArrayList<Rett> retter = new ArrayList<Rett>();

    public Meny(String navn) {
        this.navn = navn;
    }

    public Meny(String navn, ArrayList<Rett> retter) {
        this.navn = navn;
        this.retter = retter;
    }

    public String getNavn() {
        return navn;
    }

    public ArrayList<Rett> getRetter() {
        return retter;
    }

    public void leggTilRett(Rett retter) {
        this.retter.add(retter);
    }

    public Rett finnRett(String navn) {
        for (Rett rett : retter) {
            if (rett.getNavn().equals(navn)) {
                return rett;
            }
        }
        return null;
    }

    public ArrayList<Rett> finnRetterFraType(MatType type) {
        ArrayList<Rett> retter = new ArrayList<Rett>();
        for (Rett rett : this.retter) {
            if (rett.getType() == type) {
                retter.add(rett);
            }
        }

        return retter;
    }

    public double getTotalPris() {
        double pris = 0;
        for (Rett rett : retter) {
            pris += rett.getPris();
        }
        return pris;
    }

    public void skrivUt() {
        System.out.println("Navn: " + navn);
        for (Rett rett : retter) {
            System.out.println(rett.toString());
            System.out.println();
        }
    }
}

class MenyRegister {
    private HashMap<String, Meny> menyer = new HashMap<String, Meny>();
    private HashMap<String, Rett> retter = new HashMap<String, Rett>();

    public void registrerMeny(Meny meny) {
        this.menyer.put(meny.getNavn(), meny);
    }

    public void registrerRett(Rett rett) {
        this.retter.put(rett.getNavn(), rett);
    }

    public void leggRettTilMeny(String menyNavn, String rettNavn) {
        Meny meny = menyer.get(menyNavn);
        if (meny == null)
            menyer.put(menyNavn, new Meny(menyNavn));

        Rett rett = retter.get(rettNavn);
        if (rett == null) {
            System.out.println("Rett ikke funnet");
            return;
        }

        meny.leggTilRett(rett);
    }

    public void leggRettTilMeny(String menyNavn, Rett rett) {
        Meny meny = menyer.get(menyNavn);
        if (meny == null)
            menyer.put(menyNavn, new Meny(menyNavn));

        if (retter.containsKey(rett.getNavn()))
            System.out.println("Rett finnes allerede, erstatter retten med ny rett");

        this.registrerRett(rett);
        meny.leggTilRett(rett);
    }

    public Rett finnRett(String navn) {
        return retter.get(navn);
    }

    public ArrayList<Rett> finnRetterFraType(MatType type) {
        ArrayList<Rett> retter = new ArrayList<Rett>();
        for (Rett rett : this.retter.values()) {
            if (rett.getType() == type) {
                retter.add(rett);
            }
        }

        return retter;
    }

    public ArrayList<Meny> finnMenyerInnenforPrisIntervall(double prisLav, double prisHoy) {
        ArrayList<Meny> menyer = new ArrayList<Meny>();
        for (Meny meny : this.menyer.values()) {
            if (meny.getTotalPris() >= prisLav && meny.getTotalPris() <= prisHoy) {
                menyer.add(meny);
            }
        }

        return menyer;
    }

    public void skrivUtMenyer() {
        for (Meny meny : menyer.values()) {
            meny.skrivUt();
            System.out.println();
        }
    }
}