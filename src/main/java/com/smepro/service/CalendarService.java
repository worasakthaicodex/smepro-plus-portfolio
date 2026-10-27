package com.smepro.service;




import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.smepro.models.Calendar;
import com.smepro.models.Goal;
import com.smepro.models.check_log;
import com.smepro.models.holiday;
import com.smepro.models.missed_uploads;
import com.smepro.models.notification;
import com.smepro.models.upload_log;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.repository.PolicyRepository;
import com.smepro.websocket.webmeterSocket;
import org.springframework.retry.annotation.Backoff;
@Service
public class CalendarService {

	
	private jobDAO JobDAO;
	private workflowDAO WorkflowDAO;
	private edataDAO EdataDAO;
	private holidayDAO HolidayDAO;
	private PolicyRepository policyRepository;
	private otherservice Otherservice;
	private upload_pnDAO Upload_pnDAO;
	private notificationDAO NotificationDAO;
	private  userservice Userservice;
	private userDAO UserDAO;
	
	
    @Autowired
    public void setMyRepository(
    		jobDAO JobDAO,
    		workflowDAO WorkflowDAO,
    		edataDAO EdataDAO,
    		holidayDAO HolidayDAO,
    		PolicyRepository policyRepository,
    		otherservice Otherservice,
    		
    		upload_pnDAO Upload_pnDAO,
    		notificationDAO NotificationDAO,
    		userservice Userservice,
    		userDAO UserDAO
    		){
    		this.JobDAO = JobDAO;
    		this.WorkflowDAO = WorkflowDAO;
    		this.EdataDAO = EdataDAO;
    		this.HolidayDAO = HolidayDAO;
    		this.policyRepository = policyRepository;
    		this.Otherservice = Otherservice;
    		
    		this.Upload_pnDAO = Upload_pnDAO;
    		this.NotificationDAO = NotificationDAO;
    		this.Userservice = Userservice;
    		this.UserDAO = UserDAO;
    	}

    @Transactional
    @Retryable(
			value = { Exception.class },      // ระบุ Exception ที่เกิดแล้วจะให้ทำใหม่ (ใส่ Exception.class คือคลุมทุก Error)
			maxAttempts = 3,                  // พยายามสูงสุด 3 ครั้ง (รวมครั้งแรกด้วย)
			backoff = @Backoff(delay = 5000)  // หน่วงเวลา 5000 มิลลิวินาที (5 วินาที) ก่อนพยายามรอบถัดไป
	)
	public void check() {
		
				LocalDateTime localDate = LocalDateTime.now(); // For reference
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String datetime = localDate.format(formatter);
				
				List<Calendar> calendarList = Upload_pnDAO.getAllCalendarDue();
				
				// 1. สร้าง List มารอรับข้อมูล
				List<notification> notificationsToSave = new ArrayList<>();
				
				calendarList.forEach(row -> {
					// (Option) ใช้ String.format จะเร็วกว่าและอ่านง่ายกว่าการบวก String ด้วย +
					String note = String.format("กิจกรรม   :  %s วันที่  :  %s-%s-%s", 
							row.getEvent(), row.getDay(), row.getMonth(), row.getYear());
		
					notification notif = new notification();
					notif.setNotification_iduser(row.getBy_NameId());
					notif.setNotification_alert("คุณมีกิจกรรมที่กำหนดไว้");
					notif.setNotification_date(datetime);
					notif.setNotification_read(0);
					notif.setNotification_note(note);
					notif.setNotification_process_id(row.getProcess_id());
					notif.setNotification_type("Action Required");
					
					// 2. แอดข้อมูลลง List ไว้ก่อน (ยังไม่เซฟ)
					notificationsToSave.add(notif);
				});
		
				// 3. เซฟลง Database รวดเดียว (ถ้ามีข้อมูล)
				if (!notificationsToSave.isEmpty()) {
					NotificationDAO.saveAll(notificationsToSave);
				}
		  		
  		

	}
    
    
    
    
}