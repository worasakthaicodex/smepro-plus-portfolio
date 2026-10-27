package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.condition;
import com.smepro.models.eformfield;


public class eformfieldmapper implements RowMapper<eformfield> {

	@Override
	public eformfield mapRow(ResultSet rs, int rowNum) throws SQLException {
		eformfield eformfields = new eformfield();
		eformfields.setField_id(rs.getInt("field_id"));
		eformfields.setField_formid(rs.getInt("field_formid"));
		
		
		eformfields.setField_formrv(rs.getInt("field_formrv"));
		eformfields.setField_type(rs.getString("field_type"));
		eformfields.setField_label(rs.getString("field_label"));
		eformfields.setField_mainfield(rs.getInt("field_mainfield"));
		eformfields.setField_datarefertype(rs.getString("field_datarefertype"));
		eformfields.setField_datareferid(rs.getInt("field_datareferid"));
		eformfields.setField_datarefercols(rs.getString("field_datarefercols"));
		
		eformfields.setField_datareferid2(rs.getInt("field_datareferid2"));
		eformfields.setField_datareferid3(rs.getInt("field_datareferid3"));
		eformfields.setField_datareferid4(rs.getInt("field_datareferid4"));
		eformfields.setField_datareferid5(rs.getInt("field_datareferid5"));
		eformfields.setField_datareferid6(rs.getInt("field_datareferid6"));
		eformfields.setField_datareferid7(rs.getInt("field_datareferid7"));
		eformfields.setField_datareferid8(rs.getInt("field_datareferid8"));
		eformfields.setField_datareferid9(rs.getInt("field_datareferid9"));
		eformfields.setField_datareferid10(rs.getInt("field_datareferid10"));
		eformfields.setField_datareferid11(rs.getInt("field_datareferid11"));
		eformfields.setField_datareferid12(rs.getInt("field_datareferid12"));
		eformfields.setField_datareferid13(rs.getInt("field_datareferid13"));
		eformfields.setField_datareferid14(rs.getInt("field_datareferid14"));
		
		eformfields.setField_datareferid15(rs.getInt("field_datareferid15"));
		eformfields.setField_datareferid16(rs.getInt("field_datareferid16"));
		eformfields.setField_datareferid17(rs.getInt("field_datareferid17"));
		eformfields.setField_datareferid18(rs.getInt("field_datareferid18"));
		eformfields.setField_datareferid19(rs.getInt("field_datareferid19"));
		eformfields.setField_datareferid20(rs.getInt("field_datareferid20"));
		
		eformfields.setField_dataport(rs.getInt("field_dataport"));
		eformfields.setField_field_no(rs.getInt("field_field_no"));
		eformfields.setField_field_no2(rs.getInt("field_field_no2"));
		eformfields.setField_cal_type(rs.getString("field_cal_type"));
		eformfields.setField_postion(rs.getInt("field_postion"));
		eformfields.setField_cal_type2(rs.getString("field_cal_type2"));
		
		eformfields.setInput_size(rs.getInt("input_size"));
		eformfields.setAllow_adding_items(rs.getInt("allow_adding_items"));
		
		eformfields.setGroup_label_id(rs.getInt("group_label_id"));
		
		return eformfields;
	}

}


