package com.smepro.dao;

import java.util.List;

import com.smepro.models.Calendar;
import com.smepro.models.Keyactivity;
import com.smepro.models.MasterBudget;
import com.smepro.models.Milestone;
import com.smepro.models.Outcome;
import com.smepro.models.Resource;
import com.smepro.models.Risk;
import com.smepro.models.Taskview;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.Use_resources;
import com.smepro.models.jobview;
import com.smepro.models.showupload_pn;
import com.smepro.models.upload_pn;
import com.smepro.models.user;



public interface upload_pnDAO {
	List<upload_pn> getall();
	List<upload_pn> getall(int id);
	List<upload_pn> getallbyid(int id);
	List<showupload_pn> getallfull();
	List<showupload_pn> getallby(String name);
	List<showupload_pn> getallbys(String name);
	
	
	List<showupload_pn> getalls();
	List<showupload_pn> getalls(String name);

	List<showupload_pn> getallsed2();
	List<showupload_pn> getallsed2(String name);
	
	List<showupload_pn> getallsedgroup(String name);
	List<showupload_pn> getallsedgroup(String name,int due);
	
	
	showupload_pn getallnext(String name,int id);
	showupload_pn getallback(String name,int id);
	
	List<showupload_pn> getall2();
	 List<showupload_pn> getallfornewreport();
	List<showupload_pn> getalls2();
	List<showupload_pn> getalls3();
	List<showupload_pn> getalls4();
	List<showupload_pn> getalls5();
	int setStatus( int id,int status);
	int setstate(int state,int id);
	int save (upload_pn upload_pns);
	upload_pn getbyIdrefer(int id);
	
	int setActive(int id,String date);
	
	
	upload_pn getbyId(int id);
	upload_pn getbyreal( int monitor,int type,String name);
	upload_pn getbyreal( int monitor,String name,String name2,String name3) ;
	upload_pn getbyIdandAll(int monitor,int type);
	List<upload_pn> getallbymonitor( int monitor);
	List<showupload_pn> getallbymonitor1(int monitor,jobview jobviews);
	
	
	upload_pn getbymonitor( int monitor);
	
	
	int update( upload_pn upload_pns);
	int delete(int id);
	
	void savebatch(upload_pn upload_pns); 
	int changename(String name);
	int changenameapp(String name);
	
	int changename(String name,String oldname);
	int changenameapp(String name,String oldname);
	
	
	List<showupload_pn> useruploadjob();
    List<showupload_pn> userappjob();
    
    int setSetting(upload_pn upload_pns);
    int setSettingAll(upload_pn upload_pns);
    
    List<showupload_pn> getalled(String name,jobview jobviews);
    
    List<showupload_pn> getalleds(String name,jobview jobviews);
    
    List<showupload_pn> getall(String name,jobview jobviews);
    List<showupload_pn> getall2(String name,jobview jobviews);
    List<showupload_pn> getall3(String name,jobview jobviews);
    List<showupload_pn> getall2s(String name);
	  List<showupload_pn> getall1s(String name,jobview jobviews);
    
    jobview getView( String user_name);
    boolean isjobviewExists(String user_name);
    void setGroup(String condition_group,String id1,String id2,String id3,int type);
    void  saveview( jobview jobviews);
    void  setview( jobview jobviews);
    
    
    int getnum(String name,jobview jobviews);
    int getnum2(String name,jobview jobviews);
    int getnum3(String name,jobview jobviews);
    int getnum4(String name,jobview jobviews);
    
    
    UploadpnTurn findUploadpnTurn(int id_refer,String user_name);
    void turn(int turn,int id_refer,String user_name);
    void  setCategory(  upload_pn upload_pns);
    
    
    
    
    upload_pn getbyConditionsId(int id);
    
    
	void setGroup(String condition_group,String id,int type);
    List<upload_pn> getGroup();
	List<upload_pn> getallbywork(String user);
	List<upload_pn> getByDue_Type(int due, int type);
	List<upload_pn> getallByActivate();
	void updatebatch(List<upload_pn> upload_pn);
	upload_pn StatusCount(int type, int due);
	void setPop(int id);
	List<upload_pn> getallbyProject(String user);
	List<upload_pn> getallAccess(int monitor);
	void setProject(upload_pn upload_pns);
	int setRun(int state, int id);
	List<upload_pn> getByDue_Type(int due, int type, user users);
	upload_pn StatusCount(int type, int due, user users);
	List<upload_pn> getallbyProjects(String user);
	int delete(int id, int idadd);
	List<upload_pn> getallbywork(String user, int userid);
	upload_pn getbyId(int id, int idadd);
	void expired(String date);
	void start(String date);
	
	
    void  updateProject(upload_pn upload_pns);

    List<Outcome> getAllOutcome(int id);
	void saveOutcome(Outcome Outcomes);
	void deleteOutcome(Outcome outcome);
	
	
	void saveRisk(Risk risk);
	List<Risk> getAllRisk(int id);
	void deleteRisk(Risk risk);
	
	
	void deleteBudget(MasterBudget Budget);
	List<MasterBudget> getAllBudget(int id);
	void saveBudget(MasterBudget Budget);
	MasterBudget getMasterBudget(int id);
	
	void deleteMilestone(Milestone milestone);
	List<Milestone> getAllMilestone(int id);
	void saveMilestone(Milestone milestone);
	
	
	List<Keyactivity> getAllKeyactivity(int id);
	void saveKeyactivity(Keyactivity keyactivity);
	void deleteKeyactivity(Keyactivity keyactivity);
	
	
	void setTaskview(Taskview taskview, int view);
	int saveTaskview(int upload_id, int user_id, int view);
	Taskview getTaskview(int upload_id, int user_id);
	
	
	void saveResource(Resource resource);
	List<Resource> getAllResourceBytypeAndTaskId(String type, int taskId);
	void deleteResource(Resource resource);
	Resource getResourceById(int id);
	List<Resource> getAllResource(int upload_pn_id);
	

	Resource getUse_resources(int id);
	List<Resource> getAllResourceUse(int id);
	void deleteUse_resources(Use_resources resource);
	void setUse_resources(int actual, int id);
	Use_resources getResourceUse(int id);
	void saveUse_resources(Use_resources use_resources);
	Keyactivity getKeyactivity(int id);

	Milestone getMilestone(int id);
	Risk getRisk(int id);
	List<upload_pn> getallbyProject(String user, int userid);
	List<Keyactivity> getAllKeyactivity();
	void updatebatchKeyactivitys(List<Keyactivity> upload_pn);
	void updateBatchStatus(List<Keyactivity> list);
	List<Calendar> getAllCalendarByIndexId(int id);
	List<Calendar> getAllCalendarIndex();
	List<Calendar> getAllCalendarByname(String name);
	List<Calendar> getAllCalendarBynames(String name);
	void deleteCalendar(int id);
	List<Calendar> getAllCalendarDue();
	
	
	int countCalendar(String name, int alert, int status);
	int countCalendar(String name);
	int countCalendar(String name, int status);
	List<Calendar> getAllCalendarBynameAndIndexid(String name, int eform_id);
	void updateCalendarStatus(int id);
	Calendar CalendarLatest(int eform_id, int status);
	Calendar getCalendar(int ids);
	List<Calendar> getAllCalendar();

	
	
}
