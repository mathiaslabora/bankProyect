package com.bank.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
    @Id
    private int document_emp;
    private String role;
    private boolean active;
    @OneToOne
    @JoinColumn(name = "document_emp")
    private User user;

    public int getDocument_emp() {
        return document_emp;
    }

    public void setDocument_emp(int document_emp) {
        this.document_emp = document_emp;
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
