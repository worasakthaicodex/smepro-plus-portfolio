package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.attendance;



public class attendancemapper implements RowMapper<attendance> {

	@Override
	public attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		attendance attendances = new attendance();
		attendances.setAttendance_id(rs.getInt("attendance_id"));
		attendances.setAttendance_date(rs.getString("attendance_date"));
		attendances.setAttendance_checkin(rs.getString("attendance_checkin"));
		attendances.setAttendance_checkout(rs.getString("attendance_checkout"));
		attendances.setAttendance_userid(rs.getInt("attendance_userid"));
		attendances.setAttendance_ip(rs.getString("attendance_ip"));
		attendances.setAttendance_lat(rs.getString("attendance_lat"));
		attendances.setAttendance_lon(rs.getString("attendance_lon"));
		attendances.setAttendance_file(rs.getString("attendance_file"));
		attendances.setAttendance_file2(rs.getString("attendance_file2"));
		return attendances;
	}

}
