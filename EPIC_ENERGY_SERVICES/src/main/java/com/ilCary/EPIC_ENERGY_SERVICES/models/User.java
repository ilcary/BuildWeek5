package com.ilCary.EPIC_ENERGY_SERVICES.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String lastname;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(unique = true)
	private String email;
	
	@JsonIgnore
	private String password;
	private Boolean active = true;
	
	@ManyToMany
	@JoinTable( name = "users_roles" , 
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
					)
	private Set<Role> roles = new HashSet<>();
	
	public void addRole(Role r) {
		this.roles.add(r);
	}
	
}
