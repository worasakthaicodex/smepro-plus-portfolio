package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;


public class edatamapper3 implements RowMapper<edata> {

	@Override
	public  edata mapRow(ResultSet rs, int rowNum) throws SQLException {
		 edata  edatas = new  edata();
		 edatas.setData_id(rs.getInt("data_id"));
		edatas.setData_planid(rs.getInt("data_planid"));
		edatas.setData_eformid(rs.getInt("data_eformid"));
		edatas.setData_eformrv(rs.getInt("data_eformrv"));
		edatas.setData_idadd(rs.getInt("data_idadd"));
		edatas.setData_nameadd(rs.getString("data_nameadd"));
		edatas.setData_namerefer(rs.getString("data_namerefer"));
		edatas.setData_dateadd(rs.getString("data_dateadd"));
		edatas.setData_dateplan(rs.getString("data_dateplan"));
		edatas.setData_plantype(rs.getString("data_plantype"));
		edatas.setData_filetype(rs.getString("data_filetype"));
		edatas.setData_filepath(rs.getString("data_filepath"));
		edatas.setData_idapp(rs.getInt("data_idapp"));
		edatas.setData_nameapp(rs.getString("data_nameapp"));
		edatas.setData_comment(rs.getString("data_comment"));
		edatas.setData_analyst(rs.getString("data_analyst"));
		edatas.setData_title1(rs.getString("data_title1"));
		edatas.setData_value1(rs.getString("data_value1"));
		edatas.setData_title2(rs.getString("data_title2"));
		edatas.setData_value2(rs.getString("data_value2"));
		edatas.setData_title3(rs.getString("data_title3"));
		edatas.setData_value3(rs.getString("data_value3"));
		edatas.setData_title4(rs.getString("data_title4"));
		edatas.setData_value4(rs.getString("data_value4"));
		edatas.setData_title5(rs.getString("data_title5"));
		edatas.setData_value5(rs.getString("data_value5"));
		edatas.setData_title6(rs.getString("data_title6"));
		edatas.setData_value6(rs.getString("data_value6"));
		edatas.setData_title7(rs.getString("data_title7"));
		edatas.setData_value7(rs.getString("data_value7"));
		edatas.setData_title8(rs.getString("data_title8"));
		edatas.setData_value8(rs.getString("data_value8"));
		edatas.setData_title9(rs.getString("data_title9"));
		edatas.setData_value9(rs.getString("data_value9"));
		edatas.setData_title10(rs.getString("data_title10"));
		edatas.setData_value10(rs.getString("data_value10"));
		edatas.setData_title11(rs.getString("data_title11"));
		edatas.setData_value11(rs.getString("data_value11"));
		edatas.setData_title12(rs.getString("data_title12"));
		edatas.setData_value12(rs.getString("data_value12"));
		edatas.setData_title13(rs.getString("data_title13"));
		edatas.setData_value13(rs.getString("data_value13"));
		edatas.setData_title14(rs.getString("data_title14"));
		edatas.setData_value14(rs.getString("data_value14"));
		edatas.setData_title15(rs.getString("data_title15"));
		edatas.setData_value15(rs.getString("data_value15"));
		edatas.setData_title16(rs.getString("data_title16"));
		edatas.setData_value16(rs.getString("data_value16"));
		edatas.setData_title17(rs.getString("data_title17"));
		edatas.setData_value17(rs.getString("data_value17"));
		edatas.setData_title18(rs.getString("data_title18"));
		edatas.setData_value18(rs.getString("data_value18"));
		edatas.setData_title19(rs.getString("data_title19"));
		edatas.setData_value19(rs.getString("data_value19"));
		edatas.setData_title20(rs.getString("data_title20"));
		edatas.setData_value20(rs.getString("data_value20"));
		
		edatas.setData_title21(rs.getString("data_title21"));
		edatas.setData_value21(rs.getString("data_value21"));
		edatas.setData_title22(rs.getString("data_title22"));
		edatas.setData_value22(rs.getString("data_value22"));
		edatas.setData_title23(rs.getString("data_title23"));
		edatas.setData_value23(rs.getString("data_value23"));
		edatas.setData_title24(rs.getString("data_title24"));
		edatas.setData_value24(rs.getString("data_value24"));
		edatas.setData_title25(rs.getString("data_title25"));
		edatas.setData_value25(rs.getString("data_value25"));
		edatas.setData_title26(rs.getString("data_title26"));
		edatas.setData_value26(rs.getString("data_value26"));
		edatas.setData_title27(rs.getString("data_title27"));
		edatas.setData_value27(rs.getString("data_value27"));
		edatas.setData_title28(rs.getString("data_title28"));
		edatas.setData_value28(rs.getString("data_value28"));
		edatas.setData_title29(rs.getString("data_title29"));
		edatas.setData_value29(rs.getString("data_value29"));
		edatas.setData_title30(rs.getString("data_title30"));
		edatas.setData_value30(rs.getString("data_value30"));
		
		
		
		edatas.setData_filetypes(rs.getString("data_filetypes"));
		edatas.setData_filepaths(rs.getString("data_filepaths"));
		edatas.setData_dues(rs.getString("data_dues"));
		
		edatas.setData_join(rs.getInt("data_join"));
		edatas.setStock(rs.getInt("stock"));
		edatas.setData_due(rs.getString("data_due"));
		edatas.setLog_id(rs.getInt("log_id"));
		edatas.setLog_flow(rs.getInt("log_flow"));
		edatas.setLog_date(rs.getString("log_date"));
		edatas.setLog_app(rs.getString("upload_pn_app"));
		edatas.setData_nameadds(rs.getString("data_nameadds"));
		edatas.setData_namerefers(rs.getString("data_namerefers"));
		edatas.setReport_pn_datecheck(rs.getString("report_pn_datecheck"));
		edatas.setLog_note(rs.getString("log_note"));
		edatas.setData_cal_type(rs.getString("data_cal_type"));
		edatas.setData_cal_no(rs.getInt("data_cal_no"));
		edatas.setData_cal_no2(rs.getInt("data_cal_no2"));
		edatas.setData_cal(rs.getString("data_cal"));
		edatas.setData_cal_conditionid(rs.getInt("data_cal_conditionid"));
		edatas.setReport_id(rs.getInt("report_id"));

		edatas.setLog_file(rs.getString("log_file"));
		
		edatas.setData_order(rs.getString("data_order"));
		edatas.setData_minus(rs.getString("data_minus"));
		edatas.setData_plus(rs.getString("data_plus"));
		edatas.setData_dateupdate(rs.getString("data_dateupdate"));
		edatas.setData_department(rs.getString("data_department"));
		edatas.setData_status(rs.getString("data_status"));
		
		
		edatas.setShipping_id(rs.getInt("shipping_id"));
		edatas.setDistributor_id(rs.getInt("distributor_id"));
		edatas.setRecipient(rs.getInt("recipient"));
		
		
		
		edatas.setDetails(rs.getString("details"));
		edatas.setDetails_clearing(rs.getString("details_clearing"));
		edatas.setWithdrawal(rs.getDouble("withdrawal"));
		edatas.setSpent(rs.getDouble("spent"));
		edatas.setReturned(rs.getDouble("returned"));
		edatas.setAdditional(rs.getDouble("additional"));
		edatas.setDrawer_id(rs.getInt("drawer_id"));
		
		
		edatas.setData_flow(rs.getInt("data_flow"));
		edatas.setData_title(rs.getString("data_title"));
		edatas.setDate_clear(rs.getString("date_clear"));
		edatas.setData_datecheck(rs.getString("data_datecheck"));
		

		  if (hasColumn(rs, "process_id")) {
			  edatas.setProcesses_id(rs.getInt("process_id"));
	        }
		  
		  if (hasColumn(rs, "reference")) {
			  edatas.setReference(rs.getString("reference"));
	        }
		  
		  if (hasColumn(rs, "attachdocuments")) {
			  edatas.setAttachdocuments(rs.getInt("attachdocuments"));
	        }
		  if (hasColumn(rs, "attach_type")) {
			  edatas.setAttach_type(rs.getString("attach_type"));
	        }
		  
		  if (hasColumn(rs, "data_file_ck")) {
			  edatas.setData_file_ck(rs.getInt("data_file_ck"));
	        }  
		  if (hasColumn(rs, "loadall")) {
			  edatas.setLoadall(rs.getInt("loadall"));
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
