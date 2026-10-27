package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_workrule;
import com.smepro.models.edata;

public interface Edata_assetsRepository extends CPlatformRepository<edata_assets, Long>{

	edata_assets findByEdataId(String EdataId);
	
	@Transactional
	@Query("UPDATE edata_assets m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

}
