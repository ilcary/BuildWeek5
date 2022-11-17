package com.ilCary.EPIC_ENERGY_SERVICES.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String street;
	private String streetNum;//num civico
	private String zip;
	
	@ManyToOne
	private Municipality municipality;
	
	@ManyToOne
	@JsonManagedReference
	private Client client;
}
