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

    private static void initData() {
        // Buat akun admin dan customer
        accounts.add(new Account("admin", "admin123", "admin"));
        accounts.add(new Account("customer", "customer123", "customer"));

        // Buat beberapa data saham awal
        daftarSaham.add(new Saham("GOTO", "GoTo Gojek Tokopedia Tbk.", 9000));
        daftarSaham.add(new Saham("PGEO", "Pertamina Geothermal Energy Tb", 2000));
        daftarSaham.add(new Saham("INDF", "Indofood Sukses Makmur Tbk.", 4500));
        daftarSaham.add(new Saham("BUKA", "Bukalapak.com TBK", 5000));

        // Buat beberapa data SBN awal
        LocalDate now = LocalDate.now();
        daftarSBN.add(new SuratBerhargaNegara("SBR010", 6.25, 24, now.plusMonths(24), 1000000000));
        daftarSBN.add(new SuratBerhargaNegara("ORI020", 5.70, 36, now.plusMonths(36), 2000000000));
    }


