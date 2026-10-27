package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.process;


public class processmapper implements RowMapper<process> {

	@Override
	public process mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		process processs = new process();

		 
		 if (hasColumn(rs, "id")) {
			 processs.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "name")) {
			 processs.setName(rs.getString("name"));
	        } 
		 
		 if (hasColumn(rs, "description")) {
			 processs.setDescription(rs.getString("description"));
	        } 
		 
		 if (hasColumn(rs, "relatedpeople")) {
			 processs.setRelatedpeople(rs.getString("relatedpeople"));
	        } 
		 
		 if (hasColumn(rs, "id_refer")) {
			 processs.setId_refer(rs.getInt("id_refer"));
	        }
		 
		 if (hasColumn(rs, "id_condition")) {
			 processs.setId_condition(rs.getInt("id_condition"));
	        }
		 if (hasColumn(rs, "process_join")) {
			 processs.setProcess_join(rs.getInt("process_join"));
	        }
		 if (hasColumn(rs, "reference")) {
			 processs.setReference(rs.getString("reference"));
	        }
	
		 if (hasColumn(rs, "process_logo")) {
			 processs.setProcess_logo(rs.getString("process_logo"));
	        }
		 
		 if (hasColumn(rs, "data_dateupdate")) {
			 processs.setData_dateupdate(rs.getString("data_dateupdate"));
	        }
		 if (hasColumn(rs, "data_status")) {
			 processs.setData_status(rs.getString("data_status"));
	        }
		 if (hasColumn(rs, "visibility")) {
			 processs.setVisibility(rs.getString("visibility"));
	        } 
		 if (hasColumn(rs, "allow_loose_link")) {
			 processs.setAllow_loose_link(rs.getInt("allow_loose_link"));
	        } 
		 
		 
		 if (hasColumn(rs, "link_to")) {
			 processs.setLink_to(rs.getInt("link_to"));
	        } 
		 if (hasColumn(rs, "relationship")) {
			 processs.setRelationship(rs.getString("relationship"));
	        } 
		 
		 
		 if (hasColumn(rs, "outgoing_column1")) {
			 processs.setOutgoing_column1(rs.getInt("outgoing_column1"));
	        } 
		 if (hasColumn(rs, "for_column1")) {
			 processs.setFor_column1(rs.getInt("for_column1"));
	        } 
		 if (hasColumn(rs, "feed_type1")) {
			 processs.setFeed_type1(rs.getString("feed_type1"));
	        } 
		 
		 
		 if (hasColumn(rs, "outgoing_column2")) {
			 processs.setOutgoing_column2(rs.getInt("outgoing_column2"));
	        } 
		 if (hasColumn(rs, "for_column2")) {
			 processs.setFor_column2(rs.getInt("for_column2"));
	        } 
		 if (hasColumn(rs, "feed_type2")) {
			 processs.setFeed_type2(rs.getString("feed_type2"));
	        } 
		 
		 
		 if (hasColumn(rs, "outgoing_column3")) {
			 processs.setOutgoing_column3(rs.getInt("outgoing_column3"));
	        } 
		 if (hasColumn(rs, "for_column3")) {
			 processs.setFor_column3(rs.getInt("for_column3"));
	        } 
		 if (hasColumn(rs, "feed_type3")) {
			 processs.setFeed_type3(rs.getString("feed_type3"));
	        } 
		 
		 if (hasColumn(rs, "enums")) {
			 processs.setEnums(rs.getString("enums"));
	        } 
	
		 if (hasColumn(rs, "program_type")) {
			 processs.setProgram_type(rs.getInt("program_type"));
	        } 
	
		 
		 
		 
		 if (hasColumn(rs, "register")) {
			 processs.setRegister(rs.getString("register"));
	        } 
	
		 
		 if (hasColumn(rs, "register_type")) {
			 processs.setRegister_type(rs.getString("register_type"));
	        } 
	
		 if (hasColumn(rs, "access_user")) {
			 processs.setAccess_user(rs.getString("access_user"));
	        } 
	
		 if (hasColumn(rs, "access_admin")) {
			 processs.setAccess_admin(rs.getString("access_admin"));
	        } 

		 if (hasColumn(rs, "show_limit")) {
			 processs.setShow_limit(rs.getInt("show_limit"));
	        } 
	
		 if (hasColumn(rs, "listview")) {
			 processs.setListview(rs.getString("listview"));
	        } 
		 if (hasColumn(rs, "listview2")) {
			 processs.setListview2(rs.getString("listview2"));
	        } 
		 if (hasColumn(rs, "tabform")) {
			 processs.setTabform(rs.getInt("tabform"));
	        } 
	
		 if (hasColumn(rs, "closing_status")) {
			 processs.setClosing_status(rs.getString("closing_status"));
	        } 
		 
		 
		 if (hasColumn(rs, "used_valid")) {
			 processs.setUsed_valid(rs.getInt("used_valid"));
	        } 
		 if (hasColumn(rs, "used_attachment")) {
			 processs.setUsed_attachment(rs.getInt("used_attachment"));
	        } 
	
		 
		 if (hasColumn(rs, "data_transfer")) {
			 processs.setData_transfer(rs.getString("data_transfer"));
	        } 
		 if (hasColumn(rs, "integrity")) {
			 processs.setIntegrity(rs.getString("integrity"));
	        } 
		 if (hasColumn(rs, "current_status")) {
			 processs.setCurrent_status(rs.getString("current_status"));
	        } 
		 if (hasColumn(rs, "visibility")) {
			 processs.setVisibility(rs.getString("visibility"));
	        } 
		 if (hasColumn(rs, "subtable_editmode")) {
			 processs.setSubtable_editmode(rs.getString("subtable_editmode"));
	        } 
		 if (hasColumn(rs, "subtable_viewmode")) {
			 processs.setSubtable_viewmode(rs.getString("subtable_viewmode"));
	        } 
		 
		 
		 if (hasColumn(rs, "canReviseConfirmed")) {
			 processs.setCanReviseConfirmed(rs.getInt("canReviseConfirmed"));
	        } 
		 if (hasColumn(rs, "closingStatusCanRevise")) {
			 processs.setClosingStatusCanRevise(rs.getString("closingStatusCanRevise"));
	        } 
		 if (hasColumn(rs, "closingStatusAfterRevise")) {
			 processs.setClosingStatusAfterRevise(rs.getString("closingStatusAfterRevise"));
	        } 
		 
		 if (hasColumn(rs, "direct_data_edit_mode")) {
			 processs.setDirect_data_edit_mode(rs.getInt("direct_data_edit_mode"));
	        } 
		 
		 
		 if (hasColumn(rs, "requestsystem")) {
			 processs.setRequestsystem(rs.getInt("requestsystem"));
	        } 
		 
		 if (hasColumn(rs, "renamefile")) {
			 processs.setRenamefile(rs.getInt("renamefile"));
	        } 
		 if (hasColumn(rs, "version")) {
			 processs.setVersion(rs.getString("version"));
	        } 
		 
		 if (hasColumn(rs, "use_ex")) {
			 processs.setUse_ex(rs.getString("use_ex"));
	        } 
		 if (hasColumn(rs, "use_index_app")) {
			 processs.setUse_index_app(rs.getInt("use_index_app"));
	        } 
		
		 if (hasColumn(rs, "use_ex_icon")) {
			 processs.setUse_ex_icon(rs.getString("use_ex_icon"));
	        } 
		 if (hasColumn(rs, "use_ex_name")) {
			 processs.setUse_ex_name(rs.getString("use_ex_name"));
	        } 
		 
		 if (hasColumn(rs, "shortcut_newdoc_change_closing_status")) {
			 processs.setShortcut_newdoc_change_closing_status(rs.getString("shortcut_newdoc_change_closing_status"));
	        } 
		 if (hasColumn(rs, "closing_status_notsend")) {
			 processs.setClosing_status_notsend(rs.getString("closing_status_notsend"));
	        } 
		 
		 
		 if (hasColumn(rs, "closing_status_expired")) {
			 processs.setClosing_status_expired(rs.getString("closing_status_expired"));
	        } 
		 if (hasColumn(rs, "expires_in_days")) {
			 processs.setExpires_in_days(rs.getInt("expires_in_days"));
	        } 
		 if (hasColumn(rs, "is_acknowledge_required")) {
			 processs.setIs_acknowledge_required(rs.getInt("is_acknowledge_required"));
	        } 
		 if (hasColumn(rs, "closingStatusAfterAdd")) {
			 processs.setClosingStatusAfterAdd(rs.getString("closingStatusAfterAdd"));
	        } 
		 
		 if (hasColumn(rs, "profile_column")) {
			 processs.setProfile_column(rs.getString("profile_column"));
	        } 
		 
		 if (hasColumn(rs, "product_master_column")) {
			 processs.setProduct_master_column(rs.getString("product_master_column"));
	        } 

		 
		 if (hasColumn(rs, "is_attachment_required_to_close")) {
			 processs.setIs_attachment_required_to_close(rs.getString("is_attachment_required_to_close"));
	        } 
		 if (hasColumn(rs, "closing_evidence_type")) {
			 processs.setClosing_evidence_type(rs.getString("closing_evidence_type"));
	        } 
		 if (hasColumn(rs, "can_refuse_and_return")) {
			 processs.setCan_refuse_and_return(rs.getInt("can_refuse_and_return"));
	        } 

		 
		 if (hasColumn(rs, "edata_idoc_grade_ref")) {
			 processs.setEdata_idoc_grade_ref(rs.getString("edata_idoc_grade_ref"));
	        } 
		 if (hasColumn(rs, "applied_status_array")) {
			 processs.setApplied_status_array(rs.getString("applied_status_array"));
	        } 
		 if (hasColumn(rs, "is_ex_movement_active")) {
			 processs.setIs_ex_movement_active(rs.getString("is_ex_movement_active"));
	        } 
		
		 if (hasColumn(rs, "cycle_duration")) {
			 processs.setCycle_duration(rs.getInt("cycle_duration"));
	        } 
		 if (hasColumn(rs, "grade_frequency_mapping")) {
			 processs.setGrade_frequency_mapping(rs.getString("grade_frequency_mapping"));
	        } 
		 if (hasColumn(rs, "sub_process_expired")) {
			 processs.setSub_process_expired(rs.getString("sub_process_expired"));
	        } 
		 

		return processs;
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
