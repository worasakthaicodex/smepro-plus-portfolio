package com.smepro.dao;

import java.util.List;

import com.smepro.models.report_pn;
import com.smepro.models.report_pn_pdf;
import com.smepro.models.showupload_pn;
import com.smepro.models.user;;



public interface report_pnDAO {
	List<report_pn> getall(String date);
	List<report_pn> getallreport(String date);
	List<report_pn> getallreportchart(String datestart,String dateend);
	List<report_pn> getall2(int id);
	List<report_pn> getall2(int id,String date);
	List<report_pn> getallrespon(int id,String date);
	List<report_pn> getallapp(int id,String date);
	List<report_pn> getallrespon(String year);
	List<report_pn> getallapp(String year);
	
	
	
	List<report_pn> getdateplan(int id,int statusid,String name);
	List<report_pn> getall2(int id,String date,int status);
	List<report_pn> getallreportbydateandsuccess(String datestart,String dateend,String name);
	List<report_pn> getallreportbydateanderror(String datestart,String dateend,String name);
	List<report_pn> getall3(String date, String alert);
	List<report_pn> getall4(String date, String alert);
	
	List<report_pn> getall5(String date, String alert);
	int setStatus2( int id,int status);
	
	
	List<report_pn_pdf> getreport_pn_sendstatus(String month,String year);
	List<report_pn_pdf> getreport_pn_statuserror(String month,String year);
	List<report_pn_pdf> getreport_pn_sendstatus(String year);
	List<report_pn_pdf> getreport_pn_statuserror(String year);
	
	
	void save (report_pn report_pns);
	void save2(report_pn report_pns);
	report_pn getbyId(int id);
	report_pn getbyIds(int id);
	report_pn getbyId2(int id,String date);
	report_pn getbytype2(int id);
	int update( report_pn report_pns);
	int settotal( int id);
	int setsendstatus( int id);
	int setappstatus( int id,int diff);
	int setappstatus( int id,int diff,String date);
	
	int setStatus( int id,int status);
	int setStatus(int id,int status,String date);
	int unsettotal(int total,int id);
	int setStatus( int id,int status,int success,int error);
	int checkStatus(int idrefer,int status,String date);
	int settotal2( int id);
	int update2( report_pn report_pns);
	int setalert1( int id);
	int setalert2( int id);
	int delete(int id);
	int deletes(int id);
	boolean isreportExists(int id,String date);
	boolean isreportExists(int id,String months,String years);
	boolean isreportExists(int id,int week, String years);
	 
	boolean isreportExists(int id);
	
	
	report_pn getsum(String date);
	report_pn getsummonth(String month,String year);
	report_pn getsumyear(String year);
	
	List<report_pn_pdf> getreporttopdf(String month,String year);
	List<report_pn_pdf> getreporttopdf(String year);
	
	
	
	int deletebyreport(int id,String date);
	int deletebyreport(int id);
	int deletebyupload_pn(int id,String date);
	
	report_pn getallstatus(String year,String respon);
	report_pn getallRadarD(String year,int due,String date);
	report_pn getallRadarM(String year,int due,String month);
	report_pn getallRadarW(String year,int due,int week);
	report_pn getallRadarY(String year);
	report_pn getallstatus(String year);
	
	int setdateapp( int id,String date);
	int setdateadd( int id,String date);
	
	
	
	List<report_pn> getallreportbydate(String datestart,String dateend);
	List<report_pn> getallreportbydate(String date);
	List<report_pn> getallreportbymonth(String month);
	List<report_pn> getallreportbyweek(int week);
	List<report_pn> getallreportbyyear(String year);
	
	int countDailyYeardue(String year,String username,int due);
	int countDailyYear(String year,String username,int due);
	
	int countDailyYeardue2(String year,String username);
	int countDailyYear2(String year,String username);
	
	
	
	 List<report_pn> getrank(String year);
	 List<report_pn> getrank2(String year);
	 
	 
	 List<report_pn> getall2(int id,String date,int status,String name);
	 List<report_pn> getall2(int eformid,int due,String date,int status,String name);
	 List<report_pn> getall2(int id,int eformid,int due,String date,int status,String name);
	 
	 
  List<report_pn> getall2(int eformid,int due,String date,int status);
  List<report_pn> getallrespon(int eformid,int due,String date);
  List<report_pn> getallapp(int eformid,int due,String date);
  List<report_pn> getdateplan(int eformid,int due,int statusid);
  
  
  int check(String dates,int report);
  int countYear(String year);
  int countYearupload(String year);
  int countYearuploadreject(String year);
  int countYearuploadsuccess(String year);
  int countYearuploadsuccess2(String year);
  int countYear(String year,String username);
  int countYearupload(String year,String username);
  int countYearuploadreject(String year,String username);
  int countYearuploadsuccess(String year,String username);
  int countYearuploadsuccess2(String year,String username);
  
  List<showupload_pn> getalltype(String year,int type);
  List<showupload_pn> getalltype(String year,int type,String username);
  
  
  int setalert(int id,int status);
  
  
  List<report_pn> rawreport(String year);
  List<report_pn> getallresponandapp(String user,String year);
  List<report_pn> rawreportfull(String datestart,String dateend,String respon,String app,int type,String sort); 
  
  int countDue(String year,String username,int due);
  int countDueSuccess(String year,String username,int due);
  
  int countDue2(String year,String username,int type);
  int countDueSuccess2(String year,String username,int type);
  
  int setStatusapp(int id,String date);
  int setStatusadd(int id,String date);
  int setStatusall(int id,int status);
  
  
  
  
  
  //new 
  void savebatch(List<report_pn> report_pn);
  int getPending(String user_name);
  int getAllPending(int id_refer, String user_name);
  int setApprove(int id_refer, String date_due, String date_check);
  
  
  List<report_pn> getAllDateRange(String datestart,String dateend);
int setSend(int id_refer, String date_due, String dateupload);
void updatebatch(List<report_pn> report_pn);
List<report_pn> getPage(int id, int page);
report_pn StatusCount(int id_refer);
List<report_pn> getPageWeeK(String date_start, String date_end, int page);
int setApproveSuccess(int id_refer, String date_due);
int setSendSuccess(int id_refer, String date_due);
List<report_pn> getPage(int id, int page, user users);
List<report_pn> getall2(int id, user users);
int CheckGoal(String date,String username,int idrefer);
}
