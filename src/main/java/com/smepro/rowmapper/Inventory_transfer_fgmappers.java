package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Adjustment_fg;
import com.smepro.models.Fg;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Issue_fg;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class Inventory_transfer_fgmappers implements RowMapper<	Inventory_transfer_fg> {

	@Override
	public  Inventory_transfer_fg mapRow(ResultSet rs, int rowNum) throws SQLException {
		Inventory_transfer_fg  action = new  	Inventory_transfer_fg();
	
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

		 

		 if (hasColumn(rs, "transfer_date")) {
			 action.setTransfer_date(rs.getString("transfer_date"));
	        }  

		 if (hasColumn(rs, "transfer_from_location")) {
			 action.setTransfer_from_location(rs.getString("transfer_from_location"));
	        }  

		 if (hasColumn(rs, "transfer_to_location")) {
			 action.setTransfer_to_location(rs.getString("transfer_to_location"));
	        }  

		 if (hasColumn(rs, "transferred_by")) {
			 action.setTransferred_by(rs.getString("transferred_by"));
	        }  

		 if (hasColumn(rs, "transfer_remark")) {
			 action.setTransfer_remark(rs.getString("transfer_remark"));
	        }  

		 if (hasColumn(rs, "transfer_pd_code")) {
			 action.setTransfer_pd_code(rs.getString("transfer_pd_code"));
	        }  


		 if (hasColumn(rs, "transfer_lot_batch")) {
			 action.setTransfer_lot_batch(rs.getString("transfer_lot_batch"));
	        }  

		 if (hasColumn(rs, "transfer_product_code")) {
			 action.setTransfer_product_code(rs.getString("transfer_product_code"));
	        }  

		 if (hasColumn(rs, "transfer_product_name")) {
			 action.setTransfer_product_name(rs.getString("transfer_product_name"));
	        }  

		 if (hasColumn(rs, "transfer_quantity")) {
			 action.setTransfer_quantity(rs.getDouble("transfer_quantity"));
	        }  

		 if (hasColumn(rs, "transfer_unit")) {
			 action.setTransfer_unit(rs.getString("transfer_unit"));
	        }  

		 if (hasColumn(rs, "fg_status")) {
			 action.setFg_status(rs.getString("fg_status"));
	        }  

		 
		 if (hasColumn(rs, "fg_remaining")) {
			 action.setFg_remaining(rs.getDouble("fg_remaining"));
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
