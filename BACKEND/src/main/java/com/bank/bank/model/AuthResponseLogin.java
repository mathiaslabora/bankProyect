package com.bank.bank.model;

public class AuthResponseLogin {
    private boolean authenticated;
    private String role;

    public AuthResponseLogin(boolean authenticated, String role) {
        this.authenticated = authenticated;
        this.role = role;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getRole() {
        return role;
    }
}
