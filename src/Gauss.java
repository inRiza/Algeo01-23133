public class Gauss {
    public static double[][] gaussElimination(double[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            double maxEl = Math.abs(matrix[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > maxEl) {
                    maxEl = Math.abs(matrix[k][i]);
                    maxRow = k;
                }
            }

            double[] temp = matrix[maxRow];
            matrix[maxRow] = matrix[i];
            matrix[i] = temp;

            for (int k = i + 1; k < n; k++) {
                double c = -matrix[k][i] / matrix[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        matrix[k][j] = 0;
                    } else {
                        matrix[k][j] += c * matrix[i][j];
                    }
                }
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = matrix[i][n] / matrix[i][i];
            for (int k = i - 1; k >= 0; k--) {
                matrix[k][n] -= matrix[k][i] * solution[i];
            }
        }
        return new double[][]{solution};
    }
}
