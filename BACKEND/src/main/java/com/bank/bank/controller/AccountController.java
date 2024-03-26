package com.bank.bank.controller;


import com.bank.bank.model.Account;
import com.bank.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/new")
    public Account newCuenta(@RequestBody Account newAccount){
        return this.accountService.newCuenta(newAccount);
    }



}
