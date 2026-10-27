package com.smepro.dao;

import java.util.List;

import com.smepro.models.dataeform;


public interface dataeformDAO {

	void save(dataeform dataeforms);
	int update(dataeform dataeforms);
	int delete(int id);
	List<dataeform> getall();
	dataeform getbyid(int id);
	dataeform getbydate(String datestart,String dateend,int id);
	dataeform getbycondition(int condition_id,int logid);
}
