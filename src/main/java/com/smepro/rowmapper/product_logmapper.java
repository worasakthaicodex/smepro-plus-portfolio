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



public class product_logmapper implements RowMapper<product_log> {
	
	@Override
	public product_log mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		product_log register_logs = new product_log();

		 
		 if (hasColumn(rs, "id")) {
			 register_logs.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "workflow_id")) {
			 register_logs.setWorkflow_id(rs.getInt("workflow_id"));
	        } 
		 
		 if (hasColumn(rs, "id_add")) {
			 register_logs.setId_add(rs.getInt("id_add"));
	        } 
		 
		 if (hasColumn(rs, "product_category")) {
			 register_logs.setProduct_category(rs.getString("product_category"));
	        }
		 if (hasColumn(rs, "created_at")) {
			 register_logs.setCreated_at(rs.getString("created_at"));
	        }
		
		 
		 if (hasColumn(rs, "type")) {
			 register_logs.setType(rs.getString("type"));
	        }
		 if (hasColumn(rs, "edata_id")) {
			 register_logs.setEdata_id(rs.getInt("edata_id"));
	        }
		 if (hasColumn(rs, "process_id")) {
			 register_logs.setProcess_id(rs.getInt("process_id"));
	        }
		
		 if (hasColumn(rs, "product_id")) {
			 register_logs.setProduct_id(rs.getInt("product_id"));
	        }


		 if (hasColumn(rs, "product_code")) {
			 register_logs.setProduct_code(rs.getString("product_code"));
	        } 
		 
		 if (hasColumn(rs, "product_names")) {
			 register_logs.setProduct_names(rs.getString("product_names"));
	        }
		 
		 if (hasColumn(rs, "product_category")) {
			 register_logs.setProduct_category(rs.getString("product_category"));
	        }
		  if (hasColumn(rs, "product_details")) {
			  register_logs.setProduct_details(rs.getString("product_details"));
	      } 
		if (hasColumn(rs, "unit")) {
			register_logs.setUnit(rs.getString("unit"));
		} 
		if (hasColumn(rs, "product_status")) {
			register_logs.setProduct_status(rs.getString("product_status"));
		}   	 
		if (hasColumn(rs, "created_date")) {
			register_logs.setCreated_date(rs.getString("created_date"));
		}   	
		if (hasColumn(rs, "last_updated")) {
			register_logs.setLast_updated(rs.getString("last_updated"));
	}   		 
		if (hasColumn(rs, "product_daterun")) {
			register_logs.setProduct_daterun(rs.getString("product_daterun"));
	}   	
		
		 
		 
		 
		return register_logs;
		
		
		
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
