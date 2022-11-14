package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilCary.EPIC_ENERGY_SERVICES.models.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
