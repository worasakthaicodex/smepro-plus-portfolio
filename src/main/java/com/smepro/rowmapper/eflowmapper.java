package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.eflow;


public class eflowmapper implements RowMapper<eflow> {

	@Override
	public eflow mapRow(ResultSet rs, int rowNum) throws SQLException {
		eflow eflows = new eflow();
		eflows.setEflow_id(rs.getInt("eflow_id"));
		eflows.setEflow_name(rs.getString("eflow_name"));
		eflows.setEflow_idrefer(rs.getInt("eflow_idrefer"));
		eflows.setEflow_key1(rs.getString("eflow_key1"));
		eflows.setEflow_key2(rs.getString("eflow_key2"));
		eflows.setEflow_key3(rs.getString("eflow_key3"));
		eflows.setEflow_key4(rs.getString("eflow_key4"));
		eflows.setEflow_idform(rs.getInt("eflow_idform"));
		return eflows;
	}

}
