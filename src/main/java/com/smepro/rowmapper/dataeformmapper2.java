package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dataeform;


public class dataeformmapper2 implements RowMapper<dataeform> {

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
		
		
		dataeforms.setMonth1_1(rs.getString("month1_1"));
		dataeforms.setMonth1_2(rs.getString("month1_2"));
		dataeforms.setMonth1_3(rs.getString("month1_3"));
		dataeforms.setMonth1_4(rs.getString("month1_4"));
		dataeforms.setMonth1_5(rs.getString("month1_5"));
		dataeforms.setMonth1_6(rs.getString("month1_6"));
		dataeforms.setMonth1_7(rs.getString("month1_7"));
		dataeforms.setMonth1_8(rs.getString("month1_8"));
		dataeforms.setMonth1_9(rs.getString("month1_9"));
		dataeforms.setMonth1_10(rs.getString("month1_10"));
		dataeforms.setMonth1_11(rs.getString("month1_11"));
		dataeforms.setMonth1_12(rs.getString("month1_12"));
		dataeforms.setMonth1_13(rs.getString("month1_13"));
		dataeforms.setMonth1_14(rs.getString("month1_14"));
		dataeforms.setMonth1_15(rs.getString("month1_15"));
		
		dataeforms.setMonth2_1(rs.getString("month2_1"));
		dataeforms.setMonth2_2(rs.getString("month2_2"));
		dataeforms.setMonth2_3(rs.getString("month2_3"));
		dataeforms.setMonth2_4(rs.getString("month2_4"));
		dataeforms.setMonth2_5(rs.getString("month2_5"));
		dataeforms.setMonth2_6(rs.getString("month2_6"));
		dataeforms.setMonth2_7(rs.getString("month2_7"));
		dataeforms.setMonth2_8(rs.getString("month2_8"));
		dataeforms.setMonth2_9(rs.getString("month2_9"));
		dataeforms.setMonth2_10(rs.getString("month2_10"));
		dataeforms.setMonth2_11(rs.getString("month2_11"));
		dataeforms.setMonth2_12(rs.getString("month2_12"));
		dataeforms.setMonth2_13(rs.getString("month2_13"));
		dataeforms.setMonth2_14(rs.getString("month2_14"));
		dataeforms.setMonth2_15(rs.getString("month2_15"));
		
		
		dataeforms.setMonth3_1(rs.getString("month3_1"));
		dataeforms.setMonth3_2(rs.getString("month3_2"));
		dataeforms.setMonth3_3(rs.getString("month3_3"));
		dataeforms.setMonth3_4(rs.getString("month3_4"));
		dataeforms.setMonth3_5(rs.getString("month3_5"));
		dataeforms.setMonth3_6(rs.getString("month3_6"));
		dataeforms.setMonth3_7(rs.getString("month3_7"));
		dataeforms.setMonth3_8(rs.getString("month3_8"));
		dataeforms.setMonth3_9(rs.getString("month3_9"));
		dataeforms.setMonth3_10(rs.getString("month3_10"));
		dataeforms.setMonth3_11(rs.getString("month3_11"));
		dataeforms.setMonth3_12(rs.getString("month3_12"));
		dataeforms.setMonth3_13(rs.getString("month3_13"));
		dataeforms.setMonth3_14(rs.getString("month3_14"));
		dataeforms.setMonth3_15(rs.getString("month3_15"));
		
		dataeforms.setMonth4_1(rs.getString("month4_1"));
		dataeforms.setMonth4_2(rs.getString("month4_2"));
		dataeforms.setMonth4_3(rs.getString("month4_3"));
		dataeforms.setMonth4_4(rs.getString("month4_4"));
		dataeforms.setMonth4_5(rs.getString("month4_5"));
		dataeforms.setMonth4_6(rs.getString("month4_6"));
		dataeforms.setMonth4_7(rs.getString("month4_7"));
		dataeforms.setMonth4_8(rs.getString("month4_8"));
		dataeforms.setMonth4_9(rs.getString("month4_9"));
		dataeforms.setMonth4_10(rs.getString("month4_10"));
		dataeforms.setMonth4_11(rs.getString("month4_11"));
		dataeforms.setMonth4_12(rs.getString("month4_12"));
		dataeforms.setMonth4_13(rs.getString("month4_13"));
		dataeforms.setMonth4_14(rs.getString("month4_14"));
		dataeforms.setMonth4_15(rs.getString("month4_15"));
		
		
		dataeforms.setMonth5_1(rs.getString("month5_1"));
		dataeforms.setMonth5_2(rs.getString("month5_2"));
		dataeforms.setMonth5_3(rs.getString("month5_3"));
		dataeforms.setMonth5_4(rs.getString("month5_4"));
		dataeforms.setMonth5_5(rs.getString("month5_5"));
		dataeforms.setMonth5_6(rs.getString("month5_6"));
		dataeforms.setMonth5_7(rs.getString("month5_7"));
		dataeforms.setMonth5_8(rs.getString("month5_8"));
		dataeforms.setMonth5_9(rs.getString("month5_9"));
		dataeforms.setMonth5_10(rs.getString("month5_10"));
		dataeforms.setMonth5_11(rs.getString("month5_11"));
		dataeforms.setMonth5_12(rs.getString("month5_12"));
		dataeforms.setMonth5_13(rs.getString("month5_13"));
		dataeforms.setMonth5_14(rs.getString("month5_14"));
		dataeforms.setMonth5_15(rs.getString("month5_15"));
		
		dataeforms.setMonth6_1(rs.getString("month6_1"));
		dataeforms.setMonth6_2(rs.getString("month6_2"));
		dataeforms.setMonth6_3(rs.getString("month6_3"));
		dataeforms.setMonth6_4(rs.getString("month6_4"));
		dataeforms.setMonth6_5(rs.getString("month6_5"));
		dataeforms.setMonth6_6(rs.getString("month6_6"));
		dataeforms.setMonth6_7(rs.getString("month6_7"));
		dataeforms.setMonth6_8(rs.getString("month6_8"));
		dataeforms.setMonth6_9(rs.getString("month6_9"));
		dataeforms.setMonth6_10(rs.getString("month6_10"));
		dataeforms.setMonth6_11(rs.getString("month6_11"));
		dataeforms.setMonth6_12(rs.getString("month6_12"));
		dataeforms.setMonth6_13(rs.getString("month6_13"));
		dataeforms.setMonth6_14(rs.getString("month6_14"));
		dataeforms.setMonth6_15(rs.getString("month6_15"));
	
		dataeforms.setMonth7_1(rs.getString("month7_1"));
		dataeforms.setMonth7_2(rs.getString("month7_2"));
		dataeforms.setMonth7_3(rs.getString("month7_3"));
		dataeforms.setMonth7_4(rs.getString("month7_4"));
		dataeforms.setMonth7_5(rs.getString("month7_5"));
		dataeforms.setMonth7_6(rs.getString("month7_6"));
		dataeforms.setMonth7_7(rs.getString("month7_7"));
		dataeforms.setMonth7_8(rs.getString("month7_8"));
		dataeforms.setMonth7_9(rs.getString("month7_9"));
		dataeforms.setMonth7_10(rs.getString("month7_10"));
		dataeforms.setMonth7_11(rs.getString("month7_11"));
		dataeforms.setMonth7_12(rs.getString("month7_12"));
		dataeforms.setMonth7_13(rs.getString("month7_13"));
		dataeforms.setMonth7_14(rs.getString("month7_14"));
		dataeforms.setMonth7_15(rs.getString("month7_15"));
		
		dataeforms.setMonth8_1(rs.getString("month8_1"));
		dataeforms.setMonth8_2(rs.getString("month8_2"));
		dataeforms.setMonth8_3(rs.getString("month8_3"));
		dataeforms.setMonth8_4(rs.getString("month8_4"));
		dataeforms.setMonth8_5(rs.getString("month8_5"));
		dataeforms.setMonth8_6(rs.getString("month8_6"));
		dataeforms.setMonth8_7(rs.getString("month8_7"));
		dataeforms.setMonth8_8(rs.getString("month8_8"));
		dataeforms.setMonth8_9(rs.getString("month8_9"));
		dataeforms.setMonth8_10(rs.getString("month8_10"));
		dataeforms.setMonth8_11(rs.getString("month8_11"));
		dataeforms.setMonth8_12(rs.getString("month8_12"));
		dataeforms.setMonth8_13(rs.getString("month8_13"));
		dataeforms.setMonth8_14(rs.getString("month8_14"));
		dataeforms.setMonth8_15(rs.getString("month8_15"));
		
		dataeforms.setMonth9_1(rs.getString("month9_1"));
		dataeforms.setMonth9_2(rs.getString("month9_2"));
		dataeforms.setMonth9_3(rs.getString("month9_3"));
		dataeforms.setMonth9_4(rs.getString("month9_4"));
		dataeforms.setMonth9_5(rs.getString("month9_5"));
		dataeforms.setMonth9_6(rs.getString("month9_6"));
		dataeforms.setMonth9_7(rs.getString("month9_7"));
		dataeforms.setMonth9_8(rs.getString("month9_8"));
		dataeforms.setMonth9_9(rs.getString("month9_9"));
		dataeforms.setMonth9_10(rs.getString("month9_10"));
		dataeforms.setMonth9_11(rs.getString("month9_11"));
		dataeforms.setMonth9_12(rs.getString("month9_12"));
		dataeforms.setMonth9_13(rs.getString("month9_13"));
		dataeforms.setMonth9_14(rs.getString("month9_14"));
		dataeforms.setMonth9_15(rs.getString("month9_15"));
	
		dataeforms.setMonth10_1(rs.getString("month10_1"));
		dataeforms.setMonth10_2(rs.getString("month10_2"));
		dataeforms.setMonth10_3(rs.getString("month10_3"));
		dataeforms.setMonth10_4(rs.getString("month10_4"));
		dataeforms.setMonth10_5(rs.getString("month10_5"));
		dataeforms.setMonth10_6(rs.getString("month10_6"));
		dataeforms.setMonth10_7(rs.getString("month10_7"));
		dataeforms.setMonth10_8(rs.getString("month10_8"));
		dataeforms.setMonth10_9(rs.getString("month10_9"));
		dataeforms.setMonth10_10(rs.getString("month10_10"));
		dataeforms.setMonth10_11(rs.getString("month10_11"));
		dataeforms.setMonth10_12(rs.getString("month10_12"));
		dataeforms.setMonth10_13(rs.getString("month10_13"));
		dataeforms.setMonth10_14(rs.getString("month10_14"));
		dataeforms.setMonth10_15(rs.getString("month10_15"));
		
		dataeforms.setMonth11_1(rs.getString("month11_1"));
		dataeforms.setMonth11_2(rs.getString("month11_2"));
		dataeforms.setMonth11_3(rs.getString("month11_3"));
		dataeforms.setMonth11_4(rs.getString("month11_4"));
		dataeforms.setMonth11_5(rs.getString("month11_5"));
		dataeforms.setMonth11_6(rs.getString("month11_6"));
		dataeforms.setMonth11_7(rs.getString("month11_7"));
		dataeforms.setMonth11_8(rs.getString("month11_8"));
		dataeforms.setMonth11_9(rs.getString("month11_9"));
		dataeforms.setMonth11_10(rs.getString("month11_10"));
		dataeforms.setMonth11_11(rs.getString("month11_11"));
		dataeforms.setMonth11_12(rs.getString("month11_12"));
		dataeforms.setMonth11_13(rs.getString("month11_13"));
		dataeforms.setMonth11_14(rs.getString("month11_14"));
		dataeforms.setMonth11_15(rs.getString("month11_15"));
		
		
		dataeforms.setMonth12_1(rs.getString("month12_1"));
		dataeforms.setMonth12_2(rs.getString("month12_2"));
		dataeforms.setMonth12_3(rs.getString("month12_3"));
		dataeforms.setMonth12_4(rs.getString("month12_4"));
		dataeforms.setMonth12_5(rs.getString("month12_5"));
		dataeforms.setMonth12_6(rs.getString("month12_6"));
		dataeforms.setMonth12_7(rs.getString("month12_7"));
		dataeforms.setMonth12_8(rs.getString("month12_8"));
		dataeforms.setMonth12_9(rs.getString("month12_9"));
		dataeforms.setMonth12_10(rs.getString("month12_10"));
		dataeforms.setMonth12_11(rs.getString("month12_11"));
		dataeforms.setMonth12_12(rs.getString("month12_12"));
		dataeforms.setMonth12_13(rs.getString("month12_13"));
		dataeforms.setMonth12_14(rs.getString("month12_14"));
		dataeforms.setMonth12_15(rs.getString("month12_15"));
		return dataeforms;
	}

	
	
}



