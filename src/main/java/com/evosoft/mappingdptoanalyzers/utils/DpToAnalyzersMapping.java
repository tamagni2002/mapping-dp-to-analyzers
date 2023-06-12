package com.evosoft.mappingdptoanalyzers.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DpToAnalyzersMapping {
	private List<NumbersAndLineNumbersChImMappingItem> numbersAndLineNumbersChImMappingItems = new ArrayList<>();
	private List<DpQuantityMappingItem> dpQuantityMappingItems = new ArrayList<>();
	private List<ImDpQuantityMappingItem> imDpQuantityMappingItems = new ArrayList<>();
	private int totalNumberOfLinesWithCh = 0;
	private int totalNumberOfLinesWithIm = 0;
	private int totalChAnalyzers = 0;
	private int totalImAnalyzers = 0;
	
	public void calculateTotalNumberOfLines() {
		
		OptionalInt optionalTotalNumberOfLinesWithCh = numbersAndLineNumbersChImMappingItems.stream()
				.mapToInt(NumbersAndLineNumbersChImMappingItem::getCummulativeNumberOfLineWithCh)
				.max();
		if (optionalTotalNumberOfLinesWithCh.isPresent())
		    totalNumberOfLinesWithCh = optionalTotalNumberOfLinesWithCh.getAsInt();
		
		OptionalInt optionalTotalNumberOfLinesWithIm = numbersAndLineNumbersChImMappingItems.stream()
				.mapToInt(NumbersAndLineNumbersChImMappingItem::getCummulativeNumberOfLineWithIm)
				.max();
		if (optionalTotalNumberOfLinesWithIm.isPresent())
		    totalNumberOfLinesWithIm = optionalTotalNumberOfLinesWithIm.getAsInt();
	}
	
	public void calculateTotalNumberAnalyzers() {
		//totalChAnalyzers = 0;
		//totalImAnalyzers = 0;
		for(NumbersAndLineNumbersChImMappingItem item : numbersAndLineNumbersChImMappingItems) {
			totalChAnalyzers += item.getNumberOfChInLine();
			totalImAnalyzers += item.getNumberOfImInLine();	
		}
	}

}
