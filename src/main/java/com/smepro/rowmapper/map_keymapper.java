package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.map_key;


public class map_keymapper implements RowMapper<map_key> {

	@Override
	public map_key mapRow(ResultSet rs, int rowNum) throws SQLException {
		map_key map_keys = new map_key();
		
		
		map_keys.setMap_key_id(rs.getInt("map_key_id"));
		map_keys.setMap_key_id_main(rs.getInt("map_key_id_main"));
		map_keys.setMap_key_id_sub(rs.getInt("map_key_id_sub"));
		
		map_keys.setMap_key_main1(rs.getString("map_key_main1"));
		map_keys.setMap_key_main2(rs.getString("map_key_main2"));
		map_keys.setMap_key_main3(rs.getString("map_key_main3"));
		map_keys.setMap_key_main4(rs.getString("map_key_main4"));
		map_keys.setMap_key_main5(rs.getString("map_key_main5"));
	
		
		map_keys.setMap_key_sub1(rs.getString("map_key_sub1"));
		map_keys.setMap_key_sub2(rs.getString("map_key_sub2"));
		map_keys.setMap_key_sub3(rs.getString("map_key_sub3"));
		map_keys.setMap_key_sub4(rs.getString("map_key_sub4"));
		map_keys.setMap_key_sub5(rs.getString("map_key_sub5"));
		
		
		
		map_keys.setMap_key_mainsend1(rs.getString("map_key_mainsend1"));
		map_keys.setMap_key_mainsend2(rs.getString("map_key_mainsend2"));
		map_keys.setMap_key_mainsend3(rs.getString("map_key_mainsend3"));
		map_keys.setMap_key_mainsend4(rs.getString("map_key_mainsend4"));
		map_keys.setMap_key_mainsend5(rs.getString("map_key_mainsend5"));
	
		
		map_keys.setMap_key_subsend1(rs.getString("map_key_subsend1"));
		map_keys.setMap_key_subsend2(rs.getString("map_key_subsend2"));
		map_keys.setMap_key_subsend3(rs.getString("map_key_subsend3"));
		map_keys.setMap_key_subsend4(rs.getString("map_key_subsend4"));
		map_keys.setMap_key_subsend5(rs.getString("map_key_subsend5"));
		
		
		
		return map_keys;
	}

}
