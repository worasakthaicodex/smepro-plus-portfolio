package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dar;
import com.smepro.models.darsum;

public class darsummapper implements RowMapper<darsum> {

	@Override
	public darsum mapRow(ResultSet rs, int rowNum) throws SQLException {
		darsum dars = new darsum();
		dars.setMonth1(rs.getInt("month1"));
		dars.setMonth2(rs.getInt("month2"));
		dars.setMonth3(rs.getInt("month3"));
		dars.setMonth4(rs.getInt("month4"));
		dars.setMonth5(rs.getInt("month5"));
		dars.setMonth6(rs.getInt("month6"));
		dars.setMonth7(rs.getInt("month7"));
		dars.setMonth8(rs.getInt("month8"));
		dars.setMonth9(rs.getInt("month9"));
		dars.setMonth10(rs.getInt("month10"));
		dars.setMonth11(rs.getInt("month11"));
		dars.setMonth12(rs.getInt("month12"));
		return dars;
	}

}
