package com.ilCary.EPIC_ENERGY_SERVICES.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @Column(unique = true, nullable = false)

	// valori che andiamo a definire come univoci
	@Column(unique = true, nullable = false)
	private String pec;
	@Column(unique = true, nullable = false)
	private String emailContatto;
	@Column(unique = true, nullable = false)
	private String telefono;
	@Column(unique = true, nullable = false)
	private String partitaIva;
	@Column(unique = true, nullable = false)
	private String ragioneSociale;
	@Column(unique = true, nullable = false)
	private String email;
	
	@OneToOne(cascade= {
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
	})
	@JsonBackReference
	private Address sedeLegale;
	
	@OneToOne(cascade= {
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
	})
	@JsonBackReference
	private Address sedeOperativa;


	@Enumerated(EnumType.STRING)
	private ClientType type;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private Double fatturatoAnnuale;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;

}