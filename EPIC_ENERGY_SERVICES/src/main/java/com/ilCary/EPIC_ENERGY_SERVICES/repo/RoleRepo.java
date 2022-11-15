package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Role;
import com.ilCary.EPIC_ENERGY_SERVICES.models.RoleType;

public interface RoleRepo  extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleType(RoleType roleType);
	
}
