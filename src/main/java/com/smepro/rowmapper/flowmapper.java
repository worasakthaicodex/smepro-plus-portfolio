package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.datalink;
import com.smepro.models.flow;
import com.smepro.models.workflow;


public class flowmapper implements RowMapper<flow> {

	@Override
	public flow mapRow(ResultSet rs, int rowNum) throws SQLException {
		flow flows = new flow();
		
		flows.setId(rs.getInt("id"));
		flows.setEdata_id(rs.getInt("edata_id"));
		flows.setFlow_send(rs.getInt("flow_send"));
		flows.setFlow_to(rs.getInt("flow_to"));
		flows.setFlow_comment(rs.getString("flow_comment"));
		flows.setFlow_date(rs.getString("flow_date"));
		
		flows.setFlow_step(rs.getString("flow_step"));
		flows.setFlow_type(rs.getString("flow_type"));
		


		return flows;
	}

}

