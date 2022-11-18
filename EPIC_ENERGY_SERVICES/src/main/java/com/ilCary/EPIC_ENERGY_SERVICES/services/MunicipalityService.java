package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Municipality;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.MunicipalityRepo;


@Service
public class MunicipalityService {
	
	@Autowired
	private MunicipalityRepo repository;

	public Municipality save(Municipality x) {
		return repository.save(x);
	}

	public Page<Municipality> getAll(Pageable p) {
        return repository.findAll(p);
    }

	public Municipality getById(Long id) {
		
		Optional<Municipality> municipality = repository.findById(id);
		
		if(!municipality.isPresent())
			throw new NotFoundException("Municipality not available");
		
		return municipality.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
