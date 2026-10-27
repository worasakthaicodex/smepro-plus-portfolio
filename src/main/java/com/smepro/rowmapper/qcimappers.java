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
import com.smepro.models.qci;




public class qcimappers implements RowMapper<qci> {

	@Override
	public  qci mapRow(ResultSet rs, int rowNum) throws SQLException {
		qci  qcis = new  qci();
		 
		 
		 if (hasColumn(rs, "id")) {
			 qcis.setId(rs.getInt("id"));
	        }
		  if (hasColumn(rs, "edata_id")) {
			  qcis.setEdata_id(rs.getInt("edata_id"));
	        }
		  if (hasColumn(rs, "qci_code")) {
			  qcis.setQci_code(rs.getString("qci_code"));
	        } 
		  if (hasColumn(rs, "product_code")) {
			  qcis.setProduct_code(rs.getString("product_code"));
	        } 
		  if (hasColumn(rs, "product_name")) {
			  qcis.setProduct_name(rs.getString("product_name"));
	        } 
		  if (hasColumn(rs, "process_step")) {
			  qcis.setProcess_step(rs.getString("process_step"));
	        } 
		  if (hasColumn(rs, "quantity")) {
			  qcis.setQuantity(rs.getString("quantity"));
	        } 
		  if (hasColumn(rs, "start_date_time")) {
			  qcis.setStart_date_time(rs.getString("start_date_time"));
	        } 
		  if (hasColumn(rs, "inspection")) {
			  qcis.setInspection(rs.getString("inspection"));
	        } 
		  if (hasColumn(rs, "note")) {
			  qcis.setNote(rs.getString("note"));
	        } 

		  
		  if (hasColumn(rs, "results_string1")) {
			  qcis.setResults_string1(rs.getString("results_string1"));
	        } 
		  if (hasColumn(rs, "results_string2")) {
			  qcis.setResults_string2(rs.getString("results_string2"));
	        }   
		  if (hasColumn(rs, "results_string3")) {
			  qcis.setResults_string3(rs.getString("results_string3"));
	        } 
		  if (hasColumn(rs, "results_string4")) {
			  qcis.setResults_string4(rs.getString("results_string4"));
	        } 
		  if (hasColumn(rs, "results_string5")) {
			  qcis.setResults_string5(rs.getString("results_string5"));
	        } 
		  
		  
		  if (hasColumn(rs, "results_number1")) {
			  qcis.setResults_number1(rs.getString("results_number1"));
	        } 
		  if (hasColumn(rs, "results_string2")) {
			  qcis.setResults_number2(rs.getString("results_number2"));
	        }   
		  if (hasColumn(rs, "results_string3")) {
			  qcis.setResults_number3(rs.getString("results_number3"));
	        } 
		  if (hasColumn(rs, "results_string4")) {
			  qcis.setResults_number4(rs.getString("results_number4"));
	        } 
		  if (hasColumn(rs, "resultsstring5")) {
			  qcis.setResults_number5(rs.getString("results_number5"));
	        } 
		  
		  if (hasColumn(rs, "count")) {
			  qcis.setCount(rs.getInt("count"));
	        } 
		  
		  
		  if (hasColumn(rs, "stock_list")) {
			  qcis.setStock_list(rs.getString("stock_list"));
	        } 
		  if (hasColumn(rs, "stock_list")) {
			  qcis.setStock_code(rs.getString("stock_code"));
	        } 
		  
		return qcis;
	
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
