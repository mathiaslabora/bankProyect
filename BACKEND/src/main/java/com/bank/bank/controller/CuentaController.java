package com.bank.bank.controller;


import com.bank.bank.model.Cuenta;
import com.bank.bank.model.Usuario;
import com.bank.bank.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Cuenta")
public class CuentaController {
    @Autowired
    CuentaService cuentaService;

    @PostMapping("/new")
    public Cuenta newCuenta(@RequestBody Cuenta newCuenta){
        return this.cuentaService.newCuenta(newCuenta);
    }



}
