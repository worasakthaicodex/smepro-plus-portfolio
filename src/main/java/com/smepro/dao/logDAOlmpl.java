package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smepro.models.Log_login;
import com.smepro.models.log;
import com.smepro.models.report_pn;
import com.smepro.models.showlog;
import com.smepro.rowmapper.conditionmapper;
import com.smepro.rowmapper.logloginmapper;
import com.smepro.rowmapper.logmapper;
import com.smepro.rowmapper.logmapper2;
import com.smepro.rowmapper.logmapper2s;
import com.smepro.rowmapper.logmappers;
import com.smepro.rowmapper.logmappers3;
import com.smepro.rowmapper.logmappers4;
import com.smepro.rowmapper.logmappers5;
import com.smepro.rowmapper.logmappers6;
import com.smepro.rowmapper.report_pnmapper;


public class logDAOlmpl implements logDAO {

	
	private JdbcTemplate jdbcTemplate;


	public logDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	@Override
	public List<log> getall(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public List<showlog> getalldate(String date,String step) {
		String sql = "SELECT * FROM log LEFT JOIN users ON log.log_id_add = users.user_id "
				+ " where log.log_date= ? and log_step=?   order by log.log_id  desc limit 3000 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{date,step}, new logmapper2());
		return showlogs;
	}
	
	@Override
	public List<showlog> getallfolder(String log_folder) {
		String sql = "SELECT * FROM log LEFT JOIN users ON log.log_id_add = users.user_id "
				+ " where log.log_folder= ? group by  users.user_id order by log.log_id  desc limit 20 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{log_folder}, new logmapper2());
		return showlogs;
	}
	

	
	@Override
	public Log_login getLoginDate(String datess,String timestart,String timeend) {
		String sql = "SELECT"
		 		+ " @TOTAL1 :=  SUM(groups.group_level = 'Labour' )  as  total1, "
		 		+ "  @TOTAL2 :=  SUM(groups.group_level = 'Seniour Labour' )  as  total2, "
		 		+ "  @TOTAL3 := SUM(groups.group_level = 'Supervisor' )  as  total3, "
		 		+ "  @TOTAL4 := SUM(groups.group_level = 'Manager' )  as  total4, "
		 		+ "  @TOTAL5 := SUM(groups.group_level = 'Director' )  as  total5, "
		 		+ "  @TOTAL1 +@TOTAL2 +@TOTAL3 +@TOTAL4 +@TOTAL5    as  total "
				+ "FROM log "
				+ "LEFT JOIN  users ON users.user_id  = log.log_id_add "
				+ "LEFT JOIN  group_members ON group_members.username  = users.username "
				+ "LEFT JOIN  groups ON groups.id  = group_members.group_id "
				
				+ "  where  DATE(log.log_date) =?  and  TIME(log.log_time) >= ?  and  TIME(log.log_time)  <= ? ";
	
		Log_login data  =  jdbcTemplate.queryForObject(sql,new Object[]{datess,timestart,timeend},new logloginmapper());
		return data;
	}
	@Override
	public Log_login getLoginMonth(String months,String years,String timestart,String timeend) {
		String sql = "SELECT"
				+ " @TOTAL1 :=  SUM(groups.group_level = 'Labour' )  as  total1, "
		 		+ "  @TOTAL2 :=  SUM(groups.group_level = 'Seniour Labour' )  as  total2, "
		 		+ "  @TOTAL3 := SUM(groups.group_level = 'Supervisor' )  as  total3, "
		 		+ "  @TOTAL4 := SUM(groups.group_level = 'Manager' )  as  total4, "
		 		+ "  @TOTAL5 := SUM(groups.group_level = 'Director' )  as  total5, "
		 		+ "  @TOTAL1 +@TOTAL2 +@TOTAL3 +@TOTAL4 +@TOTAL5    as  total "
				+ "FROM log "
				+ "LEFT JOIN  users ON users.user_id  = log.log_id_add "
				+ "LEFT JOIN  group_members ON group_members.username  = users.username "
				+ "LEFT JOIN  groups ON groups.id  = group_members.group_id "
				
				+ "  where  MONTH(log.log_date) =?  and   YEAR(log.log_date) =?  and  TIME(log.log_time) >= ?  and  TIME(log.log_time)  <= ? ";
	
		Log_login data  =  jdbcTemplate.queryForObject(sql,new Object[]{months,years,timestart,timeend},new logloginmapper());
		return data;
	}
	
	
	@Override
	public Log_login getLoginYear(String years,String timestart,String timeend) {
		String sql = "SELECT"
				+ " @TOTAL1 :=  SUM(groups.group_level = 'Labour' )  as  total1, "
		 		+ "  @TOTAL2 :=  SUM(groups.group_level = 'Seniour Labour' )  as  total2, "
		 		+ "  @TOTAL3 := SUM(groups.group_level = 'Supervisor' )  as  total3, "
		 		+ "  @TOTAL4 := SUM(groups.group_level = 'Manager' )  as  total4, "
		 		+ "  @TOTAL5 := SUM(groups.group_level = 'Director' )  as  total5, "
		 		+ "  @TOTAL1 +@TOTAL2 +@TOTAL3 +@TOTAL4 +@TOTAL5    as  total "
				+ "FROM log "
				+ "LEFT JOIN  users ON users.user_id  = log.log_id_add "
				+ "LEFT JOIN  group_members ON group_members.username  = users.username "
				+ "LEFT JOIN  groups ON groups.id  = group_members.group_id "
				
				+ "  where  YEAR(log.log_date) =?  and  TIME(log.log_time) >= ?  and  TIME(log.log_time)  <= ? ";
	
		Log_login data  =  jdbcTemplate.queryForObject(sql,new Object[]{years,timestart,timeend},new logloginmapper());
		return data;
	}
	
	
	
	/*

	@Override
	public List<log> gettimebylevelmonth(String month) {
		String sql = "SELECT "
				+ "log.log_date as log_datestart ,log.log_time as log_timestart"
				+ ",LAG(log.log_date) OVER (ORDER BY groups.group_level,log.log_id asc) as log_dateend "
				+ ",LAG(log.log_time) OVER (ORDER BY groups.group_level,log.log_id asc) as log_timeend"
				+ ", groups.group_level as log_step,log_id_add"
				+ ", TIME_TO_SEC(TIMEDIFF(log.log_time,LAG(log.log_time) OVER (ORDER BY groups.group_level,log.log_id asc))) as  log_note "
				+ " FROM  log  "
				+ "LEFT JOIN  users ON users.user_id  = log.log_id_add "
				+ "LEFT JOIN  group_members ON group_members.username  = users.username "
				+ "LEFT JOIN  groups ON groups.id  = group_members.group_id "
				+ "where MONTH(log.log_date) = ?   ORDER BY groups.group_level,log.log_id  desc      ";
		
		List<log> showlogs  =  jdbcTemplate.query(sql , new Object[]{month}, new logmappers4());
		return showlogs;
	}
	@Override
	public List<log> gettimebylevelyear(String year) {
		String sql = "SELECT "
				+ "log.log_date as log_datestart ,log.log_time as log_timestart"
				+ ",LAG(log.log_date) OVER (ORDER BY groups.group_level,log.log_id asc) as log_dateend "
				+ ",LAG(log.log_time) OVER (ORDER BY groups.group_level,log.log_id asc) as log_timeend"
				+ ", groups.group_level as log_step,log_id_add"
				+ ", TIME_TO_SEC(TIMEDIFF(log.log_time,LAG(log.log_time) OVER (ORDER BY groups.group_level,log.log_id asc))) as  log_note "
				+ " FROM  log  "
				+ "LEFT JOIN  users ON users.user_id  = log.log_id_add "
				+ "LEFT JOIN  group_members ON group_members.username  = users.username "
				+ "LEFT JOIN  groups ON groups.id  = group_members.group_id "
				+ "where YEAR(log.log_date) = ?   ORDER BY groups.group_level,log.log_id  desc      ";
		
		List<log> showlogs  =  jdbcTemplate.query(sql , new Object[]{year}, new logmappers4());
		return showlogs;
	}
	

	
	
	
	@Override
	public List<log> gettimeavg(String dates2) {
		String sql = "SELECT log_time,log.log_date as log_datestart"
				+ ",LAG(log.log_date) OVER (ORDER BY log_id_add,log_id asc) as log_dateend "
				+ ",LAG(log_time) OVER (ORDER BY log_id_add,log_id asc) as log_refer,log_id_add"
				+ ", TIMEDIFF(log_time,LAG(log_time) OVER (ORDER BY log_id_add,log_id asc)) as  log_note "
				+ " FROM  log where log_date >= ?  ORDER BY log_id_add,log_id  desc      ";
		List<log> showlogs  =  jdbcTemplate.query(sql , new Object[]{dates2}, new logmappers3());
		return showlogs;
	}
		*/
	
	
	
	
	
	
	
	
	@Override
	public List<showlog> gettimebyleveldate(String dates2,int user_id) {
		String sql = "SELECT * FROM  log  "
				+ "LEFT JOIN  users ON users.user_id  = log.log_id_add "
				+ "LEFT JOIN  group_members ON group_members.username  = users.username "
				+ "LEFT JOIN  groups ON groups.id  = group_members.group_id "
				+ "where log.log_date = ? and log.log_id_add =? and  log.log_datestart IS NOT NULL   ORDER BY groups.group_level,log.log_id  desc      ";
		
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{dates2,user_id}, new logmapper2());
		return showlogs;
	}
	@Override
	public List<showlog> gettimebylevelmonth(String year,String month,int user_id) {
		String sql = "SELECT * FROM  log  "
				+ "LEFT JOIN  users ON users.user_id  = log.log_id_add "
				+ "LEFT JOIN  group_members ON group_members.username  = users.username "
				+ "LEFT JOIN  groups ON groups.id  = group_members.group_id "
				+ "where YEAR(log.log_date) = ? and  MONTH(log.log_date) = ? and log.log_id_add  =? and  log.log_datestart IS NOT NULL   ORDER BY groups.group_level,log.log_id  desc      ";
		
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{year,month,user_id}, new logmapper2());
		return showlogs;
	}
	@Override
	public List<showlog> gettimebylevelyear(String year,int user_id) {
		String sql = "SELECT * FROM  log  "
				+ "LEFT JOIN  users ON users.user_id  = log.log_id_add "
				+ "LEFT JOIN  group_members ON group_members.username  = users.username "
				+ "LEFT JOIN  groups ON groups.id  = group_members.group_id "
				+ "where YEAR(log.log_date) = ? and log.log_id_add =? and  log.log_datestart IS NOT NULL   ORDER BY groups.group_level,log.log_id  desc      ";
		
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{year,user_id}, new logmapper2());
		return showlogs;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public List<showlog> getall2() {
		String sql = "SELECT * FROM log LEFT JOIN users ON log.log_id_add = users.user_id     order by log.log_id  desc limit 3000 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{}, new logmapper2());
		return showlogs;
	}

	@Override
	public List<showlog> getallbyuserid(int userid) {
		String sql = "SELECT * FROM log LEFT JOIN users ON log.log_id_add = users.user_id  where users.user_id=?    order by log.log_id  desc limit 6 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{userid}, new logmapper2());
		return showlogs;
	}
	
	
	@Override
	public List<showlog> getallbypn(int pn,String date) {
		String sql = "SELECT * FROM log LEFT JOIN users ON log.log_id_add = users.user_id  where log.log_refer =? and log.log_due=? order by log.log_id  desc limit 3000 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{pn,date}, new logmapper2());
		return showlogs;
	}
	
	
	
	@Override
	public List<showlog> getallbypn2(int pn,String name) {
		String sql = "SELECT * FROM log "
				+ "LEFT JOIN users ON log.log_id_add = users.user_id "
				+ "LEFT JOIN upload_pn ON log.log_refer = upload_pn.upload_pn_id "
				+ " where log.log_refer =?"
				+ " and  users.user_name =? "
				+ " and log_file IS NOT NULL  and log.log_step != 'Task'  order by log.log_id  desc limit 3000 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{pn,name}, new logmapper2s());
		return showlogs;
	}
	
	
	@Override
	public List<showlog> getallbypn(int pn) {
		String sql = "SELECT * FROM log "
				+ "LEFT JOIN users ON log.log_id_add = users.user_id "
				+ "LEFT JOIN upload_pn ON log.log_refer = upload_pn.upload_pn_id "
				+ " where log.log_refer =?  and log_file IS NOT NULL  and log.log_step != 'Task'  order by log.log_id  desc limit 3000 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{pn}, new logmapper2s());
		return showlogs;
	}
	
	@Override
	public List<showlog> getallbyidadd(int id) {
		String sql = "SELECT * FROM log LEFT JOIN users ON log.log_id_add = users.user_id  where log.log_id_add =? order by log.log_id  desc limit 3000 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{id}, new logmapper2());
		return showlogs;
	}
	
	

	@Override
	public List<showlog> getallbyidrefer(int id) {
		String sql = "SELECT * FROM log LEFT JOIN users ON log.log_id_add = users.user_id  where log.log_refer =? order by log.log_id  desc limit 100 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{id}, new logmapper2());
		return showlogs;
	}
	
	@Override
	public List<showlog> getallbydarrefer(int id) {
		String sql = "SELECT * FROM log LEFT JOIN users ON log.log_id_add = users.user_id  where log.log_darrefer =? order by log.log_id  desc limit 100 ";
		List<showlog> showlogs  =  jdbcTemplate.query(sql , new Object[]{id}, new logmapper2());
		return showlogs;
	}
	
	
	@Override
	public void save(log logs) {
		Object[] logInfo = { 
				logs.getLog_id_add(),
				logs.getLog_date(),
				logs.getLog_time(),
				logs.getLog_step(),
				logs.getLog_refer(),
				logs.getLog_folder(),
				logs.getLog_note(),
				logs.getLog_file(),
				logs.getLog_dive(),
				logs.getLog_due(),
				logs.getLog_darrefer(),
				logs.getLog_readtime(),
				logs.getLog_datestart(),
				logs.getLog_timestart(),
				logs.getLog_code()
				};
		
		
		System.out.println(" logs ");

	    	String sql = "INSERT INTO log(log_id_add,log_date,log_time,log_step,log_refer,log_folder,log_note,log_file,log_dive,log_due,log_darrefer"
					+ ",log_readtime,log_datestart,Log_timestart,log_code) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			jdbcTemplate.update(sql,logInfo);
	 
		

	}
	
	
	@Override
	public int saves(log logs) {
		
		 if(logs.getLog_id() > 0) {
			 
			 Object[] logInfo = { 
						logs.getLog_id_add(),
						logs.getLog_date(),
						logs.getLog_time(),
						logs.getLog_step(),
						logs.getLog_refer(),
						logs.getLog_folder(),
						logs.getLog_note(),
						logs.getLog_file(),
						logs.getLog_dive(),
						logs.getLog_due(),
						logs.getLog_darrefer(),
						logs.getLog_readtime(),
						logs.getLog_flow(),
						logs.getLog_plan(),
						logs.getLog_id()
						};

				String sql = "UPDATE log SET log_id_add =? ,log_date=? ,log_time=? ,log_step=? ,log_refer=? ,log_folder=? "
						+ ",log_note=? ,log_file=? ,log_dive=? ,log_due=? ,log_darrefer=? ,log_readtime=? ,log_flow=? ,log_plan=?  "
						+ " WHERE log_id = ? ";
				jdbcTemplate.update(sql,logInfo);
			 
			 return  logs.getLog_id();
		 }else {
			
					String sql = "INSERT INTO log(log_id_add,log_date,log_time,log_step,log_refer,log_folder,log_note,log_file,log_dive,log_due,log_darrefer,log_readtime,log_flow,log_plan) "
									+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				          KeyHolder keyHolder = new GeneratedKeyHolder();
				          jdbcTemplate.update(connection -> {
				              PreparedStatement ps = connection
				                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				                ps.setInt(1,logs.getLog_id_add());
				                ps.setString(2,logs.getLog_date());
				                ps.setString(3, logs.getLog_time());
				                ps.setString(4,logs.getLog_step());
				                ps.setString(5,logs.getLog_refer());
				                ps.setString(6,logs.getLog_folder());
				                ps.setString(7,logs.getLog_note());
				                ps.setString(8,logs.getLog_file());
				                ps.setString(9,logs.getLog_dive());
				                ps.setString(10,logs.getLog_due());
				                ps.setInt(11,logs.getLog_darrefer());
				                ps.setString(12,logs.getLog_readtime());
				                ps.setInt(13,logs.getLog_flow());
				                ps.setInt(14,logs.getLog_plan());
				                return ps;
				             
				          }, keyHolder);			
				          //String key = keyHolder.getKey();
				          System.out.println( keyHolder.getKey().intValue());
				         return  keyHolder.getKey().intValue();
				     // System.out.println( keyHolder.getKey());
		 }
	}
	
	@Override
	public log getLotOld(int log_id_add) {
		 
		    
		    try {
		    	  String sql="select * from log where log_id_add=? order by log_id DESC  limit 1";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{log_id_add},new logmapper());   
		 
		 }catch(EmptyResultDataAccessException e) {
			 log logs = new  log();
			 return logs;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
	}

	@Override
	public log getbyId(int id) {
		 
		    
		    try {
		    	  String sql="select * from log where log_id=?";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new logmapper());   
		 
		 }catch(EmptyResultDataAccessException e) {
			 log logs = new  log();
			 return logs;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
	}
	
	@Override
	public int setplan(int refer,int id) {
		 String sql=  "UPDATE log SET "
			  		+ "log_refer='"+refer+"'"
			  		+ " WHERE log_id="+id;
		 return  jdbcTemplate.update(sql);   
	}


	@Override
	public int update(log logs) {
		 String sql=  "UPDATE log SET "
			  		+ "log_folder='"+logs.getLog_folder()+"'"
			  		+ ",log_file='"+logs.getLog_file()+"'"
			  		+ ",log_dive='"+logs.getLog_dive()+"'"
			  		+ " WHERE log_id="+logs.getLog_id();
		 return  jdbcTemplate.update(sql);   
	}


	
	@Override
	public int delete(int id) {
		   String sql="delete from log where log_id="+id;    
		    return jdbcTemplate.update(sql);
	}
	
	@Override
	public void setflow(int id,int status,String note) {
		  String sql=  "UPDATE log SET "
		  		+ "log_flow='"+status+"',"
		  		+ "log_note='"+note+"'"
		  		+ " WHERE log_id="+id;
			  jdbcTemplate.update(sql);   
	}
	@Override
	public void setflow(int id,int status) {
		  String sql=  "UPDATE log SET "
		  		+ "log_flow='"+status+"'"
		  		+ " WHERE log_id="+id;
			  jdbcTemplate.update(sql);   
	}

	@Override
	public void setapp(int id) {
		  String sql=  "UPDATE log SET "
		  		+ "log_flow='1' "
		  		+ " WHERE DATE(log_date) <= CURDATE() and log_step='UPLOAD' and log_refer="+id;
			  jdbcTemplate.update(sql);   
	}

	@Override
	public int getapp(int id,String date){
	    String sql = "SELECT count(*) FROM log WHERE log_refer = ? and log_due =? and log_flow=1";

	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id,date }, Integer.class);

	    return count;
	}
	
	@Override
	public int getuseronline(String date,String time){
	    String sql = "SELECT count(DISTINCT log_id_add) FROM log WHERE log_date = ? and log_time >= ?  ";

	    int count = jdbcTemplate.queryForObject(sql, new Object[] { date,time }, Integer.class);

	    return count;
	}
	
	
	
	@Override
	public List<log> getuseronreadfilemaxD(String date) {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where log.log_step ='READ' and  DATE(log.log_date) =?  AND file.file_name_original IS NOT NULL "
				+ "group by log.log_refer  order by count(log.log_refer) desc limit 5 ";
		List<log> logs  =  jdbcTemplate.query(sql , new Object[] { date}, new logmappers());
		return logs;
	}
	
	@Override
	public List<log> getuseronreadfilemaxM(String month,String year) {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where log.log_step ='READ' and  MONTH(log.log_date) =?  and  YEAR(log.log_date) =?  AND file.file_name_original IS NOT NULL "
				+ "group by log.log_refer  order by count(log.log_refer) desc limit 5 ";
		List<log> logs  =  jdbcTemplate.query(sql , new Object[] { month,year}, new logmappers());
		return logs;
	}
	@Override
	public List<log> getuseronreadfilemaxY(String year) {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where log.log_step ='READ'   and  YEAR(log.log_date) =?  AND file.file_name_original IS NOT NULL  "
				+ "group by log.log_refer  order by count(log.log_refer) desc limit 5 ";
		List<log> logs  =  jdbcTemplate.query(sql , new Object[] { year}, new logmappers());
		return logs;
	}

	@Override
	public List<log> getuseronreadfileminD(String date) {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where log.log_step ='READ' and  DATE(log.log_date) =?  AND file.file_name_original IS NOT NULL "
				+ "group by log.log_refer  order by count(log.log_refer)   asc limit 5 ";
		List<log> logs  =  jdbcTemplate.query(sql, new Object[] { date} , new logmappers());
		return logs;
	}
	
	@Override
	public List<log> getuseronreadfileminM(String month,String year) {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where log.log_step ='READ' and MONTH(log.log_date) =?  and  YEAR(log.log_date) =?  AND file.file_name_original IS NOT NULL  "
				+ "group by log.log_refer  order by count(log.log_refer) asc limit 5 ";
		List<log> logs  =  jdbcTemplate.query(sql, new Object[] {month,year} , new logmappers());
		return logs;
	}
	
	@Override
	public List<log> getuseronreadfileminY(String year) {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where log.log_step ='READ' and  YEAR(log.log_date) =?  AND file.file_name_original IS NOT NULL " 
				+ "group by log.log_refer  order by count(log.log_refer) asc limit 5 ";
		List<log> logs  =  jdbcTemplate.query(sql , new Object[] { year}, new logmappers());
		return logs;
	}
	
	
	@Override
	public List<log> getuseronreadfiledownload() {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where log.log_step ='DOWNLOAD' and file.file_name_original != ''   "
				+ "group by file.file_name_original  order by count(log.log_refer) desc  limit 30 ";
		List<log> logs  =  jdbcTemplate.query(sql , new logmappers());
		return logs;
	}
	

	@Override
	public int numuseronreadfiledownload(String month,String year){
		String sql = "SELECT count(*) FROM log    where log_step ='DOWNLOAD' and MONTH(log_date) =?  and YEAR(log_date) =? ";

	    int count = jdbcTemplate.queryForObject(sql, new Object[] { month,year }, Integer.class);

	    return count;
	}
	
	
	@Override
	public List<log> getallreadfile() {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where  file_shotcut IS NOT NULL "
				+ "group by file.file_name_original  order by count(log.log_refer) desc   ";
		List<log> logs  =  jdbcTemplate.query(sql , new logmappers());
		return logs;
	}
	
	@Override
	public List<log> getallreadfileshortcut() {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer,file.file_name_original,file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer   where log.log_step ='READ'  and  file_shotcut IS NOT NULL "
				+ "group by log.log_refer  order by count(log.log_refer) desc limit 500 ";
		List<log> logs  =  jdbcTemplate.query(sql , new logmappers());
		return logs;
	}
	

	@Override
	public List<log> getallreadfileshortcut(int refer) {
		String sql = "SELECT count(log.log_refer) as log_note,log.log_refer"
				+ ",file.file_name_original"
				+ ",file.file_path_full FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer  "
				+ " where   file_shotcut IS NOT NULL and file.file_parent =? "
				+ "group by log.log_refer  order by count(log.log_refer) desc limit 500 ";
		List<log> logs  =  jdbcTemplate.query(sql, new Object[] {refer } , new logmappers());
		return logs;
	}
	
	

	@Override
	public List<log> getallreadfileshortcutall(int refer) {
		String sql = "SELECT log.log_date,log.log_time,log.log_note,log.log_refer,file.file_name_original,file.file_path_full,users.user_name FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer  "
				+ "LEFT JOIN users ON log.log_id_add = users.user_id  "
				+ " where   file_shotcut IS NOT NULL and file.file_parent  =? "
				+ "order by log.log_id desc limit 500 ";
		List<log> logs  =  jdbcTemplate.query(sql, new Object[] {refer } , new logmappers6());
		return logs;
	}
	@Override
	public List<log> getallreadfileshortcutall2(int refer) {
		String sql = "SELECT log.log_date,log.log_time,log.log_note,log.log_refer,file.file_name_original,file.file_path_full,users.user_name FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer  "
				+ "LEFT JOIN users ON log.log_id_add = users.user_id  "
				+ " where   log.log_refer =? "
				+ "order by log.log_id desc limit 500 ";
		List<log> logs  =  jdbcTemplate.query(sql, new Object[] {refer } , new logmappers6());
		return logs;
	}
	
	
	@Override
	public boolean check(int idrefer,String date,String time) {
	    String sql = "SELECT count(*) FROM log WHERE log_refer = ? and log_time =? and  log_date=?  ";
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { idrefer,date,time }, Integer.class);

	    return count > 0;
	}

	@Override
	public int countlevel(String year,String level) {
		String sql = "SELECT count(*)  FROM log   "
				+ "LEFT JOIN users ON users.user_id  = log.log_id_add   "
				+ " LEFT JOIN group_members ON group_members.username = users.username "
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id "
				+ "WHERE log.log_step ='LOGIN'  and YEAR(log.log_date) =?   and groups.group_level =? ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,level  }, Integer.class);
	}
	
	
	@Override
	public int countLoginYear(String year,int userid) {
		String sql = "SELECT count(DISTINCT log_date)  FROM log   "
				+ "WHERE log_step ='LOGIN'  and YEAR(log_date) =?  and log_id_add =?   ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,userid }, Integer.class);
	}
	
	@Override
	public int Numreport(int reportid,String due) {
	    String sql = "SELECT count(*) FROM log WHERE log_refer = ? and log_due =?   ";
	    return   jdbcTemplate.queryForObject(sql, new Object[] { reportid,due }, Integer.class);

	}

	
	@Override
	public int numstatus(int idrefer) {
	    String sql = "SELECT count(*) FROM log WHERE log_refer = ?  and log_flow < 1 and  log_step ='UPLOAD'  ";
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { idrefer }, Integer.class);
	    return count ;
	}
	
	
	
	@Override
	public List<log> getPage(String Start,String End, int page) {
	    int limit = 20; // จำนวนรายการต่อหน้า
	    int offset = (page - 1) * limit; // คำนวณตำแหน่งเริ่มต้นของหน้า

	    
		String sql = "SELECT log.log_date,log.log_time,log.log_note,log.log_refer,log.log_step"
				+ ",file.file_name_original,file.file_path_full,users.user_name,groups.group_level FROM log   "
				+ "LEFT JOIN file ON file.file_id = log.log_refer  "
				+ "LEFT JOIN users ON log.log_id_add = users.user_id  "
				+ " LEFT JOIN group_members ON group_members.username = users.username "
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id "
								+ " where   log.log_date >= ?  AND log.log_date <= ? "
				+ "order by log.log_id desc  LIMIT ? OFFSET ? ";

	    List<log> report_pns = jdbcTemplate.query(
	        sql,
	        new Object[]{Start,End, limit, offset},
	        new logmappers5()
	    );
	    return report_pns;
	}

	
		@Override
		public int getNum(String Start,String End) {
		    String sql = "SELECT count(*) FROM log WHERE   log.log_date >= ?  AND log.log_date <= ?  ";
		    int count = jdbcTemplate.queryForObject(sql, new Object[] {Start,End }, Integer.class);
		    return count ;
		}
}
