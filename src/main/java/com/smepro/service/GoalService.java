package com.smepro.service;




import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.attendanceDAO;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eflowDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.holidayDAO;
import com.smepro.dao.jobDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.entity.Policy;
import com.smepro.entity.PolicyMember;
import com.smepro.models.Goal;
import com.smepro.models.check_log;
import com.smepro.models.holiday;
import com.smepro.models.missed_uploads;
import com.smepro.models.upload_log;
import com.smepro.models.upload_pn;
import com.smepro.repository.PolicyRepository;

@Service
public class GoalService {

	
	private jobDAO JobDAO;
	private workflowDAO WorkflowDAO;
	private edataDAO EdataDAO;
	private holidayDAO HolidayDAO;
	private PolicyRepository policyRepository;
	private otherservice Otherservice;
	
    @Autowired
    public void setMyRepository(
    		jobDAO JobDAO,
    		workflowDAO WorkflowDAO,
    		edataDAO EdataDAO,
    		holidayDAO HolidayDAO,
    		PolicyRepository policyRepository,
    		otherservice Otherservice
    		){
    		this.JobDAO = JobDAO;
    		this.WorkflowDAO = WorkflowDAO;
    		this.EdataDAO = EdataDAO;
    		this.HolidayDAO = HolidayDAO;
    		this.policyRepository = policyRepository;
    		this.Otherservice = Otherservice;
    	}
    
    
	
    public void createGoalByUploadPlans(upload_pn upload_pns) {
    	
    	List<Goal> goalAll = new ArrayList<>();
    	if(upload_pns.getId_policy() > 0) {
    	 	 Policy policy  = policyRepository.findById(upload_pns.getId_policy());
    		 createGoalPolicy(goalAll,upload_pns, policy);
    	}else {
    		createGoal(goalAll,upload_pns);
    	}
    	
    	
    	WorkflowDAO.delGoalAll(upload_pns.getUpload_pn_id());
    	 WorkflowDAO.saveGoalBatch(goalAll,100);

    }
    
    private void createGoal(List<Goal> goalAll, upload_pn upload_pns) {
    	if(upload_pns == null) { return;}
    	if(upload_pns.getUpload_pn_respon() == null) { return;}
    	
    	String[] name = upload_pns.getUpload_pn_respon().split(",");
    	
    	for(String Responsible : name) {

    		Goal  goal = new Goal();
    	    goal.setName("-");
    	    goal.setProcesses_id(upload_pns.getUpload_pn_id());
    	    goal.setQuantity_per_action(1);
    	    goal.setTaskBehaviortypes("FIXED_REPEAT");
    	    

    	    if(upload_pns.getUpload_pn_type() == 0 ) {
		       		if(upload_pns.getUpload_pn_due() == 0 ) { goal.setFrequency("EVERY_DAY"); }
		    		else if(upload_pns.getUpload_pn_due() == 1 ) { 
		    			
		    			goal.setFrequency("EVERY_WEEK");
		        	    goal.setSetto(upload_pns.getUpload_pn_setdue());
		    		
		    		}
		    		else if(upload_pns.getUpload_pn_due() == 2 ) { 
		    			goal.setFrequency("EVERY_MONTH");
		    		    goal.setSetto(upload_pns.getUpload_pn_setdue2());
		    		}
		    	    
    	    }else{
    	    	goal.setFrequency("IRREGULAR");
    	    }

    	    goal.setAlert_before_days(1);
    	    goal.setResponsible(Responsible);
    	    goal.setTables("upload_pn");
    	    
    	    goalAll.add(goal);
    	}
    	
	
	}



	private void createGoalPolicy(List<Goal> goalAll, upload_pn upload_pns, Policy policy) {
		// TODO Auto-generated method stub
		if(upload_pns == null) { return;}
    	if(policy.getMembers().size() == 0) { return;}
    	
    	for(PolicyMember Responsible : policy.getMembers()) {

    		Goal  goal = new Goal();
    	    goal.setName("-");
    	    goal.setProcesses_id(upload_pns.getUpload_pn_id());
    	    goal.setQuantity_per_action(1);
    	    goal.setTaskBehaviortypes("FIXED_REPEAT");
    	    

    	    if(upload_pns.getUpload_pn_type() == 0 ) {
		       		if(upload_pns.getUpload_pn_due() == 0 ) { goal.setFrequency("EVERY_DAY"); }
		    		else if(upload_pns.getUpload_pn_due() == 1 ) { 
		    			
		    			goal.setFrequency("EVERY_WEEK");
		        	    goal.setSetto(upload_pns.getUpload_pn_setdue());
		    		
		    		}
		    		else if(upload_pns.getUpload_pn_due() == 2 ) { 
		    			goal.setFrequency("EVERY_MONTH");
		    		    goal.setSetto(upload_pns.getUpload_pn_setdue2());
		    		}
		    	    
    	    }else{
    	    	goal.setFrequency("IRREGULAR");
    	    }

    	    goal.setAlert_before_days(1);
    	    goal.setResponsible(Responsible.getEmployeeName());
    	    goal.setTables("upload_pn");
    	    goalAll.add(goal);
    	}
    	
	}



	public void checkUploads() {
    	
    	
    	System.out.println("Stat 1  -----------checkUploads--------------");
    	
    	LocalDate lastCheckedDate  = getlastCheckedDate();
    	checkAndHandleUploads(lastCheckedDate);
        recordCheckLog();
    }
    
    
    private LocalDate getlastCheckedDate() {
    	
    	System.out.println("Stat 2  -----------getlastCheckedDate--------------");
    	
        check_log check_log = JobDAO.CheckLog();
    	
        if(check_log == null) {
        	return LocalDate.now();
        }
        String checkedDateStr = check_log.getChecked_date();
       

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lastCheckedDate = LocalDate.parse(checkedDateStr, formatter);

        return lastCheckedDate;
    }

    

    private void checkAndHandleUploads(LocalDate lastCheckedDate) {
    	
     	System.out.println("Stat 3  -----------checkAndHandleUploads--------------");
    	
    	
     	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
    	List<Goal> Goal = WorkflowDAO.getAllGoal();
    	LocalDate currentDate = LocalDate.now();
	   	String year = currentDate.format(formatter);
	   	 	   	
	   	 
    	List<holiday> Holiday  =  HolidayDAO.getall(year);
    	Set<LocalDate> holidayDates = Holiday.stream()
    		    .map(h -> LocalDate.parse(h.getHoliday_date()))
    		    .collect(Collectors.toSet());
    
    	
    	LocalDate dateToCheck = lastCheckedDate;
    	while (!dateToCheck.isAfter(currentDate)) {
    	    // ข้ามวันอาทิตย์และวันหยุด
    	    if (dateToCheck.getDayOfWeek().getValue() == 7 || holidayDates.contains(dateToCheck)) {
    	        System.out.println("วันที่ " + dateToCheck + " เป็นวันหยุดหรือวันอาทิตย์ ข้ามการตรวจสอบ");
    	    } else {
    	        // ประกาศ finalDate สำหรับใช้ใน lambda
    	        final LocalDate finalDate = dateToCheck;
    	        
    	        System.out.println("Stat 3.1  -----------check EVERY--------------");
    	        System.out.println("Stat 3.2  -----------Goal --------------"+Goal.size());
    	        
    	        // ตรวจสอบการอัพโหลด
    	        Goal.forEach(row -> {
    	            String date_due = null;
    	            switch (row.getFrequency()) {
    	                case "EVERY_MONTH":
    	                    System.out.println("Stat 3.2  -----------EVERY_MONTH--------------");
    	                    date_due = every_month(finalDate, row);
    	                    break;
    	                case "EVERY_WEEK":
    	                    date_due = every_week(finalDate, row);
    	                    break;
    	                case "EVERY_YEAR":
    	                    date_due = every_year(finalDate, row);
    	                    break;
    	                default:
    	                    date_due = finalDate.toString();
    	            }

    	            if (hasUpload(date_due, row) == 1) {
    	                handleUploadedFiles(date_due, row);
    	                System.out.println("Stat 4.1  -----------handleUploadedFiles--------------");
    	                
    	            }else if (hasUpload(date_due, row) == 0) {
    	                handleMissedUploads(date_due, row);
    	                System.out.println("Stat 4.2  -----------handleMissedUploads--------------");
    	            }else {

    	                System.out.println("Stat 4.3  -----------Not Due--------------");
    	            }
    	        });
    	    }

    	    dateToCheck = dateToCheck.plusDays(1);
    	}

    }
    
    private String every_year(LocalDate dateToCheck, Goal row) {
    	String date_due = null;
    	
    	  String days = row.getSetto() == null ? "01-01" : row.getSetto(); // default: 1 ม.ค.
    	    String[] parts = days.split("-");
    	    int month = Integer.parseInt(parts[0]);
    	    int day = Integer.parseInt(parts[1]);
    	    int year = dateToCheck.getYear();
    	    
    	    // แก้กรณีวันผิด เช่น 31 ก.พ.
    	    LocalDate tryDate = LocalDate.of(year, month, 1);
    	    int maxDay = tryDate.lengthOfMonth();
    	    day = Math.min(day, maxDay);
    	    
    	    date_due = LocalDate.of(year, month, day).toString();
    	
		return date_due;
	}


	private String every_week(LocalDate dateToCheck, Goal row) {
		String date_due = null;
		
		String dayText = row.getSetto() == null ? "จ" : row.getSetto();
	    Map<String, DayOfWeek> thaiDays = Map.of(
	        "จ", DayOfWeek.MONDAY,
	        "อ", DayOfWeek.TUESDAY,
	        "พ", DayOfWeek.WEDNESDAY,
	        "พฤ", DayOfWeek.THURSDAY,
	        "ศ", DayOfWeek.FRIDAY,
	        "ส", DayOfWeek.SATURDAY,
	        "อา", DayOfWeek.SUNDAY
	    );
	    DayOfWeek targetDay = thaiDays.getOrDefault(dayText, DayOfWeek.MONDAY); // fallback = จันทร์

	    // หา date_due เป็นวันในสัปดาห์ของ dateToCheck ที่ตรงกับ targetDay
	    date_due = dateToCheck.with(TemporalAdjusters.previousOrSame(targetDay)).toString();
		
		return date_due;
	}


	private String every_month(LocalDate dateToCheck, Goal row) {
	    
		String date_due = null;
		
		String days = row.getSetto() == null ? "1" : row.getSetto();
	    int year = dateToCheck.getYear();
	    int month = dateToCheck.getMonthValue();

	    System.out.println("Stat   -----------every_month--------------");
	    
	    if (days.equalsIgnoreCase("LAST")) {
	        date_due = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth()).toString();
	    } else {
	        int day = Integer.parseInt(days);
	        // แก้กรณีวันที่เกินวันในเดือน เช่น 31 ก.พ.
	        LocalDate tryDate = LocalDate.of(year, month, 1);
	        int maxDay = tryDate.lengthOfMonth();
	        day = Math.min(day, maxDay);
	        date_due = LocalDate.of(year, month, day).toString();
	    }
		return date_due;
	}

	
	

	private int hasUpload(String date_due, Goal row) {
		int status = 0;

	      System.err.println("date_due "+date_due);
		
	    // ตรวจสอบว่า date_due เป็น null หรือเปล่า
	    if (date_due != null && !date_due.isBlank()) {
	        try {
	            // แปลง string เป็น LocalDate (สมมุติรูปแบบเป็น yyyy-MM-dd)
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate dueDate = LocalDate.parse(date_due, formatter);
        
	            // เช็คว่าเลยกำหนดแล้วไหม
	         
	                int per = EdataDAO.CheckGoal(date_due, row);

	                if (per == 100) {
	                    status = 1;
	                } else if (per > 0) {
	                	
	                    handlePartialUploads(date_due, row,per);
	                    
	                }else {
	                	
	    	            System.err.println("per == 0  ");
	                	
	                	   if (dueDate.isBefore(LocalDate.now())) { 
	      	            	 status = -1;
	      	                }
	                }
	                
	        } catch (DateTimeParseException e) {
	            System.err.println("รูปแบบวันที่ไม่ถูกต้อง: " + date_due);
	            // หรือโยน exception ขึ้นไปก็ได้
	        }
	    }

	    return status;
	}
	
    private void handleUploadedFiles(String date_due, Goal row) {
        // จัดการกรณีที่อัปโหลดครบ
    	
        upload_log upload_log = new upload_log();
    	String Responsible = row.getResponsible() == null  ? "-" :  row.getResponsible();
        upload_log.setUpload_date(date_due);
        upload_log.setGoal_id(row.getId());
        upload_log.setUser_name(Responsible);
        upload_log.setStatus(100);
        
	    JobDAO.SavehandleUploadedFiles(upload_log);
    }
    
    private void handlePartialUploads(String date_due, Goal row, int per) {
        // จัดการกรณีที่อัปโหลดไม่ครบ
        upload_log upload_log = new upload_log();
    	String Responsible = row.getResponsible() == null  ? "-" :  row.getResponsible();
    	
    	
        upload_log.setUpload_date(date_due);
        upload_log.setGoal_id(row.getId());
        upload_log.setUser_name(Responsible);
        upload_log.setStatus(per);
        
        JobDAO.SavehandleUploadedFiles(upload_log);
        	
    	
    }
    
    private void handleMissedUploads(String date_due, Goal row) {
        
    	
    	String Responsible = row.getResponsible() == null  ? "-" :  row.getResponsible();
     	
    	// จัดการกรณีที่ไม่อัปโหลด
    	missed_uploads missed_uploads = new missed_uploads();    	
    	missed_uploads.setMissed_due(date_due);
    	missed_uploads.setGoal_id(row.getId());
    	missed_uploads.setUser_name(Responsible);
   		JobDAO.SaveMissedUploads(missed_uploads);
   		
   		
    }

    
    
    private void recordCheckLog() {
    	 JobDAO.SaveLog();
    }
    
    

    
    
}