package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.dataset;

public class datasetmapper implements RowMapper<dataset> {

	@Override
	public dataset mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		dataset datasets = new dataset();
		datasets.setDataset_id(rs.getInt("dataset_id"));
		datasets.setDataset_ids(rs.getString("dataset_id"));
		datasets.setDataset_name(rs.getString("dataset_name"));
		datasets.setDataset_type(rs.getString("dataset_type"));
		datasets.setDataset_idadd(rs.getInt("dataset_idadd"));
		datasets.setDataset_dateadd(rs.getString("dataset_dateadd"));
		datasets.setDataset_1(rs.getString("dataset_1"));
		datasets.setDataset_2(rs.getString("dataset_2"));
		datasets.setDataset_3(rs.getString("dataset_3"));
		datasets.setDataset_4(rs.getString("dataset_4"));
		datasets.setDataset_5(rs.getString("dataset_5"));
		datasets.setDataset_status(rs.getInt("dataset_status"));
		return datasets;
	}

	
	
}
