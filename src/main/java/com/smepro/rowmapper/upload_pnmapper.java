package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.upload_pn;

public class upload_pnmapper implements RowMapper<upload_pn> {

	@Override
	public upload_pn mapRow(ResultSet rs, int rowNum) throws SQLException {
		upload_pn upload_pns = new upload_pn();
		upload_pns.setUpload_pn_id(rs.getInt("upload_pn_id"));
		upload_pns.setUpload_pn_name(rs.getString("upload_pn_name"));
		upload_pns.setUpload_pn_name2(rs.getString("upload_pn_name2"));
		upload_pns.setUpload_pn_name3(rs.getString("upload_pn_name3"));
		upload_pns.setUpload_pn_name4(rs.getString("upload_pn_name4"));
		upload_pns.setUpload_pn_name5(rs.getString("upload_pn_name5"));
		upload_pns.setUpload_pn_note(rs.getString("upload_pn_note"));
		upload_pns.setUpload_pn_due(rs.getInt("upload_pn_due"));
		upload_pns.setUpload_pn_idadd(rs.getInt("upload_pn_idadd"));
		upload_pns.setUpload_pn_idrefer(rs.getInt("upload_pn_idrefer"));
		upload_pns.setUpload_pn_respon(rs.getString("upload_pn_respon"));
		upload_pns.setUpload_pn_app(rs.getString("upload_pn_app"));
		upload_pns.setUpload_pn_date(rs.getString("upload_pn_date"));
		upload_pns.setUpload_pn_end(rs.getString("upload_pn_end"));
		upload_pns.setUpload_pn_status(rs.getInt("upload_pn_status"));
		upload_pns.setUpload_pn_department(rs.getString("upload_pn_department"));
		upload_pns.setUpload_pn_type(rs.getInt("upload_pn_type"));
		upload_pns.setUpload_pn_datedue(rs.getString("upload_pn_datedue"));
		upload_pns.setUpload_pn_namefile(rs.getInt("upload_pn_namefile"));
		upload_pns.setUpload_pn_monitor(rs.getLong("upload_pn_monitor"));
		upload_pns.setUpload_pn_alert(rs.getInt("upload_pn_alert"));
		upload_pns.setUpload_pn_sum(rs.getString("upload_pn_sum"));
		upload_pns.setUpload_pn_avg(rs.getString("upload_pn_avg"));
		upload_pns.setUpload_pn_hidden(rs.getString("upload_pn_hidden"));
		upload_pns.setUpload_pn_private(rs.getInt("upload_pn_private"));
		upload_pns.setUpload_pn_pivot(rs.getInt("upload_pn_pivot"));
		upload_pns.setUpload_pn_follow(rs.getInt("upload_pn_follow"));
		upload_pns.setUpload_pn_report(rs.getInt("upload_pn_report"));
		upload_pns.setUpload_pn_setdue(rs.getString("upload_pn_setdue"));
		upload_pns.setUpload_pn_filecheck(rs.getInt("upload_pn_filecheck"));
		upload_pns.setUpload_pn_storage(rs.getString("upload_pn_storage"));
		upload_pns.setUpload_pn_send1(rs.getInt("upload_pn_send1"));
		upload_pns.setUpload_pn_allpermissions(rs.getInt("upload_pn_allpermissions"));
		upload_pns.setUpload_pn_allowallpermissions(rs.getInt("upload_pn_allowallpermissions"));
		upload_pns.setUpload_pn_appfield(rs.getString("upload_pn_appfield"));
		upload_pns.setUpload_pn_clearing(rs.getString("upload_pn_clearing"));
		upload_pns.setUpload_pn_group(rs.getString("upload_pn_group"));
		upload_pns.setProject(rs.getInt("project"));
		
		upload_pns.setApplication(rs.getInt("application"));
		
		upload_pns.setTurn(rs.getInt("turn"));
		upload_pns.setProject_info(rs.getString("project_info"));
		upload_pns.setUpload_pn_send(rs.getInt("upload_pn_send"));
		upload_pns.setUpload_pn_unsend(rs.getInt("upload_pn_unsend"));
		upload_pns.setUpload_pn_late(rs.getInt("upload_pn_late"));
		upload_pns.setUpload_pn_ontime(rs.getInt("upload_pn_ontime"));
		upload_pns.setUpload_pn_activityend(rs.getString("upload_pn_activityend"));
		upload_pns.setUpload_pn_activitystart(rs.getString("upload_pn_activitystart"));
		upload_pns.setId_policy(rs.getInt("id_policy"));
		
		
		upload_pns.setProject_manager(rs.getString("project_manager"));
		upload_pns.setProject_duration(rs.getString("project_duration"));
		upload_pns.setProject_objectives(rs.getString("project_objectives"));
		upload_pns.setProject_scope(rs.getString("project_scope"));
		upload_pns.setProject_references(rs.getString("project_references"));
		upload_pns.setProject_start(rs.getString("project_start"));
		upload_pns.setProject_end(rs.getString("project_end"));
		
		upload_pns.setId_policy(rs.getInt("id_policy"));
		return upload_pns;
	}

}
