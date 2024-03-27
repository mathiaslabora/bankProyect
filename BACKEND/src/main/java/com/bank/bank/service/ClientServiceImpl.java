package com.bank.bank.service;

import com.bank.bank.model.Client;
import com.bank.bank.repository.ClientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Flux<Client> getAllClients() {
        return Flux.fromIterable(clientRepository.findAll());
    }

    @Override
    public Mono<Client> getClientById(Long documentoCli) {
        return Mono.justOrEmpty(clientRepository.findById(documentoCli));
    }

    @Override
    public Mono<Client> saveClient(Client client) {
        return Mono.just(clientRepository.save(client));
    }

    @Override
    public Mono<Client> updateClient(Client client) {
        return Mono.just(clientRepository.save(client));
    }

    @Override
    public Mono<Client> deleteClient(int documentoCli) {

        return Mono.just(clientRepository.findById(Long.valueOf(documentoCli)))
                .flatMap(clientOptional -> {
                    if (clientOptional.isPresent()) {
                        Client client = clientOptional.get();
                        if (client.isActive()) {
                            client.setActive(false);
                            clientRepository.save(client);
                            return Mono.just(client);
                        } else {
                            return Mono.error(new RuntimeException("Cliente no encontrado"));
                        }
                    } else {
                        return Mono.error(new RuntimeException("Cliente no encontrado"));
                    }
                });

    }
}