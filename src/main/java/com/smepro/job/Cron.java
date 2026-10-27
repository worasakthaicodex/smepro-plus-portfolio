package com.smepro.job;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.smepro.dao.edataDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.holidayDAO;
import com.smepro.dao.jobDAO;
import com.smepro.dao.logDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.roleDAO;
import com.smepro.dao.setmailDAO;
import com.smepro.dao.settingDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.models.edata;
import com.smepro.models.file;
import com.smepro.models.folder;
import com.smepro.models.holiday;
import com.smepro.models.log;
import com.smepro.models.notification;
import com.smepro.models.report_pn;
import com.smepro.models.setmail;
import com.smepro.models.setting;
import com.smepro.models.showupload_pn;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.repository.PolicyRepository;
import com.smepro.service.AutoGradeService;
import com.smepro.service.CalendarService;
import com.smepro.service.GamificationService;
import com.smepro.service.GmailTextMsgSendWithoutSSL;
import com.smepro.service.GoalService;
import com.smepro.service.ProjectService;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;
import com.smepro.service.workflowservice;




@Component
public class Cron {
	

	
	@Autowired
	userservice Userservice;
	
	@Autowired
	userDAO UserDAO;
	
	@Autowired
	group_membersDAO Group_membersDAO;
	
	@Autowired
	roleDAO RoleDAO;
	
	@Autowired
	logDAO LogDAO;
	
	@Autowired
	fileDAO FileDAO;
	
	@Autowired
	upload_pnDAO Upload_pnDAO;
	
	@Autowired
	report_pnDAO Report_pnDAO;

	@Autowired
	setmailDAO SetmailDAO;
	
	@Autowired
	GmailTextMsgSendWithoutSSL gmailTextMsgSendWithoutSSL;
	
	@Autowired
	notificationDAO NotificationDAO;
	
	@Autowired
	otherservice otherservices;
	
	@Autowired
	settingDAO SettingDAO;
	
	@Autowired
	holidayDAO HolidayDAO;
	
	@Autowired
	workflowDAO WorkflowDAO;
	
	@Autowired
	edataDAO EdataDAO;
	
	
	@Autowired
	workflowservice Workflowservice;
	
	@Autowired
	GamificationService GamificationService;
	
	@Autowired
	CalendarService calendarService;
	
	@Autowired
    AutoGradeService autoGradeService;
	
	
	private ProjectService projectService;
	
	
	
	 @Autowired
	    public void setMyRepository(
	    		ProjectService projectService
	    		){
	    		this.projectService = projectService;
	    	}
	    
	
	
	
	
	 @PostConstruct
	    public void init() {
	        System.out.println(">>> เริ่มต้นระบบ: กำลังส่งงาน Init ไปทำเบื้องหลัง...");
	        
	        // สร้าง Thread ใหม่จับโยนไปทำเบื้องหลังทันที
	        new Thread(() -> {
	            try {
	                // ให้รอสัก 10 วินาที ค่อยเริ่มทำงาน (เพื่อให้แน่ใจว่าระบบหลักสตาร์ทเสร็จสมบูรณ์ 100% แล้ว)
	                Thread.sleep(10000); 
	                
	                System.out.println(">>> [Background Thread] เริ่มรันงาน Init...");
	                runCheckGamification();
	                runCheckProject();
	                runAutoGrade();
	                System.out.println(">>> [Background Thread] รันงาน Init เสร็จสิ้น");
	                
	            } catch (Exception e) {
	                System.err.println(">>> [Background Thread] Error: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }).start(); // คำสั่ง .start() คือการปล่อยให้ Thread แยกตัวออกไปทำงาน
	        
	        System.out.println(">>> เริ่มต้นระบบ: ส่งงานไปทำเบื้องหลังเรียบร้อย ระบบหลักทำงานต่อได้เลยไม่โดนบล็อค!");
	    }
	
	@Scheduled(fixedDelay = 60*60*1000) 
	public void expired() {
		 log logs = new log();
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference
		 LocalDateTime today  = LocalDateTime.now();//For reference
		 today = today.plusDays(7);
		
		 
		 
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
	   	 String date2 = today.format(formatter);
	   	 String time = localDate.format(formatter2);
	   	 
		List<file> files = FileDAO.getall3(date);
		for (file file : files) {
		
			
			logs.setLog_id_add(0);
			logs.setLog_step("Expired");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("เอกสารหมดอายุ  " + file.getFile_no()+" "+file.getFile_name_original());
			logs.setLog_refer(file.getFile_no());
			logs.setLog_folder(file.getFolder_name());
			LogDAO.save(logs);
	     }
	
		FileDAO.expired(date);
		List<file> files2 = FileDAO.getall3(date2);
		notification notifications = new notification();
		for (file file : files2) {
			notifications.setNotification_iduser(null);
			notifications.setNotification_iduser2(null);
			notifications.setNotification_alert("เอกสาร"+ file.getFile_no()+" "+file.getFile_name() + " กำลังจะหมดอายุวันที่ " + file.getFile_dateend() );
			notifications.setNotification_date(date);
			notifications.setNotification_read(0);
			notifications.setNotification_type("Expired doc");
			NotificationDAO.save(notifications);
	     }
		
		Upload_pnDAO.expired(date);
		Upload_pnDAO.start(date);
	}
	
	
	
	
	@Scheduled(cron = "0 0 21 * * *")
    public void runAutoGrade() {
        System.out.println("----------- AutoGrade Started -----------");
        try {
            // ส่งค่า LocalDate ปัจจุบันเข้าไปแทน null
            // หรือถ้าคุณต้องการให้เช็คย้อนหลัง 1 วันเสมอ ให้ใช้ LocalDate.now().minusDays(1)
        	autoGradeService.processDaily(LocalDate.now());
            
        } catch (Exception e) {
            System.err.println("AutoGrade Batch Error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("----------- AutoGrade Ended -----------");
    }
    
	
	
	
	// รันทุกวันเวลา 09:00 และ 16:00
    @Scheduled(cron = "0 0 9,16 * * *")
    public void runCheckGamification() {
        System.out.println("----------- Check Goal App Started -----------");
        try {
            // ส่งค่า LocalDate ปัจจุบันเข้าไปแทน null
            // หรือถ้าคุณต้องการให้เช็คย้อนหลัง 1 วันเสมอ ให้ใช้ LocalDate.now().minusDays(1)
            GamificationService.processDailyGamification(LocalDate.now());
            
        } catch (Exception e) {
            System.err.println("Gamification Batch Error: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("----------- Check Goal App Ended -----------");
    }
    
    
	
	@Scheduled(cron = "0 0 6,18 * * *")
	public void runCheckProject() {
			System.out.println(" -----------Check project--------------");
			projectService.check();
		    System.out.println("End -----------Check project--------------");
	}	
	
	
	@Scheduled(cron = "0 0 5,20 * * *")
	public void runCheckCalendar() {
			System.out.println(" -----------Check Calendar--------------");
			calendarService.check();
		    System.out.println("End -----------Check Calendar--------------");
	}	

		
		
		/*	
	@Scheduled(fixedDelay = 60000) 
	@RequestMapping(value="/setStatus" , method = RequestMethod.GET)
	public void setStatus() throws ParseException {
		
		List<showupload_pn> showupload_pns = Upload_pnDAO.getall2();
		 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate2.format(formatter2);
		
		
		for (showupload_pn upload_pn : showupload_pns) {
			
			
			
			int status = Report_pnDAO.checkStatus(upload_pn.getUpload_pn_id(),1,date);

				if(status == 0) {
					int status2 = Report_pnDAO.checkStatus(upload_pn.getUpload_pn_id(),2,date);
						if(status2 == 0) {
							int status3 = Report_pnDAO.checkStatus(upload_pn.getUpload_pn_id(),3,date);
							
							if(status3 > 0) {
								//set 3
								Upload_pnDAO.setStatus(upload_pn.getUpload_pn_id(), 3);
							}else {
								Upload_pnDAO.setStatus(upload_pn.getUpload_pn_id(), 0);
							}
							
						}else {
							Upload_pnDAO.setStatus(upload_pn.getUpload_pn_id(), 2);
						}
				
				}else {
					        Upload_pnDAO.setStatus(upload_pn.getUpload_pn_id(), 1);
				}
				
			}
		
		
	}



	@Scheduled(fixedDelay = 3600000) 
	@RequestMapping(value="/setTraceabilityStatus" , method = RequestMethod.GET)
	public void setTraceabilityStatus(){
		System.out.println(" -----------setTraceabilityStatus--------------");
		Workflowservice.processAndSaveStatus();
	    System.out.println("End -----------setTraceabilityStatus--------------");
    }

	*/
	
	
	
	
	
	
	/*
	
	
		//@Scheduled(cron = "0 0 6,19 * * *") 6:00 น. และ 19:00 น. ทุกวัน
		@Scheduled(fixedDelay = 60000) 
		@RequestMapping(value="/checks" , method = RequestMethod.GET)
		public void checks() throws ParseException {
			
			 LocalDateTime localDate = LocalDateTime.now().minusMonths(1);//For reference
			 LocalDateTime today  = LocalDateTime.now();//For reference

		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 String date = localDate.format(formatter);
		   	 String date2 = today.format(formatter);
			
		    List<report_pn> report_pns = Report_pnDAO.getAllDateRange(date,date2);
		    
			    report_pns.stream().forEach(row->{
			    	
			    	    if(row.getReport_pn_dateupload() != null) {
			    	    	row.setReport_pn_sendstatus(1);
			    	    	   if(row.getReport_pn_dateupload() == row.getReport_pn_date()	) {
					    	    	row.setReport_pn_statussccess(1);
					    	    }
			    	    }
			    	    
			    	    if (row.getReport_pn_datecheck() != null) {
			    	        row.setReport_pn_appstatus(1);
			    	        // แปลง String เป็น LocalDate
			    	        LocalDate dateCheck = LocalDate.parse(row.getReport_pn_datecheck(), formatter);
			    	        LocalDate reportDate = LocalDate.parse(row.getReport_pn_date(), formatter);

			    	        // ตรวจสอบความต่างของวันไม่เกิน 1 วัน
			    	        long daysDiff = ChronoUnit.DAYS.between(dateCheck, reportDate);
			    	        if (Math.abs(daysDiff) <= 1) {
			    	            row.setReport_pn_app_statussccess(1);
			    	        }
			    	    } 
			    });
			    Report_pnDAO.updatebatch(report_pns);
		   	 
		}
		*/

	/*
	//@Scheduled(cron = "0 0 6,19 * * *") 6:00 น. และ 19:00 น. ทุกวัน
	@Scheduled(fixedDelay = 60000) 
	@RequestMapping(value="/checks" , method = RequestMethod.GET)
	public void checks() throws ParseException {
		
		  System.out.println("checks Run...............");
		  
		  
		  
		 LocalDateTime localDate = LocalDateTime.now();
		 DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
		 String year = localDate.format(formatteryear);
		 List<holiday> holidays = HolidayDAO.getall(year);
		 
	
		List<showupload_pn> showupload_pns = Upload_pnDAO.getallfornewreport();
		setting settings = SettingDAO.getbyid();

		 report_pn report_pns = new report_pn();
			LocalDateTime today = LocalDateTime.now();//For reference
	    	DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	DateTimeFormatter formatters4 = DateTimeFormatter.ofPattern("dd");
	    	String dates = today.format(formatters);
	    	String day = today.format(formatters4);
	    	
	    	
	    	
	    	
	    	LocalDate localDateToday = today.toLocalDate();
	    	 boolean checked = true;
	    	if(holidays.size() > 0) {
				   boolean isHoliday = holidays.stream()
					        .anyMatch(holiday -> {
					            String holidayDateString = holiday.getHoliday_date();
					            LocalDate holidayDate = LocalDate.parse(holidayDateString, formatters);
					            return holidayDate.isEqual(localDateToday);
					        });

					if (isHoliday) {
						checked = false;
					} 
				}  	
	    	
	    	
	    	
	    boolean check = true;
		for (showupload_pn upload_pn : showupload_pns) {
			check = checked;	
			report_pn reports = Report_pnDAO.getbyId2(upload_pn.getUpload_pn_id(),dates);
	   
	   
	   System.out.println("showupload_pn "+upload_pn.getUpload_pn_id());
	   System.out.println("check "+check);
	 
	   if(reports == null && check == true) { 	 
		   System.out.println("showupload_pn--- "+upload_pn.getUpload_pn_id());
		                 
		   
		   
		   
							if(upload_pn.getUpload_pn_due() == 0) {
		
								if (today.getDayOfWeek() == DayOfWeek.SATURDAY && settings.getHoliday_notsun() > 0 ) {
									check = false;
								} 
								
								
								if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
									check = false;
								}
								
							}
							
						  else if(upload_pn.getUpload_pn_due() == 1) {
							
						   check = false;	
						   
						   
						   System.out.println("getUpload_pn_setdue "+upload_pn.getUpload_pn_setdue());
						   System.out.println("getDayOfWeek "+today.getDayOfWeek()); 
						   
							if(!upload_pn.getUpload_pn_setdue().equals("0")) {

								  		if(upload_pn.getUpload_pn_setdue().equals("W-1") && today.getDayOfWeek() == DayOfWeek.MONDAY) {
								  			check =true;
								  			}
								  		else if(upload_pn.getUpload_pn_setdue().equals("W-2") && today.getDayOfWeek() == DayOfWeek.TUESDAY) {
								  			check =true;
								  			}
										else if(upload_pn.getUpload_pn_setdue().equals("W-3") && today.getDayOfWeek() == DayOfWeek.THURSDAY) {
											check =true; 				
												  			}
										else if(upload_pn.getUpload_pn_setdue().equals("W-4") && today.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
											check =true;	
											}
										else if(upload_pn.getUpload_pn_setdue().equals("W-5") && today.getDayOfWeek() == DayOfWeek.FRIDAY) {
											check =true;	
										}
										else if(upload_pn.getUpload_pn_setdue().equals("W-6") && today.getDayOfWeek() == DayOfWeek.SATURDAY) {
											check =true;
										}
										else if(upload_pn.getUpload_pn_setdue().equals("W-7") && today.getDayOfWeek() == DayOfWeek.SUNDAY) {
											check =true;
										}
										

					            	
					        	 }
				        		 		
							
						}else{
								check = false;
					     	 if(upload_pn.getUpload_pn_setdue().equals(day)) {
					     		 check = true; 
					     	 }
  
						}
			        	 
							
						
							
							
						  	if(check == true) {	 
				        		 report_pns.setReport_pn_date(dates);   
				            	 report_pns.setReport_pn_target(1);
				            	 report_pns.setReport_pn_total(0);
				            	 report_pns.setReport_pn_idrefer(upload_pn.getUpload_pn_id());
				            	 report_pns.setReport_pn_status(1);
				            	 report_pns.setReport_pn_department(upload_pn.getUpload_pn_department());
				            	 report_pns.setReport_pn_respon(upload_pn.getUpload_pn_respon());
				            	 report_pns.setReport_pn_app(upload_pn.getUpload_pn_app());
				            	Report_pnDAO.save(report_pns); //1
				        	}
        	 
	   } 
        	 
 
			
		} 
	}
	
	*/
	
	
	
	
	/*
	
	@Scheduled(cron = "0 0 6,19 * * *")
	@RequestMapping(value="/checks2" , method = RequestMethod.GET)
	public void checks2() throws ParseException {
		
		  System.out.println("checks Run...............");
		  
		  
		  
		 LocalDateTime localDate = LocalDateTime.now();
		 DateTimeFormatter formatteryear = DateTimeFormatter.ofPattern("yyyy");
		 String year = localDate.format(formatteryear);
		 List<holiday> holidays = HolidayDAO.getall(year);
		 
	
		List<showupload_pn> showupload_pns = Upload_pnDAO.getallfornewreport();
		setting settings = SettingDAO.getbyid();

		 report_pn report_pns = new report_pn();
			LocalDateTime today = LocalDateTime.now().minusDays(1);//For reference
	    	DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	DateTimeFormatter formatters4 = DateTimeFormatter.ofPattern("dd");
	    	String dates = today.format(formatters);
	    	String day = today.format(formatters4);
	    	
	    	
	    	
	    	
	    	LocalDate localDateToday = today.toLocalDate();
	    	 boolean checked = true;
	    	if(holidays.size() > 0) {
				   boolean isHoliday = holidays.stream()
					        .anyMatch(holiday -> {
					            String holidayDateString = holiday.getHoliday_date();
					            LocalDate holidayDate = LocalDate.parse(holidayDateString, formatters);
					            return holidayDate.isEqual(localDateToday);
					        });

					if (isHoliday) {
						checked = false;
					} 
				}  	
	    	
	    	
	    	
	    boolean check = true;
		for (showupload_pn upload_pn : showupload_pns) {
			check = checked;	
			report_pn reports = Report_pnDAO.getbyId2(upload_pn.getUpload_pn_id(),dates);
	   
	   
	   System.out.println("showupload_pn "+upload_pn.getUpload_pn_id());
	   System.out.println("check "+check);
	 
	   if(reports == null && check == true) { 	 
		   System.out.println("showupload_pn--- "+upload_pn.getUpload_pn_id());
		                 
		   
		   
		   
							if(upload_pn.getUpload_pn_due() == 0) {
		
								if (today.getDayOfWeek() == DayOfWeek.SATURDAY && settings.getHoliday_notsun() > 0 ) {
									check = false;
								} 
								
								
								if (today.getDayOfWeek() == DayOfWeek.SUNDAY) {
									check = false;
								}
								
							}
							
						  else if(upload_pn.getUpload_pn_due() == 1) {
							
						   check = false;	
						   
						   
						   System.out.println("getUpload_pn_setdue "+upload_pn.getUpload_pn_setdue());
						   System.out.println("getDayOfWeek "+today.getDayOfWeek()); 
						   
							if(!upload_pn.getUpload_pn_setdue().equals("0")) {

								  		if(upload_pn.getUpload_pn_setdue().equals("W-1") && today.getDayOfWeek() == DayOfWeek.MONDAY) {
								  			check =true;
								  			}
								  		else if(upload_pn.getUpload_pn_setdue().equals("W-2") && today.getDayOfWeek() == DayOfWeek.TUESDAY) {
								  			check =true;
								  			}
										else if(upload_pn.getUpload_pn_setdue().equals("W-3") && today.getDayOfWeek() == DayOfWeek.THURSDAY) {
											check =true; 				
												  			}
										else if(upload_pn.getUpload_pn_setdue().equals("W-4") && today.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
											check =true;	
											}
										else if(upload_pn.getUpload_pn_setdue().equals("W-5") && today.getDayOfWeek() == DayOfWeek.FRIDAY) {
											check =true;	
										}
										else if(upload_pn.getUpload_pn_setdue().equals("W-6") && today.getDayOfWeek() == DayOfWeek.SATURDAY) {
											check =true;
										}
										else if(upload_pn.getUpload_pn_setdue().equals("W-7") && today.getDayOfWeek() == DayOfWeek.SUNDAY) {
											check =true;
										}
										

					            	
					        	 }
				        		 		
							
						}else{
								check = false;
					     	 if(upload_pn.getUpload_pn_setdue().equals(day)) {
					     		 check = true; 
					     	 }
  
						}
			        	 
							
						
							
							
						  	if(check == true) {	 
				        		 report_pns.setReport_pn_date(dates);   
				            	 report_pns.setReport_pn_target(1);
				            	 report_pns.setReport_pn_total(0);
				            	 report_pns.setReport_pn_idrefer(upload_pn.getUpload_pn_id());
				            	 report_pns.setReport_pn_status(1);
				            	 report_pns.setReport_pn_department(upload_pn.getUpload_pn_department());
				            	 report_pns.setReport_pn_respon(upload_pn.getUpload_pn_respon());
				            	 report_pns.setReport_pn_app(upload_pn.getUpload_pn_app());
				            	Report_pnDAO.save(report_pns); //1
				        	}
        	 
	   } 
        	 
 
			
		} 
	}
	
	
	
	
	
	
	@Scheduled(fixedDelay = 60*60*1000) 
	@RequestMapping(value="/alert" , method = RequestMethod.GET)
	public void alert() throws ParseException, MessagingException {
		
		 report_pn report_pns = new report_pn();
		 LocalDateTime localDate = LocalDateTime.now();//For reference
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String[] target = null;	
		 
		//เเจ้งเตือนก่อน 1 วัน
		  System.out.println("Get due start");
		 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate2.format(formatter2);
	   	 String datetime = localDate2.format(formatter3);
	 	 Date startDate = formatter.parse(date);
	 	 
	 	 
  	 Calendar start = Calendar.getInstance();
  	 start.setTime(startDate);
  	 start.add(Calendar.DATE, 1);
  	 Date getdate = start.getTime();
  	 String dates2 = formatter.format(getdate);
  	 
  	 
  	 
	   	 List<report_pn> report_pn = Report_pnDAO.getall3(dates2,"1");
	   	 
	   	System.out.println("Alert Num : "+report_pn.size());
	 	System.out.println("dates : "+dates2); 
	 	   for (report_pn  report : report_pn) {
	 		
	 		   
	 		  System.out.println("report_pn ID : " +report.getReport_pn_id());
	 		  
	 		  String  pn_name = report.getUpload_pn_name();
  	 	 
   	 	   if(report.getReport_pn_target() > 1 ) {
   	 		pn_name += ","+report.getUpload_pn_name2();
   	    	}
   	    	if(report.getReport_pn_target() > 2 ) {
   	    		pn_name += ","+report.getUpload_pn_name3();
   	    	}
   	    	if(report.getReport_pn_target() > 3 ) {
   	    		pn_name += ","+report.getUpload_pn_name4();
   	    	}
   	    	if(report.getReport_pn_target() > 4) {
   	    		pn_name += ","+report.getUpload_pn_name5();
   	    	}
	 		  
	 		  

	 			notification  notifications = new notification();
	 			notifications.setNotification_iduser(report.getReport_pn_respon());
				notifications.setNotification_iduser2(null);
				notifications.setNotification_alert("คุณมีเอกสารที่ต้องอัพโหลด -- ชื่อเอกสาร : " + pn_name + " กำหนดอัพโหลด : " +report.getReport_pn_date());
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_type("Job queue");
				notifications.setNotification_refer(String.valueOf(report.getReport_pn_id()));
				NotificationDAO.save(notifications);
				Report_pnDAO.setalert(report.getReport_pn_id(),1);
				
				
				if(report.getUser_email() != null) {
					if(report.getUser_email() != "") {
						user users =new user();
						setmail setmails = new setmail();
			    	    setmail mailconfig =  SetmailDAO.getbyId(1);
			    		setmails.setUsername(mailconfig.getUsername());
			    		setmails.setPassword(mailconfig.getPassword());
			    		setmails.setHostname(mailconfig.getHostname());
			    		setmails.setStarttls_port(mailconfig.getStarttls_port());
			    		setmails.setStarttls(mailconfig.isStarttls());
			    		setmails.setAuth(mailconfig.isAuth());
			    		setmails.setUrl(mailconfig.getUrl()+"/lohpn-filemanager?idrefer="+report.getReport_pn_idrefer()+"&&id="+report.getReport_pn_id());
			    		setmails.setSupport(mailconfig.getSupport());
			    		setmails.setNote("คุณมีเอกสารที่ต้องอัพโหลด -- ชื่อเอกสาร : " + report.getUpload_pn_name() + " กำหนดอัพโหลด : " +report.getReport_pn_date());
			    		setmails.setNote2("");
			     		users.setUser_email(report.getUser_email());
			    		users.setUser_name(report.getUser_name());
			    		
			    		if( report.getUser_email() != null && !report.getUser_email().isEmpty() && !report.getUser_email().equals("null")  ) {
			    		String sendmail = gmailTextMsgSendWithoutSSL.sendGmailendWithoutSSL(setmails,users,2);
			    		}
			    		
			    		
					}
					}
				
	 		
	 		Report_pnDAO.setalert1(report.getReport_pn_id());
	 	   }
	 	  System.out.println("Get due" + report_pn.size());
	 	
	 	  
	 	  //เเจ้งเตือนไม่พบรายงานหลังผ่านมาเเล้ว 
	 	 	 List<report_pn> report_pn2 = Report_pnDAO.getall4(date,"1");
		 	   for (report_pn  reports : report_pn2) {


		 		  String  pn_name = reports.getUpload_pn_name();
		   	 	 
		   	 	   if(reports.getReport_pn_target() > 1 ) {
		   	 		pn_name += ","+reports.getUpload_pn_name2();
		   	    	}
		   	    	if(reports.getReport_pn_target() > 2 ) {
		   	    		pn_name += ","+reports.getUpload_pn_name3();
		   	    	}
		   	    	if(reports.getReport_pn_target() > 3 ) {
		   	    		pn_name += ","+reports.getUpload_pn_name4();
		   	    	}
		   	    	if(reports.getReport_pn_target() > 4) {
		   	    		pn_name += ","+reports.getUpload_pn_name5();
		   	    	}
			 		  
		 		   
		 		   
		 			notification  notifications = new notification();
		 			notifications.setNotification_iduser(reports.getReport_pn_app());
					notifications.setNotification_iduser2(null);
					notifications.setNotification_alert("ไม่พบการอัพโหลดเอกสารตามที่กำหนดไว้ที่คุณมีหน้าที่ตรวจสอบ -- ชื่อเอกสาร : " + pn_name + " กำหนดอัพโหลด : " +reports.getReport_pn_date());
					notifications.setNotification_note("ผู้รับผิดชอบ : "+reports.getUpload_pn_respon());
					notifications.setNotification_date(datetime);
					notifications.setNotification_read(0);
					notifications.setNotification_refer(String.valueOf(reports.getReport_pn_id()));
					notifications.setNotification_type("Job queue error");
					NotificationDAO.save(notifications);   
					Report_pnDAO.setalert2(reports.getReport_pn_id());
					Report_pnDAO.setsendstatus(reports.getReport_pn_id());
					
					System.out.println("reports " +reports);
					if(reports.getUser_email() != null) {
						if(reports.getUser_email() != "") {
							user users =new user();
							setmail setmails = new setmail();
				    	    setmail mailconfig =  SetmailDAO.getbyId(1);
				    		setmails.setUsername(mailconfig.getUsername());
				    		setmails.setPassword(mailconfig.getPassword());
				    		setmails.setHostname(mailconfig.getHostname());
				    		setmails.setStarttls_port(mailconfig.getStarttls_port());
				    		setmails.setStarttls(mailconfig.isStarttls());
				    		setmails.setAuth(mailconfig.isAuth());
				    		setmails.setUrl(mailconfig.getUrl()+"/lohpn-filemanager?idrefer="+reports.getReport_pn_idrefer()+"&&id="+reports.getReport_pn_id());
				    		setmails.setSupport(mailconfig.getSupport());
				    		setmails.setNote("ไม่พบการอัพโหลดเอกสารตามที่กำหนดไว้ที่คุณมีหน้าที่ตรวจสอบ -- ชื่อเอกสาร : " + pn_name + " กำหนดอัพโหลด : " +reports.getReport_pn_date());
				    		setmails.setNote2("ผู้รับผิดชอบ : "+reports.getUpload_pn_respon());
				    		users.setUser_email(reports.getUser_email());
				    		users.setUser_name(reports.getUser_name());
				    		
				    		
				 
				    		if(reports.getUser_email() != null && !reports.getUser_email().isEmpty() && !reports.getUser_email().equals("null")) {	
				    				String sendmail = gmailTextMsgSendWithoutSSL.sendGmailendWithoutSSL(setmails,users,2);
				    	
				    		}
				    	
				    	
						}
						}
					
					}
		 	   
		 	   
		 	   
		 	    LocalDateTime localDate3 = LocalDateTime.now().plusMonths(1);
		 	    String date3 = localDate3.format(formatter2);
		 	    
		 	    
		 		 List<report_pn> report_pn3 = Report_pnDAO.getall5(date3,"0");
			   	 
		 	   	System.out.println("Alert Num : "+report_pn.size());
		 	 	System.out.println("dates : "+dates2); 
		 	 	   for (report_pn  report : report_pn3) {
		 	 		
		 	 		   
		 	 		   
		 	 		  String  pn_name = report.getUpload_pn_name();
				   	 	 
			   	 	   if(report.getReport_pn_target() > 1 ) {
			   	 		pn_name += ","+report.getUpload_pn_name2();
			   	    	}
			   	    	if(report.getReport_pn_target() > 2 ) {
			   	    		pn_name += ","+report.getUpload_pn_name3();
			   	    	}
			   	    	if(report.getReport_pn_target() > 3 ) {
			   	    		pn_name += ","+report.getUpload_pn_name4();
			   	    	}
			   	    	if(report.getReport_pn_target() > 4) {
			   	    		pn_name += ","+report.getUpload_pn_name5();
			   	    	}
		 	 		   
		 	 		   
		 	 		  System.out.println("report_pn ID : " +report.getReport_pn_id());
		 	 		  

		 	 			notification  notifications = new notification();
		 	 			notifications.setNotification_iduser(report.getReport_pn_respon());
		 				notifications.setNotification_iduser2(null);
		 				notifications.setNotification_alert("คุณมีเอกสารที่ต้องอัพโหลด -- ชื่อเอกสาร : " + pn_name + " กำหนดอัพโหลด : " +report.getReport_pn_date());
		 				notifications.setNotification_date(datetime);
		 				notifications.setNotification_read(0);
		 				notifications.setNotification_type("Job queue");
		 				notifications.setNotification_refer(String.valueOf(report.getReport_pn_id()));
		 				NotificationDAO.save(notifications);
		 				Report_pnDAO.setStatus2(report.getReport_pn_id(),1);
		 				
		 				
		 				if(report.getUser_email() != null) {
		 					if(report.getUser_email() != "") {
		 						user users =new user();
		 						setmail setmails = new setmail();
		 			    	    setmail mailconfig =  SetmailDAO.getbyId(1);
		 			    		setmails.setUsername(mailconfig.getUsername());
		 			    		setmails.setPassword(mailconfig.getPassword());
		 			    		setmails.setHostname(mailconfig.getHostname());
		 			    		setmails.setStarttls_port(mailconfig.getStarttls_port());
		 			    		setmails.setStarttls(mailconfig.isStarttls());
		 			    		setmails.setAuth(mailconfig.isAuth());
		 			    		setmails.setUrl(mailconfig.getUrl()+"/lohpn-filemanager?idrefer="+report.getReport_pn_idrefer()+"&&id="+report.getReport_pn_id());
		 			    		setmails.setSupport(mailconfig.getSupport());
		 			    		setmails.setNote("คุณมีเอกสารที่ต้องอัพโหลด -- ชื่อเอกสาร : " + pn_name + " กำหนดอัพโหลด : " +report.getReport_pn_date());
		 			    		setmails.setNote2("");
		 			    		users.setUser_email(report.getUser_email());
					    		users.setUser_name(report.getUser_name());
					    		
					    		if(report.getUser_email() != null && !report.getUser_email().isEmpty()   && !report.getUser_email().equals("null")) {
		 			    		String sendmail = gmailTextMsgSendWithoutSSL.sendGmailendWithoutSSL(setmails,users,2);
					    		}
		 			    		
		 			    		
		 			    		
		 					}
		 					}
		 				
		 	 		
		 	 	   }
		
	}
	
	*/
	

}

