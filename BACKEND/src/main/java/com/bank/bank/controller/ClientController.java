package com.bank.bank.controller;

import com.bank.bank.model.Client;
import com.bank.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
  @Autowired

  ClientService clientService;

    @GetMapping("/all")
    public Flux<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{documentoCli}")
    public Mono<Client> getClientById(@PathVariable Long documentoCli) {
        return clientService.getClientById(documentoCli);
    }

    @PostMapping("/newClient")
    public Mono<Client> saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/updateClient")
    public Mono<Client> updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{documentoCli}")
    public Mono<Client> deleteClient(@PathVariable int documentoCli) {
        return clientService.deleteClient(documentoCli);
    }
}
