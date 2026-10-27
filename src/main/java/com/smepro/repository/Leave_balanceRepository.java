package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.leave_balance;
import com.smepro.entity.workrule;
import com.smepro.models.edata;

public interface Leave_balanceRepository extends CPlatformRepository<leave_balance, Long> {

	
	@Transactional
	@Query("UPDATE leave_balance m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
}
