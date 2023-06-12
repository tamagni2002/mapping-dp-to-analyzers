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
@Table(name = "lis_config_mapping")
public class LisConfigMapping {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "dp_number")
	private String dpNumber;
	
	@Column(name = "specific_parameter_choice")
	private String specificParameterChoice;
	
	@Column(name = "specimen_choice")
	private String specimenChoice;

}
