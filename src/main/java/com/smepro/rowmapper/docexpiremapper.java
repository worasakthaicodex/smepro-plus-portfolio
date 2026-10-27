package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.docexpire;


public class docexpiremapper implements RowMapper<docexpire> {

	@Override
	public docexpire mapRow(ResultSet rs, int rowNum) throws SQLException {
		docexpire docexpires = new docexpire();
		docexpires.setTotal_QM(rs.getInt("total_QM"));	
		docexpires.setTotal_QP(rs.getInt("total_QP"));	
		docexpires.setTotal_WI(rs.getInt("total_WI"));	
		docexpires.setTotal_SD(rs.getInt("total_SD"));	
		docexpires.setTotal_FO(rs.getInt("total_FO"));	
		return docexpires;
	}

}
