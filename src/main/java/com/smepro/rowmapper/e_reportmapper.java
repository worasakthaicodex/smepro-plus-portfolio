package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.e_report;


public class e_reportmapper implements RowMapper<e_report> {

	@Override
	public e_report mapRow(ResultSet rs, int rowNum) throws SQLException {
		e_report e_reports = new e_report();
		e_reports.setE_report_id(rs.getInt("e_report_id"));
		e_reports.setE_report_idadd(rs.getInt("e_report_idadd"));
		e_reports.setE_report_conditionid(rs.getInt("e_report_conditionid"));
		e_reports.setLine_label(rs.getString("line_label"));
		e_reports.setLine_data(rs.getString("line_data"));
		e_reports.setLine_target(rs.getInt("line_target"));
		e_reports.setColumn_label(rs.getString("column_label"));
		e_reports.setColumn_data(rs.getString("column_data"));
		e_reports.setColumn_target(rs.getInt("column_target"));
		e_reports.setPie_label(rs.getString("pie_label"));
		e_reports.setPie_data(rs.getString("pie_data"));
		e_reports.setStatus(rs.getInt("status"));
		return e_reports;
	}

}
