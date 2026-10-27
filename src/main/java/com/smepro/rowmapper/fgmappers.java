package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Fg;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class fgmappers implements RowMapper<Fg> {

	@Override
	public  Fg mapRow(ResultSet rs, int rowNum) throws SQLException {
		Fg  action = new  Fg();
		 
			  
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "lot_batch")) {
			 action.setLot_batch(rs.getString("lot_batch"));
	        }
	
		 if (hasColumn(rs, "product_code")) {
			 action.setProduct_code(rs.getString("product_code"));
	        }
		 if (hasColumn(rs, "product_name")) {
			 action.setProduct_name(rs.getString("product_name"));
	        }
		 if (hasColumn(rs, "customer_code")) {
			 action.setCustomer_code(rs.getString("customer_code"));
	        }
		 if (hasColumn(rs, "customer_name")) {
			 action.setCustomer_name(rs.getString("customer_name"));
	        }
		 
		 if (hasColumn(rs, "quantity")) {
			 action.setQuantity(rs.getDouble("quantity"));
	        }
		 if (hasColumn(rs, "unit")) {
			 action.setUnit(rs.getString("unit"));
	        }  

		 if (hasColumn(rs, "fg_status")) {
			 action.setFg_status(rs.getString("fg_status"));
	        } 
		 if (hasColumn(rs, "location")) {
			 action.setLocation(rs.getString("location"));
	        } 
		 if (hasColumn(rs, "work_order")) {
			 action.setWork_order(rs.getDouble("work_order"));
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
