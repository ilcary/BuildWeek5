package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Invoice;
import com.ilCary.EPIC_ENERGY_SERVICES.models.InvoiceStatus;

public interface InvoiceRepo extends JpaRepository<Invoice, Long>{

	List<Invoice> findByClient(Client client);
	
	List<Invoice> findByStatus(InvoiceStatus status);
	
	List<Invoice> findByDateBetween(LocalDate start, LocalDate end);
	
	List<Invoice> findByYear(int year);
	
	List<Invoice> findByAmountBetween(Double x1, Double x2);
	
}
