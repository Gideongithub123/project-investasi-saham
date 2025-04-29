package model;

public class Saham {
    private String kode;               // Kode unik saham
    private String namaPerusahaan;     // Nama perusahaan penerbit saham
    private double harga;              // Harga per lembar saham

    public Saham(String kode, String namaPerusahaan, double harga) {
        this.kode = kode;
        this.namaPerusahaan = namaPerusahaan;
        this.harga = harga;
    }