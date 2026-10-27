package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.report_pn;

public class report_pnmapper implements RowMapper<report_pn> {

	@Override
	public report_pn mapRow(ResultSet rs, int rowNum) throws SQLException {
		report_pn report_pns = new report_pn();
		report_pns.setReport_pn_id(rs.getInt("report_pn_id"));
		report_pns.setReport_pn_date(rs.getString("report_pn_date"));
		report_pns.setReport_pn_idrefer(rs.getInt("report_pn_idrefer"));
		report_pns.setReport_pn_target(rs.getInt("report_pn_target"));
		report_pns.setReport_pn_total(rs.getInt("report_pn_total"));
		report_pns.setReport_pn_respon(rs.getString("report_pn_respon"));
		report_pns.setReport_pn_app(rs.getString("report_pn_app"));
		report_pns.setReport_pn_status(rs.getInt("report_pn_status"));
		report_pns.setReport_pn_department(rs.getString("report_pn_department"));
		report_pns.setReport_pn_statussccess(rs.getInt("report_pn_statussccess"));
		report_pns.setReport_pn_app_statussccess(rs.getInt("report_pn_app_statussccess"));
		
		report_pns.setReport_pn_appstatus(rs.getInt("report_pn_appstatus"));
		report_pns.setReport_pn_sendstatus(rs.getInt("report_pn_sendstatus"));
		
		

        if (hasColumn(rs, "report_pn_note")) {
        	report_pns.setReport_pn_note(rs.getString("report_pn_note"));
        }
		
        if (hasColumn(rs, "report_pn_dateupload")) {
        	report_pns.setReport_pn_dateupload(rs.getString("report_pn_dateupload"));
        }
        
        if (hasColumn(rs, "report_pn_datecheck")) {
        	report_pns.setReport_pn_datecheck(rs.getString("report_pn_datecheck"));
        }
        
        
		
		return report_pns;
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
