package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dar;

public class darmapper3 implements RowMapper<dar> {

	@Override
	public dar mapRow(ResultSet rs, int rowNum) throws SQLException {
		dar dars = new dar();
		dars.setDar_step(rs.getInt("dar_step"));
		dars.setUser_name(rs.getString("user_name"));

		try {
			dars.setUser_img(rs.getString("user_img"));
	    } catch (SQLException e) {
	    	dars.setUser_img(null);
	    }

		return dars;
	}

}
