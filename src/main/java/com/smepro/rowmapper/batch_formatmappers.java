package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Goal;
import com.smepro.models.actionplan;
import com.smepro.models.batchformat;
import com.smepro.models.customreport;
import com.smepro.models.edata;
import com.smepro.models.file;


public class batch_formatmappers implements RowMapper<batchformat> {

	@Override
	public  batchformat mapRow(ResultSet rs, int rowNum) throws SQLException {
		batchformat  data = new  batchformat();
		 data.setId(rs.getInt("id"));
		 data.setCode_type(rs.getString("code_type"));
		 data.setFormat_string(rs.getString("format_string"));
		 data.setRequired_fields(rs.getString("required_fields"));
		 data.setAutoid_fields(rs.getString("autoid_fields"));
		 
		return data;
	
	}
}
	

	

