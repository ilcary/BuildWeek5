package com.ilCary.EPIC_ENERGY_SERVICES.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "municipalities")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Municipality {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  private String name;
	  
	  @ManyToOne
	  private Province province;
}
