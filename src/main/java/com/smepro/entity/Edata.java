package com.smepro.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "edata")
public class Edata {

	
	@Id
	@Column(name = "data_id")
	private Integer edataId;
	
	@Column(name = "process_id")
	private int processId;
	 
		  
	
	
}

