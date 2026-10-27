package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.cm;
import com.smepro.models.form;

public class formmapper implements RowMapper<form> {

	@Override
	public form mapRow(ResultSet rs, int rowNum) throws SQLException {
		form forms = new form();
		forms.setForm_id(rs.getInt("form_id"));
		forms.setForm_name(rs.getString("form_name"));
		forms.setForm_condition(rs.getInt("form_condition"));
		forms.setForm_tag1(rs.getString("form_tag1"));
		forms.setForm_tag2(rs.getString("form_tag2"));
		forms.setForm_tag3(rs.getString("form_tag3"));
		forms.setForm_tag4(rs.getString("form_tag4"));
		forms.setForm_tag5(rs.getString("form_tag5"));
		forms.setForm_tag6(rs.getString("form_tag6"));
		forms.setForm_tag7(rs.getString("form_tag7"));
		forms.setForm_tag8(rs.getString("form_tag8"));
		forms.setForm_tag9(rs.getString("form_tag9"));
		forms.setForm_tag10(rs.getString("form_tag10"));
		forms.setForm_tag11(rs.getString("form_tag11"));
		forms.setForm_tag12(rs.getString("form_tag12"));
		forms.setForm_tag13(rs.getString("form_tag13"));
		forms.setForm_tag14(rs.getString("form_tag14"));
		forms.setForm_tag15(rs.getString("form_tag15"));
		forms.setForm_tag16(rs.getString("form_tag16"));
		forms.setForm_tag17(rs.getString("form_tag17"));
		forms.setForm_tag18(rs.getString("form_tag18"));
		forms.setForm_tag19(rs.getString("form_tag19"));
		forms.setForm_tag20(rs.getString("form_tag20"));
		forms.setForm_tag21(rs.getString("form_tag21"));
		forms.setForm_tag22(rs.getString("form_tag22"));
		forms.setForm_tag23(rs.getString("form_tag23"));
		forms.setForm_tag24(rs.getString("form_tag24"));
		forms.setForm_tag25(rs.getString("form_tag25"));
		forms.setForm_tag26(rs.getString("form_tag26"));
		forms.setForm_tag27(rs.getString("form_tag27"));
		forms.setForm_tag28(rs.getString("form_tag28"));
		forms.setForm_tag29(rs.getString("form_tag29"));
		forms.setForm_tag30(rs.getString("form_tag30"));
		forms.setForm_tag31(rs.getString("form_tag31"));
		forms.setForm_tag32(rs.getString("form_tag32"));
		forms.setForm_tag33(rs.getString("form_tag33"));
		forms.setForm_tag34(rs.getString("form_tag34"));
		forms.setForm_tag35(rs.getString("form_tag35"));
		forms.setForm_tag36(rs.getString("form_tag36"));
		forms.setForm_tag37(rs.getString("form_tag37"));
		forms.setForm_tag38(rs.getString("form_tag38"));
		forms.setForm_tag39(rs.getString("form_tag39"));
		forms.setForm_tag40(rs.getString("form_tag40"));
		return forms;
	}

}
