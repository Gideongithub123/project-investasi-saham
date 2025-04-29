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

    // Getter dan setter
    public String getKode() { return kode; }
    public String getNamaPerusahaan() { return namaPerusahaan; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    // Menampilkan informasi saham
    @Override
    public String toString() {
        return kode + " - " + namaPerusahaan + " @ Rp" + harga;
    }
}

}
