package com.smepro.dao;

import java.util.List;

import com.smepro.models.group_authorities;



public interface group_authoritiesDAO {
	List<group_authorities> getall(int id,int idrefer);
	void save (String authority, int group_id);
	void set (List<String> authoritys,group_authorities group_authoritiess,int idrefer);
	group_authorities getbyId(int id,int idrefer);
	int update( group_authorities group_authoritiess,int idrefer);
	int delete(int id,int idrefer);
	List<group_authorities> getallfoldertype(String type);
	List<group_authorities> getalltype(String type);
	int deleteforfoldertype(String type);
	int deleteauthoritiesbyfolder(String Group_id,String type);
	void setauthoritiesbyfolder(List<String> data);
}
