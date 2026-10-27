package com.smepro.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.smepro.dao.Identifiable;
import com.smepro.enums.PolicyType;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "policy")
public class Policy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	
	private String name;
	private String description;
	private int createBy;
	private int folder;

 
    
    @Enumerated(EnumType.STRING)
    private PolicyType type;  // 👈 เพิ่มตรงนี้
    
    //@OneToMany(fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PolicyMember> members = new ArrayList<>();


	    // Helper method
	    public void setALlMembers(List<PolicyMember> newMembers) {
	        this.members.clear();
	        if (newMembers != null) {
	            for (PolicyMember member : newMembers) {
	                member.setPolicy(this); // สำคัญมาก!
	                this.members.add(member);
	            }
	        }
	    }

	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getCreateBy() {
		return createBy;
	}


	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}


	public PolicyType getType() {
		return type;
	}


	public void setType(PolicyType type) {
		this.type = type;
	}


	public List<PolicyMember> getMembers() {
		return members;
	}


	public void setMembers(List<PolicyMember> members) {
		this.members = members;
	}


	public int getFolder() {
		return folder;
	}


	public void setFolder(int folder) {
		this.folder = folder;
	}


	

    
	
	
    
}