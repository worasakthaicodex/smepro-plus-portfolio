package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;


public class actionplanmapper2 implements RowMapper<actionplan> {

	@Override
	public actionplan mapRow(ResultSet rs, int rowNum) throws SQLException {
		actionplan actionplans = new actionplan();
		actionplans.setActionplan_id(rs.getInt("actionplan_id"));
		actionplans.setActionplan_idrfer(rs.getInt("actionplan_idrfer"));
		actionplans.setActionplan_status(rs.getInt("actionplan_status"));
		actionplans.setActionplan_datedue(rs.getString("actionplan_datedue"));
		actionplans.setActionplan_dateadd(rs.getString("actionplan_dateadd"));
		actionplans.setActionplan_responsible(rs.getString("actionplan_responsible"));
		actionplans.setActionplan_inspector(rs.getString("actionplan_inspector"));
		actionplans.setActionplan_problem(rs.getString("actionplan_problem"));
		actionplans.setActionplan_cause(rs.getString("actionplan_cause"));
		actionplans.setActionplan_solutions(rs.getString("actionplan_solutions"));
		actionplans.setActionplan_result(rs.getString("actionplan_result"));
		actionplans.setActionplan_comment(rs.getString("actionplan_comment"));
		actionplans.setActionplan_expenses(rs.getString("actionplan_expenses"));
		actionplans.setActionplan_payment(rs.getString("actionplan_payment"));
		
		actionplans.setSubmission(rs.getInt("submission"));
		return actionplans;
	}

}
