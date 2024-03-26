package com.bank.bank.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date_task;
    private int employee_document;
    private String description;
    @ManyToOne
    @JoinColumn(name = "document_emp", insertable = false, updatable = false)
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate_task() {
        return date_task;
    }

    public void setDate_task(LocalDateTime date_task) {
        this.date_task = date_task;
    }

    public int getEmployee_document() {
        return employee_document;
    }

    public void setEmployee_document(int employee_document) {
        this.employee_document = employee_document;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmpleado() {
        return employee;
    }

    public void setEmpleado(Employee employee) {
        this.employee = employee;
    }
}
