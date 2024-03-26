package com.bank.bank.service;

import com.bank.bank.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public interface UserService {


    Mono<User> newUsuario(User newUser);

    Flux<User> getAllUsuarios();
    User getUsuarioById(Long id) ;

    User updateUsuario(User userDetails);


    boolean deleteUsuario(Long id) ;
}