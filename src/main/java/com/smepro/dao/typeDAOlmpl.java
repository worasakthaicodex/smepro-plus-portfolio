package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.type;
import com.smepro.rowmapper.drivesmapper;
import com.smepro.rowmapper.typemapper;
import com.smepro.rowmapper.usermapper2;

public class typeDAOlmpl implements typeDAO {
    
	
	private JdbcTemplate jdbcTemplate;
	
	public typeDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	@Override
	public List<type> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(type types) {
		// TODO Auto-generated method stub

	}

	@Override
	public type getbytype(String type,int id) {

		  try {
			    String sql="select * from type where type_type=? and type_status=?  limit 1 ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{type,id},new typemapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	
	
	}

	@Override
	public int update(String type,int status) {
		  String sql=  "UPDATE type SET type_status='"+status+"' WHERE  type_name='"+type+"'";
		    return jdbcTemplate.update(sql);  
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
