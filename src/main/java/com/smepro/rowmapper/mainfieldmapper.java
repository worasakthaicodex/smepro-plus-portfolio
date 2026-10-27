package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.backup;
import com.smepro.models.central;
import com.smepro.models.mainfield;

public class mainfieldmapper implements RowMapper<mainfield> {

	@Override
	public mainfield mapRow(ResultSet rs, int rowNum) throws SQLException {
		mainfield mainfields = new mainfield();
		mainfields.setField_id(rs.getLong("field_id"));
		mainfields.setField_inputtype(rs.getString("field_inputtype"));
		mainfields.setField_dataset(rs.getInt("field_dataset"));
		mainfields.setField_inputname(rs.getString("field_inputname"));
		mainfields.setField_name1(rs.getString("field_name1"));
		mainfields.setField_name2(rs.getString("field_name2"));
		mainfields.setField_idrefer(rs.getLong("field_idrefer"));
		mainfields.setField_idadd(rs.getInt("field_idadd"));
		mainfields.setField_dateadd(rs.getString("field_dateadd"));
		mainfields.setField_type(rs.getString("field_type"));
		return mainfields;
	}

}
