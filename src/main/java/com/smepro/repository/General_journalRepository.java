package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.account_ledger;
import com.smepro.entity.general_journal;
import com.smepro.models.edata;


public interface General_journalRepository extends CPlatformRepository<general_journal, Long> { 
	
	@Transactional
	@Query("UPDATE general_journal m SET m.doc = :#{#edata.doc} WHERE m.edataId = :#{#edata.id}")
	void updateDocByEdataId(@Param("edata") edata edata);

	@Query(value = """
		    SELECT * FROM general_journal 
		    WHERE al_entry_date LIKE CONCAT(:yearMonth, '%')
		    """, nativeQuery = true)
		List<general_journal> findByYearMonthLike(@Param("yearMonth") String yearMonth);
		
		
		@Query(value = """
			    SELECT * FROM general_journal 
			    WHERE al_entry_date LIKE CONCAT(:year, '%')
			    """, nativeQuery = true)
			List<general_journal> findByYearLike(@Param("year") String year);
}
