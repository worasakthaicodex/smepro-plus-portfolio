package com.smepro.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.smepro.dao.darDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.userDAO;
import com.smepro.models.UserCode;
import com.smepro.models.dar;
import com.smepro.models.document_no;
import com.smepro.models.file;
import com.smepro.models.groups;
import com.smepro.models.user;

@Service
public class EmployeeService {

	
	
	private groupsDAO GroupsDAO;
	private userDAO UserDAO;
	
	@Autowired
	public void setMyRepository(
				groupsDAO GroupsDAO
	    		,userDAO UserDAO
	    		){
	        this.GroupsDAO = GroupsDAO;
	        this.UserDAO = UserDAO;
	    }
	
	
	
		public String  CreateEmployeePassword( String password,int group_id) {
			
			groups	group  = GroupsDAO.getbyIds(group_id);
			UserCode Code = UserDAO.findUserCodeDepartment(group.getGroup_department_id());
			if(Code == null) { return password; }	
			return Code.getDefaultpassword();
		}
	
		public String  CreateEmployeeID( String username,int group_id) {
			
			groups	group  = GroupsDAO.getbyIds(group_id);
			UserCode Code = UserDAO.findUserCodeDepartment(group.getGroup_department_id());
			if(Code == null) { return null; }
			
			String	date_code  = DateCode(Code);
			String id_code = AutoID(Code);
			return Code.getUser_code_pattern()+date_code+id_code;
		}
		
		private String DateCode(UserCode code) {
			
			if(code.getUser_code_daterun() == 0) { return ""; }
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
			LocalDateTime today = LocalDateTime.now();
			String date = today.format(formatter);
			return date;
		}

		
		public String AutoID(UserCode code) {
		    int auto = code.getAutoid() + 1;
		    return String.format("%03d", auto); // อย่างน้อย 3 หลัก เติม 0 ด้านหน้า
		}
		
	
	
}

