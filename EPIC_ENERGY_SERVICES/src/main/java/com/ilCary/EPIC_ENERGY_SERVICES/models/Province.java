package com.ilCary.EPIC_ENERGY_SERVICES.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "provinces")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	private String sigla;

	private String regione;
}
