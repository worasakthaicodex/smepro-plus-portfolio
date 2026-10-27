package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.workflow;


public class workflowmapper implements RowMapper<workflow> {

	@Override
	public workflow mapRow(ResultSet rs, int rowNum) throws SQLException {
		workflow workflows = new workflow();
		workflows.setWorkflow_id(rs.getInt("workflow_id"));
		workflows.setWorkflow_name(rs.getString("workflow_name"));
		workflows.setWorkflow_note(rs.getString("workflow_note"));
		workflows.setWorkflow_dateadd(rs.getString("workflow_dateadd"));
		workflows.setWorkflow_key1(rs.getString("workflow_key1"));
		workflows.setWorkflow_key2(rs.getString("workflow_key2"));
		workflows.setWorkflow_key3(rs.getString("workflow_key3"));
		workflows.setWorkflow_key4(rs.getString("workflow_key4"));
		workflows.setWorkflow_type(rs.getInt("workflow_type"));
		
		workflows.setWorkflow_idadmin(rs.getInt("workflow_idadmin"));
		workflows.setWorkflow_idadd(rs.getInt("workflow_idadd"));
		workflows.setWorkflow_app(rs.getString("workflow_app"));
		
		workflows.setWorkflow_folder(rs.getString("workflow_folder"));
		workflows.setWorkflow_location(rs.getString("workflow_location"));
		
		workflows.setWorkflow_maindb(rs.getString("workflow_maindb"));
		workflows.setWorkflow_subdb(rs.getString("workflow_subdb"));
		workflows.setWorkflow_subdb2(rs.getString("workflow_subdb2"));
		workflows.setWorkflow_subdb3(rs.getString("workflow_subdb3"));
		
		workflows.setTracking_step(rs.getString("tracking_step"));
		workflows.setTracking_status(rs.getInt("tracking_status"));
		workflows.setTracking_type(rs.getString("tracking_type"));
		workflows.setTracking_process_type(rs.getString("tracking_process_type"));
		workflows.setTraceability_status(rs.getInt("traceability_status"));
		
		
		workflows.setTracking_main_step(rs.getString("tracking_main_step"));
		workflows.setTracking_main_status(rs.getInt("tracking_main_status"));
		workflows.setTracking_main_type(rs.getString("tracking_main_type"));
		
		workflows.setWorkflow_join(rs.getString("workflow_join"));
		return workflows;
	}

}

