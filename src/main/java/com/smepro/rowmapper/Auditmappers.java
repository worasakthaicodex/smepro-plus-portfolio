package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Audit;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.file;


public class Auditmappers implements RowMapper<Audit> {

	@Override
	public  Audit mapRow(ResultSet rs, int rowNum) throws SQLException {
		Audit  data = new  Audit();
		 
		 
		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		  if (hasColumn(rs, "audit_type")) {
			  data.setAudit_type(rs.getString("audit_type"));
	        } 
		  if (hasColumn(rs, "audit_code")) {
			  data.setAudit_code(rs.getString("audit_code"));
	        } 
		  
		  if (hasColumn(rs, "created_at")) {
			  data.setCreated_at(rs.getString("created_at"));
	        } 
		  
		  if (hasColumn(rs, "edata_id")) {
			  data.setEdata_id(rs.getLong("edata_id"));
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
