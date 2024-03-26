package com.bank.bank.service;

import com.bank.bank.model.Cuenta;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface CuentaService {
    Cuenta newCuenta(Cuenta cuenta);
    Iterable<Cuenta> getAllCuentas();
    Cuenta getCuentaById(Long nroCuenta);
    Cuenta updateCuenta(Cuenta cuenta);
    boolean deleteCuenta(Long nroCuenta);
    Mono<Cuenta>enableCuenta(Long nroCuenta);

}
