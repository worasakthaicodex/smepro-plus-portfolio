package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.attendance;
import com.smepro.rowmapper.attendancemapper;
import com.smepro.rowmapper.attendancemapper2;
import com.smepro.rowmapper.usermapper2;




public class attendanceDAOlmpl implements attendanceDAO {

	private JdbcTemplate jdbcTemplate;

	public attendanceDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<attendance> getall(int user_id) {
		String sql = "SELECT * FROM attendance  where attendance_userid=?"
				+ " order by attendance_id DESC  limit 60";
		List<attendance> attendances  =  jdbcTemplate.query(sql ,new Object[]{user_id}, new attendancemapper());
		return attendances;
	}

	
	@Override
	public List<attendance> getall(String month,String year) {
		String sql = "SELECT * FROM attendance,users "
				+ " where (users.user_id = attendance.attendance_userid) "
				+ "and  MONTH(attendance.attendance_date) =?  and YEAR(attendance.attendance_date) =? "
				+ " order by attendance.attendance_date ASC  ";
		List<attendance> attendances  =  jdbcTemplate.query(sql ,new Object[]{month,year}, new attendancemapper2());
		return attendances;
	}

	@Override
	public List<attendance> getallflow(String month,String year,String alluser) {
		String sql = "SELECT * FROM attendance,users "
				+ " where (users.user_id = attendance.attendance_userid) "
				+ "and  MONTH(attendance.attendance_date) =?  and YEAR(attendance.attendance_date) =?  and find_in_set(attendance.attendance_userid,?) "
				+ " order by attendance.attendance_date ASC  ";
		List<attendance> attendances  =  jdbcTemplate.query(sql ,new Object[]{month,year,alluser}, new attendancemapper2());
		return attendances;
	}


	
	@Override
	public List<attendance> getall(String dt_datestart,String dt_dateend,List<String> selectuser) {
		
		String sql = "SELECT * FROM attendance,users WHERE (users.user_id = attendance.attendance_userid)  ";
		
		if(dt_datestart != null  && !dt_datestart.isEmpty()) {
			sql += " and DATE(attendance.attendance_date) >= '"+dt_datestart+"' ";
		}

		if(dt_dateend != null && !dt_dateend.isEmpty()) {
			sql += " and DATE(attendance.attendance_date) <= '"+dt_dateend+"' ";
		}
		
       if(selectuser != null && !selectuser.isEmpty()) {
    	   
    	   sql += " and FIND_IN_SET(users.user_name,'"+selectuser+"') ";
		}
		sql += " order by attendance.attendance_date ASC  ";
		
		
		//System.out.print(sql);

		List<attendance> attendances  =  jdbcTemplate.query(sql, new attendancemapper2());
		return attendances;
	}

	
	@Override
	public int delete(int user_id,String date) {
		 String sql="delete from  attendance "
		 		+ " where attendance_userid = ? and attendance_date = ?" ;    
		 return jdbcTemplate.update(sql,new Object[]{user_id,date});   
	}


	@Override
	public attendance getbyId(int user_id,String date) {
		
		  try {
				 String sql="select * from attendance where attendance_userid = ? and attendance_date = ? ";    
				 return jdbcTemplate.queryForObject(sql, new Object[]{user_id,date},new attendancemapper());  
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }

		 
		 
		 
	}
	
	@Override
	public void checkin(attendance attendances) {

		Object[] attendanceInfo = { 
				attendances.getAttendance_date(),
				attendances.getAttendance_checkin(),
				attendances.getAttendance_userid(),
				attendances.getAttendance_ip(),
				attendances.getAttendance_lat(),
				attendances.getAttendance_lon(),
				attendances.getAttendance_file()
				};
				String sql = "INSERT INTO attendance(attendance_date,attendance_checkin"
						+ ",attendance_userid,attendance_ip,attendance_lat,attendance_lon,attendance_file)"
						+ " VALUES(?,?,?,?,?,?,?)";
				jdbcTemplate.update(sql,attendanceInfo);
	}
	@Override
	public void checkout(attendance attendances) {

		Object[] attendanceInfo = { 
			
				attendances.getAttendance_checkout(),
				attendances.getAttendance_ip(),
				attendances.getAttendance_lat(),
				attendances.getAttendance_lon(),
				attendances.getAttendance_file2(),
				attendances.getAttendance_userid(),
				attendances.getAttendance_date()
				};
		String sql = "UPDATE attendance SET attendance_checkout=?"
				+ ",attendance_ip=?,attendance_lat=?,attendance_lon=?,attendance_file2=? WHERE attendance_userid=? and attendance_date =? ";
				jdbcTemplate.update(sql,attendanceInfo);
	}

	

}
