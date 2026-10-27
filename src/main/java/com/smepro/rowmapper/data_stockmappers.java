package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_stock;
import com.smepro.models.file;


public class data_stockmappers implements RowMapper<edata_stock> {

	@Override
	public  edata_stock mapRow(ResultSet rs, int rowNum) throws SQLException {
		edata_stock  stock = new  edata_stock();
		 
		 
		 if (hasColumn(rs, "stock_id")) {
			 stock.setStock_id(rs.getInt("stock_id"));
	        }
		  if (hasColumn(rs, "stock_proceedings")) {
			  stock.setStock_proceedings(rs.getString("stock_proceedings"));
	        } 
		  if (hasColumn(rs, "edata_id")) {
			  stock.setEdata_id(rs.getInt("edata_id"));
	        } 
		  if (hasColumn(rs, "stock_list")) {
			  stock.setStock_list(rs.getString("stock_list"));
	        } 
		  if (hasColumn(rs, "stock_note")) {
			  stock.setStock_note(rs.getString("stock_note"));
	        } 
		  if (hasColumn(rs, "stock_unit")) {
			  stock.setStock_unit(rs.getString("stock_unit"));
	        } 
		  if (hasColumn(rs, "stock_type")) {
			  stock.setStock_type(rs.getString("stock_type"));
	        } 
		  if (hasColumn(rs, "stock_storage")) {
			  stock.setStock_storage(rs.getString("stock_storage"));
	        } 
		  
		  
		  
		  if (hasColumn(rs, "stock_date")) {
			  stock.setStock_date(rs.getString("stock_date"));
	        } 
		  if (hasColumn(rs, "stock_pd")) {
			  stock.setStock_pd(rs.getString("stock_pd"));
	        } 
		  if (hasColumn(rs, "stock_batch")) {
			  stock.setStock_batch(rs.getString("stock_batch"));
	        } 
		  if (hasColumn(rs, "stock_lot")) {
			  stock.setStock_lot(rs.getString("stock_lot"));
	        } 
		  if (hasColumn(rs, "stock_serial_number")) {
			  stock.setStock_serial_number(rs.getString("stock_serial_number"));
	        } 
		  if (hasColumn(rs, "stock_status")) {
			  stock.setStock_status(rs.getString("stock_status"));
	        } 
		  if (hasColumn(rs, "stock_stockid")) {
			  stock.setStock_stockid(rs.getInt("stock_stockid"));
	        } 
		  if (hasColumn(rs, "stock_received")) {
			  stock.setStock_received(rs.getString("stock_received"));
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
	  
		  if (hasColumn(rs, "stock_support_dataflow")) {
			  stock.setStock_support_dataflow(rs.getString("stock_support_dataflow"));
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
