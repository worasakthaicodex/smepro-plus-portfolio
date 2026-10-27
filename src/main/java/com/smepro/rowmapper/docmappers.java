package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.purchase;
import com.smepro.models.stock;


public class docmappers implements RowMapper<doc> {

	@Override
	public  doc mapRow(ResultSet rs, int rowNum) throws SQLException {
		doc  docs = new  doc();
		 
		 
		 if (hasColumn(rs, "doc_id")) {
			 docs.setDoc_id(rs.getInt("doc_id"));
	        }
		 if (hasColumn(rs, "doc_date")) {
			 docs.setDoc_date(rs.getString("doc_date"));
	        } 
		 if (hasColumn(rs, "doc_idrefer")) {
			 docs.setDoc_idrefer(rs.getInt("doc_idrefer"));
	        }
		 if (hasColumn(rs, "doc_type")) {
			 docs.setDoc_type(rs.getString("doc_type"));
	        } 
		 if (hasColumn(rs, "doc_autoid")) {
			 docs.setDoc_autoid(rs.getInt("doc_autoid"));
	        } 
		 if (hasColumn(rs, "doc_rv")) {
			 docs.setDoc_rv(rs.getInt("doc_rv"));
	        } 
		return docs;
	
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
