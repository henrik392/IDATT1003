package oving7;

public class Oppg1 {
    public static void main(String[] args) {
        NyString ns = new NyString("denne setningen kan forkortes");
        ns.forkort().print();
        ns.fjernTegn('e').print();
    }
}

class NyString {
    private final String data;

    public NyString(String data) {
        this.data = data;
    }

    public NyString forkort() {
        String newString = "";
        for (String word : data.split(" ")) {
            newString += word.charAt(0);
        }

        return new NyString(newString);
    }

    public NyString fjernTegn(char c) {
        String newString = data;

        int indexToRemove;
        while ((indexToRemove = newString.indexOf(c)) != -1) {
            newString = newString.substring(0, indexToRemove) + newString.substring(indexToRemove + 1);
        }

        // for (int i = 0; i < data.length(); i++) {
        // newString += data.charAt(i) != c ? data.charAt(i) : "";
        // }

        return new NyString(newString);
    }

    public void print() {
        System.out.println(data);
    }
}