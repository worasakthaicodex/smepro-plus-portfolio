package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Footer_calculation;
import com.smepro.models.Links;
import com.smepro.models.LinksSetting;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.actionplan;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;


public class Linksmapper implements RowMapper<Links> {

	  @Override
	    public Links mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Links link = new Links();

	        link.setId(rs.getInt("id"));
	        link.setLink_name(rs.getString("link_name"));
	        link.setLink_to(rs.getString("link_to"));
	        link.setRelationship(rs.getString("relationship"));
	        link.setId_refer(rs.getInt("id_refer"));

	        return link;
	    }


}
