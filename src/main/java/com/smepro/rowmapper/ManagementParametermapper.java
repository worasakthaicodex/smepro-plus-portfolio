package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.ExtensionParameter;
import com.smepro.models.Management;
import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class ManagementParametermapper implements RowMapper<Management> {

	@Override
	public Management mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Management data = new Management();

		 
		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "process_id")) {
			 data.setProcess_id(rs.getInt("process_id"));
	        } 
		 
		 if (hasColumn(rs, "management_title")) {
			 data.setManagement_title(rs.getString("management_title"));
	        } 
		 
		 if (hasColumn(rs, "management_abbreviation")) {
			 data.setManagement_abbreviation(rs.getString("management_abbreviation"));
	        } 
		 
		 if (hasColumn(rs, "user_access")) {
			 data.setUser_access(rs.getString("user_access"));
	        }
		 if (hasColumn(rs, "admin_access")) {
			 data.setAdmin_access(rs.getString("admin_access"));
	        }
		 if (hasColumn(rs, "management_id")) {
			 data.setManagement_id(rs.getInt("management_id"));
	        }
		 
	
		 
		 
		 
		 
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
