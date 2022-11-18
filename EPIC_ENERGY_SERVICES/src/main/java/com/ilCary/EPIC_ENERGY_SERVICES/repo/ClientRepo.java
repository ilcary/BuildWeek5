package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;

public interface ClientRepo extends JpaRepository<Client, Long>{

	
	List<Client> findByFatturatoAnnualeBetween(Double x1, Double x2);

	List<Client> findByDataInserimento(LocalDate d);

	List<Client> findByDataUltimoContatto(LocalDate d);
	
	List<Client> findByNomeContattoAndCognomeContattoContaining(String name, String lastname);

	@Query(
			nativeQuery = true,
			value = "SELECT * FROM clients ORDER BY nome_contatto ASC"
	)
	Page<Client> findAllOrderByNomeContatto(Pageable p);

	@Query(
			nativeQuery = true,
			value = "SELECT * FROM clients ORDER BY fatturato_annuale ASC"
	)
	Page<Client> findAllOrderByFatturatoAnnuale(Pageable p);
	
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM clients ORDER BY data_inserimento ASC"
	)
	Page<Client> findAllOrderByDataInserimento(Pageable p);
	
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM clients ORDER BY data_ultimo_contatto ASC"
	)
	Page<Client> findAllOrderByDataUltimoContatto(Pageable p);
}
