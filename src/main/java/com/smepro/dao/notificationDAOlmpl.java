package com.smepro.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.groups;
import com.smepro.models.notification;
import com.smepro.rowmapper.groupsmapper;
import com.smepro.rowmapper.notificationmapper;

public class notificationDAOlmpl implements notificationDAO {

	
	private JdbcTemplate jdbcTemplate;
	
	public notificationDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public List<notification> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<notification> getallrefer(String username) {
		   String sql="select * from notification where notification_iduser=? and notification_read='0' or notification_iduser IS NULL  and notification_read='0' order by notification_id desc limit 10 ";    
			List<notification> notifications  =  jdbcTemplate.query(sql, new Object[]{username},new notificationmapper());
			return notifications;
	}

	@Override
	public List<notification> getallrefer2(String username) {
		   String sql="select * from notification where notification_iduser=? or notification_iduser IS NULL  order by notification_id desc limit 1000 ";    
			List<notification> notifications  =  jdbcTemplate.query(sql, new Object[]{username},new notificationmapper());
			return notifications;
	}
	
	@Override
	public int readall(String username) {
		  String sql=  "UPDATE notification SET notification_read='1' "
		  		+ "WHERE   notification_iduser='"+username+"'";
			    return jdbcTemplate.update(sql);   
	}
	
	
	
	@Override
	public void save(notification notifications) {
		
		if(notifications.getNotification_iduser2() == null
				&& 	
				notifications.getNotification_iduser().equals("0")
				) { return;}
		
		
		
		Object[] notificationInfo = { 
				notifications.getNotification_iduser(),
				notifications.getNotification_iduser2(),
				notifications.getNotification_alert(),
				notifications.getNotification_date(),
				notifications.getNotification_read(),
				notifications.getNotification_type(),
				notifications.getNotification_refer(),
				notifications.getNotification_note(),
				notifications.getNotification_process_id()
				};
				String sql = "INSERT INTO notification(notification_iduser,notification_iduser2,notification_alert"
						+ ",notification_date,notification_read,notification_type,notification_refer,notification_note,notification_process_id) "
						+ "VALUES(?,?,?,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,notificationInfo);

	}
	
	@Override
	public void saveAll(List<notification> notificationsList) {
		String sql = "INSERT INTO notification(notification_iduser,notification_iduser2,notification_alert"
				+ ",notification_date,notification_read,notification_type,notification_refer,notification_note,notification_process_id) "
				+ "VALUES(?,?,?,?,?,?,?,?,?);";
				
		// สร้าง List เพื่อเก็บชุดพารามิเตอร์ของแต่ละแถว
		List<Object[]> batchArgs = new ArrayList<>();
		
		for (notification notif : notificationsList) {
			Object[] notificationInfo = { 
					notif.getNotification_iduser(),
					notif.getNotification_iduser2(),
					notif.getNotification_alert(),
					notif.getNotification_date(),
					notif.getNotification_read(),
					notif.getNotification_type(),
					notif.getNotification_refer(),
					notif.getNotification_note(),
					notif.getNotification_process_id()
			};
			batchArgs.add(notificationInfo);
		}
		
		// สั่งรันรวดเดียว ประหยัดเวลาได้มหาศาล
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	@Override
	public notification getbyIdrefer(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public notification getbyId(int id,String username) {
	    String sql="select * from notification where notification_id=? and notification_iduser =? or  notification_id=? and notification_iduser IS NULL ";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{id,username,id},new notificationmapper()); 
	}
	
	@Override
	public  List<notification>  getallrefer(String id,String access,String allProcessId) {
		
		System.out.println("notification_process_id "+allProcessId);
		System.out.println("notification_iduser2 "+access);
		System.out.println("notification_iduser "+id);
		
		String sql = "select * from notification where (  FIND_IN_SET(notification_iduser,?)  or FIND_IN_SET(notification_iduser2,? )  ) and  find_in_set(notification_process_id,?)   ORDER BY notification_id DESC ";
		List<notification> notifications  =  jdbcTemplate.query(sql, new Object[]{ id,access,allProcessId},new notificationmapper());
		return notifications;
	}

	@Override
	public List<notification> getallrefer(int id) {
		   String sql="select * from notification where notification_read='0' AND  (notification_iduser=? or notification_iduser IS NULL)  order by notification_id desc limit 1000 ";    
					List<notification> notifications  =  jdbcTemplate.query(sql, new Object[]{id},new notificationmapper());
					return notifications;
	}

	@Override
	public List<notification> getallPolicies(String Policies) {
		   String sql="select * from notification where notification_read='0' AND  (  FIND_IN_SET(notification_iduser,?)  or FIND_IN_SET(notification_iduser2,? )  )  order by notification_id desc limit 1000 ";    
					List<notification> notifications  =  jdbcTemplate.query(sql, new Object[]{Policies,Policies},new notificationmapper());
					return notifications; 
	}

	
	
	@Override
	public List<notification> getallrefer2(int id) {
		   String sql="select * from notification where notification_iduser=? or notification_iduser IS NULL  order by notification_id desc limit 1000 ";    
			List<notification> notifications  =  jdbcTemplate.query(sql, new Object[]{id},new notificationmapper());
			return notifications;
	}

	@Override
	public int countnotification(String username,int read) {
		String sql = "select count(*) from notification where notification_iduser=? and  notification_read=? or notification_iduser IS NULL and  notification_read=?   ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] { username,read,read}, Integer.class);
	}
	
	@Override
	public int countnotification(String id,String access,String allProcessId,int read) {
		String sql = "select count(*) from notification where ( notification_iduser=? OR find_in_set(notification_iduser2,?)  ) "
				+ "and  notification_read=? and  find_in_set(notification_process_id,?)  ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] { id,access,read,allProcessId}, Integer.class);
	}
	
	@Override
	public int setview(int id) {
		  String sql=  "UPDATE notification SET notification_read='1' "
		  		+ "WHERE   notification_id="+id;
			    return jdbcTemplate.update(sql);   
	}
	@Override
	public int setviewByReport(int Report_pn_id,String user_name) {
		  String sql=  "UPDATE notification SET notification_read='1' "
		  		+ "WHERE ( notification_type='Job queue' or  notification_type='Job queue error' )  and    notification_refer= ?  and  notification_iduser =? ";
			    return jdbcTemplate.update(sql, new Object[] { Report_pn_id,user_name});   
	}
	
	@Override
	public void  readAll(String user_id,String access,int process_id) {
		
		
		  String sql=  "UPDATE notification SET notification_read='1'  WHERE ( notification_iduser =? OR  FIND_IN_SET(notification_iduser2,?) ) and    notification_process_id= ?     ";
			     jdbcTemplate.update(sql, new Object[] { user_id,access,process_id});   
	}
	
	
	@Override
	public int setviewByDar(int dar_id,String user_name) {
		  String sql=  "UPDATE notification SET notification_read='1' "
		  		+ "WHERE ( notification_type='Dar workflow (passed)' or  notification_type='Dar workflow (failed)' or  notification_type='Dar workflow (CREATE)'  ) "
		  		+ " and    notification_refer= ?  and  notification_iduser =? ";
			    return jdbcTemplate.update(sql, new Object[] { dar_id,user_name});   
	}
	
	
	
}
