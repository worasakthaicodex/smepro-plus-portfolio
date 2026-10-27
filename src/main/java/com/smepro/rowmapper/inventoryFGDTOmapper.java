package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.dto.inventoryDTO;
import com.smepro.models.backup;

public class inventoryFGDTOmapper implements RowMapper<inventoryDTO> {

	@Override
	public inventoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		inventoryDTO inventoryDTO = new inventoryDTO();
		inventoryDTO.setIncoming_or_deliver(rs.getString("incoming_or_deliver"));
		inventoryDTO.setHandover_fg(rs.getString("handover_fg"));
		inventoryDTO.setReceivede_fg(rs.getString("receivede_fg"));
		inventoryDTO.setDepartment(rs.getString("department"));
		inventoryDTO.setCustomer(rs.getString("customer"));
		inventoryDTO.setProduct(rs.getString("product"));
		inventoryDTO.setLot(rs.getString("lot"));
		inventoryDTO.setUnit(rs.getString("unit"));
		inventoryDTO.setDate(rs.getString("date"));
		inventoryDTO.setDate_due(rs.getString("data_due"));
		inventoryDTO.setPo(rs.getString("po"));
		
		inventoryDTO.setPd(rs.getString("pd"));
		inventoryDTO.setBatch(rs.getString("batch"));
		return inventoryDTO;
	}

}




