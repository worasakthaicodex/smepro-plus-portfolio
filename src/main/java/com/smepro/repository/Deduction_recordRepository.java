package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.deduction_record;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.payroll_record;
import com.smepro.models.edata;

public interface Deduction_recordRepository extends CPlatformRepository<deduction_record, Long>{
	
	@Transactional
	@Query("UPDATE deduction_record m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
	
	
    List<deduction_record> findByDeductYearAndDeductMonth(int year, int month);
	List<deduction_record> findByEmployeeIdAndDeductYearAndDeductMonth(
		    int employeeId, int year, int month);

}
