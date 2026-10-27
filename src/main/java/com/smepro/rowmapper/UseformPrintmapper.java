package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Edata_maindata;
import com.smepro.models.FormPrint;
import com.smepro.models.Relations;
import com.smepro.models.UseformPrint;
import com.smepro.models.condition;




public class UseformPrintmapper implements RowMapper<UseformPrint> {

	@Override
	public 	UseformPrint mapRow(ResultSet rs, int rowNum) throws SQLException {
		UseformPrint  data = new 	UseformPrint();
	
			data.setId(rs.getInt("id"));
			data.setForm_id(rs.getInt("form_id"));
			data.setUse_status(rs.getInt("use_status"));
			data.setProcess_id(rs.getInt("process_id"));
			data.setUser_id_select(rs.getInt("user_id_select"));
			data.setPayment_terms_column(rs.getInt("payment_terms_column"));
			data.setPromotion_column(rs.getString("promotion_column"));
			data.setRegister_id(rs.getString("register_id"));
			
			
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

