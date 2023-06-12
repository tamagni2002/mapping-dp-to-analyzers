package com.evosoft.mappingdptoanalyzers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evosoft.mappingdptoanalyzers.repository.DpDptLookupRepository;
import com.evosoft.mappingdptoanalyzers.repository.LisConfigMappingRepository;
import com.evosoft.mappingdptoanalyzers.service.DpDptLookupService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class DpDptLookupServiceImpl implements DpDptLookupService {
	
	@Autowired
	private DpDptLookupRepository dpDptLookupRepository;

}
