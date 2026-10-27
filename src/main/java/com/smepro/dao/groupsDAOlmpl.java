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
import com.smepro.models.groups;
import com.smepro.rowmapper.departmentmapper;
import com.smepro.rowmapper.group_membersmapper;
import com.smepro.rowmapper.groupsmapper;

public class groupsDAOlmpl implements groupsDAO {

	

	private JdbcTemplate jdbcTemplate;
	
	public groupsDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	@Override
	public List<groups> getall(int id) {
		String sql = "SELECT * FROM groups where type =? and main ='0' order by group_department asc";
		List<groups> groupss  =  jdbcTemplate.query(sql , new Object[]{id}, new groupsmapper());
		return groupss;
	}
	
	
	@Override
	public List<groups> findInAll(String idString) {
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
		        String sql = "SELECT * FROM groups WHERE id IN (" + placeholders + ") LIMIT 500";

		        // 4️⃣ Query
		        return jdbcTemplate.query(sql, idList.toArray(), new groupsmapper());
		    }
	
	
	

	@Override
	public void save(groups groupss, int idrefer) {		
		Object[] groupsInfo = { 
				groupss.getGroup_name(),
				idrefer,
				groupss.getGroup_department(),
				groupss.getGroup_department_id(),
				groupss.getGroup_level()
				};
				String sql = "INSERT INTO groups(group_name,type,main,group_department,group_department_id,group_level) VALUES(?,?,0,?,?,?);";
				jdbcTemplate.update(sql,groupsInfo);
	
	}

	@Override
	public groups getbyId(int id, int idrefer) {
	    String sql="select * from groups where id=? and type=? and main ='0' ";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{id,idrefer},new groupsmapper()); 
	}
	
	

	@Override
	public groups getbyIds(int id) {
	  
	    
		 try {
			  String sql="select * from groups where id=?  ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id},new groupsmapper()); 
			    	  
			 
		 }catch(EmptyResultDataAccessException e) {
			 
		
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("groups with the same Id .......");
		   
		 }
	}

	@Override
	public int update(groups groupss, int idrefer) {
		  String sql=  "UPDATE groups SET "
		  		+ "group_name='"+groupss.getGroup_name()+"',"
		  		+ "group_department='"+groupss.getGroup_department()+"',"
		  		+ "group_department_id='"+groupss.getGroup_department_id()+"',"
		  		+ "group_level='"+groupss.getGroup_level()+"'"
		  		+ " WHERE main ='0' and  type='"+idrefer+"'  and  id="+groupss.getId();
			    return jdbcTemplate.update(sql);   
	}

	@Override
	public int delete(int id, int idrefer) {
		 String sql="delete from groups where main ='0'  and type='"+idrefer+"' and  id="+id+"";    
		    return jdbcTemplate.update(sql);
	}
	
	public int getsum() {
		String sql = "select count(*) from  groups ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {  }, Integer.class);
	}
	

	

}
