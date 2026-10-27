package com.smepro.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class roleDAOlmpl implements roleDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public roleDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	@Override
	public void setrole(String username, String Role) {
		Object[] roleInfo = { 
				username,
				Role
				};
				String sql = "INSERT INTO user_roles(username,role) VALUES(?,?);";
				jdbcTemplate.update(sql,roleInfo);

	}
	
	
	@Override
	public void unsetrole(String username) {
		     String sql="delete from user_roles where username='"+username+"'";    
		    jdbcTemplate.update(sql);

	}
	@Override
	public void setusername(String username,String username2) {
		  String sql=  "UPDATE user_roles SET username ='"+username+"' "
			  		+" WHERE username  ='"+username2+"' ";
			  jdbcTemplate.update(sql);  

	}
}
