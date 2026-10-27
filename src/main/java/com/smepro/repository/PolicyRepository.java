package com.smepro.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.Policy;
import com.smepro.entity.account_ledger;
import com.smepro.entity.fa_payments;
import com.smepro.enums.PolicyType;
import com.smepro.models.edata;


public interface PolicyRepository extends CPlatformPartialRepository<Policy, Long> { 
	
	
	List<Policy> findAllByTypeAndCreateBy(PolicyType policyType,int CreateBy);
	
	
	@Query("""
		    SELECT DISTINCT p FROM Policy p
		    LEFT JOIN FETCH p.members m
		    WHERE p.type = :type AND p.createBy = :createBy
		""")
		List<Policy> findAllByTypeAndCreateByFetchMembers(
		    @Param("type") PolicyType policyType,
		    @Param("createBy") int createBy
		);
	
	
	List<Policy> findAllByType(PolicyType policyType);

		
	
	
	
	@Query("SELECT f FROM Policy f  LEFT JOIN FETCH f.members e WHERE f.id = :id AND f.createBy = :createBy")
	Policy findByIdAndCreateBy(@Param("id") long id ,  @Param("createBy") int createBy);
	
	@Query("SELECT f FROM Policy f  LEFT JOIN FETCH f.members e WHERE f.id = :id ")
	Policy findById(@Param("id") long id);


	
	
	
}
