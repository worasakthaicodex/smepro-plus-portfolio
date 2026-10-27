package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.payroll_record;
import com.smepro.models.edata;

public interface Payroll_recordRepository extends CPlatformRepository<payroll_record, Long> {

	
	@Transactional
	@Query("UPDATE payroll_record m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
	
	
	List<payroll_record> findByPayrollYearAndPayrollMonth(int year, int month);
	
	List<payroll_record> findByEmployeeIdAndPayrollYearAndPayrollMonth(
		    int employeeId, int year, int month);
	
	
	
	
}
