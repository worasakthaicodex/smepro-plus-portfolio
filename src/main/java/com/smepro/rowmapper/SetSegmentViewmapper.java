package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.ExtensionParameter;
import com.smepro.models.Management;
import com.smepro.models.Segmentation;
import com.smepro.models.SetSegmentView;
import com.smepro.models.TabManagement;
import com.smepro.models.Tabform;
import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class SetSegmentViewmapper implements RowMapper<SetSegmentView> {

	@Override
	public SetSegmentView mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		SetSegmentView data = new SetSegmentView();

		 
		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "process_id")) {
			 data.setProcess_id(rs.getInt("process_id"));
	        }
		 if (hasColumn(rs, "id_add")) {
			 data.setId_add(rs.getInt("id_add"));
	        }
		 if (hasColumn(rs, "segment_view")) {
			 data.setSegmentView(rs.getInt("segment_view"));
	        }
		 
		 if (hasColumn(rs, "segment_status")) {
			 data.setSegmentStatus(rs.getString("segment_status"));
	        } 
		 if (hasColumn(rs, "query_file")) {
			 data.setQuery_file(rs.getString("query_file"));
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
