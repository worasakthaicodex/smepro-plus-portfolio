package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.ExtensionParameter;
import com.smepro.models.Management;
import com.smepro.models.Segmentation;
import com.smepro.models.TabManagement;
import com.smepro.models.Tabform;
import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class SegmentationManagementmapper implements RowMapper<Segmentation> {

	@Override
	public Segmentation mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Segmentation data = new Segmentation();

		 
		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "name")) {
			 data.setName(rs.getString("name"));
	        } 
		 
		 if (hasColumn(rs, "status")) {
			 data.setStatus(rs.getInt("status"));
	        } 
		 
		 if (hasColumn(rs, "date_add")) {
			 data.setDate_add(rs.getString("date_add"));
	        } 
		 
		 if (hasColumn(rs, "date_edit")) {
			 data.setDate_edit(rs.getString("date_edit"));
	        }
		
		 if (hasColumn(rs, "id_add")) {
			 data.setId_add(rs.getInt("id_add"));
	        }
		
		 if (hasColumn(rs, "id_edit")) {
			 data.setId_edit(rs.getInt("id_edit"));
	        }
		 if (hasColumn(rs, "type")) {
			 data.setType(rs.getInt("type"));
	        }
		 if (hasColumn(rs, "total_data")) {
			 data.setTotal_data(rs.getInt("total_data"));
	        }
		 if (hasColumn(rs, "total_data_select")) {
			 data.setTotal_data_select(rs.getInt("total_data_select"));
	        }
		 
		 
		 
		 if (hasColumn(rs, "diff_data")) {
			 data.setDiff_data(rs.getInt("diff_data"));
	        }
		 if (hasColumn(rs, "total_same")) {
			 data.setTotal_same(rs.getInt("total_same"));
	        }
		 if (hasColumn(rs, "process_id")) {
			 data.setProcess_id(rs.getInt("process_id"));
	        }
		 if (hasColumn(rs, "segmentation_member")) {
			 data.setSegmentation_member(rs.getString("segmentation_member"));
	        }
		 if (hasColumn(rs, "segmentation_member_id")) {
			 data.setSegmentation_member_id(rs.getString("segmentation_member_id"));
	        }
		 	 	 
		 if (hasColumn(rs, "user_name")) {
			 data.setUser_add(rs.getString("user_name"));
	        }
		 if (hasColumn(rs, "call_total")) {
			 data.setCall_total(rs.getInt("call_total"));
	        }
		 if (hasColumn(rs, "call_unique_count")) {
			 data.setCall_unique_count(rs.getInt("call_unique_count"));
	        }
		 
		 if (hasColumn(rs, "promotion")) {
			 data.setPromotion(rs.getInt("promotion"));
	        }
		 if (hasColumn(rs, "Summary_data")) {
			 data.setSummary_data(rs.getString("summary_data"));
	        }	 
		 
		 if (hasColumn(rs, "segmentation_select")) {
			 data.setSegmentation_select(rs.getString("segmentation_select"));
	        }	 
		 
		 
		 data.setEdata_id_reserve(rs.getInt("edata_id_reserve"));
		 
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
