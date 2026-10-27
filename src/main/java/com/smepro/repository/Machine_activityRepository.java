package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.machine_activity;
import com.smepro.entity.payroll_record;
import com.smepro.models.edata;

public interface Machine_activityRepository extends CPlatformRepository<machine_activity, Long> {
	 
	@Modifying
	@Transactional
	@Query("UPDATE machine_activity m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
}
