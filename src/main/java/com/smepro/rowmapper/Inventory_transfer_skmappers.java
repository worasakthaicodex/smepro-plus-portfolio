package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Adjustment_fg;
import com.smepro.models.Fg;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class Inventory_transfer_skmappers implements RowMapper<Inventory_transfer_sk> {

	@Override
	public  Inventory_transfer_sk mapRow(ResultSet rs, int rowNum) throws SQLException {
		Inventory_transfer_sk  action = new  	Inventory_transfer_sk();
	
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "edata_id")) {
			 action.setEdata_id(rs.getInt("edata_id"));
	        }
	
		 if (hasColumn(rs, "doc_code")) {
			 action.setDoc_code(rs.getString("doc_code"));
	        }
		 if (hasColumn(rs, "tempId")) {
			 action.setTempId(rs.getString("tempId"));
	        }
		 if (hasColumn(rs, "title")) {
			 action.setTitle(rs.getString("title"));
	        }
		 if (hasColumn(rs, "status")) {
			 action.setStatus(rs.getString("status"));
	        }  

		 if (hasColumn(rs, "count")) {
			 action.setCount(rs.getInt("count"));
	        }  

		 if (hasColumn(rs, "skt_date")) {
			 action.setSkt_date(rs.getString("skt_date"));
	        }  

		 if (hasColumn(rs, "skt_from_location")) {
			 action.setSkt_from_location(rs.getString("skt_from_location"));
	        }  

		 if (hasColumn(rs, "skt_to_location")) {
			 action.setSkt_to_location(rs.getString("skt_to_location"));
	        }  

		 if (hasColumn(rs, "skt_transferred_by")) {
			 action.setSkt_transferred_by(rs.getString("skt_transferred_by"));
	        }  

		 if (hasColumn(rs, "skt_remark")) {
			 action.setSkt_remark(rs.getString("skt_remark"));
	        }  

		 if (hasColumn(rs, "skt_pd_code")) {
			 action.setSkt_pd_code(rs.getString("skt_pd_code"));
	        }  


		 if (hasColumn(rs, "skt_lot_batch")) {
			 action.setSkt_lot_batch(rs.getString("skt_lot_batch"));
	        }  

		 if (hasColumn(rs, "skt_stock_code")) {
			 action.setSkt_stock_code(rs.getString("skt_stock_code"));
	        }  

		 if (hasColumn(rs, "skt_stock_name")) {
			 action.setSkt_stock_name(rs.getString("skt_stock_name"));
	        }  

		 if (hasColumn(rs, "skt_quantity")) {
			 action.setSkt_quantity(rs.getDouble("skt_quantity"));
	        }  

		 if (hasColumn(rs, "skt_unit")) {
			 action.setSkt_unit(rs.getString("skt_unit"));
	        }  

		 if (hasColumn(rs, "skt_status")) {
			 action.setSkt_status(rs.getString("skt_status"));
	        }  
		 
		 if (hasColumn(rs, "skt_approved_by")) {
			 action.setSkt_approved_by(rs.getString("skt_approved_by"));
	        }  
		 
		 
		 
		return action;


		
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
