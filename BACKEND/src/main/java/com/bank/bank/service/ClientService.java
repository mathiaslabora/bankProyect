package com.bank.bank.service;

import com.bank.bank.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> getAllClientes();

    Optional<Client> getClienteById(Long documentoCli);

    Client saveCliente(Client client);
    Client updateCliente(Client client);
    void deleteCliente(int documentoCli);

}
