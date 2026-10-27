package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.UserCode;


public class UserCodemapper implements RowMapper<UserCode> {

	

	
	@Override
	public UserCode mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserCode row = new UserCode();
		row.setUser_code_id(rs.getInt("user_code_id"));
		row.setUser_code_pattern(rs.getString("user_code_pattern"));
		row.setUser_code_note(rs.getString("user_code_note"));
		row.setDefaultpassword(rs.getString("defaultpassword"));
		row.setAutoid(rs.getInt("autoid"));
		
		row.setUser_code_department(rs.getInt("user_code_department"));
		row.setUser_code_daterun(rs.getInt("user_code_daterun"));
		
		
		 if (hasColumn(rs, "department")) {
			 row.setDepartment(rs.getString("department"));
	        }else {
	        	 row.setDepartment("All");
	        	
	        }
		return row;
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
