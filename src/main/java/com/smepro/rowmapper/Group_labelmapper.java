package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Audit;
import com.smepro.models.Group_label;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.file;


public class Group_labelmapper implements RowMapper<Group_label> {

	@Override
	public  Group_label mapRow(ResultSet rs, int rowNum) throws SQLException {
			 Group_label  data = new  Group_label();
			 data.setId(rs.getInt("id"));
			 data.setGroup_label_name(rs.getString("group_label_name"));
			return data;
		
	}
	
}
