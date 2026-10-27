package com.smepro.service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.holidayDAO;
import com.smepro.dao.report_pnDAO;

import com.smepro.models.Plans;
import com.smepro.models.edata;
import com.smepro.models.holiday;
import com.smepro.models.report_pn;

@Service
public class UploadPlanManager {
	
	
	
	
	private  report_pnDAO Report_pnDAO;
	private  holidayDAO HolidayDAO;
	private dateservice dateservices;
	
    @Autowired
    public void setMyRepository(
    		report_pnDAO Report_pnDAO
    		,holidayDAO HolidayDAO
    		,dateservice dateservices
    		) {
        this.Report_pnDAO =Report_pnDAO; 
        this.HolidayDAO =HolidayDAO; 
        this.dateservices =dateservices; 
    }
	
 
	
	
	
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   private  List<String> users =  new ArrayList<String>();
	   private  List<holiday> companyHolidays =  new ArrayList<holiday>();
	   private  int plan_id;
	   private  DayOfWeek week;
	   private  int day_in_year;
	   private  String user_app;
	   private  String date_due;
	   
	   
	   
               //สร้างแผนการอัพโหลด
			   public  void createUploadPlans(Plans Plans) {
				      
				   setUser(Plans.getUsers());
				   setHolidays(Plans.getHoliday());
				   setPlan(Plans.getPlan_id());
				   setWeek(Plans.getWeek());
				   setDayinyear(Plans.getDay_in_year());
				   setUser_app(Plans.getUser_app());
				   setDate_due( Plans.getDate_due());
				   Holiday();
				   
				   run(Plans.getType());
				   
			    }
			   
				public  void Approve(edata  edatas) {
					
					 LocalDateTime localDate = LocalDateTime.now();//For reference	 
					 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 String date = localDate.format(formatter3);
					 String dates = localDate.format(formatter);
					
					   if(edatas.getData_due() != null) {
						   if(!edatas.getData_due().equals("-")) {
							   String date_due = edatas.getData_due();
							   int id_refer = edatas.getData_planid();
							   Report_pnDAO.setApprove(id_refer,date_due,date);  
							   
							   if(dateservices.Criteria_Check(dates,edatas.getData_dateadd().split(" ")[0])) {
								   Report_pnDAO.setApproveSuccess(id_refer,date_due);  
							   }
						   }
					   }

				   }
				public  void Send(edata  edatas) {
					
					 LocalDateTime localDate = LocalDateTime.now();//For reference	 
					 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 String date = localDate.format(formatter3);
					 String dates = localDate.format(formatter);
					 
					   if(edatas.getData_due() != null) {
						   if(!edatas.getData_due().equals("-")) {
							   String date_due = edatas.getData_due();
							   int id_refer = edatas.getData_planid();
							 
							   Report_pnDAO.setSend(id_refer,date_due,date);  
							   if(dateservices.Criteria_Upload(dates, edatas.getData_due())) {
								   Report_pnDAO.setSendSuccess(id_refer,date_due);  
							   } 
						   }
					   }

				   }

				
				
			   
			   private  void run(int  type) {
				   
				   if(type == 0) {
				        // 1. ทุกวัน (ยกเว้นวันอาทิตย์และวันหยุด)
				        createDailyUploadPlan();
				   }
				   else if(type == 1) {
				        // 2. ทุกสัปดาห์ (กำหนดวันในสัปดาห์)
				        createWeeklyUploadPlan();
				   }
				   else if(type == 2) {
					   // 3. ทุกเดือน (กำหนดวันที่ในเดือน)
				        createMonthlyUploadPlan();
				   }
				   else {
				        // 4. ครั้งเดียว (กำหนดวันที่ 1 ครั้ง)
				        createUploadPlan();
				   }
				   

				   
			   }
			   
			   
			   
			   private  void Holiday() {
				   String currentYear = String.valueOf(Year.now().getValue());
				   companyHolidays = HolidayDAO.getall(currentYear);
			   }
			   
			   
			   
			   //ครั้งเดียว
			    private  void createUploadPlan() {
			    	
			    	System.out.println(" createUploadPlan  ...... ");
			    	
			    	
			    	List<report_pn> report_pns =  new ArrayList<report_pn>();
			        for (String user : users) {
			   
			                	report_pn report_pn = new report_pn(); 	
			                	report_pn.setReport_pn_idrefer(this.plan_id);
			                	report_pn.setReport_pn_respon(user);
			                	report_pn.setReport_pn_app(user_app);
			                	report_pn.setReport_pn_date(date_due);
			                	report_pn.setReport_pn_type(3);
			                	report_pn.setReport_pn_status(1);
			                	report_pns.add(report_pn);
			            }

			        System.out.println(" report_pns  ...... " +report_pns);
			        Report_pnDAO.savebatch(report_pns); 
			    }
			   
			   
			   
			   // สร้างแผนการอัพโหลดทุกวัน
			    private  void createDailyUploadPlan() {
			    	

			    	
			    	
			    	List<report_pn> report_pns =  new ArrayList<report_pn>();
			        LocalDateTime startDate = LocalDateTime.now();
			        for (String user : users) {
			            for (int i = 0; i < 365; i++) { // ตั้งแผนอัพโหลดตลอดทั้งปี
			                LocalDateTime planDate = startDate.plusDays(i);
			                if (isValidUploadDay(planDate)) { // ตรวจสอบว่าวันนั้นเป็นวันทำงาน
			                	
			                	String dateStr = planDate.format(formatter); 
			                	report_pn report_pn = new report_pn(); 	
			                	report_pn.setReport_pn_idrefer(this.plan_id);
			                	report_pn.setReport_pn_respon(user);
			                	report_pn.setReport_pn_app(user_app);
			                	report_pn.setReport_pn_date(dateStr);
			                	report_pn.setReport_pn_type(0);
			                	report_pn.setReport_pn_status(1);
			                	report_pns.add(report_pn);
			                }
			            }
			        }
			        
			        Report_pnDAO.savebatch(report_pns);
			        
			    }
			    
			    
			    

			    // สร้างแผนการอัพโหลดทุกสัปดาห์
			    private  void createWeeklyUploadPlan() {
			    	
			    	List<report_pn> report_pns =  new ArrayList<report_pn>();
			    	
			    	
			    	System.out.println(" createWeeklyUploadPlan  ...... ");
			    	
			    	
			        LocalDateTime startDate = LocalDateTime.now();
			        for (String user : users) {
			            for (int i = 0; i < 52; i++) { // ตั้งแผนอัพโหลดทุกสัปดาห์
			                // กำหนดวันศุกร์ในสัปดาห์นี้
			                LocalDateTime planDate = startDate.plusWeeks(i).with(TemporalAdjusters.nextOrSame(this.week));
			                	if (isValidUploadDay(planDate)) { // ตรวจสอบว่าวันนั้นเป็นวันทำงาน
			                	
			                	String dateStr = planDate.format(formatter); 
			                	report_pn report_pn = new report_pn(); 	
			                	report_pn.setReport_pn_idrefer(this.plan_id);
			                	report_pn.setReport_pn_respon(user);
			                 	report_pn.setReport_pn_app(user_app);
			                	report_pn.setReport_pn_date(dateStr);
			                	report_pn.setReport_pn_type(1);
			                 	report_pn.setReport_pn_status(1);
			                	report_pns.add(report_pn);
			                }
			            }
			        }
			        Report_pnDAO.savebatch(report_pns);
			    }

			    // สร้างแผนการอัพโหลดทุกเดือน
			    private  void createMonthlyUploadPlan() {
			    	
			    	List<report_pn> report_pns =  new ArrayList<report_pn>();
			        LocalDateTime startDate = LocalDateTime.now();
			        for (String user : users) {
			            for (int i = 0; i < 12; i++) { // ตั้งแผนอัพโหลดทุกเดือน
			                // กำหนดวันที่ 15 ของทุกเดือน
			                LocalDateTime planDate = startDate.plusMonths(i).withDayOfMonth(day_in_year);
			                	if (isValidUploadDay(planDate)) { // ตรวจสอบว่าวันนั้นเป็นวันทำงาน
			                	String dateStr = planDate.format(formatter); 
			                	report_pn report_pn = new report_pn(); 	
			                	report_pn.setReport_pn_idrefer(this.plan_id);
			                	report_pn.setReport_pn_respon(user);
			                 	report_pn.setReport_pn_app(user_app);
			                	report_pn.setReport_pn_date(dateStr);
			                	report_pn.setReport_pn_type(2);
			                 	report_pn.setReport_pn_status(1);
			                	report_pns.add(report_pn);
			                }
			            }
			        }
			        Report_pnDAO.savebatch(report_pns);
			    }

			    
			    
			    // ตรวจสอบว่าวันนั้นสามารถอัพโหลดได้หรือไม่ (ไม่ใช่วันอาทิตย์และวันหยุด)
			    private  boolean isValidUploadDay(LocalDateTime date) {

			        // ตรวจสอบว่าเป็นวันอาทิตย์หรือไม่
			        if (date.getDayOfWeek() == java.time.DayOfWeek.SUNDAY) {
			            return false;
			        }

			        // ตรวจสอบว่าเป็นวันหยุดบริษัทหรือไม่
			        for (holiday holiday : companyHolidays) {
			         	 LocalDate localDate = LocalDate.parse(holiday.getHoliday_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				         LocalDateTime holiday_localDateTime = localDate.atStartOfDay();
			            if (date.toLocalDate().equals(holiday_localDateTime)) {
			                return false;
			            }
			        }

			        return true;
			    }
			   
			    
			    
			    
			    
			    
				
			    
			    
			    
			    
			    

			    private void  setUser(String users) {
			    		this.users = Arrays.asList(users.split(",\\s*"));
			    }   
			    private void  setHolidays( List<holiday> companyHolidays) {
			    			this.companyHolidays = companyHolidays;
			    }   
			    private void  setPlan( int plan_id) {
	    			this.plan_id = plan_id;
			    }
			   
			    
			    private void  setWeek( String week) {
			    	
			    	 switch (week) {
			         case "W-1":
			             this.week = java.time.DayOfWeek.MONDAY;
			             break;
			         case "W-2":
			             this.week = java.time.DayOfWeek.TUESDAY;
			             break;
			         case "W-3":
			             this.week = java.time.DayOfWeek.WEDNESDAY;
			           
			             break;
			         case "W-4":
			        	  this.week = java.time.DayOfWeek.THURSDAY;
			             break;
			         case "W-5":
			             this.week = java.time.DayOfWeek.FRIDAY;
			             break;
			         default:
			             this.week = java.time.DayOfWeek.SATURDAY; // ค่าเริ่มต้น (default)
			     }

	    			
			    }
			    

			    private void  setDayinyear( int day_in_year) {
			    	this.day_in_year = day_in_year;	
			    }
			   
			    
			    private void  setUser_app( String user_app) {
			    	this.user_app = user_app;	
			    }
			    
			    private void  setDate_due( String date_due) {
			    	this.date_due = date_due;	
			    }
			    
}
