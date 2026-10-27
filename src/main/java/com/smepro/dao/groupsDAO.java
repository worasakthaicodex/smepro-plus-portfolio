package com.smepro.dao;

import java.util.List;

import com.smepro.models.groups;



public interface groupsDAO {

	List<groups> getall(int id);

	
	void save (groups groupss,int idrefer);
	groups getbyId(int id,int idrefer);
	groups getbyIds(int id);
	int update( groups groupss,int idrefer);
	int delete(int id,int idrefer);
	int getsum();


	List<groups> findInAll(String idString);
	
}
