package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.dto.materialDTO;
import com.smepro.models.actionplan;


public class materialDTOmapper implements RowMapper<materialDTO> {

	@Override
	public materialDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		materialDTO materialDTOs = new materialDTO();
		materialDTOs.setDepartment(rs.getString("department"));
		materialDTOs.setManufacturer(rs.getString("manufacturer"));
		materialDTOs.setMaterial(rs.getString("material"));
		materialDTOs.setLot_material(rs.getString("lot_material"));
		materialDTOs.setUnit(rs.getString("unit"));
		materialDTOs.setIncoming_or_deliver(rs.getString("incoming_or_deliver"));
		materialDTOs.setDate(rs.getString("date"));
		materialDTOs.setProduct(rs.getString("product"));
		materialDTOs.setLot(rs.getString("lot"));
		
		materialDTOs.setPd(rs.getString("pd"));
		materialDTOs.setBatch(rs.getString("batch"));
		return materialDTOs;
	}

}

