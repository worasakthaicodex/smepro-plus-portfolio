package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dar;

public class darmapper implements RowMapper<dar> {

	@Override
	public dar mapRow(ResultSet rs, int rowNum) throws SQLException {
		dar dars = new dar();
		dars.setDar_id(rs.getInt("dar_id"));
		dars.setDar_request(rs.getString("dar_request"));
		dars.setDar_type(rs.getString("dar_type"));
		dars.setDar_docold_id(rs.getString("dar_docold_id"));
		dars.setDar_doc_id(rs.getString("dar_doc_id"));
		dars.setDar_doc_page(rs.getString("dar_doc_page"));
		dars.setDar_doc_date(rs.getString("dar_doc_date"));
		dars.setDar_doc_end(rs.getString("dar_doc_end"));
		dars.setDar_doc_rev(rs.getString("dar_doc_rev"));
		dars.setDar_docold_date(rs.getString("dar_docold_date"));
		dars.setDar_docold_end(rs.getString("dar_docold_end"));
		dars.setDar_docold_rev(rs.getString("dar_docold_rev"));
		dars.setDar_doc_remark(rs.getString("dar_doc_remark"));
		dars.setDar_doc_remarkfull(rs.getString("dar_doc_remarkfull"));
		dars.setDar_flow(rs.getInt("dar_flow"));
		dars.setDar_step(rs.getInt("dar_step"));
		dars.setDar_note1(rs.getString("dar_note1"));
		dars.setDar_note2(rs.getString("dar_note2"));
		dars.setDar_id_add(rs.getString("dar_id_add"));
		dars.setDar_id_check(rs.getString("dar_id_check"));
		dars.setDar_id_app(rs.getString("dar_id_app"));
		dars.setDar_doc_name(rs.getString("dar_doc_name"));
		dars.setDar_date_add(rs.getString("dar_date_add"));
		dars.setDar_jd(rs.getString("dar_jd"));
		dars.setDar_nameadd(rs.getString("dar_nameadd"));
		dars.setDar_namecheck(rs.getString("dar_namecheck"));
		dars.setDar_nameapp(rs.getString("dar_nameapp"));
		dars.setDar_doc_end2(rs.getInt("dar_doc_end2"));
		dars.setEform(rs.getString("eform"));
		dars.setOldeform(rs.getString("oldeform"));
		dars.setDar_department(rs.getInt("dar_department"));
		
		return dars;
	}

}
