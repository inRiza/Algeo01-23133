public class Invers {
    public double[][] transposeMatriks() {
        double[][] hasil = new double [this.n_kolom][this.m_baris];

        int i, j;
        for (i = 0; i < this.m_baris; i++) {
            for (j = 0; j < this.n_kolom; j++) {
                hasil[j][i] = this.matriks[i][j];
            }
        }
        return hasil;
    }

    public double[][] adjointMatriks() {
        int i,j;
        double[][] adjoint = new double[this.m_baris][this.n_kolom];
        for (i = 0; i < this.m_baris; i++) {
            for (j = 0; j < this.n_kolom; j++) {
                adjoint[i][j] = transposeMatriks(kofaktorMatriks(this.matriks[i][j]));
            }
        }
        return adjoint;
    }

    public double[][] inverseWithAdjoin() {
        int i,j;
        double[][] inverseWithAdjoin = new double[this.m_baris][this.n_kolom];
        
        for (i = 0; i < this.m_baris; i++){
            for (j = 0; j < this.n_kolom; j++){
            inverseWithAdjoin[i][j] = (1 / determiananMatiks()) * adjointMatriks();
            }
        }
    }
    
}
