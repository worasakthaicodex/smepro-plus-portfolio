package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.LinksSetting;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.actionplan;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;


public class LinksSettingmapper implements RowMapper<LinksSetting> {

	@Override
	public LinksSetting mapRow(ResultSet rs, int rowNum) throws SQLException {
	    LinksSetting data = new LinksSetting();
	    
	    data.setId(rs.getInt("id"));
	    data.setId_refer(rs.getInt("id_refer"));
	    data.setAllowed_link_relation(rs.getString("allowed_link_relation"));
	    data.setAllowed_use_data(rs.getString("allowed_use_data"));
	    
	    return data;
	}


}
