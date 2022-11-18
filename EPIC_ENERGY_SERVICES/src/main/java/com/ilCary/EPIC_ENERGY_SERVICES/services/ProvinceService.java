package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Province;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.ProvinceRepo;

@Service
public class ProvinceService {
	
	@Autowired
	private ProvinceRepo repository;

	public Province save(Province x) {
		return repository.save(x);
	}

	public Page<Province> getAll(Pageable p) {
        return repository.findAll(p);
    }

	public Province getById(Long id) {
		
		Optional<Province> province = repository.findById(id);
		
		if(!province.isPresent())
			throw new NotFoundException("Province not available");
		
		return province.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Province getByNome(String nome) {
		
		Optional<Province> province = repository.findByNome(nome);
		
		if(!province.isPresent())
			throw new NotFoundException("Province not available");
		
		return province.get();
	}
	

}
