package com.smepro.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smepro.models.Calendar;
import com.smepro.models.Keyactivity;
import com.smepro.models.MasterBudget;
import com.smepro.models.Milestone;
import com.smepro.models.Outcome;
import com.smepro.models.Resource;
import com.smepro.models.Risk;
import com.smepro.models.Taskview;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.Use_resources;
import com.smepro.models.actionplan;
import com.smepro.models.dar;
import com.smepro.models.jobview;
import com.smepro.models.report_pn;
import com.smepro.models.showupload_pn;
import com.smepro.models.task;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.rowmapper.Budgetmapper;
import com.smepro.rowmapper.Calendarmapper;
import com.smepro.rowmapper.Keyactivitymapper;
import com.smepro.rowmapper.Milestonemapper;
import com.smepro.rowmapper.Outcomemapper;
import com.smepro.rowmapper.Resourcemapper;
import com.smepro.rowmapper.Riskmapper;
import com.smepro.rowmapper.Taskviewmapper;
import com.smepro.rowmapper.UploadpnTurnmapper;
import com.smepro.rowmapper.Use_resourcesmapper;
import com.smepro.rowmapper.actionplanmapper2;
import com.smepro.rowmapper.darmapper3;
import com.smepro.rowmapper.jobviewmapper;
import com.smepro.rowmapper.taskmapper;
import com.smepro.rowmapper.upload_pnduemapper;
import com.smepro.rowmapper.upload_pnmapper;
import com.smepro.rowmapper.upload_pnmapper2;
import com.smepro.rowmapper.upload_pnmapper3;
import com.smepro.rowmapper.upload_pnmapper4;
import com.smepro.rowmapper.upload_pnmapper4s;
import com.smepro.rowmapper.usermapper;
import com.smepro.rowmapper.usermapper2;


public class upload_pnDAOlmpl implements upload_pnDAO {

private JdbcTemplate jdbcTemplate;
	
	public upload_pnDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	

	@Override
	public List<showupload_pn> getallfull() {
		
		String sql = "SELECT  "
				+ " ( SELECT COUNT(*) "
				+ "      FROM report_pn AS i "
				+ "      WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status < 2  AND i.report_pn_date <= NOW() AND DATE(i.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status1"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status =2  AND o.report_pn_date <= NOW() AND DATE(o.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status2"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS p "
				+ "      WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  AND p.report_pn_date <= NOW()  AND DATE(p.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status3"
				+ ",a.* FROM upload_pn AS a "
				+ "  order by  a.upload_pn_id desc limit 3000 ";
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper4s());
		return upload_pns;
	}	
	
	
	
	@Override
	public List<upload_pn> getall(int id) {
		String sql = "SELECT * FROM upload_pn  where upload_pn_idrefer=?   order by upload_pn_id desc limit 3000";
		List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{id},new upload_pnmapper());
		return upload_pns;
	}
	@Override
	public List<upload_pn> getall() {
		String sql = "SELECT * FROM upload_pn  order by upload_pn_id desc limit 3000";
		List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper());
		return upload_pns;
	}	
	@Override
	public List<upload_pn> getallByActivate() {
		String sql = "SELECT * FROM upload_pn WHERE upload_pn_end ='0' AND upload_pn_type != 2   order by upload_pn_id desc limit 3000";
		List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper());
		return upload_pns;
	}	
	@Override
	public List<upload_pn> getallbyid(int id) {
		String sql = "SELECT * FROM upload_pn  where upload_pn_id=?   order by upload_pn_id desc limit 3000";
		List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{id},new upload_pnmapper());
		return upload_pns;
	}

	
	@Override
	public List<showupload_pn> getall2() {
		String sql = "SELECT * FROM upload_pn  LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " WHERE  upload_pn.upload_pn_end = 0  group by upload_pn.upload_pn_note order by upload_pn.upload_pn_id desc limit 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper2());
		return upload_pns;
	}
	
	@Override
	public List<showupload_pn> getallfornewreport() {
		String sql = "SELECT * FROM upload_pn "
				+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " LEFT JOIN conditions ON conditions.condition_id = upload_pn.upload_pn_monitor"
				+ " WHERE  upload_pn.upload_pn_end = 0  and upload_pn.upload_pn_type != 2 and  conditions.condition_child IS NULL "
				+ " OR  upload_pn.upload_pn_end = 0  and upload_pn.upload_pn_type != 2 and  conditions.condition_child = 0    "
				+ "  group by upload_pn.upload_pn_id order by upload_pn.upload_pn_id desc limit 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper2());
		return upload_pns;
	}
	
	
	
	
	@Override
	public List<showupload_pn> getalls2() {
		String sql = "SELECT * FROM upload_pn  LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " WHERE upload_pn.upload_pn_end = 0 and folder.folder_name != ''   GROUP BY folder.folder_id  order by upload_pn.upload_pn_id desc limit 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper2());
		return upload_pns;
	}
	
	@Override
	public List<showupload_pn> getalls3() {
		String sql = "SELECT * FROM upload_pn  LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " WHERE upload_pn.upload_pn_end = 0 and folder.folder_name != ''  GROUP BY upload_pn.upload_pn_name,folder.folder_id  order by upload_pn.upload_pn_id desc limit 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper2());
		return upload_pns;
	}
	@Override
	public List<showupload_pn> getalls4() {
		String sql = "SELECT * FROM upload_pn  LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " WHERE upload_pn.upload_pn_end = 0 and folder.folder_name != ''  GROUP BY upload_pn.upload_pn_app,folder.folder_id "
				+ "  order by upload_pn.upload_pn_id desc limit 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper2());
		return upload_pns;
	}
	@Override
	public List<showupload_pn> getalls5() {
		String sql = "SELECT * FROM upload_pn  LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " WHERE upload_pn.upload_pn_end = 0 and folder.folder_name != ''  GROUP BY upload_pn.upload_pn_respon,upload_pn.upload_pn_app,folder.folder_id  "
				+ "order by upload_pn.upload_pn_id desc limit 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper2());
		return upload_pns;
	}
	
	@Override
	public List<showupload_pn> getallby(String name) {
		String sql = "SELECT * FROM upload_pn  LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " WHERE   FIND_IN_SET(?,upload_pn.upload_pn_respon)  or   upload_pn.upload_pn_app =? "
				+ " order  by upload_pn.upload_pn_id desc limit 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{name,name},new upload_pnmapper2());
		return upload_pns;
	}
	
	
	@Override
	public List<showupload_pn> getallbys(String name) {
		String sql = "SELECT * FROM upload_pn LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
	            + " WHERE  upload_pn.upload_pn_respon = ? "
	            + " OR  upload_pn.upload_pn_app = ? "
	            + " GROUP BY "
	            + "CASE WHEN upload_pn.upload_pn_monitor > 0 THEN upload_pn.upload_pn_monitor ELSE 0 END,upload_pn.upload_pn_monitor "
	            + "ORDER BY upload_pn.upload_pn_id DESC LIMIT 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{name,name},new upload_pnmapper2());
		return upload_pns;
	}
	
	
	
	@Override
	public List<showupload_pn> getalls() {
		String sql = "SELECT GROUP_CONCAT(DISTINCT upload_pn_respon) as upload_pn_respon,"
				+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,"
				+ "COUNT(upload_pn.upload_pn_monitor) as status0,"
				+ "SUM(report_pn_status = 1 or report_pn_status = 0 or report_pn_status = -1) as status1,"
				+ "SUM(report_pn_status = 2) as status2,"
				+ "SUM(report_pn_status = -1) as status3,"
				+ "(SUM(report_pn_status = 1) + SUM(report_pn_status = 2) + SUM(report_pn_status = -1)) as total_status,"
				+ "upload_pn.*,folder.*,report_pn.*,users.* FROM upload_pn   "
				+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " LEFT JOIN  report_pn ON  report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ " LEFT JOIN  users ON users.user_name =   upload_pn.upload_pn_respon "
				+ "WHERE    upload_pn.upload_pn_end = 0 "
	            + " GROUP BY  upload_pn.upload_pn_monitor  "
	            + "ORDER BY total_status DESC LIMIT 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper4());
		return upload_pns;
	}

	
	@Override
	public List<showupload_pn> getalls(String name) {
		String sql = "SELECT GROUP_CONCAT(DISTINCT upload_pn_respon) as upload_pn_respon,"
				+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,"
				+ "COUNT(upload_pn.upload_pn_monitor) as status0,"
				+ "SUM(report_pn_status = 1) as status1,"
				+ "SUM(report_pn_status = 2) as status2,"
				+ "SUM(report_pn_status = -1) as status3,"
				+ "(SUM(report_pn_status = 1) + SUM(report_pn_status = 2) + SUM(report_pn_status = -1)) as total_status,"
				+ "upload_pn.*,folder.*,report_pn.*,users.* FROM upload_pn   "
				+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " LEFT JOIN  report_pn ON  report_pn.report_pn_idrefer = upload_pn.upload_pn_id AND DATE(report_pn.report_pn_date) <= CURDATE()  AND DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ " LEFT JOIN  users ON users.user_name =   upload_pn.upload_pn_respon  "
	            + " WHERE  ( upload_pn.upload_pn_respon =? or upload_pn.upload_pn_app=?)  "
	            + " GROUP BY  upload_pn.upload_pn_monitor  "
	            + "ORDER BY total_status DESC LIMIT 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{name,name},new upload_pnmapper4());
		return upload_pns;
	}

	
	@Override
	public List<showupload_pn> getallsed2() {
		String sql = "SELECT "
				+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,"
				+ "COUNT(DISTINCT report_pn.report_pn_respon, report_pn.report_pn_app) as status0,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_respon  THEN (CASE WHEN report_pn_status = 1 THEN 1 ELSE 0 END) ELSE 0 END) as status1,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_app  THEN (CASE WHEN report_pn_status = 2 THEN 1 ELSE 0 END) ELSE 0 END) as status2,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_respon  THEN (CASE WHEN report_pn_status = -1 THEN 1 ELSE 0 END) ELSE 0 END) as status3,"
				+ "(SUM(report_pn_status = 1) + SUM(report_pn_status = 2) + SUM(report_pn_status = -1)) as total_status,"
				+ "upload_pn.*,folder.*,report_pn.*,users.*  FROM users   "
				+ " LEFT JOIN  upload_pn ON users.user_name =   upload_pn.upload_pn_respon or users.user_name =  upload_pn.upload_pn_app "
				+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " LEFT JOIN  report_pn ON  report_pn.report_pn_idrefer = upload_pn.upload_pn_id AND DATE(report_pn.report_pn_date) <= CURDATE() AND DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
	            + " WHERE   (users.user_name = report_pn.report_pn_respon OR users.user_name = report_pn.report_pn_app)  and  upload_pn.upload_pn_end = 0  "
	            + " GROUP BY  users.user_name "
	            + "ORDER BY total_status DESC LIMIT 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{},new upload_pnmapper4());
		return upload_pns;
	}
	
	@Override
	public List<showupload_pn> getallsed2(String name) {
		String sql = "SELECT "
				+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,"
				+ "COUNT(DISTINCT report_pn.report_pn_respon, report_pn.report_pn_app) as status0,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_respon  THEN (CASE WHEN report_pn_status = 1 THEN 1 ELSE 0 END) ELSE 0 END) as status1,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_app  THEN (CASE WHEN report_pn_status = 2 THEN 1 ELSE 0 END) ELSE 0 END) as status2,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_respon  THEN (CASE WHEN report_pn_status = -1 THEN 1 ELSE 0 END) ELSE 0 END) as status3,"
				+ "(SUM(report_pn_status = 1) + SUM(report_pn_status = 2) + SUM(report_pn_status = -1)) as total_status,"
				+ "upload_pn.*,folder.*,report_pn.*,users.*  FROM users   "
				+ " LEFT JOIN  upload_pn ON users.user_name =   upload_pn.upload_pn_respon or users.user_name =  upload_pn.upload_pn_app "
				+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " LEFT JOIN  report_pn ON  report_pn.report_pn_idrefer = upload_pn.upload_pn_id AND DATE(report_pn.report_pn_date) <= CURDATE() AND DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
	            + " WHERE   upload_pn.upload_pn_respon =? or upload_pn.upload_pn_app=? "
	            + " GROUP BY  upload_pn.upload_pn_id "
	            + "ORDER BY total_status DESC LIMIT 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{name,name},new upload_pnmapper4());
		return upload_pns;
	}
	
	@Override
	public List<showupload_pn> getallsedgroup(String name) {
		String sql = "SELECT "
				+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,"
				+ "COUNT(DISTINCT report_pn.report_pn_respon, report_pn.report_pn_app) as status0,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_respon  THEN (CASE WHEN report_pn_status = 1 THEN 1 ELSE 0 END) ELSE 0 END) as status1,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_app  THEN (CASE WHEN report_pn_status = 2 THEN 1 ELSE 0 END) ELSE 0 END) as status2,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_respon  THEN (CASE WHEN report_pn_status = -1 THEN 1 ELSE 0 END) ELSE 0 END) as status3,"
				+ "(SUM(report_pn_status = 1) + SUM(report_pn_status = 2) + SUM(report_pn_status = -1)) as total_status,"
				+ "upload_pn.*,folder.*,report_pn.*,users.*  FROM users   "
				+ " LEFT JOIN  upload_pn ON users.user_name =   upload_pn.upload_pn_respon or users.user_name =  upload_pn.upload_pn_app "
				+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " LEFT JOIN  report_pn ON  report_pn.report_pn_idrefer = upload_pn.upload_pn_id AND DATE(report_pn.report_pn_date) <= CURDATE()  "
				+ "AND DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
	            + " WHERE   upload_pn.upload_pn_type = 5  and (upload_pn.upload_pn_respon =? or upload_pn.upload_pn_app=?)  "
	            + " GROUP BY  upload_pn.upload_pn_id "
	            + "ORDER BY total_status DESC LIMIT 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{name,name},new upload_pnmapper4());
		return upload_pns;
	}	
	
	@Override
	public List<showupload_pn> getallsedgroup(String name,int due) {
		String sql = "SELECT "
				+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,"
				+ "COUNT(DISTINCT report_pn.report_pn_respon, report_pn.report_pn_app) as status0,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_respon  THEN (CASE WHEN report_pn_status = 1 THEN 1 ELSE 0 END) ELSE 0 END) as status1,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_app  THEN (CASE WHEN report_pn_status = 2 THEN 1 ELSE 0 END) ELSE 0 END) as status2,"
				+ " SUM(CASE WHEN users.user_name = report_pn.report_pn_respon  THEN (CASE WHEN report_pn_status = -1 THEN 1 ELSE 0 END) ELSE 0 END) as status3,"
				+ "(SUM(report_pn_status = 1) + SUM(report_pn_status = 2) + SUM(report_pn_status = -1)) as total_status,"
				+ "upload_pn.*,folder.*,report_pn.*,users.*  FROM users   "
				+ " LEFT JOIN  upload_pn ON users.user_name =   upload_pn.upload_pn_respon or users.user_name =  upload_pn.upload_pn_app "
				+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
				+ " LEFT JOIN  report_pn ON  report_pn.report_pn_idrefer = upload_pn.upload_pn_id AND DATE(report_pn.report_pn_date) <= CURDATE() AND DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
	            + " WHERE upload_pn.upload_pn_type = 0  and  (users.user_name = report_pn.report_pn_respon OR users.user_name = report_pn.report_pn_app) "
	            + "and  ( upload_pn.upload_pn_respon =? or upload_pn.upload_pn_app=?)  and  upload_pn_due =? "
	            + " GROUP BY  users.user_name "
	            + "ORDER BY total_status DESC LIMIT 3000";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{name,name,due},new upload_pnmapper4());
		return upload_pns;
	}	
	
	
	

	
	
	
	
	@Override
	public showupload_pn getallnext(String name,int id) {
		
		  try {
				String sql = "SELECT "
						+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,"
						+ "COUNT(DISTINCT upload_pn.upload_pn_respon, upload_pn.upload_pn_app) as status0,"
						+ " SUM(CASE WHEN users.user_name = upload_pn.upload_pn_respon  THEN (CASE WHEN report_pn_status = 1 THEN 1 ELSE 0 END) ELSE 0 END) as status1,"
						+ " SUM(CASE WHEN users.user_name = upload_pn.upload_pn_app  THEN (CASE WHEN report_pn_status = 2 THEN 1 ELSE 0 END) ELSE 0 END) as status2,"
						+ " SUM(CASE WHEN users.user_name = upload_pn.upload_pn_respon  THEN (CASE WHEN report_pn_status = -1 THEN 1 ELSE 0 END) ELSE 0 END) as status3,"
						+ "upload_pn.*,folder.*,report_pn.*,users.*  FROM users   "
						+ " LEFT JOIN  upload_pn ON users.user_name =   upload_pn.upload_pn_respon or users.user_name =  upload_pn.upload_pn_app "
						+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
						+ " LEFT JOIN  report_pn ON  report_pn.report_pn_idrefer = upload_pn.upload_pn_id AND DATE(report_pn.report_pn_date) <= CURDATE() AND DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
			            + " WHERE  users.user_name = upload_pn.upload_pn_respon  and  (upload_pn.upload_pn_respon = ? or upload_pn.upload_pn_app = ?) and upload_pn.upload_pn_id != ?  "
			            + "and upload_pn.upload_pn_id > ? "
			            + " GROUP BY  users.user_name "
			            + "ORDER BY upload_pn.upload_pn_id DESC LIMIT 1";
				showupload_pn upload_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{name,name,id,id},new upload_pnmapper4());
				return upload_pns;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }	
	}
	
	
	@Override
	public showupload_pn getallback(String name,int id) {	
		  try {
			  
				String sql = "SELECT "
						+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,"
						+ "COUNT(DISTINCT upload_pn.upload_pn_respon, upload_pn.upload_pn_app) as status0,"
						+ " SUM(CASE WHEN users.user_name = upload_pn.upload_pn_respon  THEN (CASE WHEN report_pn_status = 1 THEN 1 ELSE 0 END) ELSE 0 END) as status1,"
						+ " SUM(CASE WHEN users.user_name = upload_pn.upload_pn_app  THEN (CASE WHEN report_pn_status = 2 THEN 1 ELSE 0 END) ELSE 0 END) as status2,"
						+ " SUM(CASE WHEN users.user_name = upload_pn.upload_pn_respon  THEN (CASE WHEN report_pn_status = -1 THEN 1 ELSE 0 END) ELSE 0 END) as status3,"
						+ "upload_pn.*,folder.*,report_pn.*,users.*  FROM users   "
						+ " LEFT JOIN  upload_pn ON users.user_name =   upload_pn.upload_pn_respon or users.user_name =  upload_pn.upload_pn_app "
						+ " LEFT JOIN folder ON folder.folder_id = upload_pn.upload_pn_idrefer"
						+ " LEFT JOIN  report_pn ON  report_pn.report_pn_idrefer = upload_pn.upload_pn_id AND DATE(report_pn.report_pn_date) <= CURDATE() AND DATE(report_pn.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY)  "
			            + " WHERE  users.user_name = upload_pn.upload_pn_respon  and  (upload_pn.upload_pn_respon = ? or upload_pn.upload_pn_app = ?) and upload_pn.upload_pn_id != ?  "
			            + "and upload_pn.upload_pn_id < ? "
			            + " GROUP BY  users.user_name "
			            + "ORDER BY upload_pn.upload_pn_id DESC LIMIT 1";
				showupload_pn upload_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{name,name,id,id},new upload_pnmapper4());
				return upload_pns;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	}
	
	
	
	@Override
	public int setStatus(int id,int status) {
		  String sql=  "UPDATE upload_pn SET upload_pn_status='"+status+"'"
			  		+ " WHERE upload_pn_id="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	@Override
	public int setstate(int state,int id) {
		  String sql=  "UPDATE upload_pn SET upload_pn_end=? "
			  		+ " WHERE upload_pn_id= ? ";
			    return jdbcTemplate.update(sql, new Object[]{state,id}); 
	}	
	
	@Override
	public int setRun(int state,int id) {
		  String sql=  "UPDATE upload_pn SET project=? "
			  		+ " WHERE upload_pn_id= ? ";
			    return jdbcTemplate.update(sql, new Object[]{state,id}); 
	}	
	
	@Override
	public int setActive(int id,String date) {
		  String sql=  "UPDATE upload_pn SET active=? "
			  		+ " WHERE upload_pn_id= ? ";
			    return jdbcTemplate.update(sql, new Object[]{date,id}); 
	}	
	
	
	
	
	@Override
	public int changename(String name) {
		  String sql=  "UPDATE upload_pn SET upload_pn_respon='"+name+"'"
			  		+ " WHERE upload_pn_respon='"+name+"'";
			    return jdbcTemplate.update(sql); 
	}	
	
	@Override
	public int changenameapp(String name) {
		  String sql=  "UPDATE upload_pn SET upload_pn_app='"+name+"'"
			  		+ " WHERE upload_pn_app='"+name+"'";
			    return jdbcTemplate.update(sql); 
	}	
	
	
	@Override
	public int changename(String name,String oldname) {
		  String sql=  "UPDATE upload_pn SET upload_pn_respon='"+name+"'"
			  		+ " WHERE upload_pn_respon='"+oldname+"'";
			    return jdbcTemplate.update(sql); 
	}	
	
	@Override
	public int changenameapp(String name,String oldname) {
		  String sql=  "UPDATE upload_pn SET upload_pn_app='"+name+"'"
			  		+ " WHERE upload_pn_app='"+oldname+"'";
			    return jdbcTemplate.update(sql); 
	}	
	
	
	@Override
	public int setSetting(upload_pn upload_pns) {
		
		Object[] upload_pnsInfo = { 
				upload_pns.getUpload_pn_sum(),
				upload_pns.getUpload_pn_avg(),
				upload_pns.getUpload_pn_hidden(),
				upload_pns.getUpload_pn_private(),
				upload_pns.getUpload_pn_pivot(),
				upload_pns.getUpload_pn_follow(),
				upload_pns.getUpload_pn_report(),
				upload_pns.getUpload_pn_send1(),
				upload_pns.getUpload_pn_allpermissions(),
				upload_pns.getUpload_pn_allowallpermissions(),
				upload_pns.getUpload_pn_appfield(),
				upload_pns.getUpload_pn_activityend(),
				upload_pns.getUpload_pn_id(),
				
				
				};
		  String sql=  "UPDATE upload_pn SET upload_pn_sum= ?,upload_pn_avg= ?,upload_pn_hidden= ?,upload_pn_private= ?"
		  		+ ",upload_pn_pivot= ?,upload_pn_follow= ? ,upload_pn_report= ? ,upload_pn_send1= ?"
		  		+ ",upload_pn_allpermissions= ? ,upload_pn_allowallpermissions= ? ,upload_pn_appfield= ?,upload_pn_activityend=? "
			  		+ " WHERE upload_pn_id=? ";
			    return jdbcTemplate.update(sql,upload_pnsInfo); 
	}	
	
	@Override
	public int setSettingAll(upload_pn upload_pns) {
		
		Object[] upload_pnsInfo = { 
				upload_pns.getUpload_pn_sum(),
				upload_pns.getUpload_pn_avg(),
				upload_pns.getUpload_pn_hidden(),
				upload_pns.getUpload_pn_private(),
				upload_pns.getUpload_pn_pivot(),
				upload_pns.getUpload_pn_follow(),
				upload_pns.getUpload_pn_report(),
				upload_pns.getUpload_pn_send1(),
				upload_pns.getUpload_pn_appfield(),
				upload_pns.getUpload_pn_idrefer(),
				};
		  String sql=  "UPDATE upload_pn SET upload_pn_sum= ?,upload_pn_avg= ?,upload_pn_hidden= ?,upload_pn_private= ?"
		  		+ ",upload_pn_pivot= ?,upload_pn_follow= ? ,upload_pn_report= ? ,upload_pn_send1= ?"
		  		+ ",upload_pn_appfield= ?  "
			  		+ " WHERE upload_pn_idrefer=? AND upload_pn_allowallpermissions = 0 ";
			    return jdbcTemplate.update(sql,upload_pnsInfo); 
	}	
	
	
	
	
	@Override
	public void setCategory(upload_pn upload_pns) {
		
		Object[] upload_pnsInfo = { 
				upload_pns.getUpload_pn_category(),
				upload_pns.getUpload_pn_id(),
				};
		  String sql=  "UPDATE upload_pn SET upload_pn_category = ?  WHERE upload_pn_id=? ";
		  jdbcTemplate.update(sql,upload_pnsInfo); 
	}	
	
	
	@Override
	public int save(upload_pn upload_pns) {

				String sql = "INSERT INTO upload_pn(upload_pn_name,upload_pn_note"
						+ ",upload_pn_due,upload_pn_idadd,upload_pn_idrefer,upload_pn_respon,upload_pn_date"
						+ ",upload_pn_app,upload_pn_department,upload_pn_end,upload_pn_type,upload_pn_datedue,upload_pn_namefile,upload_pn_name2,upload_pn_name3"
						+ ",upload_pn_name4,upload_pn_name5,upload_pn_monitor,upload_pn_setdue"
						+ ",upload_pn_filecheck,upload_pn_storage,upload_pn_category,upload_pn_clearing) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
		
						        //jdbcTemplate.update(sql,memberInfo);
						          KeyHolder keyHolder = new GeneratedKeyHolder();
						
						          jdbcTemplate.update(connection -> {
						         	 
						              PreparedStatement ps = connection
						                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						                ps.setString(1, upload_pns.getUpload_pn_name());
						                ps.setString(2, upload_pns.getUpload_pn_note());
						                ps.setInt(3, upload_pns.getUpload_pn_due());
						                ps.setInt(4, upload_pns.getUpload_pn_idadd());
						                ps.setInt(5, upload_pns.getUpload_pn_idrefer());
						                ps.setString(6, upload_pns.getUpload_pn_respon());
						                ps.setString(7, upload_pns.getUpload_pn_date());
						                ps.setString(8, upload_pns.getUpload_pn_app());
						                ps.setString(9, upload_pns.getUpload_pn_department());
						                ps.setString(10, upload_pns.getUpload_pn_end());
						                ps.setInt(11, upload_pns.getUpload_pn_type());
						                ps.setString(12, upload_pns.getUpload_pn_datedue());
						                ps.setInt(13, upload_pns.getUpload_pn_namefile());
						                ps.setString(14, upload_pns.getUpload_pn_name2());
						                ps.setString(15, upload_pns.getUpload_pn_name3());
						                ps.setString(16, upload_pns.getUpload_pn_name4());
						                ps.setString(17, upload_pns.getUpload_pn_name5());
						                ps.setLong(18, upload_pns.getUpload_pn_monitor());
						                ps.setString(19, upload_pns.getUpload_pn_setdue());
						                ps.setInt(20, upload_pns.getUpload_pn_filecheck());
						                ps.setString(21, upload_pns.getUpload_pn_storage());
						                ps.setString(22, upload_pns.getUpload_pn_category());
						                ps.setString(23, upload_pns.getUpload_pn_clearing());
				
						                return ps;
						             
						          }, keyHolder);			
						          //String key = keyHolder.getKey();
						          System.out.println( keyHolder.getKey().intValue());
						         return  keyHolder.getKey().intValue();
						     // System.out.println( keyHolder.getKey());
				
				

	}

	@Override
	public upload_pn getbyId(int id) {
		
		    
		    try {
		    	 String sql="select * from upload_pn where upload_pn_id=?  ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new upload_pnmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
		    
		    
	}
	
	@Override
	public upload_pn getbyId(int id,int idadd) {
		
		    
		    try {
		    	 String sql="select * from upload_pn where upload_pn_id=? AND upload_pn_idadd=?   ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id,idadd},new upload_pnmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
		    
		    
	}
	
	@Override
	public upload_pn getbyConditionsId(int id) {
		
		    
		    try {
		    	 String sql="select * from upload_pn where upload_pn_monitor=? AND upload_pn_end =0  ORDER BY  upload_pn_id DESC LIMIT 1  ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new upload_pnmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 upload_pn upload_pns = new upload_pn();
			 upload_pns.setUpload_pn_id(0);
			 return upload_pns;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
		    
		    
	}
	

	
	
	
	@Override
	public  List<upload_pn> getallbymonitor( int monitor) {
		
	String sql="select * from upload_pn where upload_pn_monitor=?   and upload_pn_end = 0     ";    
		List<upload_pn> upload_pns  =  jdbcTemplate.query(sql, new Object[]{monitor} , new upload_pnmapper());
		return   upload_pns;
	}
	
	
	@Override
	public  List<upload_pn> getallAccess( int monitor) {
		
	String sql="select * from upload_pn where upload_pn_monitor=? GROUP BY upload_pn_respon,upload_pn_app   and upload_pn_end = 0     ";    
		List<upload_pn> upload_pns  =  jdbcTemplate.query(sql, new Object[]{monitor} , new upload_pnmapper());
		return   upload_pns;
	}
	
	
	
	
	@Override
	public List<showupload_pn> getallbymonitor1(int monitor,jobview jobviews) {
		
		
		String sub  =  " a.upload_pn_monitor  = '"+monitor+"'    and  a.upload_pn_end = 0   ";
		int step = 1;
		
		if(jobviews.getJobview_type2() != null) {
			if(jobviews.getJobview_type2().equals("1")) { step = 1;}
			if(jobviews.getJobview_type2().equals("2")) { step = 1;}
			if(jobviews.getJobview_type2().equals("3")) { step = 1;}
			if(jobviews.getJobview_type2().equals("4")) { step = 1;}
			if(jobviews.getJobview_type2().equals("5")) { step = 1;}
		}
		
		
	   String sql = "SELECT "
			    + "(SELECT COUNT(*) "
			    + "FROM report_pn AS i "
			    + "WHERE a.upload_pn_id = i.report_pn_idrefer AND  ( i.report_pn_status = 1 or i.report_pn_status = 0 or report_pn_status = -1)  ) AS status1, "
			    + "(SELECT COUNT(*) "
			    + "FROM report_pn AS i "
			    + "WHERE a.upload_pn_id = i.report_pn_idrefer AND i.report_pn_status = 2) AS status2, "
			    + "(SELECT COUNT(*) "
			    + "FROM report_pn AS i "
			    + "WHERE a.upload_pn_id = i.report_pn_idrefer AND i.report_pn_status = -1) AS status3, "
			    + "(SELECT COUNT(*) "
			    + "FROM log AS l "
			    + "WHERE a.upload_pn_id = l.log_plan AND l.log_flow = -1   ) AS status4, "
			    + "a.* "
			    + "FROM upload_pn AS a "
			    + "WHERE "+sub+"  "
			    + "ORDER BY a.active, a.upload_pn_id DESC LIMIT 20";
		
		
		System.out.println(" sql "+sql );
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper4s());
		return upload_pns;
	}	
	
	
	
	
	
	
	@Override
	public upload_pn getbymonitor( int monitor) {
		
		    
		    try {
		    	 String sql="select * from upload_pn where upload_pn_monitor=?  and upload_pn_end =0 ORDER BY upload_pn_id DESC  limit 1  ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{monitor},new upload_pnmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	
		    
	}

	
	
	
	@Override
	public upload_pn getbyreal( int monitor,int type,String name) {
		
		    
		    try {
		    	 String sql="select * from upload_pn where upload_pn_monitor=? and upload_pn_type=?  and upload_pn_respon=?  limit 1  ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{monitor,type,name},new upload_pnmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyreal  with the same Id .......");
		   
		 }
	
		    
	}
	@Override
	public upload_pn getbyreal( int monitor,String name,String name2,String name3) {
		
		    
		    try {
		    	 String sql="select * from upload_pn where upload_pn_monitor=? "
		    	 		+ " and ( upload_pn_respon=? or upload_pn_respon=?   or upload_pn_respon=?  )  limit 1  ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{monitor,name,name2,name3},new upload_pnmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyreal2 with the same Id .......");
		   
		 }
	
		    
	}

	@Override
	public upload_pn getbyIdandAll(int monitor,int type) {
		 

			  try {
					 String sql = "SELECT GROUP_CONCAT(DISTINCT upload_pn_respon) as upload_pn_respon,"
								+ "GROUP_CONCAT(DISTINCT upload_pn_app) as upload_pn_app,upload_pn.* FROM upload_pn "
					            + " WHERE  upload_pn_id=?   and upload_pn_type =?  "
					            + " GROUP BY upload_pn_respon,upload_pn_app  LIMIT 1";
					    return jdbcTemplate.queryForObject(sql, new Object[]{monitor,type},new upload_pnmapper()); 
					    
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }


		    
		    
		    
	}
	
	
	
	@Override
	public upload_pn getbyIdrefer(int id) {
		 String sql="select * from upload_pn where upload_pn_idrefer=?  ";    
		    return jdbcTemplate.queryForObject(sql, new Object[]{id},new upload_pnmapper()); 
	}

	@Override
	public int update(upload_pn upload_pns) {
		  String sql=  "UPDATE upload_pn SET upload_pn_name='"+upload_pns.getUpload_pn_name()+"'"
		  		+ ",upload_pn_note='"+upload_pns.getUpload_pn_note()+"'"
		  		+ ",upload_pn_due='"+upload_pns.getUpload_pn_due()+"'"
		  		+ ",upload_pn_respon='"+upload_pns.getUpload_pn_respon()+"'"
		  		+ ",upload_pn_app='"+upload_pns.getUpload_pn_app()+"'"
		  		+ ",upload_pn_date='"+upload_pns.getUpload_pn_date()+"'"
		  		+ ",upload_pn_department='"+upload_pns.getUpload_pn_department()+"'"
		  		+ ",upload_pn_type='"+upload_pns.getUpload_pn_type()+"'"
		  		+ ",upload_pn_datedue='"+upload_pns.getUpload_pn_datedue()+"'"
		  		+ ",upload_pn_namefile='"+upload_pns.getUpload_pn_namefile()+"'"
		  		+ ",upload_pn_name2='"+upload_pns.getUpload_pn_name2()+"'"
		  		+ ",upload_pn_name3='"+upload_pns.getUpload_pn_name3()+"'"
		  		+ ",upload_pn_name4='"+upload_pns.getUpload_pn_name4()+"'"
		  		+ ",upload_pn_name5='"+upload_pns.getUpload_pn_name5()+"'"
		  		+ ",upload_pn_clearing='"+upload_pns.getUpload_pn_clearing()+"'"
		  		+ ",upload_pn_monitor="+upload_pns.getUpload_pn_monitor()+" "
		  		+ ",upload_pn_setdue='"+upload_pns.getUpload_pn_setdue()+"'"
				+ ",upload_pn_filecheck='"+upload_pns.getUpload_pn_filecheck()+"'"
				+ ",upload_pn_activityend='"+upload_pns.getUpload_pn_activityend()+"'"
				+ ",upload_pn_activitystart='"+upload_pns.getUpload_pn_activitystart()+"'"
				+ ",id_policy='"+upload_pns.getId_policy()+"'"
		  		+ " WHERE  upload_pn_id="+upload_pns.getUpload_pn_id();
		    return jdbcTemplate.update(sql); 
	}
	
	
	
	
	
	
	
	

	@Override
	public void setProject(upload_pn upload_pns) {
		  String sql=  "UPDATE upload_pn SET project_info= ?  WHERE  upload_pn_id= ? ";
		     jdbcTemplate.update(sql, new Object[]{upload_pns.getProject_info(),upload_pns.getUpload_pn_id()}); 
	}
	
	
	
	
	

	@Override
	public int delete(int id) {
		 String sql="delete from upload_pn where upload_pn_id = "+id;    
		    return jdbcTemplate.update(sql);
	}
	
	@Override
	public int delete(int id,int idadd) {
		 String sql="delete from upload_pn where  upload_pn_idadd = ? AND  upload_pn_id = "+id;    
		    return jdbcTemplate.update(sql, new Object[]{idadd});
	}
	

	@Override
	public void savebatch(upload_pn upload_pns) {	
		
		
		String sql = "INSERT INTO upload_pn(upload_pn_name,upload_pn_note"
				+ ",upload_pn_due,upload_pn_idadd,upload_pn_idrefer,upload_pn_respon,upload_pn_date"
				+ ",upload_pn_app,upload_pn_department,upload_pn_end,upload_pn_type,upload_pn_datedue,upload_pn_namefile,upload_pn_name2,upload_pn_name3,upload_pn_name4,upload_pn_name5"
				+ ",upload_pn_monitor,upload_pn_setdue,upload_pn_filecheck,upload_pn_clearing) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {

			                ps.setString(1, upload_pns.getUpload_pn_name());
			                ps.setString(2, upload_pns.getUpload_pn_note());
			                ps.setInt(3, upload_pns.getUpload_pn_due());
			                ps.setInt(4, upload_pns.getUpload_pn_idadd());
			                ps.setInt(5, upload_pns.getUpload_pn_idrefer());
			                ps.setString(6, upload_pns.getUpload_pn_respon().split(",")[i]);
			                ps.setString(7, upload_pns.getUpload_pn_date());
			                ps.setString(8, upload_pns.getUpload_pn_app());
			                ps.setString(9, upload_pns.getUpload_pn_department());
			                ps.setString(10, upload_pns.getUpload_pn_end());
			                ps.setInt(11, upload_pns.getUpload_pn_type());
			                ps.setString(12, upload_pns.getUpload_pn_datedue());
			                ps.setInt(13, upload_pns.getUpload_pn_namefile());
			                ps.setString(14, upload_pns.getUpload_pn_name2());
			                ps.setString(15, upload_pns.getUpload_pn_name3());
			                ps.setString(16, upload_pns.getUpload_pn_name4());
			                ps.setString(17, upload_pns.getUpload_pn_name5());
			                ps.setLong(18, upload_pns.getUpload_pn_monitor());
			                ps.setString(19, upload_pns.getUpload_pn_setdue());
			                ps.setInt(20, upload_pns.getUpload_pn_filecheck());
			                ps.setString(21, upload_pns.getUpload_pn_clearing());
			                
	            }

	            @Override
	            public int getBatchSize() {
	                return upload_pns.getUpload_pn_respon().split(",").length;

	            }
	        });

	
	}
	
	@Override
	public void updatebatch(List<upload_pn> upload_pn) {

		String sql = "UPDATE upload_pn SET upload_pn_send =?,upload_pn_unsend=?,upload_pn_ontime=?,upload_pn_late=?     WHERE upload_pn_id =? ";
		
      jdbcTemplate.batchUpdate(sql,upload_pn,1000,
                new ParameterizedPreparedStatementSetter<upload_pn>() {
                    public void setValues(PreparedStatement ps, upload_pn argument) 
						throws SQLException {
                        ps.setInt(1, argument.getUpload_pn_send());
                        ps.setInt(2, argument.getUpload_pn_unsend());
                        ps.setInt(3, argument.getUpload_pn_ontime());
                        ps.setInt(4, argument.getUpload_pn_late());
                        ps.setInt(5, argument.getUpload_pn_id());
                    }
                });
    }
	
	
	@Override
	public void updatebatchKeyactivitys(List<Keyactivity> upload_pn) {

		String sql = "UPDATE keyactivity SET status=?   WHERE id =? ";
		
      jdbcTemplate.batchUpdate(sql,upload_pn,1000,
                new ParameterizedPreparedStatementSetter<Keyactivity>() {
                    public void setValues(PreparedStatement ps, Keyactivity argument) 
						throws SQLException {
                        ps.setInt(1, argument.getStatus());
                        ps.setInt(2, argument.getId());
                    }
                });
    }
	
	
	
	
	
	@Override
	public List<showupload_pn> useruploadjob() {
		String sql = "SELECT count(upload_pn.upload_pn_respon) as upload_pn_note,upload_pn.upload_pn_respon,upload_pn.upload_pn_status"
				+ ",users.user_img as upload_pn_app  "
				+ "FROM upload_pn  "
				+ "LEFT  JOIN users  ON upload_pn.upload_pn_respon = users.user_name    "
				+ "WHERE  upload_pn.upload_pn_end = 0  group by upload_pn.upload_pn_respon  order by count(upload_pn.upload_pn_respon) DESC  limit 5 ";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql , new upload_pnmapper3());
		return upload_pns;
	}
	
	@Override
	public List<showupload_pn> userappjob() {
		String sql = "SELECT count(upload_pn.upload_pn_app) as upload_pn_note,upload_pn.upload_pn_status,upload_pn.upload_pn_app"
				+ ",users.user_img as upload_pn_respon  "
				+ "FROM upload_pn  "
				+ "LEFT  JOIN users  ON upload_pn.upload_pn_app = users.user_name    "
				+ "WHERE  upload_pn.upload_pn_end = 0  group by upload_pn.upload_pn_app  order by count(upload_pn.upload_pn_app) DESC  limit 5 ";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql , new upload_pnmapper3());
		return upload_pns;
	}
	

	@Override
	public List<showupload_pn> getalled(String name,jobview jobviews) {
		
		String sub ="";
		int step =0;
		

		String subs ="";
		 if(jobviews.getJobview_category() != null) {   
			   
			  subs = " AND  find_in_set(a.upload_pn_category,'"+jobviews.getJobview_category()+"')  ";
			
			}
	if(jobviews.getJobview_type() == null) {
			
			sub = sub + " ( a.upload_pn_respon = '"+name+"' or  a.upload_pn_app = '"+name+"' )  and  a.upload_pn_end = 0   "+subs;

		}else {
			
		
		
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " ( a.upload_pn_respon = '"+name+"' or  a.upload_pn_app = '"+name+"' )   and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =0 "+subs;
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  ( a.upload_pn_respon = '"+name+"' or  a.upload_pn_app = '"+name+"' )    and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =1 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   ( a.upload_pn_respon = '"+name+"' or  a.upload_pn_app = '"+name+"' )   and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =2 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  ( a.upload_pn_respon = '"+name+"' or  a.upload_pn_app = '"+name+"' )   and  a.upload_pn_end = 0  and a.upload_pn_type = 1  "+subs;
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   ( a.upload_pn_respon = '"+name+"' or  a.upload_pn_app = '"+name+"' )   and  a.upload_pn_end = 0  and a.upload_pn_type = 2  "+subs;
			step =1;
		}	
		
		}
		
		
        if(jobviews.getJobview_app() != null) {
        	
        	sub = sub + " AND FIND_IN_SET(a.upload_pn_group,'"+jobviews.getJobview_app()+"')  ";
        }
		
		
		String sql = "SELECT  "
				+ " ( SELECT COUNT(*) "
				+ "      FROM report_pn AS i "
				+ "      WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status = 1  AND i.report_pn_date <= NOW() AND DATE(i.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status1"
				+ " , ( SELECT COUNT(*) "
				+ "      FROM report_pn AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status =2  AND o.report_pn_date <= NOW() AND DATE(o.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status2"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS p "
				+ "      WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  AND p.report_pn_date <= NOW()  AND DATE(p.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status3"
				+ ",a.* FROM upload_pn AS a "
				+ " WHERE  "+sub+" and  a.upload_pn_end = 0  AND a.application = 0   "
				+ "    order by  a.active,a.upload_pn_id desc limit 3000 ";
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper4s());
		return upload_pns;
	}	
	
	
	
	@Override
	public List<showupload_pn> getall(String name,jobview jobviews) {
		
		
		String sub ="";
		
		int step =0;
		String subs ="";
		 if(jobviews.getJobview_category() != null) {   
			   
			  subs = " AND  find_in_set(a.upload_pn_category,'"+jobviews.getJobview_category()+"')  ";
			
			}
		 
	   if(jobviews.getJobview_type() == null) {
			
			sub = sub + " a.upload_pn_respon  = '"+name+"'    and  a.upload_pn_end = 0   "+subs;
			step =1;
		}else {
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_respon  = '"+name+"'    and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =0 "+subs;
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0    and a.upload_pn_type = 0 and a.upload_pn_due =1 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0    and a.upload_pn_type = 0 and a.upload_pn_due =2 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0     and a.upload_pn_type = 1  "+subs;
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0     and a.upload_pn_type = 2  "+subs;
			step =1;
		}	
		}
		
		
	   
	   if(jobviews.getJobview_app() != null) {
       	
       	sub = sub + " AND FIND_IN_SET(a.upload_pn_group,'"+jobviews.getJobview_app()+"')  ";
       }
		
		
		
		
		
		
		
	   String sql = "SELECT "
			    + "(SELECT COUNT(*) "
			    + "FROM report_pn AS i "
			    + "WHERE a.upload_pn_id = i.report_pn_idrefer AND i.report_pn_status = 1) AS status1, "
			    + "0 AS status2, "
			    + "(SELECT COUNT(*) "
			    + "FROM report_pn AS i "
			    + "WHERE a.upload_pn_id = i.report_pn_idrefer AND i.report_pn_status = -1) AS status3, "
			    + "(SELECT COUNT(*) "
			    + "FROM log AS l "
			    + "WHERE a.upload_pn_id = l.log_plan AND l.log_flow = -1   ) AS status4, "
			    + "a.* "
			    + "FROM upload_pn AS a "
			    + "WHERE "+sub+"   AND a.application = 0   "
			    + "HAVING (status1 > 0 OR status3 > 0 OR status4 > 0 )  "
			    + "ORDER BY a.active, a.upload_pn_id DESC LIMIT 3000";
		
		
		System.out.println(" sql "+sql );
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper4s());
		return upload_pns;
	}	
	
	
	
	
	@Override
	public List<showupload_pn> getalleds(String name,jobview jobviews) {
		
		
		String sub ="";
		int step =0;
		
		String subs ="";
		 if(jobviews.getJobview_category() != null) {   
			   
			  subs = " AND  find_in_set(a.upload_pn_category,'"+jobviews.getJobview_category()+"')  ";
			
			}
		 
		 
	   if(jobviews.getJobview_type() == null) {
			
			sub = sub + " a.upload_pn_respon  = '"+name+"'    and  a.upload_pn_end = 0   "+subs;
			step =1;
		}else {
		
		

		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_respon  = '"+name+"'    and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =0 "+subs;
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0    and a.upload_pn_type = 0 and a.upload_pn_due =1 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0    and a.upload_pn_type = 0 and a.upload_pn_due =2 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0     and a.upload_pn_type = 1  "+subs;
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0     and a.upload_pn_type = 2  "+subs;
			step =1;
		}	
		}
		
		
	   if(jobviews.getJobview_app() != null) {
       	
       	sub = sub + " AND FIND_IN_SET(a.upload_pn_group,'"+jobviews.getJobview_app()+"')  ";
       }
		
	   
		
	   String sql = "SELECT "
			    + "0 AS status1, "
			    + "0 AS status2, "
			    + " 0 AS status3, "
			    + "(SELECT COUNT(*) "
			    + "FROM log AS l "
			    + "WHERE  a.upload_pn_id = l.log_plan AND l.log_flow = -2  ) AS status4, "
			    + "a.* "
			    + "FROM upload_pn AS a "
			    + "WHERE "+sub+"  AND a.application = 0   "
			    + "HAVING ( status4 > 0 )  "
			    + "ORDER BY a.active, a.upload_pn_id DESC LIMIT 3000";
		
		
		System.out.println(" sql "+sql );
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper4s());
		return upload_pns;
	}	
	
	
	
	
	
	
	@Override
	public List<showupload_pn> getall1s(String name,jobview jobviews) {
		
		
		
		String sub ="";
		int step =0;
		
		String subs ="";
		 if(jobviews.getJobview_category() != null) {   
			  subs = " AND  find_in_set(a.upload_pn_category,'"+jobviews.getJobview_category()+"')  ";
			
			}
		
	if(jobviews.getJobview_type() == null ) {
			
			sub = sub + " (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) "
					+ "and a.upload_pn_type != 2 and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =0 "+subs;
			step =1;
		}else {
		
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' )"
					+ " and a.upload_pn_type != 2 and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =0 "+subs;
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "    (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' )"
					+ " and a.upload_pn_type != 2 and   a.upload_pn_type = 0 and a.upload_pn_due =1 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) "
					+ "and a.upload_pn_type != 2 and    a.upload_pn_type = 0 and a.upload_pn_due =2 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) and a.upload_pn_type != 2 and   "
					+ " a.upload_pn_type = 1  "+subs;
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' )"
					+ " and a.upload_pn_type != 2 and    a.upload_pn_type = 2  "+subs;
			step =1;
		}	
		
		}

		 if(jobviews.getJobview_app() != null) {
	     	
	     	sub = sub + " AND FIND_IN_SET(a.upload_pn_group,'"+jobviews.getJobview_app()+"')  ";
	     }
		
		
		
		String sql = "SELECT  "
				+ " ( SELECT COUNT(*) "
				+ "      FROM report_pn AS i "
				+ "      WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status < 2    "
				+ "    ) AS status1"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status =2   "
				+ "    ) AS status2"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS p "
				+ "      WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  "
				+ "    ) AS status3"
				+ ",a.* FROM upload_pn AS a "
				+ " WHERE   "+sub+"    AND a.application = 0  and  a.upload_pn_end = 0  HAVING (status1 = 0 and status2 = 0 and status3 = 0 )  "
				+ "  order by  a.active,a.upload_pn_id desc limit 3000 ";
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper4s());
		return upload_pns;
	}	
	
	@Override
	public List<showupload_pn> getall2s(String name) {

		
		
			String sub = " a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0  and a.upload_pn_type = 2  and a.application = 0   ";


			
			
			
		String sql = "SELECT  "
				+ " ( SELECT COUNT(*) "
				+ "      FROM report_pn AS i "
				+ "      WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status < 2  AND i.report_pn_date <= NOW() AND DATE(i.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status1"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status =2  AND o.report_pn_date <= NOW() AND DATE(o.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status2"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS p "
				+ "      WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  AND p.report_pn_date <= NOW()  AND DATE(p.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status3"
				+ ",a.* FROM upload_pn AS a "
				+ " WHERE  "+sub+" AND   a.application = 0  "
				+ "   order by  a.active,a.upload_pn_id desc limit 3000 ";
		
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper4s());
		return upload_pns;
	}	
	
	@Override
	public List<showupload_pn> getall2(String name,jobview jobviews) {
		
		
		
		String sub ="";
		int step =0;
	
		String subs ="";
		 if(jobviews.getJobview_category() != null) {   
			   
			  //subs = " AND  find_in_set(a.upload_pn_category,'"+jobviews.getJobview_category()+"')  ";
			
			}
		
		
	if(jobviews.getJobview_type() == null) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_app = '"+name+"'   and a.upload_pn_end = 0  "+subs;
			step =1;
		}else {
		
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_app = '"+name+"'   and a.upload_pn_end = 0 and a.upload_pn_type = 2   and a.upload_pn_type = 0 and a.upload_pn_due =0 "+subs;
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_app  = '"+name+"'  and  a.upload_pn_end  = 0    and a.upload_pn_type = 0 and a.upload_pn_due =1 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_app = '"+name+"'   and a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =2 "+subs;
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_app = '"+name+"'   and a.upload_pn_end = 0    and a.upload_pn_type = 1  "+subs;
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_app = '"+name+"'  and a.upload_pn_end = 0    and a.upload_pn_type = 2  "+subs;
			step =1;
		}	
		
		}
	
	 if(jobviews.getJobview_app() != null) {
     	
     	sub = sub + " AND FIND_IN_SET(a.upload_pn_group,'"+jobviews.getJobview_app()+"')  ";
     }
		

		

		String sql = "SELECT  "
				+ " 0  AS status1"
				+ " , ( SELECT COUNT(*) "
				+ "      FROM edata AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status = 2   "
				+ "    ) AS status2"
				+ " , 0 AS status3"
			    + ",(SELECT COUNT(*) "
			    + "FROM log AS l "
			    + "WHERE a.upload_pn_id = l.log_plan AND l.log_flow = 0  ) AS status4 "
				+ ",a.* FROM upload_pn AS a "
				+ " WHERE "+sub+"  AND   a.application = 0  HAVING status2 > 0  and  a.upload_pn_end = 0  and   a.turn = 1 "
				+ "  order by  a.active,a.upload_pn_id desc limit 3000 ";
		
		
		System.out.println();
		
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper4s());
		return upload_pns;
	}	
	
	
	
	
	
	
	
	@Override
	public int getnum(String name,jobview jobviews) {
		
		
		
		String sub ="";
		int step =0;
	
	if(jobviews.getJobview_type() == null) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0  ";
			step =1;
		}else {
		
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0 and a.upload_pn_type = 2   and a.upload_pn_type = 0 and a.upload_pn_due =0 ";
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0    and a.upload_pn_type = 0 and a.upload_pn_due =1 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =2 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0    and a.upload_pn_type = 1  ";
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon = '"+name+"'  and a.upload_pn_end = 0    and a.upload_pn_type = 2  ";
			step =1;
		}	
		
		}

		String sql = "SELECT   "
				+ " ( SELECT COUNT(*) "
				+ "      FROM report_pn AS i "
				+ "      WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status < 2  AND i.report_pn_date <= NOW() AND DATE(i.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status1"
				+ " , ( SELECT COUNT(*) "
				+ "      FROM report_pn AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status = 2  AND o.report_pn_date <= NOW() AND DATE(o.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status2"
				+ " , ( SELECT COUNT(*) "
				+ "      FROM report_pn AS p "
				+ "      WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  AND p.report_pn_date <= NOW()  AND DATE(p.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status3"
				+ ",COUNT(*) FROM upload_pn AS a "
				+ " WHERE "+sub+"   HAVING status2 > 0    ";
		
		

		
		
		
		
		  try {
			  
				return jdbcTemplate.queryForObject(sql, Integer.class);
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return 0;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getnum with the same Id .......");
		   
		 }
		
		
		
		
		
		
		
	}	
	
	
	
	
	
	
	@Override
	public int getnum2(String name,jobview jobviews) {
		
		
		
		String sub ="";
		int step =0;
	
	if(jobviews.getJobview_type() == null) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0  ";
			step =1;
		}else {
		
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0 and a.upload_pn_type = 2   and a.upload_pn_type = 0 and a.upload_pn_due =0 ";
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon  = '"+name+"'  and  a.upload_pn_end  = 0    and a.upload_pn_type = 0 and a.upload_pn_due =1 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =2 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon = '"+name+"'   and a.upload_pn_end = 0    and a.upload_pn_type = 1  ";
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_respon = '"+name+"'  and a.upload_pn_end = 0    and a.upload_pn_type = 2  ";
			step =1;
		}	
		
		}

		String sql = "SELECT  "
				+ " ( SELECT COUNT(*) "
				+ "      FROM report_pn AS i "
				+ "      WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status < 2  AND i.report_pn_date <= NOW() AND DATE(i.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status1"
				+ " , ( SELECT COUNT(*) "
				+ "      FROM report_pn AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status = 2  AND o.report_pn_date <= NOW() AND DATE(o.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status2"
				+ " , ( SELECT COUNT(*) "
				+ "      FROM report_pn AS p "
				+ "      WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  AND p.report_pn_date <= NOW()  AND DATE(p.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status3"
				+ ",COUNT(*) FROM upload_pn AS a "
				+ " WHERE "+sub+"   HAVING status2 > 0  ";
		
		
		  try {
			  
				return jdbcTemplate.queryForObject(sql, Integer.class);
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return 0;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getnum2 with the same Id .......");
		   
		 }
		
		
		
	}	
	
	
	
	
	
	
	@Override
	public int getnum3(String name,jobview jobviews) {
		
		
		
		String sub ="";
		int step =0;
		
		
		
	if(jobviews.getJobview_type() == null ) {
			
			sub = sub + " (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) and a.upload_pn_type != 2 and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =0 ";
			step =1;
		}else {
		
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) and a.upload_pn_type != 2 and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =0 ";
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "    (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) and a.upload_pn_type != 2 and   a.upload_pn_type = 0 and a.upload_pn_due =1 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) and a.upload_pn_type != 2 and    a.upload_pn_type = 0 and a.upload_pn_due =2 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) and a.upload_pn_type != 2 and    a.upload_pn_type = 1  ";
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   (a.upload_pn_respon = '"+name+"'  or a.upload_pn_app = '"+name+"' ) and a.upload_pn_type != 2 and    a.upload_pn_type = 2  ";
			step =1;
		}	
		
		}
		
		
		String sql = "SELECT  "
				+ " ( SELECT COUNT(*) "
				+ "      FROM report_pn AS i "
				+ "      WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status < 2  AND i.report_pn_date <= NOW() AND DATE(i.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status1"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status =2  AND o.report_pn_date <= NOW() AND DATE(o.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status2"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS p "
				+ "      WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  AND p.report_pn_date <= NOW()  AND DATE(p.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status3"
				+ ",COUNT(*) FROM upload_pn AS a "
				+ " WHERE   "+sub+"  ";
		
		  try {
			  
				return jdbcTemplate.queryForObject(sql, Integer.class);
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return 0;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getnum3 with the same Id .......");
		   
		 }
		
		
		
	}	
	
	
	
	
	@Override
	public int getnum4(String name,jobview jobviews) {
		
		String sub ="";
		int step =0;
		
		
	if(jobviews.getJobview_type() == null) {
			
			sub = sub + " a.upload_pn_respon = '"+name+"'  and  a.upload_pn_end = 0   ";

		}else {
			
		
		
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_respon = '"+name+"'  and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =0 ";
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   a.upload_pn_respon = '"+name+"'  and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =1 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   a.upload_pn_respon = '"+name+"'  and  a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =2 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   a.upload_pn_respon = '"+name+"'  and  a.upload_pn_end = 0  and a.upload_pn_type = 1  ";
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "   a.upload_pn_respon = '"+name+"'  and  a.upload_pn_end = 0  and a.upload_pn_type = 2  ";
			step =1;
		}	
		
		}
		
		
		
		
		String sql = "SELECT  "
				+ " ( SELECT COUNT(*) "
				+ "      FROM report_pn AS i "
				+ "      WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status = 1  AND i.report_pn_date <= NOW() AND DATE(i.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status1"
				+ " , ( SELECT COUNT(*) "
				+ "      FROM report_pn AS o "
				+ "      WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status =2  AND o.report_pn_date <= NOW() AND DATE(o.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status2"
				+ " ,( SELECT COUNT(*) "
				+ "      FROM report_pn AS p "
				+ "      WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  AND p.report_pn_date <= NOW()  AND DATE(p.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) "
				+ "    ) AS status3"
				+ ",COUNT(*) FROM upload_pn AS a "
				+ " WHERE  "+sub+"  ";
		
		  try {
			  
				return jdbcTemplate.queryForObject(sql, Integer.class);
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return 0;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getnum4 with the same Id .......");
		   
		 }
		
		
		
	}	
	
	
	
	
	
	
	
	@Override
	public List<showupload_pn> getall3(String name,jobview jobviews) {
		
		
		
		String sub ="";
		int step =0;
		
		if(jobviews.getJobview_type().contains("1")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			sub = sub + " a.upload_pn_app = '"+name+"'   and a.upload_pn_end = 0 and a.upload_pn_type = 2   and a.upload_pn_type = 0 and a.upload_pn_due =0 ";
			step =1;
		}
		
		if(jobviews.getJobview_type().contains("2")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_app  = '"+name+"'  and  a.upload_pn_end  = 0    and a.upload_pn_type = 0 and a.upload_pn_due =1 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("3")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_app = '"+name+"'   and a.upload_pn_end = 0  and a.upload_pn_type = 0 and a.upload_pn_due =2 ";
			step =1;
		}
		if(jobviews.getJobview_type().contains("4")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_app = '"+name+"'   and a.upload_pn_end = 0    and a.upload_pn_type = 1  ";
			step =1;
		}	
		if(jobviews.getJobview_type().contains("5")) {
			
			if(step > 0) {
				sub = 	sub + " OR ";
			}
			
			sub = sub + "  a.upload_pn_app = '"+name+"'  and a.upload_pn_end = 0    and a.upload_pn_type = 2  ";
			step =1;
		}	
		
		 if(jobviews.getJobview_app() != null) {
	        	
	        	sub = sub + " AND FIND_IN_SET(a.upload_pn_group,'"+jobviews.getJobview_app()+"')  ";
	        }
			
		
		
		
		
		String sql = "SELECT  "
				+ " ( SELECT COUNT(*)  FROM report_pn AS i WHERE a.upload_pn_id = i.report_pn_idrefer and i.report_pn_status < 2  AND i.report_pn_date <= NOW() AND DATE(i.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ) AS status1"
				+ ", ( SELECT COUNT(*) FROM report_pn AS o  WHERE a.upload_pn_id = o.report_pn_idrefer and o.report_pn_status =2  AND  o.report_pn_date <= NOW() AND DATE( o.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ) AS status2"
				+ ", ( SELECT COUNT(*)  FROM report_pn AS p WHERE a.upload_pn_id = p.report_pn_idrefer and p.report_pn_status =-1  AND p.report_pn_date <= NOW()  AND DATE(p.report_pn_date) > DATE_ADD(CURDATE(), INTERVAL -31 DAY) ) AS status3"
				+ ",a.* FROM upload_pn AS a "
				+ " WHERE  "+sub+"  HAVING status2 > 0   "
				+ "  order by  a.upload_pn_id desc limit 3000 ";
		List<showupload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{name},new upload_pnmapper4s());
		return upload_pns;
	}	

	
	@Override
	public  jobview getView( String user_name) {
	   String sql="select * from jobview  where jobview_user_name=?      ";    
		jobview jobviews  =  jdbcTemplate.queryForObject(sql, new Object[]{user_name} , new jobviewmapper());
		return   jobviews;
	}
	
	
	
	@Override
	public void  saveview( jobview jobviews) {
			Object[] jobviewsInfo = { 
					jobviews.getJobview_tab(),
					jobviews.getJobview_user_name(),
					};
			  String sql=  "INSERT INTO jobview (jobview_tab,jobview_user_name) VALUES(?,?) ";
			  jdbcTemplate.update(sql,jobviewsInfo); 
		}
		
	
	@Override
	public void  setview( jobview jobviews) {
			Object[] jobviewsInfo = { 
				
					jobviews.getJobview_tab(),
					jobviews.getJobview_type(),
					jobviews.getJobview_group(),	
					jobviews.getJobview_category(),
					jobviews.getJobview_app(),
					jobviews.getJobview_id(),
					
					};
			  String sql=  "UPDATE jobview SET jobview_tab =?,jobview_type =?,jobview_group =?,jobview_category =?,jobview_app =? WHERE jobview_id =? ";
			  jdbcTemplate.update(sql,jobviewsInfo); 
		}
		

	
	@Override
	public boolean isjobviewExists(String user_name) {
	    String sql = "SELECT count(*) FROM jobview WHERE jobview_user_name = ?";

	    int count = jdbcTemplate.queryForObject(sql, new Object[] { user_name }, Integer.class);

	    return count > 0;
	}

	 @Override
	  public void setGroup(String condition_group,String id1,String id2,String id3,int type) {
					String sql = "UPDATE upload_pn SET upload_pn_group =? and application =?  WHERE (upload_pn_monitor = ? or upload_pn_monitor = ?  or upload_pn_monitor = ?) ";
					jdbcTemplate.update(sql,new Object[]{condition_group,type,id1,id2,id3});
		}
	 
	 
	 @Override
	 public void turn(int turn,int id_refer,String user_name) {
				  String sql=  "UPDATE upload_pn_turn SET turn =? WHERE id_refer =?  AND user_name =? ";
				  jdbcTemplate.update(sql,new Object[]{turn,id_refer,user_name}); 
			}
	 
	 
	 
	 @Override
	  public void setGroup(String condition_group,String id,int type) {
					String sql = "UPDATE upload_pn SET upload_pn_group =? ,upload_pn_type =? WHERE FIND_IN_SET(upload_pn_monitor,?) ";
					jdbcTemplate.update(sql,new Object[]{condition_group,type,id});
		}



	 @Override
	  public List<upload_pn> getGroup() {
					String sql = "SELECT * FROM  upload_pn WHERE  upload_pn_group  IS NOT NULL GROUP BY upload_pn_group LIMIT 10  ";
					List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new upload_pnmapper());
					return upload_pns;
		}
	  
	  
		@Override
		public List<upload_pn> getallbywork(String user) {
			

			String sql = "SELECT datalink.datalink_workflow AS upload_pn_status,upload_pn.* FROM upload_pn "
					+ "	 LEFT JOIN datalink ON upload_pn.upload_pn_monitor = datalink.datalink_idrefer  "
					+ " where  ( upload_pn.upload_pn_app =? OR FIND_IN_SET(?,upload_pn.upload_pn_respon ) ) AND   upload_pn.upload_pn_type != 3  AND   upload_pn.application != 1   "
					+ "  group by  upload_pn.upload_pn_id  order by upload_pn.upload_pn_id desc limit 5000";
			

			List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{user,user},new upload_pnmapper());
			return upload_pns;
		}
		@Override
		public List<upload_pn> getallbywork(String user,int userid) {
			

			String sql = "SELECT datalink.datalink_workflow AS upload_pn_status,upload_pn.* FROM upload_pn "
					+ "	 LEFT JOIN datalink ON upload_pn.upload_pn_monitor = datalink.datalink_idrefer  "
					+ " where  ( upload_pn.upload_pn_app =? OR FIND_IN_SET(?,upload_pn.upload_pn_respon )  OR upload_pn.upload_pn_idadd = ? ) AND   upload_pn.upload_pn_type != 3  AND   upload_pn.application != 1   "
					+ "  group by  upload_pn.upload_pn_id  order by upload_pn.upload_pn_id desc limit 5000";
			

			List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{user,user,userid},new upload_pnmapper());
			return upload_pns;
		}
		
		@Override
		public List<upload_pn> getallbyProject(String accessList,int userid) {
			

			String sql = "SELECT * FROM upload_pn "
					+ " where  ( FIND_IN_SET(id_policy,?)  OR upload_pn_idadd = ? ) AND   upload_pn_type = 3  AND   application != 1   "
					+ "  group by  upload_pn_id  order by upload_pn_id desc limit 5000";
		
			List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{accessList,userid},new upload_pnmapper());
			return upload_pns;
		}
		
		
		
		@Override
		public List<upload_pn> getallbyProject(String user) {
			

			String sql = "SELECT datalink.datalink_workflow AS upload_pn_status,upload_pn.* FROM upload_pn "
					+ "	 LEFT JOIN datalink ON upload_pn.upload_pn_monitor = datalink.datalink_idrefer  "
					+ "	 LEFT JOIN workflow ON workflow.workflow_id  = datalink.datalink_workflow  "
					+ " where  ( upload_pn.upload_pn_app =? OR FIND_IN_SET(?,upload_pn.upload_pn_respon ) ) AND upload_pn.upload_pn_type = 3  AND upload_pn.upload_pn_end = 0  "
					+ "  group by  upload_pn.upload_pn_id  order by upload_pn.upload_pn_id desc limit 50";
			

			List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{user,user},new upload_pnmapper());
			return upload_pns;
		}
		
		@Override
		public List<upload_pn> getallbyProjects(String user) {
			

			String sql = "SELECT datalink.datalink_workflow AS upload_pn_status,upload_pn.* FROM upload_pn "
					+ "	 LEFT JOIN datalink ON upload_pn.upload_pn_monitor = datalink.datalink_idrefer  "
					+ "	 LEFT JOIN workflow ON workflow.workflow_id  = datalink.datalink_workflow  "
					+ " where  ( upload_pn.upload_pn_app =? OR FIND_IN_SET(?,upload_pn.upload_pn_respon ) ) AND upload_pn.upload_pn_type = 3   "
					+ "  group by  upload_pn.upload_pn_id  order by upload_pn.upload_pn_id desc limit 50";
			

			List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{user,user},new upload_pnmapper());
			return upload_pns;
		}
		
		
		
		@Override
		public List<upload_pn> getByDue_Type(int due,int type) {
			String sql = "SELECT * FROM upload_pn  where upload_pn_due=? and upload_pn_type=?  ";
			List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{due,type},new upload_pnmapper());
			return upload_pns;
		}
		
		@Override
		public List<upload_pn> getByDue_Type(int due,int type,user users) {
			String sql = "SELECT * FROM upload_pn  where upload_pn_due=? and upload_pn_type=?  and   ( upload_pn.upload_pn_app =? OR FIND_IN_SET(?,upload_pn.upload_pn_respon ) )  ";
			List<upload_pn> upload_pns  =  jdbcTemplate.query(sql,new Object[]{due,type,users.getUser_name(),users.getUser_name()},new upload_pnmapper());
			return upload_pns;
		}
		

		 @Override
		  public upload_pn StatusCount(int type,int due) {
						String sql = "SELECT "
								+ "SUM(upload_pn_send) AS upload_pn_send"
								+ ",SUM(upload_pn_unsend) AS upload_pn_unsend"
								+ ",SUM(upload_pn_late) AS upload_pn_late"
								+ ",SUM(upload_pn_ontime) AS upload_pn_ontime"
								
								+ " FROM  upload_pn WHERE  upload_pn_type =? AND  upload_pn_due=?  AND upload_pn_end = 0   ";
						upload_pn upload_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{type,due},new upload_pnduemapper());
						return upload_pns;
			}
		  
		 
		 @Override
		  public upload_pn StatusCount(int type,int due,user users) {
						String sql = "SELECT "
								+ "SUM(upload_pn_send) AS upload_pn_send"
								+ ",SUM(upload_pn_unsend) AS upload_pn_unsend"
								+ ",SUM(upload_pn_late) AS upload_pn_late"
								+ ",SUM(upload_pn_ontime) AS upload_pn_ontime"
								
								+ " FROM  upload_pn WHERE  upload_pn_type =?  AND  upload_pn_due=?   and   ( upload_pn_app =? OR FIND_IN_SET(?,upload_pn_respon ) )  AND upload_pn_end = 0   ";
						upload_pn upload_pns  =  jdbcTemplate.queryForObject(sql,new Object[]{type,due,users.getUser_name(),users.getUser_name()},new upload_pnduemapper());
						return upload_pns;
			}
		 
		  
		@Override
		public void setPop(int id) {
				  String sql=  "UPDATE upload_pn SET upload_pn_type= 2,application= 1  WHERE upload_pn_id=? ";
				  jdbcTemplate.update(sql,new Object[]{id}); 
		}	
		
		
		@Override
		public void expired(String date) {
			
			String sql = "SELECT upload_pn   WHERE  upload_pn_activityend <=  ? AND upload_pn_end = 0   AND upload_pn_activityend  IS NOT NULL ";
			List<upload_pn>  upload_pn = jdbcTemplate.query(sql,new Object[]{date},new upload_pnduemapper());
		
			 List<Integer> annual_type =upload_pn.stream().map(row->row.getUpload_pn_id()).collect(Collectors.toList());
			 annual_type.forEach(id-> {
				 
					String sql2 = "UPDATE goal SET  status = 0   WHERE  processes_id =  ? AND tables= 'upload_pn' ";
					jdbcTemplate.update(sql2,new Object[]{id});
			 });
			
			
			String sql3 = "UPDATE upload_pn SET  upload_pn_end = 0   WHERE  upload_pn_activityend <=  ? AND upload_pn_end = 0   AND upload_pn_activityend  IS NOT NULL ";
			jdbcTemplate.update(sql3,new Object[]{date});
			
		}
		
		
		@Override
		public void start(String date) {
			String sql = "UPDATE upload_pn SET  upload_pn_end = 1   WHERE  upload_pn_activitystart =  ? AND upload_pn_end = 0   AND upload_pn_activitystart  IS NOT NULL ";
			jdbcTemplate.update(sql,new Object[]{date});
		
		}
		
		@Override
		public void updateProject(upload_pn upload_pns) {
			
			
			Object[] ObjectInfo = { 
					upload_pns.getUpload_pn_note(),
					upload_pns.getProject_start(),
					upload_pns.getProject_end(),
					upload_pns.getProject_references(),
					upload_pns.getProject_scope(),
					upload_pns.getProject_objectives(),
					upload_pns.getId_policy(),
					upload_pns.getUpload_pn_id(),
				
					};
			
			
			
			
			String sql = "UPDATE upload_pn SET  upload_pn_note =? ,project_start = ?,project_end = ?,project_references = ?,project_scope = ?,project_objectives = ?,id_policy = ?  "
					+ "WHERE  upload_pn_id =  ? ";
			jdbcTemplate.update(sql,ObjectInfo);
		
		}  
		
		 @Override
		  public UploadpnTurn findUploadpnTurn(int id_refer,String user_name) {
					  try {
							String sql = "SELECT * FROM upload_pn_turn  WHERE  id_refer =?   and user_name =? limit 1  ";
							return jdbcTemplate.queryForObject(sql, new Object[]{id_refer,user_name},new UploadpnTurnmapper()); 
							    
					 }catch(EmptyResultDataAccessException e) {
						 
						 String sqls = "INSERT INTO   upload_pn_turn (id_refer,user_name,turn) values(?,?,?)  ";
						 jdbcTemplate.update(sqls,new Object[]{id_refer,user_name,0});
						 
						 String sql = "SELECT * FROM upload_pn_turn  WHERE  id_refer =?   and user_name =? limit 1  ";
						 return jdbcTemplate.queryForObject(sql, new Object[]{id_refer,user_name},new UploadpnTurnmapper()); 
					    
					 }catch(IncorrectResultSizeDataAccessException e) {
					       
						 throw new RuntimeException("More than one users with the same Id .......");
					   
					 }
			}
		 
		 
			@Override
			public List<Outcome> getAllOutcome(int id) {
				String sql = "SELECT * FROM outcome where project_id = ?  ";
				List<Outcome> list  =  jdbcTemplate.query(sql,new Object[]{id},new Outcomemapper());
				return list;
			}
			
			@Override
			public void saveOutcome(Outcome Outcomes) {
				
				
				Object[] ObjectInfo = { 
						Outcomes.getProject_id(),
						Outcomes.getExpected_results(),
						Outcomes.getSuccess_indicators(),
						Outcomes.getMetric(),
	
						};
				
				
				String sql = "INSERT INTO outcome (project_id,expected_results,success_indicators,metric) VALUES(?,?,?,?)  ";
				jdbcTemplate.update(sql,ObjectInfo);
			
			}  
			@Override
			public void deleteOutcome(Outcome outcome) {
				String sql = "DELETE FROM outcome WHERE id = ?";
				jdbcTemplate.update(sql,new Object[]{outcome.getId()});			
			}
			
			@Override
			public void deleteRisk(Risk risk) {
				String sql = "DELETE FROM risk WHERE id = ?";
				jdbcTemplate.update(sql,new Object[]{risk.getId()});			
			}
			
			
			
			@Override
			public List<Risk> getAllRisk(int id) {
				String sql = "SELECT * FROM risk where project_id = ?  ";
				List<Risk> list  =  jdbcTemplate.query(sql,new Object[]{id},new Riskmapper());
				return list;
			}
			
			@Override
			public void saveRisk(Risk risk) {
				
				
				Object[] ObjectInfo = { 
						risk.getProject_id(),
						risk.getDescription(),
						risk.getCategory(),
						risk.getMitigation(),
						1
						};
				
				
				String sql = "INSERT INTO risk (project_id,description,category,mitigation,status) VALUES(?,?,?,?,?)  ";
				jdbcTemplate.update(sql,ObjectInfo);
			
			}  
			
			
		
			
			@Override
			public void deleteBudget(MasterBudget Budget) {
				String sql = "DELETE FROM master_budget WHERE id = ?";
				jdbcTemplate.update(sql,new Object[]{Budget.getId()});			
			}
			
			
			@Override
			public MasterBudget getMasterBudget(int id) {
				String sql = "SELECT * FROM master_budget where id = ?  ";
				MasterBudget data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new Budgetmapper());
				return data;
			}
			
			@Override
			public List<MasterBudget> getAllBudget(int id) {
				String sql = "SELECT * FROM master_budget where project_id = ?  ";
				List<MasterBudget> list  =  jdbcTemplate.query(sql,new Object[]{id},new Budgetmapper());
				return list;
			}
			
		
			
			
			@Override
			public void saveBudget(MasterBudget Budget) {
				
				
				Object[] ObjectInfo = { 
						Budget.getProject_id(),
						Budget.getDetail(),
						Budget.getCategory(),
						Budget.getBudget(),
						0.0,
						0.0
						};
				
				
				String sql = "INSERT INTO master_budget (project_id,detail,category,budget,actual,variance) VALUES(?,?,?,?,?,?)  ";
				jdbcTemplate.update(sql,ObjectInfo);
			
			}  
			
			
			@Override
			public void deleteMilestone(Milestone milestone) {
				String sql = "DELETE FROM milestone WHERE id = ?";
				jdbcTemplate.update(sql,new Object[]{milestone.getId()});			
			}
			
			@Override
			public List<Milestone> getAllMilestone(int id) {
				String sql = "SELECT * FROM milestone where project_id = ?  ";
				List<Milestone> list  =  jdbcTemplate.query(sql,new Object[]{id},new Milestonemapper());
				return list;
			}
			
			@Override
			public Milestone getMilestone(int id) {
				String sql = "SELECT * FROM milestone where id = ?  ";
				Milestone data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new Milestonemapper());
				return data;
			}
			
			@Override
			public Risk getRisk(int id) {
				String sql = "SELECT * FROM risk where id = ?  ";
				Risk data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new Riskmapper());
				return data;
			}
			
			
			
			@Override
			public void saveMilestone(Milestone milestone) {
				
				
				if(milestone.getId() > 0) {
					
					Object[] ObjectInfo = { 
							milestone.getDetail(),
							milestone.getDate_due(),
							milestone.getDate_alert(),
							milestone.getBilling_amount(),
							milestone.getUser_work_id(),
							milestone.getUser_work_name(),
							milestone.getStatus(),
							milestone.getProcessing_date(),
							milestone.getCollected_payment(),
							milestone.getId()
							};
					
					
					String sql = "UPDATE  milestone SET detail = ?,date_due= ?,date_alert= ?,billing_amount= ?,user_work_id= ?,user_work_name= ?,status= ?,collected_payment= ?,collected_payment= ? "
							+ " WHERE id =? ";
					jdbcTemplate.update(sql,ObjectInfo);
					
				}else {
				
						Object[] ObjectInfo = { 
								milestone.getProject_id(),
								milestone.getDetail(),
								milestone.getDate_due(),
								milestone.getDate_alert(),
								milestone.getBilling_amount(),
								milestone.getUser_work_id(),
								milestone.getUser_work_name(),
								milestone.getStatus(),
								milestone.getProcessing_date(),
								milestone.getCollected_payment(),
								};
						
						
						String sql = "INSERT INTO milestone (project_id,detail,date_due,date_alert,billing_amount,user_work_id,user_work_name,status,processing_date,collected_payment) "
								+ "VALUES(?,?,?,?,?,?,?,?,?,?)  ";
						jdbcTemplate.update(sql,ObjectInfo);
				}
			
			}  
			
			@Override
			public Keyactivity getKeyactivity(int id) {
				String sql = "SELECT * FROM keyactivity where id = ?  ";
				Keyactivity data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new Keyactivitymapper());
				return data;
			}
			@Override
			public List<Keyactivity> getAllKeyactivity(int id) {
				String sql = "SELECT * FROM keyactivity where project_id = ?  ";
				List<Keyactivity> list  =  jdbcTemplate.query(sql,new Object[]{id},new Keyactivitymapper());
				return list;
			}

		
			@Override
			public List<Keyactivity> getAllKeyactivity() {
				String sql = "SELECT * FROM keyactivity LEFT JOIN upload_pn "
						+ " ON  keyactivity.project_id = upload_pn.upload_pn_id "
						+ " WHERE upload_pn.project != 2 AND keyactivity.datestart <= NOW()  ";
				List<Keyactivity> list  =  jdbcTemplate.query(sql,new Keyactivitymapper());
				return list;
			}

			
			
			@Override
			public void updateBatchStatus( List<Keyactivity> list) {

				String sql = "UPDATE keyactivity SET process=?   WHERE id =? ";
				
		      jdbcTemplate.batchUpdate(sql,list,1000,
		                new ParameterizedPreparedStatementSetter<Keyactivity>() {
		                    public void setValues(PreparedStatement ps, Keyactivity argument) 
								throws SQLException {
		                        ps.setInt(1, argument.getProcess());
		                        ps.setInt(2, argument.getId());
		                    }
		                });
		    }
			
	
			@Override
			public void saveKeyactivity(Keyactivity keyactivity) {
				
				
				Object[] ObjectInfo = { 
						keyactivity.getProject_id(),
						keyactivity.getExpected_results(),
						keyactivity.getSuccess_indicators(),
						keyactivity.getDatestart(),
						keyactivity.getDateend(),
						};
				
				
				String sql = "INSERT INTO keyactivity (project_id,expected_results,success_indicators,datestart,dateend) VALUES(?,?,?,?,?)  ";
				jdbcTemplate.update(sql,ObjectInfo);
			
			}  
			@Override
			public void deleteKeyactivity(Keyactivity keyactivity) {
				String sql = "DELETE FROM keyactivity WHERE id = ?";
				jdbcTemplate.update(sql,new Object[]{keyactivity.getId()});			
			}
			
			

			@Override
			public void setTaskview(Taskview taskview,int view) {
				
				 String title = "work.alltasks";
				 if(view == 4) {  title = "work.alltasks"; }
				 else if(view == 3) {  title = "work.byoutcome";  }
				 else  if(view == 2) { title = "work.todo";  }
				 else{   title = "work.mytasks";}
							
				
				String sql = "UPDATE taskview set views = ?,title= ? WHERE id = ?  ";
				jdbcTemplate.update(sql,new Object[]{view,title,taskview.getId()});			
			}
			

			@Override
			public int saveTaskview(int upload_id,int user_id,int view) {
				
				
				 String title = "work.alltasks";
				 if(view == 4) {  title = "work.alltasks"; }
				 else if(view == 3) {  title = "work.byoutcome";  }
				 else  if(view == 2) { title = "work.todo";  }
				 else{   title = "work.mytasks";}

				 String titles = title;
				 
				 
				 
						String sql = "INSERT INTO taskview(title,views,project_id,user_id) "
								+ "VALUES(?,?,?,?)";
						
				
								        //jdbcTemplate.update(sql,memberInfo);
								          KeyHolder keyHolder = new GeneratedKeyHolder();
								
								          jdbcTemplate.update(connection -> {
								         	 
								              PreparedStatement ps = connection
								                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
								                ps.setString(1,titles);
								                ps.setInt(2, view);
								                ps.setInt(3, upload_id);
								                ps.setInt(4, user_id);
								            
								                return ps;
								             
								          }, keyHolder);			
								          //String key = keyHolder.getKey();
								          System.out.println( keyHolder.getKey().intValue());
								         return  keyHolder.getKey().intValue();
								     // System.out.println( keyHolder.getKey());
						
						

			}
			
			 @Override
			  public Taskview getTaskview(int upload_id,int user_id) {
						  try {
								String sql = "SELECT * FROM taskview  WHERE  project_id =?   and user_id =? limit 1  ";
								return jdbcTemplate.queryForObject(sql, new Object[]{upload_id,user_id},new Taskviewmapper()); 
								    
						 }catch(EmptyResultDataAccessException e) {
							 
							 return null;
						    
						 }catch(IncorrectResultSizeDataAccessException e) {
						       
							 throw new RuntimeException("More than one users with the same Id .......");
						   
						 }
				}
			 
			 
			 
				@Override
				public void saveResource(Resource resource) {
					
					
					Object[] ObjectInfo = { 
							resource.getName(),
							resource.getDescription(),
							resource.getResourceType(),
							resource.getCategory(),
							resource.getUnit(),
							resource.getEstimatedCost(),
							resource.getTaskId(),
							};
					
					
					String sql = "INSERT INTO resources (name,description,resource_type,category,unit,estimated_cost,task_id) "
							+ "VALUES(?,?,?,?,?,?,?)  ";
					jdbcTemplate.update(sql,ObjectInfo);
				
				}  

				
				@Override
				public List<Resource> getAllResource(int taskId) {
					String sql = "SELECT * FROM resources where task_id =?  ";
					List<Resource> list  =  jdbcTemplate.query(sql,new Object[]{taskId},new Resourcemapper());
					return list;
				}
				
				@Override
				public List<Resource> getAllResourceBytypeAndTaskId(String type,int taskId) {
					String sql = "SELECT * FROM resources where resource_type = ? AND task_id =?  ";
					List<Resource> list  =  jdbcTemplate.query(sql,new Object[]{type,taskId},new Resourcemapper());
					return list;
				}

				@Override
				public void deleteResource(Resource resource) {
					String sql = "DELETE FROM resources WHERE id = ?";
					jdbcTemplate.update(sql,new Object[]{resource.getId()});			
				}
				
				
				@Override
				public Resource getResourceById(int id) {
					String sql = "SELECT * FROM resources where id =?  ";
					Resource data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new Resourcemapper());
					return data;
				}
				

				
				
				@Override
				public void saveUse_resources(Use_resources use_resources){
					
						if(use_resources.getId() > 0) {
							String sql = "Update use_resources set actual =? ,resources_id=?,task_id=?"
									+ ",planned_qty=?,actual_cost=?,estimated_cost=? WHERE id =?  ";
							jdbcTemplate.update(sql,new Object[]{use_resources.getActual(),use_resources.getResources_id(),use_resources.getTask_id(),use_resources.getPlannedQty()
									,use_resources.getActualCost(),use_resources.getEstimatedCost(),use_resources.getId()});	
						}else {
							String sql = "INSERT INTO use_resources (actual,resources_id,task_id,planned_qty,actual_cost,estimated_cost) "
									+ "VALUES(?,?,?,?,?,?)  ";
							jdbcTemplate.update(sql,new Object[]{use_resources.getActual(),use_resources.getResources_id(),use_resources.getTask_id()
									,use_resources.getPlannedQty(),use_resources.getActualCost(),use_resources.getEstimatedCost()});	
						}	

				}  

				
				
				
				@Override
				public void setUse_resources(int actual,int id){
					String sql = "UPDATE use_resources  set actual=?  WHERE id = ? ";
					jdbcTemplate.update(sql,new Object[]{actual,id});
				}  
				
				@Override
				public Resource getUse_resources(int id) {
					String sql = "SELECT * FROM use_resources "
							+ "LEFT JOIN resources ON  resources.id = use_resources.resources_id"
							+ "  where use_resources.id =?  ";
					Resource data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new Resourcemapper());
					return data;
				}

				
				
				@Override
				public void deleteUse_resources(Use_resources resource) {
					String sql = "DELETE FROM use_resources   WHERE id = ?";
					jdbcTemplate.update(sql,new Object[]{resource.getId()});			
				}
				
				@Override
				public List<Resource> getAllResourceUse(int taskId) {
					String sql = "SELECT * FROM use_resources LEFT JOIN resources ON  resources.id = use_resources.resources_id WHERE use_resources.task_id =?  ";
					List<Resource> list  =  jdbcTemplate.query(sql,new Object[]{taskId},new Resourcemapper());
					return list;
				}
				
				@Override
				public Use_resources getResourceUse(int id) {
					String sql = "SELECT * FROM use_resources LEFT JOIN resources ON  resources.id = use_resources.resources_id WHERE use_resources.id =?  ";
					Use_resources data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new Use_resourcesmapper());
					return data;
				}
				
				
				@Override
				public void deleteCalendar(int id) {
					String sql = "DELETE FROM calendar   WHERE id = ?";
					jdbcTemplate.update(sql,new Object[]{id});			
				}
				
				
				@Override
				public List<Calendar> getAllCalendarBynameAndIndexid(String  name,int index_id) {
					String sql = "SELECT * FROM calendar WHERE by_name =? and index_id= ? ORDER BY  id DESC limit 500  ";
					List<Calendar> list  =  jdbcTemplate.query(sql,new Object[]{name,index_id},new Calendarmapper());
					return list;
				}
				
				@Override
				public List<Calendar> getAllCalendarByname(String  name ) {
					String sql = "SELECT * FROM calendar WHERE by_name =?  and  index_id = 0 ORDER BY  id DESC limit 500  ";
					List<Calendar> list  =  jdbcTemplate.query(sql,new Object[]{name},new Calendarmapper());
					return list;
				}
				
				@Override
				public List<Calendar> getAllCalendar() {
					String sql = "SELECT * FROM calendar ORDER BY  id DESC limit 500   ";
					List<Calendar> list  =  jdbcTemplate.query(sql,new Calendarmapper());
					return list;
				}
				
				
				@Override
				public int countCalendar(String name, int alert, int status) {
				    String sql = "SELECT COUNT(*) FROM calendar WHERE FIND_IN_SET(index_id,?) AND alert = ? AND status = ?";
				    return jdbcTemplate.queryForObject(sql, new Object[]{name, alert, status}, Integer.class);
				}
				
				@Override
				public int countCalendar(String name) {
				    String sql = "SELECT COUNT(*) FROM calendar WHERE FIND_IN_SET(index_id,?) ";
				    return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
				}
				
				@Override
				public int countCalendar(String name, int status) {
				    String sql = "SELECT COUNT(*) FROM calendar WHERE FIND_IN_SET(index_id,?)  AND status = ? ";
				    return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
				}
				
				@Override
				public Calendar CalendarLatest(int  eform_id, int status) {

							 try {
								 
								   if(status == 1) {
									    String sql = "SELECT * from  calendar WHERE eform_id =? AND status = ?  ORDER BY year DESC, month DESC, day DESC  limit 1 ";
										Calendar list  =  jdbcTemplate.queryForObject(sql, new Object[]{eform_id,status},new Calendarmapper());	   
										return list;
								   }else {
									   String sql = "SELECT * FROM calendar WHERE eform_id = ? ORDER BY year DESC, month DESC, day DESC LIMIT 1";
										Calendar list  =  jdbcTemplate.queryForObject(sql, new Object[]{eform_id},new Calendarmapper());
										return list;
								   }		
				
							 }catch(EmptyResultDataAccessException e) {
								 
								 return null;
								 //throw new RuntimeException("file ID does not exist");
							    
							 }catch(IncorrectResultSizeDataAccessException e) {
							       
								 throw new RuntimeException("More than one users with the same Id .......");
							   
							 }	
				}
				
				@Override
				public Calendar getCalendar(int  ids) {

							 try {
								    String sql = "SELECT * from  calendar WHERE id = ?  limit  1";
									Calendar list  =  jdbcTemplate.queryForObject(sql, new Object[]{ids},new Calendarmapper());
									return list;
							 }catch(EmptyResultDataAccessException e) {
								 
								 return null;
								 //throw new RuntimeException("file ID does not exist");
							    
							 }catch(IncorrectResultSizeDataAccessException e) {
							       
								 throw new RuntimeException("More than one users with the same Id .......");
							   
							 }	
				}
				
				
				
				@Override
				public List<Calendar> getAllCalendarDue() {
					// 1. หาค่า ปี, เดือน, วัน ของ "วันนี้"
				    LocalDate today = LocalDate.now();
				    int currentYear = today.getYear();
				    int currentMonth = today.getMonthValue(); // ได้ค่า 1-12
				    int currentDay = today.getDayOfMonth();   // ได้ค่า 1-31

				    // 2. ปรับ SQL ให้กรอง (Filter) ด้วย year, month, day เพิ่มเติม
				    // (เอา limit 500 ออกก็ได้ครับ เพราะกิจกรรมใน 1 วันของ 1 คนไม่น่าจะถึง 500 หรือถ้าอยากเผื่อไว้ก็ใส่ได้)
				    String sql = "SELECT * FROM calendar WHERE  year = ? AND month = ? AND day = ? ORDER BY id DESC";
				    
				    // 3. ส่ง Parameter เข้าไปตามลำดับ (?, ?, ?, ?)
				    List<Calendar> list = jdbcTemplate.query(
				        sql, 
				        new Object[]{currentYear, currentMonth, currentDay}, 
				        new Calendarmapper()
				    );
				    
				    return list;
				}

				@Override
				public List<Calendar> getAllCalendarBynames(String  name) {
					String sql = "SELECT * FROM calendar WHERE by_name =?  ORDER BY  id DESC limit 500  ";
					List<Calendar> list  =  jdbcTemplate.query(sql,new Object[]{name},new Calendarmapper());
					return list;
				}
				
				
				@Override
				public void updateCalendarStatus(int id) {
				    // 1 - 1 = 0
				    // 1 - 0 = 1
				    String sql = "UPDATE calendar SET status = 1 - status WHERE id = ?";
				    
				    jdbcTemplate.update(sql, id);
				}
				
				@Override
				public List<Calendar> getAllCalendarByIndexId(int id) {
					String sql = "SELECT * FROM calendar WHERE index_id =?  ";
					List<Calendar> list  =  jdbcTemplate.query(sql,new Object[]{id},new Calendarmapper());
					return list;
				}
				

				@Override
				public List<Calendar> getAllCalendarIndex() {
					String sql = "SELECT * FROM calendar WHERE index_id  > 0  ";
					List<Calendar> list  =  jdbcTemplate.query(sql,new Calendarmapper());
					return list;
				}
				
				
			
}
