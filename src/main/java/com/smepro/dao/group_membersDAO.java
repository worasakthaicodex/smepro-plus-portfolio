package com.smepro.dao;

import java.util.List;

import com.smepro.models.group_members;



public interface group_membersDAO {
	List<group_members> getall(String username);
	void save (String username);
	void save (String username,int groupid);
	group_members getbyId(String username);
	int update( group_members group_memberss,int idrefer);
	int delete(String username);
	int updateusername(String username,String username2);
	
	
}
