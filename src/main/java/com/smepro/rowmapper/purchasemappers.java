package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.purchase;


public class purchasemappers implements RowMapper<purchase> {

	@Override
	public  purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
		purchase  purchases = new  purchase();
		 
		 
		 if (hasColumn(rs, "id")) {
			 purchases.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "description")) {
			 purchases.setDescription(rs.getString("description"));
	        } 
		 if (hasColumn(rs, "unit")) {
			 purchases.setUnit(rs.getString("unit"));
	        } 	 
		 if (hasColumn(rs, "qty")) {
			 purchases.setQty(rs.getDouble("qty"));
	        } 	 
		 if (hasColumn(rs, "estimated")) {
			 purchases.setEstimated(rs.getDouble("estimated"));
	        } 	 

		 if (hasColumn(rs, "pr_id")) {
			 purchases.setPr_id(rs.getInt("pr_id"));
	        }
		 if (hasColumn(rs, "pr_date")) {
			 purchases.setPr_date(rs.getString("pr_date"));
	        } 
		 if (hasColumn(rs, "pr_idrefer")) {
			 purchases.setPr_idrefer(rs.getInt("pr_idrefer"));
	        }
		 if (hasColumn(rs, "po_id")) {
			 purchases.setPo_id(rs.getInt("po_id"));
	        }
		 if (hasColumn(rs, "item_id")) {
			 purchases.setItem_id(rs.getInt("item_id"));
	        }
	
		 if (hasColumn(rs, "po_date")) {
			 purchases.setPo_date(rs.getString("po_date"));
	        }
		 if (hasColumn(rs, "po_idrefer")) {
			 purchases.setPr_idrefer(rs.getInt("po_idrefer"));
	        }
		 if (hasColumn(rs, "clear_id")) {
			 purchases.setClear_id(rs.getInt("clear_id"));
	        }
		 if (hasColumn(rs, "cost")) {
			 purchases.setCost(rs.getDouble("cost"));
	        }
		 
		 if (hasColumn(rs, "distributor_id")) {
			 purchases.setDistributor_id(rs.getInt("distributor_id"));
	        }
		 
		 if (hasColumn(rs, "shipping_id")) {
			 purchases.setShipping_id(rs.getInt("shipping_id"));
	        }
		 
		 if (hasColumn(rs, "distributor_name")) {
			 purchases.setDistributor_name(rs.getString("distributor_name"));
	        }
		 
		 if (hasColumn(rs, "distributor_address")) {
			 purchases.setDistributor_address(rs.getString("distributor_address"));
	        }
		 
		 if (hasColumn(rs, "shipping_name")) {
			 purchases.setShipping_name(rs.getString("shipping_name"));
	        }
		 
		 if (hasColumn(rs, "shipping_address")) {
			 purchases.setShipping_address(rs.getString("shipping_address"));
	        }
		 
		 if (hasColumn(rs, "po_recipient")) {
			 purchases.setPo_recipient(rs.getInt("po_recipient"));
	        }
		 if (hasColumn(rs, "received")) {
			 purchases.setReceived(rs.getDouble("received"));
	        }
		 if (hasColumn(rs, "gr_date")) {
			 purchases.setGr_date(rs.getString("gr_date"));
	        }
		 if (hasColumn(rs, "gr_id")) {
			 purchases.setGr_id(rs.getInt("gr_id"));
	        } 
		 if (hasColumn(rs, "requester")) {
			 purchases.setRequester(rs.getString("requester"));
	        }
		 if (hasColumn(rs, "amount_received")) {
			 purchases.setAmount_received(rs.getDouble("amount_received"));
	        }
		 if (hasColumn(rs, "amount_paid")) {
			 purchases.setAmount_paid(rs.getDouble("amount_paid"));
	        }
		 
		 
		 if (hasColumn(rs, "drawer_id")) {
			 purchases.setDrawer_id(rs.getInt("drawer_id"));
	        }
		 if (hasColumn(rs, "drawer_name")) {
			 purchases.setDrawer_name(rs.getString("drawer_name"));
	        }
		 if (hasColumn(rs, "drawer_info")) {
			 purchases.setDrawer_info(rs.getString("drawer_info"));
	        }
	
		 if (hasColumn(rs, "r_date")) {
			 purchases.setR_date(rs.getString("r_date"));
	        }
		 if (hasColumn(rs, "r_id")) {
			 purchases.setR_id(rs.getInt("r_id"));
	        } 
		 
		 
		 
		return purchases;
	
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
