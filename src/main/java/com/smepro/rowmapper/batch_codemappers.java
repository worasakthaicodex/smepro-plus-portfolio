package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.smepro.models.batch_code;



public class batch_codemappers implements RowMapper<batch_code> {

	@Override
	public  batch_code mapRow(ResultSet rs, int rowNum) throws SQLException {
		 batch_code  data = new  batch_code();
		
		 data.setId(rs.getInt("id"));
		 data.setPrefix(rs.getString("prefix"));
		 data.setAuto_id(rs.getInt("auto_id"));

		return data;
	
	}
}
	


