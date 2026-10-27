package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.process;
import com.smepro.models.sub_processes;


public class sub_processesmapper implements RowMapper<sub_processes> {

	@Override
	public sub_processes mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		sub_processes processs = new sub_processes();

		 
		 if (hasColumn(rs, "id")) {
			 processs.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "step")) {
			 processs.setStep(rs.getString("step"));
	        } 
		 
		 if (hasColumn(rs, "description")) {
			 processs.setDescription(rs.getString("description"));
	        } 
		 
		 if (hasColumn(rs, "processe_id")) {
			 processs.setProcesse_id(rs.getInt("processe_id"));
	        }
		
		 
	
		 if (hasColumn(rs, "maindatacolumn")) {
			 processs.setMaindatacolumn(rs.getString("maindatacolumn"));
	        }

		 if (hasColumn(rs, "filtermain")) {
			 processs.setFiltermain(rs.getString("filtermain"));
	        }
		 
		 
		 
		 if (hasColumn(rs, "subdatacolumn")) {
			 processs.setSubdatacolumn(rs.getString("subdatacolumn"));
	        }
		
		 
		 if (hasColumn(rs, "filtersub")) {
			 processs.setFiltersub(rs.getString("filtersub"));
	        }
		
		 if (hasColumn(rs, "subdatacolumn1")) {
			 processs.setSubdatacolumn1(rs.getString("subdatacolumn1"));
	        }
		
		 
		 if (hasColumn(rs, "filtersub1")) {
			 processs.setFiltersub1(rs.getString("filtersub1"));
	        }
		
		 
		 
		 if (hasColumn(rs, "subdatacolumn2")) {
			 processs.setSubdatacolumn2(rs.getString("subdatacolumn2"));
	        }
		
		 
		 if (hasColumn(rs, "filtersub2")) {
			 processs.setFiltersub2(rs.getString("filtersub2"));
	        }
		 
		 
		 
		 if (hasColumn(rs, "subdatacolumn3")) {
			 processs.setSubdatacolumn3(rs.getString("subdatacolumn3"));
	        }
		
		 
		 if (hasColumn(rs, "filtersub3")) {
			 processs.setFiltersub3(rs.getString("filtersub3"));
	        }
		 
		 
		 
		 if (hasColumn(rs, "subdatacolumn4")) {
			 processs.setSubdatacolumn4(rs.getString("subdatacolumn4"));
	        }
		
		 
		 if (hasColumn(rs, "filtersub4")) {
			 processs.setFiltersub4(rs.getString("filtersub4"));
	        }
		 
		 
		 if (hasColumn(rs, "subdatacolumn5")) {
			 processs.setSubdatacolumn5(rs.getString("subdatacolumn5"));
	        }
		
		 
		 if (hasColumn(rs, "filtersub5")) {
			 processs.setFiltersub5(rs.getString("filtersub5"));
	        }
		 
		 
		 
		 
		 
		 
		 if (hasColumn(rs, "activate_autoid")) {
			 processs.setActivate_autoid(rs.getInt("activate_autoid"));
	        } 
		 
		 
		 if (hasColumn(rs, "autoiddatacolumn")) {
			 processs.setAutoiddatacolumn(rs.getString("autoiddatacolumn"));
	        }
		
		 
		 if (hasColumn(rs, "code_app")) {
			 processs.setCode_app(rs.getString("code_app"));
	        } 
		 
	
		 if (hasColumn(rs, "action")) {
			 processs.setAction(rs.getString("action"));
	        } 
		
		 if (hasColumn(rs, "activate_subdata")) {
			 processs.setActivate_subdata(rs.getInt("activate_subdata"));
	        } 
	
		 
		 
		 if (hasColumn(rs, "activate_checking_list")) {
			 processs.setActivate_checking_list(rs.getInt("activate_checking_list"));
	        } 
		 if (hasColumn(rs, "checking_list")) {
			 processs.setChecking_list(rs.getString("checking_list"));
	        } 

		 if (hasColumn(rs, "activate_editlist")) {
			 processs.setActivate_editlist(rs.getInt("activate_editlist"));
	        } 
		 if (hasColumn(rs, "edit_list")) {
			 processs.setEdit_list(rs.getString("edit_list"));
	        } 

		 
		 if (hasColumn(rs, "activate_autorid")) {
			 processs.setActivate_autorid(rs.getInt("activate_autorid"));
	        } 
		 if (hasColumn(rs, "code_rid")) {
			 processs.setCode_rid(rs.getString("code_rid"));
	        } 

		 if (hasColumn(rs, "action_batch_insert")) {
			 processs.setAction_batch_insert(rs.getString("action_batch_insert"));
	        } 
		 if (hasColumn(rs, "activate_clear")) {
			 processs.setActivate_clear(rs.getString("activate_clear"));
	        } 
		 if (hasColumn(rs, "action_custom_clear")) {
			 processs.setAction_custom_clear(rs.getString("action_custom_clear"));
	        } 
		 if (hasColumn(rs, "action_batch_clear")) {
			 processs.setAction_batch_clear(rs.getString("action_batch_clear"));
	        } 

		
		 
		 if (hasColumn(rs, "activate_reference_only")) {
			 processs.setActivate_reference_only(rs.getString("activate_reference_only"));
	        } 
		 if (hasColumn(rs, "activate_hierarchical_reference")) {
			 processs.setActivate_hierarchical_reference(rs.getString("activate_hierarchical_reference"));
	        } 
		 if (hasColumn(rs, "activate_reference_modify")) {
			 processs.setActivate_reference_modify(rs.getString("activate_reference_modify"));
	        } 
		 if (hasColumn(rs, "activate_reference_continue")) {
			 processs.setActivate_reference_continue(rs.getString("activate_reference_continue"));
	        } 
		 if (hasColumn(rs, "action_batch_modify")) {
			 processs.setAction_batch_modify(rs.getString("action_batch_modify"));
	        } 
		 if (hasColumn(rs, "action_batch_continue")) {
			 processs.setAction_batch_continue(rs.getString("action_batch_continue"));
	        } 
		 if (hasColumn(rs, "activate_clear_register")) {
			 processs.setActivate_clear_register(rs.getString("activate_clear_register"));
	        } 
		 if (hasColumn(rs, "activate_register_processing")) {
			 processs.setActivate_register_processing(rs.getString("activate_register_processing"));
	        } 
		 
		 if (hasColumn(rs, "activate_document_reference")) {
			 processs.setActivate_document_reference(rs.getString("activate_document_reference"));
	        } 
		 
		 if (hasColumn(rs, "action_batch_dreference")) {
			 processs.setAction_batch_dreference(rs.getString("action_batch_dreference"));
	        } 
		 
		 
		 if (hasColumn(rs, "prefix")) {
			 processs.setPrefix(rs.getString("prefix"));
	        } 
		 if (hasColumn(rs, "batch_code_generation")) {
			 processs.setBatch_code_generation(rs.getString("batch_code_generation"));
	        } 
		 
		 
		 if (hasColumn(rs, "endaction_list")) {
			 processs.setEndaction_list(rs.getString("endaction_list"));
	        } 
		 if (hasColumn(rs, "activate_endaction")) {
			 processs.setActivate_endaction(rs.getInt("activate_endaction"));
	        } 
		 
		 
		 if (hasColumn(rs, "input_db1")) {
			 processs.setInput_db1(rs.getString("input_db1"));
	        } 
		 if (hasColumn(rs, "input_db2")) {
			 processs.setInput_db2(rs.getString("input_db2"));
	        } 
		 if (hasColumn(rs, "input_db3")) {
			 processs.setInput_db3(rs.getString("input_db3"));
	        } 
		 
		 if (hasColumn(rs, "input_db4")) {
			 processs.setInput_db4(rs.getString("input_db4"));
	        } 
		 if (hasColumn(rs, "input_db5")) {
			 processs.setInput_db5(rs.getString("input_db5"));
	        } 
		 if (hasColumn(rs, "input_db6")) {
			 processs.setInput_db6(rs.getString("input_db6"));
	        } 
		 if (hasColumn(rs, "input_db7")) {
			 processs.setInput_db7(rs.getString("input_db7"));
	        } 
		 if (hasColumn(rs, "action_type")) {
			 processs.setAction_type(rs.getInt("action_type"));
	        } 
		 
		 
		 
		 
		 
		 
		 if (hasColumn(rs, "accepting")) {
			 processs.setAccepting(rs.getInt("accepting"));
	        } 
		 if (hasColumn(rs, "prohibited1")) {
			 processs.setProhibited1(rs.getString("prohibited1"));
	        } 
		 if (hasColumn(rs, "prohibited2")) {
			 processs.setProhibited2(rs.getString("prohibited2"));
	        } 
		 
		 if (hasColumn(rs, "visibility")) {
			 processs.setVisibility(rs.getString("visibility"));
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
