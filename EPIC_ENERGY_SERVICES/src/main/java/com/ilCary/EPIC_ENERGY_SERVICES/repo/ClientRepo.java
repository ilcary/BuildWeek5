package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;

public interface ClientRepo extends JpaRepository<Client, Long>{

}
