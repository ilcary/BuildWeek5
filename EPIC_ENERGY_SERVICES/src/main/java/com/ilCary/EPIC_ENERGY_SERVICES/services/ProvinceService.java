package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Province;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.ProvinceRepo;

@Service
public class ProvinceService {
	
	@Autowired
	private ProvinceRepo repository;

	public Province save(Province x) {
		return repository.save(x);
	}

	public List<Province> getAll() {
		return repository.findAll();
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

}
