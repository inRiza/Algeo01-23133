import java.util.*;
import java.io.*;

public class Matriks {

    Scanner scan = new Scanner(System.in); // Untuk Input
    public int minBaris = 0;
    public int minKolom = 0;
    public int maxm_baris = 100;
    public int maxn_kolom = 100;
    public int m_baris; // m_baris
    public int n_kolom; // n_kolom
    public double[][] Mat; // isi array, double biar threadsafe
    // Manggil isi matriks-nya matriks.Mat[m_baris][n_kolom]
    // Indeks m_baris & n_kolom mulai dari 0

    public Matriks(int baris, int kolom) {
        this.m_baris = baris;
        this.n_kolom = kolom;
        this.Mat = new double[baris][kolom];
    }

    public Matriks(double[][] Mat) {
        // Konstruktor dari tabel
        this.m_baris = Mat.length;
        this.n_kolom = Mat[0].length;
        this.Mat = new double[Mat.length][Mat[0].length];
        for (int i = 0; i < Mat.length; i++)
            for (int j = 0; j < Mat[0].length; j++)
                this.Mat[i][j] = Mat[i][j];
    }

    public Matriks(String file_name) throws FileNotFoundException {// Membaca Matriks dari sebuah file
        ArrayList<ArrayList<Double>> Mat = new ArrayList<ArrayList<Double>>();
        File file = new File(file_name);
        Scanner input = new Scanner(file);
        int m_baris = -1;
        int n_kolom = -1;
        while (input.hasNextLine()) {
            m_baris++;
            Mat.add(new ArrayList<Double>());
            String baris = input.nextLine();
            Scanner scanm_baris = new Scanner(baris);
            while (scanm_baris.hasNextDouble()) {
                Double element = scanm_baris.nextDouble();
                Mat.get(m_baris).add(element);
            }
        }

        if (m_baris == 0) {
            System.out.println("Tidak dapat membaca file");
        } else {
            n_kolom = Mat.get(0).size();
            this.Mat = new double[Mat.size()][Mat.get(0).size()];
            for (int i = minBaris; i <= m_baris; i++) {
                for (int j = minKolom; j < n_kolom; j++) {
                    this.Mat[i][j] = Mat.get(i).get(j);
                }
            }
            this.m_baris = m_baris + 1;
            this.n_kolom = n_kolom;
        }
    }

    public int GetFirstIdxBrs(Matriks M) {
        return minBaris;
    }

    public int GetFirstIdxKol(Matriks M) {
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

    public static Matriks Identitas(int N) {
        Matriks I = new Matriks(N, N);
        for (int i = 0; i < N; i++)
            I.Mat[i][i] = 1;
        return I;
    }

    public static Matriks Hilbert(int N) {
        Matriks H = new Matriks(N, N + 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                H.Mat[i][j] = 1.0 / (i + j + 1);
            }
        }
        return H;
    }

    public void bacaMatriks() {
        System.out.println("Silahkan masukan Matriks : ");
        for (int i = 0; i < this.m_baris; i++) {
            for (int j = 0; j < this.n_kolom; j++) {
                this.Mat[i][j] = scan.nextDouble();
            }
        }
        System.out.print("\n");
    }

    // Menulis Matriks
    public void cetakMatriks() {
        for (int i = 0; i < this.m_baris; i++) {
            for (int j = 0; j < this.n_kolom; j++) {
                this.Mat[i][j] += 0; // Agar tidak ada (-0)
                System.out.printf("%.2f ", this.Mat[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static Matriks kaliBilangan(Matriks M, double k) {
        Matriks out = new Matriks(M.m_baris, M.n_kolom);
        for (int i = 0; i < M.m_baris; i++) {
            for (int j = 0; j < M.n_kolom; j++) {
                out.Mat[i][j] = M.Mat[i][j] * k;
            }
        }
        return out;
    }

    public void kaliBilangan(double k) {
        this.Mat = kaliBilangan(this, k).Mat;
    }

    public static Matriks kaliMatriks(Matriks M, Matriks N) {
        Matriks out = new Matriks(M.m_baris, N.n_kolom);

        for (int i = 0; i < out.m_baris; i++) {
            for (int j = 0; j < out.n_kolom; j++) {
                out.Mat[i][j] = 0;
                for (int k = 0; k < M.n_kolom; k++) {
                    out.Mat[i][j] += M.Mat[i][k] * N.Mat[k][j];
                }
            }
        }

        return out;
    }

    public void Swap(int Brs1, int Brs2) {
        double[] temp = Mat[Brs1];
        Mat[Brs1] = Mat[Brs2];
        Mat[Brs2] = temp;
    }

    public void kalikanBaris(int m_baris, double x) {
        for (int i = 0; i < n_kolom; i++) {
            Mat[m_baris][i] *= x;
        }
    }

    public void Plusm_baris(int m_baris1, int m_baris2, double k) {
        for (int i = 0; i < n_kolom; i++) {
            Mat[m_baris1][i] += Mat[m_baris2][i] * k;
        }
    }

    public void Plusm_baris(int m_baris1, int m_baris2) {
        // m_baris ke-a ditambah dengan bilangan di baris ke-b
        Plusm_baris(m_baris1, m_baris2, 1);
    }

    public void kurangBaris(int a, int b, double k) {
        // m_baris ke-a dikurangi dengan bilangan di baris ke-b * k
        Plusm_baris(a, b, -k);
    }

    public void kurangBaris(int a, int b) {
        // m_baris ke-a dikurangi dengan bilangan di baris ke-b
        kurangBaris(a, b, 1);
    }

    /* ********** SIFAT MATRIKS ********** */
    public static boolean IsIdentitas(Matriks M) {
        boolean out = true;
        for (int i = 0; i < M.m_baris; i++) {
            for (int j = 0; j < M.n_kolom; j++) {
                if (!(((i == j) && M.Mat[i][j] == 1) || ((i != j) && M.Mat[i][j] == 0))) {
                    out = false;
                }
            }
        }
        return out;
    }

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
        /* Prekondisi: M bujur sangkar */
        /* Menghitung nilai determinan M */
        double det;

        if ((M.m_baris == 1) && (M.n_kolom == 1)) // Basis 1x1
            det = M.Mat[0][0];
        else { // Rekurens nxn
            det = 0;
            for (int i = GetFirstIdxBrs(M); i <= GetLastIdxBaris(M); i++)
                det += M.Mat[i][GetFirstIdxKol(M)] * Kofaktor(M, i, GetFirstIdxKol(M));
        }

        return det;
    }

    private double Kofaktor(Matriks M, int i, int j) {
        return determinanKofaktor(Minor(M, i, j)) * (((i + j) % 2 == 0) ? 1 : -1);
    }

    /* ********** MANIPULASI MATRIKS ********** */
    private Matriks Minor(Matriks M, int i, int j) {
        // Minor M(i,j) dari matriks M
        Matriks Minor = new Matriks(M.m_baris - 1, M.n_kolom - 1);
        int iMi, jMi, iM, jM;
        iMi = GetFirstIdxBrs(Minor);
        for (iM = GetFirstIdxBrs(M); iM <= GetLastIdxBaris(M); iM++)
            if (iM != i) {
                jMi = GetFirstIdxKol(Minor);
                for (jM = GetFirstIdxKol(M); jM <= GetLastIdxKolom(M); jM++)
                    if (jM != j) {
                        Minor.Mat[iMi][jMi] = M.Mat[iM][jM];
                        jMi++;
                    }
                iMi++;
            }
        return Minor;
    }

    public void transposeMatriks() {
        Matriks M1 = new Matriks(this.n_kolom, this.m_baris);

        for (int i = GetFirstIdxBrs(M1); i <= GetLastIdxBaris(M1); i++) {
            for (int j = GetFirstIdxKol(M1); j <= GetLastIdxKolom(M1); j++) {
                M1.Mat[i][j] = this.Mat[j][i];
            }
        }

        this.m_baris = M1.m_baris;
        this.n_kolom = M1.n_kolom;
        this.Mat = M1.Mat;
    }

    public void matriksKofaktor() {
        Matriks M = new Matriks(this.n_kolom, this.m_baris);
        for (int i = GetFirstIdxBrs(this); i <= GetLastIdxBaris(this); i++)
            for (int j = GetFirstIdxKol(this); j <= GetLastIdxKolom(this); j++) {
                M.Mat[i][j] = Kofaktor(this, i, j);
            }
        this.Mat = M.Mat;
    }

    public void adjoin() {
        if (this.jumlahElmt(this) != 1) {
            this.matriksKofaktor();
            this.transposeMatriks();
        } else {
            this.Mat[0][0] = 1;
        }
    }

    public static boolean inversGaussJordan(Matriks in, Matriks out) {
        Matriks M = Copy(in);

        M = gabungMatriksHorizontal(M, Identitas(M.m_baris));
        M = eliminasiGaussJordan(M);

        Matriks N = new Matriks(in.m_baris, in.n_kolom);
        for (int i = 0; i < N.m_baris; i++) {
            for (int j = 0; j < N.n_kolom; j++) {
                N.Mat[i][j] = M.Mat[i][j];
            }
        }

        if (IsIdentitas(N)) {
            Copy(in, out);

            for (int i = 0; i < out.m_baris; i++) {
                for (int j = 0; j < out.n_kolom; j++) {
                    out.Mat[i][j] = M.Mat[i][j + out.n_kolom];
                }
            }
            return true;
        } else {
            Copy(in, out);
            return false;
        }
    }

    public boolean inversadjoin(Matriks in, Matriks out) {
        Copy(in, out);
        double det = determinanKofaktor(out);
        if (det != 0) {
            out.adjoin();
            out.kaliBilangan(1 / det);
            return true;
        } else {
            return false;
        }
    }

    public static Matriks eliminasiGauss(Matriks in) {

        // Inisialisasi
        Matriks M = new Matriks(1, 1);
        M = Copy(in);

        // Proses mengurutkan baris
        int[] zeroCount = new int[M.m_baris];
        for (int i = 0; i < M.m_baris; i++) { // Kalkulasi jumlah 0
            zeroCount[i] = 0;
            int j = 0;
            while (j < M.n_kolom && M.Mat[i][j] == 0) {
                zeroCount[i]++;
                j++;
            }
        }
        for (int i = 0; i < M.m_baris; i++) { // Algoritma Pengurutan
            for (int j = 0; j < M.m_baris - 1; j++) {
                if (zeroCount[j] > zeroCount[j + 1]) {
                    int temp;
                    M.Swap(j, j + 1);
                    temp = zeroCount[j];
                    zeroCount[j] = zeroCount[j + 1];
                    zeroCount[j + 1] = temp;
                }
            }
        }

        // Proses mereduksi baris
        int indent = 0;

        for (int i = 0; i < M.m_baris; i++) {
            // Mencari sel bernilai
            while (i + indent < M.n_kolom && M.Mat[i][i + indent] == 0) {
                indent++;
            }

            if (i + indent < M.n_kolom) {
                // Ubah angka depan jadi 1
                M.kalikanBaris(i, 1 / M.Mat[i][i + indent]);

                // Pengurangan baris dibawahnya
                for (int j = i + 1; j < M.m_baris; j++) {
                    if (M.Mat[j][i + indent] != 0) {
                        M.kalikanBaris(j, 1 / M.Mat[j][i + indent]);
                        M.kurangBaris(j, i);
                    }
                }
            }
        }
        M.Approximate();

        return M;
    }

    public static Matriks eliminasiGaussJordan(Matriks in) {

        // Proses
        Matriks M = eliminasiGauss(in);
        int indent = 0;

        for (int i = 0; i < M.m_baris; i++) {
            // Pencarian sel tidak nol
            while (i + indent < M.n_kolom && M.Mat[i][i + indent] == 0) {
                indent++;
            }

            if (i + indent < M.n_kolom) {

                // Pengurangan baris diatasnya
                for (int j = i - 1; j >= 0; j--) {
                    if (M.Mat[j][i + indent] != 0) {
                        M.kurangBaris(j, i, M.Mat[j][i + indent]);
                    }
                }
            }
        }

        M.Approximate();
        return M;
    }

    public static void Copy(Matriks dari, Matriks ke) {
        ke.m_baris = dari.m_baris;
        ke.n_kolom = dari.n_kolom;
        ke.Mat = new double[dari.m_baris][dari.n_kolom];

        for (int i = 0; i < ke.m_baris; i++) {
            for (int j = 0; j < ke.n_kolom; j++) {
                ke.Mat[i][j] = dari.Mat[i][j];
            }
        }
    }

    // Varian fungsi dari Copy Matriks diatas
    public static Matriks Copy(Matriks dari) {
        Matriks ke = new Matriks(1, 1);
        Copy(dari, ke);
        return ke;
    }

    // Menyambungkan matriks M dan N
    public static Matriks gabungMatriksHorizontal(Matriks M, Matriks N) {
        Matriks out = new Matriks(M.m_baris, M.n_kolom + N.n_kolom);

        for (int i = 0; i < out.m_baris; i++) {
            for (int j = 0; j < out.n_kolom; j++) {
                if (j < M.n_kolom) {
                    out.Mat[i][j] = M.Mat[i][j];
                } else {
                    out.Mat[i][j] = N.Mat[i][j - M.n_kolom];
                }
            }
        }

        return out;
    }

    // Menyambungkan matriks M dan N
    public static Matriks gabungMatriksVertikal(Matriks M, Matriks N) {
        Matriks out = new Matriks(M.m_baris + N.m_baris, M.n_kolom);

        for (int i = 0; i < out.m_baris; i++) {
            for (int j = 0; j < out.n_kolom; j++) {
                if (i < M.m_baris) {
                    out.Mat[i][j] = M.Mat[i][j];
                } else {
                    out.Mat[i][j] = N.Mat[i - M.m_baris][j];
                }
            }
        }

        return out;
    }

    private static double Approximate(double x) {
        return (Math.round(x));
    }

    private void Approximate() {
        for (int i = 0; i < m_baris; i++) {
            for (int j = 0; j < n_kolom; j++) {
                Mat[i][j] = Approximate(Mat[i][j]);
            }
        }
    }

    public static void TulisFile(String file, Matriks M) {
        try {
            File F = new File(file);
            if (!(F.exists())) {
                F.createNewFile();
            }

            FileWriter FF = new FileWriter(file);
            PrintWriter print = new PrintWriter(FF);

            for (int i = 0; i < M.m_baris; i++) {
                for (int j = 0; j < M.n_kolom; j++) {
                    M.Mat[i][j] += 0;
                    print.printf("%.2f ", M.Mat[i][j]);
                }
                print.printf("\n");
            }

            print.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void h() {
        this.Mat[minBaris][GetLastIdxKolom(this)] = 1;
    }
}
