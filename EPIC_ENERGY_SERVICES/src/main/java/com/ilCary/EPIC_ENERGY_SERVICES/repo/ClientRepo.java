package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;

public interface ClientRepo extends JpaRepository<Client, Long>{

	
	List<Client> findByFatturatoAnnualeBetween(Double x1, Double x2);

	List<Client> findByDataInserimento(LocalDate d);

	List<Client> findByDataUltimoContatto(LocalDate d);
	
	List<Client> findByNomeContattoAndCognomeContattoContaining(String name, String lastname);
	
}
