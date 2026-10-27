package com.smepro.models;

public class PlatformUserSettings {

	private int processes_id;
	private process  process;
	private Tabform tabform;
	private InputHiddenVisibility inputHiddenVisibility;
	private String remove_id;
	private int action_id;
	
	
	public int getProcesses_id() {
		return processes_id;
	}
	public void setProcesses_id(int processes_id) {
		this.processes_id = processes_id;
	}
	public Tabform getTabform() {
		return tabform;
	}
	public void setTabform(Tabform tabform) {
		this.tabform = tabform;
	}
	public String getRemove_id() {
		return remove_id;
	}
	public void setRemove_id(String remove_id) {
		this.remove_id = remove_id;
	}
	public int getAction_id() {
		return action_id;
	}
	public void setAction_id(int action_id) {
		this.action_id = action_id;
	}
	public InputHiddenVisibility getInputHiddenVisibility() {
		return inputHiddenVisibility;
	}
	public void setInputHiddenVisibility(InputHiddenVisibility inputHiddenVisibility) {
		this.inputHiddenVisibility = inputHiddenVisibility;
	}
	public process getProcess() {
		return process;
	}
	public void setProcess(process process) {
		this.process = process;
	}
	

	
}
