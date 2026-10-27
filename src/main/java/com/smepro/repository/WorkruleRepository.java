package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.workrule;
import com.smepro.entity.workrule_leave_log;
import com.smepro.models.edata;

public interface WorkruleRepository extends CPlatformRepository<workrule, Long> {

	workrule findByRuleNameAndRuleType(String rule_name, String rule_type); 
	
	
	@Modifying
	@Transactional
	@Query("UPDATE workrule m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

	List<workrule> findTop500ByOrderByIdDesc();


	List<workrule> findAllByTempIdAndShiftStartTimeIsNotNull(int edataId);
	List<workrule> findAllByTempIdAndAnnualLeaveDaysIsNotNull(int edataId);
	List<workrule> findAllByTempIdAndThresholdMinutesIsNotNull(int edataId);
	List<workrule> findAllByTempIdAndCourseNameIsNotNull(int edataId);
	

	
}


