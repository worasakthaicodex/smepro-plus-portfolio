package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.condition;


public class conditionmapper2 implements RowMapper<condition> {

	@Override
	public condition mapRow(ResultSet rs, int rowNum) throws SQLException {
		condition conditions = new condition();
		conditions.setCondition_id(rs.getInt("condition_id"));
		conditions.setCondition_idadd(rs.getInt("condition_idadd"));
		conditions.setCondition_nameadd(rs.getString("condition_nameadd"));
		conditions.setCondition_name(rs.getString("upload_pn_note"));
		conditions.setCondition_title(rs.getString("condition_name"));
		conditions.setCondition_subtitle(rs.getString("condition_subtitle"));
		conditions.setCondition_status(rs.getInt("condition_status"));
		conditions.setChartdiv1data(rs.getString("chartdiv1data"));
		conditions.setChartdiv2data(rs.getString("chartdiv2data"));
		conditions.setChartdiv3data(rs.getString("chartdiv3data"));
		conditions.setChartdiv1label(rs.getString("chartdiv1label"));
		conditions.setChartdiv2label(rs.getString("chartdiv2label"));
		conditions.setChartdiv3label(rs.getString("chartdiv3label"));
		conditions.setShows(rs.getString("shows"));
		conditions.setCondition_rv(rs.getInt("condition_rv"));
		conditions.setCondition_industry(rs.getInt("condition_industry"));
		conditions.setCondition_department(rs.getInt("condition_department"));
		conditions.setCondition_type(rs.getInt("condition_type"));
		conditions.setCondition_sub(rs.getInt("condition_sub"));
		conditions.setCondition_sub2(rs.getInt("condition_sub2"));
		conditions.setCondition_sub3(rs.getInt("condition_sub3"));
		conditions.setCondition_titlerefer(rs.getString("condition_titlerefer"));
		conditions.setCondition_report(rs.getString("condition_report"));
		
		conditions.setCalculation_port1(rs.getInt("calculation_port1"));
		conditions.setCalculation_port2(rs.getInt("calculation_port2"));
		conditions.setCondition_group(rs.getString("condition_group"));
		return conditions;
	}

}

