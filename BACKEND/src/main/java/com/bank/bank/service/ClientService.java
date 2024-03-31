package com.bank.bank.service;

import com.bank.bank.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Flux<Client> getAllClients();

    Mono<Client> getClientById(Long documentoCli);

    Mono<Client> saveClient(Client client);

    Mono<Client> updateClient(Client client);

    Mono<Client> deleteClient(int documentoCli);

}
