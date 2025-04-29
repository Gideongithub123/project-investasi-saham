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
    private static void showLoginMenu() {
        System.out.println("\n=== APLIKASI INVESTASI SAHAM DAN SBN ===");
        System.out.println("1. Login");
        System.out.println("0. Keluar");

        int pilihan = validator.getIntInput("Pilihan: ", 0, 1);

        switch (pilihan) {
            case 0:
                System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                System.exit(0);
                break;
            case 1:
                login();
                break;
        }
    }

    private static void login() {
        String username = validator.getStringInput("Username: ");
        String password = validator.getStringInput("Password: ");

        for (Account account : accounts) {
            if (account.authenticate(username, password)) {
                loggedInAccount = account;
                System.out.println("Login berhasil sebagai " + account.getRole() + "!");
                return;
            }
        }

        System.out.println("Login gagal. Username atau password salah.");
    }

    private static void showAdminMenu() {
        System.out.println("\n=== MENU ADMIN ===");
        System.out.println("1. Saham");
        System.out.println("2. SBN");
        System.out.println("3. Logout");

        int pilihan = validator.getIntInput("Pilihan: ", 1, 3);

        switch (pilihan) {
            case 1:
                showAdminSahamMenu();
                break;
            case 2:
                showAdminSBNMenu();
                break;
            case 3:
                logout();
                break;
        }
    }

    private static void showAdminSahamMenu() {
        System.out.println("\n=== MENU ADMIN SAHAM ===");
        System.out.println("1. Tambah Saham");
        System.out.println("2. Ubah Harga Saham");
        System.out.println("3. Kembali");

        int pilihan = validator.getIntInput("Pilihan: ", 1, 3);

        switch (pilihan) {
            case 1:
                tambahSaham();
                break;
            case 2:
                ubahHargaSaham();
                break;
            case 3:
                // Kembali ke menu admin
                break;
        }
    }

    private static void tambahSaham() {
        System.out.println("\n=== TAMBAH SAHAM ===");
        String kode = validator.getStringInput("Kode saham: ");

        // Cek apakah kode sudah ada
        for (Saham saham : daftarSaham) {
            if (saham.getKode().equalsIgnoreCase(kode)) {
                System.out.println("Saham dengan kode " + kode + " sudah ada.");
                return;
            }
        }

        String namaPerusahaan = validator.getStringInput("Nama perusahaan: ");
        double harga = validator.getDoubleInput("Harga per lembar (Rp): ", 0);

        daftarSaham.add(new Saham(kode, namaPerusahaan, harga));
        System.out.println("Saham " + kode + " berhasil ditambahkan.");
    }

    private static void ubahHargaSaham() {
        System.out.println("\n=== UBAH HARGA SAHAM ===");
        if (daftarSaham.isEmpty()) {
            System.out.println("Belum ada saham yang tersedia.");
            return;
        }

        // Tampilkan daftar saham
        System.out.println("Daftar Saham:");
        for (int i = 0; i < daftarSaham.size(); i++) {
            System.out.println((i + 1) + ". " + daftarSaham.get(i));
        }

        int pilihan = validator.getIntInput("Pilih saham (1-" + daftarSaham.size() + "): ", 1, daftarSaham.size());
        Saham saham = daftarSaham.get(pilihan - 1);

        double hargaBaru = validator.getDoubleInput("Harga baru (Rp): ", 0);
        saham.setHarga(hargaBaru);

        System.out.println("Harga saham " + saham.getKode() + " berhasil diubah menjadi Rp " + hargaBaru);
    }

    private static void showAdminSBNMenu() {
        System.out.println("\n=== MENU ADMIN SBN ===");
        System.out.println("1. Tambah SBN");
        System.out.println("2. Kembali");

        int pilihan = validator.getIntInput("Pilihan: ", 1, 2);

        switch (pilihan) {
            case 1:
                tambahSBN();
                break;
            case 2:
                // Kembali ke menu admin
                break;
        }
    }

    private static void tambahSBN() {
        System.out.println("\n=== TAMBAH SBN ===");
        String nama = validator.getStringInput("Nama SBN: ");

        // Cek apakah nama sudah ada
        for (SuratBerhargaNegara sbn : daftarSBN) {
            if (sbn.getNama().equalsIgnoreCase(nama)) {
                System.out.println("SBN dengan nama " + nama + " sudah ada.");
                return;
            }
        }

        double bunga = validator.getDoubleInput("Bunga (%): ", 0);
        int jangkaWaktu = validator.getIntInput("Jangka waktu (bulan): ", 1, 600);
        LocalDate tanggalJatuhTempo = validator.getDateInput("Tanggal jatuh tempo");
        double kuotaNasional = validator.getDoubleInput("Kuota nasional (Rp): ", 0);

        daftarSBN.add(new SuratBerhargaNegara(nama, bunga, jangkaWaktu, tanggalJatuhTempo, kuotaNasional));
        System.out.println("SBN " + nama + " berhasil ditambahkan.");
    }

    private static void showCustomerMenu() {
        System.out.println("\n=== MENU CUSTOMER ===");
        System.out.println("1. Beli Saham");
        System.out.println("2. Jual Saham");
        System.out.println("3. Beli SBN");
        System.out.println("4. Simulasi SBN");
        System.out.println("5. Portofolio");
        System.out.println("6. Logout");

        int pilihan = validator.getIntInput("Pilihan: ", 1, 6);

        switch (pilihan) {
            case 1:
                beliSaham();
                break;
            case 2:
                jualSaham();
                break;
            case 3:
                beliSBN();
                break;
            case 4:
                simulasiSBN();
                break;
            case 5:
                lihatPortfolio();
                break;
            case 6:
                logout();
                break;
        }
    }

    private static void beliSaham() {
        System.out.println("\n=== BELI SAHAM ===");
        if (daftarSaham.isEmpty()) {
            System.out.println("Belum ada saham yang tersedia.");
            return;
        }

        // Tampilkan daftar saham
        System.out.println("Daftar Saham yang Tersedia:");
        for (int i = 0; i < daftarSaham.size(); i++) {
            System.out.println((i + 1) + ". " + daftarSaham.get(i));
        }

        int pilihan = validator.getIntInput("Pilih saham (1-" + daftarSaham.size() + "): ", 1, daftarSaham.size());
        Saham saham = daftarSaham.get(pilihan - 1);

        int jumlahLembar = validator.getIntInput("Jumlah lembar yang akan dibeli: ", 1, Integer.MAX_VALUE);

        // Proses pembelian
        loggedInAccount.getPortfolio().beliSaham(saham.getKode(), jumlahLembar);

        System.out.println("Berhasil membeli " + jumlahLembar + " lembar saham " + saham.getKode() +
                " dengan total harga Rp " + (jumlahLembar * saham.getHarga()));
    }

    private static void jualSaham() {
        System.out.println("\n=== JUAL SAHAM ===");
        Portfolio portfolio = loggedInAccount.getPortfolio();
        Map<String, Integer> sahamDimiliki = portfolio.getAllSaham();

        if (sahamDimiliki.isEmpty()) {
            System.out.println("Anda tidak memiliki saham apapun.");
            return;
        }

        // Buat daftar saham yang dimiliki
        List<Saham> sahamList = new ArrayList<>();
        System.out.println("Daftar Saham yang Anda Miliki:");
        int index = 1;

        for (String kode : sahamDimiliki.keySet()) {
            Saham saham = findSahamByKode(kode);
            if (saham != null) {
                sahamList.add(saham);
                System.out.println(index + ". " + saham + " - Jumlah: " + sahamDimiliki.get(kode) + " lembar");
                index++;
            }
        }

        int pilihan = validator.getIntInput("Pilih saham (1-" + sahamList.size() + "): ", 1, sahamList.size());
        Saham saham = sahamList.get(pilihan - 1);

        int jumlahDimiliki = sahamDimiliki.get(saham.getKode());
        int jumlahJual = validator.getIntInput("Jumlah lembar yang akan dijual (max " + jumlahDimiliki + "): ", 1, jumlahDimiliki);

        // Proses penjualan
        boolean berhasil = portfolio.jualSaham(saham.getKode(), jumlahJual);

        if (berhasil) {
            System.out.println("Berhasil menjual " + jumlahJual + " lembar saham " + saham.getKode() +
                    " dengan total nilai Rp " + (jumlahJual * saham.getHarga()));
        } else {
            System.out.println("Gagal menjual saham. Jumlah lembar melebihi yang Anda miliki.");
        }
    }

    private static void beliSBN() {
        System.out.println("\n=== BELI SBN ===");
        if (daftarSBN.isEmpty()) {
            System.out.println("Belum ada SBN yang tersedia.");
            return;
        }

        // Tampilkan daftar SBN
        System.out.println("Daftar SBN yang Tersedia:");
        List<SuratBerhargaNegara> sbnTersedia = new ArrayList<>();

        for (int i = 0; i < daftarSBN.size(); i++) {
            SuratBerhargaNegara sbn = daftarSBN.get(i);
            if (sbn.getKuotaTersedia() > 0) {
                sbnTersedia.add(sbn);
                System.out.println((sbnTersedia.size()) + ". " + sbn);
            }
        }

        if (sbnTersedia.isEmpty()) {
            System.out.println("Tidak ada SBN yang tersedia saat ini.");
            return;
        }

        int pilihan = validator.getIntInput("Pilih SBN (1-" + sbnTersedia.size() + "): ", 1, sbnTersedia.size());
        SuratBerhargaNegara sbn = sbnTersedia.get(pilihan - 1);

        double nominal = validator.getDoubleInput("Nominal pembelian (Rp): ", 1);

        // Cek kuota tersedia
        if (nominal > sbn.getKuotaTersedia()) {
            System.out.println("Nominal pembelian melebihi kuota tersedia.");
            return;
        }

        // Proses pembelian
        sbn.tambahKuotaTerpakai(nominal);
        loggedInAccount.getPortfolio().beliSBN(sbn.getNama(), nominal);

        System.out.println("Berhasil membeli SBN " + sbn.getNama() + " dengan nominal Rp " + nominal);
    }

    private static void simulasiSBN() {
        System.out.println("\n=== SIMULASI SBN ===");
        if (daftarSBN.isEmpty()) {
            System.out.println("Belum ada SBN yang tersedia.");
            return;
        }

        // Tampilkan daftar SBN
        System.out.println("Daftar SBN untuk Simulasi:");
        for (int i = 0; i < daftarSBN.size(); i++) {
            System.out.println((i + 1) + ". " + daftarSBN.get(i));
        }

        int pilihan = validator.getIntInput("Pilih SBN (1-" + daftarSBN.size() + "): ", 1, daftarSBN.size());
        SuratBerhargaNegara sbn = daftarSBN.get(pilihan - 1);

        double nominal = validator.getDoubleInput("Nominal investasi untuk simulasi (Rp): ", 1);

        // Hitung kupon bulanan
        double kuponBulanan = sbn.hitungKuponBulanan(nominal);

        System.out.println("\nHasil Simulasi SBN " + sbn.getNama() + ":");
        System.out.println("Nominal investasi: Rp " + nominal);
        System.out.println("Bunga: " + sbn.getBunga() + "%");
        System.out.println("Jangka waktu: " + sbn.getJangkaWaktu() + " bulan");
        System.out.println("Kupon bulanan (setelah pajak): Rp " + kuponBulanan);
        System.out.println("Total kupon yang akan diterima: Rp " + (kuponBulanan * sbn.getJangkaWaktu()));
    }

    private static void lihatPortfolio() {
        System.out.println("\n=== PORTOFOLIO INVESTASI ===");
        Portfolio portfolio = loggedInAccount.getPortfolio();
        Map<String, Integer> sahamDimiliki = portfolio.getAllSaham();
        Map<String, Double> sbnDimiliki = portfolio.getAllSBN();

        // Tampilkan saham yang dimiliki
        System.out.println("--- SAHAM ---");
        if (sahamDimiliki.isEmpty()) {
            System.out.println("Anda tidak memiliki saham.");
        } else {
            double totalNilaiPembelian = 0;
            double totalNilaiPasar = 0;

            System.out.println("Kode\tNama Perusahaan\tJumlah Lembar\tHarga Beli\tNilai Pasar");
            for (String kode : sahamDimiliki.keySet()) {
                Saham saham = findSahamByKode(kode);
                if (saham != null) {
                    int jumlahLembar = sahamDimiliki.get(kode);
                    double nilaiPasar = jumlahLembar * saham.getHarga();

                    System.out.println(kode + "\t" + saham.getNamaPerusahaan() + "\t" +
                            jumlahLembar + "\t\tRp " + saham.getHarga() + "\tRp " + nilaiPasar);

                    totalNilaiPasar += nilaiPasar;
                }
            }

            System.out.println("\nTotal nilai pasar saham: Rp " + totalNilaiPasar);
        }

        // Tampilkan SBN yang dimiliki
        System.out.println("\n--- SBN ---");
        if (sbnDimiliki.isEmpty()) {
            System.out.println("Anda tidak memiliki SBN.");
        } else {
            double totalNominalSBN = 0;
            double totalKuponBulanan = 0;

            System.out.println("Nama\tNominal\tBunga\tKupon Bulanan");
            for (String nama : sbnDimiliki.keySet()) {
                SuratBerhargaNegara sbn = findSBNByNama(nama);
                if (sbn != null) {
                    double nominal = sbnDimiliki.get(nama);
                    double kuponBulanan = sbn.hitungKuponBulanan(nominal);

                    System.out.println(nama + "\tRp " + nominal + "\t" +
                            sbn.getBunga() + "%\tRp " + kuponBulanan);

                    totalNominalSBN += nominal;
                    totalKuponBulanan += kuponBulanan;
                }
            }

            System.out.println("\nTotal nominal investasi SBN: Rp " + totalNominalSBN);
            System.out.println("Total kupon bulanan: Rp " + totalKuponBulanan);
        }
    }

    private static Saham findSahamByKode(String kode) {
        for (Saham saham : daftarSaham) {
            if (saham.getKode().equals(kode)) {
                return saham;
            }
        }
        return null;
    }

    private static SuratBerhargaNegara findSBNByNama(String nama) {
        for (SuratBerhargaNegara sbn : daftarSBN) {
            if (sbn.getNama().equals(nama)) {
                return sbn;
            }
        }
        return null;
    }

    private static void logout() {
        loggedInAccount = null;
        System.out.println("Logout berhasil.");
    }
}

