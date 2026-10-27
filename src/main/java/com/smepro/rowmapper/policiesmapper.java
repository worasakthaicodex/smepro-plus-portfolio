package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Myapp;
import com.smepro.models.keymap;
import com.smepro.models.policies;
import com.smepro.models.registerkey;



public class policiesmapper implements RowMapper<policies> {

	@Override
	public policies mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		policies key = new policies();

		 
		 if (hasColumn(rs, "id")) {
			 key.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "sub_processes")) {
			 key.setSub_processes(rs.getInt("sub_processes"));
	        } 
		 
		 if (hasColumn(rs, "process")) {
			 key.setProcess(rs.getInt("process"));
	        } 
		 
		 if (hasColumn(rs, "name")) {
			 key.setName(rs.getString("name"));
	        }
		 if (hasColumn(rs, "respon")) {
			 key.setRespon(rs.getString("respon"));
	        }
		
		 if (hasColumn(rs, "max_approval_amount")) {
			 key.setMax_approval_amount(rs.getString("max_approval_amount"));
	        }
		 if (hasColumn(rs, "group_id")) {
			 key.setGroup_id(rs.getString("group_id"));
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
