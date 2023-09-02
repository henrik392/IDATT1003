package oving8;

import java.util.Calendar;
import java.util.Scanner;

public class Oppg1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Person person = new Person("Ola", "Nordmann", 1990);
        ArbTaker arbTaker = new ArbTaker(person, 123456, 2010, 30000, 20);

        boolean running = true;
        while (running) {
            System.out.println("1. Vis navn");
            System.out.println("2. Vis alder");
            System.out.println("3. Vis år ansatt");
            System.out.println("4. Sjekk om ansatt lengre enn");
            System.out.println("5. Vis brutto lønn per år");
            System.out.println("6. Vis skattetrekk");
            System.out.println("7. Endre månedslønn");
            System.out.println("8. Endre skatteprosent");
            System.out.println("9. Avslutt");

            int choice = in.nextInt();

            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println("Navn: " + arbTaker.getNavn());
                    break;
                case 2:
                    System.out.println("Alder: " + arbTaker.getAlder());
                    break;
                case 3:
                    System.out.println("År ansatt: " + arbTaker.getAarAnsatt());
                    break;
                case 4:
                    System.out.println("Skriv inn antall år:");
                    int years = in.nextInt();
                    System.out.println("Ansatt lenger enn " + years + " år: " + arbTaker.ansattLengreEnn(years));
                    break;
                case 5:
                    System.out.println("Brutto lønn per år: " + arbTaker.getBruttoLonnPerAar());
                    break;
                case 6:
                    System.out.println("Skattetrekk: " + arbTaker.skattetrekk());
                    break;
                case 7:
                    System.out.println("Skriv inn ny månedslønn:");
                    double newSalary = in.nextDouble();
                    arbTaker.setMaanedsloenn(newSalary);
                    break;
                case 8:
                    System.out.println("Skriv inn ny skatteprosent:");
                    double newTaxRate = in.nextDouble();
                    arbTaker.setSkatteprosent(newTaxRate);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldig valg");
                    break;
            }

            System.out.println();
        }

        in.close();
    }
}

class Person {
    private final String fornavn;
    private final String etternavn;
    private final int fodselsaar;

    public Person(String fornavn, String etternavn, int fodselsaar) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fodselsaar = fodselsaar;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public int getFodselsaar() {
        return fodselsaar;
    }
}

class ArbTaker {
    private Person personalia;
    private int arbtakernr;
    private int ansettelsesaar;
    private double maanedsloenn;
    private double skatteprosent;

    public ArbTaker(Person personalia, int arbtakernr, int ansettelsesaar, double maanedsloenn, double skatteprosent) {
        this.personalia = personalia;
        this.arbtakernr = arbtakernr;
        this.ansettelsesaar = ansettelsesaar;
        this.maanedsloenn = maanedsloenn;
        this.skatteprosent = skatteprosent;
    }

    public Person getPersonalia() {
        return personalia;
    }

    public int getArbtakernr() {
        return arbtakernr;
    }

    public int getAnsettelsesaar() {
        return ansettelsesaar;
    }

    public double getMaanedsloenn() {
        return maanedsloenn;
    }

    public double getSkatteprosent() {
        return skatteprosent;
    }

    public double getSkattPerMaaned() {
        return maanedsloenn * skatteprosent / 100;
    }

    public double getBruttoLonnPerAar() {
        return maanedsloenn * 12;
    }

    public double skattetrekk() {
        return getSkattPerMaaned() * 1.5;
    }

    public String getNavn() {
        return personalia.getEtternavn() + ", " + personalia.getFornavn();
    }

    public int getAlder() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - personalia.getFodselsaar();
    }

    public int getAarAnsatt() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - ansettelsesaar;
    }

    public boolean ansattLengreEnn(int aar) {
        return getAarAnsatt() > aar;
    }

    public void setMaanedsloenn(double maanedsloenn) {
        this.maanedsloenn = maanedsloenn;
    }

    public void setSkatteprosent(double skatteprosent) {
        this.skatteprosent = skatteprosent;
    }

    public void setPersonalia(Person personalia) {
        this.personalia = personalia;
    }

    public void setArbtakernr(int arbtakernr) {
        this.arbtakernr = arbtakernr;
    }

    public void toStrin() {
        System.out.println("Navn: " + getNavn());
        System.out.println("Alder: " + getAlder());
        System.out.println("Aar ansatt: " + getAarAnsatt());
        System.out.println("Ansatt lenger enn 10 aar: " + ansattLengreEnn(10));
        System.out.println("Brutto loenn per aar: " + getBruttoLonnPerAar());
        System.out.println("Skattetrekk: " + skattetrekk());
    }
}