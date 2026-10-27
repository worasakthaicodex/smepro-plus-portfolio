package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.edata_workrule;
import com.smepro.entity.employee_compensation;
import com.smepro.models.edata;

public interface Edata_workruleRepository extends CPlatformRepository<edata_workrule, Long>{
	
	@Transactional
	@Query("UPDATE edata_workrule m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

}
