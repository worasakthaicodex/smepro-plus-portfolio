package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.backup;
import com.smepro.models.task;

public class taskmapper implements RowMapper<task> {

	@Override
	public task mapRow(ResultSet rs, int rowNum) throws SQLException {
		task tasks = new task();
		tasks.setTask_id(rs.getInt("task_id"));
		tasks.setKeyactivity_id(rs.getInt("keyactivity_id"));
		tasks.setTask_name(rs.getString("task_name"));
		tasks.setTask_note(rs.getString("task_note"));
		tasks.setTask_idrefer(rs.getInt("task_idrefer"));
		tasks.setTask_idadd(rs.getInt("task_idadd"));
		tasks.setTask_status(rs.getInt("task_status"));
		tasks.setTask_date(rs.getString("task_date"));
		tasks.setTask_datestart(rs.getString("task_datestart"));
		tasks.setTask_dateend(rs.getString("task_dateend"));
		tasks.setTask_iduser(rs.getString("task_iduser"));
		tasks.setTask_cm(rs.getInt("task_cm"));
		tasks.setTask_alert(rs.getInt("task_alert"));
		
		tasks.setTask_budget(rs.getString("task_budget"));
		tasks.setTask_payment(rs.getString("task_payment"));
		
		
		tasks.setBudget_source1(rs.getInt("budget_source1"));
		tasks.setBudget_source2(rs.getInt("budget_source2"));
		tasks.setBudget_source3(rs.getInt("budget_source3"));
		tasks.setTask_budget1(rs.getDouble("task_budget1"));
		tasks.setTask_budget2(rs.getDouble("task_budget2"));
		tasks.setTask_budget3(rs.getDouble("task_budget3"));
		tasks.setUse_budget(rs.getDouble("use_budget"));
		tasks.setOutcome(rs.getString("outcome"));
		
		tasks.setActivity_task(rs.getString("activity_task"));
		tasks.setEnd_task(rs.getString("end_task"));
		tasks.setTask_idcheck(rs.getInt("task_idcheck"));
		return tasks;
	}

}

