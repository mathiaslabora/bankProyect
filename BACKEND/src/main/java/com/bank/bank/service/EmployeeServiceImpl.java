package com.bank.bank.service;

import com.bank.bank.model.Employee;
import com.bank.bank.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Mono<Employee> saveEmployee(Employee employee) {
        return Mono.just(employeeRepository.save(employee));
    }

    @Override
    public Mono<Employee> findEmployeeByDocument(int documento_em) {
        return Mono.justOrEmpty(employeeRepository.findById(Long.valueOf(documento_em)));
    }

    @Override
    public Flux<Employee> findAllEmployee() {
        return Flux.fromIterable(employeeRepository.findAll());
    }

    @Override
    public Mono<Employee> updateEmployee(Employee employee) {
        return findEmployeeByDocument(employee.getDocument_emp())
                .flatMap(existingEmpleado -> {
                    existingEmpleado.setRole(employee.getRole());
                    existingEmpleado.setActive(employee.isActive());
                    return Mono.just(employeeRepository.save(existingEmpleado));
                });
    }

    @Override
    public Mono<Boolean> deleteEmployee(int documento_em) {
        return findEmployeeByDocument(documento_em)
                .flatMap(existingEmployee -> {
                    existingEmployee.setActive(false);
                    employeeRepository.save(existingEmployee);
                    return Mono.just(true);
                })
                .defaultIfEmpty(false);
    }
}
