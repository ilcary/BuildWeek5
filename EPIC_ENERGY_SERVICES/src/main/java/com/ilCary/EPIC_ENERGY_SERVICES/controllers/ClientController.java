package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.services.ClientService;

@RestController
@RequestMapping("/api/clients/") //TODO impostare la rotta
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @PostMapping
    public Client saveClient(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        Client client = Client.builder().build();

        logger.info("Save Client in ClientController");
        return clientService.save(client);
    }

    @GetMapping
    public List<Client> getClientList() {
        return clientService.getAll();
    }

    @GetMapping("{id}")
    public Client getClientById(@PathVariable("id") Long id) {
        return clientService.getById(id);
    }

    @DeleteMapping("{id}")
    public String deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return "Client deleted successfully";
    }

    @PutMapping("{id}")
    public Client updateClient(
            @PathVariable("id") Long id
//            @RequestParam("name") String name
            ) {

        Client client = clientService.getById(id);

        //TODO gestire il put

        clientService.save(client);
        return client;
    }

}