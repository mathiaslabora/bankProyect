package com.bank.bank.service;

import com.bank.bank.model.Empleado;
import com.bank.bank.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EmpleadoServiceImpl implements EmpleadoService{
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Override
    public Mono<Empleado> saveEmpleado(Empleado empleado) {
        return Mono.just(empleadoRepository.save(empleado));
    }

    @Override
    public Mono<Empleado> findEmpleadoeByDocumento(int documento_em) {
        return Mono.justOrEmpty(empleadoRepository.findById(Long.valueOf(documento_em)));
    }

    @Override
    public Flux<Empleado> findAllEmpleados() {
        return Flux.fromIterable(empleadoRepository.findAll());
    }

    @Override
    public Mono<Empleado> updateEmpleado(Empleado empleado) {
        return findEmpleadoeByDocumento(empleado.getDocumentoEm())
                .flatMap(existingEmpleado -> {
                    existingEmpleado.setRol(empleado.getRol());
                    existingEmpleado.setActivo(empleado.isActivo());
                    return Mono.just(empleadoRepository.save(existingEmpleado));
                });
    }

    @Override
    public Mono<Boolean> deleteEmpleado(int documento_em) {
        return findEmpleadoeByDocumento(documento_em)
                .flatMap(existingEmployee -> {
                    empleadoRepository.delete(existingEmployee);
                    return Mono.just(true);
                })
                .defaultIfEmpty(false);
    }
}
