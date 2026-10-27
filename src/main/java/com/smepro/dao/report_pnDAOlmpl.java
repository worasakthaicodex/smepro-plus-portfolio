package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.smepro.models.condition;
import com.smepro.models.report_pn;
import com.smepro.models.report_pn_pdf;
import com.smepro.models.showupload_pn;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.rowmapper.report_pnmapper;
import com.smepro.rowmapper.report_pnmapper2;
import com.smepro.rowmapper.report_pnmapper3;
import com.smepro.rowmapper.report_pnmapper4;
import com.smepro.rowmapper.report_pnmapper5;
import com.smepro.rowmapper.report_pnmapper6;
import com.smepro.rowmapper.report_pnmappers;
import com.smepro.rowmapper.reportpnpdf;
import com.smepro.rowmapper.upload_pnmapper2;
import com.smepro.rowmapper.upload_pnmapper2s;
import com.smepro.rowmapper.usermapper2;

public class report_pnDAOlmpl implements report_pnDAO {

	
private JdbcTemplate jdbcTemplate;
	

   /*
    * report_pn_appstatus = คือ 1  มีการอนุมัติ
    * report_pn_sendstatus = คือ 1 มีการส่ง
    * report_pn_app_statussccess  = คือ 1 มีการส่งตรง
    * 
    * 
    * 
    * */



	public report_pnDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public List<report_pn> getall(String date) {
		String sql = "SELECT * FROM report_pn  where report_pn_date <=?  order by report_pn_id desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{date},new report_pnmapper());
		return report_pns;
	}

	@Override
	public List<report_pn> getall2(int id) {
		String sql = "SELECT * FROM report_pn  where report_pn_idrefer=? and report_pn_date <= NOW()   order by report_pn_id desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id},new report_pnmapper());
		return report_pns;
	}
	@Override
	public List<report_pn> getall2(int id,user users) {
		String sql = "SELECT * FROM report_pn  where report_pn_idrefer=? "
				+ " and ( report_pn_app =? OR FIND_IN_SET(?,report_pn_respon ) ) "
				+ "and report_pn_date <= NOW()   order by report_pn_id desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id,users.getUser_name(),users.getUser_name()},new report_pnmapper());
		return report_pns;
	}
	
	

	
	
	@Override
	public List<report_pn> getallreportbydate(String datestart,String dateend) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "where report_pn.report_pn_date >=? "
				+ " and report_pn.report_pn_date <=?  order by report_pn.report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{datestart,dateend},new report_pnmapper6());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getallreportbydate(String date) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "where DATE(report_pn.report_pn_date) =? "
				+ " and upload_pn.upload_pn_type =0   and upload_pn.upload_pn_due =0   order by report_pn.report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{date},new report_pnmapper6());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getallreportbymonth(String month) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "where MONTH(report_pn.report_pn_date) =? "
				+ " and upload_pn.upload_pn_type =0   and upload_pn.upload_pn_due =0   order by report_pn.report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{month},new report_pnmapper6());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getallreportbyweek(int week) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "where WEEK(report_pn.report_pn_date) =? "
				+ " and upload_pn.upload_pn_type =0   and upload_pn.upload_pn_due =0   order by report_pn.report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{week},new report_pnmapper6());
		return report_pns;
	}
	@Override
	public List<report_pn> getallreportbyyear(String year) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "where YEAR(report_pn.report_pn_date) =? "
				+ " and upload_pn.upload_pn_type =1    order by report_pn.report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{year},new report_pnmapper6());
		return report_pns;
	}
	
	
	@Override
	public List<report_pn> getallreportbydateandsuccess(String datestart,String dateend,String name) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "where report_pn.report_pn_date >=? "
				+ " and report_pn.report_pn_date <=?   and  report_pn_respon = ? and report_pn.report_pn_sendstatus = 0 "
				+ " and report_pn.report_pn_total > 0  order by report_pn.report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{datestart,dateend,name},new report_pnmapper6());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getallreportbydateanderror(String datestart,String dateend,String name) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "where report_pn.report_pn_date >=? "
				+ " and report_pn.report_pn_date <=?  and  report_pn_respon = ?  and report_pn.report_pn_sendstatus > 0 order by report_pn.report_pn_date desc  limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{datestart,dateend,name},new report_pnmapper6());
		return report_pns;
	}
	
	
	@Override
	public List<report_pn> getall2(int id,String date) {
		String sql = "SELECT * FROM report_pn  where report_pn_idrefer=?  and report_pn_date <= ?  order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id,date},new report_pnmapper());
		return report_pns;
	}
	
	
	
	@Override
	public List<report_pn> getall2(int id,String date,int status) {
		String sql = "SELECT * FROM report_pn  where report_pn_idrefer=?  and report_pn_date <= ?  and report_pn_status =?  and  report_pn_date > DATE_ADD(CURDATE(), INTERVAL -31 DAY)  order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id,date,status},new report_pnmapper());
		return report_pns;
	}
	@Override
	public List<report_pn> getall2(int id,String date,int status,String name) {
		String sql = "SELECT * FROM report_pn  where report_pn_idrefer=?    and report_pn_status =? "
				+ " and (report_pn.report_pn_respon =? or report_pn.report_pn_app =? )  "
				+ "order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id,status,name,name},new report_pnmapper());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getallrespon(int id,String date) {
		String sql = "SELECT * FROM report_pn  where report_pn_idrefer=?  and report_pn_date <= ?  group by report_pn_respon  order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id,date},new report_pnmapper());
		return report_pns;
	}
	@Override
	public List<report_pn> getallapp(int id,String date) {
		String sql = "SELECT * FROM report_pn  where report_pn_idrefer=?  and report_pn_date <= ?  group by report_pn_app  order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id,date},new report_pnmapper());
		return report_pns;
	}
	
	
	@Override
	public List<report_pn> getallrespon(String year) {
		String sql = "SELECT * FROM report_pn  where  YEAR(report_pn_date) =  ?  group by report_pn_respon  order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{year},new report_pnmapper());
		return report_pns;
	}
	@Override
	public List<report_pn> getallapp(String year) {
		String sql = "SELECT * FROM report_pn  where YEAR(report_pn_date) =  ? group by report_pn_app  order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{year},new report_pnmapper());
		return report_pns;
	}
	
	
	@Override
	public List<report_pn> getallresponandapp(String user,String year) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "where (report_pn.report_pn_app =? or report_pn.report_pn_respon =? ) and YEAR(report_pn.report_pn_date) = ? "
				+ " order by report_pn.report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{user,user,year},new report_pnmapper6());
		return report_pns;
	}
	
	
	@Override
	public List<report_pn> getdateplan(int id,int statusid,String respon) {
		
		
		System.out.println("test "+id);
		System.out.println("statusid "+statusid);
		System.out.println("respon "+respon);
		
		
		String sql = "SELECT * FROM report_pn  where report_pn_idrefer=?  and report_pn_status =?  and report_pn_respon =? and report_pn_date <= NOW() "
				+ " group by report_pn_date  order by report_pn_date desc limit 10";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id,statusid,respon},new report_pnmapper());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getall2(int eformid,int due,String date,int status) {
		String sql = "SELECT * FROM report_pn "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "where upload_pn.upload_pn_monitor = ? and upload_pn.upload_pn_type =?  and "
				+ "  report_pn.report_pn_status =? order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{eformid,due,status},new report_pnmapper());
		return report_pns;
	}
	@Override
	public List<report_pn> getall2(int eformid,int due,String date,int status,String name) {
		String sql = "SELECT * FROM report_pn "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "where upload_pn.upload_pn_monitor = ? and upload_pn.upload_pn_type =?  and "
				+ " report_pn.report_pn_status =?  and (report_pn.report_pn_respon =? or report_pn.report_pn_app =? ) "
				+ ""
				+ " order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{eformid,due,status,name,name},new report_pnmapper());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getall2(int id,int eformid,int due,String date,int status,String name) {
		String sql = "SELECT * FROM report_pn "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "where upload_pn.upload_pn_id = ? and upload_pn.upload_pn_monitor = ? and upload_pn.upload_pn_type =?  and "
				+ "  report_pn.report_pn_status =?  "
				+ "and ( upload_pn.upload_pn_respon =? or  upload_pn.upload_pn_app =? ) "
				+ ""
				+ " order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{id,eformid,due,status,name,name},new report_pnmapper());
		return report_pns;
	}
	
	
	
	@Override
	public List<report_pn> getallrespon(int eformid,int due,String date) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "where upload_pn.upload_pn_monitor = ? and upload_pn.upload_pn_type =?  and "
				+ " report_pn.report_pn_date <=?  "
				+ "group by report_pn_respon  order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{eformid,due,date},new report_pnmapper());
		return report_pns;
	}
	@Override
	public List<report_pn> getallapp(int eformid,int due,String date) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "where upload_pn.upload_pn_monitor = ? and upload_pn.upload_pn_type =?  and "
				+ " report_pn_date <=?  "
				+ "group by report_pn_app  order by report_pn_date desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{eformid,due,date},new report_pnmapper());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getdateplan(int eformid,int due,int statusid) {
		String sql = "SELECT * FROM report_pn  "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "where upload_pn.upload_pn_monitor = ? and upload_pn.upload_pn_type =?  and "
				+ " report_pn.report_pn_status =?  "
				+ "group by report_pn_date  order by report_pn_date desc limit 10";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{eformid,due,statusid},new report_pnmapper());
		return report_pns;
	}
	
	
	
	@Override
	public List<report_pn> getall3(String date, String alert) {
		String sql = "SELECT * FROM report_pn LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ "LEFT JOIN users ON upload_pn.upload_pn_respon  = users.user_name"
				+ " where report_pn.report_pn_date <=?  and report_pn.report_pn_alert1 != ? and upload_pn.upload_pn_end = 0  "
				+ "order by report_pn.report_pn_date desc limit 3000";
		
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{date,alert},new report_pnmappers());
		return report_pns;
	}
	
	
	
	@Override
	public List<report_pn> getall4(String date, String alert) {
		String sql = "SELECT * FROM report_pn LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer "
				+ "LEFT JOIN users ON upload_pn.upload_pn_app  = users.user_name"
				+ "  where report_pn.report_pn_date < ?  and report_pn.report_pn_alert2 != ?"
				+ "and  report_pn.report_pn_total < report_pn.report_pn_target and upload_pn.upload_pn_end = 0"
				+ " order by report_pn.report_pn_id desc limit 3000";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{date,alert},new report_pnmappers());
		return report_pns;
	}

	
	//upload_pn_type = 1 คือกำหนดตามวันที่
	@Override
	public List<report_pn> getall5(String date, String alert) {
		String sql = "SELECT * FROM report_pn LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer "
				+ "LEFT JOIN users ON upload_pn.upload_pn_app  = users.user_name"
				+ "  where upload_pn.upload_pn_type = 1  and report_pn.report_pn_date <= ?  and report_pn.report_pn_status2 = ?  and upload_pn.upload_pn_end = 0   limit 100";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{date,alert},new report_pnmappers());
		return report_pns;
	}
	
	
	
	@Override
	public List<report_pn> getallreport(String date) {
		String sql = "SELECT * FROM report_pn LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer   where report_pn.report_pn_date =?  order by report_pn.report_pn_id desc limit 3000 ";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{date},new report_pnmapper3());
		return report_pns;
	}
	
	
	@Override
	public List<report_pn> getallreportchart(String datestart,String dateend) {
		String sql = "SELECT SUM(report_pn_statuserror) as  report_pn_statuserror"
				+ ", SUM(report_pn_statussccess) as  report_pn_statussccess,report_pn_respon,report_pn_date,report_pn_id"
				+ " FROM report_pn where report_pn_date >= ? and report_pn_date <= ?  group by report_pn_respon ";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{datestart,dateend},new report_pnmapper4());
		return report_pns;
	}
	
	
	
	@Override
	public report_pn getallRadarD(String year,int due,String date) {
	
		
		  try {
				String sql = "SELECT SUM(report_pn.report_pn_total) as  report_pn_sendstatus"
						+ ", SUM(report_pn.report_pn_target) as  report_pn_statuserror"
						+ " FROM report_pn LEFT JOIN  upload_pn  ON  upload_pn.upload_pn_id = report_pn.report_pn_idrefer "
						+ "where YEAR(report_pn.report_pn_date) = ? and upload_pn.upload_pn_due =? and  DATE(report_pn.report_pn_date) = ? and upload_pn.upload_pn_type =0  ";
				report_pn report_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{year,due,date},new report_pnmapper5());
				return report_pns;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getallRadarD with the same Id .......");
		   
		 }
	}	

	
	@Override
	public report_pn getallRadarM(String year,int due,String month) {
	
		
		  try {
				String sql = "SELECT SUM(report_pn.report_pn_total) as  report_pn_sendstatus"
						+ ", SUM(report_pn.report_pn_target) as  report_pn_statuserror"
						+ " FROM report_pn LEFT JOIN  upload_pn  ON  upload_pn.upload_pn_id = report_pn.report_pn_idrefer "
						+ "where YEAR(report_pn.report_pn_date) = ? and upload_pn.upload_pn_due =? and  MONTH(report_pn.report_pn_date) = ? and upload_pn.upload_pn_type =0  ";
				report_pn report_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{year,due,month},new report_pnmapper5());
				return report_pns;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getallRadarM with the same Id .......");
		   
		 }
	}	

	
	@Override
	public report_pn getallRadarW(String year,int due,int week) {
	
		
		  try {
				String sql = "SELECT SUM(report_pn.report_pn_total) as  report_pn_sendstatus"
						+ ", SUM(report_pn.report_pn_target) as  report_pn_statuserror"
						+ " FROM report_pn LEFT JOIN  upload_pn  ON  upload_pn.upload_pn_id = report_pn.report_pn_idrefer "
						+ "where YEAR(report_pn.report_pn_date) = ? and upload_pn.upload_pn_due =? and  WEEK(report_pn.report_pn_date) = ? and upload_pn.upload_pn_type =0  ";
				report_pn report_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{year,due,week},new report_pnmapper5());
				return report_pns;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getallRadarW with the same Id .......");
		   
		 }
	}	
	
	@Override
	public report_pn getallRadarY(String year) {
	
		
		  try {
				String sql = "SELECT SUM(report_pn.report_pn_total) as  report_pn_sendstatus"
						+ ", SUM(report_pn.report_pn_target) as  report_pn_statuserror"
						+ " FROM report_pn LEFT JOIN  upload_pn  ON  upload_pn.upload_pn_id = report_pn.report_pn_idrefer "
						+ "where YEAR(report_pn.report_pn_date) = ?   and upload_pn.upload_pn_type =1  ";
				report_pn report_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{year},new report_pnmapper5());
				return report_pns;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getallRadarY with the same Id .......");
		   
		 }
	}	
		
	
	
	
	@Override
	public report_pn getallstatus(String year,String respon) {
	
		
		  try {
				String sql = "SELECT SUM(report_pn_sendstatus) as  report_pn_sendstatus"
						+ ", SUM(report_pn_statuserror) as  report_pn_statuserror"
						+ " FROM report_pn where YEAR(report_pn_date) = ? and report_pn_respon =?   ";
				report_pn report_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{year,respon},new report_pnmapper5());
				return report_pns;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getallstatus with the same Id .......");
		   
		 }
	}	

	
	@Override
	public report_pn getallstatus(String year) {
	
		
		
		  try {
				String sql = "SELECT SUM(report_pn_sendstatus) as  report_pn_sendstatus"
						+ ", SUM(report_pn_statuserror) as  report_pn_statuserror"
						+ " FROM report_pn ";
				report_pn report_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{},new report_pnmapper5());
				return report_pns;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getallstatus with the same Id .......");
		   
		 }
	}	
	
	
	
	
	
	@Override
	public void save(report_pn report_pns) {
		Object[] report_pnInfo = { 
				report_pns.getReport_pn_date(),
				report_pns.getReport_pn_idrefer(),
				1,
				report_pns.getReport_pn_total(),
				report_pns.getReport_pn_respon(),
				report_pns.getReport_pn_app(),

				report_pns.getReport_pn_department(),
				};
				String sql = "INSERT INTO report_pn(report_pn_date,report_pn_idrefer"
						+ ",report_pn_target,report_pn_total,report_pn_respon,report_pn_app,report_pn_department) "
						+ "VALUES(?,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,report_pnInfo);

	}
	@Override
	public void save2(report_pn report_pns) {
		Object[] report_pnInfo = { 
				report_pns.getReport_pn_date(),
				report_pns.getReport_pn_idrefer(),
				1,
				report_pns.getReport_pn_total(),
				report_pns.getReport_pn_respon(),
				report_pns.getReport_pn_app(),
				report_pns.getReport_pn_department(),
				report_pns.getReport_pn_status(),
				report_pns.getReport_pn_type(),
				};
				String sql = "INSERT INTO report_pn(report_pn_date,report_pn_idrefer"
						+ ",report_pn_target,report_pn_total,report_pn_respon,report_pn_app,report_pn_department,report_pn_status,report_pn_type) "
						+ "VALUES(?,?,?,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,report_pnInfo);

	}
	
	@Override
	public report_pn getbyIds(int id) {
		  
		    
			  try {
				   String sql="select * from report_pn where report_pn_id=?   limit 1";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new report_pnmapper());
				
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one getbyIds with the same Id .......");
			   
			 }
	}
	
	
	
	@Override
	public report_pn getbyId(int id) {
		
		
		   
		    
			  try {
				  String sql="select * from report_pn where report_pn_idrefer=?  order by report_pn_date desc  limit 1 ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new report_pnmapper()); 
			
				
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one getbyId2 with the same Id .......");
			   
			 }
		    
	}
	

	
	@Override
	public report_pn getbyId2(int id,String date) {
		 
	
			  try {
				    String sql="select * from report_pn where report_pn_idrefer=? and report_pn_date = ?   order by report_pn_date desc  limit 1 ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id,date},new report_pnmapper()); 
			
				
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one getbyId2 with the same Id .......");
			   
			 }
	
	}
	
	@Override
	public report_pn getbytype2(int id) {
		 
	
			  try {
				    String sql="select * from report_pn where report_pn_idrefer=?  order by report_pn_id  desc  limit 1 ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new report_pnmapper()); 
			
				
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one getbytype2 with the same Id .......");
			   
			 }
	
	}
	
	
	@Override
	public int setalert1(int id) {
		  String sql=  "UPDATE report_pn SET report_pn_alert1=1"
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}
	
	@Override
	public int setalert2(int id) {
		  String sql=  "UPDATE report_pn SET report_pn_alert2=1"
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}
	
	@Override
	public int setStatus(int id,int status) {
		  String sql=  "UPDATE report_pn SET report_pn_status='"+status+"'"
			  		+ " WHERE report_pn_status !='"+status+"' and  report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	@Override
	public int setStatusall(int id,int status) {
		  String sql=  "UPDATE report_pn SET report_pn_status='"+status+"'"
			  		+ " WHERE    report_pn_idrefer="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	
	
	
	@Override
	public int setalert(int id,int status) {
		  String sql=  "UPDATE report_pn SET report_pn_alert1='"+status+"'"
			  		+ " WHERE   report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	@Override
	public int setStatus(int id,int status,String date) {
		  String sql=  "UPDATE report_pn SET report_pn_status='"+status+"',report_pn_datecheck='"+date+"' "
			  		+ " WHERE report_pn_status !='"+status+"' and  report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	@Override
	public int setStatus2(int id,int status) {
		  String sql=  "UPDATE report_pn SET report_pn_status2='"+status+"'"
			  		+ " WHERE report_pn_status2 !='"+status+"' and  report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	
	@Override
	public int setStatus(int id,int status,int success,int error) {
		  String sql=  "UPDATE report_pn SET report_pn_status='"+status+"'"
		  		+ ",report_pn_statussccess='"+success+"'"
		  		+ ",report_pn_statuserror='"+error+"'   "
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	
	@Override
	public int checkStatus(int idrefer,int status,String date) {
		  String sql=  "SELECT  count(*) FROM report_pn "
			  		+ " WHERE report_pn_date  <= ?  and report_pn_status = ? and  report_pn_idrefer	= ?";
		   int count = jdbcTemplate.queryForObject(sql, new Object[] { date,status,idrefer }, Integer.class);
		   return count;
	}	
	
	@Override
	public int settotal(int id) {
		  String sql=  "UPDATE report_pn SET report_pn_total = 1"
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	@Override
	public int unsettotal(int total,int id) {
		  String sql=  "UPDATE report_pn SET report_pn_total=0 ,report_pn_statussccess =0 "
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	
	@Override
	public int setsendstatus(int id) {
		  String sql=  "UPDATE report_pn SET report_pn_sendstatus=1 "
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	@Override
	public int setappstatus(int id,int diff) {
		  String sql=  "UPDATE report_pn SET  report_pn_appstatus='"+diff+"',report_pn_status = 3 "
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	@Override
	public int setappstatus(int id,int diff,String date) {
		  String sql=  "UPDATE report_pn SET  report_pn_appstatus='"+diff+"',report_pn_status = 3,report_pn_datecheck= '"+date+"' "
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	@Override
	public int settotal2(int id) {
		  String sql=  "UPDATE report_pn SET report_pn_total=0"
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	@Override
	public int update(report_pn report_pns) {
		  String sql=  "UPDATE report_pn SET report_pn_target='1'"
			  		+ ",report_pn_total='"+report_pns.getReport_pn_total()+"'"
			  		+ " WHERE report_pn_id="+report_pns.getReport_pn_id();
			    return jdbcTemplate.update(sql); 
	}
	@Override
	public int update2(report_pn report_pns) {
		  String sql=  "UPDATE report_pn SET report_pn_target='1'"
			  		+ ",report_pn_total='"+report_pns.getReport_pn_total()+"'"
			  		+ ",report_pn_date='"+report_pns.getReport_pn_date()+"'"
			  		+ ",report_pn_respon='"+report_pns.getReport_pn_respon()+"'"
					+ ",report_pn_app='"+report_pns.getReport_pn_app()+"'"
			  		+ " WHERE report_pn_date = '"+report_pns.getReport_pn_date()+"' and  report_pn_idrefer="+report_pns.getReport_pn_idrefer();
			    return jdbcTemplate.update(sql); 
	}
	@Override
	public int delete(int id) {
		 String sql="delete from report_pn where report_pn_id =? and report_pn_type= 1 ";    
		    return jdbcTemplate.update(sql, new Object[] { id });
	}
	
	
	@Override
	public int deletes(int id) {
		 String sql="delete from report_pn where report_pn_id =?  ";    
		    return jdbcTemplate.update(sql, new Object[] { id });
	}
	
	
	@Override
	public int deletebyreport(int id,String date){
		 String sql="delete from report_pn where report_pn_idrefer='"+id+"' and report_pn_date <= '"+date+"' ";    
		    return jdbcTemplate.update(sql);
	   
	}
	@Override
	public int deletebyreport(int id){
		 String sql="delete from report_pn where report_pn_idrefer='"+id+"'  ";    
		    return jdbcTemplate.update(sql);
	   
	}
	
	@Override
	public int deletebyupload_pn(int id,String date){
		 String sql="delete from report_pn where report_pn_idrefer='"+id+"' and report_pn_date >= '"+date+"' ";    
		    return jdbcTemplate.update(sql);
	   
	}
	
	
	@Override
	public boolean isreportExists(int id,String date) {
	    String sql = "SELECT count(*) FROM report_pn WHERE report_pn_idrefer = ? and report_pn_date=? ";
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id,date }, Integer.class);

	    return count > 0;
	}
	
	@Override
	public boolean isreportExists(int id,String months,String years) {
	    String sql = "SELECT count(*) FROM report_pn WHERE report_pn_idrefer = ? and MONTH(report_pn_date)=?  and YEAR(report_pn_date)=?";
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id,months,years }, Integer.class);
	    return count > 0;
	}
	
	@Override
	public boolean isreportExists(int id,int week, String years) {
	    String sql = "SELECT count(*) FROM report_pn WHERE report_pn_idrefer = ? and WEEK(report_pn_date)=?  and YEAR(report_pn_date)=?";
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id,week,years }, Integer.class);
	    return count > 0;
	}
	
	
	@Override
	public boolean isreportExists(int id) {
	    String sql = "SELECT count(*) FROM report_pn WHERE report_pn_idrefer = ?  ";
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);

	    return count > 0;
	}
	
	
	
	@Override
	public report_pn getsum(String date) {
		  String sql="select report_pn_id"
		  		+ ",SUM(report_pn_target) as report_pn_target"
		  		+ ",SUM(report_pn_appstatus) as report_pn_appstatus"
		  		+ ",SUM(report_pn_sendstatus) as report_pn_sendstatus"
		  		+ ",SUM(report_pn_total) as report_pn_total"
		  		+ ",SUM(report_pn_statussccess) as report_pn_statussccess"
		  		+ ",SUM(report_pn_app_statussccess) as report_pn_app_statussccess"
		  		+ ",report_pn_date,report_pn_idrefer"
		  		+ ",report_pn_respon,report_pn_app"
		  		+ ",report_pn_status,report_pn_department from report_pn"
		  		+ " where report_pn_date =?     ";     
		    return jdbcTemplate.queryForObject(sql, new Object[]{date},new report_pnmapper()); 
	
	
	}

	
	
	@Override
	public report_pn getsummonth(String month,String year) {
		  String sql="select report_pn_id"
		  		+ ",SUM(report_pn_target) as report_pn_target"
		  		+ ",SUM(report_pn_total) as report_pn_total"
		  		+ ",SUM(report_pn_appstatus) as report_pn_appstatus"
		  		+ ",SUM(report_pn_sendstatus) as report_pn_sendstatus"
		  		+ ",SUM(report_pn_statussccess) as report_pn_statussccess"
		  		+ ",SUM(report_pn_app_statussccess) as report_pn_app_statussccess"
		  		+ ",report_pn_date,report_pn_idrefer"
		  		+ ",report_pn_respon,report_pn_app"
		  		+ ",report_pn_status,report_pn_department from report_pn"
		  		+ " where MONTH(report_pn_date) =? and  YEAR(report_pn_date) =?      ";     
		    return jdbcTemplate.queryForObject(sql, new Object[]{month,year},new report_pnmapper()); 
	
	
	}

	@Override
	public report_pn getsumyear(String year) {
		  String sql="select report_pn_id"
		  		+ ",SUM(report_pn_target) as report_pn_target"
		  		+ ",SUM(report_pn_total) as report_pn_total"
				+ ",SUM(report_pn_appstatus) as report_pn_appstatus"
		  		+ ",SUM(report_pn_sendstatus) as report_pn_sendstatus"
		  		+ ",SUM(report_pn_statussccess) as report_pn_statussccess"
		  		+ ",SUM(report_pn_app_statussccess) as report_pn_app_statussccess"
		  		+ ",report_pn_date,report_pn_idrefer"
		  		+ ",report_pn_respon,report_pn_app"
		  		+ ",report_pn_status,report_pn_department from report_pn"
		  		+ " where YEAR(report_pn_date) =?      ";     
		    return jdbcTemplate.queryForObject(sql, new Object[]{year},new report_pnmapper()); 
	
	
	}
	
	
	
	@Override
	public List<report_pn_pdf> getreporttopdf(String month,String year) {
		String sql = "SELECT report_pn_respon,SUM(report_pn_total) as  report_pn_total"
				+ ", SUM(report_pn_target) as  report_pn_target"
				+ ", SUM(report_pn_appstatus) as  report_pn_appstatus"
				+ ", SUM(report_pn_sendstatus) as  report_pn_sendstatus"
				+ ", SUM(report_pn_statuserror) as  report_pn_statuserror"
				+ " FROM report_pn  "
				+ " where MONTH(report_pn_date) = ? and  YEAR(report_pn_date) = ?  group by report_pn_respon";
		List<report_pn_pdf> report_pns  =  jdbcTemplate.query(sql,new Object[]{month,year},new reportpnpdf());
		return report_pns;
	}
	
	
	@Override
	public List<report_pn_pdf> getreporttopdf(String year) {
		String sql = "SELECT report_pn_respon,SUM(report_pn_total) as  report_pn_total"
				+ ", SUM(report_pn_target) as  report_pn_target"
				+ ", SUM(report_pn_appstatus) as  report_pn_appstatus"
				+ ", SUM(report_pn_sendstatus) as  report_pn_sendstatus"
				+ ", SUM(report_pn_statuserror) as  report_pn_statuserror"
				+ " FROM report_pn  "
				+ " where  YEAR(report_pn_date) = ?  group by report_pn_respon";
		List<report_pn_pdf> report_pns  =  jdbcTemplate.query(sql,new Object[]{year},new reportpnpdf());
		return report_pns;
	}
	
	@Override
	public List<report_pn_pdf> getreport_pn_sendstatus(String month,String year) {
		String sql = "SELECT report_pn_respon,SUM(report_pn_total) as  report_pn_total"
				+ ", SUM(report_pn_target) as  report_pn_target"
				+ ", SUM(report_pn_appstatus) as  report_pn_appstatus"
				+ ", SUM(report_pn_sendstatus - report_pn_statussccess) as  report_pn_sendstatus"
				+ ", SUM(report_pn_statuserror) as  report_pn_statuserror"
				+ " FROM report_pn  "
				+ " where  MONTH(report_pn_date) = ? AND  YEAR(report_pn_date) = ?  AND report_pn_date <= NOW()  "
				+ "group by report_pn_respon ORDER BY SUM(report_pn_sendstatus - report_pn_statussccess) DESC   limit 5 ";
		List<report_pn_pdf> report_pns  =  jdbcTemplate.query(sql,new Object[]{month,year},new reportpnpdf());
		return report_pns;
	}
	
	
	
	@Override
	public List<report_pn_pdf> getreport_pn_sendstatus(String year) {
		String sql = "SELECT report_pn_respon,SUM(report_pn_total) as  report_pn_total"
				+ ", SUM(report_pn_target) as  report_pn_target"
				+ ", SUM(report_pn_appstatus) as  report_pn_appstatus"
				+ ", SUM(report_pn_sendstatus - report_pn_statussccess) as  report_pn_sendstatus"
				+ ", SUM(report_pn_statuserror) as  report_pn_statuserror"
				+ " FROM report_pn  "
				+ " where   YEAR(report_pn_date) = ?  AND report_pn_date <= NOW()  "
				+ "group by report_pn_respon  ORDER BY SUM(report_pn_sendstatus - report_pn_statussccess) DESC      limit 5 ";
		List<report_pn_pdf> report_pns  =  jdbcTemplate.query(sql,new Object[]{year},new reportpnpdf());
		return report_pns;
	}
	
	
	
	
	
	
	@Override
	public List<report_pn_pdf> getreport_pn_statuserror(String month,String year) {

	    String sql = "SELECT report_pn_respon, "
	               + "SUM(report_pn_target - report_pn_total) as report_pn_total, "
	               + "SUM(report_pn_target) as report_pn_target, "
	               + "SUM(report_pn_appstatus) as report_pn_appstatus, "
	               + "SUM(report_pn_target - report_pn_sendstatus) as report_pn_sendstatus, "
	               + "SUM(report_pn_statuserror) as report_pn_statuserror "
	               + "FROM report_pn "
	               + "WHERE  "
	               + " MONTH(report_pn_date) = ? and  YEAR(report_pn_date) = ?  and report_pn_date < NOW()  "
	               + "GROUP BY report_pn_respon "
	               + "ORDER BY SUM(report_pn_target - report_pn_sendstatus) DESC " // จัดเรียงตามค่า SUM ที่ต้องการ
	               + "LIMIT 5";
		
		
		List<report_pn_pdf> report_pns  =  jdbcTemplate.query(sql,new Object[]{month,year},new reportpnpdf());
		return report_pns;
	}
		
	
	@Override
	public List<report_pn_pdf> getreport_pn_statuserror(String year) {
	    String sql = "SELECT report_pn_respon, "
	               + "SUM(report_pn_target - report_pn_total) as report_pn_total, "
	               + "SUM(report_pn_target) as report_pn_target, "
	               + "SUM(report_pn_appstatus) as report_pn_appstatus, "
	               + "SUM(report_pn_target - report_pn_sendstatus) as report_pn_sendstatus, "
	               + "SUM(report_pn_statuserror) as report_pn_statuserror "
	               + "FROM report_pn "
	               + "WHERE  "
	               + " YEAR(report_pn_date) = ?  and report_pn_date < NOW()  "
	               + "GROUP BY report_pn_respon "
	               + "ORDER BY SUM(report_pn_target - report_pn_sendstatus) DESC " // จัดเรียงตามค่า SUM ที่ต้องการ
	               + "LIMIT 5";
	    List<report_pn_pdf> report_pns = jdbcTemplate.query(sql, new Object[]{year}, new reportpnpdf());
	    return report_pns;
	}
	
	
	
	@Override
	public int setdateapp(int id,String date) {
		  String sql=  "UPDATE report_pn SET report_pn_datecheck='"+date+"' "
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	@Override
	public int setdateadd(int id,String date) {
		  String sql=  "UPDATE report_pn SET report_pn_dateupload='"+date+"' "
			  		+ " WHERE report_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	@Override
	public int countDailyYeardue(String year,String username,int due) {
		String sql = "SELECT count(*)  FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ " WHERE  YEAR(report_pn_date) =?  and report_pn.report_pn_respon =?  and upload_pn.upload_pn_due =?  and upload_pn.upload_pn_type =0 ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,due }, Integer.class);
	}
	
	
	@Override
	public int countDailyYear(String year,String username,int due) {
		String sql = "SELECT count(*)  FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ " WHERE  YEAR(report_pn_date) =?  and report_pn.report_pn_respon =?  and upload_pn.upload_pn_due =?  and upload_pn.upload_pn_type =0"
				+ " and  report_pn.report_pn_target = report_pn.report_pn_total ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,due }, Integer.class);
	}
	

	@Override
	public int countDailyYeardue2(String year,String username) {
		String sql = "SELECT count(*)  FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ " WHERE  YEAR(report_pn_date) =?  and report_pn.report_pn_respon =?   and upload_pn.upload_pn_type =1 ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username}, Integer.class);
	}
	
	
	@Override
	public int countDailyYear2(String year,String username) {
		String sql = "SELECT count(*)  FROM report_pn  "
				+ "LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer  "
				+ " WHERE  YEAR(report_pn_date) =?  and report_pn.report_pn_respon =?  and upload_pn.upload_pn_type =1"
				+ " and  report_pn.report_pn_target = report_pn.report_pn_total ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username }, Integer.class);
	}
	

	@Override
	public List<report_pn> getrank(String year) {
		String sql = "SELECT report_pn_id,report_pn_date,report_pn_idrefer,report_pn_idrefer,report_pn_target"
				+ ",report_pn_respon,report_pn_app,report_pn_status,report_pn_department,report_pn_statussccess"
				+ ",count(*) as report_pn_total FROM report_pn  "
				+ " WHERE  YEAR(report_pn_date) =?  and report_pn_target != report_pn_total   group by report_pn_respon  order by  report_pn_total desc   limit 5  ";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{year},new report_pnmapper());
		return report_pns;
	}
	
	@Override
	public List<report_pn> getrank2(String year) {
		String sql = "SELECT report_pn_id,report_pn_date,report_pn_idrefer,report_pn_idrefer,report_pn_target"
				+ ",report_pn_respon,report_pn_app,report_pn_status,report_pn_department,report_pn_statussccess"
				+ ",count(*) as report_pn_total FROM report_pn  "
				+ " WHERE  YEAR(report_pn_date) =?  and report_pn_target = report_pn_total and report_pn_statuserror >  0  group by report_pn_respon  order by  report_pn_total desc   limit 5  ";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{year},new report_pnmapper());
		return report_pns;
	}
	
	
	
	@Override
	public int countYear(String year) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =?  and  DATE(report_pn_date) <= CURDATE() AND DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY)    ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year}, Integer.class);
	}
	
	
	@Override
	public int countYearupload(String year) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =?  "
				+ "and  report_pn_status > 1  and  DATE(report_pn_date) <= CURDATE()   AND DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year}, Integer.class);
	}
	
	@Override
	public int countYearuploadsuccess(String year) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =? "
				+ " and report_pn_status = 3 and  DATE(report_pn_date)  <= CURDATE() AND DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY)  ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year}, Integer.class);
	}
	
	@Override
	public int countYearuploadsuccess2(String year) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =? "
				+ " and report_pn_status != 1  and  DATE(report_pn_date)  <= CURDATE()  AND DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY)  ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year}, Integer.class);
	}
	
	@Override
	public int countYearuploadreject(String year) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =?  and report_pn_status = -1"
				+ " and  DATE(report_pn_date)  <= CURDATE()  AND DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY)  ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year}, Integer.class);
	}
	
	@Override
	public int countYear(String year,String username) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =?"
				+ " and  report_pn_respon = ? and DATE(report_pn_date) <= CURDATE()  and DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username}, Integer.class);
	}
	
	
	@Override
	public int countYearupload(String year,String username) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =?  and report_pn_status > 1  "
				+ " and (report_pn_respon = ? or report_pn_app = ?)  and DATE(report_pn_date) <= CURDATE() and DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,username}, Integer.class);
	}
	
	@Override
	public int countYearuploadsuccess(String year,String username) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =?  and report_pn_status = 3 "
				+ " and (report_pn_respon = ? or report_pn_app = ?) and  DATE(report_pn_date)  <= CURDATE() and DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY)  ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,username}, Integer.class);
	}
	
	@Override
	public int countYearuploadsuccess2(String year,String username) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =?  and report_pn_status != 1  "
				+ "and (report_pn_respon = ? or report_pn_app = ?) and  DATE(report_pn_date)  <= CURDATE() and DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY)  ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,username}, Integer.class);
	}
	
	@Override
	public int countYearuploadreject(String year,String username) {
		String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =? "
				+ " and report_pn_status = -1  and (report_pn_respon = ? or report_pn_app = ?) and  DATE(report_pn_date)  <= CURDATE() and DATE(report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY)   ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,username}, Integer.class);
	}
	
	
	
	
	
	
	
	@Override
	public List<showupload_pn>  getalltype(String year,int type) {
		String sql = null;
	
		if(type == 1) {
			 sql = "SELECT *  FROM report_pn "
					 + " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
			 		+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
			 		+ "  WHERE  YEAR(report_pn_date) =? and  DATE(report_pn.report_pn_date)  <= CURDATE() and  DATE(report_pn.report_pn_date)  > DATE_ADD(CURDATE(), INTERVAL -31 DAY)";
		}
		if(type == 2) {
			 sql = "SELECT *  FROM report_pn  "
					 + " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
				 	+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
					 + " WHERE  YEAR(report_pn.report_pn_date) =?  and report_pn.report_pn_status != 1 "
					 + "  and DATE(report_pn.report_pn_date)   <= CURDATE() and  DATE(report_pn.report_pn_date)  > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		}
		if(type == 3) {
			 sql = "SELECT *  FROM report_pn  "
					 + " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
					+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
			 		+ " WHERE  YEAR(report_pn.report_pn_date) =?  and report_pn.report_pn_status != 1   "
			 		+ "and  DATE(report_pn.report_pn_date)  <= CURDATE() and  DATE(report_pn.report_pn_date)  > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		}
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{year},new upload_pnmapper2s());
		return upload_pns;
	}
	
	
	@Override
	public List<showupload_pn> getalltype(String year,int type,String username) {
		String sql = null;
	
		if(type == 1) {
			 sql = "SELECT *  FROM report_pn "
					 + " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
			 		+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
			 		+ "  WHERE  YEAR(report_pn_date) =? and  DATE(report_pn_date)  <= CURDATE()  and  DATE(report_pn.report_pn_date)  > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
			 		+ "and report_pn.report_pn_status != -1  and report_pn.report_pn_respon =?  ";
		}
		if(type == 2) {
			 sql = "SELECT *  FROM report_pn  "
					 + " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
				 	+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
					 + " WHERE  YEAR(report_pn.report_pn_date) =? "
					 + " and report_pn.report_pn_status != 1  and report_pn.report_pn_app =? and  DATE(report_pn_date)  <= CURDATE()  and  DATE(report_pn.report_pn_date)  > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		}
		if(type == 3) {
			 sql = "SELECT *  FROM report_pn  "
					 + " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
					+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
			 		+ " WHERE  YEAR(report_pn.report_pn_date) =?  "
			 		+ "and report_pn.report_pn_status != 1  and report_pn.report_pn_respon =? and  DATE(report_pn_date)  <= CURDATE() and  DATE(report_pn.report_pn_date)  > DATE_ADD(CURDATE(), INTERVAL -31 DAY)  ";
		}
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{year,username},new upload_pnmapper2s());
		return upload_pns;
	}
	
	@Override
	public int check(String date,int idrefer) {
		  String sql=  "SELECT  count(*) FROM report_pn "
			  		+ " WHERE report_pn_date  = ?  and  report_pn_idrefer	= ?";
		   int count = jdbcTemplate.queryForObject(sql, new Object[] { date,idrefer}, Integer.class);
		   return count;
	}	
	
	
	
	
	@Override
	public List<report_pn> rawreport(String year) {
		String sql = "SELECT  * FROM report_pn  "
				+ " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
				+ " where report_pn.report_pn_respon IS NOT NULL and  YEAR(report_pn.report_pn_date) = ? order by report_pn_id desc limit 5000 ";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{year},new report_pnmapper6());
		return report_pns;
	}
	
	
	
	
	
	@Override
	public List<report_pn> rawreportfull(String datestart,String dateend,String respon,String app,int type,String sort) {
		
		
		String sub ="";
		
		if(!datestart.isEmpty()) {
			sub += "  and DATE(report_pn.report_pn_date) >= '"+datestart+"'  ";
		}
		 
		if(!dateend.isEmpty()) {
			sub += "  and DATE(report_pn.report_pn_date) <= '"+dateend+"'  ";
		}
		
		if(!respon.isEmpty()) {
			 sub += "  and report_pn.report_pn_respon = '"+respon+"'  ";
		}
		
		if(!app.isEmpty()) {
			 sub += "  and report_pn.report_pn_app = '"+app+"'  ";
		}
		
		
		if(type > 0 ) {
			if(type == 1) {
				 sub += "  and upload_pn.upload_pn_type = 0 and upload_pn.upload_pn_due = 0  ";
			}
			if(type == 2) {
				 sub += "  and upload_pn.upload_pn_type = 0 and upload_pn.upload_pn_due = 1  ";
			}
			if(type == 3) {
				 sub += "  and upload_pn.upload_pn_type = 0 and upload_pn.upload_pn_due = 2  ";
			}
			if(type == 4) {
				 sub += "  and upload_pn.upload_pn_type = 1  ";
			}
			
			if(type == 5) {
				 sub += "  and upload_pn.upload_pn_type = 2  ";
			}
		}
		
		

		if(!sort.isEmpty()) {
			 sub += "  order by "+sort+" desc ";
		}else {
			sub += "order by report_pn_id  desc  ";
		}
		 
		
		String sql = "SELECT  * FROM report_pn    "
				+ " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
				+ " where report_pn.report_pn_respon IS NOT NULL "+sub+""
				+ " limit 5000 ";
		

		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{},new report_pnmapper6());

		return report_pns;
	}
	
	
	
	@Override
	public int countDue(String year,String username,int due) {
		String sql = "SELECT count(*)  FROM report_pn "
				+ " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
				+ "  WHERE  YEAR(report_pn.report_pn_date) =?"
				+ " and  (report_pn.report_pn_respon = ? or report_pn.report_pn_app = ?)  and  upload_pn.upload_pn_due = ? and  upload_pn.upload_pn_type = 0 "
				+ "and DATE(report_pn.report_pn_date) <= CURDATE()  "
				+ "and DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,username,due}, Integer.class);
	}
	
	@Override
	public int countDueSuccess(String year,String username,int due) {
		String sql = "SELECT count(*)  FROM report_pn "
				+ " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
				+ "  WHERE  YEAR(report_pn.report_pn_date) =?"
				+ " and  (report_pn.report_pn_respon = ? or report_pn.report_pn_app = ?)  "
				+ "and  upload_pn.upload_pn_due = ?"
				+ " and  upload_pn.upload_pn_type = 0 and report_pn.report_pn_status = 3 "
				+ "and DATE(report_pn.report_pn_date) <= CURDATE()  "
				+ "and DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,username,due}, Integer.class);
	}
	
	
	
	@Override
	public int countDue2(String year,String username,int type) {
		String sql = "SELECT count(*)  FROM report_pn "
				+ " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
				+ "  WHERE  YEAR(report_pn.report_pn_date) =?"
				+ " and  (report_pn.report_pn_respon = ? or report_pn.report_pn_app = ?)  and  upload_pn.upload_pn_type = ? "
				+ "and DATE(report_pn.report_pn_date) <= CURDATE()  "
				+ "and DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,username,type}, Integer.class);
	}
	
	@Override
	public int countDueSuccess2(String year,String username,int type) {
		String sql = "SELECT count(*)  FROM report_pn "
				+ " LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id"
				+ "  WHERE  YEAR(report_pn.report_pn_date) =?"
				+ " and  (report_pn.report_pn_respon = ? or report_pn.report_pn_app = ?)  "
				+ " and  upload_pn.upload_pn_type = ? and report_pn.report_pn_status = 3 "
				+ "and DATE(report_pn.report_pn_date) <= CURDATE()  "
				+ "and DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {year,username,username,type}, Integer.class);
	}
	
	

	@Override
	public int setStatusapp(int id,String date) {
		  String sql=  "UPDATE report_pn SET report_pn_status= 3  WHERE report_pn_idrefer = ? and report_pn_date <=  ? ";
			    return jdbcTemplate.update(sql, new Object[] {id,date}); 
	}	
	
	

	@Override
	public int setStatusadd(int id,String date) {
		  String sql=  "UPDATE report_pn SET report_pn_status= 1  WHERE report_pn_idrefer = ? and report_pn_date <=  ? and report_pn_status = 0  ";
			    return jdbcTemplate.update(sql, new Object[] {id,date}); 
	}	
	
	
	
	@Override
	public void savebatch(List<report_pn> report_pn) {

		String sql = "INSERT INTO report_pn(report_pn_date,report_pn_idrefer,report_pn_respon,report_pn_app,report_pn_status,report_pn_type,report_pn_target,report_pn_total) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		
      jdbcTemplate.batchUpdate(sql,report_pn,1000,
                new ParameterizedPreparedStatementSetter<report_pn>() {
                    public void setValues(PreparedStatement ps, report_pn argument) 
						throws SQLException {
                        ps.setString(1, argument.getReport_pn_date());
                        ps.setInt(2, argument.getReport_pn_idrefer());
                        ps.setString(3, argument.getReport_pn_respon());
                        ps.setString(4, argument.getReport_pn_app());
                        ps.setInt(5, argument.getReport_pn_status());
                        ps.setInt(6, argument.getReport_pn_type());
                        ps.setInt(7,1);
                        ps.setInt(8,1);
                    }
                });
    }
	
	@Override
	public void updatebatch(List<report_pn> report_pn) {

		String sql = "UPDATE report_pn SET report_pn_sendstatus=? ,report_pn_appstatus=?  ,report_pn_statussccess=? ,report_pn_app_statussccess=?"
				+ " WHERE report_pn_id =? ";
		
      jdbcTemplate.batchUpdate(sql,report_pn,1000,
                new ParameterizedPreparedStatementSetter<report_pn>() {
                    public void setValues(PreparedStatement ps, report_pn argument) 
						throws SQLException {
                        ps.setInt(1, argument.getReport_pn_sendstatus());
                        ps.setInt(2, argument.getReport_pn_appstatus());
                        ps.setInt(3, argument.getReport_pn_statussccess());
                        ps.setInt(4, argument.getReport_pn_app_statussccess());
                        ps.setInt(5, argument.getReport_pn_id());
    
                    }
                });
    }
	
	
	
	@Override
	public int getPending(String user_name) {
	    String sql = "SELECT count(*) FROM report_pn WHERE report_pn_respon = ? and report_pn_status=1  and report_pn_date <= NOW() and report_pn_type=0    ";
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { user_name }, Integer.class);
	    return count;
	}
	
	@Override
	public int getAllPending(int id_refer,String user_name) {
	    String sql = "SELECT count(*) FROM report_pn WHERE report_pn_idrefer=? and report_pn_respon = ? and report_pn_status=1  and report_pn_date <= NOW()";
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id_refer,user_name }, Integer.class);
	    return count;
	}
	
	
	@Override
	public int setApprove(int id_refer,String date_due,String date_check) {
		
		System.out.println(" id_refer "+id_refer );
		System.out.println(" date_due "+date_due );
		System.out.println(" date_check "+date_check );
		
		
		  String sql=  "UPDATE report_pn SET report_pn_status= 2 ,report_pn_datecheck= ?,report_pn_appstatus= 1 "
			  		+ " WHERE report_pn_idrefer =?  and  report_pn_date= ? ";
			    return jdbcTemplate.update(sql, new Object[] { date_check,id_refer,date_due }); 
	}	
	
	@Override
	public int setSend(int id_refer,String date_due,String dateupload) {
		
		System.out.println(" id_refer "+id_refer );
		System.out.println(" date_due "+date_due );
		System.out.println(" date_check "+dateupload );
		
		
		  String sql=  "UPDATE report_pn SET report_pn_dateupload= ?,report_pn_sendstatus= 1 "
			  		+ " WHERE report_pn_idrefer =?  and  report_pn_date= ? ";
			    return jdbcTemplate.update(sql, new Object[] { dateupload,id_refer,date_due }); 
	}	
	

	
	
	@Override
	public int setApproveSuccess(int id_refer,String date_due) {
		
	
		
		  String sql=  "UPDATE report_pn SET report_pn_app_statussccess = 1   WHERE report_pn_idrefer =?  and  report_pn_date= ? ";
			    return jdbcTemplate.update(sql, new Object[] { id_refer,date_due }); 
	}	
	
	@Override
	public int setSendSuccess(int id_refer,String date_due) {

		  String sql=  "UPDATE report_pn SET report_pn_statussccess = 1   WHERE report_pn_idrefer =?  and  report_pn_date= ? ";
			    return jdbcTemplate.update(sql, new Object[] { id_refer,date_due }); 
	}	
	
	
	
	
	
	@Override
	public List<report_pn> getAllDateRange(String start,String end) {
		String sql = "SELECT * FROM report_pn where report_pn_date >= ? and  report_pn_date <=? ";
		List<report_pn> report_pns  =  jdbcTemplate.query(sql,new Object[]{start,end},new report_pnmapper());
		return report_pns;
	}
	
	

		@Override
		public List<report_pn> getPage(int id, int page) {
		    int limit = 20; // จำนวนรายการต่อหน้า
		    int offset = (page - 1) * limit; // คำนวณตำแหน่งเริ่มต้นของหน้า
		    String sql = "SELECT * FROM report_pn WHERE report_pn_idrefer = ? AND report_pn_date <= NOW() ORDER BY report_pn_id DESC LIMIT ? OFFSET ?";
		    List<report_pn> report_pns = jdbcTemplate.query(
		        sql,
		        new Object[]{id, limit, offset},
		        new report_pnmapper()
		    );
		    return report_pns;
		}
		
		
		@Override
		public List<report_pn> getPage(int id, int page,user users) {
		    int limit = 20; // จำนวนรายการต่อหน้า
		    int offset = (page - 1) * limit; // คำนวณตำแหน่งเริ่มต้นของหน้า
		    String sql = "SELECT * FROM report_pn WHERE report_pn_idrefer = ?  "
		    		+ " AND ( report_pn_app =? OR FIND_IN_SET(?,report_pn_respon ) ) "
		    		+ "AND report_pn_date <= NOW() ORDER BY report_pn_id DESC LIMIT ? OFFSET ?";
		    List<report_pn> report_pns = jdbcTemplate.query(
		        sql,
		        new Object[]{id,users.getUser_name(),users.getUser_name(), limit, offset},
		        new report_pnmapper()
		    );
		    return report_pns;
		}
		
		
		
		
		@Override
		public List<report_pn> getPageWeeK(String date_start,String date_end, int page) {
		    int limit = 20; // จำนวนรายการต่อหน้า
		    int offset = (page - 1) * limit; // คำนวณตำแหน่งเริ่มต้นของหน้า
		    String sql = "SELECT * FROM report_pn "
		    		+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = report_pn.report_pn_idrefer   "
		    		+ "WHERE report_pn.report_pn_date >= ? AND report_pn.report_pn_date <= ?"
		    		+ " ORDER BY report_pn.report_pn_id DESC LIMIT ? OFFSET ?";
		    List<report_pn> report_pns = jdbcTemplate.query(
		        sql,
		        new Object[]{date_start,date_end, limit, offset},
		        new report_pnmapper6()
		    );
		    return report_pns;
		}
		
		
		
		@Override
		public report_pn StatusCount(int id_refer) {
			  String sql="select report_pn_id"
			  		+ ",SUM(report_pn_target) as report_pn_target"
			  		+ ",SUM(report_pn_total) as report_pn_total"
					+ ",SUM(report_pn_appstatus) as report_pn_appstatus"
			  		+ ",SUM(report_pn_sendstatus) as report_pn_sendstatus"
			  		+ ",SUM(report_pn_statussccess) as report_pn_statussccess"
			  		+ ",SUM(report_pn_app_statussccess) as report_pn_app_statussccess"
			  		+ ",report_pn_date,report_pn_idrefer"
			  		+ ",report_pn_respon,report_pn_app"
			  		+ ",report_pn_status,report_pn_department from report_pn"
			  		+ " where report_pn_date <= NOW() and report_pn_idrefer = ?     ";     
			    return jdbcTemplate.queryForObject(sql, new Object[]{id_refer},new report_pnmapper()); 
		
		
		}
		@Override
		public int CheckGoal(String date,String username,int idrefer) {
			String sql = "SELECT count(*)  FROM report_pn   WHERE  YEAR(report_pn_date) =?  and report_pn_respon =?  and report_pn_idrefer =? ";
			return jdbcTemplate.queryForObject(
	                sql, new Object[] {date,username,idrefer }, Integer.class);
		}
		

	
		
}

