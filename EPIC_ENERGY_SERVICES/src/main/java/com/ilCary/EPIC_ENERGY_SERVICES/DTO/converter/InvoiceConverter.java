package com.ilCary.EPIC_ENERGY_SERVICES.DTO.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.ilCary.EPIC_ENERGY_SERVICES.DTO.InvoiceDTO;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Invoice;

public class InvoiceConverter {

	public InvoiceDTO entityToDTO(Invoice invoice) {
		
		ModelMapper mapper =new ModelMapper();
		InvoiceDTO map = mapper.map(invoice, InvoiceDTO.class);
		return map;
		
	}
	public  List<InvoiceDTO> entityToDTO(List<Invoice> invoice) {
		
		return	invoice.stream().map(x -> entityToDTO(x)).collect(Collectors.toList());
		
	}
	
	
	public Invoice dtoToEntity(InvoiceDTO dto)
	{
	
		ModelMapper mapper = new ModelMapper();
		Invoice map = mapper.map(dto, Invoice.class);
		return map;
	}
	
	public List<Invoice> dtoToEntity(List<InvoiceDTO> dto)
	{

		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
