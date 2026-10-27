package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.cm;
import com.smepro.models.document_no;

public class document_nomapper implements RowMapper<document_no> {

	@Override
	public document_no mapRow(ResultSet rs, int rowNum) throws SQLException {
		document_no document_nos = new document_no();
		document_nos.setDocument_no_id(rs.getInt("document_no_id"));
		document_nos.setDocument_no_pattern(rs.getString("document_no_pattern"));
		document_nos.setDocument_no_note(rs.getString("document_no_note"));
		document_nos.setAutoid(rs.getInt("autoid"));
		
		document_nos.setDocument_department(rs.getInt("document_department"));
		document_nos.setDocument_doc(rs.getString("document_doc"));
		

		 if (hasColumn(rs, "department")) {
			 document_nos.setDepartment(rs.getString("department"));
	        }
		return document_nos;
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
