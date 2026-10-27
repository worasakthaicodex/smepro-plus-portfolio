package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.entity.CustomizeTableHeaders;





public class CustomizeTableHeadersmapper implements RowMapper<CustomizeTableHeaders> {

	 @Override
	    public CustomizeTableHeaders mapRow(ResultSet rs, int rowNum) throws SQLException {
		 CustomizeTableHeaders data = new CustomizeTableHeaders();

		 data.setId(rs.getInt("id"));
		 data.setType(rs.getString("type"));
		 data.setColumnName(rs.getString("column_name"));
		 data.setCustomizeName(rs.getString("customize_name"));

	        return data;
	    }
}
