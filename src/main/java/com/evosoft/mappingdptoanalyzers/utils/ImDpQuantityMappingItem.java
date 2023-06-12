package com.evosoft.mappingdptoanalyzers.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImDpQuantityMappingItem {
	private String dpNumber;
	private double dpQuantity;
	private double qtyAssigment;
	private boolean lowVolume;
	private List<ImQuantities> imPdQuantityAssignedList = new ArrayList<>();

}
