package oving9;

import java.util.HashMap;
import java.util.Map;

public class Oppg1 {
    public static void main(String[] args) {
        Oppgaveoversikt oversikt = new Oppgaveoversikt();

        oversikt.regNyStudent(new Student("Ola", 0));
        oversikt.regNyStudent(new Student("Kari", 0));
        oversikt.regNyStudent(new Student("Per", 0));

        oversikt.økAntOppg("Ola", 2);
        oversikt.økAntOppg("Kari", 3);
        oversikt.økAntOppg("Per", 1);

        System.out.println("Antall studenter registrert: " + oversikt.getAntStud());

        System.out.println("Antall oppgaver gjort: " + oversikt.getAntOppg());

        System.out.println("Gjennomsnittlig antall oppgaver gjort: " + oversikt.getGjennomsnittOppgaver());

        System.out.println(oversikt.toString());

    }
}

class Student {
    private String navn;
    private int antOppg;

    public Student(String navn, int antOppg) {
        this.navn = navn;
        this.antOppg = antOppg;
    }

    public String getNavn() {
        return navn;
    }

    public int getAntOppg() {
        return antOppg;
    }

    public void økAntOppg(int økning) {
        antOppg += økning;
    }

    public String toString() {
        return navn + " har gjort " + antOppg + " oppgaver.";
    }
}

class Oppgaveoversikt {
    private Map<String, Student> studenter;
    private int antStud = 0;

    public Oppgaveoversikt() {
        studenter = new HashMap<>();
    }

    public int getAntStud() {
        return antStud;
    }

    public int getAntOppg() {
        int antOppg = 0;
        for (Student student : studenter.values()) {
            antOppg += student.getAntOppg();
        }

        return antOppg;
    }

    public double getGjennomsnittOppgaver() {
        return (double) getAntOppg() / antStud;
    }

    public void regNyStudent(Student nyStudent) {
        if (studenter.containsKey(nyStudent.getNavn())) {
            System.out.println("Studenten " + nyStudent.getNavn() + " er allerede registrert.");
            return;
        }

        studenter.put(nyStudent.getNavn(), nyStudent);
        antStud++;
    }

    public void økAntOppg(String navn, int økning) {
        Student student = studenter.get(navn);
        if (student != null) {
            student.økAntOppg(økning);
            return;
        }

        System.out.println("Finner ikke studenten " + navn);
    }

    public String toString() {
        String output = "Antall studenter registrert: " + antStud + "\n\n";
        for (Student student : studenter.values()) {
            output += student.toString() + "\n";
        }

        return output;
    }
}