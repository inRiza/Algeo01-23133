import java.util.*;
import java.io.*;

public class Matriks {

    Scanner scan = new Scanner(System.in);
    public int minBaris = 0;
    public int minKolom = 0;
    public int maxBaris = 100;
    public int maxKolom = 100;
    public int m_baris; // Baris
    public int n_kolom; // Kolom
    public double[][] Mat; // Matriks
    // Manggil isi matriks dengan matriks.Mat[m_baris][n_kolom]
    // Indeks Baris dan Kolom mulai dari 0

    /* KONSTRUKTOR */
    public Matriks(int baris, int kolom) {
        this.m_baris = baris;
        this.n_kolom = kolom;
        this.Mat = new double[m_baris][n_kolom];
    }

    public Matriks(double[][] Mat) {
        this.m_baris = Mat.length;
        this.n_kolom = Mat[0].length;
        this.Mat = new double[Mat.length][Mat[0].length];
        int i, j;

        for (i = 0; i < Mat.length; i++) {
            for (j = 0; j < Mat[0].length; j++) {
                this.Mat[i][j] = Mat[i][j];
            }
        }
    }

    // Baca matriks dari file
    public Matriks(String filename) throws FileNotFoundException {
        ArrayList<ArrayList<Double>> Mat = new ArrayList<ArrayList<Double>>();
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        int m_baris = -1;
        int n_kolom = -1;

        while (inputFile.hasNextLine()) {
            m_baris++;
            Mat.add(new ArrayList<Double>());
            String garisbaris = inputFile.nextLine();

            Scanner scanBaris = new Scanner(garisbaris);

            while (scanBaris.hasNextDouble()) {
                Double elmt = scanBaris.nextDouble();
                Mat.get(m_baris).add(elmt);
            }
        }

        if (m_baris == 0) {
            System.out.println("File tidak dapat dibaca");
        } else {
            n_kolom = Mat.get(0).size();
            this.Mat = new double[Mat.size()][Mat.get(0).size()];

            int i, j;
            for (i = minBaris; i <= m_baris; i++) {
                for (j = minKolom; j < n_kolom; j++) {
                    this.Mat[i][j] = Mat.get(i).get(j);
                }
            }
            this.m_baris = m_baris + 1;
            this.n_kolom = n_kolom;
        }
    }

    /* INPUT/OUTPUT MATRIKS */
    // Baca matriks
    public void bacaMatriks() {
        System.out.println("Masukkan matriks: ");

        int i, j;
        for (i = 0; i < this.m_baris; i++) {
            for (j = 0; j < this.n_kolom; j++) {
                System.out.println("Masukkan elemen [" + i + "] [" + j + "]: ");
                this.Mat[i][j] = scan.nextDouble();
            }
        }
        System.out.println();
    }

    public void cetakMatriks() {
        int i, j;
        for (i = 0; i < this.m_baris; i++) {
            for (j = 0; j < this.n_kolom; j++) {
                if (this.Mat[i][j] == (int) this.Mat[i][j]) {
                    System.out.print((int) this.Mat[i][j]);
                } else {
                    System.out.print(this.Mat[i][j]);
                }
                if (j < this.n_kolom - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public int getBaris() {
        return this.m_baris;
    }

    public int getKolom() {
        return this.n_kolom;
    }

    public static void Copy(Matriks asal, Matriks salinan) {
        asal.m_baris = salinan.m_baris;
        asal.n_kolom = salinan.n_kolom;
        asal.Mat = new double[salinan.m_baris][salinan.n_kolom];

        int i, j;
        for (i = 0; i < salinan.m_baris; i++) {
            for (j = 0; j < salinan.n_kolom; j++) {
                salinan.Mat[i][j] = asal.Mat[i][j];
            }
        }
    }

    public static Matriks Copy(Matriks asal) {
        Matriks salinan = new Matriks(1, 1);
        Copy(asal, salinan);
        return salinan;
    }

    public void Swap(int baris1, int baris2) {
        double[] temp = Mat[baris1];
        Mat[baris1] = Mat[baris2];
        Mat[baris2] = temp;
    }

    public void tambahBaris(int baris1, int baris2, double k) {
        int i, j;
        for (i = 0; i < n_kolom; i++) {
            Mat[baris1][i] += Mat[baris2][i] * k;
        }
    }

    public void tambahBaris(int baris1, int baris2) {
        tambahBaris(baris1, baris2, 1);
    }

    public void kurangBaris(int a, int b, double k) {
        tambahBaris(a, b, -k);
    }

    public void kurangBaris(int a, int b) {
        kurangBaris(a, b, 1);
    }

    public int GetFirstIdxBaris(Matriks M) {
        return minBaris;
    }

    public double getELMT(Matriks M, int i, int j) {
        if (i >= 0 && i < M.m_baris && j >= 0 && j < M.n_kolom) {
            return M.Mat[i][j];
        } else {
            throw new IndexOutOfBoundsException("Indeks berada di luar batas matriks");
        }
    }    

    public int GetFirstIdxKolom(Matriks M) {
        return minKolom;
    }

    public int GetLastIdxBaris(Matriks M) {
        return M.m_baris - 1;
    }

    public int GetLastIdxKolom(Matriks M) {
        return M.n_kolom - 1;
    }

    public int jumlahElmt(Matriks M) {
        return (M.m_baris * M.n_kolom);
    }

    public double[][] multiplyMatriks(Matriks m1, Matriks m2){
        int baris = m1.m_baris;
        int kolom = m2.n_kolom;
        double[][] hasil = new double[baris][kolom];

        int i,j,k;
        for(i=0;i<baris;i++){
            for(j=0;j<kolom;j++){
                hasil[i][j] = 0;
                for(k=0;k<baris;k++){
                    hasil[i][j] += m1.Mat[i][k]* m2.Mat[k][j];
                }
            }
        }
        return hasil;
    }

    public double[][] multiplyByConst(Matriks m, double k){
        int baris =m.m_baris;
        int kolom =m.n_kolom;
        double[][] hasil = new double[baris][kolom];

        int i,j;

        for(i=0;i<baris;i++){
            for(j=0;j<kolom;j++){
                hasil[i][j] = m.Mat[i][j]*k; 
            }
        }
        return hasil;
    }

    private double Kofaktor(Matriks M, int i, int j) {
        return determinanKofaktor(minorKofaktor(M, i, j)) * (((i + j) % 2 == 0) ? 1 : -1);
    }

    private Matriks minorKofaktor(Matriks M, int i, int j) {
        Matriks minor = new Matriks(M.m_baris - 1, M.n_kolom - 1);
        int iMati, jMati, iMat, jMat;
        iMati = GetFirstIdxBaris(minor);
        for (iMat = GetFirstIdxKolom(M); iMat <= GetLastIdxBaris(minor); iMat++) {
            if (iMat != 1) {
                jMati = GetFirstIdxKolom(minor);
                for (jMat = GetFirstIdxKolom(M); jMat <= GetLastIdxKolom(M); jMat++) {
                    if (jMat != j) {
                        minor.Mat[iMati][jMati] = M.Mat[iMat][jMat];
                        jMati++;
                    }
                }
                iMati++;
            }
        }
        return minor;

    }

    /* FUNGSI SKALAR */
    // Determinan
    public double determinanOBE(Matriks M) {
        Matriks N = Copy(M);

        // Proses mengurutkan baris
        int[] zeroCount = new int[N.m_baris];
        int swapCount = 0;
        for (int i = 0; i < N.m_baris; i++) { // Kalkulasi jumlah 0
            zeroCount[i] = 0;
            int j = 0;
            while (j < N.n_kolom && N.Mat[i][j] == 0) {
                zeroCount[i]++;
                j++;
            }
        }
        for (int i = 0; i < N.m_baris; i++) { // Algoritma Pengurutan
            for (int j = 0; j < N.m_baris - 1; j++) {
                if (zeroCount[j] > zeroCount[j + 1]) {
                    int temp;
                    N.Swap(j, j + 1);
                    swapCount++;
                    temp = zeroCount[j];
                    zeroCount[j] = zeroCount[j + 1];
                    zeroCount[j + 1] = temp;
                }
            }
        }
        // Proses mereduksi baris
        int indent = 0;

        for (int i = 0; i < N.m_baris; i++) {
            // Mencari sel bernilai
            while (i + indent < N.n_kolom && N.Mat[i][i + indent] == 0) {
                indent++;
            }

            if (i + indent < N.n_kolom) {
                // Pengurangan baris dibawahnya
                for (int j = i + 1; j < N.m_baris; j++) {
                    N.kurangBaris(j, i, N.Mat[j][i + indent] / N.Mat[i][i + indent]);

                }
            }
        }
        // Proses menghitung jumlah diagonal
        double det = N.Mat[0][0];
        for (int i = 1; i < N.m_baris; i++) {
            det *= N.Mat[i][i];
        }
        det *= ((swapCount & 2) == 0) ? 1 : -1;
        return det;
    }

    public double determinanKofaktor(Matriks M) {
        double det;
        int i;

        if ((M.m_baris == 1) && (M.n_kolom == 1)) {
            det = M.Mat[0][0];
        } else {
            det = 0;
            for (i = GetFirstIdxBaris(M); i <= GetLastIdxBaris(M); i++) {
                det += M.Mat[i][GetFirstIdxKolom(M)] * Kofaktor(M, i, GetFirstIdxKolom(M));
            }
        }

        return det;
    }

    public void transposeMatriks() {
        Matriks Mbaru = new Matriks(this.m_baris, this.n_kolom);

        int i, j;
        for (i = GetFirstIdxBaris(Mbaru); i <= GetLastIdxBaris(Mbaru); i++) {
            for (j = GetFirstIdxKolom(Mbaru); j <= GetLastIdxKolom(Mbaru); j++) {
                Mbaru.Mat[i][j] = this.Mat[j][i];
            }
        }

        this.m_baris = Mbaru.m_baris;
        this.n_kolom = Mbaru.n_kolom;
        this.Mat = Mbaru.Mat;
    }

    public void testCase() {
        System.out.println("testing baru woi");
    }

}
