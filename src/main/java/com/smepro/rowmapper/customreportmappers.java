package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.customreport;
import com.smepro.models.edata;
import com.smepro.models.file;


public class customreportmappers implements RowMapper<customreport> {

	@Override
	public  customreport mapRow(ResultSet rs, int rowNum) throws SQLException {
		customreport  edatas = new  customreport();

		 if (hasColumn(rs, "id")) {
			  edatas.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "report_name")) {
			  edatas.setReport_name(rs.getString("report_name"));
	        }
		 if (hasColumn(rs, "report_description")) {
			  edatas.setReport_description(rs.getString("report_description"));
	        }
		 if (hasColumn(rs, "report_table")) {
			  edatas.setReport_table(rs.getInt("report_table"));
	        }
		 if (hasColumn(rs, "report_app")) {
			  edatas.setReport_app(rs.getInt("report_app"));
	        }
		 if (hasColumn(rs, "type")) {
			  edatas.setType(rs.getInt("type"));
	        }
		 if (hasColumn(rs, "level")) {
			  edatas.setLevel(rs.getString("level"));
	        }
		 if (hasColumn(rs, "analysis")) {
			  edatas.setAnalysis(rs.getString("analysis"));
	        }
		 if (hasColumn(rs, "filter")) {
			  edatas.setFilter(rs.getString("filter"));
	        }
		 if (hasColumn(rs, "axis_x")) {
			  edatas.setAxis_x(rs.getString("axis_x"));
	        }
		 if (hasColumn(rs, "axis_y")) {
			  edatas.setAxis_y(rs.getString("axis_y"));
	        }
		 if (hasColumn(rs, "status")) {
			  edatas.setStatus(rs.getInt("status"));
	        }
		 
		 
		 if (hasColumn(rs, "filter_data")) {
			  edatas.setFilter_data(rs.getString("filter_data"));
	        }
		 if (hasColumn(rs, "date_group")) {
			  edatas.setDate_group(rs.getString("date_group"));
	        }
		 if (hasColumn(rs, "filter_date")) {
			  edatas.setFilter_date(rs.getString("filter_date"));
	        }
		 
		 
		 if (hasColumn(rs, "condition_choice1")) {
			  edatas.setCondition_choice1(rs.getString("condition_choice1"));
	        }
		 if (hasColumn(rs, "filter_column1")) {
			  edatas.setFilter_column1(rs.getString("filter_column1"));
	        }
		 if (hasColumn(rs, "operation1")) {
			  edatas.setOperation1(rs.getString("operation1"));
	        }
		 if (hasColumn(rs, "condition_value1")) {
			  edatas.setCondition_value1(rs.getString("condition_value1"));
	        }
		 
		 
		 if (hasColumn(rs, "condition_choice2")) {
			  edatas.setCondition_choice2(rs.getString("condition_choice2"));
	        }
		 if (hasColumn(rs, "filter_column2")) {
			  edatas.setFilter_column2(rs.getString("filter_column2"));
	        }
		 if (hasColumn(rs, "operation2")) {
			  edatas.setOperation2(rs.getString("operation2"));
	        }
		 if (hasColumn(rs, "condition_value2")) {
			  edatas.setCondition_value2(rs.getString("condition_value2"));
	        }
		 
		 if (hasColumn(rs, "condition_choice3")) {
			  edatas.setCondition_choice3(rs.getString("condition_choice3"));
	        }
		 if (hasColumn(rs, "filter_column3")) {
			  edatas.setFilter_column3(rs.getString("filter_column3"));
	        }
		 if (hasColumn(rs, "operation3")) {
			  edatas.setOperation3(rs.getString("operation3"));
	        }
		 if (hasColumn(rs, "condition_value3")) {
			  edatas.setCondition_value3(rs.getString("condition_value3"));
	        }

		 
		 if (hasColumn(rs, "binning")) {
			  edatas.setBinning(rs.getInt("binning"));
	        }
		 if (hasColumn(rs, "binning_range")) {
			  edatas.setBinning_range(rs.getString("binning_range"));
	        }
		 if (hasColumn(rs, "binning_title")) {
			  edatas.setBinning_title(rs.getString("binning_title"));
	        }
		 if (hasColumn(rs, "binning_group")) {
			  edatas.setBinning_group(rs.getString("binning_group"));
	        }
		 if (hasColumn(rs, "binning_date")) {
			  edatas.setBinning_date(rs.getString("binning_date"));
	        }
		 if (hasColumn(rs, "binning_operation")) {
			  edatas.setBinning_operation(rs.getString("binning_operation"));
	        }
		 if (hasColumn(rs, "binning_value")) {
			  edatas.setBinning_value(rs.getString("binning_value"));
	        }
		 if (hasColumn(rs, "theme")) {
			  edatas.setTheme(rs.getString("theme"));
	        }
		 
		 if (hasColumn(rs, "planId")) {
			  edatas.setPlanId(rs.getInt("planId"));
	        }
		 if (hasColumn(rs, "department")) {
			  edatas.setDepartment(rs.getString("department"));
	        }
		 if (hasColumn(rs, "machineryId")) {
			  edatas.setMachineryId(rs.getString("machineryId"));
	        }
		 
		 
		return edatas;
	
	}

	

	   private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
	        ResultSetMetaData rsMetaData = rs.getMetaData();
	        int columnCount = rsMetaData.getColumnCount();
	        for (int i = 1; i <= columnCount; i++) {
	            if (columnName.equalsIgnoreCase(rsMetaData.getColumnName(i))) {
	                return true;
	            }
	        }
	        return false;
	    }
}
