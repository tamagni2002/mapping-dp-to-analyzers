package com.evosoft.mappingdptoanalyzers.controller;

import com.evosoft.mappingdptoanalyzers.myenum.GenericModuleType;
import com.evosoft.mappingdptoanalyzers.myenum.SpecificModuleType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ModuleDTO {
	private Long id;
	private int number;
	private GenericModuleType genericModuleType;
	private SpecificModuleType specificModuleType;
	private boolean automated;

}
