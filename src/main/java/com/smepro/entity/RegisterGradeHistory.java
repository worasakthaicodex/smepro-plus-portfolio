package com.smepro.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "register_grade_history")
public class RegisterGradeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "register_id")
    private int registerId; // หรือ String ตาม Type ID ของคุณ

    @Column(name = "old_grade", length = 10)
    private String oldGrade;

    @Column(name = "new_grade", length = 10)
    private String newGrade;

    @Column(name = "process_id")
    private   int processId;

    @Column(name = "calculate_date")
    private LocalDateTime calculateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRegisterId() {
		return registerId;
	}

	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}

	public String getOldGrade() {
		return oldGrade;
	}

	public void setOldGrade(String oldGrade) {
		this.oldGrade = oldGrade;
	}

	public String getNewGrade() {
		return newGrade;
	}

	public void setNewGrade(String newGrade) {
		this.newGrade = newGrade;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public LocalDateTime getCalculateDate() {
		return calculateDate;
	}

	public void setCalculateDate(LocalDateTime calculateDate) {
		this.calculateDate = calculateDate;
	}

    
    
    
    
    
}