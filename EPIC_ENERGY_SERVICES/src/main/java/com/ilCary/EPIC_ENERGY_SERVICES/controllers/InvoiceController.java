package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ilCary.EPIC_ENERGY_SERVICES.models.InvoiceStatus;
import com.ilCary.EPIC_ENERGY_SERVICES.services.ClientService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.InvoiceService;

@RestController
@RequestMapping("/api/invoices/") //TODO impostare la rotta
public class InvoiceController {

    private final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private ClientService clientService;

    
//---------------------------- Get ---------------------------------
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Invoice> getInvoiceList() {
        return invoiceService.getAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Invoice getInvoiceById(@PathVariable("id") Long id) {
        return invoiceService.getById(id);
    }
    
    @GetMapping("client/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Invoice> getInvoiceListByClient(@PathVariable("id") Long clientId) {
    	Client client = clientService.getById(clientId);
        return invoiceService.getByClient(client);
    }
    
    @GetMapping("status/{status}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Invoice> getInvoiceListByStatus(@PathVariable("status") InvoiceStatus status) {
        return invoiceService.getByStatus(status);
    }
    
    @GetMapping("find_between/{start}/{end}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Invoice> getByLastContactDate(
    		@PathVariable("start") String start,
    		@PathVariable("end") String end
    	) {
    	String[] s1 = start.split("-");
    	String[] s2 = end.split("-");
    	
    	int x1 = Integer.parseInt(s1[0]);
    	int x2 = Integer.parseInt(s1[1]);
    	int x3 = Integer.parseInt(s1[2]);
    	
    	int y1 = Integer.parseInt(s2[0]);
    	int y2 = Integer.parseInt(s2[1]);
    	int y3 = Integer.parseInt(s2[2]);
    	
    	LocalDate d1 = LocalDate.of(x1, x2, x3);
    	LocalDate d2 = LocalDate.of(y1, y2, y3);
    	
        return invoiceService.getByDateInRange(d1, d2);
    }
    
    @GetMapping("year/{year}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Invoice> getInvoiceListByYear(@PathVariable("year") int year) {
        return invoiceService.getByYear(year);
    }
    
    @GetMapping("amount/{x1}/{x2}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Invoice> getByAmountInRange(@PathVariable("x1") Double x1,@PathVariable("x2") Double x2) {
        return invoiceService.getByAmountInRange(x1,x2);
    }
    
//---------------------------- Post --------------------------------

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Invoice saveInvoice(
            @RequestParam(value="amount",required=true) Double amount,
            @RequestParam(value="date",required=true) String date,
            @RequestParam(value="clientId",required=true) Long clientId,
            @RequestParam(value="status",required=true) InvoiceStatus status
            
    ) {
    	
    	String[] s = date.split("-");
    	LocalDate d = LocalDate.of(
    			Integer.parseInt(s[0]),
    			Integer.parseInt(s[1]),
    			Integer.parseInt(s[2])
    			);
    	
        Invoice invoice = Invoice.builder()
        		.amount(amount)
        		.date(d)
        		.year(d.getYear())
        		.client(clientService.getById(clientId))
        		.status(status)
        		.build();

        logger.info("Save Invoice in InvoiceController");
        return invoiceService.save(invoice);
    }
    
    
//---------------------------- Put ---------------------------------    

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Invoice updateInvoice(
            @PathVariable("id") Long id,
            @RequestParam(value="amount",required=false) Double amount,
            @RequestParam(value="date",required=false) LocalDate date,
            @RequestParam(value="client",required=false) Client client
            ) {

        Invoice invoice = invoiceService.getById(id);

        if(amount != null) invoice.setAmount(amount);
        if(date != null) invoice.setDate(date);
        if(client != null) invoice.setClient(client);

        invoiceService.save(invoice);
        return invoice;
    }
    
//---------------------------- Delete -------------------------------

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteInvoiceById(@PathVariable("id") Long id) {
        invoiceService.deleteById(id);
        return "Invoice deleted successfully";
    }
    
    
}