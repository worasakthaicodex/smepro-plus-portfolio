package com.smepro.dao;

import java.util.List;

import com.smepro.models.dataset;

public interface datasetDAO {

	void save(dataset datasets);
	int update(dataset datasets);
	int delete(int id);
	List<dataset> getall();
	List<dataset> getalls();
	List<dataset> getallmain(); 
	dataset getbyid(int id);
	dataset getMain(String label);
	List<dataset> getalls(int level);
	List<dataset> getallNotmain();

}
