package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.fa_invoices;
import com.smepro.entity.workrule;
import com.smepro.models.edata;

public interface Fa_invoicesRepository extends CPlatformRepository<fa_invoices, Long> { 
	
	@Transactional
	@Query("UPDATE fa_invoices m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

}
