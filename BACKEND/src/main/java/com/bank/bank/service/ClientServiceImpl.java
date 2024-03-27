package com.bank.bank.service;

import com.bank.bank.model.Client;
import com.bank.bank.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long documentoCli) {
        return clientRepository.findById(documentoCli);
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(int documentoCli) {

    }
}
