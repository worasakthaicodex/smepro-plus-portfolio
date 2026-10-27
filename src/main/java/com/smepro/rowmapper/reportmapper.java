package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



import com.smepro.models.report;

public class reportmapper implements RowMapper<report> {

	@Override
	public report mapRow(ResultSet rs, int rowNum) throws SQLException {
		report reports = new report();
		reports.setReport_id(rs.getInt("report_id"));
		reports.setReport_status(rs.getInt("report_status"));
		reports.setReport_name(rs.getString("report_name"));
		reports.setReport_id_refer(rs.getInt("report_id_refer"));
		reports.setReport_date(rs.getString("report_date"));
		reports.setReport_for(rs.getString("report_for"));
		reports.setReport_by(rs.getString("report_by"));

		reports.setTitle1(rs.getString("title1"));
		reports.setTitle2(rs.getString("title2"));
		reports.setTitle3(rs.getString("title3"));
		reports.setTitle4(rs.getString("title4"));
		
		reports.setReport_month(rs.getString("report_month"));
		reports.setReport_start(rs.getString("report_start"));
		reports.setReport_end(rs.getString("report_end"));
		reports.setReport_data(rs.getString("report_data"));
		reports.setReport_report(rs.getString("report_report"));
		reports.setFoot(rs.getInt("foot"));
		return reports;
	}

}
