package com.smepro.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.smepro.dao.actionplanDAO;
import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.models.Keyactivity;
import com.smepro.models.Problem;
import com.smepro.models.ProjectProgress;
import com.smepro.models.Taskview;
import com.smepro.models.task;
import com.smepro.models.user;

@Service
public class TaskService {

	
	private int Attribute_step  = 0;
	private long all_task= 0;
	private long pending_task= 0;
	private LocalDate currentDate;
	
	
	
	
	
	  Taskview taskview = new Taskview();
	  List<task> list = new ArrayList<task>();

	  
	  private  Map<String, Object> modelData = new HashMap<>();
	  List<Keyactivity> keyactivitys = new ArrayList<Keyactivity>();
		 
	  private taskDAO TaskDAO; 
	  private  upload_pnDAO Upload_pnDAO;
	  private  dateservice Dateservice;
	  
	  private ProjectProgress progress = new ProjectProgress();
	  
	  
	  @Autowired
		public void setMyRepository(
			    		taskDAO TaskDAO,
			    		upload_pnDAO Upload_pnDAO,
			    		dateservice Dateservice
			   
			    		) {
		
			        this.TaskDAO = TaskDAO;
			        this.Upload_pnDAO =Upload_pnDAO; 
			        this.Dateservice =Dateservice; 
		
			    }
	  
	  
	public TaskService initiate(){
  		currentDate = LocalDate.now();
		Attribute_step = 0;
		all_task= 0;
		list.clear();
		modelData.clear();
		keyactivitys.clear();
		
		return this;
	}

	public TaskService loadAllTask(int id){
		list = TaskDAO.getall(id);
		Attribute_step =1;
		
		list.stream().forEach(row->{

		    	
				 List<Problem> myproblem_list = TaskDAO.getallProblemByTaskId(row.getTask_id());
				 List<Problem> myproblem_list_notclose = myproblem_list.stream().filter(rows ->rows.getStatus() == 1).collect(Collectors.toList());
				 row.setCount_problems(myproblem_list_notclose.size());
				 
				 
				 
	 			 LocalDate startDate = LocalDate.parse(row.getTask_datestart());
	 			 LocalDate endDate = LocalDate.parse(row.getTask_dateend());
	 			 
	 			 if (!endDate.isAfter(currentDate.minusDays(1)) && row.getTask_status() == 1) {
	             	row.setTask_alert(2);
	             } 
	 			 else  if (!startDate.isAfter(currentDate) && row.getTask_status() == 1) {
	            	row.setTask_alert(1);
	            } else {
	                System.out.println("Task start date (" + startDate + ") is after the current date (" + currentDate + ").");
	            }
	            
	            
	            
	 		});
		
		
		return this;
	}
	
	public TaskService loadTaskview(int projectid,user usersed){
		
		  taskview =   Upload_pnDAO.getTaskview(projectid,usersed.getUser_id());
		  Attribute_step =2;
		  

		  System.out.println(" taskview***>  "+ taskview.getViews() );
		  
		  
		  
		  if(taskview != null) {
		    	
		    	if(taskview.getViews() == 1) {
		    		list = list.stream()
		    				.filter(row-> 
		    					
		    					( row.getTask_iduser() != null && row.getTask_iduser().contains(usersed.getUser_name()) ) 
		    					||( row.getTask_idadd() == usersed.getUser_id() )
		    					||( row.getTask_idcheck() == usersed.getUser_id() )
		    				 )
		    				.collect(Collectors.toList());
		    	}
		    	else if(taskview.getViews() == 2) {
		    		
		    		list = list.stream()
		    				.filter(row-> 
	    					
	    					( row.getTask_iduser() != null && row.getTask_iduser().contains(usersed.getUser_name()) ) 
	    					||( row.getTask_idadd() == usersed.getUser_id() )
	    					||( row.getTask_idcheck() == usersed.getUser_id() )
	    				 )
		    				.filter(row->row.getTask_alert() > 0)
		    				.collect(Collectors.toList());
		    		
		    		
		    		
		    	}
		    	else if(taskview.getViews() == 3) {
		    		list = list.stream()
		    				.filter(row->row.getTask_alert() == 1)
		    				.collect(Collectors.toList());
		    		
		    	}
	    	
	    }  
		return this;
	}	
	
	
	 
	
	
	
	public TaskService countAllTask(){
				all_task = list.size(); 
				Attribute_step =3;
				return this;
	}
	public TaskService countPendingTask(){
				pending_task = list.stream().filter(row->row.getTask_alert() > 0).count();
				 Attribute_step =4;
				return this;
	}
	
	public TaskService UpdateStatus(){
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);  
	   	 
		 list.stream().forEach(row->{
		    	
		    	if(row.getTask_status() != 2 ) {
		    		
					if(Dateservice.Criteria_Check(date, row.getTask_dateend())) {
		    			
		        		if(Dateservice.Criteria_Check(row.getTask_datestart(), date)) {
		        			row.setTask_status(1);
					}else {
							row.setTask_status(0);
						}
		    			
						
					}else {
						row.setTask_status(-1);
					}
				}
		    	
		    });
		    TaskDAO.updateBatchStatus(list);
		return this;
}
	
	
	
	
	
	
	
	public TaskService loadKeyactivity(int projectid){
		 keyactivitys = Upload_pnDAO.getAllKeyactivity(projectid);
		 
 
		 
		    keyactivitys.forEach(ka ->{
		    	
		    	List<task> Alltask =  list.stream()
	          .filter(t -> t.getKeyactivity_id() == ka.getId())
	          .toList();
		        ka.setTasks(Alltask);
		        ka.setTask(Alltask.size());
		        
		      int success_count =  (int) list.stream().filter(t -> t.getKeyactivity_id() == ka.getId()).filter(t -> t.getTask_status() == 2).count();
			      if(Alltask.size() > 0) {
			    	 int Process =  (success_count * 100)  / Alltask.size();
			    	  ka.setProcess( Process);
			    
			      }
		    });
		    
		    
			  Upload_pnDAO.updateBatchStatus(keyactivitys);
		    
		    Attribute_step =5; 
		    return this;
	}		
	
	public TaskService Progress(String date_start,String date_end){
		
		
		LocalDate start = LocalDate.parse(date_start);
		LocalDate end = LocalDate.parse(date_end);
		LocalDate today = LocalDate.now(); // หรือ LocalDate.parse(date) จากโค้ดเดิม
		
		long totalDays = ChronoUnit.DAYS.between(start, end);     // จำนวนวันทั้งหมดของช่วง
		long passedDays = ChronoUnit.DAYS.between(start, today);  // จำนวนวันที่ผ่านมา
		double percent = (passedDays * 100.0) / totalDays;
		// ป้องกันค่า <0 หรือ >100
		percent = Math.max(0, Math.min(100, percent));
		
		double result = keyactivitys.stream().filter(t -> t.getStatus() > 0).mapToDouble(t -> t.getProcess())
		        .average()
		        .orElse(0.0);
		
		
		progress.setPlan(Double.parseDouble(String.format("%.2f", percent)));
		progress.setResult(Double.parseDouble(String.format("%.2f", result)));
		
	
		
		System.out.println("percent"+result);
		
	    Attribute_step =6; 
		return this;
	}
	
	
	public  List<task> getTasks(){
				return list;
	}
	 public Map<String, Object> getModelData() {
	        return modelData;
	    }
	 
	 

	 
	 
    public TaskService exposeAs(String attribute) {
        switch (Attribute_step) {
            case 1 -> modelData.put(attribute, new ArrayList<>(list));
            case 2 -> modelData.put(attribute, taskview);
            case 3 -> modelData.put(attribute, all_task);
            case 4 -> modelData.put(attribute, pending_task);
            case 5 -> modelData.put(attribute,  new ArrayList<>(keyactivitys) );
            case 6 -> modelData.put(attribute,  progress );
        }
        return this;
    }
	
	
	
}
