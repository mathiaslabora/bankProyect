package com.bank.bank.service;

import com.bank.bank.model.Account;
import com.bank.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account newCuenta(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Iterable<Account> getAllCuentas() {
        return null;
    }

    @Override
    public Account getCuentaById(Long nroCuenta) {
        return null;
    }

    @Override
    public Account updateCuenta(Account account) {
        return null;
    }

    @Override
    public boolean deleteCuenta(Long nroCuenta) {
        Account account = accountRepository.findById(nroCuenta).orElse(null);
        if (account != null) {
            account.setActive(false);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public Mono<Account> enableCuenta(Long nroCuenta) {
        //Aplico reactor
        Optional<Account> optionalCuenta = accountRepository.findById(nroCuenta);
        if (optionalCuenta.isPresent()) {
            Account account = optionalCuenta.get();
            account.setActive(true);
            return Mono.just(accountRepository.save(account));
        } else {
            return Mono.empty();
        }
    }
}
