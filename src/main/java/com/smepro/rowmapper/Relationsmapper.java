package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Relations;
import com.smepro.models.condition;


public class Relationsmapper implements RowMapper<Relations> {

	@Override
	public 	Relations mapRow(ResultSet rs, int rowNum) throws SQLException {
		Relations data = new 	Relations();
		data.setId(rs.getInt("id"));
		data.setMain_id(rs.getInt("main_id"));
		data.setRelations_id(rs.getInt("relations_id"));
		data.setMain_doc_code(rs.getString("main_doc_code"));
		data.setRelations_doc_code(rs.getString("relations_doc_code"));
		data.setMain_doc_name(rs.getString("main_doc_name"));
		data.setRelations_doc_name(rs.getString("relations_doc_name"));
		data.setProfile_code(rs.getString("profile_code"));
		data.setProfile_name(rs.getString("profile_name"));
		data.setTree(rs.getInt("tree"));
		
		 if (hasColumn(rs, "process_id")) {
			 data.setProcesses_id(rs.getInt("process_id"));
	        }
		 if (hasColumn(rs, "closing_status")) {
			 data.setClosing_status(rs.getString("closing_status"));
	        }
		 
		 
		 
		return data;
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

