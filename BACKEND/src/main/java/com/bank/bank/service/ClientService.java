package com.bank.bank.service;

import com.bank.bank.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> getAllClient();

    Optional<Client> getClientById(Long documentoCli);

    Client saveClient(Client client);
    Client updateClient(Client client);
    void deleteClient(int documentoCli);

}
