import java.util.Scanner;

// Informasi : 
// Persamaan SPL : a1x1 + a2x2 + a3x3 + . . . + axnx = b 
// Matrix hasil dari persamaan diatas :
// | a1x1 a2x2 a3x3 . . . axn | | b1 | -- Persamaan 1
// | a1x1 a2x2 a3x3 . . . axn | | b2 | -- Persamaan 2
// | ...............................| |
// | ...............................| |
// | a1x1 a2x2 a3x3 . . . axn | | bn | -- Persamaan ke n

// Sehingga matrixnya ketika input 3x3 maka akan ada penambahan menjadi 3x4 (untuk solusi (kolom b))

public class eliminasiGaussJordan {

    public static void gaussJordan(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            // Make the diagonal contain all 1's
            double diagElement = matrix[i][i];
            for (int j = 0; j < cols; j++) {
                matrix[i][j] /= diagElement;
            }

            // Make the other elements in the current column 0
            for (int k = 0; k < rows; k++) {
                if (k != i) {
                    double factor = matrix[k][i];
                    for (int j = 0; j < cols; j++) {
                        matrix[k][j] -= factor * matrix[i][j];
                    }
                }
            }
        }
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf("%d ", (int) value);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("jmlh baris (persamaan) : "); 
        int baris = scan.nextInt();
        System.out.print("jmlh kolom (variabel tiap persamaan) : ");
        int kolom = scan.nextInt();

        double[][] matrix = new double[baris][kolom+1];
        for (int i = 0; i < baris; i++) {
            System.out.println("Persamaan ke-" + (i + 1));
            System.err.println("===================================");
            for (int j = 0; j < kolom; j++) {
                if (j == kolom - 1) {
                    System.out.print("Masukkan hasil dari persamaan ke-" + (i + 1) + ": ");
                } else {
                    System.out.print("Masukkan koefisien dari " + (char) ('a' + j) + " pada persamaan ke-" + (i + 1) + ": ");
                }
            }
        }

        System.out.println("Sistem Persamaan Linear dalam bentuk Matrix:");
        printMatrix(matrix);

        gaussJordan(matrix);

        System.out.println("Dengan Penyelesaian Eliminasi Gauss-Jordan :");
        printMatrix(matrix);
    }
}