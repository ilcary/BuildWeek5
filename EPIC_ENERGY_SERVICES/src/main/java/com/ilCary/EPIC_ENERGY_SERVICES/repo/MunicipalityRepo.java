package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Municipality;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;

public interface MunicipalityRepo extends JpaRepository<Municipality, Long> {


}
