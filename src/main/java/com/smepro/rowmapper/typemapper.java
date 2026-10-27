package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.type;

public class typemapper implements RowMapper<type> {

	@Override
	public type mapRow(ResultSet rs, int rowNum) throws SQLException {
		type types = new type();
		types.setType_id(rs.getInt("type_id"));
		types.setType_name(rs.getString("type_name"));
		types.setType_status(rs.getInt("type_status"));
		types.setType_type(rs.getString("type_type"));
		return types;
	}

}
