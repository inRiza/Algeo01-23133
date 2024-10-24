import java.util.Scanner;

public class Interpolasi {
    static Scanner scan = new Scanner(System.in);

    // Fungsi untuk membaca matriks dari input pengguna
    public static void interpolasiSplineBikubik() {
        System.out.print("Masukkan jumlah baris: ");
        int baris = scan.nextInt();

        System.out.print("Masukkan jumlah kolom: ");
        int kolom = scan.nextInt();

        Matriks M = new Matriks(baris, kolom); // Membuat objek matriks

        M.bacaMatriks(); // Membaca nilai-nilai matriks

        System.out.print("Masukkan titik (x): ");
        double x = scan.nextDouble();
        System.out.print("Masukkan titik (y): ");
        double y = scan.nextDouble();

        // Melakukan interpolasi bikubik
        double hasil = interpolasiSplineBikubik(M, x, y);

        // Output hasil interpolasi
        System.out.println("Hasil interpolasi bikubik: " + hasil);
    }

    // Fungsi untuk melakukan interpolasi kubik 1D
    public static double interpolasiKubik(double[] p, double x) {
        return p[1] + 0.5 * x * (p[2] - p[0] + x * (2.0 * p[0] - 5.0 * p[1] + 4.0 * p[2] - p[3]
                + x * (3.0 * (p[1] - p[2]) + p[3] - p[0])));
    }

    // Fungsi untuk interpolasi bikubik pada grid 2D
    public static double interpolasiSplineBikubik(Matriks M, double x, double y) {
        // Asumsi Matriks M adalah 4x4 (untuk interpolasi bikubik)

        double[] arr = new double[4];
        for (int i = 0; i < 4; i++) {
            // Mengambil baris ke-i dari matriks
            double[] row = M.Mat[i]; // Asumsi M.getBaris(i) mengembalikan array double[] baris ke-i
            arr[i] = interpolasiKubik(row, x);
        }
        // Lakukan interpolasi kubik pada hasil interpolasi kolom (sumbu Y)
        return interpolasiKubik(arr, y);
    }

    /*public static void interpolasiPolinom() {
        System.out.print("Masukan derajat polinom (n): ");
        int baris = scan.nextInt();
        Matriks titik = new Matriks(baris, 2);
        titik.bacaMatriks();
        Matriks baru = new Matriks(baris, 4);
        for (int i = 0; i < baru.m_baris; i++) {
            baru.Mat[i][2] = 1;
        }
        for (int i = 0; i < baru.m_baris; i++) {
            baru.Mat[i][1] = titik.Mat[i][0];
        }
        for (int i = 0; i < baru.m_baris; i++) {
            baru.Mat[i][0] = titik.Mat[i][0] * titik.Mat[i][0];
        }
        for (int i = 0; i < baru.m_baris; i++) {
            baru.Mat[i][3] = titik.Mat[i][1];
        }

        SPL.metodeGauss(baru, "x");
    }*/
}
