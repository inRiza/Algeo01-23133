public class Invers {
    public static void inversPakeGaussJordan() {
        Matriks M = Input.inputMatriks();
        if (M == null) {
            System.out.println("Matriks bukan persegi.");
        } else {
            Matriks N = new Matriks(M.Mat);
            Matriks.inversBuatGaussJordan(M, N);

            N.cetakMatriks();
        }
    }

    public static void inversPakeAdjoin() {
        Matriks M = Input.inputMatriks();
        if (M == null) {
            System.out.println("Matriks bukan persegi.");
        } else {
            Matriks N = new Matriks(M.Mat);
            M.inversAdjoin(M, N);

            N.cetakMatriks();
        }
    }
}
