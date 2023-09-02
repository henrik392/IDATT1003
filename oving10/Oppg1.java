package oving10;

import java.util.ArrayList;
import java.util.Scanner;
// UUID
import java.util.UUID;
import java.util.stream.Collectors;

public class Oppg1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrangementRegister register = new ArrangementRegister();

        Klient klient = new Klient();

        // Dummy arrangementer
        register.leggTilArrangement(new Arrangement("Konsert", "Oslo", "Konsert AS", "Konsert",
                20201120, 200, 100));
        register.leggTilArrangement(new Arrangement("Festival", "Oslo", "Festival AS", "Festival",
                20201121, 300, 200));
        register.leggTilArrangement(new Arrangement("Konsert", "Oslo", "Konsert AS", "Konsert",
                20201122, 400, 300));

        System.out.println("Velkommen til arrangementregisteret!");

        boolean running = true;

        while (running) {
            System.out.println("Hva vil du gjøre?");
            System.out.println("1. Legg til arrangement");
            System.out.println("2. Fjern arrangement");
            System.out.println("3. Finn arrangement");
            System.out.println("4. Sorter arrangementer");
            System.out.println("5. Finn arrangementer mellom datoer");
            System.out.println("6. Print ut arrangementer");
            System.out.println("7. Avslutt");

            int valg = in.nextInt();

            switch (valg) {
                case 1: {
                    klient.handleAddArrengement(register, in);
                    break;
                }
                case 2: {
                    klient.handleRemoveArrangement(register, in);
                    break;
                }
                case 3: {
                    klient.handleFindArrangement(register, in);
                    break;
                }
                case 4: {
                    klient.handleSortArrangement(register, in);
                    break;
                }
                case 5: {
                    klient.handleFindArrangementBetweenDates(register, in);
                    break;
                }
                case 6: {
                    klient.handlePrintArrangementer(register);
                    break;
                }
                case 7: {
                    running = false;
                    break;
                }
                default: {
                    System.out.println("Ugyldig valg");
                    break;
                }
            }

            System.out.println();
        }

        in.close();
    }
}

class Klient {
    Felt skrivInnFelt(Scanner in) {
        System.out.println("Du kan velge å søke etter et arrangement ved å skrive inn ut følgende felter: " +
                String.join(", ", Arrangement.getFelter()));
        System.out.println("Skriv inn feltet du vil søke etter:");
        Felt felt = Felt.strengTilFelt(in.next());

        if (felt == null) {
            System.out.println("Ugyldig felt");
            return null;
        }

        return felt;
    }

    String skrivInnVerdi(Scanner in) {
        System.out.println("Skriv inn verdien du vil søke etter:");

        return in.next();
    }

    ArrayList<Arrangement> finnArrangementFraFelt(ArrangementRegister register, Scanner in) {
        Felt felt = skrivInnFelt(in);

        if (felt == null)
            return null;

        String verdi = skrivInnVerdi(in);

        ArrayList<Arrangement> funnetArrangemeter = register.finnArrangementFraFelt(felt, verdi);

        if (funnetArrangemeter.size() == 0) {
            System.out.println("Fant ingen arrangementer med " + felt + " = " + verdi);
            return null;
        }

        return funnetArrangemeter;
    }

    public void handleAddArrengement(ArrangementRegister register, Scanner in) {
        System.out.println("Skriv inn arrangementets navn:");
        String navn = in.next();
        System.out.println("Skriv inn arrangementets sted:");
        String sted = in.next();
        System.out.println("Skriv inn arrangementets arrangør:");
        String arrangor = in.next();
        System.out.println("Skriv inn arrangementets type:");
        String type = in.next();
        System.out.println("Skriv inn arrangementets tidspunkt (ååååmmdd):");
        int tidspunkt = in.nextInt();
        System.out.println("Skriv inn arrangementets pris:");
        int pris = in.nextInt();
        System.out.println("Skriv inn arrangementets antall plasser:");
        int antPlasser = in.nextInt();

        register.leggTilArrangement(new Arrangement(navn, sted, arrangor, type,
                tidspunkt, pris, antPlasser));
    }

    public void handleRemoveArrangement(ArrangementRegister register, Scanner in) {
        ArrayList<Arrangement> funnetArrangemeter = finnArrangementFraFelt(register, in);

        if (funnetArrangemeter == null)
            return;

        for (Arrangement arrangement : funnetArrangemeter) {
            System.out.println("Fjerner arrangementet: " + arrangement.getFelt(Felt.NAVN));
            register.fjernArrangementFraID((int) arrangement.getFelt(Felt.ID));
        }
    }

    public void handleFindArrangement(ArrangementRegister register, Scanner in) {
        ArrayList<Arrangement> funnetArrangemeter = finnArrangementFraFelt(register, in);

        if (funnetArrangemeter == null)
            return;

        for (Arrangement arrangement : funnetArrangemeter) {
            System.out.println("Fant arrangementet:\n" + arrangement.toString() + "\n");
        }
    }

    public void handleSortArrangement(ArrangementRegister register, Scanner in) {
        System.out.println("Du kan velge å sortere arrangementene ved å skrive inn ut følgende felter: " +
                String.join(", ", Arrangement.getFelter()));
        System.out.println("Skriv inn feltet du vil sortere etter:");
        Felt felt = Felt.strengTilFelt(in.next());

        register.sorterVed(felt);

        System.out.println("Arrangementene er nå sortert etter " + felt);
    }

    public void handleFindArrangementBetweenDates(ArrangementRegister register, Scanner in) {
        System.out.println("Skriv inn dato 1 (ååååmmdd):");
        int dato1 = in.nextInt();
        System.out.println("Skriv inn dato 2 (ååååmmdd):");
        int dato2 = in.nextInt();

        ArrayList<Arrangement> funnetArrangemeter = register.getArrangementerMellomDato(dato1, dato2);

        if (funnetArrangemeter.size() == 0) {
            System.out.println("Fant ingen arrangementer mellom datoene " + dato1 + " og " + dato2);
            return;
        }

        for (Arrangement arrangement : funnetArrangemeter) {
            System.out.println("Fant " + register.getAntallArrangementer() + " arrangementet:\n"
                    + arrangement.toString() + "\n");

        }
    }

    public void handlePrintArrangementer(ArrangementRegister register) {
        System.out.println("Det er " + register.getAntallArrangementer() + " arrangementer:");
        for (Arrangement arrangement : register.getArrangementer()) {
            System.out.println(arrangement.toString() + "\n");
        }
    }
}

enum Felt {
    ID, NAVN, STED, ARRANGOR, TYPE, TIDSPUNKT, PRIS, ANTPLASSER;

    public static boolean erString(Felt felt) {
        return switch (felt) {
            case NAVN, STED, ARRANGOR, TYPE -> true;
            default -> false;
        };
    }

    public static Felt strengTilFelt(String felt) {
        return switch (felt) {
            case "id" -> ID;
            case "navn" -> NAVN;
            case "sted" -> STED;
            case "arrangor" -> ARRANGOR;
            case "type" -> TYPE;
            case "tidspunkt" -> TIDSPUNKT;
            case "pris" -> PRIS;
            case "antPlasser" -> ANTPLASSER;
            default -> null;
        };
    }
}

class Arrangement {
    private int id;
    private String navn;
    private String sted;
    private String arrangor;
    private String type;
    private int tidspunkt;
    private int pris;
    private int antPlasser;

    public static String[] getFelter() {
        return new String[] { "id", "navn", "sted", "arrangor", "type", "tidspunkt", "pris", "antPlasser" };
    }

    public static boolean objectIsString(Object obj) {
        return obj instanceof String;
    }

    public Arrangement(String navn, String sted, String arrangor, String type,
            int tidspunkt, int pris, int antPlasser) {
        this.id = UUID.randomUUID().hashCode();
        this.navn = navn;
        this.sted = sted;
        this.arrangor = arrangor;
        this.type = type;
        this.tidspunkt = tidspunkt;
        this.pris = pris;
        this.antPlasser = antPlasser;
    }

    public Object getFelt(Felt felt) {
        switch (felt) {
            case ID:
                return id;
            case NAVN:
                return navn;
            case STED:
                return sted;
            case ARRANGOR:
                return arrangor;
            case TYPE:
                return type;
            case TIDSPUNKT:
                return tidspunkt;
            case PRIS:
                return pris;
            case ANTPLASSER:
                return antPlasser;
            default:
                return null;
        }
    }

    public int getMuligInntekt() {
        return antPlasser * pris;
    }

    public String toString() {
        return "Navn: " + navn + "\nSted: " + sted + "\nArrangør: " + arrangor + "\nType: " + type +
                "\nTidspunkt: " + tidspunkt + "\nPris: " + pris + "\nAntall plasser: " + antPlasser +
                "\nMulig inntekt: " + getMuligInntekt();
    }
}

class ArrangementRegister {
    private ArrayList<Arrangement> arrangementer;

    public ArrangementRegister() {
        arrangementer = new ArrayList<>();
    }

    public int getAntallArrangementer() {
        return arrangementer.size();
    }

    public ArrayList<Arrangement> getArrangementer() {
        return arrangementer;
    }

    public void leggTilArrangement(Arrangement arrangement) {
        arrangementer.add(arrangement);
    }

    public boolean fjernArrangementFraID(int id) {
        for (int i = 0; i < arrangementer.size(); i++) {
            Arrangement arrangement = arrangementer.get(i);
            if ((int) arrangement.getFelt(Felt.ID) == id) {
                arrangementer.remove(i);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Arrangement> finnArrangementFraFelt(Felt felt, String verdi) {
        return new ArrayList<>(arrangementer.stream()
                .filter(a -> Felt.erString(felt) ? a.getFelt(felt).equals(verdi)
                        : (int) a.getFelt(felt) == Integer.parseInt(verdi))
                .collect(Collectors.toList()));
    }

    public void sorterVed(Felt felt) {
        arrangementer.sort((a1, a2) -> {
            if (Felt.erString(felt)) {
                return ((String) a1.getFelt(felt)).compareTo((String) a2.getFelt(felt));
            } else {
                return (int) a1.getFelt(felt) - (int) a2.getFelt(felt);
            }
        });
    }

    public ArrayList<Arrangement> getArrangementerMellomDato(int dato1, int dato2) {
        ArrayList<Arrangement> funnetArrangementer = new ArrayList<>();

        for (Arrangement arrangement : arrangementer) {
            if ((int) arrangement.getFelt(Felt.TIDSPUNKT) >= dato1 &&
                    (int) arrangement.getFelt(Felt.TIDSPUNKT) <= dato2) {
                funnetArrangementer.add(arrangement);
            }
        }

        return funnetArrangementer;
    }
}
