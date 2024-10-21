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
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bikubik Spline");
        System.out.println("6. Regresi Linear & Kuadratik berganda");
        System.out.println("7. Keluar menu");
        System.out.println("========================");
        System.out.print("Ayo dipilih ya :D (ketik no. sesuai urutan): ");

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
        System.out.print("1 atau 2 nih: ");

        result = scan.nextInt();

        return result;
    }

    public static int menuSPL() {
        Scanner scan = new Scanner(System.in);
        int result;

        System.out.println("\n1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Metode Cramer");
        System.out.println("========================");
        System.out.print("Pilihan: ");

        result = scan.nextInt();

        return result;
    }

    public static String inputFile() {
        Scanner scan = new Scanner(System.in);
        String result;

        System.out.print("\nMasukkan nama file dalam .txt (misal: matriks.txt): ");

        result = scan.nextLine();

        return result;
    }

    public static int regresi() {
        Scanner scan = new Scanner(System.in);
        int result;

        System.out.println("\n1. Regresi linear berganda");
        System.out.println("2. Regresi kuadratik berganda");
        System.out.println("========================");
        System.out.print("Pilihan: ");

        result = scan.nextInt();

        return result;
    }

    public static int determinan() {
        Scanner scan = new Scanner(System.in);
        int result;

        System.out.println("\n1. Metode Operasi Baris Elementer (OBE)");
        System.out.println("2. Metode Matriks Kofaktor");
        System.out.println("========================");
        System.out.print("Pilihan: ");

        result = scan.nextInt();

        return result;
    }

    public static int invers() {
        Scanner scan = new Scanner(System.in);
        int result;

        System.out.println("\n1. Metode Adjoint");
        System.out.println("\n2. Metode Eliminasi Gauss-Jordan");
        System.out.println("========================");
        System.out.print("\nPilihan");

        result = scan.nextInt();

        return result;
    }

    public static void outputFile() {

    }

    public static void Run() {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        int menu, spl, determinan, invers, regresi, inputSPL;
        Matriks fileSPL;
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
            } else if (menu == 1) {
                spl = Menu.menuSPL();
                if (spl == 1) {
                    inputSPL = menuType();
                    if (inputSPL == 1) {
                        SPL.bacaSPLBuatGauss("x");
                    } else if (inputSPL == 2) {
                        String filename = inputFile();
                        try {
                            fileSPL = new Matriks(filename);
                            SPL.metodeGauss(fileSPL, "x");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (spl == 2) {
                    inputSPL = menuType();
                    if (inputSPL == 1) {
                        SPL.buatGaussJordan("x");
                    } else if (inputSPL == 2) {
                        String filename = inputFile();
                        try {
                            fileSPL = new Matriks(filename);
                            SPL.metodeGaussJordan(fileSPL, "x");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (spl == 3) {
                    inputSPL = menuType();
                    if (inputSPL == 1) {
                        SPL.buatSPLInvers("x");
                    } else if (inputSPL == 2) {
                        String filename = inputFile();
                        try {
                            fileSPL = new Matriks(filename);
                            SPL.SPLInvers(fileSPL, "X");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (spl == 4) {
                    SPL.buatCramer("x");
                } else {
                    System.out.println("Pilihan tidak ada! Cek lagi ya :D");
                }
            } else if (menu == 3) {
                invers = Menu.invers();
                if (invers == 1) {
                    Invers.inversPakeAdjoin();
                } else if (invers == 2) {
                    Invers.inversPakeGaussJordan();
                } else {
                    System.out.println("Pilihan tidak ada! Cek lagi ya :D");
                }
            } else if (menu == 7) {
                break;
            }
            System.out.println();
            System.out.print("Memulai lagi(y/n) ? ");
            char mulai = scan.next().charAt(0);
            if (mulai != 'Y' && mulai != 'y') {
                running = false;
            }
        }
    }
}
