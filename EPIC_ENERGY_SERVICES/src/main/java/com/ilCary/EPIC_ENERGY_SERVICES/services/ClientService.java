package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.ClientRepo;


@Service
public class ClientService {

    @Autowired
    private ClientRepo repository;

    public Client save(Client x) {
        return repository.save(x);
    }

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client getById(Long id) {

        Optional<Client> client = repository.findById(id);

        if(!client.isPresent())
            throw new NotFoundException("Client not available");

        return client.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
