import java.util.*;

public class SPL {
    static Scanner scan = new Scanner(System.in);

    public static void bacaSPL(String sfx) {
        System.out.println("Masukan jumlah baris: ");
        int baris = scan.nextInt();
        System.out.println("Masukan jumlah kolom: ");
        int kolom = scan.nextInt();
        Matriks M = new Matriks(baris, kolom + 1);

        M.bacaMatriks();

        bacaSPL(M, sfx);
    }
    // eliminasi gauss
    //eliminasi gauss jordan
    //cramer
}