package model;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private String username;
    private String password;
    private boolean isAdmin;
    private Portfolio portfolio;  // Menggunakan kelas Portfolio yang sudah dibahas sebelumnya

    public Account(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.portfolio = new Portfolio();  // Setiap akun memiliki portofolio saham dan SBN
    }

    // Getter untuk username
    public String getUsername() {
        return username;
    }

    // Getter untuk password
    public String getPassword() {
        return password;
    }

    // Getter untuk status Admin
    public boolean isAdmin() {
        return isAdmin;
    }

    // Mengakses portofolio akun
    public Portfolio getPortfolio() {
        return portfolio;
    }

    // Login dengan username dan password
    public static Account login(String username, String password) {
        // List akun admin dan customer yang sudah terdaftar
        Account adminAccount = new Account("admin", "adminpass", true);
        Account customerAccount = new Account("customer", "customerpass", false);

        // Validasi login
        if (username.equals(adminAccount.getUsername()) && password.equals(adminAccount.getPassword())) {
            System.out.println("Login sebagai Admin berhasil!");
            return adminAccount;
        } else if (username.equals(customerAccount.getUsername()) && password.equals(customerAccount.getPassword())) {
            System.out.println("Login sebagai Customer berhasil!");
            return customerAccount;
        } else {
            System.out.println("Login gagal! Username atau password salah.");
            return null;
        }
    }
}