package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dar;
import com.smepro.models.darsum;

public class darsummapper2 implements RowMapper<darsum> {

	@Override
	public darsum mapRow(ResultSet rs, int rowNum) throws SQLException {
		darsum dars = new darsum();
		dars.setYear1(rs.getInt("year1"));
		dars.setYear2(rs.getInt("year2"));
		dars.setYear3(rs.getInt("year3"));
		return dars;
	}

}
