package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matriks {
    public int m_baris;
    public int n_kolom;
    public double[][] matriks;

    public Matriks() {
        this.m_baris = 0;
        this.n_kolom = 0;
        this.matriks = new double[0][0];
    }

    public void buatMatriks(int m, int n) {
        this.m_baris = m;
        this.n_kolom = n;
        this.matriks = new double[m][n];

        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                this.matriks[i][j] = 0;
            }
        }
    }

    public Matriks bacaMatriks() {
        Scanner objMatriks = new Scanner(System.in);

        System.out.print("Masukan panjang baris matrix: ");
        this.m_baris = objMatriks.nextInt();

        System.out.print("Masukan panjang kolom matrix: ");
        this.n_kolom = objMatriks.nextInt();

        this.matriks = new double[this.m_baris][this.n_kolom];

        System.out.println("Masukan elemen matriks: ");
        int i, j;
        for (i = 0; i < this.m_baris; i++) {
            for (j = 0; j < this.n_kolom; j++) {
                System.out.print("Elemen [" + i + "]" + "[" + j + "]: ");
                this.matriks[i][j] = objMatriks.nextDouble();
            }
        }
        return this;
    }

    public void cetakMatriks() {
        int i, j;
        for (i = 0; i < this.m_baris; i++) {
            for (j = 0; j < this.n_kolom; j++) {
                if (this.matriks[i][j] == (int) this.matriks[i][j]) {
                    System.out.print((int) this.matriks[i][j]);
                } else {
                    System.out.print(this.matriks[i][j]);
                }
                if (j < this.n_kolom - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void bacaMatriksDariFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner objFile = new Scanner(file);

            // Read dimensions from file
            this.m_baris = 0;
            this.n_kolom = 0;

            // Count rows
            while (objFile.hasNextLine()) {
                String line = objFile.nextLine();
                if (this.n_kolom == 0) {
                    this.n_kolom = line.split(" ").length; // Get column count from the first line
                }
                this.m_baris++;
            }

            // Reset the scanner to read the file again for elements
            objFile.close();
            objFile = new Scanner(file);

            // Initialize the matrix
            this.matriks = new double[this.m_baris][this.n_kolom];

            // Read elements into the matrix
            for (int i = 0; i < this.m_baris; i++) {
                for (int j = 0; j < this.n_kolom; j++) {
                    if (objFile.hasNextDouble()) {
                        this.matriks[i][j] = objFile.nextDouble();
                    }
                }
            }
            objFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        }
    }

    void determinanMatriks() {
        System.out.println(this.m_baris);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Matriks matriks = new Matriks();

        // User menu to choose input method
        System.out.println("Pilih metode input:");
        System.out.println("1. Input manual");
        System.out.println("2. Baca dari file");
        System.out.print("Masukkan pilihan (1/2): ");
        int pilihan = input.nextInt();

        if (pilihan == 1) {
            // Input matrix manually
            matriks.bacaMatriks();
        } else if (pilihan == 2) {
            // Read matrix from file
            System.out.print("Masukkan nama file (misal: matrix.txt): ");
            String fileName = input.next();
            matriks.bacaMatriksDariFile(fileName);
        } else {
            System.out.println("Pilihan tidak valid!");
            input.close();
            return; // Exit the program
        }

        // Print the matrix
        System.out.println("Matriks yang dibaca:");
        matriks.cetakMatriks();

        matriks.determinanMatriks();
        input.close();
    }
}
