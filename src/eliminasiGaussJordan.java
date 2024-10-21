import java.util.Scanner;

// Informasi : 
// Persamaan SPL : a1x1 + a2x2 + a3x3 + . . . + axnx = b 
// Matrix hasil dari persamaan diatas :
// | a1x1 b1x2 c1x3 . . . zxn | | b1 | -- Persamaan 1
// | a2x1 b2x2 c3x3 . . . zxn | | b2 | -- Persamaan 2
// | ...............................| |
// | ...............................| |
// | anxn bnxn cnxn . . . zxn | | bn | -- Persamaan ke n

// Sehingga matrixnya ketika input 3x3 maka akan ada penambahan menjadi 3x4 (untuk solusi (kolom b))

public class eliminasiGaussJordan {

    public static void gaussJordan(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            double diagElement = matrix[i][i];
            if (diagElement == 0) {
                throw new ArithmeticException("Elemen diagonal 0 pada baris ke-" + i);
            }
            for (int j = 0; j < cols; j++) {
                matrix[i][j] /= diagElement;
            }

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
        System.out.print("Jumlah baris (persamaan): "); 
        int baris = scan.nextInt();
        System.out.print("Jumlah kolom (variabel tiap persamaan): ");
        int kolom = scan.nextInt();

        double[][] matrix = new double[baris][kolom + 1];
        for (int i = 0; i < baris; i++) {
            System.out.println("Persamaan ke-" + (i + 1));
            System.err.println("===================================");
            for (int j = 0; j <= kolom; j++) { 
                if (j == kolom) {
                    System.out.print("Masukkan hasil dari persamaan ke-" + (i + 1) + ": ");
                } else {
                    System.out.print("Masukkan koefisien dari " + (char) ('a' + j) + " pada persamaan ke-" + (i + 1) + ": ");
                }
                matrix[i][j] = scan.nextDouble();
            }
        }
        System.out.println("========");
        System.out.println("Sistem Persamaan Linear dalam bentuk Matrix:");
        printMatrix(matrix);

        gaussJordan(matrix);

        System.out.println("========");
        System.out.println("Dengan Penyelesaian Eliminasi Gauss-Jordan:");
        printMatrix(matrix);
    }
}