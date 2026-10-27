package com.smepro.models;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestParam;

public class PlatformConfig {

	
	private int sub_processes_id;
	private int processe_id;
	private int workflow_id;
	private String type;
	private keymap keymap;
	private String action_batch_insert;
	private process process;
	
	private String datacolumn1;
	private String filter1;
	private String input_db1;
	
	private String datacolumn2;
	private String filter2;
	private String input_db2;
	
	private String datacolumn3;
	private String filter3;
	private String input_db3;
	
	private String datacolumn4;
	private String filter4;
	private String input_db4;
	
	
	private String datacolumn5;
	private String filter5;
	private String input_db5;
	
	
	private String datacolumn6;
	private String filter6;
	private String input_db6;
	
	private String datacolumn7;
	private String filter7;
	private String input_db7;
	
	private String datacolumn8;
	private String filter8;
	private String input_db8;
	
	private String datacolumn9;
	private String filter9;
	private String input_db9;
	
	private int accepting;
	private String prohibited1;
	private String prohibited2;
	private String visibility;
	private String enums;
	private String config;
	private String config_id;
	private int renamefile;
	
	private String autoiddatacolumn;
	private String code_app;
	
	
	@NotNull(message = "activate_register must not be Null")
	private  String activate_register = "-";
	@NotNull(message = "activate_action must not be Null")
	private  String activate_action = "-";

	@NotNull(message = "activate_autoid must not be Null")
	private  String activate_autoid = "-";
	
	private String closing_status_expired;
	private int  expires_in_days;
	private int  is_acknowledge_required; 
	
	private String closing_evidence_type;
	private int  can_refuse_and_return;
	private String  is_attachment_required_to_close; 
	private String  sub_process_expired; 
	


	public int getSub_processes_id() {
		return sub_processes_id;
	}


	public void setSub_processes_id(int sub_processes_id) {
		this.sub_processes_id = sub_processes_id;
	}


	public int getProcesse_id() {
		return processe_id;
	}


	public void setProcesse_id(int processe_id) {
		this.processe_id = processe_id;
	}

	public int getWorkflow_id() {
		return workflow_id;
	}

	public void setWorkflow_id(int workflow_id) {
		this.workflow_id = workflow_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public keymap getKeymap() {
		return keymap;
	}


	public void setKeymap(keymap keymap) {
		this.keymap = keymap;
	}


	public String getDatacolumn1() {
		return datacolumn1;
	}

	public void setDatacolumn1(String datacolumn1) {
		this.datacolumn1 = datacolumn1;
	}

	public String getFilter1() {
		return filter1;
	}

	public void setFilter1(String filter1) {
		this.filter1 = filter1;
	}

	public String getInput_db1() {
		return input_db1;
	}

	public void setInput_db1(String input_db1) {
		this.input_db1 = input_db1;
	}

	public String getDatacolumn2() {
		return datacolumn2;
	}

	public void setDatacolumn2(String datacolumn2) {
		this.datacolumn2 = datacolumn2;
	}

	public String getFilter2() {
		return filter2;
	}

	public void setFilter2(String filter2) {
		this.filter2 = filter2;
	}

	public String getInput_db2() {
		return input_db2;
	}

	public void setInput_db2(String input_db2) {
		this.input_db2 = input_db2;
	}


	public String getDatacolumn3() {
		return datacolumn3;
	}


	public void setDatacolumn3(String datacolumn3) {
		this.datacolumn3 = datacolumn3;
	}

	public String getFilter3() {
		return filter3;
	}

	public void setFilter3(String filter3) {
		this.filter3 = filter3;
	}

	public String getInput_db3() {
		return input_db3;
	}

	public void setInput_db3(String input_db3) {
		this.input_db3 = input_db3;
	}

	public String getDatacolumn4() {
		return datacolumn4;
	}

	public void setDatacolumn4(String datacolumn4) {
		this.datacolumn4 = datacolumn4;
	}

	public String getFilter4() {
		return filter4;
	}

	public void setFilter4(String filter4) {
		this.filter4 = filter4;
	}

	public String getInput_db4() {
		return input_db4;
	}
	public void setInput_db4(String input_db4) {
		this.input_db4 = input_db4;
	}

	public String getDatacolumn5() {
		return datacolumn5;
	}

	public void setDatacolumn5(String datacolumn5) {
		this.datacolumn5 = datacolumn5;
	}

	public String getFilter5() {
		return filter5;
	}

	public void setFilter5(String filter5) {
		this.filter5 = filter5;
	}

	public String getInput_db5() {
		return input_db5;
	}

	public void setInput_db5(String input_db5) {
		this.input_db5 = input_db5;
	}

	public String getDatacolumn6() {
		return datacolumn6;
	}

	public void setDatacolumn6(String datacolumn6) {
		this.datacolumn6 = datacolumn6;
	}

	public String getFilter6() {
		return filter6;
	}

	public void setFilter6(String filter6) {
		this.filter6 = filter6;
	}

	public String getInput_db6() {
		return input_db6;
	}

	public void setInput_db6(String input_db6) {
		this.input_db6 = input_db6;
	}

	public String getDatacolumn7() {
		return datacolumn7;
	}

	public void setDatacolumn7(String datacolumn7) {
		this.datacolumn7 = datacolumn7;
	}

	public String getFilter7() {
		return filter7;
	}

	public void setFilter7(String filter7) {
		this.filter7 = filter7;
	}
	public String getInput_db7() {
		return input_db7;
	}

	public void setInput_db7(String input_db7) {
		this.input_db7 = input_db7;
	}

	public String getDatacolumn8() {
		return datacolumn8;
	}

	public void setDatacolumn8(String datacolumn8) {
		this.datacolumn8 = datacolumn8;
	}

	public String getFilter8() {
		return filter8;
	}

	public void setFilter8(String filter8) {
		this.filter8 = filter8;
	}

	public String getInput_db8() {
		return input_db8;
	}

	public void setInput_db8(String input_db8) {
		this.input_db8 = input_db8;
	}

	public String getDatacolumn9() {
		return datacolumn9;
	}

	public void setDatacolumn9(String datacolumn9) {
		this.datacolumn9 = datacolumn9;
	}

	public String getFilter9() {
		return filter9;
	}

	public void setFilter9(String filter9) {
		this.filter9 = filter9;
	}

	public String getInput_db9() {
		return input_db9;
	}

	public void setInput_db9(String input_db9) {
		this.input_db9 = input_db9;
	}

	public String getActivate_register() {
		return activate_register;
	}

	public void setActivate_register(String activate_register) {
		this.activate_register = activate_register;
	}
	

	public int getAccepting() {
		return accepting;
	}

	public void setAccepting(int accepting) {
		this.accepting = accepting;
	}

	public String getProhibited1() {
		return prohibited1;
	}

	public void setProhibited1(String prohibited1) {
		this.prohibited1 = prohibited1;
	}

	public String getProhibited2() {
		return prohibited2;
	}

	public void setProhibited2(String prohibited2) {
		this.prohibited2 = prohibited2;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	
	
	
	public String getActivate_action() {
		return activate_action;
	}

	public void setActivate_action(String activate_action) {
		this.activate_action = activate_action;
	}

	public String getEnums() {
		return enums;
	}

	public void setEnums(String enums) {
		this.enums = enums;
	}
	

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	
	public String getConfig_id() {
		return config_id;
	}

	public void setConfig_id(String config_id) {
		this.config_id = config_id;
	}

	
	
	public String getAction_batch_insert() {
		return action_batch_insert;
	}
	public void setAction_batch_insert(String action_batch_insert) {
		this.action_batch_insert = action_batch_insert;
	}
	public process getProcess() {
		return process;
	}

	public void setProcess(process process) {
		this.process = process;
	}
	public int getRenamefile() {
		return renamefile;
	}

	public void setRenamefile(int renamefile) {
		this.renamefile = renamefile;
	}
	
	

	public String getAutoiddatacolumn() {
		return autoiddatacolumn;
	}

	public void setAutoiddatacolumn(String autoiddatacolumn) {
		this.autoiddatacolumn = autoiddatacolumn;
	}

	public String getCode_app() {
		return code_app;
	}

	public void setCode_app(String code_app) {
		this.code_app = code_app;
	}

	public String getActivate_autoid() {
		return activate_autoid;
	}

	public void setActivate_autoid(String activate_autoid) {
		this.activate_autoid = activate_autoid;
	}
	
	

	public String getClosing_status_expired() {
		return closing_status_expired;
	}


	public void setClosing_status_expired(String closing_status_expired) {
		this.closing_status_expired = closing_status_expired;
	}


	public int getExpires_in_days() {
		return expires_in_days;
	}


	public void setExpires_in_days(int expires_in_days) {
		this.expires_in_days = expires_in_days;
	}


	public int getIs_acknowledge_required() {
		return is_acknowledge_required;
	}


	public void setIs_acknowledge_required(int is_acknowledge_required) {
		this.is_acknowledge_required = is_acknowledge_required;
	}


	public String getClosing_evidence_type() {
		return closing_evidence_type;
	}


	public void setClosing_evidence_type(String closing_evidence_type) {
		this.closing_evidence_type = closing_evidence_type;
	}


	public int getCan_refuse_and_return() {
		return can_refuse_and_return;
	}


	public void setCan_refuse_and_return(int can_refuse_and_return) {
		this.can_refuse_and_return = can_refuse_and_return;
	}


	public String getIs_attachment_required_to_close() {
		return is_attachment_required_to_close;
	}


	public void setIs_attachment_required_to_close(String is_attachment_required_to_close) {
		this.is_attachment_required_to_close = is_attachment_required_to_close;
	}


	public String getSub_process_expired() {
		return sub_process_expired;
	}


	public void setSub_process_expired(String sub_process_expired) {
		this.sub_process_expired = sub_process_expired;
	}


	@Override
	public String toString() {
		return String.format(
				"PlatformConfig [_processes_id=%s, processe_id=%s, workflow_id=%s, type=%s, keymap=%s, datacolumn1=%s, filter1=%s, input_db1=%s, datacolumn2=%s, filter2=%s, input_db2=%s, datacolumn3=%s, filter3=%s, input_db3=%s, datacolumn4=%s, filter4=%s, input_db4=%s, datacolumn5=%s, filter5=%s, input_db5=%s, datacolumn6=%s, filter6=%s, input_db6=%s, datacolumn7=%s, filter7=%s, input_db7=%s, datacolumn8=%s, filter8=%s, input_db8=%s, datacolumn9=%s, filter9=%s, input_db9=%s, activate_register=%s]",
				sub_processes_id, processe_id, workflow_id, type, keymap, datacolumn1, filter1, input_db1, datacolumn2,
				filter2, input_db2, datacolumn3, filter3, input_db3, datacolumn4, filter4, input_db4, datacolumn5,
				filter5, input_db5, datacolumn6, filter6, input_db6, datacolumn7, filter7, input_db7, datacolumn8,
				filter8, input_db8, datacolumn9, filter9, input_db9, activate_register);
	}




	
	
	
}
