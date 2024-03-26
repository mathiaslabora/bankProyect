package com.bank.bank.service;

import com.bank.bank.model.Cliente;
import com.bank.bank.repository.ClienteRepository;
import org.antlr.v4.runtime.misc.LogManager;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService{

    ClienteRepository clienteRepository;
    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Long documentoCli) {
        return clienteRepository.findById(documentoCli);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(int documentoCli) {

    }
}
