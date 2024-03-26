package com.bank.bank.model;

import jakarta.persistence.*;

@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nroCuenta;
    private int documento_cli;
    private boolean activo;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "documento_cli", insertable = false, updatable = false)
    private Cliente cliente;

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public int getDocumento_cli() {
        return documento_cli;
    }

    public void setDocumento_cli(int documento_cli) {
        this.documento_cli = documento_cli;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
