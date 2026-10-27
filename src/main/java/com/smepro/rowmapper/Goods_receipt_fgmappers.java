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
import com.smepro.models.Issue_fg;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class Goods_receipt_fgmappers implements RowMapper<Goods_receipt_fg> {

	@Override
	public  Goods_receipt_fg mapRow(ResultSet rs, int rowNum) throws SQLException {
		Goods_receipt_fg  action = new  Goods_receipt_fg();
		 
			  
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

		 

		 if (hasColumn(rs, "receipt_date")) {
			 action.setReceipt_date(rs.getString("receipt_date"));
	        }  

		 if (hasColumn(rs, "work_order_no")) {
			 action.setWork_order_no(rs.getString("work_order_no"));
	        }  

		 if (hasColumn(rs, "location")) {
			 action.setLocation(rs.getString("location"));
	        }  

		 if (hasColumn(rs, "remark")) {
			 action.setRemark(rs.getString("remark"));
	        }  

		 if (hasColumn(rs, "goods_receipt_pd_code")) {
			 action.setGoods_receipt_pd_code(rs.getString("goods_receipt_pd_code"));
	        }  

		 if (hasColumn(rs, "goods_receipt_lot_batch")) {
			 action.setGoods_receipt_lot_batch(rs.getString("goods_receipt_lot_batch"));
	        }  


		 if (hasColumn(rs, "goods_receipt_product_code")) {
			 action.setGoods_receipt_product_code(rs.getString("goods_receipt_product_code"));
	        }  

		 if (hasColumn(rs, "goods_receipt_product_name")) {
			 action.setGoods_receipt_product_name(rs.getString("goods_receipt_product_name"));
	        }  
		 
		 
		 if (hasColumn(rs, "goods_receipt_customer_code")) {
			 action.setGoods_receipt_customer_code(rs.getString("goods_receipt_customer_code"));
	        }  

		 if (hasColumn(rs, "goods_receipt_customer_name")) {
			 action.setGoods_receipt_customer_name(rs.getString("goods_receipt_customer_name"));
	        }  

		 if (hasColumn(rs, "goods_receipt_quantity")) {
			 action.setGoods_receipt_quantity(rs.getDouble("goods_receipt_quantity"));
	        }  

		 if (hasColumn(rs, "goods_receipt_unit")) {
			 action.setGoods_receipt_unit(rs.getString("goods_receipt_unit"));
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
