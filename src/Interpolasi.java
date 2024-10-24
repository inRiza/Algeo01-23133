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
            double[] baris = M.Mat[i]; // Asumsi M.getBaris(i) mengembalikan array double[] baris ke-i
            arr[i] = interpolasiKubik(baris, x);
        }
        // Lakukan interpolasi kubik pada hasil interpolasi kolom (sumbu Y)
        return interpolasiKubik(arr, y);
    }

    public static void interpolasiPolinom() {
        // Input jumlah titik M
        System.out.print("Masukkan jumlah titik: ");
        int baris = scan.nextInt();

        Matriks M = new Matriks(baris, 2);
        M.bacaMatriks();

        // Memasukkan nilai x yang ingin diprediksi
        System.out.print("Masukkan nilai x untuk estimasi: ");
        double xp = scan.nextDouble();

        // Lakukan interpolasi Lagrange
        double yp = interpolasiPolinom(M, xp);

        // Output hasil prediksi
        System.out.println("Hasil estimasi y pada x = " + xp + " adalah " + yp);

        scan.close();
    }

    // Fungsi untuk menghitung polinom interpolasi Lagrange
    public static double interpolasiPolinom(Matriks M, double xp) {
        int n = M.m_baris;
        double hasil = 0.0;

        // Loop untuk menghitung setiap suku Lagrange
        for (int i = 0; i < n; i++) {
            double sukuL = 1.0;

            // Hitung nilai suku Lagrange L_i(x)
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    sukuL *= (xp - M.Mat[j][0]) / (M.Mat[i][0] - M.Mat[j][0]);
                }
            }

            // Tambahkan suku Lagrange yang telah dikalikan dengan y[i]
            hasil += sukuL * M.Mat[i][1];
        }

        return hasil;
    }
}
