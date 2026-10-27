package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.MasterBudget;
import com.smepro.models.Milestone;
import com.smepro.models.Outcome;
import com.smepro.models.Risk;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class Milestonemapper implements RowMapper<Milestone> {

	@Override
	public Milestone mapRow(ResultSet rs, int rowNum) throws SQLException {
		Milestone data = new Milestone();
		data.setId(rs.getInt("id"));
		data.setProject_id(rs.getInt("project_id"));
		data.setDetail(rs.getString("detail"));
		data.setDate_due(rs.getString("date_due"));
		data.setDate_alert(rs.getString("date_alert"));
		data.setBilling_amount(rs.getDouble("billing_amount"));
		data.setUser_work_id(rs.getInt("user_work_id"));
		data.setUser_work_name(rs.getString("user_work_name"));
		data.setStatus(rs.getInt("status"));
		
		
		data.setProcessing_date(rs.getString("processing_date"));
		data.setCollected_payment(rs.getDouble("collected_payment"));
		
		return data;
	}

}

