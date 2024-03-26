package com.bank.bank.service;

import com.bank.bank.model.Account;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface AccountService {
    Account newCuenta(Account account);
    Iterable<Account> getAllCuentas();
    Account getCuentaById(Long nroCuenta);
    Account updateCuenta(Account account);
    boolean deleteCuenta(Long nroCuenta);
    Mono<Account>enableCuenta(Long nroCuenta);

}
