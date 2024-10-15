package SPLPackage;

import MatriksPackage.Matriks;

public class Invers {
    private Matriks matriks;

    public Invers(Matriks matriks) {
        this.matriks = matriks;
    }

    public double[][] transposeMatriks() {
        double[][] hasil = new double[matriks.m_baris][matriks.n_kolom];

        int i, j;
        for (i = 0; i < matriks.m_baris; i++) {
            for (j = 0; j < matriks.n_kolom; j++) {
                hasil[i][j] = matriks.matriks[j][i];
            }
        }
        return hasil;
    }

    private double[][] minorKofaktor(double[][] matriks, int baris, int kolom) {
        double[][] hasil = new double[baris - 1][kolom - 1];
        int bar = 0, kol = 0;

        for (int i = 0; i < baris; i++) {
            if (i == baris)
                continue;
            kol = 0;
            for (int j = 0; j < kolom; j++) {
                if (j == kolom)
                    continue;
                hasil[bar][kol] = matriks[i][j];
                kol += 1;
            }
            bar += 1;
        }
        return hasil;
    }

    public double[][] adjointMatriks() {
        int i, j;
        double[][] adjoint = new double[matriks.m_baris][matriks.n_kolom];

        for (i = 0; i < matriks.m_baris; i++) {
            for (j = 0; j < matriks.n_kolom; j++) {
                double minor = matriks.hitungDeterminan(minorKofaktor(matriks.matriks, i, j), matriks.m_baris - 1);
                adjoint[j][i] = Math.pow(-1, i + j) * minor; // Transposed cofactor
            }
        }
        return adjoint;
    }

    // Fixed inverseWithAdjoin
    public double[][] inverseWithAdjoin() {
        double det = matriks.determinanMatriks();
        if (det == 0) {
            System.out.println("Matriks tidak memiliki invers karena determinannya 0.");
            return null;
        }

        double[][] adjoint = adjointMatriks();
        double[][] inverse = new double[matriks.m_baris][matriks.n_kolom];

        for (int i = 0; i < matriks.m_baris; i++) {
            for (int j = 0; j < matriks.n_kolom; j++) {
                inverse[i][j] = (1 / det) * adjoint[i][j];
            }
        }
        return inverse;
    }
}
