package com.smepro.repository;

import java.util.List;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.attendance_record;
import com.smepro.entity.deduction_record;
import com.smepro.entity.shift_record;
import com.smepro.entity.workrule;
import com.smepro.models.edata;

public interface Shift_recordRepository  extends CPlatformRepository<shift_record, Long> { 
	
	
	@Transactional
	@Query("UPDATE shift_record m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
	
	
	List<shift_record> findAllByEdataIdIsNotNullAndEdataIdNotAndShiftYearAndShiftMonth(int notEdataId, int shiftYear, int shiftMonth);
	List<shift_record> findAllByTempIdAndEmployeeNameIsNotNull(int TempId);
	
	
	List<shift_record>  findAllByShiftYearAndShiftMonth(int shiftYear, int shiftMonth);


	

	

}

