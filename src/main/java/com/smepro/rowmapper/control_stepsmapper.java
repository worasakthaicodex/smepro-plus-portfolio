package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.control_steps;


public class control_stepsmapper implements RowMapper<control_steps> {

	@Override
	public control_steps mapRow(ResultSet rs, int rowNum) throws SQLException {
		control_steps steps = new control_steps();
		
		
		 if (hasColumn(rs, "id")) {
			 steps.setId(rs.getInt("id"));
	        }
		 
	
		 if (hasColumn(rs, "production_directive")) {
				steps.setProduction_directive(rs.getString("production_directive"));
	        }
		 if (hasColumn(rs, "lot_batch")) {
				steps.setBatch(rs.getString("lot_batch"));
	        }
		 if (hasColumn(rs, "product_name")) {
				steps.setProduct(rs.getString("product_name"));
	        }
		 if (hasColumn(rs, "product_category")) {
				steps.setProduct_category(rs.getString("product_category"));
	        }
	
		 if (hasColumn(rs, "product_code")) {
				steps.setProduct_code(rs.getString("product_code"));
	        }
		 if (hasColumn(rs, "customer_code")) {
				steps.setCustomer(rs.getString("customer_code")+" "+rs.getString("customer_name"));
	        }
		 if (hasColumn(rs, "quantity")) {
				steps.setOrder_quantity(rs.getString("quantity"));
	        }
		 if (hasColumn(rs, "total_order_quantity")) {
				steps.setTotal_order_quantity(rs.getString("total_order_quantity"));
	        }
		 
		 if (hasColumn(rs, "material")) {
				steps.setMaterial(rs.getString("material"));
	        }
		 if (hasColumn(rs, "skg_tracking_number")) {
				steps.setBatch_material(rs.getString("skg_tracking_number"));
	        }
		 
		 
		 if (hasColumn(rs, "manufacturer")) {
				steps.setManufacturer(rs.getString("manufacturer"));
	        }
		 
		 if (hasColumn(rs, "step_1")) {
				steps.setStep_1(rs.getString("step_1"));
	        }
		 if (hasColumn(rs, "step_2")) {
				steps.setStep_2(rs.getString("step_2"));
	        }
		 if (hasColumn(rs, "step_3")) {
				steps.setStep_3(rs.getString("step_3"));
	        }
		 
		return steps;
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
