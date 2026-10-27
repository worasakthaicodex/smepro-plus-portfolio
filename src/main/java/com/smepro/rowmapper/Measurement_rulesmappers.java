package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Measurement_rules;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.purchase;
import com.smepro.models.stock;


public class Measurement_rulesmappers implements RowMapper<Measurement_rules> {

	@Override
	public  Measurement_rules mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Measurement_rules  data = new  Measurement_rules();
			data.setId(rs.getInt("id"));
			data.setThis_process(rs.getInt("this_process"));
			data.setDestination_process(rs.getInt("destination_process"));
			data.setTitle(rs.getString("title"));
			data.setThis_closing_status(rs.getString("this_closing_status"));
			data.setDestination_closing_status(rs.getString("destination_closing_status"));
			data.setThis_target(rs.getString("this_target"));
			data.setDestination_column_operation(rs.getString("destination_column_operation"));
			data.setWork_cycle(rs.getInt("work_cycle"));
			data.setRules(rs.getInt("rules"));
		return data;
	
	}
	



	
	
	
	
	   private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
	        ResultSetMetaData rsMetaData = rs.getMetaData();
	        int columnCount = rsMetaData.getColumnCount();
	        for (int i = 1; i <= columnCount; i++) {
	            if (columnName.equalsIgnoreCase(rsMetaData.getColumnName(i))) {
	                return true;
	            }
	        }
	        return false;
	    }
}
