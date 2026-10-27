package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smepro.models.actionplan;
import com.smepro.models.backup;
import com.smepro.models.holiday;
import com.smepro.models.upload_pn;
import com.smepro.rowmapper.actionplanmapper;
import com.smepro.rowmapper.backupmapper;
import com.smepro.rowmapper.holidaymapper;


public class holidayDAOlmpl implements holidayDAO {

	private JdbcTemplate jdbcTemplate;
	
	public holidayDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<holiday> getall(String year) {
		String sql = "SELECT * FROM holiday  where holiday_year = ?  AND  holiday_date IS NOT NULL  ";
		List<holiday> holidays  =  jdbcTemplate.query(sql,new Object[]{year},new holidaymapper());
		return holidays;
	}
	
	
	@Override
	public List<holiday> getall(String year,String type) {
		String sql = "SELECT * FROM holiday  where holiday_year = ? and holiday_type=?  ";
		List<holiday> holidays  =  jdbcTemplate.query(sql,new Object[]{year,type},new holidaymapper());
		return holidays;
	}
	
	
	
	@Override
	public int delete(String year) {
		 String sql="delete from holiday where  holiday_year = ?";    
		    return jdbcTemplate.update(sql,new Object[]{year});
	}
	
	
	@Override
	public int copy(String oldyear,String year) {
		 String sql="INSERT INTO holiday (holiday_year,holiday_type,holiday_date) "
		 		+ "SELECT ? AS holiday_year,holiday_type,holiday_date FROM holiday WHERE holiday_year = ? ";    
		    return jdbcTemplate.update(sql,new Object[]{oldyear,year});
	}
	
	
	@Override
	public void savebatch(
			List<String> holiday_date,
			List<String> holiday_name,
			List<String>holiday_type,
			String holiday_year) {	
		
		
		String sql = "INSERT INTO holiday(holiday_year,holiday_type,holiday_date,holiday_name) "
				+ "VALUES(?,?,?,?)";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {
			                ps.setInt(1,Integer.valueOf(holiday_year));
			                ps.setString(2, holiday_type.get(i));
			                ps.setString(3, holiday_date.get(i));
			                ps.setString(4, holiday_name.get(i));
	            }

	            @Override
	            public int getBatchSize() {
	                return holiday_name.size();

	            }
	        });

	
	}

	
	
}
