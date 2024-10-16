import java.util.*;

public class sPersLinear {
    static Scanner scan = new Scanner(System.in); 

    public static double[][] matrixAugmented(int n) {
        double[][] matrixAug = new double[n][n+1];
        // karena kalo matrix 3 x 3 berartikan barisnya 3 dan kolomnya 4 (3 untuk matrix dan 1 untuk solusi)
        // gitu si 

        int i, j ; 
        for (i = 0 ; i < n ; i++) { 
            for (j = 0 ; j <= n ; j++) { 
                matrixAug[i][j] = scan.nextDouble();
            }
        }
        return matrixAug; 
    }

}