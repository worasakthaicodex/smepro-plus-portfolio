package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.condition;


public class EdataReportbuildInmapper implements RowMapper<	EdataReportbuildIn> {

	@Override
	public 	EdataReportbuildIn mapRow(ResultSet rs, int rowNum) throws SQLException {
	EdataReportbuildIn data = new 	EdataReportbuildIn();
		data.setId(rs.getInt("id"));
		data.setBuildin_id_refer(rs.getInt("buildin_id_refer"));
		data.setBuildin_edata(rs.getInt("buildin_edata"));

		// --- ชุด 1-10 (เอาทั้ง Title และ Value) ---
		data.setColumns_title1(rs.getString("columns_title1"));
		data.setColumns_value1(rs.getString("columns_value1"));

		data.setColumns_title2(rs.getString("columns_title2"));
		data.setColumns_value2(rs.getString("columns_value2")); // แก้แล้ว

		data.setColumns_title3(rs.getString("columns_title3"));
		data.setColumns_value3(rs.getString("columns_value3")); // แก้แล้ว

		data.setColumns_title4(rs.getString("columns_title4"));
		data.setColumns_value4(rs.getString("columns_value4")); // แก้แล้ว

		data.setColumns_title5(rs.getString("columns_title5"));
		data.setColumns_value5(rs.getString("columns_value5")); // แก้แล้ว

		data.setColumns_title6(rs.getString("columns_title6"));
		data.setColumns_value6(rs.getString("columns_value6")); // แก้แล้ว

		data.setColumns_title7(rs.getString("columns_title7"));
		data.setColumns_value7(rs.getString("columns_value7")); // แก้แล้ว

		data.setColumns_title8(rs.getString("columns_title8"));
		data.setColumns_value8(rs.getString("columns_value8")); // แก้แล้ว

		data.setColumns_title9(rs.getString("columns_title9"));
		data.setColumns_value9(rs.getString("columns_value9")); // แก้แล้ว

		data.setColumns_title10(rs.getString("columns_title10"));
		data.setColumns_value10(rs.getString("columns_value10")); // แก้แล้ว

		// --- ชุด 11-20 (เอาเฉพาะ Value ตามสั่ง) ---
		data.setColumns_value11(rs.getString("columns_value11"));
		data.setColumns_value12(rs.getString("columns_value12"));
		data.setColumns_value13(rs.getString("columns_value13"));
		data.setColumns_value14(rs.getString("columns_value14"));
		data.setColumns_value15(rs.getString("columns_value15"));
		data.setColumns_value16(rs.getString("columns_value16"));
		data.setColumns_value17(rs.getString("columns_value17"));
		data.setColumns_value18(rs.getString("columns_value18"));
		data.setColumns_value19(rs.getString("columns_value19"));
		data.setColumns_value20(rs.getString("columns_value20"));
		
		
	
		return data;
	}

}

