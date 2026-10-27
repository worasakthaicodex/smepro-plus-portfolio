package com.smepro.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.attendance;
import com.smepro.models.check_log;
import com.smepro.models.eformfield;
import com.smepro.models.missed_uploads;
import com.smepro.models.upload_log;
import com.smepro.rowmapper.attendancemapper;
import com.smepro.rowmapper.attendancemapper2;
import com.smepro.rowmapper.check_logmapper;
import com.smepro.rowmapper.eformfieldmapper;
import com.smepro.rowmapper.upload_logmapper;
import com.smepro.rowmapper.usermapper2;




public class jobDAOlmpl implements jobDAO {

	private JdbcTemplate jdbcTemplate;

	public jobDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	
	
	
	@Override
	public check_log CheckLog() {

		  try {
				String sql = "SELECT * FROM check_log limit 1 ";
				check_log check_logs  =  jdbcTemplate.queryForObject(sql,new check_logmapper());
				return check_logs;
		 }catch(EmptyResultDataAccessException e) {
			 
			 LocalDateTime today  = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 String date = today.format(formatter);
			 
			 String sql = "INSERT INTO check_log (checked_at) VALUES (?)  ";
				jdbcTemplate.update(sql,new Object[]{date});   
			 return null;
			 
			 
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
	}

	
	@Override
	public void SaveLog() {

		 LocalDateTime today  = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = today.format(formatter);

		String sql = "UPDATE check_log SET checked_date=?  ";
		jdbcTemplate.update(sql,new Object[]{date});   
	}

	
	
	
	@Override
	public void SaveMissedUploads(missed_uploads missed_uploads) {

		Object[] missed_uploadsInfo = { 
				missed_uploads.getUser_name(),
				missed_uploads.getGoal_id(),
				missed_uploads.getMissed_due(),
				};
		
		String sql = "SELECT COUNT(*) FROM  missed_uploads WHERE user_name =? AND goal_id =?  AND missed_due =?  ";
		int num = jdbcTemplate.queryForObject(sql,missed_uploadsInfo,Integer.class);
		
		System.err.println(" num "+num);
		
		
		if(num == 0) {
				String sqls = "INSERT INTO missed_uploads(user_name,goal_id,missed_due) VALUES(?,?,?)";
				jdbcTemplate.update(sqls,missed_uploadsInfo);
	    	}		
	}
	
	@Override
	public void SavehandleUploadedFiles(upload_log upload_logs) {

		
		Object[] upload_logsInfo = { 
				upload_logs.getUser_name(),
				upload_logs.getGoal_id(),
				upload_logs.getEdata_id(),
				upload_logs.getUpload_date(),
				upload_logs.getStatus(),

				};
				String sql = "INSERT INTO upload_log(user_name,goal_id,edata_id,upload_date,status)"
						+ " VALUES(?,?,?,?,?)";
				jdbcTemplate.update(sql,upload_logsInfo);
				
				
				
				
				
	}
	
	@Override
	public void SaveUploadedFiles(upload_log upload_logs) {

		
		
		Object[] upload_logsInfo = { 
				upload_logs.getUser_name(),
				upload_logs.getGoal_id(),
				upload_logs.getEdata_id(),
				upload_logs.getUpload_date(),
				upload_logs.getUpload_time(),

				};
		       
		
				String sql = "INSERT INTO upload_log(user_name,goal_id,edata_id,upload_date,upload_time)"
						+ " VALUES(?,?,?,?,?)";
				jdbcTemplate.update(sql,upload_logsInfo);
	}
	
	
	
	@Override
	public upload_log getResultGoal(int id) {
 
		
		   try {
				
				String sql = "SELECT * FROM upload_log WHERE goal_id = ?  ORDER BY id DESC limit 1 ";
				upload_log upload_log =  jdbcTemplate.queryForObject(sql,new Object[]{id}, new upload_logmapper());
				return upload_log;
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
			

	}

	

}
