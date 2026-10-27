package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Fg;
import com.smepro.models.Issue_fg;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class Issue_fgmappers implements RowMapper<Issue_fg> {

	@Override
	public  Issue_fg mapRow(ResultSet rs, int rowNum) throws SQLException {
		Issue_fg  action = new  Issue_fg();
		 
			  
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

		 
		 if (hasColumn(rs, "issue_date")) {
			 action.setIssue_date(rs.getString("issue_date"));
	        }  

		 if (hasColumn(rs, "issue_type")) {
			 action.setIssue_type(rs.getString("issue_type"));
	        }  

		 if (hasColumn(rs, "issue_destination")) {
			 action.setIssue_destination(rs.getString("issue_destination"));
	        }  

		 if (hasColumn(rs, "issued_by")) {
			 action.setIssued_by(rs.getString("issued_by"));
	        }  

		 if (hasColumn(rs, "approved_by")) {
			 action.setApproved_by(rs.getString("approved_by"));
	        }  

		 if (hasColumn(rs, "issued_remark")) {
			 action.setIssued_remark(rs.getString("issued_remark"));
	        }  

		 if (hasColumn(rs, "approved_by")) {
			 action.setApproved_by(rs.getString("approved_by"));
	        }  

		 if (hasColumn(rs, "issued_pd_code")) {
			 action.setIssued_pd_code(rs.getString("issued_pd_code"));
	        }  

		 if (hasColumn(rs, "issued_lot_batch")) {
			 action.setIssued_lot_batch(rs.getString("issued_lot_batch"));
	        }  

		 if (hasColumn(rs, "issued_product_code")) {
			 action.setIssued_product_code(rs.getString("issued_product_code"));
	        }  

		 if (hasColumn(rs, "issued_lot_batch")) {
			 action.setIssued_lot_batch(rs.getString("issued_lot_batch"));
	        }  

		 if (hasColumn(rs, "issued_product_name")) {
			 action.setIssued_product_name(rs.getString("issued_product_name"));
	        }  

		 if (hasColumn(rs, "issued_quantity")) {
			 action.setIssued_quantity(rs.getDouble("issued_quantity"));
	        }  

		 if (hasColumn(rs, "issued_unit")) {
			 action.setIssued_unit(rs.getString("issued_unit"));
	        }  
		 
		 if (hasColumn(rs, "issued_customer_code")) {
			 action.setIssued_customer_code(rs.getString("issued_customer_code"));
	        } 
		 if (hasColumn(rs, "issued_customer_name")) {
			 action.setIssued_customer_name(rs.getString("issued_customer_name"));
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
