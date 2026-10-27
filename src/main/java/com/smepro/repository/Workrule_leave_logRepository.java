package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.account_ledger;
import com.smepro.entity.workrule_leave_log;
import com.smepro.models.edata;

public interface Workrule_leave_logRepository extends CPlatformRepository<workrule_leave_log, Long> { 

	
	@Modifying
	@Transactional
	@Query("UPDATE workrule_leave_log m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

	workrule_leave_log findTop500ByWorkruleIdOrderByIdDesc(int id);
}
