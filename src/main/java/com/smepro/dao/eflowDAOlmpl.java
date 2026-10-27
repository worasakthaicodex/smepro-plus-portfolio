package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smepro.models.eflow;
import com.smepro.models.group_authorities;
import com.smepro.models.user;
import com.smepro.rowmapper.eflowmapper;
import com.smepro.rowmapper.usermapper2;



public class eflowDAOlmpl implements eflowDAO {

	private JdbcTemplate jdbcTemplate;
	
	public eflowDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public List<eflow> getall(int idrefer) {
		String sql = "SELECT * FROM eflow where  eflow_idform=?  order by eflow_id ASC";
		List<eflow> eflows  =  jdbcTemplate.query(sql,new Object[]{idrefer},new eflowmapper());
		return eflows;
	}
	
	@Override
	public eflow getbyid(int id) {
		
		  try {
			  String sql = "SELECT * FROM eflow  where  eflow_id=? ";
				eflow eflows  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new eflowmapper());
				return eflows;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
	}
	
	@Override
	public List<eflow> getall() {
		String sql = "SELECT * FROM eflow limit 1000  ";
		List<eflow> eflows  =  jdbcTemplate.query(sql,new eflowmapper());
		return eflows;
	}
	@Override
	public int delete(int id) {
		 String sql="delete from eflow where  eflow_idform = ?";    
		    return jdbcTemplate.update(sql,new Object[]{id});
	}
	@Override
	public int clear() {
		 String sql="delete from eflow where  eflow_idform = 0 ";    
		    return jdbcTemplate.update(sql,new Object[]{});
	}
	
	@Override
	public int save(eflow eflows) {
		Object[] workflowInfo = { 
				eflows.getEflow_name(),
				eflows.getEflow_idrefer(),
				eflows.getEflow_idform(),
				eflows.getEflow_key1(),
				eflows.getEflow_key2(),
				eflows.getEflow_key3(),
				eflows.getEflow_key4(),
				
				};
				String sql = "INSERT INTO eflow(eflow_name,eflow_idrefer,eflow_idform,eflow_key1,eflow_key2,eflow_key3,eflow_key4) "
						+ "VALUES(?,?,?,?,?,?,?)";
				return jdbcTemplate.update(sql,workflowInfo);
	}

	
	
	@Override
	public void set(List<String> workflow_eform,List<String> workflow_name, int id
			,List<String> eflow_key1,List<String> eflow_key2
			,List<String> eflow_key3,List<String> eflow_key4) {	
		
		
		String sql = "INSERT INTO eflow(eflow_name,eflow_idrefer,eflow_idform,eflow_key1,eflow_key2,eflow_key3,eflow_key4) "
				+ "VALUES(?,?,?,?,?,?,?)";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {
		                ps.setString(1, workflow_name.get(i));
		                ps.setInt(2, Integer.valueOf(workflow_eform.get(i)));
		                ps.setInt(3,id);
		                ps.setString(4,eflow_key1 == null ?  null:eflow_key1.get(i));
		                ps.setString(5,eflow_key2 == null ?  null: eflow_key2.get(i));
		                ps.setString(6,eflow_key3 == null ?  null:eflow_key3.get(i));
		                ps.setString(7,eflow_key4 == null ?  null:eflow_key4.get(i));
	            	}

	            @Override
	            public int getBatchSize() {
	                return workflow_eform.size();
	            }
	        });

	
	}
	
	
	
	@Override
	public int save(int id) {
		
		String sql = "INSERT INTO eflow(eflow_name,eflow_idrefer,eflow_idform,eflow_key1,eflow_key2,eflow_key3,eflow_key4) "
				+ "VALUES(?,?,?,?,?,?,?)";
				          KeyHolder keyHolder = new GeneratedKeyHolder();
				
				          jdbcTemplate.update(connection -> {
				         	 
				              PreparedStatement ps = connection
				                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				                ps.setString(1,null);
				                ps.setInt(2, 0);
				                ps.setInt(3,id);
				                ps.setString(4,null);
				                ps.setString(5,null);
				                ps.setString(6,null);
				                ps.setString(7,null);
				                return ps;
				             
				          }, keyHolder);			
				          //String key = keyHolder.getKey();
				          System.out.println( keyHolder.getKey().intValue());
				         return  keyHolder.getKey().intValue();
				     // System.out.println( keyHolder.getKey());
		
	}
	

	@Override
	public int update(eflow eflows) {
		Object[] workflowInfo = { 
				eflows.getEflow_name(),
				eflows.getEflow_idrefer(),
				eflows.getEflow_key1(),
				eflows.getEflow_key2(),
				eflows.getEflow_key3(),
				eflows.getEflow_key4(),
				eflows.getEflow_id(),
				};
		
		String sql = "UPDATE eflow SET eflow_name=?,eflow_idrefer=?,eflow_key1=?,eflow_key2=?,eflow_key3=?,eflow_key4=? WHERE eflow_id =? ";
		return jdbcTemplate.update(sql,workflowInfo);
	}


}
