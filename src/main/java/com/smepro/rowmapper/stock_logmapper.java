package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Myapp;
import com.smepro.models.keymap;
import com.smepro.models.policies;
import com.smepro.models.product_log;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;
import com.smepro.models.stock_log;



public class stock_logmapper implements RowMapper<stock_log> {
	
	@Override
	public stock_log mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		stock_log logs = new stock_log();

		 if (hasColumn(rs, "edata_id")) {
			 logs.setEdata_id(rs.getInt("edata_id"));
	        }
		 if (hasColumn(rs, "id")) {
			 logs.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "workflow_id")) {
			 logs.setWorkflow_id(rs.getInt("workflow_id"));
	        } 
		 
		 if (hasColumn(rs, "process_id")) {
			 logs.setProcess_id(rs.getInt("process_id"));
	        } 
		 if (hasColumn(rs, "id_refer")) {
			 logs.setWorkflow_id(rs.getInt("id_refer"));
	        } 
		  
		 
		 if (hasColumn(rs, "id_add")) {
			 logs.setId_add(rs.getInt("id_add"));
	        } 
		 if (hasColumn(rs, "stock_type")) {
			 logs.setStock_type(rs.getString("stock_type"));
	        } 
		 if (hasColumn(rs, "stock_group")) {
			 logs.setStock_group(rs.getString("stock_group"));
	        }
		 if (hasColumn(rs, "created_at")) {
			 logs.setCreated_at(rs.getString("created_at"));
	        }
		

	
	
		 
		return logs;
		
		
		
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
