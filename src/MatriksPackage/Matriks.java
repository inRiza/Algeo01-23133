package MatriksPackage;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

    // Fixed kofaktorMatriks
    public void kofaktorMatriks() {
        if (!validDeterminan()) {
            System.out.println("Kofaktor matriks hanya dapat dihitung pada matriks persegi.");
        } else {
            int i, j;
            double[][] kofaktor = new double[this.m_baris][this.n_kolom];

            for (i = 0; i < this.m_baris; i++) {
                for (j = 0; j < this.n_kolom; j++) {
                    double minor = hitungDeterminan(minorKofaktor(this.matriks, i, j), this.m_baris - 1);
                    kofaktor[i][j] = Math.pow(-1, i + j) * minor; // Fixed sign alternation
                }
            }
            this.matriks = kofaktor;
        }
    }

    // Fixed transposeMatriks
    public void transposeMatriks() {
        double[][] hasil = new double[this.n_kolom][this.m_baris]; // Swap dimensions

        int i, j;
        for (i = 0; i < this.m_baris; i++) {
            for (j = 0; j < this.n_kolom; j++) {
                hasil[j][i] = this.matriks[i][j]; // Transpose elements
            }
        }
        this.matriks = hasil;
        int temp = this.m_baris;
        this.m_baris = this.n_kolom;
        this.n_kolom = temp; // Swap row and column counts
    }

    // Adjoint calculation
    public double[][] adjointMatriks() {
        int i, j;
        double[][] adjoint = new double[this.m_baris][this.n_kolom];

        for (i = 0; i < this.m_baris; i++) {
            for (j = 0; j < this.n_kolom; j++) {
                double minor = hitungDeterminan(minorKofaktor(this.matriks, i, j), this.m_baris - 1);
                adjoint[j][i] = Math.pow(-1, i + j) * minor; // Transposed cofactor
            }
        }
        return adjoint;
    }

    // Fixed inverseWithAdjoin
    public double[][] inverseWithAdjoin() {
        double det = determinanMatriks();
        if (det == 0) {
            System.out.println("Matriks tidak memiliki invers karena determinannya 0.");
            return null;
        }

        double[][] adjoint = adjointMatriks();
        double[][] inverse = new double[this.m_baris][this.n_kolom];

        for (int i = 0; i < this.m_baris; i++) {
            for (int j = 0; j < this.n_kolom; j++) {
                inverse[i][j] = (1 / det) * adjoint[i][j]; // Divide each adjoint element by determinant
            }
        }
        return inverse;
    }

    public double[] pisahSpl() {
        double[] b = new double[this.m_baris];

        int i, j;
        for (i = 0; i < this.m_baris; i++) {
            b[i] = this.matriks[i][n_kolom - 1];
        }
        return b;
    }

    public double[] hitungCramer(double[] b) {
        if (!validDeterminan()) {
            System.out.println("Cramer hanya dapat dihitung untuk matriks persegi.");
            return null;
        }
        double detA = determinanMatriks();
        if (detA == 0) {
            System.out.println("Determinan matriks adalah 0, maka tidak dapat dihitung cramernya.");
        }

        double[] solusi = new double[this.m_baris];
        int i, j, k;
        for (i = 0; i < this.m_baris; i++) {

            double[][] matriksAi = new double[this.m_baris][this.n_kolom];
            for (j = 0; j < this.m_baris; j++) {
                for (k = 0; k < this.n_kolom; k++) {
                    if (k == 1) {
                        matriksAi[j][k] = b[j];
                    } else {
                        matriksAi[j][k] = this.matriks[j][k];
                    }
                }
            }
            double detAi = hitungDeterminan(matriksAi, this.m_baris);

            solusi[i] = detAi / detA;

        }
        return solusi;

    }

    public void cetakList(double[] a) {
        int i;
        for (i = 0; i < this.m_baris; i++) {
            System.out.println(a[i]);
        }
    }
}
