package com.evosoft.mappingdptoanalyzers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evosoft.mappingdptoanalyzers.controller.LineDTO;
import com.evosoft.mappingdptoanalyzers.controller.ModuleDTO;
import com.evosoft.mappingdptoanalyzers.controller.ProductLineDTO;
import com.evosoft.mappingdptoanalyzers.myenum.SpecificModuleType;
import com.evosoft.mappingdptoanalyzers.repository.LisConfigMappingRepository;
import com.evosoft.mappingdptoanalyzers.service.LisConfigMappingService;
import com.evosoft.mappingdptoanalyzers.utils.DpNumberAndDpQuantity;
import com.evosoft.mappingdptoanalyzers.utils.DpQuantityMappingItem;
import com.evosoft.mappingdptoanalyzers.utils.DpToAnalyzersMapping;
import com.evosoft.mappingdptoanalyzers.utils.ImDpQuantityMappingItem;
import com.evosoft.mappingdptoanalyzers.utils.ImQuantities;
import com.evosoft.mappingdptoanalyzers.utils.NumbersAndLineNumbersChImMappingItem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LisConfigMappingServiceImpl implements LisConfigMappingService {
	
	
	private final LisConfigMappingRepository lisConfigMappingRepository;
	
	@Autowired
	public LisConfigMappingServiceImpl(LisConfigMappingRepository lisConfigMappingRepository) {

		this.lisConfigMappingRepository = lisConfigMappingRepository;
	}

	

	@Override
	public DpToAnalyzersMapping mapDpToAnalysers(ProductLineDTO productLineDTO) {
		List<NumbersAndLineNumbersChImMappingItem> numbersAndLineNumbersChImMappingItems = new ArrayList<>();
		List<LineDTO> lines = productLineDTO.getLines();
		
		int cummulativeNumberOfLineWithCh = 0;
		int cummulativeNumberOfLineWithIm = 0;
		int cummulativeNumberOfLineWithIm1300 = 0;
		int cummulativeNumberOfLineWithIm1600 = 0;
		
		for(LineDTO line: lines) {
			boolean isLineWithCh = false;
			boolean isLineWithIm = false;
			boolean isLineWithIm1600 = false;
			boolean isLineWithIm1300 = false;
			boolean isLineWithCi1900 = false;
			
			
			System.out.println("lineNumber: "+line.getLineNumber());
			
			NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem = new NumbersAndLineNumbersChImMappingItem();
				
			numbersAndLineNumbersChImMappingItem.setNumberOfChInLine(0);
			numbersAndLineNumbersChImMappingItem.setNumberOfImInLine(0);
			numbersAndLineNumbersChImMappingItem.setNumberOfIm1600InLine(0);
			numbersAndLineNumbersChImMappingItem.setNumberOfIm1300InLine(0);
			numbersAndLineNumbersChImMappingItem.setNumberOfCi1900InLine(0);
				
			List<ModuleDTO> modules = line.getModules();
			for(ModuleDTO module: modules) {
				switch(module.getSpecificModuleType()) {
					case I:
						numbersAndLineNumbersChImMappingItem.setNumberOfImInLine(numbersAndLineNumbersChImMappingItem.getNumberOfImInLine()+1);
						numbersAndLineNumbersChImMappingItem.setNumberOfIm1600InLine(numbersAndLineNumbersChImMappingItem.getNumberOfIm1600InLine()+1);
						
						isLineWithIm = true;
						isLineWithIm1600 = true;
						break;
					case i:
						numbersAndLineNumbersChImMappingItem.setNumberOfImInLine(numbersAndLineNumbersChImMappingItem.getNumberOfImInLine()+1);
						numbersAndLineNumbersChImMappingItem.setNumberOfIm1300InLine(numbersAndLineNumbersChImMappingItem.getNumberOfIm1300InLine()+1);
						isLineWithIm1300 = true;
						break;
					case C:
						numbersAndLineNumbersChImMappingItem.setNumberOfChInLine(numbersAndLineNumbersChImMappingItem.getNumberOfChInLine()+1);
						isLineWithCh = true;
						break;
					case CI:
						numbersAndLineNumbersChImMappingItem.setNumberOfImInLine(numbersAndLineNumbersChImMappingItem.getNumberOfImInLine()+1);
						numbersAndLineNumbersChImMappingItem.setNumberOfChInLine(numbersAndLineNumbersChImMappingItem.getNumberOfChInLine()+1);
						numbersAndLineNumbersChImMappingItem.setNumberOfCi1900InLine(numbersAndLineNumbersChImMappingItem.getNumberOfCi1900InLine()+1);
						isLineWithCi1900 = true;
						//isLineWithIm1300 = true;
						break;
					default:
						break;
					}// End of switch
				}//End of For ModuleDTO module: modules
				
				if(isLineWithCh) {
					cummulativeNumberOfLineWithCh++;
					numbersAndLineNumbersChImMappingItem.setCummulativeNumberOfLineWithCh(cummulativeNumberOfLineWithCh);
					//System.out.println("cummulativeNumberOfLineWithCh: " + cummulativeNumberOfLineWithCh);
				}
				
				if(isLineWithIm) {
					cummulativeNumberOfLineWithIm++;
					numbersAndLineNumbersChImMappingItem.setCummulativeNumberOfLineWithIm(cummulativeNumberOfLineWithIm);
				}
				
				if(isLineWithIm1300) {
					cummulativeNumberOfLineWithIm1300++;
					numbersAndLineNumbersChImMappingItem.setCummulativeNumberOfLineWithIm1300(cummulativeNumberOfLineWithIm1300);
				}
				
				if(isLineWithIm1600) {
					cummulativeNumberOfLineWithIm1600++;
					numbersAndLineNumbersChImMappingItem.setCummulativeNumberOfLineWithIm1600(cummulativeNumberOfLineWithIm1600);
				}
				
				if(isLineWithCi1900) {
					cummulativeNumberOfLineWithCh++;
					cummulativeNumberOfLineWithIm++;
					numbersAndLineNumbersChImMappingItem.setCummulativeNumberOfLineWithIm(cummulativeNumberOfLineWithIm);
					numbersAndLineNumbersChImMappingItem.setCummulativeNumberOfLineWithCh(cummulativeNumberOfLineWithCh);
				}
				
				numbersAndLineNumbersChImMappingItems.add(numbersAndLineNumbersChImMappingItem);	
		}//End of for for(LineDTO line: lines)
		
		DpToAnalyzersMapping dpToAnalyzersMapping = new DpToAnalyzersMapping();
		dpToAnalyzersMapping.setNumbersAndLineNumbersChImMappingItems(numbersAndLineNumbersChImMappingItems);
		//dpToAnalyzersMapping.setTotalNumberOfLinesWithCh(cummulativeNumberOfLineWithCh);
		//dpToAnalyzersMapping.setTotalNumberOfLinesWithIm(cummulativeNumberOfLineWithIm);;
		dpToAnalyzersMapping.calculateTotalNumberOfLines();
		dpToAnalyzersMapping.calculateTotalNumberAnalyzers();
		return dpToAnalyzersMapping;
	}






	@Override
	public DpToAnalyzersMapping mapDpQuantities(DpToAnalyzersMapping dpToAnalyzersMapping) {
		// TODO Auto-generated method
		List<DpNumberAndDpQuantity> dpNumberAndDpQuantities= lisConfigMappingRepository.findAllDpNumberAndDpQuantity();
		
		int currentLineForLowVolume = 1;
		int DP_index = 0;
		for(DpNumberAndDpQuantity dpNumberAndDpQuantity: dpNumberAndDpQuantities) {
			DP_index++;
			System.out.print("DP_index: " + DP_index);
			//yoif(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithCh()) currentLineForLowVolume = 1;
			DpQuantityMappingItem dpQuantityMappingItem = new DpQuantityMappingItem();
			dpQuantityMappingItem.setDpNumber(dpNumberAndDpQuantity.getDpNumber());
			double dpQuantity = dpNumberAndDpQuantity.getPdQuantity();
			dpQuantityMappingItem.setDpQuantity(dpQuantity);
			double qtyAssignment = (double)Math.floor(dpQuantity/dpToAnalyzersMapping.getTotalChAnalyzers());
			dpQuantityMappingItem.setQtyAssigment(qtyAssignment);
			
			if(qtyAssignment <= 1000) 
				dpQuantityMappingItem.setLowVolume(true);
			else
				dpQuantityMappingItem.setLowVolume(false);
			
			if(dpQuantityMappingItem.isLowVolume()) {
				//for(NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems()) {
				  //if(currentLineForLowVolume == numbersAndLineNumbersChImMappingItem.getCummulativeNumberOfLineWithCh()) {
				  for(int currentLine = 1; currentLine <= dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().size(); currentLine++) {
					//if(currentLineForLowVolume == numbersAndLineNumbersChImMappingItem.getCummulativeNumberOfLineWithCh()) {
					if(currentLineForLowVolume == currentLine) {
						dpQuantityMappingItem.getPdQuantityAssignedList().add(dpQuantity);
					}
					else {
						dpQuantityMappingItem.getPdQuantityAssignedList().add(0d); 
					}
				 }//End of For
				  
				 if(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithCh())
					 currentLineForLowVolume = 0;
				currentLineForLowVolume++;	

			}else {
				for(NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems()) {
					if(numbersAndLineNumbersChImMappingItem.getNumberOfChInLine() != 0)
						dpQuantityMappingItem.getPdQuantityAssignedList().add(numbersAndLineNumbersChImMappingItem.getNumberOfChInLine()*qtyAssignment);
					else dpQuantityMappingItem.getPdQuantityAssignedList().add(0d);
				}				
					
			}
				
			
		
			dpToAnalyzersMapping.getDpQuantityMappingItems().add(dpQuantityMappingItem);	
		}
		
		return dpToAnalyzersMapping;
	 }
	
	@Override
	public DpToAnalyzersMapping imMapDpQuantities(DpToAnalyzersMapping dpToAnalyzersMapping) {
		// TODO Auto-generated method

		List<DpNumberAndDpQuantity> dpNumberAndDpQuantities= lisConfigMappingRepository.findAllDpNumberAndDpQuantity();
		
		int currentLineForLowVolume = 1;
		int DP_index = 0;
		boolean moreThanOneImmunologyTypesLowVolumeInLine = false;
		int inLowVolumeImmunologyPosition = 0;
		for(DpNumberAndDpQuantity dpNumberAndDpQuantity: dpNumberAndDpQuantities) {
			DP_index++;
			System.out.print("DP_index: " + DP_index);
			//if(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithCh()) currentLineForLowVolume = 1;
			ImDpQuantityMappingItem imDpQuantityMappingItem = new ImDpQuantityMappingItem();
			imDpQuantityMappingItem.setDpNumber(dpNumberAndDpQuantity.getDpNumber());
			double dpQuantity = dpNumberAndDpQuantity.getPdQuantity();
			imDpQuantityMappingItem.setDpQuantity(dpQuantity);
			double qtyAssignment = (double)Math.floor(dpQuantity/dpToAnalyzersMapping.getTotalImAnalyzers());
			imDpQuantityMappingItem.setQtyAssigment(qtyAssignment);
			
			if(qtyAssignment <= 1000) 
				imDpQuantityMappingItem.setLowVolume(true);
			else
				imDpQuantityMappingItem.setLowVolume(false);
			
			if(imDpQuantityMappingItem.isLowVolume()) {
				//for(NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems()) {
				  //if(currentLineForLowVolume == numbersAndLineNumbersChImMappingItem.getCummulativeNumberOfLineWithCh()) {
				  for(int currentLine = 1; currentLine <= dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().size(); currentLine++) {
					//if(currentLineForLowVolume == numbersAndLineNumbersChImMappingItem.getCummulativeNumberOfLineWithCh()) {
					if(currentLineForLowVolume == currentLine) {
						NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(currentLine-1);
						moreThanOneImmunologyTypesLowVolumeInLine = numbersAndLineNumbersChImMappingItem.getNumberOfIm1600InLine() >= 1 && numbersAndLineNumbersChImMappingItem.getNumberOfIm1300InLine() >= 1;
						if (moreThanOneImmunologyTypesLowVolumeInLine) {
							ImQuantities imQuantities = new ImQuantities();
							if(inLowVolumeImmunologyPosition == 0) {
								imQuantities.setIm1600(dpQuantity);
								imQuantities.setIm1300(0d);
								imQuantities.setCi1900(0d);
							}else {
								imQuantities.setIm1600(0d);
								imQuantities.setIm1300(dpQuantity);
								imQuantities.setCi1900(0d);	
							}
							imDpQuantityMappingItem.getImPdQuantityAssignedList().add(imQuantities);	
						}else if(numbersAndLineNumbersChImMappingItem.getNumberOfCi1900InLine()>0){
							ImQuantities imQuantities = new ImQuantities();
							imQuantities.setIm1600(0d);
							imQuantities.setIm1300(0d);
							imQuantities.setCi1900(dpQuantity);
							imDpQuantityMappingItem.getImPdQuantityAssignedList().add(imQuantities);	
				        }else {
							
							if( numbersAndLineNumbersChImMappingItem.getNumberOfIm1600InLine() >= 1) {
								ImQuantities imQuantities = new ImQuantities();
								imQuantities.setIm1600(dpQuantity);
								imQuantities.setIm1300(0d);
								imQuantities.setCi1900(0d);
								imDpQuantityMappingItem.getImPdQuantityAssignedList().add(imQuantities);	
							}else if(numbersAndLineNumbersChImMappingItem.getNumberOfIm1300InLine() >= 1){
								ImQuantities imQuantities = new ImQuantities();
								imQuantities.setIm1600(0d);
								imQuantities.setIm1300(dpQuantity);
								imQuantities.setCi1900(0d);
								
							}
						}	
					}
					else {
						ImQuantities imQuantities = new ImQuantities();
						imQuantities.setIm1600(0d);
						imQuantities.setIm1300(0d);
						imQuantities.setCi1900(0d);
						imDpQuantityMappingItem.getImPdQuantityAssignedList().add(imQuantities); 
					}
		       }
				  //End For each in Lines
				  if(moreThanOneImmunologyTypesLowVolumeInLine) {

					  if(inLowVolumeImmunologyPosition == 1) {	
						  if(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithIm())
								 currentLineForLowVolume = 0;
						  else
							  currentLineForLowVolume++;  
					  }
					  
				       if(inLowVolumeImmunologyPosition == 1)
						  inLowVolumeImmunologyPosition = 0;
					   else if(inLowVolumeImmunologyPosition == 0)
							inLowVolumeImmunologyPosition++;
					   
				  }else {
					  if(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithIm())
							 currentLineForLowVolume = 0;
					  else
						  currentLineForLowVolume++; 
				  }
				  
			}else {
				
				//It is high volume
				for(NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems()) {
					ImQuantities imQuantities = new ImQuantities();
					if(numbersAndLineNumbersChImMappingItem.getNumberOfImInLine() != 0) {					
						imQuantities.setIm1600(numbersAndLineNumbersChImMappingItem.getNumberOfIm1600InLine()*qtyAssignment);
						imQuantities.setIm1300(numbersAndLineNumbersChImMappingItem.getNumberOfIm1300InLine()*qtyAssignment);
						imQuantities.setCi1900(numbersAndLineNumbersChImMappingItem.getNumberOfCi1900InLine()*qtyAssignment);
					}else{
						imQuantities.setIm1600(0d);
						imQuantities.setIm1300(0d);
						imQuantities.setCi1900(0d);
					}
					imDpQuantityMappingItem.getImPdQuantityAssignedList().add(imQuantities);
				}				
					
			}
				
			dpToAnalyzersMapping.getImDpQuantityMappingItems().add(imDpQuantityMappingItem);
			
		}// For Each on DP 
		
		return dpToAnalyzersMapping;
	 }


/*
	@Override
	public DpToAnalyzersMapping imMapDpQuantities(DpToAnalyzersMapping dpToAnalyzersMapping) {
		// TODO Auto-generated method
		List<DpNumberAndDpQuantity> dpNumberAndDpQuantities= lisConfigMappingRepository.findAllDpNumberAndDpQuantity();
		
		int currentLineForLowVolume = 1;
		int DP_index = 0;
		for(DpNumberAndDpQuantity dpNumberAndDpQuantity: dpNumberAndDpQuantities) {
			DP_index++;
			System.out.print("DP_index: " + DP_index);
			//yoif(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithCh()) currentLineForLowVolume = 1;
			ImDpQuantityMappingItem imDpQuantityMappingItem = new ImDpQuantityMappingItem();
			imDpQuantityMappingItem.setDpNumber(dpNumberAndDpQuantity.getDpNumber());
			double dpQuantity = dpNumberAndDpQuantity.getPdQuantity();
			imDpQuantityMappingItem.setDpQuantity(dpQuantity);
			double qtyAssignment = (double)Math.floor(dpQuantity/dpToAnalyzersMapping.getTotalChAnalyzers());
			imDpQuantityMappingItem.setQtyAssigment(qtyAssignment);
			
			if(qtyAssignment <= 1000) 
				imDpQuantityMappingItem.setLowVolume(true);
			else
				imDpQuantityMappingItem.setLowVolume(false);
			
			if(imDpQuantityMappingItem.isLowVolume()) {
				//for(NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems()) {
				  //if(currentLineForLowVolume == numbersAndLineNumbersChImMappingItem.getCummulativeNumberOfLineWithCh()) {
				  for(int currentLine = 1; currentLine <= dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().size(); currentLine++) {
					//if(currentLineForLowVolume == numbersAndLineNumbersChImMappingItem.getCummulativeNumberOfLineWithCh()) {
					if(currentLineForLowVolume == currentLine) {
						imDpQuantityMappingItem.getImPdQuantityAssignedList().add(dpQuantity);
					}
					else {
						imDpQuantityMappingItem.getImPdQuantityAssignedList().add(0d); 
					}
				 }
				  
				 if(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithIm())
					 currentLineForLowVolume = 0;
				currentLineForLowVolume++;	
	
			}else {
				for(NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems()) {
					double [] toAssignedElements = new double[2];
					if(numbersAndLineNumbersChImMappingItem.getNumberOfImInLine() != 0) {					
						toAssignedElements[0] = numbersAndLineNumbersChImMappingItem.getNumberOfIm1600InLine()*qtyAssignment;
						toAssignedElements[1] = numbersAndLineNumbersChImMappingItem.getNumberOfIm1300InLine()*qtyAssignment;
						imDpQuantityMappingItem.getImPdQuantityAssignedList().add(toAssignedElements);
					}else {
						toAssignedElements[0] = 0d;
						toAssignedElements[1] =  0d;
						imDpQuantityMappingItem.getImPdQuantityAssignedList().add(toAssignedElements);
					}
				}				
					
			}
				
			
		
			dpToAnalyzersMapping.getDpQuantityMappingItems().add(imDpQuantityMappingItem);	
		}
		
		return dpToAnalyzersMapping;
	 }
*/

/*
	@Override
	public DpToAnalyzersMapping imMapDpQuantities(DpToAnalyzersMapping dpToAnalyzersMapping) {
		// TODO Auto-generated method stub
		List<DpNumberAndDpQuantity> dpNumberAndDpQuantities= lisConfigMappingRepository.findAllDpNumberAndDpQuantity();
		
		int currentLineForLowVolume = 1;
		int DP_index = 0;
		for(DpNumberAndDpQuantity dpNumberAndDpQuantity: dpNumberAndDpQuantities) {
			DP_index++;
			System.out.print("DP_index: " + DP_index);
			//yoif(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithCh()) currentLineForLowVolume = 1;
			ImDpQuantityMappingItem imDpQuantityMappingItem = new ImDpQuantityMappingItem();
			imDpQuantityMappingItem.setDpNumber(dpNumberAndDpQuantity.getDpNumber());
			double dpQuantity = dpNumberAndDpQuantity.getPdQuantity();
			imDpQuantityMappingItem.setDpQuantity(dpQuantity);
			double qtyAssignment = (double)Math.floor(dpQuantity/dpToAnalyzersMapping.getTotalChAnalyzers());
			imDpQuantityMappingItem.setQtyAssigment(qtyAssignment);
			
			if(qtyAssignment <= 1000) 
				imDpQuantityMappingItem.setLowVolume(true);
			else
				imDpQuantityMappingItem.setLowVolume(false);
			
			if(imDpQuantityMappingItem.isLowVolume()) {
				//for(NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems()) {
				  //if(currentLineForLowVolume == numbersAndLineNumbersChImMappingItem.getCummulativeNumberOfLineWithCh()) {
				  for(int currentLine = 1; currentLine <= dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().size(); currentLine++) {
					//if(currentLineForLowVolume == numbersAndLineNumbersChImMappingItem.getCummulativeNumberOfLineWithCh()) {
					if(currentLineForLowVolume == currentLine) {
						imDpQuantityMappingItem.getImPdQuantityAssignedList().add(dpQuantity);
					}
					else {
						imDpQuantityMappingItem.getPdQuantityAssignedList().add(0d); 
					}
				 }
				  
				 if(currentLineForLowVolume == dpToAnalyzersMapping.getTotalNumberOfLinesWithCh())
					 currentLineForLowVolume = 0;
				currentLineForLowVolume++;	

			}else {
				for(NumbersAndLineNumbersChImMappingItem numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems()) {
					if(numbersAndLineNumbersChImMappingItem.getNumberOfChInLine() != 0)
						imDpQuantityMappingItem.getPdQuantityAssignedList().add(numbersAndLineNumbersChImMappingItem.getNumberOfChInLine()*qtyAssignment);
					else imDpQuantityMappingItem.getPdQuantityAssignedList().add(0d);
					
				}//End For	numbersAndLineNumbersChImMappingItem : dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems			
					
			}//End For dpNumberAndDpQuantity: dpNumberAndDpQuantities
				
			
		
			dpToAnalyzersMapping.getImDpQuantityMappingItems().add(imDpQuantityMappingItem);	
		}
		
		return dpToAnalyzersMapping;
	}*/
}
