package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.purchase;
import com.smepro.models.stock;


public class stockmappers implements RowMapper<stock> {

	@Override
	public  stock mapRow(ResultSet rs, int rowNum) throws SQLException {
		stock  stock = new  stock();
		 
		 
		 if (hasColumn(rs, "id")) {
			 stock.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "stock_status")) {
			 stock.setStock_status(rs.getString("stock_status"));
	        } 
	
		 if (hasColumn(rs, "stock_group")) {
			 stock.setStock_group(rs.getString("stock_group"));
	        } 
		 
		 if (hasColumn(rs, "stock_type")) {
			 stock.setStock_type(rs.getString("stock_type"));
	        }
		 if (hasColumn(rs, "stock_price")) {
			 stock.setStock_price(rs.getString("stock_price"));
	        } 
		 
	
		 if (hasColumn(rs, "ir_idrefer")) {
			 stock.setIr_idrefer(rs.getInt("ir_idrefer"));
	        } 
		 if (hasColumn(rs, "stock_id")) {
			 stock.setStock_id(rs.getInt("stock_id"));
	        } 
		 if (hasColumn(rs, "stock_details")) {
			 stock.setStock_details(rs.getString("stock_details"));
	        } 
		 if (hasColumn(rs, "stock_remaining")) {
			 stock.setStock_remaining(rs.getString("stock_remaining"));
	        } 
		 if (hasColumn(rs, "stock_price")) {
			 stock.setStock_price(rs.getString("stock_price"));
	        } 
		 if (hasColumn(rs, "stock_receipt_date")) {
			 stock.setStock_receipt_date(rs.getString("stock_receipt_date"));
	        } 
		 if (hasColumn(rs, "stock_list")) {
			 stock.setStock_list(rs.getString("stock_list"));
	        } 
		 if (hasColumn(rs, "stock_received")) {
			 stock.setStock_received(rs.getString("stock_received"));
	        } 
		 if (hasColumn(rs, "stock_type")) {
			 stock.setStock_type(rs.getString("stock_type"));
	        } 
		 if (hasColumn(rs, "stock_unit")) {
			 stock.setStock_unit(rs.getString("stock_unit"));
	        } 
		 if (hasColumn(rs, "stock_storage")) {
			 stock.setStock_storage(rs.getString("stock_storage"));
	        } 
		 if (hasColumn(rs, "stock_receipt_dateupdate")) {
			 stock.setStock_receipt_dateupdate(rs.getString("stock_receipt_dateupdate"));
	        } 
		 if (hasColumn(rs, "stock_payout")) {
			 stock.setStock_payout(rs.getString("stock_payout"));
	        } 
		 if (hasColumn(rs, "stock_pd")) {
			 stock.setStock_pd(rs.getString("stock_pd"));
	        } 
		 if (hasColumn(rs, "stock_batch")) {
			 stock.setStock_batch(rs.getString("stock_batch"));
	        } 
		 if (hasColumn(rs, "stock_eform")) {
			 stock.setStock_eform(rs.getInt("stock_eform"));
	        } 
		  
		  if (hasColumn(rs, "stock_max")) {
			  stock.setStock_max(rs.getString("stock_max"));
	        } 
		  if (hasColumn(rs, "stock_min")) {
			  stock.setStock_min(rs.getString("stock_min"));
	        } 
	  
		  if (hasColumn(rs, "stock_expdate")) {
			  stock.setStock_expdate(rs.getString("stock_expdate"));
	        } 
		  if (hasColumn(rs, "stock_code")) {
			  stock.setStock_code(rs.getString("stock_code"));
	        } 
	
		  if (hasColumn(rs, "stock_group")) {
			  stock.setStock_group(rs.getString("stock_group"));
	        } 
	
		return stock;
	
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
