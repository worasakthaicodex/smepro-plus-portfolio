package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Fg;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class Issue_skmappers implements RowMapper<Issue_sk> {

	@Override
	public  Issue_sk mapRow(ResultSet rs, int rowNum) throws SQLException {
		Issue_sk  action = new  Issue_sk();
		 
			  
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



		 
		 if (hasColumn(rs, "ski_date")) {
			 action.setSki_date(rs.getString("ski_date"));
	        }  

		 if (hasColumn(rs, "ski_type")) {
			 action.setSki_type(rs.getString("ski_type"));
	        }  

		 if (hasColumn(rs, "ski_destination")) {
			 action.setSki_destination(rs.getString("ski_destination"));
	        }  

		 if (hasColumn(rs, "ski_by")) {
			 action.setSki_by(rs.getString("ski_by"));
	        }  

		 if (hasColumn(rs, "ski_approved_by")) {
			 action.setSki_approved_by(rs.getString("ski_approved_by"));
	        }  

		 if (hasColumn(rs, "ski_remark")) {
			 action.setSki_remark(rs.getString("ski_remark"));
	        }  

		 if (hasColumn(rs, "ski_pd_code")) {
			 action.setSki_pd_code(rs.getString("ski_pd_code"));
	        }  

		 if (hasColumn(rs, "ski_lot_batch")) {
			 action.setSki_lot_batch(rs.getString("ski_lot_batch"));
	        }  
		 if (hasColumn(rs, "ski_lot_batch2")) {
			 action.setSki_lot_batch2(rs.getString("ski_lot_batch2"));
	        }  
		 if (hasColumn(rs, "ski_stock_code")) {
			 action.setSki_stock_code(rs.getString("ski_stock_code"));
	        }  

		 if (hasColumn(rs, "ski_stock_name")) {
			 action.setSki_stock_name(rs.getString("ski_stock_name"));
	        }  

		 if (hasColumn(rs, "ski_tracking_number")) {
			 action.setSki_tracking_number(rs.getString("ski_tracking_number"));
	        }  

		 if (hasColumn(rs, "ski_supplier_code")) {
			 action.setSki_supplier_code(rs.getString("ski_supplier_code"));
	        }  

		 if (hasColumn(rs, "ski_quantity")) {
			 action.setSki_quantity(rs.getDouble("ski_quantity"));
	        }  
		 if (hasColumn(rs, "ski_withdrawal")) {
			 action.setSki_withdrawal(rs.getDouble("ski_withdrawal"));
	        }  
		 if (hasColumn(rs, "ski_unit")) {
			 action.setSki_unit(rs.getString("ski_unit"));
	        }  
		 
		 if (hasColumn(rs, "ski_remaining")) {
			 action.setSki_remaining(rs.getDouble("ski_remaining"));
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
