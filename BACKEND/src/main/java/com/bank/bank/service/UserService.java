package com.bank.bank.service;

import com.bank.bank.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public interface UserService {


    Mono<User> newUser(User newUser);

    Flux<User> getAllUser();
    User getUserById(Long id) ;

    User updateUser(User userDetails);


    boolean deleteUser(Long id) ;
}