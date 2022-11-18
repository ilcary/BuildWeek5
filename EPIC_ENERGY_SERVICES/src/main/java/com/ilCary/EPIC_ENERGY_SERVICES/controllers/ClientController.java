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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ilCary.EPIC_ENERGY_SERVICES.DTO.ClientDTO;
import com.ilCary.EPIC_ENERGY_SERVICES.DTO.converter.AddressConverter;
import com.ilCary.EPIC_ENERGY_SERVICES.DTO.converter.ClientConverter;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Address;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.ClientType;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;
import com.ilCary.EPIC_ENERGY_SERVICES.services.AddressService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.ClientService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.MunicipalityService;

@RestController
@RequestMapping("/api/clients/") // TODO impostare la rotta
//@CrossOrigin(origins = "*") 
public class ClientController {

	private final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private MunicipalityService municipalityService;

	@Autowired
	private ClientConverter clientConverter;

	@Autowired
	private AddressConverter addressConverter;

//---------------------------- Get ---------------------------------

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Page<Client>> getClientList(Pageable p) {

		Page<Client> res = clientService.getAll(p);

		if (res.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}

	@GetMapping("{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Client getClientById(@PathVariable("id") Long id) {
		return clientService.getById(id);
	}

	@GetMapping("turnover/{x1}/{x2}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<Client> getByAnnualTurnover(@PathVariable("x1") Double x1, @PathVariable("x2") Double x2) {
		return clientService.getByAnnualTurnover(x1, x2);
	}

	@GetMapping("registration_date/{date}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<Client> getByRegistrationDate(@PathVariable("date") String date) {

		String[] s = date.split("-");

		int x1 = Integer.parseInt(s[0]);
		int x2 = Integer.parseInt(s[1]);
		int x3 = Integer.parseInt(s[2]);

		return clientService.getByRegistrationDate(x1, x2, x3);
	}

	@GetMapping("last_contact/{date}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<Client> getByLastContactDate(@PathVariable("date") String date) {

		String[] s = date.split("-");

		int x1 = Integer.parseInt(s[0]);
		int x2 = Integer.parseInt(s[1]);
		int x3 = Integer.parseInt(s[2]);

		return clientService.getByLastContactDate(x1, x2, x3);
	}

	@GetMapping("fullname/{x1}/{x2}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<Client> getByNameAndLastname(@PathVariable("x1") String x1, @PathVariable("x2") String x2) {
		return clientService.getByNameAndLastname(x1, x2);
	}

//---------------------------- Post --------------------------------

 

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public Client saveClient(@RequestBody ClientDTO dto) {
		Client client = clientConverter.dtoToEntity(dto);
		client.setDataInserimento(LocalDate.now());
		Address sedeLegale = addressConverter.dtoToEntity(dto.getSedeLegale());
		Address sedeOperativa = addressConverter.dtoToEntity(dto.getSedeOperativa());

		boolean bLegale = false;
		boolean bOperativa = false;

		if (sedeLegale.getId() == null && (sedeLegale.getStreet() != null) && (sedeLegale.getStreetNum() != null)
				&& (sedeLegale.getZip() != null)) {

			client.setSedeLegale(sedeLegale);
			sedeLegale.setClient(client);

			bLegale = true;

			// logger.info("Save Address in ClientController");

		} else {
			client.setSedeLegale(addressService.getById(sedeLegale.getId()));
		}

		if (sedeOperativa.getId() == null && (sedeOperativa.getStreet() != null)
				&& (sedeOperativa.getStreetNum() != null) && (sedeOperativa.getZip() != null)) {

			client.setSedeOperativa(sedeOperativa);
			sedeOperativa.setClient(client);

			bLegale = true;

			// logger.info("Save Address in ClientController");

		} else {
			client.setSedeOperativa(addressService.getById(sedeOperativa.getId()));
		}

		clientService.save(client);

		if (bOperativa) {
			addressService.save(sedeOperativa);
		}
		if (bLegale) {
			addressService.save(sedeLegale);
		}

		logger.info("Save Client in SaveController");

		return client;
	}

//---------------------------- Put ---------------------------------

	@PutMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Client updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO dto
	) {

		
		Client client = clientService.getById(id);
		Client updateClient = clientConverter.dtoToEntity(dto);
		
		Address sedeLegale = new Address();
		if (dto.getSedeLegale()!= null) {
			sedeLegale = addressConverter.dtoToEntity(dto.getSedeLegale());
		}
		
		Address sedeOperativa = new Address();
		if (dto.getSedeOperativa()!= null) {
			sedeOperativa = addressConverter.dtoToEntity(dto.getSedeOperativa());
		}
		

		if (updateClient.getPec() != null)
			client.setPec(updateClient.getPec());
		if (updateClient.getEmail() != null)
			client.setEmail(updateClient.getEmail());
		if (updateClient.getEmailContatto() != null)
			client.setEmailContatto(updateClient.getEmailContatto());
		if (updateClient.getTelefono() != null)
			client.setTelefono(updateClient.getTelefono());
		if (updateClient.getTelefonoContatto() != null)
			client.setTelefonoContatto(updateClient.getTelefonoContatto());
		if (updateClient.getPartitaIva() != null)
			client.setPartitaIva(updateClient.getPartitaIva());
		if (updateClient.getRagioneSociale() != null)
			client.setRagioneSociale(updateClient.getRagioneSociale());
		if (updateClient.getDataUltimoContatto() != null)
			client.setDataUltimoContatto(updateClient.getDataUltimoContatto());
		if (updateClient.getFatturatoAnnuale() != null)
			client.setFatturatoAnnuale(updateClient.getFatturatoAnnuale());
		if (updateClient.getNomeContatto() != null)
			client.setNomeContatto(updateClient.getNomeContatto());
		if (updateClient.getCognomeContatto() != null)
			client.setCognomeContatto(updateClient.getCognomeContatto());
		if (updateClient.getType() != null)
			client.setType(updateClient.getType());
		
		// valori booleani che ci permettono di decidere se salvare
		boolean bLegale = false;
		boolean bOperativa = false;

		if (sedeLegale.getId() == null && (sedeLegale.getStreet() != null) && (sedeLegale.getStreetNum() != null)
				&& (sedeLegale.getZip() != null)) {

			client.setSedeLegale(sedeLegale);
			sedeLegale.setClient(client);

			bLegale = true;

			// logger.info("Save Address in ClientController");

		} else if(sedeLegale.getId() != null){
			client.setSedeLegale(addressService.getById(sedeLegale.getId()));
		}
		

		if (sedeOperativa.getId() == null && (sedeOperativa.getStreet() != null)
				&& (sedeOperativa.getStreetNum() != null) && (sedeOperativa.getZip() != null)) {

			client.setSedeOperativa(sedeOperativa);
			sedeOperativa.setClient(client);

			bLegale = true;

			// logger.info("Save Address in ClientController");

		} else if (sedeOperativa.getId() != null){
			client.setSedeOperativa(addressService.getById(sedeOperativa.getId()));
		}

		clientService.save(client);

		if (bOperativa) {
			//addressService.save(sedeOperativa);
		}
		if (bLegale) {
			//addressService.save(sedeLegale);
		}

		logger.info("Save Client in SaveController");

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

	@GetMapping("turnover")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Page<Client>> getAndOrderByAnnualTurnover(Pageable p) {

		Page<Client> res = clientService.orderByAnnualTurnover(p);

		if (res.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

	}

	@GetMapping("name")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Page<Client>> getAndOrderByName(Pageable p) {

		Page<Client> res = clientService.orderByContactName(p);

		if (res.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}

	@GetMapping("registration_date")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Page<Client>> getAndOrderByRegistrationDate(Pageable p) {

		Page<Client> res = clientService.orderByRegistrationDate(p);

		if (res.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}

	@GetMapping("last_contact")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Page<Client>> getAndOrderByLastContact(Pageable p) {

		Page<Client> res = clientService.orderByLastContactDate(p);

		if (res.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}

	@GetMapping("head_office")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public List<Client> getAndOrderByHeadOffice(Pageable p) {

		return clientService.getAll(p).stream()
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