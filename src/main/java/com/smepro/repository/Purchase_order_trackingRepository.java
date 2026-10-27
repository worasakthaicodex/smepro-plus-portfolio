package com.smepro.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.purchase_order_tracking;
import com.smepro.entity.request_for_quotation;
import com.smepro.models.edata;

public interface Purchase_order_trackingRepository extends CPlatformRepository<purchase_order_tracking, Long> { 

	
	@Transactional
	@Query("UPDATE purchase_order_tracking m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
}
