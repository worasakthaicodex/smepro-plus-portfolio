package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.member;

public class membermapper implements RowMapper<member> {

	@Override
	public member mapRow(ResultSet rs, int rowNum) throws SQLException {
		member members = new member();
		members.setId(rs.getInt("member_id"));
		members.setName(rs.getString("member_name"));
		members.setDate(rs.getString("member_date"));
		return members;
	}

}
