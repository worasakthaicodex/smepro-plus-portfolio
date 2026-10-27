package com.smepro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.actionplanDAO;
import com.smepro.dao.taskDAO;
import com.smepro.models.Problem;
import com.smepro.models.Progress;
import com.smepro.models.actionplan;
import com.smepro.models.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class ProgressService {

	private taskDAO TaskDAO; 
	private actionplanDAO ActionplanDAO;
	private otherservice otherservices;
	private dateservice dateservice;
	
	private List<Progress> progress_all = new ArrayList<Progress>();
	private List<actionplan> actionplan_all = new ArrayList<actionplan>();
	private List<Problem> problem_all = new ArrayList<Problem>();
	private task task;
	String date;
	String dateTime;
	@Autowired
	public void setMyRepository(
		    		taskDAO TaskDAO,
		    		actionplanDAO ActionplanDAO,
		    		otherservice otherservices,
		    		dateservice dateservice
		   
		    		) {
	
		        this.TaskDAO = TaskDAO;
		        this.ActionplanDAO =ActionplanDAO; 
		        this.otherservices =otherservices; 
		        this.dateservice =dateservice; 
		    }
			
	
	
	
	public ProgressService initiate(){
		
		  LocalDateTime localDate = LocalDateTime.now();//For reference
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		  DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		date = localDate.format(formatter);
		dateTime = localDate.format(formatter2);
		
		//ล้างค่าก่อน
		progress_all.clear();
		actionplan_all.clear();
		return this;
	}
	
	public ProgressService forTask(task task){
		
		progress_all =  TaskDAO.getallProgressByTaskId(task.getTask_id());
		actionplan_all =  ActionplanDAO.getAll(task.getTask_id());
		problem_all =  TaskDAO.getallProblemByTaskId(task.getTask_id());
		this.task = task;
		
		
		assignTaskByType(task);
		return this;
	}
	//เเจกจ่ายงาน
	private void assignTaskByType(task task){
		//เมื่อเริ่มงานใหม่
		startTask(task);
		//เมื่อมามีการส่งงาน
		submitTask(task);
		//เมื่อมีการเปิดปัยหา
		openTaskIssue(task);
		//เมื่อปิดปัยหาทั้งหมดจบเเล้ว
		closeAllTaskIssues(task);
		//เมื่อมีการตรวจรับงาน
		reviewTask(task);
		//เมื่อมีการบันทึกโดยผู้ใช้
		activityTask(task);
		//เมื่อมีการปิดงาน
		completeTask(task);
	}
	
	
	public List<Progress> get(){
		List<Progress> list =  TaskDAO.getallProgressByTaskId(task.getTask_id());
		list.stream().forEach(row -> {
			
			String[] TimeAgo  = dateservice.toTimeAgo(row.getDate_log());
			row.setDate_log(TimeAgo[0]);
			row.setTimeAgo(TimeAgo[1]);
		});
		return list;
	}


	private void startTask(task task){
		
		//เช็คว่ามี Progress ยังเเล้ววันที่เข้างานถึงยัง สร้างไว้ position 0 
	    //สร้างกระบวนการเข้างานเเรก
		
		 if(progress_all.size() > 0) {return;}
		if(dateservice.Criteria_Upload(task.getTask_datestart(),date) == false) { return;}
		 
		
		  Progress newprogress = new Progress();
		  newprogress.setDate_log(task.getTask_datestart()+" 06:00");
		  newprogress.setTitle("task.startTask");
		  newprogress.setNote("");
		  newprogress.setType(1);
		  newprogress.setTask_id(task.getTask_id());
		  newprogress.setPositon(0);
		  TaskDAO.saveProgress(newprogress);
  
		  return;
	}

	
	private void submitTask(task task){
		
		//เช็คว่ามี มีการส่งงาน
		//สร้างกระบวนการบันทึกการส่งงานนั้น
		
		System.out.println("progress_all "+progress_all.size());
		System.out.println("actionplan_all "+actionplan_all.size());
		
		
		if(progress_all.size() == 0) {return;}
		if(actionplan_all.size() == 0) {return;}
			List<actionplan>  submit = actionplan_all.stream().filter( row-> row.getSubmission() != 2  ).filter( row-> row.getSubmission() != -1  ).collect(Collectors.toList());
		if(submit.size() == 0) {
			TaskDAO.delProgress(2,task.getTask_id());
			return;}
		System.out.println("submit "+submit.size());
		String submit_id = submit.stream().map(row->String.valueOf( row.getActionplan_id())).collect(Collectors.joining(","));
		
		
		Progress newprogress = new Progress();
		newprogress.setTitle("task.submitTask");
		newprogress.setPositon(1);
		 
		newprogress.setDate_log(dateTime);
		newprogress.setNote("");
		newprogress.setType(2);
		newprogress.setTask_id(task.getTask_id());
		newprogress.setId_refer(submit_id);
		

		TaskDAO.saveProgress(newprogress); 
	
	   
	}
	private void reviewTask(task task){
		
		//เช็คว่ามี การตรวจรับงาน
		//สร้างกระบวนการ
		if(progress_all.size() == 0) {return;}
		if(actionplan_all.size() == 0) {return;}
			List<actionplan>  submit = actionplan_all.stream().filter( row-> row.getSubmission() == 0).collect(Collectors.toList());
			List<actionplan>  review = actionplan_all.stream().filter( row-> row.getSubmission() == 1 ).collect(Collectors.toList());
		if(submit.size() > review.size() || review.size() == 0 ) {
			TaskDAO.delProgress(4,task.getTask_id());
			return;}
		 String review_id = review.stream().map(row->String.valueOf( row.getActionplan_id())).collect(Collectors.joining(","));

		 
		 Progress newprogress = new Progress();
		 newprogress.setTitle("task.reviewTask");
		  newprogress.setPositon(4);
		 
			newprogress.setDate_log(dateTime);
			newprogress.setNote("");
			newprogress.setType(2);
			newprogress.setTask_id(task.getTask_id());
			newprogress.setId_refer(review_id);
			TaskDAO.saveProgress(newprogress);
	}
	
	
	private void activityTask(task task){
		
		if(task.getActivity_task() == null  ) {
			TaskDAO.delProgress(4,task.getTask_id());
			return;}
		if(task.getActivity_task().isBlank()  ) {
			TaskDAO.delProgress(4,task.getTask_id());
			return;}
		
		
		  
		  Optional<Progress>  activity = progress_all.stream().filter( row-> row.getPositon() == 5 ).findFirst();
	      if(activity.isPresent()) {
	    	  Progress data = activity.get();
	    	  date = data.getDate_log();
	    	  }
		  
		
		    Progress newprogress = new Progress();
		    newprogress.setTitle("task.activityTask");
		    newprogress.setPositon(5);
			newprogress.setDate_log(dateTime);
			newprogress.setNote(task.getActivity_task());
			newprogress.setType(4);
			newprogress.setTask_id(task.getTask_id());
			TaskDAO.saveProgress(newprogress);
	}
	
	private void openTaskIssue(task task){
		//เช็คว่ามี เมื่อมีการเปิดปัยหา
		 //สร้างกระบวนการ
		if(progress_all.size() == 0) {return;}
		if(actionplan_all.size() == 0) {return;}
		if(problem_all.size() == 0) {
			TaskDAO.delProgress(2,task.getTask_id());
			return;
			}
		
		List<Problem>  openIssue = problem_all.stream().filter( row-> row.getStatus() == 1 ).collect(Collectors.toList());
		if(openIssue.size()  == 0 ) {return;}
			
			
		 Progress newprogress = new Progress();
		 newprogress.setTitle("task.openTaskIssue");
		  newprogress.setPositon(2);

				
				newprogress.setDate_log(dateTime);
				newprogress.setNote("");
				newprogress.setType(3);
				newprogress.setTask_id(task.getTask_id());
				TaskDAO.saveProgress(newprogress);
		  
		  
	}
	
	private void closeAllTaskIssues(task task){
		//เช็คว่ามี เมื่อปิดปัยหาทั้งหมดจบเเล้ว
		 //สร้างกระบวนการ   
		if(progress_all.size() == 0) {return;}
		if(actionplan_all.size() == 0) {return;}
		if(problem_all.size() == 0) {return;}
		
		List<Problem>  openIssue = problem_all.stream().filter( row-> row.getStatus() == 1 ).collect(Collectors.toList());
		List<Problem>  closeIssues = problem_all.stream().filter( row-> row.getStatus() == 2 ).collect(Collectors.toList());
		if( ( openIssue.size() > closeIssues.size() ) || closeIssues.size() == 0 ) {
			
			TaskDAO.delProgress(3,task.getTask_id());
			return;
			}
		
		 Progress newprogress = new Progress();
		 newprogress.setTitle("task.closeAllTaskIssues");
		  newprogress.setPositon(3);

				
				newprogress.setDate_log(dateTime);
				newprogress.setNote("");
				newprogress.setType(3);
				newprogress.setTask_id(task.getTask_id());
				TaskDAO.saveProgress(newprogress);
	}
	
	private void completeTask(task task){
		//เช็คว่ามี เมื่อปิดปัยหาทั้งหมดจบเเล้ว สร้างไว้ position สุดท้าย โดยลบกระบวนการนี้ก่อน 
		 //สร้างกระบวนการ  
		if(task.getTask_status() == 1) {
			
			TaskDAO.delProgress(6,task.getTask_id());
			return;}
		 Progress newprogress = new Progress();
		 newprogress.setTitle("task.completeTask");
		 newprogress.setPositon(6);

				
				newprogress.setDate_log(dateTime);
				newprogress.setNote(task.getEnd_task());
				newprogress.setType(1);
				newprogress.setTask_id(task.getTask_id());
				TaskDAO.saveProgress(newprogress);
	}
	
	
}
