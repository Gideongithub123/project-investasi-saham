package model;

import java.time.LocalDate;

// Kelas untuk SBN
public class SuratBerhargaNegara {
    private String nama;                 // Nama produk SBN
    private double bunga;               // Persentase bunga per tahun
    private int jangkaWaktu;            // Dalam hitungan tahun
    private String tanggalJatuhTempo;   // Tanggal jatuh tempo
    private double kuotaNasional;       // Batas pembelian secara nasional

    public SuratBerhargaNegara(String nama, double bunga, int jangkaWaktu, String tanggalJatuhTempo, double kuotaNasional) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.kuotaNasional = kuotaNasional;
    }

    public String getNama() { return nama; }
    public double getBunga() { return bunga; }
    public int getJangkaWaktu() { return jangkaWaktu; }
    public LocalDate getTanggalJatuhTempo() { return tanggalJatuhTempo; }
    public double getKuotaNasional() { return kuotaNasional; }
    
    // Mendapatkan kuota yang masih tersedia
    public double getKuotaTersedia() { 
        return kuotaNasional - kuotaTerpakai; 
    }
    
    // Menambah kuota terpakai saat customer melakukan pembelian
    public void tambahKuotaTerpakai(double nominal) { 
        this.kuotaTerpakai += nominal; 
    }
    
    // Menghitung kupon bulanan (setelah pajak 10%)
    public double hitungKuponBulanan(double nominal) {
        // Rumus: %bunga / 12 bulan * 90% * nominal investasi
        return (bunga / 100) / 12 * 0.9 * nominal;
    }
    
    // Menampilkan informasi SBN
    @Override
    public String toString() {
        return nama + " - Bunga: " + bunga + "%, Jangka waktu: " + jangkaWaktu + 
               " bulan, Kuota tersedia: Rp " + getKuotaTersedia();
    }
}
