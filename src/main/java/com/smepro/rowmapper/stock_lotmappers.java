package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.purchase;
import com.smepro.models.stock;
import com.smepro.models.stock_lot;


public class stock_lotmappers implements RowMapper<stock_lot> {

	@Override
	public  stock_lot mapRow(ResultSet rs, int rowNum) throws SQLException {
		stock_lot  stock = new  stock_lot();
		 
		 
		 if (hasColumn(rs, "stock_id")) {
			 stock.setStock_id(rs.getInt("stock_id"));
	        }
		 if (hasColumn(rs, "stock_code")) {
			 stock.setStock_code(rs.getString("stock_code"));
	        } 
		 if (hasColumn(rs, "stock_name")) {
			 stock.setStock_name(rs.getString("stock_name"));
	        }
		 if (hasColumn(rs, "supplier_code")) {
			 stock.setSupplier_code(rs.getString("supplier_code"));
	        } 
	
		 if (hasColumn(rs, "supplier_name")) {
			 stock.setSupplier_name(rs.getString("supplier_name"));
	        } 
		 if (hasColumn(rs, "lot_batch")) {
			 stock.setLot_batch(rs.getString("lot_batch"));
	        } 
		 if (hasColumn(rs, "stock_remaining")) {
			 stock.setStock_remaining(rs.getDouble("stock_remaining"));
	        } 
		 if (hasColumn(rs, "stock_storage")) {
			 stock.setStock_storage(rs.getString("stock_storage"));
	        } 
		 if (hasColumn(rs, "price")) {
			 stock.setPrice(rs.getString("price"));
	        } 
		 
		 if (hasColumn(rs, "stock_status")) {
			 stock.setStock_status(rs.getString("stock_status"));
	        } 
		 
		 if (hasColumn(rs, "tracking_number")) {
			 stock.setTracking_number(rs.getString("tracking_number"));
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
