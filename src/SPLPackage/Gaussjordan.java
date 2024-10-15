package SPLPackage;

public class Gaussjordan {
    public static void gaussJordanElimination(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            double diagElement = matrix[i][i];
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
}
