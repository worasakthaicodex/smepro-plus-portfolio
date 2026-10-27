package com.smepro.dao;

import java.util.List;

import com.smepro.models.actionplan;
import com.smepro.models.holiday;


public interface holidayDAO {
	List<holiday> getall(String year);
	List<holiday> getall(String year,String type);
	int delete(String year);
	int copy(String oldyear,String year);
	void savebatch(List<String> holiday_date,List<String> holiday_name,List<String>  holiday_type,String holiday_year);
}
