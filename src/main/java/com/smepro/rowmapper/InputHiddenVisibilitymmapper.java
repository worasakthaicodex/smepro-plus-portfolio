package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.ExtensionParameter;
import com.smepro.models.InputHiddenVisibility;
import com.smepro.models.Management;
import com.smepro.models.TabManagement;
import com.smepro.models.Tabform;
import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class InputHiddenVisibilitymmapper implements RowMapper<InputHiddenVisibility> {

	@Override
	public InputHiddenVisibility mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InputHiddenVisibility data = new InputHiddenVisibility();

		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "form_id")) {
			 data.setForm_id(rs.getInt("form_id"));
	        } 
		 
		 if (hasColumn(rs, "form_rv")) {
			 data.setForm_rv(rs.getInt("form_rv"));
	        } 
		 
		 if (hasColumn(rs, "title")) {
			 data.setTitle(rs.getString("title"));
	        } 
		 
		 if (hasColumn(rs, "form_column")) {
			 data.setForm_column(rs.getString("form_column"));
	        }
		
		 if (hasColumn(rs, "process_id")) {
			 data.setProcess_id(rs.getInt("process_id"));
	        }
		 if (hasColumn(rs, "step")) {
			 data.setStep(rs.getInt("step"));
	        }
		 if (hasColumn(rs, "action_type")) {
			 data.setAction_type(rs.getString("action_type"));
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
