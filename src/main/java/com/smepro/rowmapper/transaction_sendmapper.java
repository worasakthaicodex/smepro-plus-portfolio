package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Myapp;
import com.smepro.models.keymap;
import com.smepro.models.policies;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;
import com.smepro.models.transaction_send;


public class transaction_sendmapper implements RowMapper<transaction_send> {

	@Override
	public transaction_send mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		transaction_send transaction_send = new transaction_send();

		 
		 if (hasColumn(rs, "id")) {
			 transaction_send.setId(rs.getInt("id"));
	        }	
		 if (hasColumn(rs, "send_to")) {
			 transaction_send.setSend_to(rs.getString("send_to"));
	        }
		 if (hasColumn(rs, "send_type")) {
			 transaction_send.setSend_type(rs.getString("send_type"));
	        } 
		 
		 if (hasColumn(rs, "receiver_type")) {
			 transaction_send.setReceiver_type(rs.getString("receiver_type"));
	        } 
		 
		 if (hasColumn(rs, "created_by")) {
			 transaction_send.setCreated_by(rs.getInt("created_by"));
	        }
		 if (hasColumn(rs, "created_at")) {
			 transaction_send.setCreated_at(rs.getString("created_at"));
	        }
		 if (hasColumn(rs, "status")) {
			 transaction_send.setStatus(rs.getString("status"));
	        }
	
		 if (hasColumn(rs, "comment")) {
			 transaction_send.setComment(rs.getString("comment"));
	        }
		 if (hasColumn(rs, "process_id")) {
			 transaction_send.setProcess_id(rs.getInt("process_id"));
	        }
		 if (hasColumn(rs, "edata_id")) {
			 transaction_send.setEdata_id(rs.getInt("edata_id"));
	        }
		 
		
		 if (hasColumn(rs, "data_nameadd")) {
			 transaction_send.setData_nameadd(rs.getString("data_nameadd"));
	        }
		 
		 if (hasColumn(rs, "data_value1")) {
			 transaction_send.setData_value1(rs.getString("data_value1"));
	        }
		 
		 if (hasColumn(rs, "data_value2")) {
			 transaction_send.setData_value2(rs.getString("data_value2"));
	        }
		 
		 if (hasColumn(rs, "data_value3")) {
			 transaction_send.setData_value3(rs.getString("data_value3"));
	        }
		 if (hasColumn(rs, "data_title")) {
			 transaction_send.setData_title(rs.getString("data_title"));
	        }
		 
		 if (hasColumn(rs, "alert")) {
			 transaction_send.setAlert(rs.getInt("alert"));
	        }
		 
		 
		return transaction_send;
		
		
		
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
