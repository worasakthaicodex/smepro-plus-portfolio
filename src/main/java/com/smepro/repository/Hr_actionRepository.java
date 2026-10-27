package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.hr_action;
import com.smepro.entity.workrule;
import com.smepro.models.edata;

public interface Hr_actionRepository extends CPlatformRepository<hr_action, Long> { 

	
	@Transactional
	@Query("UPDATE hr_action m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
}
