package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.FieldConfig;
import com.smepro.models.condition;
import com.smepro.models.eformfield;


public class FieldConfigmapper implements RowMapper<FieldConfig> {

	@Override
	public FieldConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		FieldConfig data = new FieldConfig();
		
		data.setId(rs.getInt("id"));
		data.setId_refer(rs.getInt("id_refer"));
		data.setProcess_id(rs.getInt("process_id"));
		data.setAllow_adding_items(rs.getInt("allow_adding_items"));
		data.setData_access_interface(rs.getInt("data_access_interface"));
		return data;
	}

}

