package com.smepro.dao;

import java.util.List;

import com.smepro.models.eflow;


public interface eflowDAO {
	List<eflow> getall(int idrefer);
	int save(int id);
	List<eflow> getall();
	eflow getbyid(int id);
	int delete(int id);
	int clear();
	int save(eflow eflows);
	void set(List<String> workflow_eform,List<String> workflow_name,int id
			,List<String> eflow_key1,List<String> eflow_key2
			,List<String> eflow_key3,List<String> eflow_key4);
	int update(eflow eflows);
	
}
