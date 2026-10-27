package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.edatas;


public class edatamappers2 implements RowMapper<edatas> {

	@Override
	public  edatas mapRow(ResultSet rs, int rowNum) throws SQLException {
		edatas  edatas = new  edatas();
		 
		 
		 if (hasColumn(rs, "data_id")) {
			 edatas.setData_id(rs.getInt("data_id"));
	        }
		  if (hasColumn(rs, "data_planid")) {
				edatas.setData_planid(rs.getInt("data_planid"));
	        } 
		  if (hasColumn(rs, "data_eformid")) {
				edatas.setData_eformid(rs.getInt("data_eformid"));
	        } 
		  if (hasColumn(rs, "data_eformrv")) {
				edatas.setData_eformrv(rs.getInt("data_eformrv"));
	        } 
		  if (hasColumn(rs, "data_idadd")) {
				edatas.setData_idadd(rs.getInt("data_idadd"));
	        } 
		  if (hasColumn(rs, "data_nameadd")) {
				edatas.setData_nameadd(rs.getString("data_nameadd"));
	        } 
		  if (hasColumn(rs, "data_dateadd")) {
				edatas.setData_dateadd(rs.getString("data_dateadd"));
	        } 
		  if (hasColumn(rs, "data_dateplan")) {
				edatas.setData_dateplan(rs.getString("data_dateplan"));
	        } 
		  if (hasColumn(rs, "data_plantype")) {
				edatas.setData_plantype(rs.getString("data_plantype"));
	        }  
		  if (hasColumn(rs, "data_filetype")) {
				edatas.setData_filetype(rs.getString("data_filetype"));
	        }  	 
		  if (hasColumn(rs, "data_filepath")) {
				edatas.setData_filepath(rs.getString("data_filepath"));
	        }  	 
		  if (hasColumn(rs, "data_idapp")) {
				edatas.setData_idapp(rs.getInt("data_idapp"));
	        }  	 

		  if (hasColumn(rs, "data_nameapp")) {
				edatas.setData_nameapp(rs.getString("data_nameapp"));
	        }  	
		  if (hasColumn(rs, "data_comment")) {
				edatas.setData_nameapp(rs.getString("data_comment"));
	        }  	
		  if (hasColumn(rs, "data_analyst")) {
				edatas.setData_nameapp(rs.getString("data_analyst"));
	        }  	
		 
		  if (hasColumn(rs, "data_title1")) {
				edatas.setData_title1(rs.getString("data_title1"));
	        }  	
		  if (hasColumn(rs, "data_value1")) {
				edatas.setData_value1(rs.getString("data_value1"));
	        }  	

		  if (hasColumn(rs, "data_title2")) {
				edatas.setData_title2(rs.getString("data_title2"));
	        }  	
		  if (hasColumn(rs, "data_value2")) {
				edatas.setData_value2(rs.getString("data_value2"));
	        }  	

		  if (hasColumn(rs, "data_title3")) {
				edatas.setData_title3(rs.getString("data_title3"));
	        }  	
		  if (hasColumn(rs, "data_value3")) {
				edatas.setData_value3(rs.getString("data_value3"));
	        }  	
		  if (hasColumn(rs, "data_title4")) {
				edatas.setData_title4(rs.getString("data_title4"));
	        }  	
		  if (hasColumn(rs, "data_value4")) {
				edatas.setData_value4(rs.getString("data_value4"));
	        }  	

		  if (hasColumn(rs, "data_title5")) {
				edatas.setData_title5(rs.getString("data_title5"));
	        }  	
		  if (hasColumn(rs, "data_value5")) {
				edatas.setData_value5(rs.getString("data_value5"));
	        }  	

		  if (hasColumn(rs, "data_title6")) {
				edatas.setData_title6(rs.getString("data_title6"));
	        }  	
		  if (hasColumn(rs, "data_value6")) {
				edatas.setData_value6(rs.getString("data_value6"));
	        }  	 

		  if (hasColumn(rs, "data_title7")) {
				edatas.setData_title7(rs.getString("data_title7"));
	        }  	
		  if (hasColumn(rs, "data_value7")) {
				edatas.setData_value7(rs.getString("data_value7"));
	        }  	
		 
		  if (hasColumn(rs, "data_title8")) {
				edatas.setData_title8(rs.getString("data_title8"));
	        }  	
		  if (hasColumn(rs, "data_value8")) {
				edatas.setData_value8(rs.getString("data_value8"));
	        }  	
		  if (hasColumn(rs, "data_title9")) {
				edatas.setData_title9(rs.getString("data_title9"));
	        }  	
		  if (hasColumn(rs, "data_value9")) {
				edatas.setData_value9(rs.getString("data_value9"));
	        }  	
		  if (hasColumn(rs, "data_title10")) {
				edatas.setData_title10(rs.getString("data_title10"));
	        }  	
		  if (hasColumn(rs, "data_value10")) {
				edatas.setData_value10(rs.getString("data_value10"));
	        }  	

		  if (hasColumn(rs, "data_title11")) {
				edatas.setData_title11(rs.getString("data_title11"));
	        }  	
		  if (hasColumn(rs, "data_value11")) {
				edatas.setData_value11(rs.getString("data_value11"));
	        }  
		 
		 
		  if (hasColumn(rs, "data_title12")) {
				edatas.setData_title12(rs.getString("data_title12"));
	        }  	
		  if (hasColumn(rs, "data_value12")) {
				edatas.setData_value12(rs.getString("data_value12"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title13")) {
				edatas.setData_title13(rs.getString("data_title13"));
	        }  	
		  if (hasColumn(rs, "data_value13")) {
				edatas.setData_value13(rs.getString("data_value13"));
	        }  
		 
		 
		  if (hasColumn(rs, "data_title14")) {
				edatas.setData_title14(rs.getString("data_title14"));
	        }  	
		  if (hasColumn(rs, "data_value14")) {
				edatas.setData_value14(rs.getString("data_value14"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title15")) {
				edatas.setData_title15(rs.getString("data_title15"));
	        }  	
		  if (hasColumn(rs, "data_value15")) {
				edatas.setData_value15(rs.getString("data_value15"));
	        }  
		 
		 
		  if (hasColumn(rs, "data_title16")) {
				edatas.setData_title16(rs.getString("data_title16"));
	        }  	
		 	 if (hasColumn(rs, "data_value16")) {
				edatas.setData_value16(rs.getString("data_value16"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title17")) {
				edatas.setData_title17(rs.getString("data_title17"));
	        }  	
		  if (hasColumn(rs, "data_value17")) {
				edatas.setData_value17(rs.getString("data_value17"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title18")) {
				edatas.setData_title18(rs.getString("data_title18"));
	        }  	
		  if (hasColumn(rs, "data_value18")) {
				edatas.setData_value18(rs.getString("data_value18"));
	        }  	
		 
		  if (hasColumn(rs, "data_title19")) {
				edatas.setData_title19(rs.getString("data_title19"));
	        }  	
		  if (hasColumn(rs, "data_value19")) {
				edatas.setData_value19(rs.getString("data_value19"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title20")) {
				edatas.setData_title20(rs.getString("data_title20"));
	        }  	
		  if (hasColumn(rs, "data_value20")) {
				edatas.setData_value20(rs.getString("data_value20"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_due")) {
				edatas.setData_due(rs.getString("data_due"));
	        }  	
		  if (hasColumn(rs, "data_department")) {
				edatas.setData_department(rs.getString("data_department"));
	        }  	
		  if (hasColumn(rs, "data_status")) {
				edatas.setData_status(rs.getString("data_status"));
	        }  	
		  if (hasColumn(rs, "auto_read")) {
				edatas.setAuto_read(rs.getInt("auto_read"));
	        }  	
		 
		  if (hasColumn(rs, "alert")) {
				edatas.setAlert(rs.getInt("alert"));
	        }  
		 
		  if (hasColumn(rs, "creator")) {
				edatas.setCreator(rs.getInt("creator"));
	        }  
		  if (hasColumn(rs, "sender")) {
				edatas.setSender(rs.getInt("sender"));
	        }   
		  if (hasColumn(rs, "receiver")) {
				edatas.setReceiver(rs.getInt("receiver"));
	        }   
		 
		  if (hasColumn(rs, "current_step")) {
				edatas.setCurrent_step(rs.getString("current_step"));
	        }   
		 
		  if (hasColumn(rs, "data_namerefer")) {
				edatas.setData_namerefer(rs.getString("data_namerefer"));
	        }   
		 
		  if (hasColumn(rs, "data_title")) {
				edatas.setData_title(rs.getString("data_title"));
	        }   
		 
		  if (hasColumn(rs, "date_clear")) {
				edatas.setDate_clear(rs.getString("date_clear"));
	        }    
		 
		  if (hasColumn(rs, "cleared")) {
				edatas.setCleared(rs.getInt("cleared"));
	        }   
		  if (hasColumn(rs, "data_flow")) {
				edatas.setData_flow(rs.getInt("data_flow"));
	        }    
		  if (hasColumn(rs, "upload_pn_note")) {
				edatas.setUpload_pn_note(rs.getString("upload_pn_note"));
	        }    
	        
		  if (hasColumn(rs, "upload_pn_id")) {
				edatas.setUpload_pn_id(rs.getInt("upload_pn_id"));
	        }    
		  if (hasColumn(rs, "upload_pn_type")) {
				edatas.setUpload_pn_type(rs.getInt("upload_pn_type"));
	        }     
		  if (hasColumn(rs, "upload_pn_due")) {
				edatas.setUpload_pn_due(rs.getInt("upload_pn_due"));
	        }
		  
		  if (hasColumn(rs, "upload_pn_group")) {
				edatas.setUpload_pn_group(rs.getString("upload_pn_group"));
	        }
		  if (hasColumn(rs, "upload_pn_category")) {
				edatas.setUpload_pn_category(rs.getString("upload_pn_category"));
	        }
		  if (hasColumn(rs, "upload_pn_date")) {
				edatas.setUpload_pn_date(rs.getString("upload_pn_date"));
	        }
		return edatas;
	
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
