package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class othermappers2 implements RowMapper<other> {

	@Override
	public  other mapRow(ResultSet rs, int rowNum) throws SQLException {
		other  action = new  other();
		 
		 action.setId(rs.getInt("id"));  
		 action.setEdata_id(rs.getInt("edata_id"));
		 action.setTempId(rs.getString("tempId"));
		  action.setProduct_code(rs.getString("product_code"));
		  action.setProduct_name(rs.getString("product_name"));
		  action.setLot_batch(rs.getString("lot_batch"));
		  action.setProcess_step(rs.getString("process_step"));
		  action.setDepartment(rs.getString("department"));
		  action.setDoc_code(rs.getString("doc_code"));
		  action.setList(rs.getString("list"));
		  action.setEmployee(rs.getString("employee"));
		  action.setInspection(rs.getString("inspection"));
		  action.setEquipment(rs.getString("equipment"));
		  action.setDate_start(rs.getString("date_start"));

		return action;
		
	
	}
	
	


	
	
	
	
	
}
