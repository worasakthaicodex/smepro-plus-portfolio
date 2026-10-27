package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Myapp;
import com.smepro.models.keymap;
import com.smepro.models.registerkey;



public class keymapmapper implements RowMapper<keymap> {

	@Override
	public keymap mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		keymap key = new keymap();

		 
		
		 if (hasColumn(rs, "id")) {
			 key.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "title")) {
			 key.setTitle(rs.getString("title"));
	        }
		 if (hasColumn(rs, "sub_processes_id")) {
			 key.setSub_processes_id(rs.getInt("sub_processes_id"));
	        } 
		 
		 if (hasColumn(rs, "processes_id")) {
			 key.setProcesses_id(rs.getInt("processes_id"));
	        } 
		 
		 if (hasColumn(rs, "mainkey")) {
			 key.setMainkey(rs.getString("mainkey"));
	        }
		 if (hasColumn(rs, "eformkey")) {
			 key.setEformkey(rs.getString("eformkey"));
	        }
		
		 if (hasColumn(rs, "change_format")) {
			 key.setChange_format(rs.getString("change_format"));
	        }
		 if (hasColumn(rs, "enum_id")) {
			 key.setEnum_id(rs.getString("enum_id"));
	        }
		
		 if (hasColumn(rs, "filter_enum")) {
			 key.setFilter_enum(rs.getString("filter_enum"));
	        }
		 
		 if (hasColumn(rs, "type")) {
			 key.setType(rs.getString("type"));
	        }
		
		 if (hasColumn(rs, "registerkey_id")) {
			 key.setRegisterkey_id(rs.getString("registerkey_id"));
	        }
		
		 
		 
		return key;
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
