package com.bank.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {
    @Id
    private int documentoCli;
    private String tipoCli;
    private boolean activo;
    @OneToOne
    @JoinColumn(name = "documento_cli")
    private Usuario usuario;

    public int getDocumentoCli() {
        return documentoCli;
    }

    public void setDocumentoCli(int documentoCli) {
        this.documentoCli = documentoCli;
    }

    public String getTipoCli() {
        return tipoCli;
    }

    public void setTipoCli(String tipoCli) {
        this.tipoCli = tipoCli;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
