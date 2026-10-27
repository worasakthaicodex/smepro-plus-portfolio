package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class UseExtensionmapper implements RowMapper<UseExtension> {

	@Override
	public UseExtension mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UseExtension data = new UseExtension();

		 
		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "name")) {
			 data.setName(rs.getString("name"));
	        } 
		 
		 if (hasColumn(rs, "access_user")) {
			 data.setAccess_user(rs.getString("access_user"));
	        } 
		 
		 if (hasColumn(rs, "access_admin")) {
			 data.setAccess_admin(rs.getString("access_admin"));
	        } 
		 
		 if (hasColumn(rs, "extension_id")) {
			 data.setExtension_id(rs.getInt("extension_id"));
	        }
		 if (hasColumn(rs, "process_id")) {
			 data.setProcess_id(rs.getInt("process_id"));
	        }
		 if (hasColumn(rs, "eform_id")) {
			 data.setEform_id(rs.getInt("eform_id"));
	        }
		 if (hasColumn(rs, "use_process_id")) {
			 data.setUse_process_id(rs.getInt("use_process_id"));
	        }
	
		 if (hasColumn(rs, "auto_update_parameter_column")) {
			 data.setAuto_update_parameter_column(rs.getString("auto_update_parameter_column"));
	        }
		 if (hasColumn(rs, "auto_update_tocolumn")) {
			 data.setAuto_update_tocolumn(rs.getString("auto_update_tocolumn"));
	        }
		 if (hasColumn(rs, "auto_update_segment")) {
			 data.setAuto_update_segment(rs.getInt("auto_update_segment"));
	        }
		 if (hasColumn(rs, "icon")) {
			 data.setIcon(rs.getString("icon"));
	        }
		 if (hasColumn(rs, "segment_count")) {
			 data.setSegment_count(rs.getInt("segment_count"));
	        }
		 
		 if (hasColumn(rs, "segment_issue_column")) {
			 data.setSegment_issue_column(rs.getString("segment_issue_column"));
	        }
		 if (hasColumn(rs, "segment_issue_count")) {
			 data.setSegment_issue_count(rs.getInt("segment_issue_count"));
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
