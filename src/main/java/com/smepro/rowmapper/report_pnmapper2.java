package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.report_pn;

public class report_pnmapper2 implements RowMapper<report_pn> {

	@Override
	public report_pn mapRow(ResultSet rs, int rowNum) throws SQLException {
		report_pn report_pns = new report_pn();
		report_pns.setReport_pn_id(rs.getInt("report_pn_id"));
		report_pns.setReport_pn_date(rs.getString("report_pn_date"));
		report_pns.setReport_pn_idrefer(rs.getInt("report_pn_idrefer"));
		report_pns.setReport_pn_target(rs.getInt("report_pn_target"));
		report_pns.setReport_pn_total(rs.getInt("report_pn_total"));
		report_pns.setUpload_pn_id(rs.getInt("upload_pn_id"));
		report_pns.setUpload_pn_name(rs.getString("upload_pn_name"));
		report_pns.setUpload_pn_note(rs.getString("upload_pn_note"));
		report_pns.setUpload_pn_due(rs.getInt("upload_pn_due"));
		report_pns.setUpload_pn_idadd(rs.getInt("upload_pn_idadd"));
		report_pns.setUpload_pn_idrefer(rs.getInt("upload_pn_idrefer"));
		report_pns.setUpload_pn_respon(rs.getString("upload_pn_respon"));
		report_pns.setUpload_pn_date(rs.getString("upload_pn_date"));
		report_pns.setReport_pn_status(rs.getInt("report_pn_status"));
		report_pns.setReport_pn_respon(rs.getString("report_pn_respon"));
		report_pns.setReport_pn_app(rs.getString("report_pn_app"));
		report_pns.setReport_pn_statussccess(rs.getInt("report_pn_statussccess"));
		report_pns.setReport_pn_sendstatus(rs.getInt("report_pn_sendstatus"));
		report_pns.setReport_pn_statuserror(rs.getInt("report_pn_statuserror"));
		return report_pns;
	}

}
