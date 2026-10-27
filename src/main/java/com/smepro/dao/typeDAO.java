package com.smepro.dao;

import java.util.List;

import com.smepro.models.type;



public interface typeDAO {
	List<type> getall();
	void save (type types);
	type getbytype(String type,int id);
	int update( String type,int status);
	int delete(int id);
}
