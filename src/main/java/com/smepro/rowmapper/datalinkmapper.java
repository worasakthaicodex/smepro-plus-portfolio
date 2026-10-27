package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.datalink;
import com.smepro.models.workflow;


public class datalinkmapper implements RowMapper<datalink> {

	@Override
	public datalink mapRow(ResultSet rs, int rowNum) throws SQLException {
		datalink datalinks = new datalink();
		
		datalinks.setDatalink_id(rs.getInt("datalink_id"));
		datalinks.setDatalink_idrefer(rs.getInt("datalink_idrefer"));
		datalinks.setDatalink_workflow(rs.getInt("datalink_workflow"));
		datalinks.setDatalink_name(rs.getString("datalink_name"));
		datalinks.setKey1(rs.getInt("key1"));
		datalinks.setKey2(rs.getInt("key2"));
		datalinks.setKey3(rs.getInt("key3"));
		
		return datalinks;
	}

}
