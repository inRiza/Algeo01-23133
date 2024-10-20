public class Invers {
    // OBE

    //adjoin
    public double[][] inversWithAdjoint(Matriks M, int i, int j ){
        double det = determinanKofaktor(M.Mat[i][j]);
        double [][] kofaktor = kofaktorMatriks(matriks);
        double [][] adjoint = transposeMatriks(kofaktor);

        double[][] hasil = multiplyByConst(adjoint, 1/det);
        return hasil;
    }
    
}
