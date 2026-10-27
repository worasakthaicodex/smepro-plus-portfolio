package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.group_authorities;



public class group_authoritiesmapper implements RowMapper<group_authorities> {

	@Override
	public group_authorities mapRow(ResultSet rs, int rowNum) throws SQLException {
		group_authorities group_authoritiess = new group_authorities();
		group_authoritiess.setId(rs.getInt("id"));
		group_authoritiess.setAuthority(rs.getString("authority"));
		group_authoritiess.setGroup_id(rs.getInt("group_id"));
		group_authoritiess.setType(rs.getInt("type"));
		group_authoritiess.setMain(rs.getInt("main"));
		
		return group_authoritiess;
	}

}
