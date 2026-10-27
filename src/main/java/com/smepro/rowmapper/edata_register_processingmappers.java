package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_register_processing;
import com.smepro.models.edata_stock;
import com.smepro.models.file;


public class edata_register_processingmappers implements RowMapper<edata_register_processing> {

	@Override
	public  edata_register_processing mapRow(ResultSet rs, int rowNum) throws SQLException {
		edata_register_processing  action = new  edata_register_processing();
		 
		 
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }
		 
		 if (hasColumn(rs, "edata_id")) {
			 action.setEdata_id(rs.getInt("edata_id"));
	        }

		 if (hasColumn(rs, "supplier_id")) {
			 action.setSupplier_id(rs.getString("supplier_id"));
	        } 
		 if (hasColumn(rs, "supplier_name")) {
			 action.setSupplier_name(rs.getString("supplier_name"));
	        } 
		 if (hasColumn(rs, "doc_refer")) {
			 action.setDoc_refer(rs.getString("doc_refer"));
	        } 
		 if (hasColumn(rs, "quality")) {
			 action.setQuality(rs.getString("quality"));
	        } 
		 if (hasColumn(rs, "value")) {
			 action.setValue(rs.getString("value"));
	        } 
		 
		 if (hasColumn(rs, "delivery")) {
			 action.setDelivery(rs.getString("delivery"));
	        } 
		 
		 if (hasColumn(rs, "support")) {
			 action.setSupport(rs.getString("support"));
	        } 
		 
		 if (hasColumn(rs, "reliability")) {
			 action.setReliability(rs.getString("reliability"));
	        } 
		 
		 if (hasColumn(rs, "flexibility")) {
			 action.setFlexibility(rs.getString("flexibility"));
	        } 
		 
		 if (hasColumn(rs, "user_experience")) {
			 action.setUser_experience(rs.getString("user_experience"));
	        } 
		 if (hasColumn(rs, "sustainability")) {
			 action.setSustainability(rs.getString("sustainability"));
	        } 
		 if (hasColumn(rs, "partnership")) {
			 action.setPartnership(rs.getString("partnership"));
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
