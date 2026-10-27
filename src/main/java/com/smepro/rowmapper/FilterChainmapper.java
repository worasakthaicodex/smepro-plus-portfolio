package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.FilterChain;
import com.smepro.models.PrepareReport;
import com.smepro.models.report;

public class FilterChainmapper implements RowMapper<FilterChain> {

	@Override
	public FilterChain mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		FilterChain reports = new FilterChain();

		reports.setId(rs.getInt("id"));
		reports.setId_refer(rs.getInt("id_refer"));
		reports.setTables(rs.getInt("tables"));
		reports.setType(rs.getString("type"));
		reports.setQuery_text(rs.getString("query_text"));
		reports.setGroups(rs.getString("groups"));
		reports.setStatus(rs.getInt("status"));
		return reports;
		
		

	}

}
