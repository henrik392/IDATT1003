package oving1;

public class Oppg3 {
    public static void main(String[] args) {
        int sekunder = 8345;

        int timer = sekunder / 3600;
        int minutter = (sekunder % 3600) / 60;
        int restSekunder = sekunder % 60;

        System.out.println(
                sekunder + " sekunder er " + timer + " timer, " + minutter + " minutter og " + restSekunder
                        + " sekunder.");
    }
}
