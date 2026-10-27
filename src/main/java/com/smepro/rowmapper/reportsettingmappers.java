package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Goal;
import com.smepro.models.actionplan;
import com.smepro.models.customreport;
import com.smepro.models.edata;
import com.smepro.models.file;
import com.smepro.models.reportsetting;


public class reportsettingmappers implements RowMapper<reportsetting> {

	@Override
	public  reportsetting mapRow(ResultSet rs, int rowNum) throws SQLException {
		reportsetting  data = new  reportsetting();

		 if (hasColumn(rs, "reportsetting_id")) {
			 data.setReportsetting_id(rs.getInt("reportsetting_id"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_id_refer")) {
			 data.setReportsetting_id_refer(rs.getInt("reportsetting_id_refer"));
	        }

			 if (hasColumn(rs, "reportsetting_pd")) {
				 data.setReportsetting_pd(rs.getString("reportsetting_pd"));
		        }
			 if (hasColumn(rs, "reportsetting_batch")) {
				 data.setReportsetting_batch(rs.getString("reportsetting_batch"));
		        }
			 if (hasColumn(rs, "reportsetting_department")) {
				 data.setReportsetting_department(rs.getString("reportsetting_department"));
		        }
		 if (hasColumn(rs, "reportsetting_list")) {
			 data.setReportsetting_list(rs.getString("reportsetting_list"));
	        }
		
		 if (hasColumn(rs, "reportsetting_employee")) {
			 data.setReportsetting_employee(rs.getString("reportsetting_employee"));
	        }
		 if (hasColumn(rs, "reportsetting_machine")) {
			 data.setReportsetting_machine(rs.getString("reportsetting_machine"));
	        }
		 if (hasColumn(rs, "reportsetting_date_wip")) {
			 data.setReportsetting_date_wip(rs.getString("reportsetting_date_wip"));
	        }
		 if (hasColumn(rs, "reportsetting_orders")) {
			 data.setReportsetting_orders(rs.getString("reportsetting_orders"));
	        }
		 if (hasColumn(rs, "reportsetting_received_today")) {
			 data.setReportsetting_received_today(rs.getString("reportsetting_received_today"));
	        }
		 if (hasColumn(rs, "reportsetting_paid_today")) {
			 data.setReportsetting_paid_today(rs.getString("reportsetting_paid_today"));
	        }
		 if (hasColumn(rs, "reportsetting_form_name")) {
			 data.setReportsetting_form_name(rs.getString("reportsetting_form_name"));
	        }
		 if (hasColumn(rs, "reportsetting_type")) {
			 data.setReportsetting_type(rs.getString("reportsetting_type"));
	        }
		 if (hasColumn(rs, "reportsetting_tables")) {
			 data.setReportsetting_tables(rs.getString("reportsetting_tables"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_data")) {
			 data.setReportsetting_data(rs.getString("reportsetting_data"));
	        }
		 if (hasColumn(rs, "reportsetting_line")) {
			 data.setReportsetting_line(rs.getString("reportsetting_line"));
	        }
		 
		 
		 if (hasColumn(rs, "reportsetting_stock_code")) {
			 data.setReportsetting_stock_code(rs.getString("reportsetting_stock_code"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_stock_name")) {
			 data.setReportsetting_stock_name(rs.getString("reportsetting_stock_name"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_total_system")) {
			 data.setReportsetting_total_system(rs.getString("reportsetting_total_system"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_total_real")) {
			 data.setReportsetting_total_real(rs.getString("reportsetting_total_real"));
	        }
		 
		 
		 
		 
		 if (hasColumn(rs, "reportsetting_total_real")) {
			 data.setReportsetting_total_real(rs.getString("reportsetting_total_real"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_total_real")) {
			 data.setReportsetting_total_real(rs.getString("reportsetting_total_real"));
	        }
		 
	
		 
		 if (hasColumn(rs, "reportsetting_downtime")) {
			 data.setReportsetting_downtime(rs.getString("reportsetting_downtime"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_planned_time")) {
			 data.setReportsetting_planned_time(rs.getString("reportsetting_planned_time"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_availability")) {
			 data.setReportsetting_availability(rs.getString("reportsetting_availability"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_speed")) {
			 data.setReportsetting_speed(rs.getString("reportsetting_speed"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_total_count")) {
			 data.setReportsetting_total_count(rs.getString("reportsetting_total_count"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_operating_time")) {
			 data.setReportsetting_operating_time(rs.getString("reportsetting_operating_time"));
	        }
		 
		 if (hasColumn(rs, "reportsetting_performance")) {
			 data.setReportsetting_performance(rs.getString("reportsetting_performance"));
	        }
		 if (hasColumn(rs, "reportsetting_oee")) {
			 data.setReportsetting_oee(rs.getString("reportsetting_oee"));
	        }
		 
		 
		 if (hasColumn(rs, "reportsetting_drawer")) {
			 data.setReportsetting_drawer(rs.getString("reportsetting_drawer"));
	        }
		 if (hasColumn(rs, "reportsetting_payer")) {
			 data.setReportsetting_payer(rs.getString("reportsetting_payer"));
	        }
		 
		 

		 
		return data;
	
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
