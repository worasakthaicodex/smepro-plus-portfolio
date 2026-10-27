package com.smepro.models;

import java.util.List;

public class edatas extends upload_pn {

	
	private int data_id;
	private int data_planid;
	private int report_id;
	private int data_eformid;
	private int data_eformrv;
	private int data_idadd;
	private String data_nameadd;
	private String data_namerefer;
	private String data_nameadds;
	private String data_namerefers;
	private String data_dateadd;
	private String data_due;
	private String data_dateplan;
	private String data_plantype;
	private String data_filetype;
	private String data_filepath;
	private int data_idapp;
	private String data_nameapp;
	private String data_comment;
	private String data_analyst;
	private String data_title1;
	private String data_value1;
	private String data_title2;
	private String data_value2;
	private String data_title3;
	private String data_value3;
	private String data_title4;
	private String data_value4;
	private String data_title5;
	private String data_value5;
	private String data_title6;
	private String data_value6;
	private String data_title7;
	private String data_value7;
	private String data_title8;
	private String data_value8;
	private String data_title9;
	private String data_value9;
	private String data_title10;
	private String data_value10;
	private String data_title11;
	private String data_value11;
	private String data_title12;
	private String data_value12;
	private String data_title13;
	private String data_value13;
	private String data_title14;
	private String data_value14;
	private String data_title15;
	private String data_value15;
	private String data_title16;
	private String data_value16;
	private String data_title17;
	private String data_value17;
	private String data_title18;
	private String data_value18;
	private String data_title19;
	private String data_value19;
	private String data_title20;
	private String data_value20;

	
	
	
	
	private String data_titles1;
	private String data_values1;
	private String data_titles2;
	private String data_values2;
	private String data_titles3;
	private String data_values3;
	private String data_titles4;
	private String data_values4;
	private String data_titles5;
	private String data_values5;
	private String data_titles6;
	private String data_values6;
	private String data_titles7;
	private String data_values7;
	private String data_titles8;
	private String data_values8;
	private String data_titles9;
	private String data_values9;
	private String data_titles10;
	private String data_values10;
	private String data_titles11;
	private String data_values11;
	private String data_titles12;
	private String data_values12;
	private String data_titles13;
	private String data_values13;
	private String data_titles14;
	private String data_values14;
	private String data_titles15;
	private String data_values15;
	private String data_titles16;
	private String data_values16;
	private String data_titles17;
	private String data_values17;
	private String data_titles18;
	private String data_values18;
	private String data_titles19;
	private String data_values19;
	private String data_titles20;
	private String data_values20;
	private int auto_read;
	
	
	
	
	private String data_filetypes;
	private String data_filepaths;
	private String data_dues;
	private int data_join;
	private String data_app;
	private String report_pn_datecheck;

	private String data_cal_type;
	private long data_cal_no;
	private long data_cal_no2;
	private int data_cal_conditionid;
	private String data_cal;

	private String data_order;
	private String data_minus;
	private String data_plus;
	
	private int stock;
	private int id_refer;
	
	private String data_dateupdate;
	private String data_dateedit;
	private String data_department;
	private String log;
	private String data_status;
	private int alert = 0;
	
	private String current_step;
	private String date;
	private String remarks;
	private String remarks_rejected;
	private String remarks_rejected2;
	private int creator;
	private int sender;
	private int receiver;
	
	
	private int distributor_id;
	private int shipping_id;
	private int recipient;
	
	private String data_title;
	private String date_clear;
	
	
	private Double additional = 0.0;
	private Double returned= 0.0;
	private Double spent= 0.0;
	private Double withdrawal= 0.00;
	private String details_clearing;
	private String details;
	private int drawer_id;
	private int cleared;
	List<file> file;
	private int data_flow;
	private int application;

	

	
	
	public List<file> getFile() {
		return file;
	}
	public void setFile(List<file> file) {
		this.file = file;
	}
	public Double getAdditional() {
		return additional;
	}
	public void setAdditional(Double additional) {
		this.additional = additional == null  ? 0.0 : additional;
	}
	public Double getReturned() {
		return returned;
	}
	public void setReturned(Double returned) {
		this.returned = returned  == null  ? 0.0 : returned;
	}
	public Double getSpent() {
		return spent;
	}
	public void setSpent(Double spent) {
		this.spent = spent== null  ? 0.0 : spent;
	}
	public Double getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(Double withdrawal) {
		this.withdrawal = withdrawal== null  ? 0.0 : withdrawal;
	}
	public String getDetails_clearing() {
		return details_clearing;
	}
	public void setDetails_clearing(String details_clearing) {
		this.details_clearing = details_clearing;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getData_nameadds() {
		return data_nameadds;
	}
	public void setData_nameadds(String data_nameadds) {
		this.data_nameadds = data_nameadds;
	}

	public String getData_namerefers() {
		return data_namerefers;
	}

	public void setData_namerefers(String data_namerefers) {
		this.data_namerefers = data_namerefers;
	}

	public String getData_namerefer() {
		return data_namerefer;
	}
	public void setData_namerefer(String data_namerefer) {
		this.data_namerefer = data_namerefer;
	}
	
	
	public int getData_id() {
		return data_id;
	}


	public void setData_id(int data_id) {
		this.data_id = data_id;
	}

	public int getData_planid() {
		return data_planid;
	}

	public void setData_planid(int data_planid) {
		this.data_planid = data_planid;
	}


	public int getData_eformid() {
		return data_eformid;
	}


	public void setData_eformid(int data_eformid) {
		this.data_eformid = data_eformid;
	}

	public int getData_eformrv() {
		return data_eformrv;
	}


	public void setData_eformrv(int data_eformrv) {
		this.data_eformrv = data_eformrv;
	}

	public int getData_idadd() {
		return data_idadd;
	}

	public void setData_idadd(int data_idadd) {
		this.data_idadd = data_idadd;
	}

	public String getData_nameadd() {
		return data_nameadd;
	}

	public void setData_nameadd(String data_nameadd) {
		this.data_nameadd = data_nameadd;
	}


	public String getData_dateadd() {
		return data_dateadd;
	}

	public void setData_dateadd(String data_dateadd) {
		this.data_dateadd = data_dateadd;
	}


	public String getData_dateplan() {
		return data_dateplan;
	}

	public void setData_dateplan(String data_dateplan) {
		this.data_dateplan = data_dateplan;
	}

	public String getData_plantype() {
		return data_plantype;
	}

	public void setData_plantype(String data_plantype) {
		this.data_plantype = data_plantype;
	}

	public String getData_filetype() {
		return data_filetype;
	}

	public void setData_filetype(String data_filetype) {
		this.data_filetype = data_filetype;
	}

	public String getData_filepath() {
		return data_filepath;
	}

	public void setData_filepath(String data_filepath) {
		this.data_filepath = data_filepath;
	}

	public int getData_idapp() {
		return data_idapp;
	}

	public void setData_idapp(int data_idapp) {
		this.data_idapp = data_idapp;
	}

	public String getData_nameapp() {
		return data_nameapp;
	}


	public void setData_nameapp(String data_nameapp) {
		this.data_nameapp = data_nameapp;
	}


	public String getData_comment() {
		return data_comment;
	}

	public void setData_comment(String data_comment) {
		this.data_comment = data_comment;
	}


	public String getData_analyst() {
		return data_analyst;
	}


	public void setData_analyst(String data_analyst) {
		this.data_analyst = data_analyst;
	}

	public String getData_title1() {
		return data_title1;
	}

	public void setData_title1(String data_title1) {
		this.data_title1 = data_title1;
	}


	public String getData_value1() {
		return data_value1;
	}

	public void setData_value1(String data_value1) {
		this.data_value1 = data_value1;
	}

	public String getData_title2() {
		return data_title2;
	}

	public void setData_title2(String data_title2) {
		this.data_title2 = data_title2;
	}

	public String getData_value2() {
		return data_value2;
	}

	public void setData_value2(String data_value2) {
		this.data_value2 = data_value2;
	}


	public String getData_title3() {
		return data_title3;
	}

	public void setData_title3(String data_title3) {
		this.data_title3 = data_title3;
	}
public String getData_value3() {
		return data_value3;
	}

	public void setData_value3(String data_value3) {
		this.data_value3 = data_value3;
	}

	public String getData_title4() {
		return data_title4;
	}

	public void setData_title4(String data_title4) {
		this.data_title4 = data_title4;
	}

	public String getData_value4() {
		return data_value4;
	}

	public void setData_value4(String data_value4) {
		this.data_value4 = data_value4;
	}

	public String getData_title5() {
		return data_title5;
	}


	public void setData_title5(String data_title5) {
		this.data_title5 = data_title5;
	}

	public String getData_value5() {
		return data_value5;
	}


	public void setData_value5(String data_value5) {
		this.data_value5 = data_value5;
	}

	public String getData_title6() {
		return data_title6;
	}


	public void setData_title6(String data_title6) {
		this.data_title6 = data_title6;
	}

	public String getData_value6() {
		return data_value6;
	}

	public void setData_value6(String data_value6) {
		this.data_value6 = data_value6;
	}

	public String getData_title7() {
		return data_title7;
	}

	public void setData_title7(String data_title7) {
		this.data_title7 = data_title7;
	}


	public String getData_value7() {
		return data_value7;
	}

	public void setData_value7(String data_value7) {
		this.data_value7 = data_value7;
	}

	public String getData_title8() {
		return data_title8;
	}

	public void setData_title8(String data_title8) {
		this.data_title8 = data_title8;
	}


	public String getData_value8() {
		return data_value8;
	}


	public void setData_value8(String data_value8) {
		this.data_value8 = data_value8;
	}

	public String getData_title9() {
		return data_title9;
	}

	public void setData_title9(String data_title9) {
		this.data_title9 = data_title9;
	}

	public String getData_value9() {
		return data_value9;
	}

	public void setData_value9(String data_value9) {
		this.data_value9 = data_value9;
	}

	public String getData_title10() {
		return data_title10;
	}

	public void setData_title10(String data_title10) {
		this.data_title10 = data_title10;
	}

	public String getData_value10() {
		return data_value10;
	}

	public void setData_value10(String data_value10) {
		this.data_value10 = data_value10;
	}

	public String getData_title11() {
		return data_title11;
	}


	public void setData_title11(String data_title11) {
		this.data_title11 = data_title11;
	}

	public String getData_value11() {
		return data_value11;
	}

	public void setData_value11(String data_value11) {
		this.data_value11 = data_value11;
	}

	public String getData_title12() {
		return data_title12;
	}


	public void setData_title12(String data_title12) {
		this.data_title12 = data_title12;
	}

	public String getData_value12() {
		return data_value12;
	}


	public void setData_value12(String data_value12) {
		this.data_value12 = data_value12;
	}

	public String getData_title13() {
		return data_title13;
	}

	public void setData_title13(String data_title13) {
		this.data_title13 = data_title13;
	}

	public String getData_value13() {
		return data_value13;
	}

	public void setData_value13(String data_value13) {
		this.data_value13 = data_value13;
	}

	public String getData_title14() {
		return data_title14;
	}

	public void setData_title14(String data_title14) {
		this.data_title14 = data_title14;
	}

	public String getData_value14() {
		return data_value14;
	}

	public void setData_value14(String data_value14) {
		this.data_value14 = data_value14;
	}

	public String getData_title15() {
		return data_title15;
	}

	public void setData_title15(String data_title15) {
		this.data_title15 = data_title15;
	}

	public String getData_value15() {
		return data_value15;
	}

	public void setData_value15(String data_value15) {
		this.data_value15 = data_value15;
	}

	public String getData_title16() {
		return data_title16;
	}

	public void setData_title16(String data_title16) {
		this.data_title16 = data_title16;
	}

	public String getData_value16() {
		return data_value16;
	}

	public void setData_value16(String data_value16) {
		this.data_value16 = data_value16;
	}

	public String getData_title17() {
		return data_title17;
	}

	public void setData_title17(String data_title17) {
		this.data_title17 = data_title17;
	}

	public String getData_value17() {
		return data_value17;
	}

	public void setData_value17(String data_value17) {
		this.data_value17 = data_value17;
	}

	public String getData_title18() {
		return data_title18;
	}

	public void setData_title18(String data_title18) {
		this.data_title18 = data_title18;
	}


	public String getData_value18() {
		return data_value18;
	}


	public void setData_value18(String data_value18) {
		this.data_value18 = data_value18;
	}

	public String getData_title19() {
		return data_title19;
	}



	public void setData_title19(String data_title19) {
		this.data_title19 = data_title19;
	}


	public String getData_value19() {
		return data_value19;
	}

	public void setData_value19(String data_value19) {
		this.data_value19 = data_value19;
	}


	public String getData_title20() {
		return data_title20;
	}

	public void setData_title20(String data_title20) {
		this.data_title20 = data_title20;
	}


	public String getData_value20() {
		return data_value20;
	}

	public void setData_value20(String data_value20) {
		this.data_value20 = data_value20;
	}

	

	public String getData_due() {
		return data_due;
	}


	public void setData_due(String data_due) {
		this.data_due = data_due;
	}

	public String getData_titles1() {
		return data_titles1;
	}


	public void setData_titles1(String data_titles1) {
		this.data_titles1 = data_titles1;
	}


	public String getData_values1() {
		return data_values1;
	}


	public void setData_values1(String data_values1) {
		this.data_values1 = data_values1;
	}


	public String getData_titles2() {
		return data_titles2;
	}


	public void setData_titles2(String data_titles2) {
		this.data_titles2 = data_titles2;
	}


	public String getData_values2() {
		return data_values2;
	}


	public void setData_values2(String data_values2) {
		this.data_values2 = data_values2;
	}


	public String getData_titles3() {
		return data_titles3;
	}


	public void setData_titles3(String data_titles3) {
		this.data_titles3 = data_titles3;
	}


	public String getData_values3() {
		return data_values3;
	}


	public void setData_values3(String data_values3) {
		this.data_values3 = data_values3;
	}


	public String getData_titles4() {
		return data_titles4;
	}


	public void setData_titles4(String data_titles4) {
		this.data_titles4 = data_titles4;
	}


	public String getData_values4() {
		return data_values4;
	}


	public void setData_values4(String data_values4) {
		this.data_values4 = data_values4;
	}


	public String getData_titles5() {
		return data_titles5;
	}


	public void setData_titles5(String data_titles5) {
		this.data_titles5 = data_titles5;
	}


	public String getData_values5() {
		return data_values5;
	}


	public void setData_values5(String data_values5) {
		this.data_values5 = data_values5;
	}


	public String getData_titles6() {
		return data_titles6;
	}


	public void setData_titles6(String data_titles6) {
		this.data_titles6 = data_titles6;
	}


	public String getData_values6() {
		return data_values6;
	}


	public void setData_values6(String data_values6) {
		this.data_values6 = data_values6;
	}


	public String getData_titles7() {
		return data_titles7;
	}


	public void setData_titles7(String data_titles7) {
		this.data_titles7 = data_titles7;
	}


	public String getData_values7() {
		return data_values7;
	}


	public void setData_values7(String data_values7) {
		this.data_values7 = data_values7;
	}


	public String getData_titles8() {
		return data_titles8;
	}


	public void setData_titles8(String data_titles8) {
		this.data_titles8 = data_titles8;
	}


	public String getData_values8() {
		return data_values8;
	}


	public void setData_values8(String data_values8) {
		this.data_values8 = data_values8;
	}


	public String getData_titles9() {
		return data_titles9;
	}


	public void setData_titles9(String data_titles9) {
		this.data_titles9 = data_titles9;
	}


	public String getData_values9() {
		return data_values9;
	}


	public void setData_values9(String data_values9) {
		this.data_values9 = data_values9;
	}


	public String getData_titles10() {
		return data_titles10;
	}


	public void setData_titles10(String data_titles10) {
		this.data_titles10 = data_titles10;
	}


	public String getData_values10() {
		return data_values10;
	}


	public void setData_values10(String data_values10) {
		this.data_values10 = data_values10;
	}


	public String getData_titles11() {
		return data_titles11;
	}


	public void setData_titles11(String data_titles11) {
		this.data_titles11 = data_titles11;
	}


	public String getData_values11() {
		return data_values11;
	}


	public void setData_values11(String data_values11) {
		this.data_values11 = data_values11;
	}


	public String getData_titles12() {
		return data_titles12;
	}


	public void setData_titles12(String data_titles12) {
		this.data_titles12 = data_titles12;
	}


	public String getData_values12() {
		return data_values12;
	}


	public void setData_values12(String data_values12) {
		this.data_values12 = data_values12;
	}


	public String getData_titles13() {
		return data_titles13;
	}


	public void setData_titles13(String data_titles13) {
		this.data_titles13 = data_titles13;
	}


	public String getData_values13() {
		return data_values13;
	}


	public void setData_values13(String data_values13) {
		this.data_values13 = data_values13;
	}


	public String getData_titles14() {
		return data_titles14;
	}


	public void setData_titles14(String data_titles14) {
		this.data_titles14 = data_titles14;
	}


	public String getData_values14() {
		return data_values14;
	}


	public void setData_values14(String data_values14) {
		this.data_values14 = data_values14;
	}


	public String getData_titles15() {
		return data_titles15;
	}


	public void setData_titles15(String data_titles15) {
		this.data_titles15 = data_titles15;
	}


	public String getData_values15() {
		return data_values15;
	}


	public void setData_values15(String data_values15) {
		this.data_values15 = data_values15;
	}


	public String getData_titles16() {
		return data_titles16;
	}


	public void setData_titles16(String data_titles16) {
		this.data_titles16 = data_titles16;
	}


	public String getData_values16() {
		return data_values16;
	}


	public void setData_values16(String data_values16) {
		this.data_values16 = data_values16;
	}


	public String getData_titles17() {
		return data_titles17;
	}


	public void setData_titles17(String data_titles17) {
		this.data_titles17 = data_titles17;
	}


	public String getData_values17() {
		return data_values17;
	}


	public void setData_values17(String data_values17) {
		this.data_values17 = data_values17;
	}


	public String getData_titles18() {
		return data_titles18;
	}


	public void setData_titles18(String data_titles18) {
		this.data_titles18 = data_titles18;
	}


	public String getData_values18() {
		return data_values18;
	}


	public void setData_values18(String data_values18) {
		this.data_values18 = data_values18;
	}


	public String getData_titles19() {
		return data_titles19;
	}


	public void setData_titles19(String data_titles19) {
		this.data_titles19 = data_titles19;
	}


	public String getData_values19() {
		return data_values19;
	}


	public void setData_values19(String data_values19) {
		this.data_values19 = data_values19;
	}


	public String getData_titles20() {
		return data_titles20;
	}


	public void setData_titles20(String data_titles20) {
		this.data_titles20 = data_titles20;
	}


	public String getData_values20() {
		return data_values20;
	}


	public void setData_values20(String data_values20) {
		this.data_values20 = data_values20;
	}


	public int getData_join() {
		return data_join;
	}


	public void setData_join(int data_join) {
		this.data_join = data_join;
	}


	public String getReport_pn_datecheck() {
		return report_pn_datecheck;
	}


	public void setReport_pn_datecheck(String report_pn_datecheck) {
		this.report_pn_datecheck = report_pn_datecheck;
	}

	public String getData_app() {
		return data_app;
	}

	public void setData_app(String data_app) {
		this.data_app = data_app;
	}
	public String getData_cal_type() {
		return data_cal_type;
	}
	public void setData_cal_type(String data_cal_type) {
		this.data_cal_type = data_cal_type;
	}
	public long getData_cal_no() {
		return data_cal_no;
	}
	public void setData_cal_no(long data_cal_no) {
		this.data_cal_no = data_cal_no;
	}
	public int getData_cal_conditionid() {
		return data_cal_conditionid;
	}
	public void setData_cal_conditionid(int data_cal_conditionid) {
		this.data_cal_conditionid = data_cal_conditionid;
	}
	public String getData_cal() {
		return data_cal;
	}
	public void setData_cal(String data_cal) {
		this.data_cal = data_cal;
	}
	
	
	public long getData_cal_no2() {
		return data_cal_no2;
	}
	public void setData_cal_no2(long data_cal_no2) {
		this.data_cal_no2 = data_cal_no2;
	}
	
	
	
	
	public String getData_order() {
		return data_order;
	}
	public void setData_order(String data_order) {
		this.data_order = data_order;
	}
	public String getData_minus() {
		return data_minus;
	}
	public void setData_minus(String data_minus) {
		this.data_minus = data_minus;
	}
	public String getData_plus() {
		return data_plus;
	}
	public void setData_plus(String data_plus) {
		this.data_plus = data_plus;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getData_filetypes() {
		return data_filetypes;
	}
	public void setData_filetypes(String data_filetypes) {
		this.data_filetypes = data_filetypes;
	}
	public String getData_filepaths() {
		return data_filepaths;
	}
	public void setData_filepaths(String data_filepaths) {
		this.data_filepaths = data_filepaths;
	}
	public String getData_dues() {
		return data_dues;
	}
	public void setData_dues(String data_dues) {
		this.data_dues = data_dues;
	}
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getData_dateupdate() {
		return data_dateupdate;
	}
	public void setData_dateupdate(String data_dateupdate) {
		this.data_dateupdate = data_dateupdate;
	}

	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}

	public String getData_dateedit() {
		return data_dateedit;
	}
	public void setData_dateedit(String data_dateedit) {
		this.data_dateedit = data_dateedit;
	}
	public String getData_department() {
		return data_department;
	}
	public void setData_department(String data_department) {
		this.data_department = data_department;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public int getAuto_read() {
		return auto_read;
	}
	public void setAuto_read(int auto_read) {
		this.auto_read = auto_read;
	}
	public int getAlert() {
		return alert;
	}
	public void setAlert(int alert) {
		this.alert = alert;
	}
	
	
	public String getCurrent_step() {
		return current_step;
	}
	public void setCurrent_step(String current_step) {
		this.current_step = current_step;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks_rejected() {
		return remarks_rejected;
	}
	public void setRemarks_rejected(String remarks_rejected) {
		this.remarks_rejected = remarks_rejected;
	}
	public String getRemarks_rejected2() {
		return remarks_rejected2;
	}
	public void setRemarks_rejected2(String remarks_rejected2) {
		this.remarks_rejected2 = remarks_rejected2;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public int getDistributor_id() {
		return distributor_id;
	}
	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}
	public int getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(int shipping_id) {
		this.shipping_id = shipping_id;
	}
	public int getRecipient() {
		return recipient;
	}
	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}

	public int getDrawer_id() {
		return drawer_id;
	}
	public void setDrawer_id(int drawer_id) {
		this.drawer_id = drawer_id;
	}
	public String getData_title() {
		return data_title;
	}
	public void setData_title(String data_title) {
		this.data_title = data_title;
	}
	public String getDate_clear() {
		return date_clear;
	}
	public void setDate_clear(String date_clear) {
		this.date_clear = date_clear;
	}
	public int getCleared() {
		return cleared;
	}
	public void setCleared(int cleared) {
		this.cleared = cleared;
	}
	public int getData_flow() {
		return data_flow;
	}
	public void setData_flow(int data_flow) {
		this.data_flow = data_flow;
	}
	public int getApplication() {
		return application;
	}
	public void setApplication(int application) {
		this.application = application;
	}
	
	

	@Override
	public String toString() {
		return String.format(
				"edata [data_id=%s, data_planid=%s, data_eformid=%s, data_eformrv=%s, data_idadd=%s, data_nameadd=%s, data_dateadd=%s, data_dateplan=%s, data_plantype=%s, data_filetype=%s, data_filepath=%s, data_idapp=%s, data_nameapp=%s, data_comment=%s, data_analyst=%s, data_title1=%s, data_value1=%s, data_title2=%s, data_value2=%s, data_title3=%s, data_value3=%s, data_title4=%s, data_value4=%s, data_title5=%s, data_value5=%s, data_title6=%s, data_value6=%s, data_title7=%s, data_value7=%s, data_title8=%s, data_value8=%s, data_title9=%s, data_value9=%s, data_title10=%s, data_value10=%s, data_title11=%s, data_value11=%s, data_title12=%s, data_value12=%s, data_title13=%s, data_value13=%s, data_title14=%s, data_value14=%s, data_title15=%s, data_value15=%s, data_title16=%s, data_value16=%s, data_title17=%s, data_value17=%s, data_title18=%s, data_value18=%s, data_title19=%s, data_value19=%s, data_title20=%s, data_value20=%s]",
				data_id, data_planid, data_eformid, data_eformrv, data_idadd, data_nameadd, data_dateadd, data_dateplan,
				data_plantype, data_filetype, data_filepath, data_idapp, data_nameapp, data_comment, data_analyst,
				data_title1, data_value1, data_title2, data_value2, data_title3, data_value3, data_title4, data_value4,
				data_title5, data_value5, data_title6, data_value6, data_title7, data_value7, data_title8, data_value8,
				data_title9, data_value9, data_title10, data_value10, data_title11, data_value11, data_title12,
				data_value12, data_title13, data_value13, data_title14, data_value14, data_title15, data_value15,
				data_title16, data_value16, data_title17, data_value17, data_title18, data_value18, data_title19,
				data_value19, data_title20, data_value20);
	}

	
	
	
	
	
	
	
	
}
