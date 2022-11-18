package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.AddressAlreadyExistingException;
import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Address;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.AddressRepo;

@Service
public class AddressService {

	@Autowired
	private AddressRepo repository;

	public Address save(Address x) {

		List<Address> list = repository.findAll();

		for (Address address : list) {

			if (x.getStreet().equals( address.getStreet()) && x.getStreetNum().equals(address.getStreetNum()) 
					&& x.getMunicipality().getId() == address.getMunicipality().getId())
				throw new AddressAlreadyExistingException("Address already existing");
		}

		return repository.save(x);
	}

	public Page<Address> getAll(Pageable p) {
        return repository.findAll(p);
    }

	public Address getById(Long id) {

		Optional<Address> address = repository.findById(id);

		if (!address.isPresent())
			throw new NotFoundException("Address not available");

		return address.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}