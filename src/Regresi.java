import java.util.Scanner;

public class Regresi {
    public static void regresiLinearBerganda() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris matriks X: ");
        int rows = scan.nextInt();
        System.out.print("Masukkan jumlah kolom matriks X: ");
        int cols = scan.nextInt();

        Matriks X = new Matriks(rows, cols);
        X.bacaMatriks();

        double[] Y = new double[rows];

        for (int i = 0; i < rows; i++) {
            Y[i] = X.Mat[i][cols - 1];
        }

        Matriks Xbaru = new Matriks(rows, cols - 1);
        // Menghapus kolom terakhir
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                Xbaru.Mat[i][j] = X.Mat[i][j];
            }
        }

        regresiLinear(X, Y);
    }

    public static void regresiKuadratikBerganda() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Masukkan jumlah baris matriks X: ");
        int rows = scan.nextInt();
        System.out.print("Masukkan jumlah kolom matriks X: ");
        int cols = scan.nextInt();

        Matriks X = new Matriks(rows, cols);
        X.bacaMatriks();

        double[] Y = new double[rows];

        for (int i = 0; i < rows; i++) {
            Y[i] = X.Mat[i][cols - 1];
        }

        Matriks Xbaru = new Matriks(rows, cols - 1);
        // Menghapus kolom terakhir
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                Xbaru.Mat[i][j] = X.Mat[i][j];
            }
        }

        int newCols = Xbaru.n_kolom + Xbaru.n_kolom + (Xbaru.n_kolom * (Xbaru.n_kolom - 1)) / 2;
        Matriks X_quad = new Matriks(Xbaru.m_baris, newCols);
        for (int i = 0; i < Xbaru.m_baris; i++) {
            int index = 0;
            for (int j = 0; j < Xbaru.n_kolom; j++) {
                X_quad.Mat[i][index++] = Xbaru.Mat[i][j]; // linear term
            }
            for (int j = 0; j < Xbaru.n_kolom; j++) {
                X_quad.Mat[i][index++] = Xbaru.Mat[i][j] * Xbaru.Mat[i][j]; // quadratic term
            }
            for (int j = 0; j < Xbaru.n_kolom; j++) {
                for (int k = j + 1; k < Xbaru.n_kolom; k++) {
                    X_quad.Mat[i][index++] = Xbaru.Mat[i][j] * Xbaru.Mat[i][k]; // interaction term
                }
            }
        }

        regresiKuadratik(X_quad, Y);
    }

    public static void regresiLinear(Matriks X, double[] Y) {
        int n = X.m_baris;
        int m = X.n_kolom;
        double[][] Xt = new double[m][n];
        double[][] XtX = new double[m][m];
        double[] XtY = new double[m];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Xt[j][i] = X.Mat[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                XtX[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    XtX[i][j] += Xt[i][k] * X.Mat[k][j];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            XtY[i] = 0;
            for (int j = 0; j < n; j++) {
                XtY[i] += Xt[i][j] * Y[j];
            }
        }

        for (int i = 0; i < m; i++) {
            if (XtX[i][i] == 0) {
                // Swap with a row below that has a non-zero element in the same column
                for (int j = i + 1; j < m; j++) {
                    if (XtX[j][i] != 0) {
                        double[] tempRow = XtX[i];
                        XtX[i] = XtX[j];
                        XtX[j] = tempRow;
                        double tempVal = XtY[i];
                        XtY[i] = XtY[j];
                        XtY[j] = tempVal;
                        break;
                    }
                }
            }
            for (int j = i + 1; j < m; j++) {
                if (XtX[i][i] != 0) {
                    double ratio = XtX[j][i] / XtX[i][i];
                    for (int k = 0; k < m; k++) {
                        XtX[j][k] -= ratio * XtX[i][k];
                    }
                    XtY[j] -= ratio * XtY[i];
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            B[i] = XtY[i];
            for (int j = i + 1; j < m; j++) {
                B[i] -= XtX[i][j] * B[j];
            }
            if (XtX[i][i] != 0) {
                B[i] /= XtX[i][i];
            } else {
                B[i] = 0; // Set to 0 if division by zero occurs
            }
        }

        System.out.print("Persamaannya: Y = ");
        for (int i = 0; i < m; i++) {
            if (i > 0) {
                System.out.print(" + ");
            }
            System.out.printf("%.2f * X%d", B[i], i);
        }
        System.out.println("");
    }

    public static void regresiKuadratik(Matriks X, double[] Y) {
        int n = X.m_baris;
        int m = X.n_kolom;
        double[][] Xt = new double[m][n];
        double[][] XtX = new double[m][m];
        double[] XtY = new double[m];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Xt[j][i] = X.Mat[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                XtX[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    XtX[i][j] += Xt[i][k] * X.Mat[k][j];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            XtY[i] = 0;
            for (int j = 0; j < n; j++) {
                XtY[i] += Xt[i][j] * Y[j];
            }
        }

        for (int i = 0; i < m; i++) {
            if (XtX[i][i] == 0) {
                // Swap with a row below that has a non-zero element in the same column
                for (int j = i + 1; j < m; j++) {
                    if (XtX[j][i] != 0) {
                        double[] tempRow = XtX[i];
                        XtX[i] = XtX[j];
                        XtX[j] = tempRow;
                        double tempVal = XtY[i];
                        XtY[i] = XtY[j];
                        XtY[j] = tempVal;
                        break;
                    }
                }
            }
            for (int j = i + 1; j < m; j++) {
                if (XtX[i][i] != 0) {
                    double ratio = XtX[j][i] / XtX[i][i];
                    for (int k = 0; k < m; k++) {
                        XtX[j][k] -= ratio * XtX[i][k];
                    }
                    XtY[j] -= ratio * XtY[i];
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            B[i] = XtY[i];
            for (int j = i + 1; j < m; j++) {
                B[i] -= XtX[i][j] * B[j];
            }
            if (XtX[i][i] != 0) {
                B[i] /= XtX[i][i];
            } else {
                B[i] = 0; // Set to 0 if division by zero occurs
            }
        }

        System.out.print("Persamaannya: Y = ");
        boolean firstTerm = true;
        for (int i = 0; i < m; i++) {
            if (B[i] != 0) {
                if (!firstTerm) {
                    System.out.print(" + ");
                }
                System.out.printf("%.2f * X%d", B[i], i);
                firstTerm = false;
            }
        }
        System.out.println("");
    }
}
