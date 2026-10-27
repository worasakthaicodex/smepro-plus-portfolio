package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.ExtensionParameter;
import com.smepro.models.Management;
import com.smepro.models.TabManagement;
import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class TabManagementmapper implements RowMapper<TabManagement> {

	@Override
	public TabManagement mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TabManagement data = new TabManagement();

		 
		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "tab_name")) {
			 data.setTab_name(rs.getString("tab_name"));
	        } 
		 
		 if (hasColumn(rs, "tab_count")) {
			 data.setTab_count(rs.getInt("tab_count"));
	        } 
		 
		 if (hasColumn(rs, "tab_type_view")) {
			 data.setTab_type_view(rs.getInt("tab_type_view"));
	        } 
		 
		 if (hasColumn(rs, "tab_columns")) {
			 data.setTab_columns(rs.getString("tab_columns"));
	        }
		 if (hasColumn(rs, "tab_table")) {
			 data.setTab_table(rs.getString("tab_table"));
	        }
		 if (hasColumn(rs, "tab_maintable")) {
			 data.setTab_maintable(rs.getString("tab_maintable"));
	        }
		 
		 if (hasColumn(rs, "tab_child")) {
			 data.setTab_child(rs.getInt("tab_child"));
	        }
		 if (hasColumn(rs, "tab_process_id")) {
			 data.setTab_process_id(rs.getInt("tab_process_id"));
	        }
		 if (hasColumn(rs, "tab_management_id")) {
			 data.setTab_management_id(rs.getInt("tab_management_id"));
	        }
		  
		 
		 if (hasColumn(rs, "tab_current_step_int")) {
			 data.setTab_current_step_int(rs.getInt("tab_current_step_int"));
	        }
		 if (hasColumn(rs, "tab_closing_status")) {
			 data.setTab_closing_status(rs.getString("tab_closing_status"));
	        }
		 if (hasColumn(rs, "tab_current_step")) {
			 data.setTab_current_step(rs.getString("tab_current_step"));
	        }

		 if (hasColumn(rs, "shortcut_profile_code")) {
			 data.setShortcut_profile_code(rs.getString("shortcut_profile_code"));
	        }
		 if (hasColumn(rs, "shortcut_profile_app")) {
			 data.setShortcut_profile_app(rs.getInt("shortcut_profile_app"));
	        }
		 
		 if (hasColumn(rs, "shortcut_profile_linkname")) {
			 data.setShortcut_profile_linkname(rs.getString("shortcut_profile_linkname"));
	        }
		 
		 
		 
		 if (hasColumn(rs, "shortcut_newdoc_code")) {
			 data.setShortcut_newdoc_code(rs.getString("shortcut_newdoc_code"));
	        }
		 
		 if (hasColumn(rs, "shortcut_newdoc_linkname")) {
			 data.setShortcut_newdoc_linkname(rs.getString("shortcut_newdoc_linkname"));
	        }
		 
		 if (hasColumn(rs, "shortcut_newdoc_change_closing_status")) {
			 data.setShortcut_newdoc_change_closing_status(rs.getString("shortcut_newdoc_change_closing_status"));
	        }
		 
		
		 
		 if (hasColumn(rs, "shortcut_change_closing_status")) {
			 data.setShortcut_change_closing_status(rs.getString("shortcut_change_closing_status"));
	        }
		 
		
		 if (hasColumn(rs, "shortcut_change_closing_linkname")) {
			 data.setShortcut_change_closing_linkname(rs.getString("shortcut_change_closing_linkname"));
	        }
		 
		 
		 
		 if (hasColumn(rs, "shortcut_segment_linkname")) {
			 data.setShortcut_segment_linkname(rs.getString("shortcut_segment_linkname"));
	        }
		 if (hasColumn(rs, "shortcut_segment_code")) {
			 data.setShortcut_segment_code(rs.getString("shortcut_segment_code"));
	        }
		 if (hasColumn(rs, "shortcut_segment_column")) {
			 data.setShortcut_segment_column(rs.getString("shortcut_segment_column"));
	        }
		 
		 if (hasColumn(rs, "acknowledge")) {
			 data.setAcknowledge(rs.getInt("acknowledge"));
	        }
		 if (hasColumn(rs, "followup")) {
			 data.setFollowup(rs.getInt("followup"));
	        }
		 if (hasColumn(rs, "followup_plan")) {
			 data.setFollowup_plan(rs.getInt("followup_plan"));
	        }
		 
		 
		 if (hasColumn(rs, "grouping_row")) {
			 data.setGrouping_row(rs.getInt("grouping_row"));
	        }
		 if (hasColumn(rs, "grouping_row_check")) {
			 data.setGrouping_row_check(rs.getInt("grouping_row_check"));
	        }
		 if (hasColumn(rs, "grouping_row_date")) {
			 data.setGrouping_row_date(rs.getInt("grouping_row_date"));
	        }

		 if (hasColumn(rs, "tab_current_step2")) {
			 data.setTab_current_step2(rs.getString("tab_current_step2"));
	        }
		 if (hasColumn(rs, "tab_closing_status2")) {
			 data.setTab_closing_status2(rs.getString("tab_closing_status2"));
	        }
		 if (hasColumn(rs, "acknowledge2")) {
			 data.setAcknowledge2(rs.getInt("acknowledge2"));
	        }
		 if (hasColumn(rs, "acknowledge_user")) {
			 data.setAcknowledge_user(rs.getInt("acknowledge_user"));
	        }
		 if (hasColumn(rs, "acknowledge_user2")) {
			 data.setAcknowledge_user2(rs.getInt("acknowledge_user2"));
	        }
		 if (hasColumn(rs, "expired")) {
			 data.setExpired(rs.getInt("expired"));
	        }
		 
		 
		 if (hasColumn(rs, "request_to_edit")) {
			 data.setRequest_to_edit(rs.getInt("request_to_edit"));
	        }
		 if (hasColumn(rs, "request_to_edit2")) {
			 data.setRequest_to_edit2(rs.getInt("request_to_edit2"));
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
