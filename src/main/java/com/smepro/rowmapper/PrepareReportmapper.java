package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.PrepareReport;
import com.smepro.models.report;

public class PrepareReportmapper implements RowMapper<PrepareReport> {

	@Override
	public PrepareReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		PrepareReport reports = new PrepareReport();
		reports.setId(rs.getInt("id"));
		reports.setSource_type_method(rs.getInt("source_type_method"));
		reports.setSource_id(rs.getInt("source_id"));
		reports.setProcess_id(rs.getInt("process_id"));
		reports.setSource_type(rs.getString("source_type"));
		reports.setQuerychains_enabled(rs.getInt("querychains_enabled"));
		reports.setSource_name(rs.getString("source_name"));
		
		reports.setData_info_show_row(rs.getInt("data_info_show_row"));
		reports.setData_info_showsample_total(rs.getInt("data_info_showsample_total"));
		reports.setData_info_showtotal_row(rs.getInt("data_info_showtotal_row"));
		
		reports.setSync_back_limit(rs.getInt("sync_back_limit"));
		reports.setSync_back_random(rs.getInt("sync_back_random"));
		reports.setSync_back_not_unique_active(rs.getInt("sync_back_not_unique_active"));
		return reports;
		
		
		
		

	}

}
