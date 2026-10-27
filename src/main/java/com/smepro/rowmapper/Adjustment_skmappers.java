package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Fg;
import com.smepro.models.Issue_fg;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class Adjustment_skmappers implements RowMapper<Adjustment_sk> {

	@Override
	public  Adjustment_sk mapRow(ResultSet rs, int rowNum) throws SQLException {
		Adjustment_sk  action = new  Adjustment_sk();
		 
			  
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "edata_id")) {
			 action.setEdata_id(rs.getInt("edata_id"));
	        }
	
		 if (hasColumn(rs, "doc_code")) {
			 action.setDoc_code(rs.getString("doc_code"));
	        }
		 if (hasColumn(rs, "tempId")) {
			 action.setTempId(rs.getString("tempId"));
	        }
		 if (hasColumn(rs, "title")) {
			 action.setTitle(rs.getString("title"));
	        }
		 if (hasColumn(rs, "status")) {
			 action.setStatus(rs.getString("status"));
	        }  

		 if (hasColumn(rs, "count")) {
			 action.setCount(rs.getInt("count"));
	        }  

		

		 if (hasColumn(rs, "ska_date")) {
			 action.setSka_date(rs.getString("ska_date"));
	        }  

		 if (hasColumn(rs, "ska_reason")) {
			 action.setSka_reason(rs.getString("ska_reason"));
	        }  

		 if (hasColumn(rs, "ska_remark")) {
			 action.setSka_remark(rs.getString("ska_remark"));
	        }  

		 if (hasColumn(rs, "ska_type")) {
			 action.setSka_type(rs.getString("ska_type"));
	        }  

		 if (hasColumn(rs, "ska_location")) {
			 action.setSka_location(rs.getString("ska_location"));
	        }  

		 if (hasColumn(rs, "ska_lot_batch")) {
			 action.setSka_lot_batch(rs.getString("ska_lot_batch"));
	        }  


		 if (hasColumn(rs, "ska_tracking_number")) {
			 action.setSka_tracking_number(rs.getString("ska_tracking_number"));
	        }  


		 if (hasColumn(rs, "ska_stock_code")) {
			 action.setSka_stock_code(rs.getString("ska_stock_code"));
	        }  

		 if (hasColumn(rs, "ska_stock_name")) {
			 action.setSka_stock_name(rs.getString("ska_stock_name"));
	        }  

		 if (hasColumn(rs, "ska_quantity")) {
			 action.setSka_quantity(rs.getDouble("ska_quantity"));
	        }  

		 if (hasColumn(rs, "ska_unit")) {
			 action.setSka_unit(rs.getString("ska_unit"));
	        }  
		 if (hasColumn(rs, "ska_pd")) {
			 action.setSka_pd(rs.getString("ska_pd"));
	        }  
		 
		 if (hasColumn(rs, "data_nameadd")) {
			 action.setData_nameadd(rs.getString("data_nameadd"));
	        }  
		 if (hasColumn(rs, "ska_approved_by")) {
			 action.setSka_approved_by(rs.getString("ska_approved_by"));
	        }  
		 
		 if (hasColumn(rs, "ska_remaining")) {
			 action.setSka_remaining(rs.getDouble("ska_remaining"));
	        }  
		 
		 
		 
		 
		 
		return action;


	
		
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
