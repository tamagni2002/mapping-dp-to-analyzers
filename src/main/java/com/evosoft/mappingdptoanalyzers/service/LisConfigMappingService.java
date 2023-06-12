package com.evosoft.mappingdptoanalyzers.service;

import java.util.List;

import com.evosoft.mappingdptoanalyzers.controller.ProductLineDTO;
import com.evosoft.mappingdptoanalyzers.utils.DpToAnalyzersMapping;
import com.evosoft.mappingdptoanalyzers.utils.NumbersAndLineNumbersChImMappingItem;

public interface LisConfigMappingService {

	DpToAnalyzersMapping mapDpToAnalysers(ProductLineDTO productLineDTO);

	DpToAnalyzersMapping mapDpQuantities(
			DpToAnalyzersMapping dpToAnalyzersMapping);

	DpToAnalyzersMapping imMapDpQuantities(DpToAnalyzersMapping dpToAnalyzersMapping);

}
