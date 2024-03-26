package com.bank.bank.service;

import com.bank.bank.model.Empleado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmpleadoService {
    Mono<Empleado> saveEmpleado(Empleado empleado);

    Mono<Empleado> findEmpleadoeByDocumento(int documento_em);

    Flux<Empleado> findAllEmpleados();

    Mono<Empleado> updateEmpleado(Empleado empleado);

    Mono<Boolean> deleteEmpleado(int documento_em);
}
