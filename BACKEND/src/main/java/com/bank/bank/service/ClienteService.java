package com.bank.bank.service;

import com.bank.bank.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> getAllClientes();

    Optional<Cliente> getClienteById(Long documentoCli);

    Cliente saveCliente(Cliente cliente);
    Cliente updateCliente(Cliente cliente);
    void deleteCliente(int documentoCli);

}
