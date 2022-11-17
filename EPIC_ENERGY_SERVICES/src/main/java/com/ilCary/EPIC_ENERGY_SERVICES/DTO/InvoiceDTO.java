package com.ilCary.EPIC_ENERGY_SERVICES.DTO;

import java.time.LocalDate;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.InvoiceStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceDTO {

	 	private Long id;
		private Double amount;
		private LocalDate date;
		private int year;
		private InvoiceStatus status;
		private Client client;
}
