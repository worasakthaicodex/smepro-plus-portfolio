package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.edata_production_record;




public class edata_production_recordmappers2 implements RowMapper<edata_production_record> {

	@Override
	public  edata_production_record mapRow(ResultSet rs, int rowNum) throws SQLException {
		edata_production_record  production = new  edata_production_record();

		 production.setId(rs.getInt("id"));
		 production.setEdata_id(rs.getInt("edata_id"));
		 production.setProcess_step(rs.getString("process_step"));
		 production.setStart_date_time(rs.getString("start_date_time"));
		 production.setStatus(rs.getString("status"));
		  
		return production;
	
	}

	
}
