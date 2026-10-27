package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Idoc;
import com.smepro.models.cm;

public class idocmapper implements RowMapper<Idoc> {

	@Override
	public Idoc mapRow(ResultSet rs, int rowNum) throws SQLException {
		Idoc docs = new Idoc();
		docs.setId(rs.getInt("id"));
		docs.setEform_id(rs.getInt("eform_id"));
		docs.setDocument_id(rs.getInt("document_id"));
		docs.setDocument_rv(rs.getInt("document_rv"));
		docs.setCurrent_step(rs.getString("current_step"));
		docs.setDate(rs.getString("date"));
		docs.setDate_update(rs.getString("date_update"));
		docs.setRemarks(rs.getString("remarks"));
		docs.setRemarks_rejected(rs.getString("remarks_rejected"));
		docs.setRemarks_rejected2(rs.getString("remarks_rejected2"));
		docs.setCreator(rs.getInt("creator"));
		docs.setSender(rs.getInt("sender"));
		docs.setReceiver(rs.getInt("receiver"));
		docs.setCleared(rs.getString("cleared"));
		docs.setCurrent_step_int(rs.getInt("current_step_int"));
		docs.setAlert(rs.getInt("alert"));
		docs.setClosing_status(rs.getString("closing_status"));
		
		
		docs.setRefer_user(rs.getString("refer_user"));
		docs.setReference_oldsys(rs.getString("reference_oldsys"));
		docs.setCustomer_oldsys(rs.getString("customer_oldsys"));
		docs.setSale_oldsys(rs.getString("sale_oldsys"));
		docs.setQttotal_oldsys(rs.getString("qttotal_oldsys"));
		docs.setClosing_status_rejected(rs.getString("closing_status_rejected"));
		
		
		docs.setRequest_to_edit(rs.getInt("request_to_edit"));
		
		
		return docs;
		
		
		
	}

}


