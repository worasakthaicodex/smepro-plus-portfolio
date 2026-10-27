package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_product;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;


public class edata_productmappers implements RowMapper<edata_product> {

	@Override
	public  edata_product mapRow(ResultSet rs, int rowNum) throws SQLException {
		edata_product  register = new  edata_product();
		 
		 
		 if (hasColumn(rs, "id")) {
			 register.setId(rs.getInt("id"));
	        }
		  if (hasColumn(rs, "product_proceedings")) {
			  register.setProduct_proceedings(rs.getString("product_proceedings"));
	        } 
		  if (hasColumn(rs, "product_id")) {
			  register.setProduct_id(rs.getInt("product_id"));
	        } 

		  if (hasColumn(rs, "edata_id")) {
			  register.setEdata_id(rs.getInt("edata_id"));
	        } 
	
		  
		  if (hasColumn(rs, "tempId")) {
			  register.setTempId(rs.getString("tempId"));
	        } 
		  if (hasColumn(rs, "productCode")) {
			  register.setProductCode(rs.getString("productCode"));
	        } 
		  if (hasColumn(rs, "product_names")) {
			  register.setProduct_names(rs.getString("product_names"));
	        } 
		  if (hasColumn(rs, "product_category")) {
			  register.setProduct_category(rs.getString("product_category"));
	        } 
		  if (hasColumn(rs, "product_details")) {
			  register.setProduct_details(rs.getString("product_details"));
	        } 
		  if (hasColumn(rs, "unit")) {
			  register.setUnit(rs.getString("unit"));
	        } 
		  if (hasColumn(rs, "created_date")) {
			  register.setCreated_date(rs.getString("created_date"));
	        } 
		  if (hasColumn(rs, "last_updated")) {
			  register.setLast_updated(rs.getString("last_updated"));
	        }
			if (hasColumn(rs, "product_type")) {
				register.setProduct_type(rs.getString("product_type"));
		}   	
			
			if (hasColumn(rs, "product_dimensions")) {
				register.setProduct_dimensions(rs.getString("product_dimensions"));
		}   	
			
			if (hasColumn(rs, "product_expiration_date")) {
				register.setProduct_expiration_date(rs.getString("product_expiration_date"));
		}   	
			
			if (hasColumn(rs, "product_selling_price")) {
				register.setProduct_selling_price(rs.getString("product_selling_price"));
		}   	
			
			if (hasColumn(rs, "product_cost_price")) {
				register.setProduct_cost_price(rs.getString("product_cost_price"));
		}   	
			if (hasColumn(rs, "product_wholesale_price")) {
				register.setProduct_wholesale_price(rs.getString("product_wholesale_price"));
		}   	
		
			if (hasColumn(rs, "product_status")) {
				register.setProduct_status(rs.getString("product_status"));
		}   	
			if (hasColumn(rs, "id_oldsys")) {
				register.setId_oldsys(rs.getString("id_oldsys"));
		}   	
		
			
		  
		return register;
	
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
