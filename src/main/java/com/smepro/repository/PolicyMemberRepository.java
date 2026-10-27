package com.smepro.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.Policy;
import com.smepro.entity.PolicyMember;
import com.smepro.entity.account_ledger;
import com.smepro.enums.PolicyType;
import com.smepro.models.edata;


public interface PolicyMemberRepository extends CPlatformPartialRepository<PolicyMember, Long> { 

	PolicyMember findById(long id);
	void deleteById(long id);
	List<PolicyMember> findAllByIdEmployee(int IdEmployee);
	
}
