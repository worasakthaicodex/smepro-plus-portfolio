package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;


public class traceabilityedata_skmapper implements RowMapper<edata> {

	@Override
	public  edata mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		edata  edatas = new  edata();

		 if (hasColumn(rs, "data_id")) {
			 edatas.setData_id(rs.getInt("data_id"));
	        }
	
		 if (hasColumn(rs, "title")) {
			 edatas.setTitle(rs.getString("title"));
	        }
		 
		 if (hasColumn(rs, "data_nameadd")) {
			 edatas.setData_nameadd(rs.getString("data_nameadd"));
	        }
		 
		 

		 if (hasColumn(rs, "ski_pd_code")) {
			 if(rs.getString("ski_pd_code") != null) {	 edatas.setProduction_directive(rs.getString("ski_pd_code"));}
	        }
		 if (hasColumn(rs, "ski_lot_batch")) {
			 if(rs.getString("ski_lot_batch") != null) {	 edatas.setBatch(rs.getString("ski_lot_batch"));}
	        }
		 if (hasColumn(rs, "ski_supplier_name")) {
			 if(rs.getString("ski_supplier_name") != null) {	 edatas.setSupplier(rs.getString("ski_supplier_name"));}
	        }
		 if (hasColumn(rs, "ski_stock_name")) {
			 if(rs.getString("ski_stock_name") != null) {	 edatas.setMaterial(rs.getString("ski_stock_name"));}
	        }
		 if (hasColumn(rs, "ski_tracking_number")) {
			 if(rs.getString("ski_tracking_number") != null) {	 edatas.setBatch_material(rs.getString("ski_tracking_number"));}
	        }

		 if (hasColumn(rs, "ski_type")) {
			 if(rs.getString("ski_type") != null) {	 edatas.setIssue_type(rs.getString("ski_type"));}
	        }
		 if (hasColumn(rs, "ski_quantity")) {
			 if(rs.getString("ski_quantity") != null) {	 edatas.setQuantity(rs.getString("ski_quantity"));}
	        }
		 if (hasColumn(rs, "ski_by")) {
			 if(rs.getString("ski_by") != null) {	 edatas.setData_nameadd(rs.getString("ski_by"));}
			 
	        }
		 if (hasColumn(rs, "ski_unit")) {
			 if(rs.getString("ski_unit") != null) {	 edatas.setUnit(rs.getString("ski_unit"));}
	        }
		 if (hasColumn(rs, "ski_date")) {
			 if(rs.getString("ski_date") != null) {	 edatas.setStart(rs.getString("ski_date"));}
	        } 
		


		 if (hasColumn(rs, "ska_type")) {
			 if(rs.getString("ska_type") != null) {	 edatas.setIssue_type(rs.getString("ska_type"));}
	        }
		 if (hasColumn(rs, "ska_pd")) {
			 if(rs.getString("ska_pd") != null) {	 edatas.setProduction_directive(rs.getString("ska_pd"));}
	        }
		 if (hasColumn(rs, "ska_lot_batch")) {
			 if(rs.getString("ska_lot_batch") != null) {	 edatas.setBatch(rs.getString("ska_lot_batch"));}
	        }
		 if (hasColumn(rs, "ska_lot_batch2")) {
			 if(rs.getString("ska_lot_batch2") != null) {	 edatas.setBatch2(rs.getString("ska_lot_batch2"));}
	        }
		 if (hasColumn(rs, "ska_quantity")) {
			 if(rs.getString("ska_quantity") != null) {	 edatas.setQuantity(rs.getString("ska_quantity"));}
	        }
	
		 if (hasColumn(rs, "ska_unit")) {
			 if(rs.getString("ska_unit") != null) {	 edatas.setUnit(rs.getString("ska_unit"));}
	        }
		 if (hasColumn(rs, "ska_date")) {
			 if(rs.getString("ska_date") != null) {	 edatas.setStart(rs.getString("ska_date"));}
	        } 
		 if (hasColumn(rs, "ski_supplier_name")) {
			 if(rs.getString("ski_supplier_name") != null) {	 edatas.setSupplier(rs.getString("ski_supplier_name"));}
	        }
		 if (hasColumn(rs, "ski_stock_name")) {
			 if(rs.getString("ski_stock_name") != null) {	 edatas.setMaterial(rs.getString("ski_stock_name"));}
	        }
		 if (hasColumn(rs, "ska_tracking_number")) {
			 if(rs.getString("ska_tracking_number") != null) {	 edatas.setBatch_material(rs.getString("ska_tracking_number"));}
	        }

		 

		 if (hasColumn(rs, "skg_pd_code")) {
			 if(rs.getString("skg_pd_code") != null) {	 edatas.setProduction_directive(rs.getString("skg_pd_code"));}
	        }
		 if (hasColumn(rs, "skg_lot_batch")) {

			 if(rs.getString("skg_lot_batch") != null) {	 edatas.setBatch(rs.getString("skg_lot_batch"));}
	        }
	
		 if (hasColumn(rs, "skg_quantity_received")) {

			 if(rs.getString("skg_quantity_received") != null) {	 edatas.setQuantity(rs.getString("skg_quantity_received"));}
	        }

		 if (hasColumn(rs, "skg_unit")) {

			 if(rs.getString("skg_unit") != null) {	 edatas.setUnit(rs.getString("skg_unit"));}
	        }
		 if (hasColumn(rs, "skg_received_date")) {

			 if(rs.getString("skg_received_date") != null) {	 edatas.setStart(rs.getString("skg_received_date"));}
	        } 
		 if (hasColumn(rs, "skg_inspection_status")) {
			 if(rs.getString("skg_inspection_status") != null) {	 edatas.setInspection_result(rs.getString("skg_inspection_status"));}
	        } 
		 if (hasColumn(rs, "skg_supplier_name")) {
			 if(rs.getString("skg_supplier_name") != null) {	 edatas.setSupplier(rs.getString("skg_supplier_name"));}
	        }
		 if (hasColumn(rs, "skg_stock_name")) {
			 if(rs.getString("skg_stock_name") != null) {	 edatas.setMaterial(rs.getString("skg_stock_name"));}
	        }
		 if (hasColumn(rs, "skg_tracking_number")) {
			 if(rs.getString("skg_tracking_number") != null) {	 edatas.setBatch_material(rs.getString("skg_tracking_number"));}
	        }

	
		 
		 
		 

		 if (hasColumn(rs, "skt_pd_code")) {
			 if(rs.getString("skt_pd_code") != null) {	 edatas.setProduction_directive(rs.getString("skt_pd_code"));}
	        }
		 if (hasColumn(rs, "skt_lot_batch")) {
			 if(rs.getString("skt_lot_batch") != null) {	 edatas.setBatch(rs.getString("skt_lot_batch"));}
	        }
		 if (hasColumn(rs, "skt_quantity")) {

			 if(rs.getString("skt_quantity") != null) {	 edatas.setQuantity(rs.getString("skt_quantity"));}
	        }
		 if (hasColumn(rs, "skt_transferred_by")) {
			 if(rs.getString("skt_transferred_by") != null) {	
				 edatas.setData_nameadd(rs.getString("skt_transferred_by"));}
	        }
		 if (hasColumn(rs, "skt_unit")) {

			 if(rs.getString("skt_unit") != null) {	 edatas.setUnit(rs.getString("skt_unit"));}
	        }
		 if (hasColumn(rs, "skt_date")) {

			 if(rs.getString("skt_date") != null) {	 edatas.setStart(rs.getString("skt_date"));}
	        } 
		 
		 if (hasColumn(rs, "skg_supplier_name")) {
			 if(rs.getString("skg_supplier_name") != null) {	 edatas.setSupplier(rs.getString("skg_supplier_name"));}
	        }
		 if (hasColumn(rs, "skt_stock_name")) {
			 if(rs.getString("skt_stock_name") != null) {	 edatas.setMaterial(rs.getString("skt_stock_name"));}
	        }
	
		 
		 if (hasColumn(rs, "skt_from_location")) {
			 if(rs.getString("skt_from_location") != null) {	 edatas.setFrom_location(rs.getString("skt_from_location"));}
	        }
		 if (hasColumn(rs, "skt_to_location")) {
			 if(rs.getString("skt_to_location") != null) {	 edatas.setTo_location(rs.getString("skt_to_location"));}
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
