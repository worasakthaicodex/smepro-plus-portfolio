package com.smepro.dao;


import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;



import com.smepro.models.setting;
import com.smepro.rowmapper.settingmapper;


public class settingDAOlmpl implements settingDAO {

	private JdbcTemplate jdbcTemplate;
	
	public settingDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	

	@Override
	public int setholiday(int holiday_notsun) {
		 String sql="UPDATE setting set  holiday_notsun = ? WHERE setting_id= 1 ";    
		    return jdbcTemplate.update(sql,new Object[]{holiday_notsun});
	}
	
	
	@Override
	public setting getbyid() {
		
		
		 String sql="select * from setting where setting_id= 1 ";   
		 setting settings = jdbcTemplate.queryForObject(sql,new settingmapper());
		 
		 
		 return settings;
	}
	

	@Override
	public int UpVersion() {
		 String sql="UPDATE setting set  version = version+1 WHERE setting_id= 1 ";    
		    return jdbcTemplate.update(sql);
	}
	
	
}
