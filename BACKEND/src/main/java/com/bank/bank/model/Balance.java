package com.bank.bank.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
    public class Balance {

        @Id
        @Column(name = "account_number")
        private Long account_number;


    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", insertable = false, updatable = false)
        private Account account;

        private BigDecimal amount_pesos;
        private BigDecimal amount_dollars;
        private BigDecimal overdraft_limit;




    public BigDecimal getAmount_pesos() {
        return amount_pesos;
    }

    public void setAmount_pesos(BigDecimal amount_pesos) {
        this.amount_pesos = amount_pesos;
    }

    public BigDecimal getAmount_dollars() {
        return amount_dollars;
    }

    public void setAmount_dollars(BigDecimal amount_dollars) {
        this.amount_dollars = amount_dollars;
    }

    public BigDecimal getOverdraft_limit() {
        return overdraft_limit;
    }

    public void setOverdraft_limit(BigDecimal overdraft_limit) {
        this.overdraft_limit = overdraft_limit;
    }

    public Account getCuenta() {
        return account;
    }

    public void setCuenta(Account account) {
        this.account = account;
    }


    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }
}

