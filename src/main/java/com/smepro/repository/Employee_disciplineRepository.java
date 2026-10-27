package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.employee_discipline;
import com.smepro.entity.employee_shift;
import com.smepro.models.edata;

public interface Employee_disciplineRepository  extends CPlatformRepository<employee_discipline, Long>{
	
	@Transactional
	@Query("UPDATE employee_discipline m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

}
