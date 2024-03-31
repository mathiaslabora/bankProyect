package com.bank.bank.model;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    private int document_cli;
    private String role;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "document_cli", insertable = false, updatable = false)
    private User user;

    public int getDocument_cli() {
        return document_cli;
    }

    public void setDocument_cli(int document_cli) {
        this.document_cli = document_cli;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUsuario() {
        return user;
    }

    public void setUsuario(User user) {
        this.user = user;
    }
}
