package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.attendance_record;
import com.smepro.entity.company_annual_plan;
import com.smepro.entity.deduction_record;
import com.smepro.models.edata;

public interface Attendance_recordRepository  extends CPlatformRepository<attendance_record, Long>{
	@Transactional
	@Query("UPDATE attendance_record m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
	
	
    List<attendance_record> findByAttendanceYearAndAttendanceMonth(int year, int month);
	List<attendance_record> findByEmployeeIdAndAttendanceYearAndAttendanceMonth(
		    int employeeId, int year, int month);
}
