package com.ilCary.EPIC_ENERGY_SERVICES.DTO;

import java.util.HashSet;
import java.util.Set;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

	private String name;
	private String lastname;
	private String username;
	private String email;
	private Set<Role> roles = new HashSet<>();
	
}
