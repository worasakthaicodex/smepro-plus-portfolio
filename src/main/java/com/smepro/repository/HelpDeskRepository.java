package com.smepro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smepro.entity.HelpDeskTicket;


public interface HelpDeskRepository  extends JpaRepository<HelpDeskTicket, Long>{

	List<HelpDeskTicket> findByRefId(int item_id);

	List<HelpDeskTicket> findByRefIdOrderByIdDesc(int item_id);
	List<HelpDeskTicket> findByStatus(String string);
	
	
	long countByRefIdAndStatus(Integer refId, String status);

	long countByStatus(String string);

	long countByprocessIdAndStatus(int processId, String status);

}
