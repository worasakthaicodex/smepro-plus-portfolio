package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Myapp;
import com.smepro.models.registerkey;



public class registerkeymapper implements RowMapper<registerkey> {

	@Override
	public registerkey mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		registerkey register = new registerkey();

		 
		
		
		 if (hasColumn(rs, "id")) {
			 register.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "app_id")) {
			 register.setApp_id(rs.getInt("app_id"));
	        } 
		 
		 if (hasColumn(rs, "mainkey")) {
			 register.setMainkey(rs.getString("mainkey"));
	        } 
		 
		 if (hasColumn(rs, "type")) {
			 register.setType(rs.getString("type"));
	        }
		 if (hasColumn(rs, "title")) {
			 register.setTitle(rs.getString("title"));
	        }
		
		 if (hasColumn(rs, "required")) {
			 register.setRequired(rs.getInt("required"));
	        }
		 if (hasColumn(rs, "source")) {
			 register.setSource(rs.getString("source"));
	        }
		
		 if (hasColumn(rs, "doc")) {
			 register.setDoc(rs.getString("doc"));
	        }
		 if (hasColumn(rs, "action_type")) {
			 register.setAction_type(rs.getString("action_type"));
	        }
		 
		 if (hasColumn(rs, "keygroups")) {
			 register.setKeygroups(rs.getString("keygroups"));
	        }
		
		return register;
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
