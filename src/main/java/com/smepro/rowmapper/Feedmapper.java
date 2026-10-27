package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Feed;
import com.smepro.models.Footer_calculation;
import com.smepro.models.Links;
import com.smepro.models.LinksSetting;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.actionplan;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;


public class Feedmapper implements RowMapper<Feed> {

	 @Override
	    public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Feed feed = new Feed();

	        feed.setId(rs.getInt("id"));
	        feed.setLinks_id(rs.getInt("links_id"));
	        feed.setOutgoing_column(rs.getString("outgoing_column"));
	        feed.setFor_column(rs.getString("for_column"));
	        feed.setFeed_type(rs.getString("feed_type"));

	        return feed;
	    }
}
