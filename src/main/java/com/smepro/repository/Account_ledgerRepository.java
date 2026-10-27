package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.account_ledger;
import com.smepro.models.edata;


public interface Account_ledgerRepository extends CPlatformRepository<account_ledger, Long> { 
	
	@Transactional
	@Query("UPDATE account_ledger m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

	
	@Query(value = """
		    SELECT * FROM account_ledger 
		    WHERE al_entry_date LIKE CONCAT(:yearMonth, '%')
		    """, nativeQuery = true)
		List<account_ledger> findByYearMonthLike(@Param("yearMonth") String yearMonth);
		
		
		
		@Query(value = """
			    SELECT * FROM account_ledger 
			    WHERE al_entry_date LIKE CONCAT(:year, '%')
			    """, nativeQuery = true)
			List<account_ledger> findByYearLike(@Param("year") String year);
}
