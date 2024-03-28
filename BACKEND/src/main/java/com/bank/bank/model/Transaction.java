package com.bank.bank.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_number;
    private Date date_trans;
    private BigDecimal amount;
    private String alert;
    private int destination_account;
    private String description;
    private int currency;
    private String transaction_type;

    private boolean processed;

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    private short ordinal;
    @ManyToOne
    @JoinColumn(name = "account_number")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "document_cli")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Transaction() {
    }


    public int getTransaction_number() {
        return transaction_number;
    }

    public void setTransaction_number(int transaction_number) {
        this.transaction_number = transaction_number;
    }

    public Date getDate_trans() {
        return date_trans;
    }

    public void setDate_trans(Date date_trans) {
        this.date_trans = date_trans;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public int getDestination_account() {
        return destination_account;
    }

    public void setDestination_account(int destination_account) {
        this.destination_account = destination_account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Account getCuenta() {
        return account;
    }

    public void setCuenta(Account account) {
        this.account = account;
    }

    public Client getCliente() {
        return client;
    }

    public void setCliente(Client client) {
        this.client = client;
    }

    public Card getTarjeta() {
        return card;
    }

    public void setTarjeta(Card card) {
        this.card = card;
    }

    public short getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(short ordinal) {
        this.ordinal = ordinal;
    }
}
