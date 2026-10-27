package com.smepro.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smepro.dao.Identifiable;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
@Table(name = "workrule_leave_log")
public class workrule_leave_log  implements Identifiable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "employee_code")
	private String employeeCode;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "workrule_id")
	private String workruleId;
	
	@Column(name = "workrule_name")
	private String workruleName;


	private String log_date;
	private String leave_type;
	private String previous_entitlement;
	private String added_days;
	private String new_entitlement;
	private String rule_reason;
	private String note;
	
	@Column(name = "edata_id")
	private int edataId;
	
	private String tempId; 
	private String  doc;
	
	


	@Transient
	private String  check;
	@Transient
	private String  id_refer;
	@Transient
	private int count;
	
	@Column(name = "created_at", updatable = false, insertable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false)
	private Timestamp updatedAt;
	
	@Transient
	private List<Object> join1;
	@Transient
	private List<Object> join2;


  @Column(name = "processId", columnDefinition = "int default 0")
  private int processId;


	@Override
	public void setJoin1(List<Object> join1) {
		this.join1 = join1;
	}

	public List<Object> getJoin1() {
		return join1;
	}
	@Override
	public void setJoin2(List<Object> join2) {
		this.join2 = join2;
	}

	public List<Object> getJoin2() {
		return join2;
	}
	


  @Override
    public int getProcessId() {
        return this.processId;
 }

    public void setProcessId(int processId) {
        this.processId = processId;
 }
	
	  @Override
	    public int getId() {
	        return this.id;
	 
	  }
	
	
}
