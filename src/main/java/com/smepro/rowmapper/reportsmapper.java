package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.report_mapping;
import com.smepro.models.report_pn;

public class reportsmapper implements RowMapper<report_mapping> {

	@Override
	public report_mapping mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		report_mapping report_mappings = new report_mapping();
		report_mappings.setMapping_id(rs.getInt("mapping_id"));
		report_mappings.setMapping_id_refer(rs.getInt("mapping_id_refer"));
		report_mappings.setMapping_id_map(rs.getInt("mapping_id_map"));
		report_mappings.setMapping_id_add(rs.getInt("mapping_id_add"));
		
		report_mappings.setMapping_key1(rs.getString("mapping_key1"));
		report_mappings.setMapping_key2(rs.getString("mapping_key2"));
		report_mappings.setMapping_key3(rs.getString("mapping_key3"));
		report_mappings.setMapping_key4(rs.getString("mapping_key4"));
		report_mappings.setMapping_key5(rs.getString("mapping_key5"));
		report_mappings.setMapping_key6(rs.getString("mapping_key6"));
		report_mappings.setMapping_key7(rs.getString("mapping_key7"));
		report_mappings.setMapping_key8(rs.getString("mapping_key8"));
		report_mappings.setMapping_key9(rs.getString("mapping_key9"));
		report_mappings.setMapping_key10(rs.getString("mapping_key10"));
		
	
		return report_mappings;
	}

}
