package model;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    // Menyimpan data saham (kode saham, jumlah lembar)
    private Map<String, Integer> sahamPortfolio;

    // Menyimpan data SBN (nama SBN, nominal investasi)
    private Map<String, Double> sbnPortfolio;

    public Portfolio() {
        this.sahamPortfolio = new HashMap<>();
        this.sbnPortfolio = new HashMap<>();
    }

    // Metode untuk saham
    public void beliSaham(String kodeSaham, int jumlahLembar) {
        sahamPortfolio.put(kodeSaham, sahamPortfolio.getOrDefault(kodeSaham, 0) + jumlahLembar);
    }

    public boolean jualSaham(String kodeSaham, int jumlahLembar) {
        int lembarDimiliki = sahamPortfolio.getOrDefault(kodeSaham, 0);
        if (lembarDimiliki >= jumlahLembar) {
            int sisaLembar = lembarDimiliki - jumlahLembar;
            if (sisaLembar == 0) {
                sahamPortfolio.remove(kodeSaham);
            } else {
                sahamPortfolio.put(kodeSaham, sisaLembar);
            }
            return true;
        }
        return false;
    }

    public int getJumlahLembarSaham(String kodeSaham) {
        return sahamPortfolio.getOrDefault(kodeSaham, 0);
    }

    public Map<String, Integer> getAllSaham() {
        return sahamPortfolio;
    }

