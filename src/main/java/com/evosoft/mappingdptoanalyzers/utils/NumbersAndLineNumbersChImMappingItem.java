package com.evosoft.mappingdptoanalyzers.utils;

import java.util.List;

import com.evosoft.mappingdptoanalyzers.controller.LineDTO;
import com.evosoft.mappingdptoanalyzers.myenum.ProductLineType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NumbersAndLineNumbersChImMappingItem {
	private int lineNumber;
	private int numberOfChInLine;
	private int numberOfImInLine;
	private int numberOfIm1300InLine;
	private int numberOfIm1600InLine;
	private int numberOfCi1900InLine;
	private int cummulativeNumberOfLineWithCh;
	private int cummulativeNumberOfLineWithIm;
	private int cummulativeNumberOfLineWithIm1300;
	private int cummulativeNumberOfLineWithIm1600;
	private int cummulativeNumberOfLineWithCi1900;

}
