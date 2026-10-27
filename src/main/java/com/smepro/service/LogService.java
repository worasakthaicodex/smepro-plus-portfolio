package com.smepro.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.IdocDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.logDAO;
import com.smepro.dao.userDAO;
import com.smepro.models.log;
import com.smepro.models.user;

@Service
public class LogService {
	
	private  logDAO logDAOs;
	private  userDAO UserDAO;
	private  userservice Userservice;
	
	
	
    @Autowired
    public void setMyRepository(
    		logDAO logDAOs,
    		userDAO UserDAO,
    		userservice Userservice
    		) {
    	
        this.logDAOs = logDAOs;
        this.UserDAO = UserDAO;
        this.Userservice = Userservice;
    }

    
    	/*
    	 * Error 400 (Missing Config) 
    	 * Error 401 (Validation / Missing field)
    	 * Error 403 (Validation)
    	 * Error 404 
    	 * Error 422 (Unprocessable Policies Respon)
    	 * */
	    public void notice(int code,String notice,String path) { 
	    	

		    LocalDateTime localDate = LocalDateTime.now();//For reference
		   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
		   	String date = localDate.format(formatter);
		   	String time = localDate.format(formatter2);
		   	user you = 	UserDAO.getbyusername2(Userservice.getLoggedName());
		   	
	    	log logs = new log();	
	    	logs.setLog_id_add(you.getUser_id());
	    	logs.setLog_step("System notice!");
	    	logs.setLog_folder(path);
	    	logs.setLog_date(date);
	    	logs.setLog_time(time);
	    	logs.setLog_note(notice);
	    	logs.setLog_refer(null);
	    	logs.setLog_code(code);
	    	logDAOs.save(logs);
	    }
    public void notice(int code,String notice,String path,int id) { 
	    	

		    LocalDateTime localDate = LocalDateTime.now();//For reference
		   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
		   	String date = localDate.format(formatter);
		   	String time = localDate.format(formatter2);
		   	user you = 	UserDAO.getbyusername2(Userservice.getLoggedName());
	    	
	    	log logs = new log();	
	    	logs.setLog_id_add(you.getUser_id());
	    	logs.setLog_step("System notice!");
	    	logs.setLog_folder(path);
	    	logs.setLog_date(date);
	    	logs.setLog_time(time);
	    	logs.setLog_note(notice);
	    	logs.setLog_refer(String.valueOf(id));
	    	logs.setLog_code(code);
	    	logDAOs.save(logs);
	    }
    
    
    
    /*
     * Success 200 (Change Close Status )
     * Success 202 (Request action )
     * */
    public void action(int code,String notice,String path,int id) { 
    	

	    LocalDateTime localDate = LocalDateTime.now();//For reference
	   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
	   	String date = localDate.format(formatter);
	   	String time = localDate.format(formatter2);
	   	user you = 	UserDAO.getbyusername2(Userservice.getLoggedName());
    	
    	log logs = new log();	
    	logs.setLog_id_add(you.getUser_id());
    	
    
      	
      	logs.setLog_id_add(you.getUser_id());
    	logs.setLog_step("Important events!");
    	logs.setLog_folder(path);
    	logs.setLog_date(date);
    	logs.setLog_code(code);
    	logs.setLog_time(time);
    	logs.setLog_note(notice);
    	logs.setLog_refer(String.valueOf(id));
    	logDAOs.save(logs);
    }

    
    
    
    
    

}
