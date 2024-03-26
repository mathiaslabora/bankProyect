package com.bank.bank.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
    public class Saldo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private int idSaldo;
        private BigDecimal montoPesos;
        private BigDecimal montoDolares;
        private BigDecimal limiteSobregiro;
        @ManyToOne
        @JoinColumn(name = "nro_cuenta")
        private Cuenta cuenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(int idSaldo) {
        this.idSaldo = idSaldo;
    }

    public BigDecimal getMontoPesos() {
        return montoPesos;
    }

    public void setMontoPesos(BigDecimal montoPesos) {
        this.montoPesos = montoPesos;
    }

    public BigDecimal getMontoDolares() {
        return montoDolares;
    }

    public void setMontoDolares(BigDecimal montoDolares) {
        this.montoDolares = montoDolares;
    }

    public BigDecimal getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public void setLimiteSobregiro(BigDecimal limiteSobregiro) {
        this.limiteSobregiro = limiteSobregiro;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}

