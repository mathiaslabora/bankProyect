package com.bank.bank.controller;


import com.bank.bank.model.Account;
import com.bank.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/new")
    public Account newAccount(@RequestBody Account newAccount){
        return this.accountService.newAccount(newAccount);
    }


    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.newAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        Iterable<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{numAccount}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long numAccount) {
        Account account = accountService.getAccountById(numAccount);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{nroCuenta}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long nroCuenta, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(account);
        if (updatedAccount != null) {
            return ResponseEntity.ok(updatedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{numAccount}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long numAccount) {
        boolean deleted = accountService.deleteAccount(numAccount);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{numAccount}/enable")
    public Mono<ResponseEntity<Account>> enableAccount(@PathVariable Long numAccount) {
        return accountService.enableAccount(numAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }



}
