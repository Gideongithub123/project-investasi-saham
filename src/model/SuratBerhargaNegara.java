package model;

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