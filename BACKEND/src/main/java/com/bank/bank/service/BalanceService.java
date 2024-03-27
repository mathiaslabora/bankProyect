package com.bank.bank.service;

import com.bank.bank.model.Balance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BalanceService {
    Flux<Balance> getAllBalances();

    Mono<Balance> getBalanceById(int balanceId);

    Mono<Balance> newBalance(Balance balance);

    Mono<Balance> updateBalance(Balance balance);

    Mono<Boolean> deleteBalance(int balanceId);
}