package com.ilCary.EPIC_ENERGY_SERVICES.DTO;

import java.time.LocalDate;

import com.ilCary.EPIC_ENERGY_SERVICES.models.ClientType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {

	private Long id;
	private String pec;
	private String emailContatto;
	private String telefono;
	private String partitaIva;
	private String ragioneSociale;
	private String email;
	private ClientType type;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private Double fatturatoAnnuale;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	
	private AddressDTO sedeLegale;
	private AddressDTO sedeOperativa;
	
}
