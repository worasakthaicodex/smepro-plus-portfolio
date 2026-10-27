package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;


public class actionplanmapper implements RowMapper<actionplan> {

	@Override
	public actionplan mapRow(ResultSet rs, int rowNum) throws SQLException {
		actionplan actionplans = new actionplan();
		actionplans.setActionplan_id(rs.getInt("actionplan_id"));
		actionplans.setActionplan_idrfer(rs.getInt("actionplan_idrfer"));
		actionplans.setActionplan_status(rs.getInt("actionplan_status"));
		actionplans.setActionplan_datedue(rs.getString("actionplan_datedue"));
		actionplans.setActionplan_dateadd(rs.getString("actionplan_dateadd"));
		actionplans.setActionplan_responsible(rs.getString("actionplan_responsible"));
		actionplans.setActionplan_inspector(rs.getString("actionplan_inspector"));
		actionplans.setActionplan_problem(rs.getString("actionplan_problem"));
		actionplans.setActionplan_cause(rs.getString("actionplan_cause"));
		actionplans.setActionplan_solutions(rs.getString("actionplan_solutions"));
		actionplans.setActionplan_result(rs.getString("actionplan_result"));
		actionplans.setActionplan_comment(rs.getString("actionplan_comment"));

		
		actionplans.setFile_id(rs.getInt("file_id"));
		actionplans.setFile_name(rs.getString("file_name"));
		actionplans.setFile_name_original(rs.getString("file_name_original"));
		actionplans.setFile_type(rs.getString("file_type"));
		actionplans.setFile_path(rs.getString("file_path"));
		actionplans.setFile_type2(rs.getInt("file_type2"));
		actionplans.setFile_size(rs.getInt("file_size"));
		actionplans.setFile_id_refer(rs.getInt("file_id_refer"));
		actionplans.setFile_folder(rs.getInt("file_folder"));
		actionplans.setFile_size2(rs.getString("file_size2"));
		actionplans.setFile_no(rs.getString("file_no"));
		actionplans.setFile_rev(rs.getString("file_rev"));
		actionplans.setFile_dateadd(rs.getString("file_dateadd"));
		actionplans.setFile_dateend(rs.getString("file_dateend"));
		actionplans.setFile_code(rs.getString("file_code"));
		actionplans.setFile_shotcut(rs.getString("file_shotcut"));
		actionplans.setFile_path_full(rs.getString("file_path_full"));
		actionplans.setFile_idpn(rs.getString("file_idpn"));
		actionplans.setFile_by(rs.getString("file_by"));
		actionplans.setFile_jd(rs.getString("file_jd"));
		actionplans.setFile_parent(rs.getInt("file_parent"));
		actionplans.setEform(rs.getString("eform"));
		
		actionplans.setActionplan_expenses(rs.getString("actionplan_expenses"));
		actionplans.setActionplan_payment(rs.getString("actionplan_payment"));
		
		
		return actionplans;
	}

}
