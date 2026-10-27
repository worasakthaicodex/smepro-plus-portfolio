package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.cm;
import com.smepro.models.fieldindex;

public class fieldindexmapper implements RowMapper<fieldindex> {

	@Override
	public fieldindex mapRow(ResultSet rs, int rowNum) throws SQLException {
		fieldindex fieldindexs = new fieldindex();
		fieldindexs.setFieldindex_id(rs.getInt("fieldindex_id"));
		fieldindexs.setFieldindex_idrefer(rs.getInt("fieldindex_idrefer"));
		fieldindexs.setField_index_idcondition(rs.getInt("field_index_idcondition"));
		fieldindexs.setField_index_idcondition2(rs.getInt("field_index_idcondition2"));
		fieldindexs.setFieldindex_type(rs.getString("fieldindex_type"));
		fieldindexs.setFieldindex_group1(rs.getInt("fieldindex_group1"));
		fieldindexs.setFieldindex_group2(rs.getInt("fieldindex_group2"));
		
		fieldindexs.setFieldindex_group1(rs.getInt("fieldindex_group1"));
		fieldindexs.setFieldindex_group2(rs.getInt("fieldindex_group2"));
		fieldindexs.setGood_output(rs.getInt("good_output"));
		fieldindexs.setDefective_output(rs.getInt("defective_output"));
		fieldindexs.setDepartment(rs.getString("department"));
		fieldindexs.setTarget(rs.getInt("target"));
		fieldindexs.setProduct(rs.getInt("product"));
		fieldindexs.setLot(rs.getInt("lot"));
		
		fieldindexs.setWip(rs.getInt("wip"));
		fieldindexs.setLot_wip(rs.getInt("lot_wip"));
		
		fieldindexs.setReceivede_fg(rs.getInt("receivede_fg"));
		fieldindexs.setHandover_fg(rs.getInt("handover_fg"));

		fieldindexs.setUse_wip(rs.getInt("use_wip"));
		fieldindexs.setReceivede_wip(rs.getInt("receivede_wip"));
		fieldindexs.setHandover_wip(rs.getInt("handover_wip"));
		
		fieldindexs.setUse_material(rs.getInt("use_material"));
		fieldindexs.setReceivede_material(rs.getInt("receivede_material"));
		fieldindexs.setHandover_material(rs.getInt("handover_material"));
		
		
		fieldindexs.setManufacturer(rs.getInt("manufacturer"));
		fieldindexs.setMaterial(rs.getInt("material"));
		fieldindexs.setLot_material(rs.getInt("lot_material"));
		fieldindexs.setUnit(rs.getInt("unit"));
		fieldindexs.setIncoming_or_deliver(rs.getInt("incoming_or_deliver"));
		
		fieldindexs.setPo(rs.getInt("po"));
		fieldindexs.setPr(rs.getInt("pr"));
		fieldindexs.setVc(rs.getInt("vc"));
		fieldindexs.setPd(rs.getInt("pd"));
		fieldindexs.setBatch(rs.getInt("batch"));
		fieldindexs.setDate(rs.getInt("date"));
		fieldindexs.setCustomer(rs.getInt("customer"));
		fieldindexs.setTimestart(rs.getInt("timestart"));
		fieldindexs.setTimeend(rs.getInt("timeend"));
		fieldindexs.setRegister(rs.getInt("register"));
		

		
		return fieldindexs;
	}
	
}
