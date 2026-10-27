package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Adjustment_fg;
import com.smepro.models.Fg;
import com.smepro.models.Issue_fg;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class Adjustment_fgmappers implements RowMapper<Adjustment_fg> {

	@Override
	public  Adjustment_fg mapRow(ResultSet rs, int rowNum) throws SQLException {
		Adjustment_fg  action = new  Adjustment_fg();
		 
			  
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

		 
		 if (hasColumn(rs, "adjustment_reason")) {
			 action.setAdjustment_reason(rs.getString("adjustment_reason"));
	        }  
		 if (hasColumn(rs, "adjustment_date")) {
			 action.setAdjustment_date(rs.getString("adjustment_date"));
	        }  

		 if (hasColumn(rs, "adjustment_remark")) {
			 action.setAdjustment_remark(rs.getString("adjustment_remark"));
	        }  

		 if (hasColumn(rs, "adjustment_type")) {
			 action.setAdjustment_type(rs.getString("adjustment_type"));
	        }  

		 if (hasColumn(rs, "adjustment_location")) {
			 action.setAdjustment_location(rs.getString("adjustment_location"));
	        }  

		 if (hasColumn(rs, "adjustment_pd_code")) {
			 action.setAdjustment_pd_code(rs.getString("adjustment_pd_code"));
	        }  

		 if (hasColumn(rs, "adjustment_lot_batch")) {
			 action.setAdjustment_lot_batch(rs.getString("adjustment_lot_batch"));
	        }  


		 if (hasColumn(rs, "adjustment_product_code")) {
			 action.setAdjustment_product_code(rs.getString("adjustment_product_code"));
	        }  

		 if (hasColumn(rs, "adjustment_product_name")) {
			 action.setAdjustment_product_name(rs.getString("adjustment_product_name"));
	        }  

		 if (hasColumn(rs, "adjustment_quantity")) {
			 action.setAdjustment_quantity(rs.getDouble("adjustment_quantity"));
	        }  

		 if (hasColumn(rs, "adjustment_unit")) {
			 action.setAdjustment_unit(rs.getString("adjustment_unit"));
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
