package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.ExtensionParameter;
import com.smepro.models.Management;
import com.smepro.models.TabManagement;
import com.smepro.models.Tabform;
import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class Tabformmapper implements RowMapper<Tabform> {

	@Override
	public Tabform mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Tabform data = new Tabform();

		 
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
		
		 if (hasColumn(rs, "tab_step")) {
			 data.setTab_step(rs.getInt("tab_step"));
	        }
		
		 if (hasColumn(rs, "tab_access")) {
			 data.setTab_access(rs.getInt("tab_access"));
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
