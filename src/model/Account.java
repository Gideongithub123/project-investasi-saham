package model;

public class Account {
    private String username;
    private String password;
    private String role; // "admin" or "customer"
    private Portfolio portfolio;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;

        // Initialize portfolio for customers
        if (role.equalsIgnoreCase("customer")) {
            this.portfolio = new Portfolio();
        }
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    // Authentication method
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}