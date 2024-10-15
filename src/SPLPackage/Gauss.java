package SPLPackage;

public class Gauss {
    public static double[][] gaussElimination(double[][] matriks) {
        int n = matriks.length;

        for (int i = 0; i < n; i++) {
            double maxEl = Math.abs(matriks[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matriks[k][i]) > maxEl) {
                    maxEl = Math.abs(matriks[k][i]);
                    maxRow = k;
                }
            }

            double[] temp = matriks[maxRow];
            matriks[maxRow] = matriks[i];
            matriks[i] = temp;

            for (int k = i + 1; k < n; k++) {
                double c = -matriks[k][i] / matriks[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        matriks[k][j] = 0;
                    } else {
                        matriks[k][j] += c * matriks[i][j];
                    }
                }
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = matriks[i][n] / matriks[i][i];
            for (int k = i - 1; k >= 0; k--) {
                matriks[k][n] -= matriks[k][i] * solution[i];
            }
        }
        return new double[][] { solution };
    }
}
