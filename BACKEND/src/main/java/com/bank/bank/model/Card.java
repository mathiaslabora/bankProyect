package com.bank.bank.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int card_id;
    private Long card_number;
    private int account_number;
    private LocalDateTime issue_date;
    private LocalDateTime expiration_date;
    private String card_type;
    private BigDecimal limit_amount;
    private BigDecimal available_balance;
    private LocalDateTime closing_date;
    @ManyToOne
    @JoinColumn(name = "account_number", insertable = false, updatable = false)
    private Account account;

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public Long getCard_number() {
        return card_number;
    }

    public void setCard_number(Long card_number) {
        this.card_number = card_number;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public LocalDateTime getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(LocalDateTime issue_date) {
        this.issue_date = issue_date;
    }

    public LocalDateTime getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDateTime expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public BigDecimal getLimit_amount() {
        return limit_amount;
    }

    public void setLimit_amount(BigDecimal limit_amount) {
        this.limit_amount = limit_amount;
    }

    public BigDecimal getAvailable_balance() {
        return available_balance;
    }

    public void setAvailable_balance(BigDecimal available_balance) {
        this.available_balance = available_balance;
    }

    public LocalDateTime getClosing_date() {
        return closing_date;
    }

    public void setClosing_date(LocalDateTime closing_date) {
        this.closing_date = closing_date;
    }

    public Account getCuenta() {
        return account;
    }

    public void setCuenta(Account account) {
        this.account = account;
    }
}
