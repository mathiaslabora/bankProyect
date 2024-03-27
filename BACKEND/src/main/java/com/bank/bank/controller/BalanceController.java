package com.bank.bank.controller;

import com.bank.bank.model.Balance;
import com.bank.bank.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("/getAllBalances")
    public Flux<Balance> getAllBalances() {
        return balanceService.getAllBalances();
    }

    @GetMapping("/getBalance/{balanceId}")
    public Mono<Balance> getBalanceById(@PathVariable int balanceId) {
        return balanceService.getBalanceById(balanceId);
    }

    @PostMapping("/newBalance")
    public Mono<Balance> newBalance(@RequestBody Balance balance) {
        return balanceService.newBalance(balance);
    }

    @PutMapping("/updateBalance")
    public Mono<Balance> updateBalance(@RequestBody Balance balance) {
        return balanceService.updateBalance(balance);
    }

    @DeleteMapping("/deleteBalance/{balanceId}")
    public Mono<Boolean> deleteBalance(@PathVariable int balanceId) {
        return balanceService.deleteBalance(balanceId);
    }

}
