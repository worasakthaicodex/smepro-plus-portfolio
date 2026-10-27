package com.smepro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.entity.Policy;
import com.smepro.entity.PolicyMember;
import com.smepro.enums.PolicyType;
import com.smepro.models.user;
import com.smepro.repository.PolicyMemberRepository;


@Service
public class RepositoryService {

	
	
	  private  PolicyMemberRepository policyMemberRepository;
	
	  @Autowired
	 public void setMyRepository(
			 		PolicyMemberRepository policyMemberRepository
				   
				    		) {
			
				        this.policyMemberRepository = policyMemberRepository;

				    }
		  
		  

	
	
		@Transactional
		public  <T> T syncData(List<String> selectedIds,T data){
		
			
			
			if (data instanceof Policy policy) {
			System.out.println(" instanceof Policy");
				
				Policy updated  =  handlePolicy(policy,selectedIds);
				return (T) updated; 
		    }
			return null; 
		}
		
		private Policy handlePolicy(Policy policy, List<String> selectedEmployeeIds) {
		    System.out.println("handlePolicy");

		    // ดึง ID (เฉพาะเลข) จาก selectedEmployeeIds เช่น "123_data1_data2"
		    List<Integer> selectedIds = selectedEmployeeIds.stream()
		        .map(s -> s.split("_")[0])
		        .map(Integer::parseInt)
		        .collect(Collectors.toList());

		    // 1. ลบของเก่าที่มี ID ซ้ำกับของใหม่ (selected)
		    policy.getMembers().removeIf(m -> selectedIds.contains(m.getIdEmployee()));

		    // 2. เพิ่มใหม่ตาม selectedEmployeeIds
		    for (String entry : selectedEmployeeIds) {
		        String[] parts = entry.split("_");
		        if (parts.length >= 3) {
		            int id = Integer.parseInt(parts[0]);

			        String name = parts[1];
			        String postion = parts[2];
			        String department = parts[3];
			        
			        
			        PolicyMember m = new PolicyMember();
			        m.setIdEmployee(id);

			        m.setEmployeeName(name);
			        m.setEmployeePostion(postion);
			        m.setEmployeeDepartment(department);
			        m.setPolicy(policy);
	

		            policy.getMembers().add(m);
		        }
		    }

		    System.out.println("handlePolicy End");
		    return policy;
		}
		
		
		public  String  accessList(user user){
			List<PolicyMember> list =   policyMemberRepository.findAllByIdEmployee(user.getUser_id());
			String 	accessList = list.stream().map(row-> String.valueOf(row.getPolicy().getId()) ).collect(Collectors.joining(","));
		   return accessList;
		}
		
		
		
}
