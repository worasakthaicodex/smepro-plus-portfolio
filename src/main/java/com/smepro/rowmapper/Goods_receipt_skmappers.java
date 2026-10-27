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
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class Goods_receipt_skmappers implements RowMapper<Goods_receipt_sk> {

	@Override
	public  Goods_receipt_sk mapRow(ResultSet rs, int rowNum) throws SQLException {
		Goods_receipt_sk  action = new  Goods_receipt_sk();
		 
			  
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
		 if (hasColumn(rs, "skg_inspection_status")) {
			 action.setStatus(rs.getString("skg_inspection_status"));
	        }  

		 if (hasColumn(rs, "count")) {
			 action.setCount(rs.getInt("count"));
	        }  

		 if (hasColumn(rs, "skg_received_date")) {
			 action.setSkg_received_date(rs.getString("skg_received_date"));
	        }  

		 if (hasColumn(rs, "skg_expiration_date")) {
			 action.setSkg_expiration_date(rs.getString("skg_expiration_date"));
	        }  

		 if (hasColumn(rs, "skg_storage_location")) {
			 action.setSkg_storage_location(rs.getString("skg_storage_location"));
	        }  

		 if (hasColumn(rs, "skg_received_by")) {
			 action.setSkg_received_by(rs.getString("skg_received_by"));
	        }  

		 if (hasColumn(rs, "skg_remark")) {
			 action.setSkg_remark(rs.getString("skg_remark"));
	        }  

		 if (hasColumn(rs, "skg_pd_code")) {
			 action.setSkg_pd_code(rs.getString("skg_pd_code"));
	        }  
		 if (hasColumn(rs, "skg_lot_batch")) {
			 action.setSkg_lot_batch(rs.getString("skg_lot_batch"));
	        }  
		 if (hasColumn(rs, "skg_tracking_number")) {
			 action.setSkg_tracking_number(rs.getString("skg_tracking_number"));
	        }  


		 if (hasColumn(rs, "skg_damage_report")) {
			 action.setSkg_damage_report(rs.getString("skg_damage_report"));
	        }  

		 if (hasColumn(rs, "skg_supplier_code")) {
			 action.setSkg_supplier_code(rs.getString("skg_supplier_code"));
	        }  
		 
		 
		 if (hasColumn(rs, "skg_supplier_name")) {
			 action.setSkg_supplier_name(rs.getString("skg_supplier_name"));
	        }  

		 if (hasColumn(rs, "skg_quantity_received")) {
			 action.setSkg_quantity_received(rs.getDouble("skg_quantity_received"));
	        }  

		 if (hasColumn(rs, "skg_unit")) {
			 action.setSkg_unit(rs.getString("skg_unit"));
	        }  
		 if (hasColumn(rs, "skg_price")) {
			 action.setSkg_price(rs.getDouble("skg_price"));
	        }  
		 if (hasColumn(rs, "skg_stock_code")) {
			 action.setSkg_stock_code(rs.getString("skg_stock_code"));
	        }  
		 if (hasColumn(rs, "skg_stock_name")) {
			 action.setSkg_stock_name(rs.getString("skg_stock_name"));
	        }  
		 if (hasColumn(rs, "skg_remaining")) {
			 action.setSkg_remaining(rs.getDouble("skg_remaining"));
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
