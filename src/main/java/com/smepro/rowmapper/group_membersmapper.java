package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.group_authorities;
import com.smepro.models.group_members;

public class group_membersmapper implements RowMapper<group_members> {

	@Override
	public group_members mapRow(ResultSet rs, int rowNum) throws SQLException {
		group_members group_memberss = new group_members();
		group_memberss.setId(rs.getInt("id"));
		group_memberss.setUsername(rs.getString("username"));
		group_memberss.setGroup_id(rs.getInt("group_id"));
		
		return group_memberss;
	}

}
