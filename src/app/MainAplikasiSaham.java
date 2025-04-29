package app;

import model.*;
import validasi.validator;

import java.time.LocalDate;
import java.util.*;

public class MainAplikasiSaham {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Account> accounts = new ArrayList<>();
    private static List<Saham> daftarSaham = new ArrayList<>();
    private static List<SuratBerhargaNegara> daftarSBN = new ArrayList<>();
    private static Account loggedInAccount = null;

    public static void main(String[] args) {
        // Inisialisasi data awal
        initData();

        boolean running = true;
        while (running) {
            if (loggedInAccount == null) {
                // Menu login
                showLoginMenu();
            } else if (loggedInAccount.getRole().equalsIgnoreCase("admin")) {
                // Menu admin
                showAdminMenu();
            } else {
                // Menu customer
                showCustomerMenu();
            }
        }
    }

