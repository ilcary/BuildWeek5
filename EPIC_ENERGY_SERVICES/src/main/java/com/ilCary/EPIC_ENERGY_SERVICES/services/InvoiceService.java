package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Invoice;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.InvoiceRepo;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo repository;

    public Invoice save(Invoice x) {
        return repository.save(x);
    }

    public List<Invoice> getAll() {
        return repository.findAll();
    }

    public Invoice getById(Long id) {

        Optional<Invoice> invoice = repository.findById(id);

        if(!invoice.isPresent())
            throw new NotFoundException("Invoice not available");

        return invoice.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}

