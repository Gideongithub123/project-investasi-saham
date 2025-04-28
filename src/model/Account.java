package model;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Selamat datang di Sistem Investasi!");

        // Minta login dari pengguna
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();

        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        // Proses login
        Account userAccount = Account.login(username, password);

        // Jika login berhasil
        if (userAccount != null) {
            if (userAccount.isAdmin()) {
                showAdminMenu(userAccount);
            } else {
                showCustomerMenu(userAccount);
            }
        } else {
            System.out.println("Login gagal. Program akan keluar.");
        }
    }

    // Menu untuk Admin
    public static void showAdminMenu(Account adminAccount) {
        System.out.println("\nSelamat datang Admin!");
        System.out.println("1. Saham");
        System.out.println("2. SBN");
        System.out.println("3. Logout");
        System.out.print("Pilih menu: ");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                // Tambah saham, ubah harga, dll
                break;
            case 2:
                // Tambah SBN, dll
                break;
            case 3:
                System.out.println("Logout berhasil.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    // Menu untuk Customer
    public static void showCustomerMenu(Account customerAccount) {
        System.out.println("\nSelamat datang Customer!");
        System.out.println("1. Beli Saham");
        System.out.println("2. Jual Saham");
        System.out.println("3. Beli SBN");
        System.out.println("4. Simulasi SBN");
        System.out.println("5. Portofolio");
        System.out.println("6. Logout");
        System.out.print("Pilih menu: ");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                // Beli saham
                break;
            case 2:
                // Jual saham
                break;
            case 3:
                // Beli SBN
                break;
            case 4:
                // Simulasi SBN
                break;
            case 5:
                // Tampilkan portofolio
                showPortfolio(customerAccount);
                break;
            case 6:
                System.out.println("Logout berhasil.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }
