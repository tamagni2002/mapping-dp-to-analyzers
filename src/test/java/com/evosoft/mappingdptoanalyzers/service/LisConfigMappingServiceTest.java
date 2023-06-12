package com.evosoft.mappingdptoanalyzers.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.evosoft.mappingdptoanalyzers.repository.LisConfigMappingRepository;
import com.evosoft.mappingdptoanalyzers.service.impl.LisConfigMappingServiceImpl;
import com.evosoft.mappingdptoanalyzers.utils.DpNumberAndDpQuantity;
import com.evosoft.mappingdptoanalyzers.utils.DpQuantityMappingItem;
import com.evosoft.mappingdptoanalyzers.utils.DpToAnalyzersMapping;
import com.evosoft.mappingdptoanalyzers.utils.ImDpQuantityMappingItem;
import com.evosoft.mappingdptoanalyzers.utils.ImQuantities;
import com.evosoft.mappingdptoanalyzers.utils.NumbersAndLineNumbersChImMappingItem;
import com.evosoft.mappingdptoanalyzers.controller.LineDTO;
import com.evosoft.mappingdptoanalyzers.controller.ModuleDTO;
import com.evosoft.mappingdptoanalyzers.controller.ProductLineDTO;
import com.evosoft.mappingdptoanalyzers.myenum.SpecificModuleType;

public class LisConfigMappingServiceTest {
	
    @Mock
    private LisConfigMappingRepository lisConfigMappingRepository;
	
	private LisConfigMappingService underTestService;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTestService = new LisConfigMappingServiceImpl(lisConfigMappingRepository);
    }
	
	@Test
	void itShouldMapDpToAnalyzersOnFirstLine() {
		
		ProductLineDTO productLineDTO = createProductLineDTO();
		
		//productLineDTO.setLines(null);
		
		//When
		DpToAnalyzersMapping dpToAnalyzersMapping = underTestService.mapDpToAnalysers(productLineDTO);
		
		//Then
		NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem0 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(0);
		
		assertThat(pToAnalyzersMappingItem0.getNumberOfChInLine()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem0.getNumberOfImInLine()).isEqualTo(3);
		assertThat(pToAnalyzersMappingItem0.getNumberOfIm1600InLine()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem0.getNumberOfIm1300InLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem0.getCummulativeNumberOfLineWithIm()).isEqualTo(1);	
		assertThat(pToAnalyzersMappingItem0.getCummulativeNumberOfLineWithCh()).isEqualTo(1);
	}
	
	@Test
	void itShouldMapDpToAnalyzers() {
		
		//Given 
		
		ProductLineDTO productLineDTO = createProductLineDTO();
		
		
		//When
		DpToAnalyzersMapping dpToAnalyzersMapping = underTestService.mapDpToAnalysers(productLineDTO);
		
		//Then
		NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem0 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(0);
		
		
		assertThat(pToAnalyzersMappingItem0.getNumberOfChInLine()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem0.getNumberOfImInLine()).isEqualTo(3);
		assertThat(pToAnalyzersMappingItem0.getNumberOfIm1600InLine()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem0.getNumberOfIm1300InLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem0.getCummulativeNumberOfLineWithIm()).isEqualTo(1);	
		assertThat(pToAnalyzersMappingItem0.getCummulativeNumberOfLineWithCh()).isEqualTo(1);

		
        NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem1 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(1);
		
		assertThat(pToAnalyzersMappingItem1.getNumberOfChInLine()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem1.getNumberOfImInLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem1.getNumberOfIm1600InLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem1.getNumberOfIm1300InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem1.getCummulativeNumberOfLineWithCh()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem1.getCummulativeNumberOfLineWithIm()).isEqualTo(2);
		
        NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem2 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(2);
		
		assertThat(pToAnalyzersMappingItem2.getNumberOfChInLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem2.getNumberOfImInLine()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem2.getNumberOfIm1600InLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem2.getNumberOfIm1300InLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem2.getCummulativeNumberOfLineWithCh()).isEqualTo(3);
		assertThat(pToAnalyzersMappingItem2.getCummulativeNumberOfLineWithIm()).isEqualTo(3);
		
        NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem3 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(3);
		
		assertThat(pToAnalyzersMappingItem3.getNumberOfChInLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem3.getNumberOfImInLine()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem3.getNumberOfIm1600InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem3.getNumberOfIm1300InLine()).isEqualTo(2);
		assertThat(pToAnalyzersMappingItem3.getCummulativeNumberOfLineWithCh()).isEqualTo(4);
		assertThat(pToAnalyzersMappingItem3.getCummulativeNumberOfLineWithIm()).isEqualTo(0);
		
        NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem4 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(4);
		
		assertThat(pToAnalyzersMappingItem4.getNumberOfChInLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem4.getNumberOfImInLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem4.getNumberOfIm1600InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem4.getNumberOfIm1300InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem4.getCummulativeNumberOfLineWithCh()).isEqualTo(5);
		assertThat(pToAnalyzersMappingItem4.getCummulativeNumberOfLineWithIm()).isEqualTo(4);
		
	    NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem5 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(5);
			
	    assertThat(pToAnalyzersMappingItem5.getNumberOfChInLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem5.getNumberOfImInLine()).isEqualTo(1);
		assertThat(pToAnalyzersMappingItem5.getNumberOfIm1600InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem5.getNumberOfIm1300InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem5.getCummulativeNumberOfLineWithCh()).isEqualTo(6);
		assertThat(pToAnalyzersMappingItem5.getCummulativeNumberOfLineWithIm()).isEqualTo(5);
		
	    NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem6 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(6);
			
		assertThat(pToAnalyzersMappingItem6.getNumberOfChInLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem6.getNumberOfImInLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem6.getNumberOfIm1600InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem6.getNumberOfIm1300InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem6.getCummulativeNumberOfLineWithCh()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem6.getCummulativeNumberOfLineWithIm()).isEqualTo(0);
		
		
		NumbersAndLineNumbersChImMappingItem pToAnalyzersMappingItem7 = dpToAnalyzersMapping.getNumbersAndLineNumbersChImMappingItems().get(7);
		
		assertThat(pToAnalyzersMappingItem7.getNumberOfChInLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem7.getNumberOfImInLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem7.getNumberOfIm1600InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem7.getNumberOfIm1300InLine()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem7.getCummulativeNumberOfLineWithCh()).isEqualTo(0);
		assertThat(pToAnalyzersMappingItem7.getCummulativeNumberOfLineWithIm()).isEqualTo(0);
		
		
		assertThat(dpToAnalyzersMapping.getTotalNumberOfLinesWithCh()).isEqualTo(6);
		
		
		assertThat(dpToAnalyzersMapping.getTotalNumberOfLinesWithIm()).isEqualTo(5);
		
		
		assertThat(dpToAnalyzersMapping.getTotalChAnalyzers()).isEqualTo(8);
		assertThat(dpToAnalyzersMapping.getTotalImAnalyzers()).isEqualTo(10);
			
			
	}
	
	@Test
	void itShouldMapQuantities(){
		
		//given: DpToAnalyzersMapping with calculated dpToAnalyzersMappingItems elements
		ProductLineDTO productLineDTO = createProductLineDTO();
        DpToAnalyzersMapping dpToAnalyzersMapping = underTestService.mapDpToAnalysers(productLineDTO);
		

		
		//when: calling mapQuantities method of DpToAnalyzersMapping
		DpToAnalyzersMapping dpToAnalyzersMappingWithQuantities = underTestService.mapDpQuantities(dpToAnalyzersMapping);
		
		//Then: right quantities are assigned
		List<DpQuantityMappingItem> imDpQuantityMappingItems = dpToAnalyzersMappingWithQuantities.getDpQuantityMappingItems();
		
		//assertThat(imDpQuantityMappingItems.getTotalImAnalyzers()).isEqualTo(10);
		
		
		
	}
	
	@Test
	void itShouldMap_dpNumber_And_dpQuantity(){
		
		//given: DpToAnalyzersMapping with calculated dpToAnalyzersMappingItems elements
		ProductLineDTO productLineDTO = createProductLineDTO();
		DpToAnalyzersMapping dpToAnalyzersMapping = underTestService.mapDpToAnalysers(productLineDTO);
		
		//when: calling mapQuantities method of DpToAnalyzersMapping
		List<DpNumberAndDpQuantity> dpNumberAndDpQuantities = new ArrayList<>();
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity1 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity1.setDpNumber("DP_CH_1");
		dpNumberAndDpQuantity1.setPdQuantity(100000d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity1);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity2 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity2.setDpNumber("DP_CH_2");
		dpNumberAndDpQuantity2.setPdQuantity(500d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity2);
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity3 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity3.setDpNumber("DP_CH_3");
		dpNumberAndDpQuantity3.setPdQuantity(300d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity3);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity4 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity4.setDpNumber("DP_CH_4");
		dpNumberAndDpQuantity4.setPdQuantity(100d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity4);
		
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity5 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity5.setDpNumber("DP_CH_5");
		dpNumberAndDpQuantity5.setPdQuantity(250d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity5);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity6 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity6.setDpNumber("DP_CH_6");
		dpNumberAndDpQuantity6.setPdQuantity(150d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity6);
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity7 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity7.setDpNumber("DP_CH_7");
		dpNumberAndDpQuantity7.setPdQuantity(200d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity7);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity8 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity8.setDpNumber("DP_CH_8");
		dpNumberAndDpQuantity8.setPdQuantity(450d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity8);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity9 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity9.setDpNumber("DP_CH_9");
		dpNumberAndDpQuantity9.setPdQuantity(800d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity9);
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity10 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity10.setDpNumber("DP_CH_10");
		dpNumberAndDpQuantity10.setPdQuantity(200d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity10);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity11 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity11.setDpNumber("DP_CH_11");
		dpNumberAndDpQuantity11.setPdQuantity(5000d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity11);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity12 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity12.setDpNumber("DP_CH_12");
		dpNumberAndDpQuantity12.setPdQuantity(49530d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity12);
		
		when(lisConfigMappingRepository.findAllDpNumberAndDpQuantity()).thenReturn(dpNumberAndDpQuantities);
		DpToAnalyzersMapping dpToAnalyzersMappingWithQuantities = underTestService.mapDpQuantities(dpToAnalyzersMapping);
		
		//Then: right quantities are assigned
		List<DpQuantityMappingItem> imDpQuantityMappingItems = dpToAnalyzersMappingWithQuantities.getDpQuantityMappingItems();
		
		assertThat(imDpQuantityMappingItems.get(0).getDpNumber()).isEqualTo("DP_CH_1");
		assertThat(imDpQuantityMappingItems.get(0).getDpQuantity()).isEqualTo(100000L);
		
		assertThat(imDpQuantityMappingItems.get(1).getDpNumber()).isEqualTo("DP_CH_2");
		assertThat(imDpQuantityMappingItems.get(1).getDpQuantity()).isEqualTo(500L);
		
	}
	
	
	
	@Test
	void itShouldMap_qtyAssignment(){
		
		//given: DpToAnalyzersMapping with calculated dpToAnalyzersMappingItems elements
		ProductLineDTO productLineDTO = createProductLineDTO();
		DpToAnalyzersMapping dpToAnalyzersMapping = underTestService.mapDpToAnalysers(productLineDTO);
		
		//when: calling mapQuantities method of DpToAnalyzersMapping
		List<DpNumberAndDpQuantity> dpNumberAndDpQuantities = new ArrayList<>();
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity1 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity1.setDpNumber("DP_CH_1");
		dpNumberAndDpQuantity1.setPdQuantity(100000d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity1);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity2 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity2.setDpNumber("DP_CH_2");
		dpNumberAndDpQuantity2.setPdQuantity(500d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity2);
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity3 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity3.setDpNumber("DP_CH_3");
		dpNumberAndDpQuantity3.setPdQuantity(300d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity3);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity4 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity4.setDpNumber("DP_CH_4");
		dpNumberAndDpQuantity4.setPdQuantity(100d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity4);
		
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity5 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity5.setDpNumber("DP_CH_5");
		dpNumberAndDpQuantity5.setPdQuantity(250d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity5);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity6 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity6.setDpNumber("DP_CH_6");
		dpNumberAndDpQuantity6.setPdQuantity(150d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity6);
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity7 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity7.setDpNumber("DP_CH_7");
		dpNumberAndDpQuantity7.setPdQuantity(200d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity7);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity8 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity8.setDpNumber("DP_CH_8");
		dpNumberAndDpQuantity8.setPdQuantity(450d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity8);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity9 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity9.setDpNumber("DP_CH_9");
		dpNumberAndDpQuantity9.setPdQuantity(800d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity9);
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity10 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity10.setDpNumber("DP_CH_10");
		dpNumberAndDpQuantity10.setPdQuantity(200d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity10);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity11 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity11.setDpNumber("DP_CH_11");
		dpNumberAndDpQuantity11.setPdQuantity(5000d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity11);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity12 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity12.setDpNumber("DP_CH_12");
		dpNumberAndDpQuantity12.setPdQuantity(49530d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity12);
		
		when(lisConfigMappingRepository.findAllDpNumberAndDpQuantity()).thenReturn(dpNumberAndDpQuantities);
		DpToAnalyzersMapping dpToAnalyzersMappingWithQuantities = underTestService.mapDpQuantities(dpToAnalyzersMapping);
		
		//Then: right quantities are assigned
		List<DpQuantityMappingItem> imDpQuantityMappingItems = dpToAnalyzersMappingWithQuantities.getDpQuantityMappingItems();
		
		assertThat(imDpQuantityMappingItems.get(0).getQtyAssigment()).isEqualTo(12500L);	
	}
	
	@Test
	void itShouldMap_Immunology_qtyAssignedList(){
		
		//given: DpToAnalyzersMapping with calculated dpToAnalyzersMappingItems elements
		ProductLineDTO productLineDTO = createProductLineDTO();
		DpToAnalyzersMapping dpToAnalyzersMapping = underTestService.mapDpToAnalysers(productLineDTO);
		
		//when: calling mapQuantities method of DpToAnalyzersMapping
		List<DpNumberAndDpQuantity> dpNumberAndDpQuantities = new ArrayList<>();
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity1 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity1.setDpNumber("DP_IM_1");
		dpNumberAndDpQuantity1.setPdQuantity(100000d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity1);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity2 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity2.setDpNumber("DP_IM_2");
		dpNumberAndDpQuantity2.setPdQuantity(500d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity2);
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity3 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity3.setDpNumber("DP_IM_3");
		dpNumberAndDpQuantity3.setPdQuantity(300d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity3);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity4 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity4.setDpNumber("DP_IM_4");
		dpNumberAndDpQuantity4.setPdQuantity(250d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity4);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity5 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity5.setDpNumber("DP_IM_5");
		dpNumberAndDpQuantity5.setPdQuantity(250d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity5);
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity6 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity6.setDpNumber("DP_IM_6");
		dpNumberAndDpQuantity6.setPdQuantity(600d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity6);
		
		
		DpNumberAndDpQuantity dpNumberAndDpQuantity7 = new DpNumberAndDpQuantity();
		dpNumberAndDpQuantity7.setDpNumber("DP_IM_7");
		dpNumberAndDpQuantity7.setPdQuantity(49530d);
		dpNumberAndDpQuantities.add(dpNumberAndDpQuantity7);
		

		
		when(lisConfigMappingRepository.findAllDpNumberAndDpQuantity()).thenReturn(dpNumberAndDpQuantities);
		DpToAnalyzersMapping dpToAnalyzersMappingWithImQuantities = underTestService.imMapDpQuantities(dpToAnalyzersMapping);
		
		//Then: right quantities are assigned
		List<ImDpQuantityMappingItem> imDpQuantityMappingItems = dpToAnalyzersMappingWithImQuantities.getImDpQuantityMappingItems();
		
		
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(0).getIm1600()).isEqualTo(20000d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(0).getIm1300()).isEqualTo(10000d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(0).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(1).getIm1600()).isEqualTo(10000d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(1).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(1).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(2).getIm1600()).isEqualTo(10000d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(2).getIm1300()).isEqualTo(10000d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(2).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(3).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(3).getIm1300()).isEqualTo(20000d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(3).getCi1900()).isEqualTo(0d);
		   
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(4)).isEqualTo(new ImQuantities(0d, 0d, 10000d));	

		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(4).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(4).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(4).getCi1900()).isEqualTo(10000d);
		
		
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(5).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(5).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(5).getCi1900()).isEqualTo(10000d);
		
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(6).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(6).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(6).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(7).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(7).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(0).getImPdQuantityAssignedList().get(7).getCi1900()).isEqualTo(0d);

	
	
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(0).getIm1600()).isEqualTo(500d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(0).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(0).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(1).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(1).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(1).getCi1900()).isEqualTo(0d);
		

		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(2).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(2).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(2).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(3).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(3).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(3).getCi1900()).isEqualTo(0d);
	
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(4).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(4).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(4).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(5).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(5).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(5).getCi1900()).isEqualTo(0d);
		
		
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(6).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(6).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(6).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(7).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(7).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(7).getCi1900()).isEqualTo(0d);
	    
		
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(0).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(0).getIm1300()).isEqualTo(300d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(0).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(1).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(1).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(1).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(2).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(2).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(2).getCi1900()).isEqualTo(0d);
			
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(3).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(3).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(3).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(4).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(4).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(4).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(5).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(5).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(5).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(6).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(6).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(6).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(7).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(7).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(7).getCi1900()).isEqualTo(0d);

		
		
		
		
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(0).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(0).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(0).getCi1900()).isEqualTo(0d);
				
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(1).getIm1600()).isEqualTo(250d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(1).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(1).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(2).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(2).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(2).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(3).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(3).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(3).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(4).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(4).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(4).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(5).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(5).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(5).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(6).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(6).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(6).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(7).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(7).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(7).getCi1900()).isEqualTo(0d);
		
		
		
		
		
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(0).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(0).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(0).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(1).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(1).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(1).getCi1900()).isEqualTo(0d);
		

		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(2).getIm1600()).isEqualTo(250d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(2).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(2).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(3).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(3).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(3).getCi1900()).isEqualTo(0d);
	
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(4).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(4).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(4).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(5).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(5).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(5).getCi1900()).isEqualTo(0d);
		
		
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(6).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(6).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(6).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(7).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(7).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(7).getCi1900()).isEqualTo(0d);
	    

		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(0).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(0).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(0).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(1).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(1).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(1).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(2).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(2).getIm1300()).isEqualTo(600d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(2).getCi1900()).isEqualTo(0d);
			
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(3).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(3).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(3).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(4).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(4).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(4).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(5).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(5).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(5).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(6).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(6).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(6).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(7).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(7).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(7).getCi1900()).isEqualTo(0d);

		
		
       
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(0).getIm1600()).isEqualTo(9906d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(0).getIm1300()).isEqualTo(4953d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(0).getCi1900()).isEqualTo(0d);
		
	
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(1).getIm1600()).isEqualTo(4953d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(1).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(1).getCi1900()).isEqualTo(0d);

		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(2).getIm1600()).isEqualTo(4953d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(2).getIm1300()).isEqualTo(4953d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(2).getCi1900()).isEqualTo(0d);
		

		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(3).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(3).getIm1300()).isEqualTo(9906d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(3).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(4).getIm1600()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(4).getIm1300()).isEqualTo(0d);

		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(4).getCi1900()).isEqualTo(4953d);
			
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(5).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(5).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(5).getCi1900()).isEqualTo(4953d);
		
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(6).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(6).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(6).getCi1900()).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(7).getIm1600()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(7).getIm1300()).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(7).getCi1900()).isEqualTo(0d);
		
		
		
		

		
		/*	
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(4)).isEqualTo(0L);	
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(5)).isEqualTo(0L);	
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(6)).isEqualTo(0L);
		assertThat(imDpQuantityMappingItems.get(1).getImPdQuantityAssignedList().get(7)).isEqualTo(0L);
				
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(0)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(1)).isEqualTo(300d);	
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(2)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(3)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(4)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(5)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(2).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);	
	    
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(0)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(1)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(2)).isEqualTo(100d);	
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(3)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(4)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(5)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(3).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);	
		
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(0)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(1)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(2)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(3)).isEqualTo(250d);	
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(4)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(5)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(4).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(0)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(1)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(2)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(3)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(4)).isEqualTo(150d);	
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(5)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(5).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(0)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(1)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(2)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(3)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(4)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(5)).isEqualTo(200d);	
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(6).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);
		
		
		assertThat(imDpQuantityMappingItems.get(7).getImPdQuantityAssignedList().get(0)).isEqualTo(450d);
		assertThat(imDpQuantityMappingItems.get(7).getImPdQuantityAssignedList().get(1)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(7).getImPdQuantityAssignedList().get(2)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(7).getImPdQuantityAssignedList().get(3)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(7).getImPdQuantityAssignedList().get(4)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(7).getImPdQuantityAssignedList().get(5)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(7).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(7).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);	
		
		
		
		assertThat(imDpQuantityMappingItems.get(8).getImPdQuantityAssignedList().get(0)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(8).getImPdQuantityAssignedList().get(1)).isEqualTo(800d);	
		assertThat(imDpQuantityMappingItems.get(8).getImPdQuantityAssignedList().get(2)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(8).getImPdQuantityAssignedList().get(3)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(8).getImPdQuantityAssignedList().get(4)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(8).getImPdQuantityAssignedList().get(5)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(8).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(8).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);
		
		
		
		assertThat(imDpQuantityMappingItems.get(9).getImPdQuantityAssignedList().get(0)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(9).getImPdQuantityAssignedList().get(1)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(9).getImPdQuantityAssignedList().get(2)).isEqualTo(200d);	
		assertThat(imDpQuantityMappingItems.get(9).getImPdQuantityAssignedList().get(3)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(9).getImPdQuantityAssignedList().get(4)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(9).getImPdQuantityAssignedList().get(5)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(9).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(9).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);
		
		
		assertThat(imDpQuantityMappingItems.get(10).getImPdQuantityAssignedList().get(0)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(10).getImPdQuantityAssignedList().get(1)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(10).getImPdQuantityAssignedList().get(2)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(10).getImPdQuantityAssignedList().get(3)).isEqualTo(5000d);	
		assertThat(imDpQuantityMappingItems.get(10).getImPdQuantityAssignedList().get(4)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(10).getImPdQuantityAssignedList().get(5)).isEqualTo(0d);	
		assertThat(imDpQuantityMappingItems.get(10).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(10).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);
		
		assertThat(imDpQuantityMappingItems.get(11).getImPdQuantityAssignedList().get(0)).isEqualTo(12382d);
		assertThat(imDpQuantityMappingItems.get(11).getImPdQuantityAssignedList().get(1)).isEqualTo(12382d);
		assertThat(imDpQuantityMappingItems.get(11).getImPdQuantityAssignedList().get(2)).isEqualTo(6191d);	
		assertThat(imDpQuantityMappingItems.get(11).getImPdQuantityAssignedList().get(3)).isEqualTo(6191d);	
		assertThat(imDpQuantityMappingItems.get(11).getImPdQuantityAssignedList().get(4)).isEqualTo(6191d);	
		assertThat(imDpQuantityMappingItems.get(11).getImPdQuantityAssignedList().get(5)).isEqualTo(6191d);	
		assertThat(imDpQuantityMappingItems.get(11).getImPdQuantityAssignedList().get(6)).isEqualTo(0d);
		assertThat(imDpQuantityMappingItems.get(11).getImPdQuantityAssignedList().get(7)).isEqualTo(0d);*/
	
	}
	
	/*@Test
	void itShouldMap_IM_qtyAssignedList(){
		
		//given: DpToAnalyzersMapping with calculated dpToAnalyzersMappingItems elements
		ProductLineDTO productLineDTO = createProductLineDTO();
		DpToAnalyzersMapping dpToAnalyzersMapping = underTestService.mapDpToAnalysers(productLineDTO);
		
		//when: calling mapQuantities method of DpToAnalyzersMapping
		List<DpNumberAndDpQuantity> dpNumberAndDpQuantities = new ArrayList<>();
		
	}*/
	
	private ProductLineDTO createProductLineDTO() {
		//Given 
				ProductLineDTO productLineDTO = new ProductLineDTO();
				List<LineDTO> lines = new ArrayList<>();
				
				LineDTO line1 = new LineDTO();
				line1.setId(1L);
				line1.setLineNumber(1);
				
				ModuleDTO module1_1 = new ModuleDTO();
				module1_1.setId(1L);
				module1_1.setSpecificModuleType(SpecificModuleType.S);
				ModuleDTO module1_2 = new ModuleDTO();
				module1_2.setId(2L);
				module1_2.setSpecificModuleType(SpecificModuleType.C);
				ModuleDTO module1_3 = new ModuleDTO();
				module1_3.setId(3L);
				module1_3.setSpecificModuleType(SpecificModuleType.C);
				ModuleDTO module1_4 = new ModuleDTO();
				module1_4.setId(4L);
				module1_4.setSpecificModuleType(SpecificModuleType.I);
				ModuleDTO module1_5 = new ModuleDTO();
				module1_5.setId(5L);
				module1_5.setSpecificModuleType(SpecificModuleType.i);
				ModuleDTO module1_6 = new ModuleDTO();
				module1_6.setId(6L);
				module1_6.setSpecificModuleType(SpecificModuleType.I);
				
				List<ModuleDTO> modules1 = new ArrayList<>();
				modules1.add(module1_1);
				modules1.add(module1_2);
				modules1.add(module1_3);
				modules1.add(module1_4);
				modules1.add(module1_5);
				modules1.add(module1_6);
				line1.setModules(modules1);
				
				
				

				LineDTO line2 = new LineDTO();
				line2.setId(2L);
				line2.setLineNumber(2);
				
				ModuleDTO module2_1 = new ModuleDTO();
				module2_1.setId(1L);
				module2_1.setSpecificModuleType(SpecificModuleType.S);
				ModuleDTO module2_2 = new ModuleDTO();
				module2_2.setId(2L);
				module2_2.setSpecificModuleType(SpecificModuleType.C);
				ModuleDTO module2_3 = new ModuleDTO();
				module2_3.setId(3L);
				module2_3.setSpecificModuleType(SpecificModuleType.C);
				ModuleDTO module2_4 = new ModuleDTO();
				module2_4.setId(4L);
				module2_4.setSpecificModuleType(SpecificModuleType.I);
				
				List<ModuleDTO> modules2 = new ArrayList<>();
				modules2.add(module2_1);
				modules2.add(module2_2);
				modules2.add(module2_3);
				modules2.add(module2_4);
				line2.setModules(modules2);
				
				
				
				LineDTO line3 = new LineDTO();
				line3.setId(3L);
				line3.setLineNumber(3);
				
				ModuleDTO module3_1 = new ModuleDTO();
				module3_1.setId(1L);
				module3_1.setSpecificModuleType(SpecificModuleType.S);
				ModuleDTO module3_2 = new ModuleDTO();
				module3_2.setId(2L);
				module3_2.setSpecificModuleType(SpecificModuleType.C);
				ModuleDTO module3_3 = new ModuleDTO();
				module3_3.setId(3L);
				module3_3.setSpecificModuleType(SpecificModuleType.I);
				ModuleDTO module3_4 = new ModuleDTO();
				module3_4.setId(4L);
				module3_4.setSpecificModuleType(SpecificModuleType.i);
				
				List<ModuleDTO> modules3 = new ArrayList<>();
				modules3.add(module3_1);
				modules3.add(module3_2);
				modules3.add(module3_3);
				modules3.add(module3_4);
				line3.setModules(modules3);
				
				
				
				LineDTO line4 = new LineDTO();
				line4.setId(4L);
				line4.setLineNumber(4);
				
				ModuleDTO module4_1 = new ModuleDTO();
				module4_1.setId(1L);
				module4_1.setSpecificModuleType(SpecificModuleType.S);
				ModuleDTO module4_2 = new ModuleDTO();
				module4_2.setId(2L);
				module4_2.setSpecificModuleType(SpecificModuleType.C);
				ModuleDTO module4_3 = new ModuleDTO();
				module4_3.setId(3L);
				module4_3.setSpecificModuleType(SpecificModuleType.i);
				ModuleDTO module4_4 = new ModuleDTO();
				module4_4.setId(4L);
				module4_4.setSpecificModuleType(SpecificModuleType.i);
				
				List<ModuleDTO> modules4 = new ArrayList<>();
				modules4.add(module4_1);
				modules4.add(module4_2);
				modules4.add(module4_3);
				modules4.add(module4_4);
				line4.setModules(modules4);
				
				
				

				LineDTO line5 = new LineDTO();
				line5.setId(5L);
				line5.setLineNumber(5);
				
				ModuleDTO module5_1 = new ModuleDTO();
				module5_1.setId(1L);
				module5_1.setSpecificModuleType(SpecificModuleType.CI);
				
				List<ModuleDTO> modules5 = new ArrayList<>();
				modules5.add(module5_1);
				line5.setModules(modules5);
				
				
				
				LineDTO line6 = new LineDTO();
				line6.setId(6L);
				line6.setLineNumber(6);
				
				ModuleDTO module6_1 = new ModuleDTO();
				module6_1.setId(1L);
				module6_1.setSpecificModuleType(SpecificModuleType.CI);
				
				List<ModuleDTO> modules6 = new ArrayList<>();
				modules6.add(module6_1);
				line6.setModules(modules6);
				
				
				
				LineDTO line7 = new LineDTO();
				line7.setId(7L);
				line7.setLineNumber(7);
				
				List<ModuleDTO> modules7 = new ArrayList<>();
				line7.setModules(modules7);
				
				
				LineDTO line8 = new LineDTO();
				line8.setId(8L);
				line8.setLineNumber(8);

				
				List<ModuleDTO> modules8 = new ArrayList<>();
				line8.setModules(modules8);	
				
				lines.add(line1);
				lines.add(line2);
				lines.add(line3);
				lines.add(line4);
				lines.add(line5);
				lines.add(line6);
				lines.add(line7);
				lines.add(line8);
				productLineDTO.setLines(lines);
				
		return productLineDTO;
	}

}
