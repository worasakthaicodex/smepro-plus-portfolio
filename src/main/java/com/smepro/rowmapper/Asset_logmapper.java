package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Asset_log;
import com.smepro.models.Myapp;
import com.smepro.models.keymap;
import com.smepro.models.policies;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;



public class Asset_logmapper implements RowMapper<Asset_log> {

	@Override
	public Asset_log mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Asset_log register = new Asset_log();

		 
		 if (hasColumn(rs, "edata_id")) {
			 register.setEdata_id(rs.getInt("edata_id"));
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
