package com.smepro.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


import com.smepro.models.setmail;
import com.smepro.rowmapper.setmailmapper;


public class setmailDAOlmpl implements setmailDAO {

	
	private JdbcTemplate jdbcTemplate;
	
	public setmailDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public void set(int idrefer) {
	Object[] buildingInfo = { 
			idrefer,"-","-","smtp.gmail.com","587",true,true,"-","-","-"
			};
			String sql = "INSERT INTO setmail(id_refer,username,password,hostname,starttls_port,starttls,auth,url,support,emailtest) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?);";
			jdbcTemplate.update(sql,buildingInfo);
	}
	
	
	
	@Override
	public setmail getbyId(int id) {
		 String sql="select * from setmail where id=?";    
		    return jdbcTemplate.queryForObject(sql, new Object[]{id},new setmailmapper()); 
	}

	@Override
	public int update(setmail setmails, int idrefer) {
		
		int Auth = 0;
		int Starttls = 0;
		if(setmails.isAuth() == true) {
			Auth = 1;
		}
		if(setmails.isStarttls() == true) {
			Starttls = 1;
		}
		
		
		  String sql=  "UPDATE setmail SET username='"+setmails.getUsername()+"'"
		  		+ ",password ='"+setmails.getPassword()+"'  "
		  		+ ",hostname ='"+setmails.getHostname()+"'  "
		  		+ ",starttls_port ='"+setmails.getStarttls_port()+"'  "
		  		+ ",starttls ='"+Starttls+"'  "
		  		+ ",auth ='"+Auth+"'  "
		  		+ ",url ='"+setmails.getUrl()+"'  "
		  		+ ",support ='"+setmails.getSupport()+"'  "
		  		+ ",emailtest ='"+setmails.getEmailtest()+"'  "
		  		+ "WHERE  id='"+idrefer+"'";
		  
		  return jdbcTemplate.update(sql);  
	}
	
	
	public int ismail(int id) {
		String sql = "select count(*) from setmail where id_refer = ? ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] { id}, Integer.class);
	}
	

}
