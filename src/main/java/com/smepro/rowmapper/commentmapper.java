package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.comment;

public class commentmapper implements RowMapper<comment> {

	@Override
	public comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		comment comments = new comment();
		comments.setComment_id(rs.getInt("comment_id"));
		comments.setComment_comment(rs.getString("comment_comment"));
		comments.setComment_name(rs.getString("comment_name"));
		comments.setComment_date(rs.getString("comment_date"));
		comments.setComment_note(rs.getString("comment_note"));
		return comments;
		

	}

}
