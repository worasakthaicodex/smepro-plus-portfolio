package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Outcome;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class Outcomemapper implements RowMapper<Outcome> {

	@Override
	public Outcome mapRow(ResultSet rs, int rowNum) throws SQLException {
		Outcome data = new Outcome();
		data.setId(rs.getInt("id"));
		data.setProject_id(rs.getInt("project_id"));
		data.setExpected_results(rs.getString("expected_results"));
		data.setSuccess_indicators(rs.getString("success_indicators"));
		data.setMetric(rs.getString("metric"));
		data.setTask(rs.getInt("task"));
		data.setProcess(rs.getInt("process"));
		return data;
	}

}

