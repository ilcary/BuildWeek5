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
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;
import com.ilCary.EPIC_ENERGY_SERVICES.services.ClientService;

@RestController
@RequestMapping("/api/clients/") //TODO impostare la rotta
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;
    
    
//---------------------------- Get ---------------------------------
    

    @GetMapping
    public List<Client> getClientList() {
        return clientService.getAll();
    }

    @GetMapping("{id}")
    public Client getClientById(@PathVariable("id") Long id) {
        return clientService.getById(id);
    }
    
    
//---------------------------- Post --------------------------------


    @PostMapping
    public Client saveClient(
            @RequestParam(value="pec",required=false) String pec,
            @RequestParam(value="email",required=true) String email,
            @RequestParam(value="emailContatto",required=false) String emailContatto,
            @RequestParam(value="telefono",required=true) String telefono,
            @RequestParam(value="telefonoContatto",required=true) String telefonoContatto,
            @RequestParam(value="partitaIva",required=false) String partitaIva,
            @RequestParam(value="ragioneSociale",required=true) String ragioneSociale,
            @RequestParam(value="dataInserimento",required=true) LocalDate dataInserimento,
            @RequestParam(value="dataUltimoContatto",required=true) LocalDate dataUltimoContatto,
            @RequestParam(value="fatturatoAnnuale",required=true) Double fatturatoAnnuale,
            @RequestParam(value="nomeContatto",required=true) String nomeContatto,
            @RequestParam(value="cognomeContatto",required=true) String cognomeContatto
    ) {
    	Client client = Client.builder()
        		.pec(pec)
        		.email(email)
        		.emailContatto(emailContatto)
        		.telefono(telefono)
        		.telefonoContatto(telefonoContatto)
        		.partitaIva(partitaIva)
        		.ragioneSociale(ragioneSociale)
        		.dataInserimento(dataInserimento)
        		.dataUltimoContatto(dataUltimoContatto)
        		.fatturatoAnnuale(fatturatoAnnuale)
        		.nomeContatto(nomeContatto)
        		.cognomeContatto(cognomeContatto)
        		.build();

        logger.info("Save client in SaveController");
        return clientService.save(client);
    }


//---------------------------- Put ---------------------------------
    
    @PutMapping("{id}")
    public Client updateClient(
            @PathVariable("id") Long id,
            @RequestParam(value="pec",required=false) String pec,
            @RequestParam(value="email",required=false) String email,
            @RequestParam(value="emailContatto",required=false) String emailContatto,
            @RequestParam(value="telefono",required=false) String telefono,
            @RequestParam(value="telefonoContatto",required=false) String telefonoContatto,
            @RequestParam(value="partitaIva",required=false) String partitaIva,
            @RequestParam(value="ragioneSociale",required=false) String ragioneSociale,
            @RequestParam(value="dataInserimento",required=false) LocalDate dataInserimento,
            @RequestParam(value="dataUltimoContatto",required=false) LocalDate dataUltimoContatto,
            @RequestParam(value="fatturatoAnnuale",required=false) Double fatturatoAnnuale,
            @RequestParam(value="nomeContatto",required=false) String nomeContatto,
            @RequestParam(value="cognomeContatto",required=false) String cognomeContatto
            ) {

        Client client = clientService.getById(id);

        if(pec != null) client.setPec(pec);
        if(email != null) client.setEmail(emailContatto);
        if(emailContatto != null) client.setEmailContatto(emailContatto);
        if(telefono != null) client.setTelefono(telefono);
        if(telefonoContatto != null) client.setTelefonoContatto(telefonoContatto);
        if(partitaIva != null) client.setPartitaIva(partitaIva);
        if(ragioneSociale != null) client.setRagioneSociale(ragioneSociale);
        if(dataInserimento != null) client.setDataInserimento(dataInserimento);
        if(dataUltimoContatto != null) client.setDataUltimoContatto(dataUltimoContatto);
        if(fatturatoAnnuale != null) client.setFatturatoAnnuale(fatturatoAnnuale);
        if(nomeContatto != null) client.setNomeContatto(nomeContatto);
        if(cognomeContatto != null) client.setCognomeContatto(cognomeContatto);
        
        clientService.save(client);
        return client;
    }
    

  //---------------------------- Delete -------------------------------    


    @DeleteMapping("{id}")
    public String deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return "Client deleted successfully";
    }
    

}