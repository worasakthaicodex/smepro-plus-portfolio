package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.dto.productionDTO;
import com.smepro.models.actionplan;


public class productionDTOmapper implements RowMapper<productionDTO> {

	@Override
	public productionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		productionDTO productionDTOs = new productionDTO();
	
		productionDTOs.setGood_production_output1(rs.getInt("good_production_output1"));
		productionDTOs.setDefective_production_output1(rs.getInt("defective_production_output1"));
		
		productionDTOs.setData_due(rs.getString("data_due"));
		productionDTOs.setDate(rs.getString("date"));
		productionDTOs.setTimestart(rs.getString("timestart"));
		productionDTOs.setTimeend(rs.getString("timeend"));
		productionDTOs.setUnit(rs.getString("unit"));
	
		productionDTOs.setProduct(rs.getString("product"));
		productionDTOs.setLot(rs.getString("lot"));
		
		productionDTOs.setPd(rs.getString("pd"));
		productionDTOs.setBatch(rs.getString("batch"));
		productionDTOs.setUse_material(rs.getString("use_material"));
		productionDTOs.setWip(rs.getString("wip"));
		productionDTOs.setLot_wip(rs.getString("lot_wip"));
		return productionDTOs;
	}

}



