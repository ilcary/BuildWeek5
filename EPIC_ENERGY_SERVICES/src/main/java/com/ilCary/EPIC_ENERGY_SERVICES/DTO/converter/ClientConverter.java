package com.ilCary.EPIC_ENERGY_SERVICES.DTO.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ilCary.EPIC_ENERGY_SERVICES.DTO.ClientDTO;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;

@Component
public class ClientConverter {

	public ClientDTO entityToDto(Client client) {

//		return new ClientDTO().builder()
//				.id(client.getId())
//				.pec(client.getPec())
//				.emailContatto(client.getEmailContatto())
//				.telefono(client.getTelefono())
//				.partitaIva(client.getPartitaIva())
//				.ragioneSociale(client.getRagioneSociale())
//				.email(client.getEmail())
//				.dataInserimento(client.getDataInserimento())
//				.dataUltimoContatto(client.getDataUltimoContatto())
//				.fatturatoAnnuale(client.getFatturatoAnnuale())
//				.nomeContatto(client.getNomeContatto())
//				.cognomeContatto(client.getCognomeContatto())
//				.telefonoContatto(client.getTelefonoContatto())
//				.type(client.getType())
//				.build();
		
//ModelMapper è una dipendenza, è una libreria top per rimappare gli oggetti :)
//https://www.baeldung.com/java-modelmapper <3
		
		ModelMapper mapper = new ModelMapper();
		ClientDTO map = mapper.map(client, ClientDTO.class);
		return map;
		
	}
	
	public  List<ClientDTO> entityToDto(List<Client> student) {
		
		return	student.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
		
	}
	
	public Client dtoToEntity(ClientDTO dto) {
		
		ModelMapper mapper = new ModelMapper();
		Client map = mapper.map(dto, Client.class);
		return map;
		
	}
	
}
