package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.DocumentEffect;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.file;


public class DocumentEffectmappers implements RowMapper<DocumentEffect> {

	@Override
	public  DocumentEffect mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentEffect  data = new  DocumentEffect();
		 
		 
		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		  if (hasColumn(rs, "edata_id")) {
			  data.setEdata_id(rs.getInt("edata_id"));
	        } 
		  if (hasColumn(rs, "data_type")) {
			  data.setData_type(rs.getString("data_type"));
	        } 

		  if (hasColumn(rs, "title")) {
			  data.setTitle(rs.getString("title"));
	        } 
		  if (hasColumn(rs, "type")) {
			  data.setType(rs.getString("type"));
	        } 
		
		  if (hasColumn(rs, "created_at")) {
			  data.setCreated_at(rs.getString("created_at"));
	        } 	
		  
			 if (hasColumn(rs, "id_new")) {
				 data.setId_new(rs.getInt("id_new"));
		        }
			 if (hasColumn(rs, "id_old")) {
				 data.setId_old(rs.getInt("id_old"));
		        }
		  
			 if (hasColumn(rs, "id_add")) {
				 data.setId_add(rs.getInt("id_add"));
		        }
			 if (hasColumn(rs, "date_add")) {
				 data.setDate_add(rs.getString("date_add"));
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
