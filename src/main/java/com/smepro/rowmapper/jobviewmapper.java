package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.cm;
import com.smepro.models.jobview;

public class jobviewmapper implements RowMapper<jobview> {

		@Override
		public jobview mapRow(ResultSet rs, int rowNum) throws SQLException {
			jobview jobviews = new jobview();
			jobviews.setJobview_id(rs.getInt("jobview_id"));
			jobviews.setJobview_tab(rs.getInt("jobview_tab"));
			jobviews.setJobview_type(rs.getString("jobview_type"));
			jobviews.setJobview_category(rs.getString("jobview_category"));
			jobviews.setJobview_group(rs.getString("jobview_group"));
			jobviews.setJobview_user_name(rs.getString("jobview_user_name"));
			return jobviews;
		}
	
	
}
