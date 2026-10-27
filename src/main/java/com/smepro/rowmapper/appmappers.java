package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.file;


public class appmappers implements RowMapper<edata> {

	@Override
	public  edata mapRow(ResultSet rs, int rowNum) throws SQLException {
		 edata  edatas = new  edata();
		 

		 if (hasColumn(rs, "reference")) {
			  edatas.setReference(rs.getString("reference"));
	        }
		 
		 if (hasColumn(rs, "id")) {
			  edatas.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "alert")) {
			  edatas.setAlert(rs.getInt("alert"));
	        }
	 
		 if (hasColumn(rs, "data_id")) {
			  edatas.setData_id(rs.getInt("data_id"));
	        }
		 if (hasColumn(rs, "data_title")) {
			  edatas.setData_title(rs.getString("data_title"));
	        }

		 if (hasColumn(rs, "data_value2")) {
			  edatas.setData_value2(rs.getString("data_value2"));
	        }
		 if (hasColumn(rs, "data_value1")) {
			  edatas.setData_value1(rs.getString("data_value1"));
	        }
		 if (hasColumn(rs, "data_value3")) {
			  edatas.setData_value3(rs.getString("data_value3"));
	        }
		 
		 if (hasColumn(rs, "data_value4")) {
			  edatas.setData_value4(rs.getString("data_value4"));
	        }
		 if (hasColumn(rs, "data_value5")) {
			  edatas.setData_value5(rs.getString("data_value5"));
	        }
		 if (hasColumn(rs, "data_value6")) {
			  edatas.setData_value6(rs.getString("data_value6"));
	        }
		 if (hasColumn(rs, "data_value7")) {
			  edatas.setData_value7(rs.getString("data_value7"));
	        }
		 if (hasColumn(rs, "data_value8")) {
			  edatas.setData_value8(rs.getString("data_value8"));
	        }
		 if (hasColumn(rs, "data_value9")) {
			  edatas.setData_value9(rs.getString("data_value9"));
	        }
		 if (hasColumn(rs, "data_value10")) {
			  edatas.setData_value10(rs.getString("data_value10"));
	        }
		 if (hasColumn(rs, "data_value11")) {
			  edatas.setData_value11(rs.getString("data_value11"));
	        }
		 if (hasColumn(rs, "data_value12")) {
			  edatas.setData_value12(rs.getString("data_value12"));
	        }
		 if (hasColumn(rs, "data_value13")) {
			  edatas.setData_value13(rs.getString("data_value13"));
	        }
		 if (hasColumn(rs, "data_value14")) {
			  edatas.setData_value14(rs.getString("data_value14"));
	        }
		 if (hasColumn(rs, "data_value15")) {
			  edatas.setData_value15(rs.getString("data_value15"));
	        }
		 if (hasColumn(rs, "data_value16")) {
			  edatas.setData_value16(rs.getString("data_value16"));
	        } 
		 if (hasColumn(rs, "data_value17")) {
			  edatas.setData_value17(rs.getString("data_value17"));
	        }
		 
		 if (hasColumn(rs, "data_value18")) {
			  edatas.setData_value18(rs.getString("data_value18"));
	        }	 
		 if (hasColumn(rs, "data_value19")) {
			  edatas.setData_value19(rs.getString("data_value19"));
	        }
		 if (hasColumn(rs, "data_value20")) {
			  edatas.setData_value20(rs.getString("data_value20"));
	        }
		 
		 if (hasColumn(rs, "data_value21")) {
			  edatas.setData_value21(rs.getString("data_value21"));
	        }
		 if (hasColumn(rs, "data_value22")) {
			  edatas.setData_value22(rs.getString("data_value22"));
	        }
		 if (hasColumn(rs, "data_value23")) {
			  edatas.setData_value23(rs.getString("data_value23"));
	        }
		 if (hasColumn(rs, "data_value24")) {
			  edatas.setData_value24(rs.getString("data_value24"));
	        }
		 if (hasColumn(rs, "data_value25")) {
			  edatas.setData_value25(rs.getString("data_value25"));
	        }
		 if (hasColumn(rs, "data_value26")) {
			  edatas.setData_value26(rs.getString("data_value26"));
	        }
		 if (hasColumn(rs, "data_value27")) {
			  edatas.setData_value27(rs.getString("data_value27"));
	        }
		 if (hasColumn(rs, "data_value28")) {
			  edatas.setData_value28(rs.getString("data_value28"));
	        }
		 if (hasColumn(rs, "data_value29")) {
			  edatas.setData_value29(rs.getString("data_value29"));
	        }
		 if (hasColumn(rs, "data_value30")) {
			  edatas.setData_value30(rs.getString("data_value30"));
	        }
		 
		 if (hasColumn(rs, "data_dateplan")) {
			  edatas.setData_dateplan(rs.getString("data_dateplan"));
	        }
		 
		  if (hasColumn(rs, "current_step")) {
			  edatas.setCurrent_step(rs.getString("current_step"));
	        }
		  if (hasColumn(rs, "date")) {
			  edatas.setData_dateadd(rs.getString("date"));
	        }
		  
		 if (hasColumn(rs, "creator")) {
				edatas.setCreator(rs.getInt("creator"));
		    }
		 if (hasColumn(rs, "remarks_rejected")) {
				edatas.setRemarks_rejected(rs.getString("remarks_rejected"));
		    }
		 if (hasColumn(rs, "data_status")) {
				edatas.setData_status(rs.getString("data_status"));
		    }
		 if (hasColumn(rs, "data_nameadd")) {
				edatas.setData_nameadd(rs.getString("data_nameadd"));
		    }
	
			// 1. ให้มันหาตำแหน่งของ Alias ก่อน (วิธีนี้ Driver ส่วนใหญ่จะยอมทำงาน)
		    int senderIdx = rs.findColumn("data_namesend");
		    if(senderIdx > 0) {
		    edatas.setData_namesend(rs.getString(senderIdx));
		    }
		 
		 
		 if (hasColumn(rs, "data_namereceiver")) {
				edatas.setData_namereceiver(rs.getString("data_namereceiver"));
		    }
		 
		 if (hasColumn(rs, "data_dateupdate")) {
				edatas.setData_dateupdate(rs.getString("data_dateupdate"));
		    }
		 if (hasColumn(rs, "name")) {
				edatas.setData_title(rs.getString("name"));
		    }
		 if (hasColumn(rs, "sender")) {
				edatas.setSender(rs.getInt("sender"));
		    }
		 
		 if (hasColumn(rs, "receiver")) {
				edatas.setReceiver(rs.getInt("receiver"));
		    }
		 
		 if (hasColumn(rs, "refer_user")) {
				edatas.setRefer_user(rs.getString("refer_user"));
		    }
		 if (hasColumn(rs, "date_update")) {
				edatas.setData_date_update(rs.getString("date_update"));
		    }
		 if (hasColumn(rs, "acknowledge")) {
				edatas.setAcknowledge(rs.getInt("acknowledge"));
		    }
		  
		  if (hasColumn(rs, "closing_status")) {
			  edatas.setClosing_status(rs.getString("closing_status"));
	        }  
		  if (hasColumn(rs, "data_date_receiver")) {
			  edatas.setData_date_receiver(rs.getString("data_date_receiver"));
	        }  
		  

		 if (hasColumn(rs, "doc_id")) {
			 
				 if(rs.getInt("doc_id") > 0) {
				 	String rv ="";
			 		String DOC ="";
			 		if(rs.getInt("doc_rv") > 0) {
			 			rv ="-R"+rs.getInt("doc_rv"); 
			 		}
			 			DOC = rs.getString("doc_type")+rs.getString("doc_date")+"-"+rs.getInt("doc_autoid");
			 			edatas.setDoc(DOC+rv);
			    }
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
