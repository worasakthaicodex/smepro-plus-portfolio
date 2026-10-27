package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.SubData;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.purchase;


public class subdatamappers implements RowMapper<SubData> {

	
	@Override
	public  SubData mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubData  SubData = new  SubData();
		 
		 
		 if (hasColumn(rs, "id")) {
			 SubData.setId(rs.getString("id"));
	        }
		 if (hasColumn(rs, "id_refer")) {
			 SubData.setTempId(rs.getString("id_refer"));
			 SubData.setId_refer(rs.getInt("id_refer"));
	        }
		 if (hasColumn(rs, "type")) {
			 SubData.setDatalink_name(rs.getString("type"));
			 SubData.setType(rs.getString("type"));
	        } 
		 if (hasColumn(rs, "description")) {
			 SubData.setDescription(rs.getString("description"));
	        }  
		 if (hasColumn(rs, "qty")) {
			 SubData.setQty(rs.getString("qty"));
	        } 
		 if (hasColumn(rs, "unit")) {
			 SubData.setUnit(rs.getString("unit"));
	        } 
		 if (hasColumn(rs, "actual_price")) {
			 SubData.setActual_price(rs.getString("actual_price"));
	        } 	 
		 if (hasColumn(rs, "received_qty")) {
			 SubData.setReceived_qty(rs.getString("received_qty"));
	        } 	 
		
		 if (hasColumn(rs, "issued_qty")) {
			 SubData.setIssued_qty(rs.getString("issued_qty"));
	        } 	
		 if (hasColumn(rs, "issued_amount")) {
			 SubData.setIssued_amount(rs.getString("issued_amount"));
	        } 	
		 if (hasColumn(rs, "status")) {
			 SubData.setStatus(rs.getString("status"));
	        } 
		 if (hasColumn(rs, "id_cleared_by")) {
			 SubData.setId_cleared_by(rs.getInt("id_cleared_by"));
	        } 
		 
		 if (hasColumn(rs, "id_stock")) {
			 SubData.setId_stock(rs.getInt("id_stock"));
	        } 	 
		 if (hasColumn(rs, "pd")) {
			 SubData.setPd(rs.getString("pd"));
	        } 	 
		 if (hasColumn(rs, "batch")) {
			 SubData.setBatch(rs.getString("batch"));
	        } 	 
		 
		 if (hasColumn(rs, "date_action")) {
			 SubData.setDate_action(rs.getString("date_action"));
	        } 	 
		 
		 if (hasColumn(rs, "requester")) {
			 SubData.setRequester(rs.getString("requester"));
	        } 	
		 
		 
		 
		return SubData;
	
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
