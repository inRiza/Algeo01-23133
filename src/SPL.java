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

    //SPL Invers
    public SPLInvers(Matriks Aug){
        int baris = Aug.m_baris;
        int kolom = Aug.n_kolom;

        Matriks a = new Matriks (baris, kolom-1);
        Matriks b = new Matriks (baris, 1);

        int i,j;

        for(i=0;i<baris;i++){
            for(j=0;j<kolom-1;j++){
                if(j<kolom-1){
                    a.Matriks[i][j] = Aug.Matriks[i][j];
                } else{
                    b.Matriks[i][0] = Aug.Matriks[i][j];
                }
            }
        }

        double detA = determinanKofaktor(a);
        if(detA == 0){
            System.out.println("Matriks tidak mempunyai Determian");
            return null;
        }

        Matriks Invers = inversWithAdjoint(a);
        Matriks hasil = multiplyMatriks(Invers, b);

        return hasil;
    }

    //cramer
}