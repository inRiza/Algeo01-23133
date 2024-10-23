import java.util.*;

public class SPL {
    static Scanner scanner = new Scanner(System.in);

    public static void metodeGauss(String sfx) {
        System.out.print("Masukan m : ");
        int m = scanner.nextInt();
        System.out.print("Masukan n : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);

        M.bacaMatriks();

        metodeGauss(M, sfx);

    }

    public static void metodeGauss(Matriks M, String sfx) {

        boolean tidakBernilai = false;

        int n = M.GetLastIdxKolom(M);
        M = Matriks.eliminasiGauss(M);

        StrukturSPL[] hasil = new StrukturSPL[n];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new StrukturSPL();
        }

        for (int i = M.GetLastIdxBaris(M); i >= M.GetFirstIdxBrs(M); i--) {
            int idxPertama = -1;
            StrukturSPL value = new StrukturSPL();
            for (int j = M.GetFirstIdxKol(M); j <= M.GetLastIdxKolom(M); j++) {
                if (M.Mat[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) { // Belum ada nilai
                            if (idxPertama == -1) { // Variabel baru pertama
                                idxPertama = j;
                            } else { // Buat variabel parametrik
                                value.SetVariable(hasil[j].GenerateNewVar(), -M.Mat[i][j]);
                            }
                        } else { // Sudah ada nilai
                            for (int k = 0; k < Nilai.varList.length(); k++) {
                                value.SetVariable(k,
                                        value.GetVariable(k).val - (hasil[j].GetVariable(k).val * M.Mat[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Mat[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Mat[i][n] != 0) {
                    tidakBernilai = true;
                }
            } else {
                hasil[idxPertama] = value;
            }
        }

        /*
         * ************************************** OUTPUT
         * **************************************
         */

        // Cek bernilai atau tidak
        if (tidakBernilai) {
            System.out.println("SPL tersebut tidak memiliki solusi");
        } else {
            for (int i = 0; i < hasil.length; i++) {
                System.out.println(sfx + (i + 1) + " = " + hasil[i].ToString());
            }
        }

        StrukturSPL.ResetVars();

    }

    public static void metodeGaussJordan(String sfx) {
        System.out.print("Masukan m : ");
        int m = scanner.nextInt();
        System.out.print("Masukan n : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);

        M.bacaMatriks();

        metodeGaussJordan(M, sfx);
    }

    public static void metodeGaussJordan(Matriks M, String sfx) {

        boolean tidakBernilai = false;

        int m = M.m_baris;
        int n = M.n_kolom - 1;

        M = Matriks.eliminasiGaussJordan(M);

        StrukturSPL[] hasil = new StrukturSPL[n];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new StrukturSPL();
        }

        for (int i = M.GetLastIdxBaris(M); i >= M.GetFirstIdxBrs(M); i--) {
            int idxPertama = -1;
            StrukturSPL value = new StrukturSPL();
            for (int j = M.GetFirstIdxKol(M); j <= M.GetLastIdxKolom(M); j++) {
                if (M.Mat[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) { // Belum ada nilai
                            if (idxPertama == -1) { // Variabel baru pertama
                                idxPertama = j;
                            } else { // Buat variabel parametrik
                                value.SetVariable(hasil[j].GenerateNewVar(), -M.Mat[i][j]);
                            }
                        } else { // Sudah ada nilai
                            for (int k = 0; k < Nilai.varList.length(); k++) {
                                value.SetVariable(k,
                                        value.GetVariable(k).val - (hasil[j].GetVariable(k).val * M.Mat[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Mat[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Mat[i][n] != 0) {
                    tidakBernilai = true;
                }
            } else {
                hasil[idxPertama] = value;
            }
        }

        /*
         * ************************************** OUTPUT
         * **************************************
         */

        // Cek bernilai atau tidak
        if (tidakBernilai) {
            System.out.println("SPL tersebut tidak memiliki solusi");
        } else {
            for (int i = 0; i < hasil.length; i++) {
                System.out.println(sfx + (i + 1) + " = " + hasil[i].ToString());
            }
        }

        StrukturSPL.ResetVars();

    }

    public static void SPLInvers(String sfx) {
        System.out.print("Masukan m : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(n, n + 1);

        M.bacaMatriks();

        SPLInvers(M, sfx);
    }

    public static void SPLInvers(Matriks M, String sfx) {
        int n = M.m_baris;

        // SPLIT INPUT
        Matriks A = new Matriks(n, n);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBaris(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKolom(A); j++) {
                A.Mat[i][j] = M.Mat[i][j];
            }
        }

        Matriks B = new Matriks(n, 1);
        for (int i = B.GetFirstIdxBrs(B); i <= B.GetLastIdxBaris(B); i++) {
            B.Mat[i][0] = M.Mat[i][n];
        }

        // OUTPUT
        Matriks AInvers = new Matriks(n, n);
        if (Matriks.inversGaussJordan(A, AInvers)) {
            Matriks X = Matriks.kaliMatriks(AInvers, B); // PROSES SEBELUM OUTPUT

            for (int i = X.GetFirstIdxBrs(X); i <= X.GetLastIdxBaris(X); i++) {
                System.out
                        .println(sfx + (i + 1) + " = " + X.Mat[i][0]);
            }
        } else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }

    }

    public static void metodeCramer(String sfx) {
        System.out.print("Masukan m : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(n, n + 1);

        M.bacaMatriks();

        metodeCramer(M, sfx);
    }

    public static void metodeCramer(Matriks M, String sfx) {
        int n = M.m_baris;

        // SPLIT INPUT
        Matriks A = new Matriks(n, n);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBaris(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKolom(A); j++) {
                A.Mat[i][j] = M.Mat[i][j];
            }
        }

        Matriks B = new Matriks(n, 1);
        for (int i = B.GetFirstIdxBrs(B); i <= B.GetLastIdxBaris(B); i++) {
            B.Mat[i][0] = M.Mat[i][n];
        }

        // PROSES & OUTPUT
        double detA = A.determinanKofaktor(A);
        if (detA != 0) {
            double[] x = new double[n];
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKolom(A); j++) {
                Matriks Aj = new Matriks(n, n);
                Matriks.Copy(A, Aj);
                for (int i = A.GetFirstIdxBrs(M); i <= A.GetLastIdxBaris(A); i++) {
                    Aj.Mat[i][j] = B.Mat[i][0];
                }

                x[j] = Aj.determinanKofaktor(Aj) / detA;
            }

            // OUTPUT
            for (int i = 0; i < n; i++) {
                System.out.println(sfx + (i + 1) + " = " + x[i]);
            }
        } else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }
    }
}