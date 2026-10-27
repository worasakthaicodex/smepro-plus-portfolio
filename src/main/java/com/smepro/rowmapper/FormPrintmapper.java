package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Edata_maindata;
import com.smepro.models.FormPrint;
import com.smepro.models.Relations;
import com.smepro.models.condition;




public class FormPrintmapper implements RowMapper<FormPrint> {

	@Override
	public 	FormPrint mapRow(ResultSet rs, int rowNum) throws SQLException {
		FormPrint  data = new 	FormPrint();
		data.setId(rs.getInt("id"));
		data.setForm_name(rs.getString("form_name"));
		data.setForm_link(rs.getString("form_link"));
		data.setId_add(rs.getInt("id_add"));
		data.setProcess_id(rs.getInt("process_id"));

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

