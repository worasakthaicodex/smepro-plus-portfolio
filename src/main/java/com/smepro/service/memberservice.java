package com.smepro.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;

import com.smepro.models.models;

@Service
public class memberservice {
	
	private static int studentNumber = 4;
	private static List<models> members = new ArrayList<models>();

	static {
		members.add(new models(1,"วรศักดิ์","14-10-1988",true));
		members.add(new models(2,"Tao","14-10-1988",true));
		members.add(new models(3,"pun","14-10-1988",true));
		members.add(new models(4,"tip","14-10-1988",true));		
	}
	
	public List<models> readAllmember(){		
		List<models> member  = new ArrayList<models>();
		for(models row : members) {
				member.add(row);
		}	
		return member;	
	}
	
	
	public void addmember(String name, String date, boolean isActive) {
		members.add(new models(++studentNumber,name,date,isActive));
	
	}

	public void delmember(int id) {
		Iterator<models> iterator = members.iterator();
	
		while(iterator.hasNext()) {
			models members = iterator.next();
			if(members.getId() == id) {
				iterator.remove();
			}
		
		}
	}
	
	public models getmemberinfo(int id){
		
		for(models member : members ) {
			if(member.getId() == id) {
				return member;
			}
		}
		 return null;
	}
	
	public void updatemember(int id, String name, String date, boolean isActive) {
       
		Iterator<models> iterator = members.iterator();
		
		while(iterator.hasNext()) {
			models members = iterator.next();
			if(members.getId() == id) {
				members.setName(name);
				members.setDate(date);
			}
		
		}
		
	}	
	

	
	
	
}
	
	















