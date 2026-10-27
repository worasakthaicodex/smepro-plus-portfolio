package com.smepro.service;

import org.springframework.stereotype.Service;

import com.smepro.entity.Policy;
import com.smepro.models.upload_pn;
import com.smepro.models.user;


@Service
public class PolicyService {

	
		public String getcheckAccessByPolicy(upload_pn upload_pns, user usersed, Policy policy) {
			if(policy == null ) { return "guest";}
			if(policy.getMembers().size() == 0 ) { return "guest";}
			if(upload_pns == null ) { return "guest";}
			if(usersed == null ) { return "guest";}
			
			
			String Access = "guest";
			
				if(upload_pns.getUpload_pn_app() != null) {
					if(upload_pns.getUpload_pn_app().equals(usersed.getUser_name()) ) { Access = "Inspector";}
				}
				
			
			if(policy.getMembers().stream().map(row->  row.getEmployeeName().equals(usersed.getUser_name()) ).count() > 0) {
				  if(Access.equals("Inspector")) {  return "admin"; }
				  return "uploader"; 
			}
			
			return "guest";
			// TODO Auto-generated method stub
		}
	
		

		public String getcheckAccess(upload_pn upload_pns, user usersed) {
			
			if(upload_pns == null ) { return "guest";}
			if(usersed == null ) { return "guest";}
			String Access = "guest";
			
			if(upload_pns.getUpload_pn_app() != null) {
				if(upload_pns.getUpload_pn_app().equals(usersed.getUser_name()) ) { Access = "Inspector";}
			}
			if(upload_pns.getUpload_pn_respon().equals(usersed.getUser_name())) {
				  if(Access.equals("Inspector")) {  return "admin"; }
				  return "uploader"; 
			}
		
			return "guest";
			
		}

		
}
