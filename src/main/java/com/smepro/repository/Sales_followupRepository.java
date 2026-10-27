package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.sales_followup;
import com.smepro.entity.shift_record;
import com.smepro.models.edata;

public interface Sales_followupRepository extends CPlatformRepository<sales_followup, Long> { 
	
	
	@Transactional
	@Query("UPDATE sales_followup m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

	List<sales_followup> findAllByProcessIdAndCustomerCode(int processId, String customerCode);
	List<sales_followup> findAllByProcessId(Integer integer);

	List<sales_followup> findAllByProcessIdAndPromotionCode(Integer measurementProcessId, String valueOf);
	


}
