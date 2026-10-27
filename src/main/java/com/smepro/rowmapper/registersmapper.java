package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Myapp;
import com.smepro.models.registerkey;
import com.smepro.models.registers;



public class registersmapper implements RowMapper<registers> {

	@Override
	public registers mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		registers register = new registers();
		
		
		 if (hasColumn(rs, "id")) {
			 register.setId(rs.getInt("id"));
	        }
		
		 if (hasColumn(rs, "register_id")) {
			 register.setRegister_id(rs.getInt("register_id"));
	        }
		 if (hasColumn(rs, "register_name")) {
			 register.setRegister_name(rs.getString("register_name"));
	        } 
		 if (hasColumn(rs, "register_type")) {
			 register.setRegister_type(rs.getString("register_type"));
	        } 
		 if (hasColumn(rs, "register_groups")) {
			 register.setRegister_groups(rs.getString("register_groups"));
	        }
		 if (hasColumn(rs, "register_address")) {
			 register.setRegister_address(rs.getString("register_address"));
	        }
		 if (hasColumn(rs, "register_fulladdress")) {
			 register.setRegister_fulladdress(rs.getString("register_fulladdress"));
	        }
		 
		 
		 if (hasColumn(rs, "register_note")) {
			 register.setRegister_note(rs.getString("register_note"));
	        } 
		 if (hasColumn(rs, "register_code")) {
			 register.setRegister_code(rs.getString("register_code"));
	        }
		 if (hasColumn(rs, "register_daterun")) {
			 register.setRegister_daterun(rs.getString("register_daterun"));
	        }
		 if (hasColumn(rs, "register_poin")) {
			 register.setRegister_poin(rs.getInt("register_poin"));
	        }
		 
		 if (hasColumn(rs, "contact_person")) {
			 register.setContact_person(rs.getString("contact_person"));
	        }
		 if (hasColumn(rs, "phone_number")) {
			 register.setPhone_number(rs.getString("phone_number"));
	        }
		 if (hasColumn(rs, "mobile_number")) {
			 register.setMobile_number(rs.getString("mobile_number"));
	        }
		 if (hasColumn(rs, "website")) {
			 register.setWebsite(rs.getString("website"));
	        }
		 if (hasColumn(rs, "social")) {
			 register.setSocial(rs.getString("social"));
	        }
		 if (hasColumn(rs, "tax_id")) {
			 register.setTax_id(rs.getString("tax_id"));
	        }
		 if (hasColumn(rs, "branch_code")) {
			 register.setBranch_code(rs.getString("branch_code"));
	        }
		 if (hasColumn(rs, "business_type")) {
			 register.setBusiness_type(rs.getString("business_type"));
	        }
		 if (hasColumn(rs, "preferred_currency")) {
			 register.setPreferred_currency(rs.getString("preferred_currency"));
	        }
		 if (hasColumn(rs, "payment_terms")) {
			 register.setPayment_terms(rs.getString("payment_terms"));
	        }
		 if (hasColumn(rs, "credit_limit")) {
			 register.setCredit_limit(rs.getString("credit_limit"));
	        }
		 if (hasColumn(rs, "bank_account")) {
			 register.setBank_account(rs.getString("bank_account"));
	        }
		 if (hasColumn(rs, "revision")) {
			 register.setRevision(rs.getInt("revision"));
	        }
	
		 if (hasColumn(rs, "bank_account")) {
			 register.setBank_account(rs.getString("bank_account"));
	        }
		 
		 if (hasColumn(rs, "register_grade")) {
			 register.setRegister_grade(rs.getString("register_grade"));
	        }
		 
		 if (hasColumn(rs, "updated_at")) {
			 register.setUpdated_at(rs.getString("updated_at"));
	        }
		 
		 if (hasColumn(rs, "created_at")) {
			 register.setCreated_at(rs.getString("created_at"));
	        }
		 
			if (hasColumn(rs, "register_status")) {
				register.setRegister_status(rs.getString("register_status"));
		}   	
			
			if (hasColumn(rs, "register_fulladdress")) {
				 register.setRegister_fulladdress(rs.getString("register_fulladdress"));
			 }
			 if (hasColumn(rs, "register_other_conditions")) {
				 register.setRegister_other_conditions(rs.getString("register_other_conditions"));
			 }
			 if (hasColumn(rs, "categorization")) {
				 register.setCategorization(rs.getString("categorization"));
			 }
			 if (hasColumn(rs, "behavior")) {
				 register.setBehavior(rs.getString("behavior"));
			 }
			 if (hasColumn(rs, "person_email")) {
				 register.setPerson_email(rs.getString("person_email"));
			 }
			 if (hasColumn(rs, "register_email")) {
				 register.setRegister_email(rs.getString("register_email"));
			 }
			 if (hasColumn(rs, "party_type")) {
				 register.setParty_type(rs.getString("party_type"));
			 }
			 if (hasColumn(rs, "product_category")) {
				 register.setProduct_category(rs.getString("product_category"));
			 }
			 if (hasColumn(rs, "register_title")) {
				 register.setRegister_title(rs.getString("register_title"));
			 }
			 if (hasColumn(rs, "fax")) {
				 register.setFax(rs.getString("fax"));
			 }
			 if (hasColumn(rs, "person_position")) {
				 register.setPerson_position(rs.getString("person_position"));
			 }
			 if (hasColumn(rs, "person_number")) {
				 register.setPerson_number(rs.getString("person_number"));
			 }
			 if (hasColumn(rs, "contact_approvers")) {
				 register.setContact_approvers(rs.getString("contact_approvers"));
			 }
			 if (hasColumn(rs, "approvers_position")) {
				 register.setApprovers_position(rs.getString("approvers_position"));
			 }
			 if (hasColumn(rs, "approvers_number")) {
				 register.setApprovers_number(rs.getString("approvers_number"));
			 }
			 if (hasColumn(rs, "approvers_email")) {
				 register.setApprovers_email(rs.getString("approvers_email"));
			 }
			 if (hasColumn(rs, "region")) {
				 register.setRegion(rs.getString("region"));
			 }
			 if (hasColumn(rs, "lat")) {
				 register.setLat(rs.getString("lat"));
			 }
			 if (hasColumn(rs, "lon")) {
				 register.setLon(rs.getString("lon"));
			 }
			 if (hasColumn(rs, "dbd")) {
				 register.setDbd(rs.getString("dbd"));
			 }
			 if (hasColumn(rs, "date_insert_oldsys")) {
				 register.setDate_insert_oldsys(rs.getString("date_insert_oldsys"));
			 }
			 if (hasColumn(rs, "merged_duplicate_ids")) {
				 register.setMerged_duplicate_ids(rs.getString("merged_duplicate_ids"));
			 }
			 if (hasColumn(rs, "id_oldsys")) {
				 register.setId_oldsys(rs.getString("id_oldsys"));
			 }
			
			
			
			if (hasColumn(rs, "extension_column_1")) { register.setExtension_column_1(rs.getString("extension_column_1")); }
			if (hasColumn(rs, "extension_column_2")) { register.setExtension_column_2(rs.getString("extension_column_2")); }
			if (hasColumn(rs, "extension_column_3")) { register.setExtension_column_3(rs.getString("extension_column_3")); }
			if (hasColumn(rs, "extension_column_4")) { register.setExtension_column_4(rs.getString("extension_column_4")); }
			if (hasColumn(rs, "extension_column_5")) { register.setExtension_column_5(rs.getString("extension_column_5")); }
			if (hasColumn(rs, "extension_column_6")) { register.setExtension_column_6(rs.getString("extension_column_6")); }
			if (hasColumn(rs, "extension_column_7")) { register.setExtension_column_7(rs.getString("extension_column_7")); }
			if (hasColumn(rs, "extension_column_8")) { register.setExtension_column_8(rs.getString("extension_column_8")); }
			if (hasColumn(rs, "extension_column_9")) { register.setExtension_column_9(rs.getString("extension_column_9")); }
			if (hasColumn(rs, "extension_column_10")) { register.setExtension_column_10(rs.getString("extension_column_10")); }
			if (hasColumn(rs, "extension_column_11")) { register.setExtension_column_11(rs.getString("extension_column_11")); }
			if (hasColumn(rs, "extension_column_12")) { register.setExtension_column_12(rs.getString("extension_column_12")); }
			if (hasColumn(rs, "extension_column_13")) { register.setExtension_column_13(rs.getString("extension_column_13")); }
			if (hasColumn(rs, "extension_column_14")) { register.setExtension_column_14(rs.getString("extension_column_14")); }
			if (hasColumn(rs, "extension_column_15")) { register.setExtension_column_15(rs.getString("extension_column_15")); }
			if (hasColumn(rs, "extension_column_16")) { register.setExtension_column_16(rs.getString("extension_column_16")); }
			if (hasColumn(rs, "extension_column_17")) { register.setExtension_column_17(rs.getString("extension_column_17")); }
			if (hasColumn(rs, "extension_column_18")) { register.setExtension_column_18(rs.getString("extension_column_18")); }
			if (hasColumn(rs, "extension_column_19")) { register.setExtension_column_19(rs.getString("extension_column_19")); }
			if (hasColumn(rs, "extension_column_20")) { register.setExtension_column_20(rs.getString("extension_column_20")); }
		return register;
		
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
