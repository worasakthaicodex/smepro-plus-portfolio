package com.smepro.models;

public class task {

	 
	private int task_id; 
	private String task_name; 
	private String task_note; 
	private String task_date; 
	private String task_iduser; 
	private int task_idrefer; 
	private int task_idadd; 
	private int task_idcheck; 
	private int task_status;
	private String task_datestart;
	private String task_dateend;
	private int task_cm;
	private int task_alert;
	
	private String task_budget;
	private String task_payment;

	private String task_expenses;
	private String task_real_payment;
	
	private int keyactivity_id;
	
	private int budget_source1;
	private int budget_source2;
	private int budget_source3;
	
	private Double task_budget1 = 0.0;
	private Double task_budget2 = 0.0;
	private Double task_budget3 = 0.0;
	private String outcome;
	
	private Double use_budget = 0.0;
	private Double plan_budget = 0.0;
	 private long count_problems;
	
	private String activity_task;
	private String end_task;
	
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_note() {
		return task_note;
	}
	public void setTask_note(String task_note) {
		this.task_note = task_note;
	}
	public String getTask_date() {
		return task_date;
	}
	public void setTask_date(String task_date) {
		this.task_date = task_date;
	}
	public String getTask_iduser() {
		return task_iduser;
	}
	public void setTask_iduser(String task_iduser) {
		this.task_iduser = task_iduser;
	}
	public int getTask_idrefer() {
		return task_idrefer;
	}
	public void setTask_idrefer(int task_idrefer) {
		this.task_idrefer = task_idrefer;
	}
	public int getTask_idadd() {
		return task_idadd;
	}
	public void setTask_idadd(int task_idadd) {
		this.task_idadd = task_idadd;
	}
	public int getTask_status() {
		return task_status;
	}
	public void setTask_status(int task_status) {
		this.task_status = task_status;
	}
	public String getTask_datestart() {
		return task_datestart;
	}
	public void setTask_datestart(String task_datestart) {
		this.task_datestart = task_datestart;
	}
	public String getTask_dateend() {
		return task_dateend;
	}
	public void setTask_dateend(String task_dateend) {
		this.task_dateend = task_dateend;
	}
	public int getTask_cm() {
		return task_cm;
	}
	public void setTask_cm(int task_cm) {
		this.task_cm = task_cm;
	}
	public int getTask_alert() {
		return task_alert;
	}
	public void setTask_alert(int task_alert) {
		this.task_alert = task_alert;
	}
	
	
	
	
	
	public String getTask_budget() {
		return task_budget;
	}
	public void setTask_budget(String task_budget) {
		this.task_budget = task_budget;
	}
	public String getTask_payment() {
		return task_payment;
	}
	public void setTask_payment(String task_payment) {
		this.task_payment = task_payment;
	}
	
	
	
	
	public String getTask_expenses() {
		return task_expenses;
	}
	public void setTask_expenses(String task_expenses) {
		this.task_expenses = task_expenses;
	}
	public String getTask_real_payment() {
		return task_real_payment;
	}
	public void setTask_real_payment(String task_real_payment) {
		this.task_real_payment = task_real_payment;
	}
	
	
	
	
	public int getBudget_source1() {
		return budget_source1;
	}
	public void setBudget_source1(int budget_source1) {
		this.budget_source1 = budget_source1;
	}
	public int getBudget_source2() {
		return budget_source2;
	}
	public void setBudget_source2(int budget_source2) {
		this.budget_source2 = budget_source2;
	}
	public int getBudget_source3() {
		return budget_source3;
	}
	public void setBudget_source3(int budget_source3) {
		this.budget_source3 = budget_source3;
	}
	public Double getTask_budget1() {
		return task_budget1;
	}
	public void setTask_budget1(Double task_budget1) {
		this.task_budget1 = task_budget1;
	}
	public Double getTask_budget2() {
		return task_budget2;
	}
	public void setTask_budget2(Double task_budget2) {
		this.task_budget2 = task_budget2;
	}
	public Double getTask_budget3() {
		return task_budget3;
	}
	public void setTask_budget3(Double task_budget3) {
		this.task_budget3 = task_budget3;
	}
	
	
	
	public int getKeyactivity_id() {
		return keyactivity_id;
	}
	public void setKeyactivity_id(int keyactivity_id) {
		this.keyactivity_id = keyactivity_id;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public Double getUse_budget() {
		return use_budget;
	}
	public void setUse_budget(Double use_budget) {
		this.use_budget = use_budget;
	}
	public Double getPlan_budget() {
		return plan_budget;
	}
	public void setPlan_budget(Double plan_budget) {
		this.plan_budget = plan_budget;
	}
	public long getCount_problems() {
		return count_problems;
	}
	public void setCount_problems(long count_problems) {
		this.count_problems = count_problems;
	}
	
	
	public String getActivity_task() {
		return activity_task;
	}
	public void setActivity_task(String activity_task) {
		this.activity_task = activity_task;
	}
	public String getEnd_task() {
		return end_task;
	}
	public void setEnd_task(String end_task) {
		this.end_task = end_task;
	}
	public int getTask_idcheck() {
		return task_idcheck;
	}
	public void setTask_idcheck(int task_idcheck) {
		this.task_idcheck = task_idcheck;
	}
	@Override
	public String toString() {
		return String.format(
				"task [task_id=%s, task_name=%s, task_note=%s, task_date=%s, task_iduser=%s, task_idrefer=%s, task_idadd=%s, task_status=%s]",
				task_id, task_name, task_note, task_date, task_iduser, task_idrefer, task_idadd, task_status);
	} 

	
	
	
	
	
}
