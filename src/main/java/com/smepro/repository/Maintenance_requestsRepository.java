package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.machine_activity;
import com.smepro.entity.maintenance_requests;
import com.smepro.models.edata;

public interface Maintenance_requestsRepository extends CPlatformRepository<maintenance_requests, Long> {
	
	@Transactional
	@Query("UPDATE maintenance_requests m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

}
