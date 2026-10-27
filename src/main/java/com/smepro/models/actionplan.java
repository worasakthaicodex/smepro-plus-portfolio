package com.smepro.models;

import java.util.List;

public class actionplan  extends file{
	
	
	 private int actionplan_id;	
	 private int  actionplan_idrfer;
	 private String actionplan_datedue;
	 private String actionplan_dateadd;	
	 private String actionplan_responsible;	
	 private String actionplan_inspector;
	 private String actionplan_problem;
	 private String actionplan_cause;
	 private String actionplan_solutions;
	 private String actionplan_result;
	 private String actionplan_comment;
	 private int actionplan_status;
	 private int actionplan_user_id;
	 private int submission;
	 private long count_problems;
	 
	 private String actionplan_expenses;
	 private String actionplan_payment;

	 
	 
	 
	 private List<file> file;
	 
	 
	public int getActionplan_id() {
		return actionplan_id;
	}
	public void setActionplan_id(int actionplan_id) {
		this.actionplan_id = actionplan_id;
	}
	public int getActionplan_idrfer() {
		return actionplan_idrfer;
	}
	public void setActionplan_idrfer(int actionplan_idrfer) {
		this.actionplan_idrfer = actionplan_idrfer;
	}
	public String getActionplan_datedue() {
		return actionplan_datedue;
	}
	public void setActionplan_datedue(String actionplan_datedue) {
		this.actionplan_datedue = actionplan_datedue;
	}
	public String getActionplan_dateadd() {
		return actionplan_dateadd;
	}
	public void setActionplan_dateadd(String actionplan_dateadd) {
		this.actionplan_dateadd = actionplan_dateadd;
	}
	public String getActionplan_responsible() {
		return actionplan_responsible;
	}
	public void setActionplan_responsible(String actionplan_responsible) {
		this.actionplan_responsible = actionplan_responsible;
	}
	public String getActionplan_inspector() {
		return actionplan_inspector;
	}
	public void setActionplan_inspector(String actionplan_inspector) {
		this.actionplan_inspector = actionplan_inspector;
	}
	public String getActionplan_problem() {
		return actionplan_problem;
	}
	public void setActionplan_problem(String actionplan_problem) {
		this.actionplan_problem = actionplan_problem;
	}
	public String getActionplan_cause() {
		return actionplan_cause;
	}
	public void setActionplan_cause(String actionplan_cause) {
		this.actionplan_cause = actionplan_cause;
	}
	public String getActionplan_solutions() {
		return actionplan_solutions;
	}
	public void setActionplan_solutions(String actionplan_solutions) {
		this.actionplan_solutions = actionplan_solutions;
	}
	public String getActionplan_result() {
		return actionplan_result;
	}
	public void setActionplan_result(String actionplan_result) {
		this.actionplan_result = actionplan_result;
	}
	public String getActionplan_comment() {
		return actionplan_comment;
	}
	public void setActionplan_comment(String actionplan_comment) {
		this.actionplan_comment = actionplan_comment;
	}
	public int getActionplan_status() {
		return actionplan_status;
	}
	public void setActionplan_status(int actionplan_status) {
		this.actionplan_status = actionplan_status;
	}
	public String getActionplan_expenses() {
		return actionplan_expenses;
	}
	public void setActionplan_expenses(String actionplan_expenses) {
		this.actionplan_expenses = actionplan_expenses;
	}
	public String getActionplan_payment() {
		return actionplan_payment;
	}
	public void setActionplan_payment(String actionplan_payment) {
		this.actionplan_payment = actionplan_payment;
	}
	public List<file> getFile() {
		return file;
	}
	public void setFile(List<file> file) {
		this.file = file;
	}
	public int getActionplan_user_id() {
		return actionplan_user_id;
	}
	public void setActionplan_user_id(int actionplan_user_id) {
		this.actionplan_user_id = actionplan_user_id;
	}
	public int getSubmission() {
		return submission;
	}
	public void setSubmission(int submission) {
		this.submission = submission;
	}
	public long getCount_problems() {
		return count_problems;
	}
	public void setCount_problems(long count_problems) {
		this.count_problems = count_problems;
	}

	 
	
}
