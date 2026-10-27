package com.smepro.dao;

import java.util.List;

import com.smepro.models.department;

public interface departmentDAO {
	List<department> getall();
	void save( department departments);
	int update( department departments);
	int delete( int id);
	department getbyId(int id);
	department getbyName(String name);
	List<department> findInAll(String idString);
}
