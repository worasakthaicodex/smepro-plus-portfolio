package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.report_pn_pdf;

public class reportpnpdf implements RowMapper<report_pn_pdf> {

	@Override
	public report_pn_pdf mapRow(ResultSet rs, int rowNum) throws SQLException {
		report_pn_pdf pdf = new report_pn_pdf();
		pdf.setUser_name(rs.getString("report_pn_respon"));
		pdf.setReport_pn_total(rs.getInt("report_pn_total"));
		pdf.setReport_pn_appstatus(rs.getInt("report_pn_appstatus"));
		pdf.setReport_pn_sendstatus(rs.getInt("report_pn_sendstatus"));
		pdf.setReport_pn_statuserror(rs.getInt("report_pn_statuserror"));
		pdf.setReport_pn_target(rs.getInt("report_pn_target"));
		return pdf;
	}
	

}
