package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.FilterChain;
import com.smepro.models.PrepareReport;
import com.smepro.models.QuickQuery;
import com.smepro.models.report;

public class QuickQuerymapper implements RowMapper<QuickQuery> {

	@Override
	public QuickQuery mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		QuickQuery reports = new QuickQuery();

		reports.setId(rs.getInt("id"));
		reports.setId_refer(rs.getString("id_refer"));
		reports.setField(rs.getString("field"));
		reports.setType(rs.getString("type"));

		
		return reports;

	}

}
