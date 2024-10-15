import MatriksPackage.Matriks;
import SPLPackage.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Matriks matriks = new Matriks();
        Invers invers = new Invers(matriks);
        Gauss gauss = new Gauss();

        System.out.println("Pilih metode input:");
        System.out.println("1. Input manual");
        System.out.println("2. Baca dari file");
        System.out.print("Masukkan pilihan (1/2): ");
        int pilihan = input.nextInt();

        if (pilihan == 1) {
            matriks.bacaMatriks();
        } else if (pilihan == 2) {
            System.out.print("Masukkan nama file (misal: matrix.txt): ");
            String fileName = input.next();
            matriks.bacaMatriksDariFile(fileName);
        } else {
            System.out.println("Pilihan tidak valid!");
            input.close();
            return;
        }

        System.out.println("Matriks yang dibaca:");
        matriks.cetakMatriks();
        //System.out.println("Transpose yang dihasilkan: ");
        //invers.transposeMatriks();
        //matriks.cetakMatriks();

        double det = matriks.determinanMatriks();
        System.out.println("Determinan matriks yang terhitung adalah: " + det);

        System.out.println("Kofaktor matriksnya adalah: ");
        matriks.kofaktorMatriks();
        matriks.cetakMatriks();

        System.out.println("b: ");
        double[] a = matriks.pisahSpl();
        matriks.cetakList(a);

        System.out.println("cramer: ");
        double[] b = matriks.hitungCramer(a);
        matriks.cetakList(b);

        //System.out.println("Gauss matriksnya adalah: ");
        //double[] a = matriks.pisahSpl();
        //matriks.hitungCramer(a);

        input.close();
    }
}
