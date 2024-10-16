public class Determinan {
    public static void detOBE() {
        Matriks M = Input.inputMatriks();
        if (M == null) {
            System.out.println("Matriks bukan persegi.");
        } else {
            System.out.println("Determinan dari matriks tersebut adalah " + M.determinanOBE(M));
        }

    }

    public static void detKofaktor() {
        Matriks M = Input.inputMatriks();
        if (M == null) {
            System.out.println("Matriks bukan persegi.");
        } else {
            System.out.println("Determinan dari matriks tersebut adalah " + M.determinanKofaktor(M));
        }

    }

}