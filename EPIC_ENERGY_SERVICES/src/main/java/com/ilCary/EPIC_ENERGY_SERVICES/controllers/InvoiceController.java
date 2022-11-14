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

import com.ilCary.EPIC_ENERGY_SERVICES.models.Invoice;
import com.ilCary.EPIC_ENERGY_SERVICES.services.InvoiceService;

@RestController
@RequestMapping("/api/invoices/") //TODO impostare la rotta
public class InvoiceController {

    private final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public Invoice saveInvoice(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        Invoice invoice = Invoice.builder().build();

        logger.info("Save Invoice in InvoiceController");
        return invoiceService.save(invoice);
    }

    @GetMapping
    public List<Invoice> getInvoiceList() {
        return invoiceService.getAll();
    }

    @GetMapping("{id}")
    public Invoice getInvoiceById(@PathVariable("id") Long id) {
        return invoiceService.getById(id);
    }

    @DeleteMapping("{id}")
    public String deleteInvoiceById(@PathVariable("id") Long id) {
        invoiceService.deleteById(id);
        return "Invoice deleted successfully";
    }

    @PutMapping("{id}")
    public Invoice updateInvoice(
            @PathVariable("id") Long id
//            @RequestParam("name") String name
            ) {

        Invoice invoice = invoiceService.getById(id);

        //TODO gestire il put

        invoiceService.save(invoice);
        return invoice;
    }

}