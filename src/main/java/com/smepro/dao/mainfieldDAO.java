package com.smepro.dao;

import java.util.List;

import com.smepro.models.mainfield;

public interface mainfieldDAO {

	  void save(mainfield mainfields);
	  void update(mainfield mainfields);
	  mainfield getbyid(int id);
	  List<mainfield> getall(int idrefer,String type,String inputtype);
	  List<mainfield> getall(int idrefer);
	  int delete(int id);
	  int getsum(long l,String type);
	  
	  
}

