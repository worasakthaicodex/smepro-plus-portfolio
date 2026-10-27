package com.smepro.dao;

import java.math.BigDecimal;
import java.util.List;

import com.smepro.models.Problem;
import com.smepro.models.Progress;
import com.smepro.models.backup;
import com.smepro.models.task;
import com.smepro.models.user;


public interface taskDAO {
	 List<task> getall(int idrefer,String name,int idadd);
	 List<task> getallresponandapp(String user_name,int idadd,String year);
	 List<task> getallstatus(int idrefer,int status,String name,int idadd);
	 int delete(int id);
	 int save(task tasks);
	 int update(task tasks);
	 int setteam(task tasks);
	 task getbyid(int id);
	 int setstatus(int id,int status);
	 int setcm(int num,int task_id);
	 int setalert(int num,int alert,int task_id);
	List<task> getallbyProject(user users);	 
	 List<task> getall(int idrefer);
	int getnum(int id_refer, int status, user users);
	
	int getnum(int id_refer, int status);
	
	int getnumbyProject(user users);
	int getnumnow(int id_refer, int status, user users);
	task getEnd(int id);
	task getStart(int id);
	List<task> getalloriginal(int idrefer, String name, int idadd);
	void saveoriginal_task(List<task> tasks);
	int getnumbyProjectNear(user users);
	int getnumnow(int id_refer, int status);
	List<task> getalloriginal(int idrefer);
	List<task> getallKeyactivityId(int keyactivity_id);
	void setTotalCost(int id, BigDecimal Usetotal);
	List<task> getallMasterBudgetId(int getallMasterBudgetId);
	int increaseProgress(task tasks);
	
	void saveProblems(Problem problem);
	List<Problem> getallProblemByActionplanId(int actionplan_id);
	Problem getProblemById(int id);
	int deleteProblems(Problem Problems);
	List<Problem> getallProblemByTaskId(int actionplan_id);
	int getCountProblemByProjectId(int project_id);
	int getCountProblemByProjectId(int project_id, int status);
	List<Progress> getallProgressByTaskId(int actionplan_id);
	void saveProgress(Progress Progress);
	
	int delProgress(int id, int task_id);
	int getCountAllStatus(int idrefer);
	void updateBatchStatus(List<task> upload_pn);
	
}

