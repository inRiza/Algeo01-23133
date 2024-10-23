public class Interpolasi {
    
    public static interpolasiPolinom(double[][] Matriks){
        int row = Matriks.length;
        int col = Matriks[0].length;

        double [][] temp = new double[row][row+1];

        for(int i=0;i<row;i++){
            for(int j=0;j<col+1;j++){
                if(j==col){
                    temp[i][j] = Matriks[i][1];
                } else {
                    temp[i][j] = Math.pow(Matriks[i][0],j);
                }
            }
        }

        double[][] hasil = gaussJordan(temp);
        double[][] hasilAkhr = new double[row][1];

        for(int i=0;i<row;i++){
            hasilAkhr[i][0] = hasil[i][col];
        }
        return hasilAkhr;
    }   

    public static double estimasiY(double[] Matriks, double x){
        double y = 0;
        int n = Matriks.length;

        for(int i=0;i<n;i++){
            y += Matriks[i]*Math.pow(x,i);
        }
        return y;
    }

    public static void hasilInterpolasiPolinom(double[][] hasilAkhr, double x){
        double hasil1 = 0;
        int i;
        for(i = 0;i<hasilAkhr.length;i++){
            hasil1 += hasilAkhr[i][0]*Math.pow(x,i);
        }

        String jawaban ="";
        System.out.println("Jawaban :");
        String jawabanAkhir ="";

        for(i=0;i< hasilAkhr.length;i++){
            if(i == 0){
                jawaban += jawaban + "f(x)" + hasilAkhr[i][0];
            } else if(i == 1){
                jawaban += " + " + hasilAkhr[i][0] + "x";
            } else {
                jawaban += " + " + hasilAkhr[i][0] + "x^" + i;
            }
        }
        jawabanAkhir = jawaban + " = " + hasil1;
        return jawabanAkhir;
    }

    public static void menuInterpolasiPolinom(){
        Scanner input = new Scanner(System.in);
        int inputTipe;

        System.out.println("\nPilih jenis masukan: ");
        System.out.println("1.Input dari keyboard");
        System.out.println("2.Input dari file");
        System.out.println("========================");
        System.out.print("1 atau 2 nih: ");
        inputTipe = input.nextInt();

        while(inputTipe != 1 && inputTipe != 2){
            System.out.println("Masukan salah, masukan ulang");
            System.out.print("1 atau 2 nih: ");
            inputTipe = input.nextInt();
        }

        // if(inputTipe == 1){
        //     System.out.print("Masukkan derjat polinomial: ");
        //     int n = input.nextInt();
        //     double[][] titik = new double[n+1][2];
        //     for(int i=0;i<n;i++){
        //         System.out.print("Masukkan x"+(i+1)+": ");
        //         titik[i][0] = input.nextDouble();
        //         System.out.print("Masukkan y"+(i+1)+": ");
        //         titik[i][1] = input.nextDouble();
        //     }
        // }
        // else{

        // }
    }


}
