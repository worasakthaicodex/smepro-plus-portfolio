package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


import com.smepro.models.group_members;
import com.smepro.models.groups;
import com.smepro.rowmapper.group_membersmapper;
import com.smepro.rowmapper.groupsmapper;
import com.smepro.rowmapper.usermapper;

public class group_membersDAOlmpl implements group_membersDAO {

	private JdbcTemplate jdbcTemplate;
	
	public group_membersDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	

	
	@Override
	public List<group_members> getall(String username) {
		String sql = "SELECT * FROM group_members LEFT JOIN groups ON groups.id  = group_members.group_id where group_members.username =? ";
		List<group_members> group_memberss  =  jdbcTemplate.query(sql , new Object[]{username}, new group_membersmapper());
		return group_memberss;
	}

	@Override
	public void save(String username) {
		Object[] group_membersInfo = { username};
				String sql = "INSERT INTO group_members(username,group_id) VALUES(?,0);";
				jdbcTemplate.update(sql,group_membersInfo);

	}
	
	@Override
	public void save(String username,int groupid) {
		Object[] group_membersInfo = { username,groupid};
				String sql = "INSERT INTO group_members(username,group_id) VALUES(?,?);";
				jdbcTemplate.update(sql,group_membersInfo);

	}


	@Override
	public group_members getbyId(String username) {
		
		
		 try {
			 String sql = "SELECT * FROM group_members  where group_members.username =? ";
				return jdbcTemplate.queryForObject(sql, new Object[]{username},new group_membersmapper());			  
			 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
		
		
	}

	@Override
	public int update(group_members group_memberss, int idrefer) {
		  String sql=  "UPDATE group_members SET group_id='"+group_memberss.getGroup_id()+"' WHERE  username='"+group_memberss.getUsername()+"'";
		    return jdbcTemplate.update(sql);  
	}

	@Override
	public int updateusername(String username,String username2) {
		  String sql=  "UPDATE group_members SET username='"+username+"' WHERE  username='"+username2+"'";
		    return jdbcTemplate.update(sql);  
	}
	
	
	@Override
	public int delete(String username) {
		 String sql="delete from group_members where username ='"+username+"'";    
		    return jdbcTemplate.update(sql);
	}

}
