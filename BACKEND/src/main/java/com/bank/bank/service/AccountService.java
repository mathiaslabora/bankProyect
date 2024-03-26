package com.bank.bank.service;

import com.bank.bank.model.Account;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface AccountService {
    Account newAccount(Account account);
    Iterable<Account> getAllAccounts();
    Account getAccountById(Long nroCuenta);
    Account updateAccount(Account account);
    boolean deleteAccount(Long nroCuenta);
    Mono<Account> enableAccount(Long nroCuenta);

}
