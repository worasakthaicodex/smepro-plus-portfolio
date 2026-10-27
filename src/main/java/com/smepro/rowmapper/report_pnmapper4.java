package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.report_pn;

public class report_pnmapper4 implements RowMapper<report_pn> {

	@Override
	public report_pn mapRow(ResultSet rs, int rowNum) throws SQLException {
		report_pn report_pns = new report_pn();
		report_pns.setReport_pn_id(rs.getInt("report_pn_id"));
		report_pns.setReport_pn_date(rs.getString("report_pn_date"));
		report_pns.setReport_pn_respon(rs.getString("report_pn_respon"));
		report_pns.setReport_pn_statussccess(rs.getInt("report_pn_statussccess"));
		report_pns.setReport_pn_statuserror(rs.getInt("report_pn_statuserror"));
		return report_pns;
	}

}
