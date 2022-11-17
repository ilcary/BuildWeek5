package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;
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
    
 // --------------------------- Filtering --------------------------

    public List<Client> getByAnnualTurnover(Double x1, Double x2) {
        return repository.findByFatturatoAnnualeBetween(x1,x2);
    }
    
    public List<Client> getByRegistrationDate(int x1,int x2, int x3) {
        return repository.findByDataInserimento(LocalDate.of(x1, x2, x3));
    }
    
    public List<Client> getByLastContactDate(int x1,int x2, int x3) {
        return repository.findByDataUltimoContatto(LocalDate.of(x1, x2, x3));
    }
    
    public List<Client> getByNameAndLastname(String string1, String string2) {
        return repository.findByNomeContattoAndCognomeContattoContaining(string1,string2);
    }
    

}
