package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Invoice;
import com.ilCary.EPIC_ENERGY_SERVICES.services.InvoiceService;

@RestController
@RequestMapping("/api/invoices/") //TODO impostare la rotta
public class InvoiceController {

    private final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    
//---------------------------- Get ---------------------------------
    
    @GetMapping
    public List<Invoice> getInvoiceList() {
        return invoiceService.getAll();
    }

    @GetMapping("{id}")
    public Invoice getInvoiceById(@PathVariable("id") Long id) {
        return invoiceService.getById(id);
    }
    
//---------------------------- Post --------------------------------

    @PostMapping
    public Invoice saveInvoice(

            @RequestParam(value="number",required=false) Integer number,
            @RequestParam(value="amount",required=false) Double amount,
            @RequestParam(value="date",required=false) LocalDate date,
            @RequestParam(value="client",required=false) Client client
            
    ) {
        Invoice invoice = Invoice.builder().build();

        logger.info("Save Invoice in InvoiceController");
        return invoiceService.save(invoice);
    }
    
    
//---------------------------- Put ---------------------------------    

    @PutMapping("{id}")
    public Invoice updateInvoice(
            @PathVariable("id") Long id,
            @RequestParam(value="number",required=false) Integer number,
            @RequestParam(value="amount",required=false) Double amount,
            @RequestParam(value="date",required=false) LocalDate date,
            @RequestParam(value="client",required=false) Client client
            ) {

        Invoice invoice = invoiceService.getById(id);

        if(number != null) invoice.setNumber(number);
        if(amount != null) invoice.setAmount(amount);
        if(date != null) invoice.setDate(date);
        if(client != null) invoice.setClient(client);

        invoiceService.save(invoice);
        return invoice;
    }
    
//---------------------------- Delete -------------------------------

    @DeleteMapping("{id}")
    public String deleteInvoiceById(@PathVariable("id") Long id) {
        invoiceService.deleteById(id);
        return "Invoice deleted successfully";
    }
    
    
}