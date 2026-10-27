package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dataset_data;

public class dataset_datamapper implements RowMapper<dataset_data> {

	@Override
	public dataset_data mapRow(ResultSet rs, int rowNum) throws SQLException {
		dataset_data dataset_datas = new dataset_data();
		dataset_datas.setDataset_data_id(rs.getInt("dataset_data_id"));
		dataset_datas.setDataset_data_id_refer(rs.getInt("dataset_data_id_refer"));
		dataset_datas.setDataset_data_title(rs.getString("dataset_data_title"));
		dataset_datas.setDataset_data_subtitle1(rs.getString("dataset_data_subtitle1"));
		dataset_datas.setDataset_data_subtitle2(rs.getString("dataset_data_subtitle2"));
		dataset_datas.setDataset_data_subtitle3(rs.getString("dataset_data_subtitle3"));
		dataset_datas.setDataset_data_subtitle4(rs.getString("dataset_data_subtitle4"));
		dataset_datas.setMain(rs.getInt("main"));
		return dataset_datas;
	}

	
	
}


