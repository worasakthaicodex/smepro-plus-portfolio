package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Extension;
import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class Extensionmapper implements RowMapper<Extension> {

	@Override
	public Extension mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Extension data = new Extension();

		 
		 if (hasColumn(rs, "extension_id")) {
			 data.setExtension_id(rs.getInt("extension_id"));
	        }
		 if (hasColumn(rs, "extension_name")) {
			 data.setExtension_name(rs.getString("extension_name"));
	        } 
		 
		 if (hasColumn(rs, "extension_type")) {
			 data.setExtension_type(rs.getString("extension_type"));
	        } 
		 
		 if (hasColumn(rs, "extension_parameter")) {
			 data.setExtension_parameter(rs.getString("extension_parameter"));
	        } 
		 
		 if (hasColumn(rs, "extension_parameter_type")) {
			 data.setExtension_parameter_type(rs.getString("extension_parameter_type"));
	        }
		 if (hasColumn(rs, "extension_process")) {
			 data.setExtension_process(rs.getInt("extension_process"));
	        }
		 
		 if (hasColumn(rs, "component_req")) {
			 data.setComponent_req(rs.getString("component_req"));
	        }
		 if (hasColumn(rs, "extension_parameter_name")) {
			 data.setExtension_parameter_name(rs.getString("extension_parameter_name"));
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
