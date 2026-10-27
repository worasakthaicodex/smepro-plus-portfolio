package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.models.edata;

public interface Edata_annual_planRepository extends CPlatformRepository<edata_annual_plan, Long>{
	@Transactional
	@Query("UPDATE edata_annual_plan m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
}
