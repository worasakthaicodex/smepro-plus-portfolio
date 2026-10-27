package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.employee_shift;
import com.smepro.entity.fa_invoices;
import com.smepro.models.edata;

public interface Employee_shiftRepository extends CPlatformRepository<employee_shift, Long> { 

	
	@Transactional
	@Query("UPDATE employee_shift m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
}
