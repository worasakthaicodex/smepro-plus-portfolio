package com.smepro.dao;

import java.util.List;

import com.smepro.models.attendance;





public interface attendanceDAO {
	List<attendance> getall(int user_id);
	List<attendance> getall(String month,String year);
	List<attendance> getallflow(String month,String year,String alluser);
	List<attendance> getall(String dt_datestart,String dt_dateend,List<String> selectuser);
	int delete(int user_id,String date);
	attendance getbyId(int user_id,String date);
	void checkin(attendance attendances);
	void checkout(attendance attendances);
}
