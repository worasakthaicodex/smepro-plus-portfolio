package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.request_for_quotation;
import com.smepro.entity.sales_followup;
import com.smepro.models.edata;

public interface Request_for_quotationRepository extends CPlatformRepository<request_for_quotation, Long> { 
	
	
	@Transactional
	@Query("UPDATE request_for_quotation m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

}
