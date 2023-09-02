package oving1;

public class Oppg2 {
    public static void main(String[] args) {
        int timer = 2;
        int minutter = 30;
        int sekunder = 45;
        int totalSekunder = tidTilSekunder(timer, minutter, sekunder);

        System.out.println(timer + " timer, " + minutter + " minutter og " + sekunder + " sekunder er " + totalSekunder
                + " sekunder.");
    }

    static int tidTilSekunder(int timer, int minutter, int sekunder) {
        return timer * 3600 + minutter * 60 + sekunder;
    }
}
