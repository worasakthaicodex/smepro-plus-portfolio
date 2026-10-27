package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.drives;


public class drivesmapper2 implements RowMapper<drives> {

	@Override
	public drives mapRow(ResultSet rs, int rowNum) throws SQLException {
		drives drivess = new drives();
		drivess.setDrives_id(rs.getInt("drives_id"));
		drivess.setDrives_name(rs.getString("drives_name"));
		drivess.setDrives_size(rs.getLong("drives_size"));
		drivess.setDrives_drive(rs.getString("drives_drive"));
		drivess.setDrives_size2(rs.getString("drives_size2"));
		drivess.setDrives_use(rs.getLong("drives_use"));
		drivess.setDrives_use2(rs.getString("drives_use2"));
		drivess.setDrives_usedisplay(rs.getDouble("drives_usedisplay"));
		return drivess;
	}

}
