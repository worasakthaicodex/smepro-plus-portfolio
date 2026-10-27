package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;


public class edatamapper implements RowMapper<edata> {

	@Override
	public  edata mapRow(ResultSet rs, int rowNum) throws SQLException {
		 edata  edatas = new  edata();
		 edatas.setData_id(rs.getInt("data_id"));
		edatas.setData_planid(rs.getInt("data_planid"));
		edatas.setData_eformid(rs.getInt("data_eformid"));
		edatas.setData_eformrv(rs.getInt("data_eformrv"));
		edatas.setData_idadd(rs.getInt("data_idadd"));
		edatas.setData_nameadd(rs.getString("data_nameadd"));
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
		edatas.setData_due(rs.getString("data_due"));
		edatas.setLog_id(rs.getInt("log_id"));
		edatas.setLog_flow(rs.getInt("log_flow"));
		edatas.setData_namerefer(rs.getString("data_namerefer"));
		edatas.setLog_note(rs.getString("log_note"));
		return edatas;
		
	}

}
