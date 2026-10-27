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

import com.smepro.models.actionplan;
import com.smepro.models.backup;
import com.smepro.rowmapper.actionplanmapper;
import com.smepro.rowmapper.actionplanmapper2;
import com.smepro.rowmapper.attendancemapper;
import com.smepro.rowmapper.backupmapper;


public class actionplanDAOlmpl implements actionplanDAO {

	private JdbcTemplate jdbcTemplate;
	
	public actionplanDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<actionplan> getall(int idrefer) {
		String sql = "SELECT * FROM actionplan LEFT JOIN file "
				+ " ON  file.file_actionplan = actionplan.actionplan_id  where actionplan_idrfer = ? and  actionplan.actionplan_type = 0 ";
		List<actionplan> actionplans  =  jdbcTemplate.query(sql,new Object[]{idrefer},new actionplanmapper());
		return actionplans;
	}
	
	
	@Override
	public List<actionplan> getAll(int idrefer) {
		String sql = "SELECT * FROM actionplan   where actionplan_idrfer = ? and  actionplan_type = 0 ";
		List<actionplan> actionplans  =  jdbcTemplate.query(sql,new Object[]{idrefer},new actionplanmapper2());
		return actionplans;
	}
	
	@Override
	public actionplan getByIdreferAndUser_id(int idrefer,int id_add)
	{

		
		  try {
				String sql = "SELECT * FROM actionplan   where actionplan_idrfer = ? AND actionplan_user_id = ?  limit 1";
				actionplan actionplans  =  jdbcTemplate.queryForObject(sql,new Object[]{idrefer,id_add},new actionplanmapper2());
				return actionplans;
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }


		
	}
	
	
	@Override
	public actionplan getByid(int id) {
		String sql = "SELECT * FROM actionplan  where actionplan_id = ? ";
		actionplan actionplans  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new actionplanmapper2());
		return actionplans;
	}

	
	
	@Override
	public actionplan getByids(int id) {
		String sql = "SELECT * FROM actionplan LEFT JOIN file  ON  file.file_subactionplan = actionplan.actionplan_id"
				+ "  where actionplan.actionplan_id = ?  and  actionplan.actionplan_type = 1  limit 1";
		actionplan actionplans  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new actionplanmapper());
		return actionplans;
	}

	
	@Override
	public List<actionplan> getalls(int idrefer) {
		String sql = "SELECT * FROM actionplan LEFT JOIN file "
				+ " ON  file.file_subactionplan = actionplan.actionplan_id  where actionplan_idrfer = ? and  actionplan.actionplan_type = 1 ";
		List<actionplan> actionplans  =  jdbcTemplate.query(sql,new Object[]{idrefer},new actionplanmapper());
		return actionplans;
	}
	

	@Override
	public int delete(int id) {
		 String sql="delete from actionplan where  actionplan_id = "+id;    
		    return jdbcTemplate.update(sql);
	}
	
	
	@Override
	public int deleteall(int id) {
		 String sql="delete from actionplan where   actionplan.actionplan_type = 1 and  actionplan_idrfer = "+id;    
		    return jdbcTemplate.update(sql);
	}
	
	@Override
	public int deletealls(int id) {
		 String sql="delete from actionplan where   actionplan.actionplan_type = 0 and  actionplan_idrfer = "+id;    
		    return jdbcTemplate.update(sql);
	}
	

	@Override
	public int save(actionplan actionplans) {

				String sql = "INSERT INTO actionplan(actionplan_idrfer,actionplan_dateadd,actionplan_comment,actionplan_responsible,actionplan_expenses,actionplan_payment,actionplan_user_id"
						+ ",actionplan_problem,actionplan_solutions,actionplan_result)"
						+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
								  
						        //jdbcTemplate.update(sql,memberInfo);
						          KeyHolder keyHolder = new GeneratedKeyHolder();
						
						          jdbcTemplate.update(connection -> {
						         	 
						              PreparedStatement ps = connection
						                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						                ps.setInt(1, actionplans.getActionplan_idrfer());
						                ps.setString(2, actionplans.getActionplan_dateadd());
						                ps.setString(3, actionplans.getActionplan_comment());
						                ps.setString(4, actionplans.getActionplan_responsible());
						                ps.setString(5, actionplans.getActionplan_expenses());
						                ps.setString(6, actionplans.getActionplan_payment());
						                ps.setInt(7, actionplans.getActionplan_user_id());
						                ps.setString(8, actionplans.getActionplan_problem());
						                ps.setString(9, actionplans.getActionplan_solutions());
						                ps.setString(10, actionplans.getActionplan_result());
						                
						                return ps;
						             
						          }, keyHolder);			
						          //String key = keyHolder.getKey();
						          System.out.println( keyHolder.getKey().intValue());
						         return  keyHolder.getKey().intValue();
						     // System.out.println( keyHolder.getKey());
				
						         
	}
	@Override
	public int save2(actionplan actionplans) {

				String sql = "INSERT INTO actionplan(actionplan_idrfer,actionplan_dateadd,actionplan_comment,actionplan_responsible,actionplan_type,actionplan_expenses,actionplan_payment)"
						+ " VALUES(?,?,?,?,?,?,?)";
								  
						        //jdbcTemplate.update(sql,memberInfo);
						          KeyHolder keyHolder = new GeneratedKeyHolder();
						
						          jdbcTemplate.update(connection -> {
						         	 
						              PreparedStatement ps = connection
						                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
						                ps.setInt(1, actionplans.getActionplan_idrfer());
						                ps.setString(2, actionplans.getActionplan_dateadd());
						                ps.setString(3, actionplans.getActionplan_comment());
						                ps.setString(4, actionplans.getActionplan_responsible());
						                ps.setInt(5,1);
						                ps.setString(6, actionplans.getActionplan_expenses());
						                ps.setString(7, actionplans.getActionplan_payment());
						                return ps;
						             
						          }, keyHolder);			
						          //String key = keyHolder.getKey();
						          System.out.println( keyHolder.getKey().intValue());
						         return  keyHolder.getKey().intValue();
						     // System.out.println( keyHolder.getKey());
				
				
	}

	@Override
	public int update(actionplan actionplans) {
		Object[] actionplanInfo = { 
				actionplans.getActionplan_idrfer(),
				actionplans.getActionplan_datedue(),
				actionplans.getActionplan_dateadd(),
				actionplans.getActionplan_responsible(),
				actionplans.getActionplan_inspector(),
				actionplans.getActionplan_problem(),
				actionplans.getActionplan_cause(),
				actionplans.getActionplan_solutions(),
				actionplans.getActionplan_result(),
				actionplans.getActionplan_comment(),
				actionplans.getActionplan_status(),
				actionplans.getActionplan_expenses(),
				actionplans.getActionplan_payment(),
				actionplans.getSubmission(),
				actionplans.getActionplan_id(),
				};
				String sql = "UPDATE  actionplan set actionplan_idrfer=?,actionplan_datedue=?,actionplan_dateadd=?"
						+ ",actionplan_responsible=?,actionplan_inspector=?,actionplan_problem=?,actionplan_cause=?"
						+ ",actionplan_solutions=?,actionplan_result=?,actionplan_comment=?,actionplan_status=?"
						+ ",actionplan_expenses=?,actionplan_payment=?,submission=? where  actionplan_id =?   ";
				return jdbcTemplate.update(sql,actionplanInfo);
	}
	
	
	@Override
	public int setPoin(String poin,int id) {

				String sql = "UPDATE  actionplan set actionplan_solutions=? where  actionplan_id =?  ";
				return jdbcTemplate.update(sql,new Object[]{poin,id});
	}
	@Override
	public int setSubmission(int id,int Submission) {
		
				String sql = "UPDATE  actionplan set submission = ? where  actionplan_id =?  ";
				return jdbcTemplate.update(sql,new Object[]{Submission,id});
	}
	
	
	@Override
	public int setcm(int result,int id) {

				String sql = "UPDATE  actionplan set actionplan_result=? where  actionplan_id =?  ";
				return jdbcTemplate.update(sql,new Object[]{result,id});
	}
	
	
	@Override
	public int numbyidrefer(int id) {

				String sql = "select COUNT(*) from actionplan   where  actionplan_idrfer =? and  actionplan.actionplan_type = 0  ";
				return jdbcTemplate.queryForObject(sql,new Object[]{id}, Integer.class);
	}

	
	
	

	@Override
	public int numbyidrefer2(int id) {

				String sql = "select COUNT(*) from actionplan   where  actionplan_idrfer =?  and  actionplan.actionplan_type = 1 ";
				return jdbcTemplate.queryForObject(sql,new Object[]{id}, Integer.class);
	}

	
	@Override
	public actionplan Total(int id) {
		
		  try {
				String sql = "SELECT SUM(actionplan_expenses) AS actionplan_expenses,SUM(actionplan_payment) AS actionplan_payment,actionplan.*"
						+ " FROM actionplan  where actionplan_idrfer = ?  GROUP BY actionplan_idrfer ";
				actionplan actionplans  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new actionplanmapper2());
				return actionplans;
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }


		
		
		
	}

	
}
