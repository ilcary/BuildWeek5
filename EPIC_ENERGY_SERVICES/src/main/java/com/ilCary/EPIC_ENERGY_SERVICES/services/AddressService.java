package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Address;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.AddressRepo;

@Service
public class AddressService {

    @Autowired
    private AddressRepo repository;

    public Address save(Address x) {
        return repository.save(x);
    }

    public List<Address> getAll() {
        return repository.findAll();
    }

    public Address getById(Long id) {

        Optional<Address> address = repository.findById(id);

        if(!address.isPresent())
            throw new NotFoundException("Address not available");

        return address.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}