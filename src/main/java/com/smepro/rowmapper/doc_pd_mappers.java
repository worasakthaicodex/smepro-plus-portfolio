package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.control_steps;


public class doc_pd_mappers implements RowMapper<control_steps> {

	@Override
	public control_steps mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		control_steps steps = new control_steps();
		
		if(rs.getString("doc_type")  != null ) {
				if(rs.getInt("doc_rv")  > 0) {
				steps.setProduction_directive(rs.getString("doc_type")+rs.getString("doc_date")+rs.getInt("doc_autoid")+"-"+rs.getInt("doc_rv"));
				}else {
					steps.setProduction_directive(rs.getString("doc_type")+rs.getString("doc_date")+rs.getString("doc_autoid"));
				}
		}
		
		steps.setBatch(rs.getString("lot_batch"));
		
		return steps;
	
		
	}

	
	
	

}
