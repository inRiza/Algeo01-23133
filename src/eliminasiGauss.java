public class eliminasiGauss {
    public static void tukarBaris (double[][] Matrix, int N) { 
        int i, j, k;
        for ( i = 0; i < N; i++) {
            for ( k = i + 1; k < N; k++) {
                if (Matrix[k][i] > Matrix[i][i]) {

                    for ( j = 0; j <= N; j++) {
                        double temp = Matrix[i][j];
                        Matrix[i][j] = Matrix[k][j];
                        Matrix[k][j] = temp;
                    }
                }
            }
        }
    }

    public static void eliminasiMethod (double[][] Matrix, int N) { 
        int i, j, k; 
        for ( i = 0; i < N - 1; i++) {
            for ( k = i + 1; k < N; k++) {
                double temp = Matrix[k][i] / Matrix[i][i];
                for ( j = 0; j <= N; j++) {
                    Matrix[k][j] = Matrix[k][j] - temp * Matrix[i][j];
                }
            }
        }   
    }

    public static double[] subtitusiMundur (double[][] Matrix, int N){ 
        double[] solusi = new double[N];
        for (int i = N - 1 ; i >= 0 ; i--) {
            solusi[i] = Matrix[i][N];
            for (int j = 1 + 1 ; j < N ; j++) {
                solusi[i] -= Matrix[i][j] * solusi[j];
            }
            solusi[i] = solusi[i] / Matrix[i][i];
        }
        return solusi; 
    }

    public static void printSolution (double[] solusi) {
        for (int i = 0 ; i < solusi.length ; i++) {
            System.out.println("X" + (i+1) + " = " + solusi[i]);
        }
    }
}