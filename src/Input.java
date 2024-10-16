import java.util.*;

public class Input {
    static Scanner scan = new Scanner(System.in);

    public static Matriks inputMatriks() {
        int baris, kolom;

        // Prompt for number of rows
        while (true) {
            try {
                System.out.print("Masukkan jumlah baris: ");
                baris = scan.nextInt();

                // Prompt for number of columns
                System.out.print("Masukkan jumlah kolom: ");
                kolom = scan.nextInt();

                // Check if rows and columns are positive
                if (baris <= 0 || kolom <= 0) {
                    System.out.println("Jumlah baris dan kolom harus positif.");
                } else {
                    break; // Exit the loop if valid input is provided
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa bilangan bulat.");
                scan.next(); // Clear invalid input
            }
        }

        if (!isSquareMatriks(baris, kolom)) {
            return null;
        }
        // Create a new matrix with the specified dimensions
        Matriks M = new Matriks(baris, kolom);

        // Read the matrix values
        M.bacaMatriks();
        return M;
    }

    public static boolean isSquareMatriks(int baris, int kolom) {
        return baris == kolom;
    }
}