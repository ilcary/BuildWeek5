package com.ilCary.EPIC_ENERGY_SERVICES.DTO;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Municipality;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

	private String street;
	private String streetNum;//num civico
	private String zip;
	private Municipality municipality;
	private ClientDTO clientDTO;
}
