package com.evosoft.mappingdptoanalyzers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "dp_dpt_lookup")
public class DpDptLookup {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@Column(name = "dp_number")
	private String dpNumber;
	
	@Column(name = "dpt_number")
	private String dptNumber;
	
	@Column(name = "is_default")
	private boolean dptDefault;
	
	@Column(name = "specific_parameter_choice")
	private String specificParameterChoice;
	
	@Column(name = "speciment_choice")	
	private String specimentChoice;
	
	@Column(name = "country_specific")	
	private String countrySpecific;

}
