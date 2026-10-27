package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dataset_data;
import com.smepro.models.levenshtein;

public class levenshteinmapper implements RowMapper<levenshtein> {

	@Override
	public levenshtein mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		levenshtein levenshteins = new levenshtein();

		levenshteins.setId(rs.getInt("id"));
		levenshteins.setEdata_id(rs.getInt("edata_id"));
		levenshteins.setDistance(rs.getInt("distance"));
		levenshteins.setShtein_name1(rs.getString("shtein_name1"));
		levenshteins.setShtein_name2(rs.getString("shtein_name2"));

		return levenshteins;
		
		
	}
	
	
	
}


