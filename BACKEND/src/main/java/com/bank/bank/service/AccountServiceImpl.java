package com.bank.bank.service;

import com.bank.bank.model.Account;
import com.bank.bank.model.Balance;
import com.bank.bank.repository.AccountRepository;
import com.bank.bank.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    BalanceRepository balanceRepository;
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account newAccount(Account account) {

        Account accountCreated = accountRepository.save(account);
        Balance balance = new Balance();

        balance.setAccount_number(Long.valueOf(account.getAccount_number()));
        balance.setAmount_pesos(BigDecimal.valueOf(0));
        balance.setAmount_dollars(BigDecimal.valueOf(0));
        balance.setOverdraft_limit(
                account.getAccount_tipe().equals("CC")? BigDecimal.valueOf(10000):BigDecimal.valueOf(0)
        );
        balanceRepository.save(balance);
        return accountCreated;
    }

    @Override
    public Iterable<Account> getAllAccounts() {
        return null;
    }

    @Override
    public Account getAccountById(Long nroCuenta) {
        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public boolean deleteAccount(Long nroCuenta) {
        Account account = accountRepository.findById(nroCuenta).orElse(null);
        if (account != null) {
            account.setActive(false);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public Mono<Account> enableAccount(Long nroCuenta) {
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
