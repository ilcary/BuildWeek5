package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Province;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;

public interface ProvinceRepo extends JpaRepository<Province, Long> {

	Optional<Province> findByNome(String nome);


}
