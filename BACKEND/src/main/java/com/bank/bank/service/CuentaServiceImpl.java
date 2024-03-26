package com.bank.bank.service;

import com.bank.bank.model.Cuenta;
import com.bank.bank.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
@Service
public class CuentaServiceImpl implements CuentaService{
    @Autowired CuentaRepository cuentaRepository;
    @Override
    public Cuenta newCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Iterable<Cuenta> getAllCuentas() {
        return null;
    }

    @Override
    public Cuenta getCuentaById(Long nroCuenta) {
        return null;
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta) {
        return null;
    }

    @Override
    public boolean deleteCuenta(Long nroCuenta) {
        Cuenta cuenta = cuentaRepository.findById(nroCuenta).orElse(null);
        if (cuenta != null) {
            cuenta.setActivo(false);
            cuentaRepository.save(cuenta);
            return true;
        }
        return false;
    }

    @Override
    public Mono<Cuenta> enableCuenta(Long nroCuenta) {
        //Aplico reactor
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(nroCuenta);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            cuenta.setActivo(true);
            return Mono.just(cuentaRepository.save(cuenta));
        } else {
            return Mono.empty();
        }
    }
}
