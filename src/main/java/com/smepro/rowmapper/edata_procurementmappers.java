package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;


public class edata_procurementmappers implements RowMapper<edata_procurement> {

	@Override
	public  edata_procurement mapRow(ResultSet rs, int rowNum) throws SQLException {
		edata_procurement  action = new  edata_procurement();
		 

			  
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }

		 if (hasColumn(rs, "procurement_itemid")) {
			 action.setProcurement_itemid(rs.getString("procurement_itemid"));
	        } 
		 
		 if (hasColumn(rs, "edata_id")) {
			 action.setEdata_id(rs.getInt("edata_id"));
	        }
		 
		 if (hasColumn(rs, "tempId")) {
			 action.setTempId(rs.getString("tempId"));
	        }
		 
		 
		  if (hasColumn(rs, "procurement_documentnumber")) {
			  action.setProcurement_documentnumber(rs.getString("procurement_documentnumber"));
	        } 

		
	
			  if (hasColumn(rs, "procurement_itemname")) {
				  action.setProcurement_itemname(rs.getString("procurement_itemname"));
		        } 
			  if (hasColumn(rs, "procurement_quantityrequested")) {
				  action.setProcurement_quantityrequested(rs.getString("procurement_quantityrequested"));
		        }   	 
			  if (hasColumn(rs, "procurement_estimatedprice")) {
				  action.setProcurement_estimatedprice(rs.getString("procurement_estimatedprice"));
		        }   	
			  if (hasColumn(rs, "procurement_unit")) {
				  action.setProcurement_unit(rs.getString("procurement_unit"));
		        }   	
			  if (hasColumn(rs, "procurement_requesteddate")) {
				  action.setProcurement_requesteddate(rs.getString("procurement_requesteddate"));
		        }  
			  
			  if (hasColumn(rs, "procurement_documenttype")) {
				  action.setProcurement_documenttype(rs.getString("procurement_documenttype"));
		        }  
			  
			  if (hasColumn(rs, "procurement_linetotal")) {
				  action.setProcurement_linetotal(rs.getString("procurement_linetotal"));
		        }  	 
			  
			  if (hasColumn(rs, "procurement_grandtotal")) {
				  action.setProcurement_grandtotal(rs.getString("procurement_grandtotal"));
		        }  
			
			  
			  if (hasColumn(rs, "procurement_quantity_ordered")) {
				  action.setProcurement_quantity_ordered(rs.getString("procurement_quantity_ordered"));
		        }  
			
			  if (hasColumn(rs, "procurement_unit_price")) {
				  action.setProcurement_unit_price(rs.getString("procurement_unit_price"));
		        }  
			
			  if (hasColumn(rs, "procurement_payment_status")) {
				  action.setProcurement_payment_status(rs.getString("procurement_payment_status"));
		        }  
			
			  if (hasColumn(rs, "procurement_delivery_date")) {
				  action.setProcurement_delivery_date(rs.getString("procurement_delivery_date"));
		        }  
			
			  if (hasColumn(rs, "procurement_supplier_name")) {
				  action.setProcurement_supplier_name(rs.getString("procurement_supplier_name"));
		        }  
			
			  if (hasColumn(rs, "procurement_supplier_address")) {
				  action.setProcurement_supplier_address(rs.getString("procurement_supplier_address"));
		        }  
			
			  if (hasColumn(rs, "procurement_supplier_id")) {
				  action.setProcurement_supplier_id(rs.getString("procurement_supplier_id"));
		        }  
			
			  if (hasColumn(rs, "procurement_linetotal2")) {
				  action.setProcurement_linetotal2(rs.getString("procurement_linetotal2"));
		        }  
			
			  if (hasColumn(rs, "procurement_grandtotal2")) {
				  action.setProcurement_grandtotal2(rs.getString("procurement_grandtotal2"));
		        }  
			  if (hasColumn(rs, "procurement_type")) {
				  action.setProcurement_type(rs.getString("procurement_type"));
		        }  

			  if (hasColumn(rs, "received_date")) {
				  action.setReceived_date(rs.getString("received_date"));
		        } 
			  if (hasColumn(rs, "tracking_number")) {
				  action.setTracking_number(rs.getString("tracking_number"));
		        } 
			  if (hasColumn(rs, "inspection_status")) {
				  action.setInspection_status(rs.getString("inspection_status"));
		        } 
			  if (hasColumn(rs, "damage_report")) {
				  action.setDamage_report(rs.getString("damage_report"));
		        } 
			  if (hasColumn(rs, "storage_location")) {
				  action.setStorage_location(rs.getString("storage_location"));
		        } 
			  if (hasColumn(rs, "quantity_received")) {
				  action.setQuantity_received(rs.getString("quantity_received"));
		        } 
			  if (hasColumn(rs, "id_refer")) {
				  action.setId_refer(rs.getInt("id_refer"));
		        } 
			  if (hasColumn(rs, "id_refer_by")) {
				  action.setId_refer_by(rs.getInt("id_refer_by"));
		        } 
	  
			  if (hasColumn(rs, "doc")) {
				  action.setDoc(rs.getString("doc"));
		        } 
 
			  if (hasColumn(rs, "procurement_status")) {
				  action.setProcurement_status(rs.getString("procurement_status"));
		        } 
			  if (hasColumn(rs, "action_type")) {
				  action.setAction_type(rs.getString("action_type"));
		        } 
   
			  if (hasColumn(rs, "count")) {
				  action.setCount(rs.getInt("count"));
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
