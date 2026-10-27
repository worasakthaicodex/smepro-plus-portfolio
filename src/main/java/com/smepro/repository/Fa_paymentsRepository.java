package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.account_ledger;
import com.smepro.entity.fa_payments;
import com.smepro.models.edata;

public interface Fa_paymentsRepository extends CPlatformRepository<fa_payments, Long> { 

	
	@Transactional
	@Query("UPDATE fa_payments m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);
	
	
	@Query("""
		    SELECT f FROM fa_payments f
		    LEFT JOIN f.edata e
		    WHERE e.processId = :processId
		    AND f.fpPaymentDate LIKE CONCAT(:yearMonth, '%')
		""")
		List<fa_payments> findByProcessIdAndYearMonth(
		    @Param("processId") int processId,
		    @Param("yearMonth") String yearMonth
		);
	
	@Query("""
		    SELECT f FROM fa_payments f
		    LEFT JOIN f.edata e
		    WHERE e.processId = :processId
		    AND f.fpPaymentDate LIKE CONCAT(:year, '%')
		""")
		List<fa_payments> findByProcessIdAndYear(
		    @Param("processId") int processId,
		    @Param("year") String year
		);
}
