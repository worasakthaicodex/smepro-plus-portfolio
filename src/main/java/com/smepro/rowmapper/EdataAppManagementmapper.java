package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.EdataAppManagement;
import com.smepro.models.ExtensionParameter;
import com.smepro.models.Management;
import com.smepro.models.TabManagement;
import com.smepro.models.UseExtension;
import com.smepro.models.process;

public class EdataAppManagementmapper implements RowMapper<EdataAppManagement> {

	@Override
	public EdataAppManagement mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EdataAppManagement data = new EdataAppManagement();

		 
		// ===== main ids =====
		if (hasColumn(rs, "data_id")) {
		    data.setData_id(rs.getInt("data_id"));
		}
		if (hasColumn(rs, "data_eformid")) {
		    data.setData_eformid(rs.getInt("data_eformid"));
		}
		if (hasColumn(rs, "data_eformrv")) {
		    data.setData_eformrv(rs.getInt("data_eformrv"));
		}
		if (hasColumn(rs, "data_title")) {
		    data.setData_title(rs.getString("data_title"));
		}
		if (hasColumn(rs, "process_id")) {
		    data.setProcesses_id(rs.getInt("process_id"));
		}

		// ===== data_value 1 - 20 =====
		if (hasColumn(rs, "data_value1"))  data.setData_value1(rs.getString("data_value1"));
		if (hasColumn(rs, "data_value2"))  data.setData_value2(rs.getString("data_value2"));
		if (hasColumn(rs, "data_value3"))  data.setData_value3(rs.getString("data_value3"));
		if (hasColumn(rs, "data_value4"))  data.setData_value4(rs.getString("data_value4"));
		if (hasColumn(rs, "data_value5"))  data.setData_value5(rs.getString("data_value5"));
		if (hasColumn(rs, "data_value6"))  data.setData_value6(rs.getString("data_value6"));
		if (hasColumn(rs, "data_value7"))  data.setData_value7(rs.getString("data_value7"));
		if (hasColumn(rs, "data_value8"))  data.setData_value8(rs.getString("data_value8"));
		if (hasColumn(rs, "data_value9"))  data.setData_value9(rs.getString("data_value9"));
		if (hasColumn(rs, "data_value10")) data.setData_value10(rs.getString("data_value10"));
		if (hasColumn(rs, "data_value11")) data.setData_value11(rs.getString("data_value11"));
		if (hasColumn(rs, "data_value12")) data.setData_value12(rs.getString("data_value12"));
		if (hasColumn(rs, "data_value13")) data.setData_value13(rs.getString("data_value13"));
		if (hasColumn(rs, "data_value14")) data.setData_value14(rs.getString("data_value14"));
		if (hasColumn(rs, "data_value15")) data.setData_value15(rs.getString("data_value15"));
		if (hasColumn(rs, "data_value16")) data.setData_value16(rs.getString("data_value16"));
		if (hasColumn(rs, "data_value17")) data.setData_value17(rs.getString("data_value17"));
		if (hasColumn(rs, "data_value18")) data.setData_value18(rs.getString("data_value18"));
		if (hasColumn(rs, "data_value19")) data.setData_value19(rs.getString("data_value19"));
		if (hasColumn(rs, "data_value20")) data.setData_value20(rs.getString("data_value20"));
		if (hasColumn(rs, "data_value21")) data.setData_value21(rs.getString("data_value21"));
		if (hasColumn(rs, "data_value22")) data.setData_value22(rs.getString("data_value22"));
		if (hasColumn(rs, "data_value23")) data.setData_value23(rs.getString("data_value23"));
		if (hasColumn(rs, "data_value24")) data.setData_value24(rs.getString("data_value24"));
		if (hasColumn(rs, "data_value25")) data.setData_value25(rs.getString("data_value25"));
		if (hasColumn(rs, "data_value26")) data.setData_value26(rs.getString("data_value26"));
		if (hasColumn(rs, "data_value27")) data.setData_value27(rs.getString("data_value27"));
		if (hasColumn(rs, "data_value28")) data.setData_value28(rs.getString("data_value28"));
		if (hasColumn(rs, "data_value29")) data.setData_value29(rs.getString("data_value29"));
		if (hasColumn(rs, "data_value30")) data.setData_value30(rs.getString("data_value30"));
		if (hasColumn(rs, "data_value31")) data.setData_value31(rs.getString("data_value31"));
		if (hasColumn(rs, "data_value32")) data.setData_value32(rs.getString("data_value32"));
		if (hasColumn(rs, "data_value33")) data.setData_value33(rs.getString("data_value33"));
		if (hasColumn(rs, "data_value34")) data.setData_value34(rs.getString("data_value34"));
		if (hasColumn(rs, "data_value35")) data.setData_value35(rs.getString("data_value35"));
		if (hasColumn(rs, "data_value36")) data.setData_value36(rs.getString("data_value36"));
		if (hasColumn(rs, "data_value37")) data.setData_value37(rs.getString("data_value37"));
		if (hasColumn(rs, "data_value38")) data.setData_value38(rs.getString("data_value38"));
		if (hasColumn(rs, "data_value39")) data.setData_value39(rs.getString("data_value39"));
		if (hasColumn(rs, "data_value40")) data.setData_value40(rs.getString("data_value40"));
		
		

		// ===== columns_value 1 - 10 =====
		if (hasColumn(rs, "columns_value1"))  data.setColumns_value1(rs.getString("columns_value1"));
		if (hasColumn(rs, "columns_value2"))  data.setColumns_value2(rs.getString("columns_value2"));
		if (hasColumn(rs, "columns_value3"))  data.setColumns_value3(rs.getString("columns_value3"));
		if (hasColumn(rs, "columns_value4"))  data.setColumns_value4(rs.getString("columns_value4"));
		if (hasColumn(rs, "columns_value5"))  data.setColumns_value5(rs.getString("columns_value5"));
		if (hasColumn(rs, "columns_value6"))  data.setColumns_value6(rs.getString("columns_value6"));
		if (hasColumn(rs, "columns_value7"))  data.setColumns_value7(rs.getString("columns_value7"));
		if (hasColumn(rs, "columns_value8"))  data.setColumns_value8(rs.getString("columns_value8"));
		if (hasColumn(rs, "columns_value9"))  data.setColumns_value9(rs.getString("columns_value9"));
		if (hasColumn(rs, "columns_value10")) data.setColumns_value10(rs.getString("columns_value10"));
		if (hasColumn(rs, "columns_value11")) data.setColumns_value11(rs.getString("columns_value11"));
		if (hasColumn(rs, "columns_value12")) data.setColumns_value12(rs.getString("columns_value12"));
		if (hasColumn(rs, "columns_value13")) data.setColumns_value13(rs.getString("columns_value13"));
		if (hasColumn(rs, "columns_value14")) data.setColumns_value14(rs.getString("columns_value14"));
		if (hasColumn(rs, "columns_value15")) data.setColumns_value15(rs.getString("columns_value15"));
		if (hasColumn(rs, "columns_value16")) data.setColumns_value16(rs.getString("columns_value16"));
		if (hasColumn(rs, "columns_value17")) data.setColumns_value17(rs.getString("columns_value17"));
		if (hasColumn(rs, "columns_value18")) data.setColumns_value18(rs.getString("columns_value18"));
		if (hasColumn(rs, "columns_value19")) data.setColumns_value19(rs.getString("columns_value19"));
		if (hasColumn(rs, "columns_value20")) data.setColumns_value20(rs.getString("columns_value20"));
		
		
		if (hasColumn(rs, "data_eformrv")) {
		    data.setData_eformrv(rs.getInt("data_eformrv"));
		}
		 if (hasColumn(rs, "doc_id")) {
			 
			 if(rs.getInt("doc_id") > 0) {
			 	String rv ="";
		 		String DOC ="";
		 		if(rs.getInt("doc_rv") > 0) {
		 			rv ="-R"+rs.getInt("doc_rv"); 
		 		}
		 			DOC = rs.getString("doc_type")+rs.getString("doc_date")+"-"+rs.getInt("doc_autoid");
		 			data.setDoc(DOC+rv);
		    }
	 }
	 
		 if (hasColumn(rs, "current_step_int")) {
			 	data.setCurrent_step_int(rs.getInt("current_step_int"));
		    }
		 
		 if (hasColumn(rs, "data_dateupdate")) {
			 	data.setData_dateupdate(rs.getString("data_dateupdate"));
		    }
		  
		 
		 
		 if (hasColumn(rs, "closing_status")) {
			 	data.setClosing_status(rs.getString("closing_status"));
		    }
		  
		 
		 if (hasColumn(rs, "acknowledge")) {
			 	data.setAcknowledge(rs.getInt("acknowledge"));
		    }
		  
		 
		 if (hasColumn(rs, "current_step")) {
			 	data.setCurrent_step(rs.getString("current_step"));
		    }
		  
		 
		 if (hasColumn(rs, "remarks")) {
			 	data.setRemarks(rs.getString("remarks"));
		    }
		  
		 if (hasColumn(rs, "remarks_rejected")) {
			 	data.setRemarks_rejected(rs.getString("remarks_rejected"));
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
