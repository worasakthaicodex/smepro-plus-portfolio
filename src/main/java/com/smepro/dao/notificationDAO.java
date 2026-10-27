package com.smepro.dao;

import java.util.List;

import com.smepro.models.notification;



public interface notificationDAO {
	List<notification> getall();
	List<notification> getallrefer(int id);
	List<notification> getallrefer2(int id);
	void save (notification notifications);
	notification getbyIdrefer(int id);
	notification getbyId(int id,String username);
	int setview(int id);
	int setviewByReport(int Report_pn_id,String username);
	int setviewByDar(int dar_id,String user_name);
	List<notification> getallrefer2(String username);
	List<notification> getallrefer(String username);
	int countnotification(String username,int read);
	
	
	int readall(String username);

	void readAll(String user_id, String access, int process_id);
	int countnotification(String id, String access, String allProcessId, int read);
	List<notification> getallrefer(String id, String access, String allProcessId);
	void saveAll(List<notification> notificationsList);
	List<notification> getallPolicies(String Policies);
}
