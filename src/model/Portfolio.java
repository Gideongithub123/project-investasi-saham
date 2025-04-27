package model;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    // Menyimpan saham yang dimiliki
    private Map<Saham, Integer> saham;
    // Menyimpan SBN yang dimiliki
    private Map<SuratBerhargaNegara, Double> sbn;

    public Portfolio() {
        saham = new HashMap<>();
        sbn = new HashMap<>();
    }

    // Menambahkan saham ke portofolio
    public void addSaham(Saham saham, int jumlahLembar) {
        // Jika saham sudah ada, tambah jumlah lembar yang dimiliki
        if (this.saham.containsKey(saham)) {
            this.saham.put(saham, this.saham.get(saham) + jumlahLembar);
        } else {
            this.saham.put(saham, jumlahLembar);
        }
    }

    // Menghapus saham dari portofolio
    public void removeSaham(Saham saham, int jumlahLembar) {
        if (this.saham.containsKey(saham)) {
            int jumlahSahamSekarang = this.saham.get(saham);
            // Kurangi jumlah lembar saham yang dimiliki
            if (jumlahSahamSekarang > jumlahLembar) {
                this.saham.put(saham, jumlahSahamSekarang - jumlahLembar);
            } else {
                // Hapus saham jika sudah tidak ada lagi yang dimiliki
                this.saham.remove(saham);
            }
        }
    }

    // Menambahkan SBN ke portofolio
    public void addSBN(SuratBerhargaNegara sbn, double nominal) {
        // Jika SBN sudah ada, tambah nominal yang dimiliki
        if (this.sbn.containsKey(sbn)) {
            this.sbn.put(sbn, this.sbn.get(sbn) + nominal);
        } else {
            this.sbn.put(sbn, nominal);
        }
    }

    // Mengambil daftar saham yang dimiliki
    public Map<Saham, Integer> getSaham() {
        return saham;
    }

    // Mengambil daftar SBN yang dimiliki
    public Map<SuratBerhargaNegara, Double> getSBN() {
        return sbn;
    }
}