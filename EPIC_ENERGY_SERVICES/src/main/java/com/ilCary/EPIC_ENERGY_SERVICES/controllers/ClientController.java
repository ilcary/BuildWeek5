package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

import com.ilCary.EPIC_ENERGY_SERVICES.models.Address;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.ClientType;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;
import com.ilCary.EPIC_ENERGY_SERVICES.services.AddressService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.ClientService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.MunicipalityService;

@RestController
@RequestMapping("/api/clients/") //TODO impostare la rotta
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private MunicipalityService municipalityService;
    
    
//---------------------------- Get ---------------------------------
    

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getClientList() {
        return clientService.getAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Client getClientById(@PathVariable("id") Long id) {
        return clientService.getById(id);
    }
    
    @GetMapping("turnover/{x1}/{x2}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getByAnnualTurnover(@PathVariable("x1") Double x1,@PathVariable("x2") Double x2) {
        return clientService.getByAnnualTurnover(x1,x2);
    }
    
    @GetMapping("registration_date/{date}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getByRegistrationDate(
    		@PathVariable("date") String date
    	) {
    	
    	String[] s = date.split("-");
    	
    	int x1 = Integer.parseInt(s[0]);
    	int x2 = Integer.parseInt(s[1]);
    	int x3 = Integer.parseInt(s[2]);
   
        return clientService.getByRegistrationDate(x1,x2,x3);
    }
    
    @GetMapping("last_contact/{date}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getByLastContactDate(
    		@PathVariable("date") String date
    	) {
    	
    	String[] s = date.split("-");
    	
    	int x1 = Integer.parseInt(s[0]);
    	int x2 = Integer.parseInt(s[1]);
    	int x3 = Integer.parseInt(s[2]);
   
        return clientService.getByLastContactDate(x1,x2,x3);
    }
    
    @GetMapping("fullname/{x1}/{x2}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getByNameAndLastname(
    		@PathVariable("x1") String x1,
    		@PathVariable("x2") String x2
    		) {
        return clientService.getByNameAndLastname(x1,x2);
    }
    
//---------------------------- Post --------------------------------


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Client saveClient(
            @RequestParam(value="pec",required=false) String pec,
            @RequestParam(value="email",required=true) String email,
            @RequestParam(value="emailContatto",required=false) String emailContatto,
            @RequestParam(value="telefono",required=true) String telefono,
            @RequestParam(value="telefonoContatto",required=true) String telefonoContatto,
            @RequestParam(value="partitaIva",required=true) String partitaIva,
            @RequestParam(value="ragioneSociale",required=true) String ragioneSociale,
            @RequestParam(value="fatturatoAnnuale",required=true) Double fatturatoAnnuale,
            @RequestParam(value="nomeContatto",required=true) String nomeContatto,
            @RequestParam(value="cognomeContatto",required=true) String cognomeContatto,
            @RequestParam(value="sedeLegaleId",required=false) Long sedeLegaleId,
            @RequestParam(value="sedeOperativaId",required=false) Long sedeOperativaId,
            @RequestParam(value="tipoCliente",required=false) ClientType tipoCliente,
       		@RequestParam(value="street",required=false) String street,
            @RequestParam(value="streetNum",required=false) String streetNum,
            @RequestParam(value="zip",required=false) String zip,
            @RequestParam(value="municipalityId",required=false) Long municipalityId,
            @RequestParam(value="sedeOperativaStreet",required=false) String sedeOperativaStreet,
            @RequestParam(value="sedeOperativaStreetNum",required=false) String sedeOperativaStreetNum,
            @RequestParam(value="sedeOperativaZip",required=false) String sedeOperativaZip,
            @RequestParam(value="sedeOperativaMunicipalityId",required=false) Long sedeOperativaMunicipalityId
    ) {
    	Client client = Client.builder()
        		.pec(pec)
        		.email(email)
        		.emailContatto(emailContatto)
        		.telefono(telefono)
        		.telefonoContatto(telefonoContatto)
        		.partitaIva(partitaIva)
        		.ragioneSociale(ragioneSociale)
        		.dataInserimento(LocalDate.now())
        		.dataUltimoContatto(randomDate())
        		.fatturatoAnnuale(fatturatoAnnuale)
        		.nomeContatto(nomeContatto)
        		.cognomeContatto(cognomeContatto)
        		.type(tipoCliente)
        		.build();
    	
    	if(sedeLegaleId == null &&(street != null) &&(streetNum != null) &&(zip != null)) {
    		
    			Address address = Address.builder()
    	        		.street(street)
    	        		.streetNum(streetNum)
    	        		.zip(zip)
    	        		.municipality(municipalityService.getById(municipalityId))
    	        		.build();
    			
    			clientService.save(client);
    		    address.setClient(client);
    		    addressService.save(address);
    		        
    			logger.info("Save Address in ClientController");
    			
    			client.setSedeLegale(address);
    		
    	}else {
    		client.setSedeLegale(addressService.getById(sedeLegaleId));
    	}
    	
    	if(sedeOperativaId == null &&(sedeOperativaStreet != null) &&(sedeOperativaStreetNum != null) &&(sedeOperativaZip != null)) {
    		
			Address address = Address.builder()
	        		.street(sedeOperativaStreet)
	        		.streetNum(sedeOperativaStreetNum)
	        		.zip(sedeOperativaZip)
	        		.municipality(municipalityService.getById(sedeOperativaMunicipalityId))
	        		.build();
			
			clientService.save(client);
		    address.setClient(client);
		    addressService.save(address);
		        
			logger.info("Save Address in ClientController");
			
			client.setSedeOperativa(address);
		
	}else {
		client.setSedeOperativa(addressService.getById(sedeOperativaId));
	}
    	
    	clientService.save(client);	
    	logger.info("Save Client in SaveController");
        
        return client;
    }


//---------------------------- Put ---------------------------------
    
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
            @RequestParam(value="cognomeContatto",required=false) String cognomeContatto,
            @RequestParam(value="tipoCliente",required=false) ClientType tipoCliente,
            @RequestParam(value="sedeLegaleId",required=false) Long sedeLegaleId,
            @RequestParam(value="sedeOperativaId",required=false) Long sedeOperativaId,
       		@RequestParam(value="street",required=false) String street,
            @RequestParam(value="streetNum",required=false) String streetNum,
            @RequestParam(value="zip",required=false) String zip,
            @RequestParam(value="municipalityId",required=false) Long municipalityId,
            @RequestParam(value="sedeOperativaStreet",required=false) String sedeOperativaStreet,
            @RequestParam(value="sedeOperativaStreetNum",required=false) String sedeOperativaStreetNum,
            @RequestParam(value="sedeOperativaZip",required=false) String sedeOperativaZip,
            @RequestParam(value="sedeOperativaMunicipalityId",required=false) Long sedeOperativaMunicipalityId
            
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
        if(tipoCliente != null) client.setType(tipoCliente);
        if(sedeLegaleId != null) client.setSedeLegale(addressService.getById(sedeLegaleId));
        if(sedeOperativaId != null) client.setSedeOperativa(addressService.getById(sedeOperativaId));
        
       	if(sedeLegaleId == null &&(street != null) &&(streetNum != null) &&(zip != null)) {
    		
			Address address = Address.builder()
	        		.street(street)
	        		.streetNum(streetNum)
	        		.zip(zip)
	        		.municipality(municipalityService.getById(municipalityId))
	        		.build();
			
			clientService.save(client);
		    address.setClient(client);
		    addressService.save(address);
		        
			logger.info("Save Address in ClientController");
			
			client.setSedeLegale(address);
	}
       	
 	if(sedeOperativaId == null &&(sedeOperativaStreet != null) &&(sedeOperativaStreetNum != null) &&(sedeOperativaZip != null)) {
    		
			Address address = Address.builder()
	        		.street(sedeOperativaStreet)
	        		.streetNum(sedeOperativaStreetNum)
	        		.zip(sedeOperativaZip)
	        		.municipality(municipalityService.getById(sedeOperativaMunicipalityId))
	        		.build();
			
			clientService.save(client);
		    address.setClient(client);
		    addressService.save(address);
		        
			logger.info("Save Address in ClientController");
			
			client.setSedeOperativa(address);
 	}
        clientService.save(client);
        return client;
    }
    

//---------------------------- Delete -------------------------------    


    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return "Client deleted successfully";
    }
 
//---------------------- Ordering ---------------------------- 
    
//    Provincia della sede legale.

    
    @GetMapping("turnover")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getAndOrderByAnnualTurnover() {
   
    	return clientService.getAll()
    			.stream()
    			.sorted(Comparator.comparing(Client::getFatturatoAnnuale))
    			.collect(Collectors.toList());
    }
    
    @GetMapping("name")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getAndOrderByName() {
   
    	return clientService.getAll()
    			.stream()
    			.sorted(Comparator.comparing(Client::getNomeContatto))
    			.collect(Collectors.toList());
    }
    
    @GetMapping("registration_date")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getAndOrderByRegistrationDate() {
   
    	return clientService.getAll()
    			.stream()
    			.sorted(Comparator.comparing(Client::getDataInserimento))
                .collect(Collectors.toList());
    }
    
    @GetMapping("last_contact")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getAndOrderByLastContact() {
   
    	return clientService.getAll()
    			.stream()
    			.sorted(Comparator.comparing(Client::getDataUltimoContatto))
                .collect(Collectors.toList());
    }
    
    @GetMapping("head_office")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Client> getAndOrderByHeadOffice() {
   
    	return clientService.getAll()
    			.stream()
//    			.sorted(Comparator.comparing(Client::getSedeLegale))
    			.sorted((i1, i2) -> i2.getSedeLegale().getMunicipality().getProvince().getSigla()
    					.compareTo(i1.getSedeLegale().getMunicipality().getProvince().getSigla()))
                .collect(Collectors.toList());
    }
    
    
    private static LocalDate randomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2021, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }
    
    
    

}