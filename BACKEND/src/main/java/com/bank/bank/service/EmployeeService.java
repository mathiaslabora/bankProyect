package com.bank.bank.service;

import com.bank.bank.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> saveEmpleado(Employee employee);

    Mono<Employee> findEmpleadoeByDocumento(int documento_em);

    Flux<Employee> findAllEmpleados();

    Mono<Employee> updateEmpleado(Employee employee);

    Mono<Boolean> deleteEmpleado(int documento_em);
}
