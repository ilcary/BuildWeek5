package com.ilCary.EPIC_ENERGY_SERVICES.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ilCary.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Role;
import com.ilCary.EPIC_ENERGY_SERVICES.models.RoleType;
import com.ilCary.EPIC_ENERGY_SERVICES.repo.RoleRepo;


@Service
public class RoleService {

    @Autowired
    private RoleRepo repository;

    public Role save(Role x) {
        return repository.save(x);
    }

    public Page<Role> getAll(Pageable p) {
        return repository.findAll(p);
    }

    public Role getById(Long id) {

        Optional<Role> role = repository.findById(id);

        if(!role.isPresent())
            throw new NotFoundException("Role not available");

        return role.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    public Role getByRole(RoleType roleType) {		
		Optional<Role> ba = repository.findByRoleType(roleType);
		if (!ba.isPresent())
			throw new NotFoundException("Role not available");
		return ba.get();
	}

}
