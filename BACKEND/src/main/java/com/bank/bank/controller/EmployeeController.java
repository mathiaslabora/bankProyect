package com.bank.bank.controller;

import com.bank.bank.model.Employee;
import com.bank.bank.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/allEmp")
    public Flux<Employee> getAllEmployees() {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/{documento_em}")
    public Mono<Employee> getEmployeeByDocument(@PathVariable int documento_em) {
        return employeeService.findEmployeeByDocument(documento_em);
    }

    @PostMapping("/newEmp")
    public Mono<Employee> saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("updateEmployee")
    public Mono<Employee> updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{documento_em}")
    public Mono<Boolean> deleteEmployee(@PathVariable int documento_em) {
        return employeeService.deleteEmployee(documento_em);
    }
}
