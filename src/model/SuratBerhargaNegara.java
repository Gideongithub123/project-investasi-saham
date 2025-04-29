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

    public String getNama() { return nama; }
    public double getBunga() { return bunga; }
    public int getJangkaWaktu() { return jangkaWaktu; }
    public String getTanggalJatuhTempo() { return tanggalJatuhTempo; }
    public double getKuotaNasional() { return kuotaNasional; }

    // Mengurangi kuota nasional saat customer melakukan pembelian
    public void kurangiKuota(double nominal) { this.kuotaNasional -= nominal; }

    // Menampilkan informasi SBN
    @Override
    public String toString() {
        return nama + " - Bunga: " + bunga + "%, Kuota: Rp" + kuotaNasional;
    }
}