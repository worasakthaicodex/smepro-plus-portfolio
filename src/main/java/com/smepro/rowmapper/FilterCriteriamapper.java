package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.FilterChain;
import com.smepro.models.FilterCriteria;
import com.smepro.models.PrepareReport;
import com.smepro.models.report;

public class FilterCriteriamapper implements RowMapper<FilterCriteria> {

	@Override
	public FilterCriteria mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		FilterCriteria reports = new FilterCriteria();

		reports.setId(rs.getInt("id"));
		reports.setId_refer(rs.getInt("id_refer"));
		reports.setJson_out_put(rs.getString("json_out_put"));
		
		return reports;
		
		

	}

}
