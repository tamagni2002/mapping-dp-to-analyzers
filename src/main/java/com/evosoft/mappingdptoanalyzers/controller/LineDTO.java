package com.evosoft.mappingdptoanalyzers.controller;

import java.util.List;

import com.evosoft.mappingdptoanalyzers.myenum.ConfigurationLineType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LineDTO {
	private Long id;
	private int lineNumber;
	private List<ModuleDTO> modules;
	private ConfigurationLineType type;

}
