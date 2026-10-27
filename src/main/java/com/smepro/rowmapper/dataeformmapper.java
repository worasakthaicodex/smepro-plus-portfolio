package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dataeform;


public class dataeformmapper implements RowMapper<dataeform> {

	@Override
	public dataeform mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		dataeform dataeforms = new dataeform();
		
		
		dataeforms.setDataeform_id(rs.getInt("dataeform_id"));
		dataeforms.setDataeform_idadd(rs.getInt("dataeform_idadd"));
		dataeforms.setDataeform_idapp(rs.getInt("dataeform_idapp"));
		dataeforms.setDataeform_idrefer(rs.getInt("dataeform_idrefer"));
		dataeforms.setDataeform_condition_id(rs.getInt("dataeform_condition_id"));	
		dataeforms.setCondition_support4(rs.getString("condition_support4"));
		dataeforms.setCondition_support5_1(rs.getString("condition_support5_1"));
		dataeforms.setCondition_support5_2(rs.getString("condition_support5_2"));
		dataeforms.setCondition_1_note1(rs.getString("condition_1_note1"));
		dataeforms.setCondition_1_note2(rs.getString("condition_1_note2"));
		dataeforms.setCondition_1_note3(rs.getString("condition_1_note3"));
		dataeforms.setCondition_1_total(rs.getString("condition_1_total"));
		dataeforms.setCondition_2_note1(rs.getString("condition_2_note1"));
		dataeforms.setCondition_2_note2(rs.getString("condition_2_note2"));
		dataeforms.setCondition_2_note3(rs.getString("condition_2_note3"));
		dataeforms.setCondition_2_total(rs.getString("condition_2_total"));
		dataeforms.setCondition_3_note1(rs.getString("condition_3_note1"));
		dataeforms.setCondition_3_note2(rs.getString("condition_3_note2"));
		dataeforms.setCondition_3_note3(rs.getString("condition_3_note3"));
		dataeforms.setCondition_3_total(rs.getString("condition_3_total"));
		dataeforms.setCondition_4_note1(rs.getString("condition_4_note1"));
		dataeforms.setCondition_4_note2(rs.getString("condition_4_note2"));
		dataeforms.setCondition_4_note3(rs.getString("condition_4_note3"));
		dataeforms.setCondition_4_total(rs.getString("condition_4_total"));
		dataeforms.setCondition_5_note1(rs.getString("condition_5_note1"));
		dataeforms.setCondition_5_note2(rs.getString("condition_5_note2"));
		dataeforms.setCondition_5_note3(rs.getString("condition_5_note3"));
		dataeforms.setCondition_5_total(rs.getString("condition_5_total"));
		dataeforms.setCondition_6_note1(rs.getString("condition_6_note1"));
		dataeforms.setCondition_6_note2(rs.getString("condition_6_note2"));
		dataeforms.setCondition_6_note3(rs.getString("condition_6_note3"));
		dataeforms.setCondition_6_total(rs.getString("condition_6_total"));
		dataeforms.setCondition_7_note1(rs.getString("condition_7_note1"));
		dataeforms.setCondition_7_note2(rs.getString("condition_7_note2"));
		dataeforms.setCondition_7_note3(rs.getString("condition_7_note3"));
		dataeforms.setCondition_7_total(rs.getString("condition_7_total"));
		dataeforms.setCondition_8_note1(rs.getString("condition_8_note1"));
		dataeforms.setCondition_8_note2(rs.getString("condition_8_note2"));
		dataeforms.setCondition_8_note3(rs.getString("condition_8_note3"));
		dataeforms.setCondition_8_total(rs.getString("condition_8_total"));
		dataeforms.setCondition_9_note1(rs.getString("condition_9_note1"));
		dataeforms.setCondition_9_note2(rs.getString("condition_9_note2"));
		dataeforms.setCondition_9_note3(rs.getString("condition_9_note3"));
		dataeforms.setCondition_9_total(rs.getString("condition_9_total"));
		dataeforms.setCondition_10_note1(rs.getString("condition_10_note1"));
		dataeforms.setCondition_10_note2(rs.getString("condition_10_note2"));
		dataeforms.setCondition_10_note3(rs.getString("condition_10_note3"));
		dataeforms.setCondition_10_total(rs.getString("condition_10_total"));
		dataeforms.setCondition_11_note1(rs.getString("condition_11_note1"));
		dataeforms.setCondition_11_note2(rs.getString("condition_11_note2"));
		dataeforms.setCondition_11_note3(rs.getString("condition_11_note3"));
		dataeforms.setCondition_11_total(rs.getString("condition_11_total"));
		dataeforms.setCondition_12_note1(rs.getString("condition_12_note1"));
		dataeforms.setCondition_12_note2(rs.getString("condition_12_note2"));
		dataeforms.setCondition_12_note3(rs.getString("condition_12_note3"));
		dataeforms.setCondition_12_total(rs.getString("condition_12_total"));
		dataeforms.setCondition_13_note1(rs.getString("condition_13_note1"));
		dataeforms.setCondition_13_note2(rs.getString("condition_13_note2"));
		dataeforms.setCondition_13_note3(rs.getString("condition_13_note3"));
		dataeforms.setCondition_13_total(rs.getString("condition_13_total"));
		dataeforms.setCondition_14_note1(rs.getString("condition_14_note1"));
		dataeforms.setCondition_14_note2(rs.getString("condition_14_note2"));
		dataeforms.setCondition_14_note3(rs.getString("condition_14_note3"));
		dataeforms.setCondition_14_total(rs.getString("condition_14_total"));
		dataeforms.setCondition_15_note1(rs.getString("condition_15_note1"));
		dataeforms.setCondition_15_note2(rs.getString("condition_15_note2"));
		dataeforms.setCondition_15_note3(rs.getString("condition_15_note3"));
		dataeforms.setCondition_15_total(rs.getString("condition_15_total"));
	
		
		return dataeforms;
	}

	
	
}



