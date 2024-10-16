import java.util.*;

class Menu {

    public static int menu() throws NoSuchElementException {
        Scanner scan = new Scanner(System.in);

        int result;
        System.out.println("\nWelcome To Office Menu!");
        System.out.println("Pilih no. untuk melakukan perintah.");
        System.out.println("1. Sistem Persamaan Linear(SPL)");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks kofaktor");
        System.out.println("5. Adjoint");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar menu");
        System.out.println("========================");
        System.out.println("Ayo dipliih ya :D (ketik no. sesuai urutan): ");
        
        result = scan.nextInt();

        return result;
    }

    public static int menuType() {
        Scanner scan = new Scanner(System.in);
        int result;

        System.out.println("\nPilih jenis masukan: ");
        System.out.println("1.Input dari keyboard");
        System.out.println("2.Input dari file");
        System.out.println("========================");
        System.out.println("1 atau 2 nih: ");

        result = scan.nextInt();

        return result;
    }

    public static String inputFile() {
        Scanner scan = new Scanner(System.in);
        String result;

        System.out.println("\nMasukkan nama file dalam .txt (misal: matriks.txt): ");

        result = scan.nextLine();

        return result;
    }

    public static int spl() {
        Scanner scan = new Scanner(System.in);
        int result;

        System.out.println("\n1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Metode Cramer");
        System.out.println("========================");
        System.out.println("Pilihan: ");

        result = scan.nextInt();

        return result;
    }

    public static int determinan() {
        Scanner scan = new Scanner(System.in);
        int result;

        System.out.println("\n1. Metode Operasi Baris Elementer (OBE)");
        System.out.println("2. Metode Matriks Kofaktor");
        System.out.println("========================");
        System.out.println("Pilihan: ");

        result = scan.nextInt();

        return result;
    }

    public static int invers() {
        Scanner scan = new Scanner(System.in);
        int result;

        System.out.println("\n1. Metode Adjoint");
        System.out.println("\n2. Metode Eliminasi Gauss-Jordan");
        System.out.println("========================");
        System.out.println("\nPilihan");

        result = scan.nextInt();

        return result;
    }

    public static void outputFile() {

    }

    public static void Run() {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        int menu, spl, determinan, invers;

        while (running) {
            menu = Menu.menu();
            if (menu == 2) {
                determinan = Menu.determinan();
                if (determinan == 1) {
                    Determinan.detOBE();
                } else if (determinan == 2) {
                    Determinan.detKofaktor();
                } else {
                    System.out.println("Pilihan tidak ada! Cek lagi ya :D");
                }
            }
        }
    }
}
