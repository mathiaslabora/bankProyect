package com.bank.bank.service;

import com.bank.bank.model.Balance;
import com.bank.bank.repository.BalanceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BalanceServiceImpl implements BalanceService{
    BalanceRepository balanceRepository;
    @Override
    public Flux<Balance> getAllBalances() {
        return Flux.fromIterable(balanceRepository.findAll());
    }

    @Override
    public Mono<Balance> getBalanceById(int balanceId) {
        return Mono.justOrEmpty(balanceRepository.findById(Long.valueOf(balanceId)));
    }

    @Override
    public Mono<Balance> newBalance(Balance balance) {
        return Mono.just(balanceRepository.save(balance));
    }

    @Override
    public Mono<Balance> updateBalance(Balance balance) {
        return Mono.just(balanceRepository.save(balance));
    }

    @Override
    public Mono<Boolean> deleteBalance(int balanceId) {
        return Mono.fromCallable(() -> {
            balanceRepository.deleteById(Long.valueOf(balanceId));
            return true;
        });
    }
}
