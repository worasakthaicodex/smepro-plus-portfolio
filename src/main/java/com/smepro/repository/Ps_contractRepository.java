package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.ps_contract;
import com.smepro.entity.purchase_order_tracking;
import com.smepro.models.edata;

public interface Ps_contractRepository extends CPlatformRepository<ps_contract, Long> {
	
	@Transactional
	@Query("UPDATE ps_contract m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

}
