package com.bank.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Empleado {
    @Id
    private int documentoEm;
    private String rol;
    private boolean activo;
    @OneToOne
    @JoinColumn(name = "documento_em")
    private Usuario usuario;

    public int getDocumentoEm() {
        return documentoEm;
    }

    public void setDocumentoEm(int documentoEm) {
        this.documentoEm = documentoEm;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
