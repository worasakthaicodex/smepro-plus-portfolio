package com.smepro.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.department;
import com.smepro.models.user;
import com.smepro.rowmapper.departmentmapper;
import com.smepro.rowmapper.drivesmapper;
import com.smepro.rowmapper.group_membersmapper;
import com.smepro.rowmapper.usermappers;


public class departmentDAOlmpl implements departmentDAO {

	private JdbcTemplate jdbcTemplate;
	
	public departmentDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public List<department> getall() {
		String sql = "SELECT * FROM department  ";
		List<department> departments  =  jdbcTemplate.query(sql,new departmentmapper());
		return departments;
	}

	@Override
	public List<department> findInAll(String idString) {
		        if (idString == null || idString.trim().isEmpty()) {
		            return Collections.emptyList();
		        }

		        // 1️⃣ Split string → array, remove empty, trim, parse to Integer
		        List<Integer> idList = Arrays.stream(idString.split(","))
		                .map(String::trim)
		                .filter(s -> !s.isEmpty())
		                .map(Integer::parseInt)
		                .collect(Collectors.toList());

		        if (idList.isEmpty()) {
		            return Collections.emptyList();
		        }

		        // 2️⃣ สร้าง placeholders เท่ากับจำนวน id
		        String placeholders = idList.stream()
		                .map(i -> "?")
		                .collect(Collectors.joining(","));

		        // 3️⃣ สร้าง SQL dynamic
		        String sql = "SELECT * FROM department WHERE department_id IN (" + placeholders + ") LIMIT 500";

		        // 4️⃣ Query
		        return jdbcTemplate.query(sql, idList.toArray(), new departmentmapper());
		    }
	
	
	
	
	@Override
	public department getbyId(int id) {
		  String sql="select * from department where department_id=?  ";    
		return jdbcTemplate.queryForObject(sql, new Object[]{id},new departmentmapper()); 
	}
	
	@Override
	public department getbyName(String name) {
		
	
		
		 try {
			  String sql="select * from department where department_name=? limit 1   ";    
				return jdbcTemplate.queryForObject(sql, new Object[]{name},new departmentmapper());   
			 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
		
		
	}
	
	@Override
	public void save(department departments) {
		Object[] departmentInfo = { 
				departments.getDepartment_name(),
				};
				String sql = "INSERT INTO department(department_name)"
						+ " VALUES(?);";
				jdbcTemplate.update(sql,departmentInfo);

	}

	@Override
	public int update(department departments) {
		String sql=  "UPDATE department SET department_name='"+departments.getDepartment_name()+"'"
		  		+ " WHERE  department_id="+departments.getDepartment_id();
		    return jdbcTemplate.update(sql); 
	}

	@Override
	public int delete(int id) {
	    String sql="delete from department where department_id="+id;    
	    return jdbcTemplate.update(sql);
	}

}
