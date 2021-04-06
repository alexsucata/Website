package com.sda.website.controller;


import com.sda.website.entity.ClientEntity;
import com.sda.website.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ClientRestController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{clientId}")
    public ClientEntity getClientById(@PathVariable Integer clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    @GetMapping("/clients/name/{name}")
    public ClientEntity getClientByName(@PathVariable String name) {
        return clientRepository.getClientEntitiesByName(name);
    }

    @PostMapping("clients/add")
    public ClientEntity addNewClient(@RequestBody ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
//        ClientEntity client = getClientByName(clientEntity.getName());
//        if(client == null) {
//            return clientRepository.save(clientEntity);
//        } else {
//            return client;
//        }
    }

    @PutMapping("/clients/update/{clientId}")
    public ClientEntity updateClient(@PathVariable Integer clientId, @RequestBody ClientEntity clientEntity) {
        ClientEntity client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            return clientEntity;
        } else {
            client.setName(clientEntity.getName());
            client.setAddress(clientEntity.getAddress());
            client.setCity(clientEntity.getCity());
            client.setCountry(clientEntity.getCountry());
            client.setPhone(clientEntity.getPhone());
            return clientRepository.save(client);
        }
    }

    @DeleteMapping("/clients/delete/{clientId}")
    public String deleteClient(@PathVariable Integer clientId) {
        Optional<ClientEntity> client = clientRepository.findById(clientId);

        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return "Client: " + client.get().getName() + " was deleted";
        } else {
            return "Record not found";
        }
    }

}
