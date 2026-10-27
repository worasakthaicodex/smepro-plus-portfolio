package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Keyactivity;
import com.smepro.models.MasterBudget;
import com.smepro.models.Milestone;
import com.smepro.models.Outcome;
import com.smepro.models.Risk;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class Keyactivitymapper implements RowMapper<Keyactivity> {

	@Override
	public Keyactivity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Keyactivity data = new Keyactivity();
		data.setId(rs.getInt("id"));
		data.setProject_id(rs.getInt("project_id"));
		data.setExpected_results(rs.getString("expected_results"));
		data.setSuccess_indicators(rs.getString("success_indicators"));
		data.setTask(rs.getInt("task"));
		data.setProcess(rs.getInt("process"));
		
		data.setDatestart(rs.getString("datestart"));
		data.setDateend(rs.getString("dateend"));
		data.setStatus(rs.getInt("status"));

		return data;
	}

}

