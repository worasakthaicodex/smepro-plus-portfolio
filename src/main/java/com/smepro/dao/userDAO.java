package com.smepro.dao;

import java.util.List;

import com.smepro.models.UserCode;
import com.smepro.models.user;



public interface userDAO {
	
	List<user> getall();
	List<user> getallsort();
	List<user> getall(int id,int idrefer);
	 List<user> getallStringid(String id) ;
	List<user> getallgroup(String authorities);
	
	int save (user users,int idrefer);
	user getbyId(int id,int idrefer);
	user getbyId2(int string);
	user getbyname(String name);
	user getbyusername(String username);
	user getbyusername2(String username);
	int update( user users , String username);
	int updatefull( user users , int id,int idrefer);
	int delete(int id,int idrefer);
	int getsum();
	boolean isuserExists(String username);
	int updateusername( user users , String username);
	int resetpassword(int id, String password);
	void setnewpassword(String newpassword, int user_id);
	int countlevel(String level);
	
	void setbyAuth(String Auth,String username);
	user getbyAuth(String Auth);
	
	
	void resetprofileimage(String username);
	void setprofileimage(String username,String img);
	List<user> getRandom();
	List<user> getSearch(String name, String position, String department);
	List<user> findAll();
	List<UserCode> findAllUserCode();
	void deleleUserCode(int id);
	UserCode findUserCodeById(int id);
	void editUserCode(UserCode UserCodes);
	void saveUserCode(UserCode UserCodes);
	UserCode findUserCodeDepartment(int id);
	
	
	List<user> findInAll(String id);
}
