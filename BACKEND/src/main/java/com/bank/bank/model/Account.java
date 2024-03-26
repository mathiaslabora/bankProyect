package com.bank.bank.model;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_number;
    private int document_cli;
    private boolean active;
    private String account_tipe;
    @ManyToOne
    @JoinColumn(name = "document_cli", insertable = false, updatable = false)
    private Client client;

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public int getDocument_cli() {
        return document_cli;
    }

    public void setDocument_cli(int document_cli) {
        this.document_cli = document_cli;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAccount_tipe() {
        return account_tipe;
    }

    public void setAccount_tipe(String account_tipe) {
        this.account_tipe = account_tipe;
    }

    public Client getCliente() {
        return client;
    }

    public void setCliente(Client client) {
        this.client = client;
    }
}
