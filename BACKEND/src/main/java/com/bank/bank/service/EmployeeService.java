package com.bank.bank.service;

import com.bank.bank.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> saveEmployee(Employee employee);

    Mono<Employee> findEmployeeByDocument(int documento_em);

    Flux<Employee> findAllEmployee();

    Mono<Employee> updateEmployee(Employee employee);

    Mono<Boolean> deleteEmployee(int documento_em);
}
