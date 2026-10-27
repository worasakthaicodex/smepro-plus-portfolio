package com.smepro.dao;

import java.util.List;

import com.smepro.models.Log_login;
import com.smepro.models.log;
import com.smepro.models.showlog;



public interface logDAO {
	List<log> getall(int id);
	List<showlog> getall2();
	List<showlog> getallbyuserid(int userid);
	List<showlog> getallbyidadd(int id);
	List<showlog> getallbyidrefer(int id);
	List<showlog> getallbydarrefer(int id);
	List<showlog> getallbypn(int pn,String date);
	List<showlog> getallbypn2(int pn,String name);
	List<showlog> getallbypn(int pn);
	int getapp(int id,String date);
	void save(log logs);
	int saves(log logs);
	
	boolean check(int id,String date,String time);
	
	void setflow (int id,int status);
	void  setapp(int id);
	void setflow (int id,int status,String note);
	log getbyId(int id);
	int setplan(int upload_pnid,int ids);
	
	
	
	List<showlog> getalldate(String date,String step);
	List<showlog> getallfolder(String log_folder);
	
	int update( log logs);
	int delete(int id);
	int getuseronline(String date,String time);
	//List<log>  gettimeavg(String dates2);
	
	List<log> getuseronreadfilemaxD(String date);
	List<log> getuseronreadfilemaxM(String month,String year);
	List<log> getuseronreadfilemaxY(String year);
	
	List<log> getuseronreadfileminD(String date);
	List<log> getuseronreadfileminM(String month,String year);
	List<log> getuseronreadfileminY(String year);
	
	
	int  numuseronreadfiledownload(String month,String year);
	List<log> getuseronreadfiledownload();
	
	List<log> getallreadfileshortcut();
	List<log> getallreadfileshortcut(int refer);
	List<log> getallreadfileshortcutall(int refer);
	List<log> getallreadfileshortcutall2(int refer);
	
	List<log> getallreadfile();
	
	
	


	
	
	Log_login getLoginDate(String datess,String timestart,String timeend);
	Log_login getLoginMonth(String months,String years,String timestart,String timeend);
	Log_login getLoginYear(String years,String timestart,String timeend);
	
	
	int  countlevel(String year,String level);
	int  countLoginYear(String year,int userid);
	int  Numreport(int reportid,String due);
	int numstatus(int idrefer);
	
	
	
	log getLotOld(int log_id_add);
	List<showlog>  gettimebyleveldate(String dates2,int user_id);
	List<showlog> gettimebylevelmonth(String year, String month, int user_id);
	List<showlog> gettimebylevelyear(String year, int user_id);
	List<log> getPage(String Start, String End, int page);
	int getNum(String Start, String End);
}
