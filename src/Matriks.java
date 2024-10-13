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

            this.m_baris = 0;
            this.n_kolom = 0;

            while (objFile.hasNextLine()) {
                String line = objFile.nextLine();
                if (this.n_kolom == 0) {
                    this.n_kolom = line.split(" ").length;
                }
                this.m_baris++;
            }

            objFile.close();
            objFile = new Scanner(file);

            this.matriks = new double[this.m_baris][this.n_kolom];

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

    public boolean validDeterminan() {
        return this.m_baris == this.n_kolom;
    }

    public double determinanMatriks() {
        if (validDeterminan()) {
            return hitungDeterminan(this.matriks, this.m_baris);
        } else {
            System.out.println("Determinan matriks hanya dapat dihitung pada matriks persegi");
            return 0;
        }
    }

    public double hitungDeterminan(double[][] matriks, int ukuranMatriks) {
        int i;

        if (ukuranMatriks == 1) {
            return matriks[0][0];
        } else if (ukuranMatriks == 2) {
            return (matriks[0][0] * matriks[1][1]) - (matriks[0][1] * matriks[1][0]);
        }

        double hasil = 0;
        for (i = 0; i < ukuranMatriks; i++) {
            hasil += Math.pow(-1, i) * matriks[0][i]
                    * hitungDeterminan(minorKofaktor(matriks, 0, i), ukuranMatriks - 1);
        }
        return hasil;
    }

    private double[][] minorKofaktor(double[][] matriks, int baris, int kolom) {
        double[][] hasil = new double[this.m_baris - 1][this.n_kolom - 1];
        int bar = 0, kol = 0;

        for (int i = 0; i < this.m_baris; i++) {
            if (i == baris)
                continue;
            kol = 0;
            for (int j = 0; j < this.n_kolom; j++) {
                if (j == kolom)
                    continue;
                hasil[bar][kol] = matriks[i][j];
                kol += 1;
            }
            bar += 1;
        }
        return hasil;
    }

    public double[][] kofaktorMatriks() {
        if (!validDeterminan()) {
            System.out.println("Kofaktor matriks hanya dapat dihitung pada matriks persegi.");
            return null;
        }

        else {
            int i, j;
            double[][] kofaktor = new double[this.m_baris][this.n_kolom];

            for (i = 0; i < this.m_baris; i++) {
                for (j = 0; j < this.n_kolom; j++) {
                    double minor = hitungDeterminan(minorKofaktor(this.matriks, i, j), this.m_baris - 1);
                    kofaktor[i][j] = Math.pow(i, j) * minor;
                }
            }
            return kofaktor;
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Matriks matriks = new Matriks();

        System.out.println("Pilih metode input:");
        System.out.println("1. Input manual");
        System.out.println("2. Baca dari file");
        System.out.print("Masukkan pilihan (1/2): ");
        int pilihan = input.nextInt();

        if (pilihan == 1) {
            matriks.bacaMatriks();
        } else if (pilihan == 2) {
            System.out.print("Masukkan nama file (misal: matrix.txt): ");
            String fileName = input.next();
            matriks.bacaMatriksDariFile(fileName);
        } else {
            System.out.println("Pilihan tidak valid!");
            input.close();
            return;
        }

        System.out.println("Matriks yang dibaca:");
        matriks.cetakMatriks();

        double det = matriks.determinanMatriks();
        System.out.println("Determinan matriks yang terhitung adalah: " + det);

        input.close();
    }

}