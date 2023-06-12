package com.evosoft.mappingdptoanalyzers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evosoft.mappingdptoanalyzers.entity.LisConfigMapping;
import com.evosoft.mappingdptoanalyzers.utils.DpNumberAndDpQuantity;

public interface LisConfigMappingRepository extends JpaRepository<LisConfigMapping, Long> {

	List<DpNumberAndDpQuantity> findAllDpNumberAndDpQuantity();

}
