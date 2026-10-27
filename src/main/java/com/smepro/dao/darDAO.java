package com.smepro.dao;

import java.util.List;

import com.smepro.models.dar;
import com.smepro.models.darsum;
import com.smepro.models.document_no;
import com.smepro.models.showdar;



public interface darDAO {
	List<dar> getall();
	List<showdar> getallbyfile(String fileno);
	List<showdar> getallbyfile2(String fileno,int darid);
	List<dar> getall2( int id);
	List<dar> getall3( int id);
	List<dar> getall4( int id);
	int save (dar dars, int id);
	dar getbyId(int id);
	int update( dar dars);
	int updatenotecheck(  int id,String note);
	int updatenoteapp(  int id,String note);
	int flow( int id,int flow,int step);
	int check( int id,int flow,int step,int by);
	int app( int id,int flow,int step,int by);
	int uncheck( int id,int flow,int step,int by);
	int unapp( int id,int flow,int step,int by);
	int delete(int id,int idadd);
	int updateresponsible(int id,String nameadd,String namecheck,String nameapp);
	
	
	int gettotal();
	int gettotalunfinished();
	List<dar>  getdarunfinished();
	darsum getsubdar(String request,String years);
	darsum getsubdar(String request,String years1,String years2,String years3);
	int getmaindar(String years); 
	int getmaindar(int month,String years); 
	
	
	List<dar>  getalltotalunfinished();
	int gettotalbytype(String years,String type);
	
	
	
	List<document_no> getalldocument();
	document_no getdocumentbyId( int id);
	void setAutoid(int document_id ,int step);
	
	void savedocument(document_no document_nos);
	void editdocument(document_no document_nos);
	void deldocument(int document_id);
	List<dar> getallPending();
	int CountFlow1(int id);
	int CountFlow2(int id);
	int CountFlow3(int id);
	document_no getdocumentNO(int department, String doc);
	
}
