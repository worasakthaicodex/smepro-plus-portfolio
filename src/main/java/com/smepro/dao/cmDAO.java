package com.smepro.dao;

import java.util.List;

import com.smepro.models.cm;


public interface cmDAO {
	List<cm> getall(int idrefer,String type);
	List<cm> getall(int idrefer,String type,String date);
	int delete(int id,String type);
	int save(cm cms);
	
	
	cm getbyid(int id);
	int getnum(int idrefer);
}
