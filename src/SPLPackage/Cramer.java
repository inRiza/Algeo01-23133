package SPLPackage;

import Mat.Matriks;

public class Cramer {
    private Matriks matriks;

    public Cramer(Matriks matriks) {
        this.matriks = matriks;
    }

    public double[] hitungCramer(double[] b) {
        if (!matriks.validDeterminan()) {
            System.out.println("Cramer hanya dapat dihitung untuk matriks persegi.");
            return null;
        }
        double detA = matriks.determinanMatriks();
        if (detA == 0) {
            System.out.println("Determinan matriks adalah 0, maka tidak dapat dihitung cramernya.");
        }

        double[] solusi = new double[matriks.m_baris];
        int i, j, k;
        for (i = 0; i < matriks.m_baris; i++) {

            double[][] matriksAi = new double[matriks.m_baris][matriks.n_kolom];
            for (j = 0; j < matriks.m_baris; j++) {
                for (k = 0; k < matriks.n_kolom; k++) {
                    if (k == 1) {
                        matriksAi[j][k] = b[j];
                    } else {
                        matriksAi[j][k] = matriks.matriks[j][k];
                    }
                }
            }
            double detAi = matriks.hitungDeterminan(matriksAi, matriks.m_baris);

            solusi[i] = detAi / detA;

        }
        return solusi;

    }
}
