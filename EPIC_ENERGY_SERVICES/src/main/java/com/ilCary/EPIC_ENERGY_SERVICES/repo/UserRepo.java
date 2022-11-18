package com.ilCary.EPIC_ENERGY_SERVICES.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ilCary.EPIC_ENERGY_SERVICES.models.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

//	@Query(
//	nativeQuery = true,
//	value = "SELECT * FROM users WHERE lower(name) LIKE %:n%"
//)
//public Page<User> findByNameAndPaginate(@Param("n") String n, Pageable p);
	
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM users WHERE lower(name) LIKE %:n%"
	)
	Page<User> findByNameAndPaginate( String n, Pageable p);
	
}
