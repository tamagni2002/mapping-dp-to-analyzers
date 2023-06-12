package com.evosoft.mappingdptoanalyzers.controller;

import java.util.List;

import com.evosoft.mappingdptoanalyzers.myenum.ProductLineType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductLineDTO {
	private Long id;
	private List<LineDTO> lines;
	private Integer lineQuantity;
	private ProductLineType type;

}
