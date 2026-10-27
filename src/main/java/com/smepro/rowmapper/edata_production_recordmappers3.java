package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.edata_production_record;




public class edata_production_recordmappers3 implements RowMapper<edata_production_record> {

	@Override
	public  edata_production_record mapRow(ResultSet rs, int rowNum) throws SQLException {
		edata_production_record  production = new  edata_production_record();

		 production.setId(rs.getInt("id"));
		 production.setEdata_id(rs.getInt("edata_id"));
		 production.setLot_batch(rs.getString("lot_batch"));
		 production.setProduct_code(rs.getString("product_code")); 
		 production.setProduct_name(rs.getString("product_name"));
		  production.setProcess_step(rs.getString("process_step")); 
		  production.setDepartment(rs.getString("department"));
		  production.setOrder_quantity(rs.getInt("order_quantity"));
		  production.setActual_output(rs.getInt("actual_output"));
		  production.setDefective_output(rs.getInt("defective_output"));
		  production.setRecord_date(rs.getString("record_date"));
		  production.setRecorder(rs.getString("recorder"));
		  production.setInspection_result(rs.getString("inspection_result"));
		  production.setRemarks(rs.getString("remarks"));
		  production.setOperator(rs.getString("operator"));
		  production.setLine(rs.getString("line"));
		  production.setStatus(rs.getString("status"));
		  production.setShift(rs.getString("shift"));
		  production.setUnit_of_measure(rs.getString("unit_of_measure"));  
		  production.setStart_date_time(rs.getString("start_date_time"));
		  production.setEnd_date_time(rs.getString("end_date_time"));
		  production.setMachine_id(rs.getString("machine_id")); 
		  production.setDowntime_title(rs.getString("downtime_title"));
		  production.setSpeed(rs.getDouble("speed")); 
		  production.setSetup_time(rs.getDouble("setup_time"));
		  production.setDowntime(rs.getDouble("downtime"));
		  production.setReference_code(rs.getString("reference_code"));
		
		return production;
	
	}

	
}
