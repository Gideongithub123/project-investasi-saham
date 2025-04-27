package app;

import model.Account;
import model.portfolio;


public class MainAplikasiSaham {
        private static Account loggedInAccount;
        private static Portfolio portfolio = new Portfolio();
        private static Scanner scanner = new Scanner(System.in);

        // Hardcoded accounts untuk login
        private static Account admin = new Account("admin", "admin123", "admin");
        private static Account customer = new Account("customer", "customer123", "customer");
    private static void displayMainMenu() {
        System.out.println("\nSelamat datang, " + loggedInAccount.getUsername() + "!");
        if (loggedInAccount.getRole().equals("admin")) {
            System.out.println("1. Saham");
            System.out.println("2. SBN");
            System.out.println("3. Logout");
        } else if (loggedInAccount.getRole().equals("customer")) {
            System.out.println("1. Beli Saham");
            System.out.println("2. Jual Saham");
            System.out.println("3. Beli SBN");
            System.out.println("4. Simulasi SBN");
            System.out.println("5. Portofolio");
            System.out.println("6. Logout");
        }
    }


    // Menu utama yang akan menampilkan pilihan berdasarkan role
    private static void mainMenu() {
        boolean continueMainMenu = true;
        while (continueMainMenu) {
            displayMainMenu();
            System.out.print("Pilih menu: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    if (loggedInAccount.getRole().equals("admin")) {
                        adminSahamMenu();
                    } else {
                        beliSaham();
                    }
                    break;
                case 2:
                    if (loggedInAccount.getRole().equals("admin")) {
                        adminSBNMenu();
                    } else {
                        jualSaham();
                    }
                    break;
                case 3:
                    if (loggedInAccount.getRole().equals("admin")) {
                        continueMainMenu = false;
                    } else {
                        beliSBN();
                    }
                    break;
                case 4:
                    if (loggedInAccount.getRole().equals("customer")) {
                        simulasiSBN();
                    }
                    break;
                case 5:
                    if (loggedInAccount.getRole().equals("customer")) {
                        lihatPortofolio();
                    }
                    break;
                case 6:
                    if (loggedInAccount.getRole().equals("customer")) {
                        continueMainMenu = false;
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
