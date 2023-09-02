package oving6;

public class Oppg3 {
    public static void main(String[] args) {
        Matrise m1 = new Matrise(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });
        Matrise m2 = new Matrise(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });
        Matrise m3 = m1.add(m2);

        m3.printMatrise();

        Matrise m4 = m3.transpose();

        m4.printMatrise();

        Matrise m5 = m1.multiply(m4);

        m5.printMatrise();
    }
}

class Matrise {
    private final int[][] matrise;
    private final int rows;
    private final int cols;

    public Matrise(int n, int m) {
        this.rows = n;
        this.cols = m;

        this.matrise = new int[n][m];
    }

    public Matrise(int[][] initMatrise) {
        if (initMatrise == null || initMatrise.length == 0)
            throw new IllegalArgumentException("Input matrisen kan ikke være null eller tom");

        this.rows = initMatrise.length;
        this.cols = initMatrise[0].length;

        this.matrise = new int[rows][];
        for (int i = 0; i < this.rows; i++) {
            if (initMatrise[i] == null || initMatrise[i].length != cols)
                throw new IllegalArgumentException(
                        "Alle rader i input matrisen kan ikke være tom og må ha samme lengde");

            this.matrise[i] = initMatrise[i].clone();
        }
    }

    public Matrise add(Matrise matrise2) {
        if (matrise2.rows != rows || matrise2.cols != cols)
            throw new IllegalArgumentException("Matrisene må ha samme dimensjoner");

        int[][] newMatrise = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newMatrise[i][j] = matrise[i][j] + matrise2.matrise[i][j];
            }
        }

        return new Matrise(newMatrise);
    }

    public Matrise multiply(Matrise matrise2) {
        if (this.cols != matrise2.rows)
            throw new IllegalArgumentException("Matrisene må ha samme dimensjoner");

        int n = this.rows;
        int m = matrise2.cols;

        int[][] newMatrise = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Add up element i,j
                for (int k = 0; k < this.cols; k++) {
                    newMatrise[i][j] += matrise[i][k] * matrise2.matrise[k][j];
                }
            }
        }

        return new Matrise(newMatrise);
    }

    public Matrise transpose() {
        int[][] newMatrise = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newMatrise[j][i] = matrise[i][j];
            }
        }

        return new Matrise(newMatrise);
    }

    public void printMatrise() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrise[i][j] + " ");
            }
            System.out.println();
        }
    }
}
