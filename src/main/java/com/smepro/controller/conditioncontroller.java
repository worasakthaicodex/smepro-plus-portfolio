package com.smepro.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.smepro.dao.centralDAO;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.dataeformDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.formDAO;
import com.smepro.dao.mainfieldDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.reportDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.enums.DB;
import com.smepro.enums.eformcolumntype;
import com.smepro.enums.eformsubcolumntype;
import com.smepro.models.Group_label;
import com.smepro.models.Myapp;
import com.smepro.models.central;
import com.smepro.models.condition;
import com.smepro.models.dataeform;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.edata;
import com.smepro.models.eformfield;
import com.smepro.models.file;
import com.smepro.models.form;
import com.smepro.models.keymap;
import com.smepro.models.mainfield;
import com.smepro.models.map_key;
import com.smepro.models.map_key2;
import com.smepro.models.map_key3;
import com.smepro.models.notification;
import com.smepro.models.process;
import com.smepro.models.register_log;
import com.smepro.models.registers;
import com.smepro.models.report;
import com.smepro.models.report_mapping;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.service.MainService;
import com.smepro.service.filemanagerservice;
import com.smepro.service.userservice;







@Controller
@SessionAttributes({"Member_name","name","Member_id"})
public class conditioncontroller {
	
	   private conditionDAO ConditionDAO;
	   private datasetDAO DatasetDAO;
	   private userDAO UserDAO;
	   private userservice Userservice;
	   private dataeformDAO DataeformDAO;
	   private centralDAO CentralDAO;
	   private mainfieldDAO MainfieldDAO;
	   private eformfieldDAO EformfieldDAO; 
	   private  dataset_dataDAO Dataset_dataDAO;
	   private notificationDAO NotificationDAO;
	   private edataDAO EdataDAO;
	   private upload_pnDAO Upload_pnDAO;
	   private formDAO FormDAO;
	   private reportDAO ReportDAO;
	   private fileDAO FileDAO;
	   private  MainService MainServices;
	   private  filemanagerservice Filemanagerservices;

	   
	    @Autowired
	    public void setMyRepository(
	    		conditionDAO ConditionDAO
	    		,datasetDAO DatasetDAO
	    		,userDAO UserDAO,userservice Userservice
	    		,dataeformDAO DataeformDAO
	    		,centralDAO CentralDAO
	    		,mainfieldDAO MainfieldDAO
	    		,eformfieldDAO EformfieldDAO
	    		,dataset_dataDAO Dataset_dataDAO
	    		,notificationDAO NotificationDAO
	    		,edataDAO EdataDAO
	    		,upload_pnDAO Upload_pnDAO
	    		,formDAO FormDAO
	    		,reportDAO ReportDAO
	    		,fileDAO FileDAO
	    		,MainService MainServices
	    		,filemanagerservice Filemanagerservices
	    		) {
	    	
	        this.ConditionDAO = ConditionDAO;
	        this.FormDAO = FormDAO;
	        this.DatasetDAO = DatasetDAO;
	        this.UserDAO = UserDAO;
	        this.Userservice = Userservice;
	        this.DataeformDAO = DataeformDAO;
	        this.CentralDAO = CentralDAO;
	        this.MainfieldDAO = MainfieldDAO;
	        this.EformfieldDAO = EformfieldDAO;
	        this.Dataset_dataDAO = Dataset_dataDAO;
	        this.NotificationDAO = NotificationDAO;
	        this.EdataDAO = EdataDAO;
	        this.Upload_pnDAO = Upload_pnDAO;
	        this.ReportDAO = ReportDAO;
	        this.FileDAO = FileDAO;
	        this.MainServices =MainServices; 
	        this.Filemanagerservices =Filemanagerservices; 
	    }
	    

	    
	

	    
	    
	
	@RequestMapping(value="/editcondition-condition/{id}",method = RequestMethod.GET)
	public String editcondition(ModelMap model, @PathVariable int id,@RequestParam(value = "save",required=false) String save) throws IOException {
		MainServices.addNotificationAttributes(model);
		
		
		condition conditions = ConditionDAO.getbyid(id);
		List<dataset> datasets  = DatasetDAO.getall();
		  model.addAttribute("condition",conditions); 
		
		  model.addAttribute("datasets",datasets); 
		List<user> users  =  UserDAO.getall();
        model.addAttribute("users",users); 

   
         
        List<eformfield> eformfields = EformfieldDAO.getall(id, 0);
  	  model.addAttribute("eformfields",eformfields); 
   
		
		return  "e-form";
	}
	
	
	
	@RequestMapping(value="/render-condition/{id}",method = RequestMethod.GET)
	public String rendercondition(ModelMap model, @PathVariable int id,@RequestParam(value = "title",required=false) String title) throws IOException {
		MainServices.addNotificationAttributes(model);
		condition conditions = ConditionDAO.getbyid(id);
		List<dataset> datasets  = DatasetDAO.getall();
		
		List<user> users  =  UserDAO.getall();
        model.addAttribute("users",users); 

   
         
        List<eformfield> eformfields = EformfieldDAO.getall(id, 0);
        
        
        List<mainfield> textfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","text");  
        model.addAttribute("textfield",textfield); 
        List<mainfield> numberfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","number");  
        model.addAttribute("numberfield",numberfield);
        List<mainfield> datefield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","date");  
        model.addAttribute("datefield",datefield);
        List<mainfield> selectfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","select");  
        model.addAttribute("selectfield",selectfield); 
        List<mainfield> checkboxfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","checkbox");  
        model.addAttribute("checkboxfield",checkboxfield); 
        
        model.addAttribute("title",title); 
		model.addAttribute("condition",conditions); 
		model.addAttribute("datasets",datasets); 
		model.addAttribute("eformfields",eformfields); 
		
		
		
	   condition conditionssub = ConditionDAO.getbyid(conditions.getCondition_sub());
	  List<eformfield> eformfields2 = EformfieldDAO.getall(conditions.getCondition_sub(), 0);
      model.addAttribute("eformfields2",eformfields2);
      
	  List<eformfield> eformfields3 = EformfieldDAO.getall(conditions.getCondition_sub2(), 0);
      model.addAttribute("eformfields3",eformfields3);
      
      
	  List<eformfield> eformfields4 = EformfieldDAO.getall(conditions.getCondition_sub3(), 0);
      model.addAttribute("eformfields4",eformfields4);
      
      
      
      condition conditionssub2 = ConditionDAO.getbyid(conditions.getCondition_sub2());
      condition conditionssub3 = ConditionDAO.getbyid(conditions.getCondition_sub3());
		model.addAttribute("conditionssub",conditionssub); 
		model.addAttribute("conditionssub2",conditionssub2); 
		model.addAttribute("conditionssub3",conditionssub3); 

		
		
		if(conditionssub != null) { 
			map_key map_key1 = ConditionDAO.getMap_key(conditions.getCondition_id(),conditionssub.getCondition_id());
			model.addAttribute("map_key1",map_key1); 
		}
		if(conditionssub2 != null) { 
			map_key map_key2 = ConditionDAO.getMap_key(conditions.getCondition_id(),conditionssub2.getCondition_id()); 
			model.addAttribute("map_key2",map_key2); 
		}
		if(conditionssub3 != null) { 
			map_key map_key3 = ConditionDAO.getMap_key(conditions.getCondition_id(),conditionssub3.getCondition_id()); 
			model.addAttribute("map_key3",map_key3); 
		}
		
		
		return  "e-form2";
	}
	
	
	
	
	
	@RequestMapping(value="/render-condition/{id}/{id2}",method = RequestMethod.GET)
	public String rendercondition2(ModelMap model, @PathVariable int id, @PathVariable int id2) throws IOException {

		model.addAttribute("id",id); 
		model.addAttribute("id2",id2); 
		return  "e-form3";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/listcondition-condition",method = RequestMethod.GET)
	public String listcondition(ModelMap model) throws IOException {
		MainServices.addNotificationAttributes(model);
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
        model.addAttribute("name", Userservice.getLoggedName());
        model.addAttribute("Member_name", users.getUser_name());
        List<notification> notifications = NotificationDAO.getallrefer(users.getUser_name());
        int alert = NotificationDAO.countnotification(users.getUser_name(), 0);
        model.addAttribute("notification", notifications);
        model.addAttribute("alert", alert);
		
		List<condition> conditions = ConditionDAO.getalls(users.getUser_id());
		model.addAttribute("conditions",conditions); 
		
	
		
		List<central> centrals = CentralDAO.getall(0);
		model.addAttribute("centrals",centrals); 
		List<central> centrals2 = CentralDAO.getall(1);
		model.addAttribute("centrals2",centrals2); 
		
		List<central> centrals3 = CentralDAO.getall(2);
		model.addAttribute("centrals3",centrals3); 
		
		
		return  "liste-form";
	}
	
	
	@RequestMapping(value="/listcondition2-condition",method = RequestMethod.GET)
	public String listcondition2(ModelMap model) throws IOException {
		MainServices.addNotificationAttributes(model);
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
        model.addAttribute("name", Userservice.getLoggedName());
        model.addAttribute("Member_name", users.getUser_name());
        List<notification> notifications = NotificationDAO.getallrefer(users.getUser_name());
        int alert = NotificationDAO.countnotification(users.getUser_name(), 0);
        model.addAttribute("notification", notifications);
        model.addAttribute("alert", alert);
		
		
		List<condition> conditions = ConditionDAO.getall();
		model.addAttribute("conditions",conditions); 
		
		List<central> centrals = CentralDAO.getall(0);
		model.addAttribute("centrals",centrals); 
		List<central> centrals2 = CentralDAO.getall(1);
		model.addAttribute("centrals2",centrals2); 
		
		List<central> centrals3 = CentralDAO.getall(2);
		model.addAttribute("centrals3",centrals3); 
		
		
		return  "liste-form2";
		
		
		
	}
	@RequestMapping(value="/copycondition-condition/{id}",method = RequestMethod.GET)
	public String copycondition(ModelMap model,@PathVariable int id) throws IOException {
		
		
		MainServices.addNotificationAttributes(model);
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());
		ConditionDAO.copy(id,usersed.getUser_id());	
		
		condition condition = ConditionDAO.getbyid(usersed.getUser_id(),-5);
		EformfieldDAO.copy(id,condition.getCondition_id());	
		ConditionDAO.setnewid(condition.getCondition_id());
		
		
		return "redirect:/listcondition-condition";
		
	}
	
	
	
	@RequestMapping(value="/echart-condition/{id}",method = RequestMethod.GET)
	public String echart(ModelMap model,@PathVariable int id) throws IOException {

		MainServices.addNotificationAttributes(model);
		condition conditions = ConditionDAO.getbyid(id);
		model.addAttribute("conditions",conditions);
		
	    LocalDateTime localDate = LocalDateTime.now();//For reference
	    LocalDateTime localDate2 = LocalDateTime.now().minusDays(7);//For reference
	   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	String datestart = localDate2.format(formatter);
	   	String dateend = localDate.format(formatter);

	   	
		dataeform eformdata = DataeformDAO.getbydate(datestart,dateend,id);
		model.addAttribute("eformdata",eformdata);
		model.addAttribute("datestart",datestart);
		model.addAttribute("dateend",dateend);
		
		
		model.addAttribute("chartdiv1label",conditions.getChartdiv1label());
		model.addAttribute("chartdiv1data",conditions.getChartdiv1data());
	
		model.addAttribute("chartdiv2label",conditions.getChartdiv2label());
		model.addAttribute("chartdiv2data",conditions.getChartdiv2data());
		
		model.addAttribute("chartdiv3label",conditions.getChartdiv3label());
		model.addAttribute("shows",conditions.getShows());
		model.addAttribute("tilte","7 วันล่าสุด");
		
		
		
        
        
        
        
		return  "report";
	}
	 public static String mergeThaiCharacters(String text) {
	        // ใช้ regex เพื่อค้นหาอักษรไทยที่แยกตัวกันและรวมกัน
	        Pattern pattern = Pattern.compile("([ก-๙]+)\\s*");
	        Matcher matcher = pattern.matcher(text);

	        StringBuilder result = new StringBuilder();
	        while (matcher.find()) {
	            result.append(matcher.group(1));
	        }

	        return result.toString();
	    }


	    
	    
	@RequestMapping(value="/searchreport-dormitory",method = RequestMethod.POST)
	public String searchreport(
			ModelMap model
			,@RequestParam(value = "datestart",required=false) String datestart
			,@RequestParam(value = "dateend",required=false) String dateend
			,@RequestParam(value = "condition_id",required=false) int condition_id) throws IOException {

		
		condition conditions = ConditionDAO.getbyid(condition_id);
		model.addAttribute("conditions",conditions);


	   	
		dataeform eformdata = DataeformDAO.getbydate(datestart,dateend,condition_id);
		model.addAttribute("eformdata",eformdata);
		model.addAttribute("datestart",datestart);
		model.addAttribute("dateend",dateend);
		
		
		model.addAttribute("chartdiv1label",conditions.getChartdiv1label());
		model.addAttribute("chartdiv1data",conditions.getChartdiv1data());
	
		model.addAttribute("chartdiv2label",conditions.getChartdiv2label());
		model.addAttribute("chartdiv2data",conditions.getChartdiv2data());
		
		model.addAttribute("chartdiv3label",conditions.getChartdiv3label());
		model.addAttribute("shows",conditions.getShows());
		
		model.addAttribute("tilte",datestart+" - "+dateend);
		return  "report";
	}
	
	
	@RequestMapping(value="/setreport-dormitory",method = RequestMethod.POST)
	public String setreport(
			ModelMap model
			,@RequestParam String chartdiv1label
			,@RequestParam String chartdiv1data
			,@RequestParam String chartdiv2label
			,@RequestParam String chartdiv2data
			,@RequestParam String chartdiv3label
			,@RequestParam String chartdiv3data
			,@RequestParam(value = "shows",required=false)  String shows
			,@RequestParam int condition_id
			
			)throws IOException {

		condition conditions = new condition();
		conditions.setChartdiv1data(chartdiv1data);
		conditions.setChartdiv2data(chartdiv2data);
		conditions.setChartdiv3data(chartdiv3data);
		conditions.setChartdiv1label(chartdiv1label);
		conditions.setChartdiv2label(chartdiv2label);
		conditions.setChartdiv3label(chartdiv3label);
		conditions.setCondition_id(condition_id);
		
		if(shows != null ) {
			conditions.setShows(shows);
		}
		
		
		
		
		
		ConditionDAO.setChart(conditions);
		 return "redirect:/echart-condition/"+condition_id;
	}
	
	
	@RequestMapping(value="/setplan-condition",method = RequestMethod.POST)
	public String setplan(
			ModelMap model
			,@RequestParam condition conditions	
			){
		
		//ConditionDAO.setplan(conditions);
		 return "redirect:/duepn-filemanager?id="+conditions.getCondition_id()+"&&step=1&&group=1";
		 
	}
	
	@RequestMapping(value="/mainreportplan-condition/{id}/{report}",method = RequestMethod.GET)
	public String mainreportplan(ModelMap model, @PathVariable int id, @PathVariable int report){

		
		 condition conditions = ConditionDAO.getbyid(id);
		 model.addAttribute("id",id);  
		 model.addAttribute("conditions",conditions); 
		 if(conditions != null) {
			 List<edata> edatas = EdataDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv());
			 model.addAttribute("edatas",edatas);  
		 }else {
			 
			 System.out.println("condition_null");
			 model.addAttribute("error","condition_null");  
		 }
		 
		 upload_pn upload_pns = Upload_pnDAO.getbyId(report);
		 model.addAttribute("upload_pn",upload_pns);  
		 System.out.println("upload_pns " + upload_pns.getUpload_pn_app());
		 
		 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());
		 
		 model.addAttribute("access",usersed.getUser_name());  
		  List<report> reportall = ReportDAO.getalls(report);
	      model.addAttribute("reportall",reportall); 
	        
		 
		
		 return "/eformparts/report3";
		 
	}
	
	
	
	
	@RequestMapping(value="/mainreportplans-condition/{id}/{report}",method = RequestMethod.GET)
	public String mainreportplans(ModelMap model, @PathVariable int id, @PathVariable int report){
		MainServices.addNotificationAttributes(model);
		report reports = ReportDAO.getbyid(report);
		List<eformfield> eformfields  = null;
		
		
	    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());
		 condition conditions = ConditionDAO.getbyid(id);
		 
		 upload_pn upload_pns = Upload_pnDAO.getbymonitor(id);
		 
		 model.addAttribute("id",id);  
		 model.addAttribute("conditions",conditions); 
		 model.addAttribute("reports",reports); 
		  report_mapping mappings  = ConditionDAO.getMap(conditions.getCondition_id(),usersed.getUser_id());
		  
		  if(mappings != null) { eformfields = EformfieldDAO.getall(mappings.getMapping_id_refer(), 0); }
		 

		 
		 if(reports != null) {
			 
			 condition subconditions = ConditionDAO.getbysub(conditions.getCondition_id());
			 if(subconditions != null) {
				 List<edata> edatas = EdataDAO.getallreport(reports.getReport_data());
				 model.addAttribute("edatas",edatas);  
				 
			 }else {
				
				 upload_pn upload_pns2 = null;
				 List<edata> edatas =null;  
				    condition database1 = ConditionDAO.getbyport(conditions.getCondition_id(),1);
				    condition  database2 = null;
				  
				    System.out.println("step : 1");
				    
				    int monitor = 0;
					    
					    if(database1 == null) {
					    	database2 = ConditionDAO.getbyport(conditions.getCondition_id(),2);
		
					    }
					    
					    if(database1 != null || database2 != null) {
					    	if(database1 != null) {
					    		 monitor =database1.getCalculation_port2();
					    	}
					    	if(database2 != null) {
					    		monitor= database2.getCalculation_port1();
					    	}
					    }
				    
				    
				    if(monitor > 0) {
				    	
				    	  System.out.println("step : 2");
				    	  System.out.println("monitor : "+monitor);
				    	  
				    	  
				    	 upload_pns2 = Upload_pnDAO.getbymonitor(monitor);
				    	 condition conditions2 = ConditionDAO.getbyid(monitor);
				    	   model.addAttribute("monitor2",monitor);  
				    }
				    
				    
				 
				  
				    
				    if(upload_pns2 != null && mappings != null && eformfields != null ) {
				    	

				    		List<eformfield> eformfields2 = EformfieldDAO.getall(mappings.getMapping_id_map(), 0);
				    		
				    	     edatas = EdataDAO.getallreportmap(reports.getReport_data(),upload_pns.getUpload_pn_id(),upload_pns2.getUpload_pn_id(),mappings,eformfields.size());
							 model.addAttribute("edatas",edatas);  
							 
							 
							 int size = eformfields.size();
							 
							 
				        	 if(mappings.getMapping_key1() != null) { 
			
				        		if(  Integer.valueOf(mappings.getMapping_key1()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields2.get(0).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 if(mappings.getMapping_key2() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key2()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(1).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
				        	 if(mappings.getMapping_key3() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key3()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(2).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
				        	 if(mappings.getMapping_key4() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key4()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(3).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
				        	 if(mappings.getMapping_key5() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key5()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(4).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
				        	 if(mappings.getMapping_key6() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key6()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(5).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
				        	 if(mappings.getMapping_key7() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key7()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(6).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
				        	 if(mappings.getMapping_key8() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key8()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(7).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
				        	 if(mappings.getMapping_key9() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key9()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(8).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
				        	 if(mappings.getMapping_key10() != null) { 
				        			
					        		if(  Integer.valueOf(mappings.getMapping_key10()) >  size ) {
					        			 eformfield eformfieldsed = new eformfield();
					        			 eformfieldsed.setField_label(eformfields2.get(9).getField_label());
					        			 eformfields.add(eformfieldsed);
					        		}
					        	 }
					        	 
							 
				        	 
				        	 
				        	 
							 System.out.println("mappings ....." + edatas);


				    }else {
				    	  edatas = EdataDAO.getallreport(reports.getReport_data());
						 model.addAttribute("edatas",edatas); 
				    }
				    
				    
				     String idall = "-";
					 for(edata  row: edatas) {
						 
						if(row.getData_filetype() != null) {
							 idall+=idall+","+row.getData_filepath();
						} 

					 }
					 
					 System.out.println("idall" +idall);
					 
					 List<file>  files = FileDAO.getallLog(idall);
					 model.addAttribute("files",files);    
				 
			 }
			 
 
			 
		 }else {
			 
			 System.out.println("condition_null");
			 model.addAttribute("error","condition_null");  
		 }
		 
		 
		 
		 
		 //new run
		 
	
		 
		 
		 
		 if(eformfields != null) {
				 if( eformfields.size() > 0 ) { model.addAttribute("fields1",eformfields.get(0).getField_label());  }
				 if( eformfields.size() > 1 ) { model.addAttribute("fields2",eformfields.get(1).getField_label());  }
				 if( eformfields.size() > 2 ) { model.addAttribute("fields3",eformfields.get(2).getField_label());  }
				 if( eformfields.size() > 3 ) { model.addAttribute("fields4",eformfields.get(3).getField_label());  }
				 if( eformfields.size() > 4 ) { model.addAttribute("fields5",eformfields.get(4).getField_label());  }
				 if( eformfields.size() > 5 ) { model.addAttribute("fields6",eformfields.get(5).getField_label());  }
				 if( eformfields.size() > 6 ) { model.addAttribute("fields7",eformfields.get(6).getField_label());  }
				 if( eformfields.size() > 7 ) { model.addAttribute("fields8",eformfields.get(7).getField_label());  }
				 if( eformfields.size() > 8 ) { model.addAttribute("fields9",eformfields.get(8).getField_label());  }
				 if( eformfields.size() > 9 ) { model.addAttribute("fields10",eformfields.get(9).getField_label());  }
				 if( eformfields.size() > 10 ) { model.addAttribute("fields11",eformfields.get(10).getField_label());  }
				 if( eformfields.size() > 11 ) { model.addAttribute("fields12",eformfields.get(11).getField_label());  }
				 if( eformfields.size() > 12 ) { model.addAttribute("fields13",eformfields.get(12).getField_label());  }
				 if( eformfields.size() > 13 ) { model.addAttribute("fields14",eformfields.get(13).getField_label());  }
				 if( eformfields.size() > 14 ) { model.addAttribute("fields15",eformfields.get(14).getField_label());  }
				 if( eformfields.size() > 15 ) { model.addAttribute("fields16",eformfields.get(15).getField_label());  }
				 if( eformfields.size() > 16 ) { model.addAttribute("fields17",eformfields.get(16).getField_label());  }
				 if( eformfields.size() > 17 ) { model.addAttribute("fields18",eformfields.get(17).getField_label());  }
				 if( eformfields.size() > 18 ) { model.addAttribute("fields19",eformfields.get(18).getField_label());  }
				 if( eformfields.size() > 19 ) { model.addAttribute("fields20",eformfields.get(19).getField_label());  }
		 }
		 
		 model.addAttribute("access",usersed.getUser_name());  
		 

		 return "/eformparts/report2";
		 
	}
	
	
	
	
	
	@RequestMapping(value="/setstatus-condition/{id}/{report}",method = RequestMethod.GET)
	public String setstatus(ModelMap model, @PathVariable int id, @PathVariable int report){
		MainServices.addNotificationAttributes(model);
		ReportDAO.setStatus(report);
		return "redirect:/mainreportplan-condition/"+id+"/"+report;
		 
	}
	
	
	
	
	@RequestMapping(value="/delmainreportplans-condition/{id}/{report}/{reportid}",method = RequestMethod.GET)
	public String delmainreportplans(ModelMap model, @PathVariable int id, @PathVariable int report, @PathVariable int reportid){
		MainServices.addNotificationAttributes(model);
		 ReportDAO.delete(reportid);
		 return "redirect:/mainreportplan-condition/"+id+"/"+report;
		 
	}
	
	
	
	
	@RequestMapping(value="/settingreportplan-condition",method = RequestMethod.POST)
	public String settingreportplan(report reports,ModelMap model){
		
       System.out.println("reports" + reports);
		ReportDAO.update(reports);
		model.addAttribute("save","success");  
		 
		 
		 
		 return "/eformparts/report";
		 
	}
	
	
	@RequestMapping(value="/unsettingreportplan-condition",method = RequestMethod.POST)
	public String unsettingreportplan(condition conditions,ModelMap model){
		MainServices.addNotificationAttributes(model);
		 conditions.setCondition_report(null);
		 ConditionDAO.setReport(conditions);
		 model.addAttribute("save","success");  
		 return "/eformparts/report";
		 
	}
	
	
	
	@RequestMapping(value="/deletecondition-condition/{id}",method = RequestMethod.GET)
	public String deletecondition(ModelMap model, @PathVariable int id) throws IOException {
		MainServices.addNotificationAttributes(model);
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());
		  ConditionDAO.delete(id,usersed.getUser_id());
		 return "redirect:/listcondition-condition";	
	}
	
	
	
	
	
	@RequestMapping(value="/editcondition-condition",method = RequestMethod.POST)
	public String updatecondition(
			ModelMap model,
			@RequestParam String field_type,
			@RequestParam String field_label,
			@RequestParam String field_mainfield,
			@RequestParam String field_subfield,
			@RequestParam(value = "field_datarefertype",required=false) String field_datarefertype,
			@RequestParam(value = "field_datareferid",required=false) String field_datareferid,
			@RequestParam(value = "field_dataport",required=false) String field_dataport,
			@RequestParam(value = "field_datarefercols",required=false) String field_datarefercols,
			@RequestParam int field_formid,
			@RequestParam String condition_name,
			@RequestParam int field_formrv
			) throws IOException {
		

		


		

		String[] field_types = field_type.split(",");
		String[] field_labels = field_label.split(",");
		String[] field_datarefertypes = field_datarefertype.split(",");
		String[] field_datareferids = field_datareferid.split(",");
		
	

		
		
		
		List<eformfield> eformfields = new ArrayList<eformfield>();
		
		for (int i = 0; i < field_types.length; i++) {
			
			//System.out.println("length " +field_subfields.length);
			
			eformfield eformfield2 = new eformfield();
			
			eformfield2.setField_type(field_types[i]);
			eformfield2.setField_label(field_labels[i].trim());
			eformfield2.setField_formid(field_formid);
			eformfield2.setField_formrv(field_formrv);
			eformfield2.setField_datarefertype(field_datarefertypes[i]);
			eformfield2.setField_datareferid( field_datareferids[i].equals("-") ? 0 :  Integer.valueOf(field_datareferids[i]));
			eformfield2.setField_postion(i+1);
			eformfields.add(eformfield2);
			
			
	
			 
			 

		}
		
		

		 
		   EformfieldDAO.deleteall(field_formid,field_formrv);
		   EformfieldDAO.savebatch(eformfields, 100);
		   
		   
	      ConditionDAO.updatename(field_formid,condition_name);
			
	      

	        
	        
	  
	        
	        
	   
	        //Set new join type end


		 return "redirect:/editcondition-condition/"+field_formid+"?save=success";
		 
	}

	
	@RequestMapping(value="/listgroup_label-condition",method = RequestMethod.GET)
	public String listgroup_label(ModelMap model) throws IOException {
		  
		List<Group_label> listgroup_label =  ConditionDAO.getGroup_label();
		model.addAttribute("listgroup_label", listgroup_label);
		return  "group_label";
	}    
	
	@RequestMapping(value="/addgroup_label-condition",method = RequestMethod.GET)
	public String addgroup_label() throws IOException {
		return  "from_group_label";
	}    
	@RequestMapping(value="/applygroup_label-condition",method = RequestMethod.GET)
	public String applygroup_label(
			@RequestParam int field_id,
			@RequestParam int value
			) throws IOException {
		
			EformfieldDAO.applyGroupLabel(field_id,value);
			return  "ajax/save";
	}    
	@RequestMapping(value="/editgroup_label-condition/{id}",method = RequestMethod.GET)
	public String editgroup_label(ModelMap model,@PathVariable int id) throws IOException {
		Group_label group_label =  ConditionDAO.getGroup_labelbyId(id);
		model.addAttribute("group_label", group_label);
		return  "from_group_label";
	}    
	
	@RequestMapping(value="/savegroup_label-condition",method = RequestMethod.POST)
	public String savegroup_label(Group_label group_labels) throws IOException {
		  ConditionDAO.saveGrouplabels(group_labels);
		 return "redirect:/listgroup_label-condition";
	}    
	
	
	@RequestMapping(value="/delgroup_label-condition/{id}",method = RequestMethod.GET)
	public String delgroup_label(@PathVariable int id) throws IOException {
		  ConditionDAO.delGrouplabel(id);
		return "redirect:/listgroup_label-condition";
	}    
	
	
	
	
	
	@RequestMapping(value="/addcondition-condition",method = RequestMethod.GET)
	public String addconditions(ModelMap model) throws IOException {
		return  "e-form";
	}    
	
	

	
	@RequestMapping(value="/addconditions-condition",method = RequestMethod.POST)
	public String addconditiontitle(ModelMap mode,condition conditions) throws IOException {
		

	 	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 


	 	conditions.setCondition_idadd(usersed.getUser_id());
	 	conditions.setCondition_nameadd(usersed.getUser_name());
	 
		  ConditionDAO.save(conditions);
		 return "redirect:/listcondition-condition";
	}    
	
	
	
	@RequestMapping(value="/addcondition-condition",method = RequestMethod.POST)
	public String addcondition(
			ModelMap model,
			@RequestParam String field_type,
			@RequestParam String field_label,
			@RequestParam String field_mainfield,
			@RequestParam(value = "field_datarefertype",required=false) String field_datarefertype,
			@RequestParam(value = "field_datareferid",required=false) String field_datareferid,
			@RequestParam(value = "field_datarefercols",required=false) String field_datarefercols,
			@RequestParam int field_formid,
			@RequestParam int field_formrv
			) throws IOException {
		
		
		
		System.out.println("field_type = " + field_type);
		System.out.println("field_label = " + field_label);
		System.out.println("field_mainfield = " + field_mainfield);
		System.out.println("field_datarefertype = " + field_datarefertype);	
		System.out.println("field_datareferid = " + field_datareferid);
		System.out.println("field_datarefercols = " + field_datarefercols);
		
		
		String[] field_types = field_type.split(",");
		String[] field_labels = field_label.split(",");
		String[] field_mainfields = field_mainfield.split(",");
		String[] field_datarefertypes = field_datarefertype.split(",");
		String[] field_datareferids = field_datareferid.split(",");
		String[] field_datarefercolss = field_datarefercols.split(",");
		
		System.out.println(field_datareferids.length);
		List<eformfield> eformfields = new ArrayList<eformfield>();
		
		for (int i = 0; i < field_types.length; i++) {
			
			System.out.println(i);
			
			eformfield eformfield2 = new eformfield();
			
			eformfield2.setField_type(field_types[i]);
			eformfield2.setField_label(field_labels[i].trim());
			eformfield2.setField_formid(field_formid);
			eformfield2.setField_formrv(field_formrv);
			
			
			eformfield2.setField_mainfield(field_mainfields[i].equals("-") ? 0 :  Integer.valueOf(field_mainfields[i]));
			eformfield2.setField_datarefertype(field_datarefertypes[i]);
			eformfield2.setField_datareferid( field_datareferids[i].equals("-") ? 0 :  Integer.valueOf(field_datareferids[i]));
			eformfield2.setField_datarefercols(field_datarefercolss[i]);
			
			eformfields.add(eformfield2);
		}
		
		
		
		
	 	EformfieldDAO.savebatch(eformfields, 100);
		return "redirect:/list-dataset";
		 
	}   
	    
	   
	@RequestMapping(value="/dataset-condition",method = RequestMethod.GET)
	public String dataset(ModelMap model){
		MainServices.addNotificationAttributes(model);

		 List<DB> database = Arrays.stream(DB.values())
				    .sorted(Comparator.comparing(Enum::name))
				    .collect(Collectors.toList());
			model.addAttribute("database", database);
		
		return  "list-maindataset";
		
	}    
	
	@RequestMapping(value="/subdataset-condition/{level}",method = RequestMethod.GET)
	public String subdataset(ModelMap model,@PathVariable int level){
		MainServices.addNotificationAttributes(model);
		List<dataset> datasets =  DatasetDAO.getalls(level);
		model.addAttribute("datasets",datasets); 
		model.addAttribute("level",level); 
		return  "list-dataset";
		
	}    
	
	
	
	@RequestMapping(value="/central-condition/{level}",method = RequestMethod.GET)
	public String centralall(ModelMap model,@PathVariable int level){
		MainServices.addNotificationAttributes(model);
		List<central> centrals =  CentralDAO.getall(level,0);
		model.addAttribute("centrals",centrals); 
		return  "/central/list-central";
		
	} 
	

	
	@RequestMapping(value="/addcentral-condition/{level}/{idrefer}",method = RequestMethod.GET)
	public String addcentral(ModelMap model,@PathVariable int level,@PathVariable int idrefer){
		MainServices.addNotificationAttributes(model);
		model.addAttribute("idrefer",idrefer); 
		model.addAttribute("level",level); 
		return  "/central/formcentral";
		
	}    
	
	
	@RequestMapping(value="/addmainfield-condition/{central_id}",method = RequestMethod.GET)
	public String addmainfield(ModelMap model,@PathVariable int central_id){
	
		List<dataset> datasets  = DatasetDAO.getall();
		

		model.addAttribute("datasets",datasets); 
		model.addAttribute("central_id",central_id); 
		return  "/central/formmainfield";
		
	}    
	
	
	@RequestMapping(value="/addmainfield-condition",method = RequestMethod.POST)
	public String addmainfields(mainfield mainfields){
		
		     LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 String date = localDate.format(formatter);
		 	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 

		 	mainfields.setField_idadd(usersed.getUser_id());
		 	mainfields.setField_dateadd(date);
		 	
		 	
		 	MainfieldDAO.save(mainfields);
		 	int num =  MainfieldDAO.getsum(mainfields.getField_idrefer(),"primary"); 	
		 	CentralDAO.setotal(num,(int)mainfields.getField_idrefer());
           return "redirect:/viewcentral-condition/"+mainfields.getField_idrefer();
		 	
		
	} 
	
	
	@RequestMapping(value="/editmainfield-condition",method = RequestMethod.POST)
	public String editmainfields(mainfield mainfields){
		MainfieldDAO.update(mainfields);
	    return "redirect:/viewcentral-condition/"+mainfields.getField_idrefer();
	}   
	
	
	
	
	@RequestMapping(value="/editcentral-condition/{id}/{level}",method = RequestMethod.GET)
	public String editcentral(ModelMap model,@PathVariable int id,@PathVariable int level){
		MainServices.addNotificationAttributes(model);
		central centrals =  CentralDAO.getbyid(id);
		model.addAttribute("centrals",centrals); 
		model.addAttribute("level",level); 
		return  "/central/formcentral";
		
	}    
	
	@RequestMapping(value="/addcentral-condition",method = RequestMethod.POST)
	public String addcentrals(central centrals){
		
		     LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 String date = localDate.format(formatter);
		 	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		   	 
		 	centrals.setCentral_nameadd(usersed.getUser_name());
		 	centrals.setCentral_dateadd(date);
		 	
		 	CentralDAO.save(centrals);
		 	if(centrals.getCentral_level() > 0 ) {
		 		
		 		int num =  CentralDAO.getsum(centrals.getCentral_idrefer()); 	
		 		CentralDAO.setotal(num, centrals.getCentral_idrefer());
		 		
		 		return "redirect:/viewcentral-condition/"+centrals.getCentral_idrefer();
		 	}else {
		 		return "redirect:/central-condition/"+centrals.getCentral_level();
		 	}
		
	} 
	
	
	
	@RequestMapping(value="/editcentral-condition",method = RequestMethod.POST)
	public String editcentrals(central centrals){
		
		CentralDAO.update(centrals);
		
	 	if(centrals.getCentral_level() > 0 ) {
	 		
	 		return "redirect:/viewcentral-condition/"+centrals.getCentral_idrefer();
	 	}else {
	 		return "redirect:/central-condition/"+centrals.getCentral_level();
	 	}
	
	}   
	
	@RequestMapping(value="/viewcentral-condition/{id}",method = RequestMethod.GET)
	public String viewcentral(ModelMap model,@PathVariable int id){
		MainServices.addNotificationAttributes(model);
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
        model.addAttribute("name", Userservice.getLoggedName());
        model.addAttribute("Member_name", users.getUser_name());
        List<notification> notifications = NotificationDAO.getallrefer(users.getUser_name());
        int alert = NotificationDAO.countnotification(users.getUser_name(), 0);
        model.addAttribute("notification", notifications);
        model.addAttribute("alert", alert);
		
		
		central maincentrals = CentralDAO.getbyid(id);

		model.addAttribute("level",maincentrals.getCentral_level()); 
		model.addAttribute("maincentrals",maincentrals); 
		
		
		if(maincentrals.getCentral_level() == 2) {
			List<mainfield> mainfield= MainfieldDAO.getall(maincentrals.getCentral_id());
			model.addAttribute("mainfield",mainfield); 
		}else {
			
			List<central> centrals= CentralDAO.getall(maincentrals.getCentral_level() + 1, id);
			model.addAttribute("centrals",centrals); 
		}
	
		
		return  "/central/list-central";
	}   
	
	
	
	@RequestMapping(value="/deletecentral-condition/{id}/{level}",method = RequestMethod.GET)
	public String deletecentral(ModelMap model,@PathVariable int id,@PathVariable int level,@RequestParam(value = "idrefer",required=false) int idrefer){
		MainServices.addNotificationAttributes(model);
		CentralDAO.delete(id);
		
		if(idrefer > 0) {
			
	 		int num =  CentralDAO.getsum(idrefer); 	
	 		CentralDAO.setotal(num,idrefer);
			
			return "redirect:/viewcentral-condition/"+idrefer;
		}else {
			 return "redirect:/central-condition/"+level;
		}

	}   
	

	
	@RequestMapping(value="/adddataset-condition/{setgroup}",method = RequestMethod.GET)
	public String addataset(ModelMap model,@PathVariable int setgroup){
		model.addAttribute("setgroup",setgroup); 
		return  "formdataset";
		
	}    
	
	@RequestMapping(value="/editdataset-condition/{id}",method = RequestMethod.GET)
	public String editdataset(ModelMap model,@PathVariable int id){
		
		dataset datasets =  DatasetDAO.getbyid(id);
		model.addAttribute("datasets",datasets); 
		return  "formdataset";
		
	}    
	
	@RequestMapping(value="/addsubdataset-condition/{type}/{id}/{id_refer}",method = RequestMethod.GET)
	public String editdataset2(ModelMap model,@PathVariable String type,@PathVariable int id,@PathVariable int id_refer){
		
		MainServices.addNotificationAttributes(model);

	   	process process = EdataDAO.getProcess(id_refer);
    	
    	List<process> all_process = EdataDAO.getAllProcessJoin(process.getId_refer());
    	String ids = all_process.stream()
                .map(p -> String.valueOf(p.getId_refer())) // แปลง int เป็น String
                .distinct()      
                .collect(Collectors.joining(","));
		
		
				if(type.equals("Register")) {
					model.addAttribute("type",type);
				//	Filemanagerservices.LoadRegister(model,id_refer,ids);
				}
		
			

		
		return  "formdataset2";
		
	}    
	
	@RequestMapping(value="/addsubdataset-condition/{id}",method = RequestMethod.GET)
	public String addsubdataset2(ModelMap model,@PathVariable int id){
		MainServices.addNotificationAttributes(model);
		dataset datasets =  DatasetDAO.getbyid(id);
		model.addAttribute("datasets",datasets); 
		
		
		if(datasets.getDataset_name().equals("Product_Category")) {
			System.out.println(" Dataset_name Product_Category");
		}
		
		
		
		List<dataset_data> dataset_datas =  Dataset_dataDAO.getall(id);
		model.addAttribute("dataset_data",dataset_datas); 
		
		
		
		
		return  "formdataset3";
		
	}    
	
	
	@RequestMapping(value="/addsubdatasetapp-condition/{id}",method = RequestMethod.GET)
	public String addsubdatasetapp(ModelMap model,@PathVariable int id){
		MainServices.addNotificationAttributes(model);
		dataset datasets =  DatasetDAO.getbyid(id);
		model.addAttribute("datasets",datasets); 

		if(datasets.getDataset_name().equals("Product_Category")) {
			System.out.println(" Dataset_name Product_Category");
		}

		
		List<dataset_data> dataset_datas =  Dataset_dataDAO.getall(id);
		model.addAttribute("dataset_data",dataset_datas); 
		
		return  "app/windows/formdataset";
		
	}    
	@RequestMapping(value="/adddatasetapp-condition/{id}",method = RequestMethod.GET)
	public String adddatasetapp(ModelMap model,@PathVariable int id){
		MainServices.addNotificationAttributes(model);
		dataset datasets =  DatasetDAO.getbyid(id);
		model.addAttribute("datasets",datasets); 

		return  "app/windows/formadddataset";
		
	}    
	
	@RequestMapping(value="/editdatasetapp-condition/{id}/{dataset_id}",method = RequestMethod.GET)
	public String editdatasetapp(ModelMap model,@PathVariable int id,@PathVariable int dataset_id){
		
		MainServices.addNotificationAttributes(model);
		dataset datasets =  DatasetDAO.getbyid(id);
		model.addAttribute("datasets",datasets); 
		
		dataset_data dataset_datas =  Dataset_dataDAO.getbyid(dataset_id);
		model.addAttribute("dataset_datas",dataset_datas); 

		return  "app/windows/formadddataset";
		
	}    
	
	
	
	
	
	
	@RequestMapping(value="/addsubdataset2-condition",method = RequestMethod.POST)
	public String addsubdatasets2(dataset_data dataset_datas){
		   Dataset_dataDAO.save(dataset_datas);
		 return "redirect:/addsubdatasetapp-condition/"+dataset_datas.getDataset_data_id_refer();
		
	} 
	
	
	
	
	@RequestMapping(value="/addsubdataset-condition",method = RequestMethod.POST)
	public String addsubdatasets(dataset_data dataset_datas){
		   Dataset_dataDAO.save(dataset_datas);
		 return "redirect:/addsubdataset-condition/"+dataset_datas.getDataset_data_id_refer();
		
	} 
	
	@RequestMapping(value="/deletesubdataset-condition/{id}/{id_refer}",method = RequestMethod.GET)
	public String deletesubdataset(ModelMap model,@PathVariable int id,@PathVariable int id_refer){
		
		Dataset_dataDAO.delete(id);
		return "redirect:/addsubdataset-condition/"+id_refer;
	}    
	@RequestMapping(value="/deletesubdataset2-condition",method = RequestMethod.POST)
	public String deletesubdataset2(
			ModelMap model,
			@RequestParam int id,
			@RequestParam int id_refer){
		
		Dataset_dataDAO.delete(id);
		 return "redirect:/addsubdatasetapp-condition/"+id_refer;
	}    
	
	
	@RequestMapping(value="/editsubdataset-condition",method = RequestMethod.POST)
	public String editsubdatasets(dataset_data dataset_datas){
		Dataset_dataDAO.update(dataset_datas);
		 return "redirect:/addsubdataset-condition/"+dataset_datas.getDataset_data_id_refer();
		
	}   
	

	
	
	@RequestMapping(value="/deletedataset-condition/{id}",method = RequestMethod.GET)
	public String deletedataset(ModelMap model,@PathVariable int id){
		
		DatasetDAO.delete(id);
		return "redirect:/dataset-condition";
	}    
	
	@RequestMapping(value="/editdataset-condition",method = RequestMethod.POST)
	public String editdatasets(dataset datasets){
		
		 DatasetDAO.update(datasets);
		 return "redirect:/dataset-condition";
		
	}   
	
	
	@RequestMapping(value="/adddataset-condition",method = RequestMethod.POST)
	public String adddatasets(dataset datasets){
		
		     LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 String date = localDate.format(formatter);
		 	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		   	 
		 	datasets.setDataset_idadd(usersed.getUser_id());
		   	datasets.setDataset_dateadd(date);
		   	DatasetDAO.save(datasets);
		 return "redirect:/dataset-condition";
		
	}   
	
	
	

	@RequestMapping(value="/loadcondition-condition/{id}",method = RequestMethod.GET)
	public String loadcondition(ModelMap model,@PathVariable int id){
		
		  List<eformfield> eformfields = EformfieldDAO.getall(id, 0);
		 model.addAttribute("eformfields",eformfields);
		return "/ajax/eformfields";

	}   
	
	
	@RequestMapping(value="/relation-condition/{id}",method = RequestMethod.GET)
	public String relationcondition(ModelMap model, @PathVariable int id) throws IOException {
		MainServices.addNotificationAttributes(model);
		condition conditions = ConditionDAO.getbyid(id);
		List<dataset> datasets  = DatasetDAO.getall();
		
		List<user> users  =  UserDAO.getall();
        model.addAttribute("users",users); 

   
         
        List<eformfield> eformfields = EformfieldDAO.getall(id, 0);
        
        
        List<mainfield> textfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","text");  
        model.addAttribute("textfield",textfield); 
        List<mainfield> numberfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","number");  
        model.addAttribute("numberfield",numberfield);
        List<mainfield> datefield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","date");  
        model.addAttribute("datefield",datefield);
        List<mainfield> selectfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","select");  
        model.addAttribute("selectfield",selectfield); 
        List<mainfield> checkboxfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","checkbox");  
        model.addAttribute("checkboxfield",checkboxfield); 
        

		model.addAttribute("condition",conditions); 
		model.addAttribute("datasets",datasets); 
		model.addAttribute("eformfields",eformfields); 
		
		
		
	   condition conditionssub = ConditionDAO.getbyid(conditions.getCondition_sub());
	  List<eformfield> eformfields2 = EformfieldDAO.getall(conditions.getCondition_sub(), 0);
      model.addAttribute("eformfields2",eformfields2);
      
	  List<eformfield> eformfields3 = EformfieldDAO.getall(conditions.getCondition_sub2(), 0);
      model.addAttribute("eformfields3",eformfields3);
      
      
	  List<eformfield> eformfields4 = EformfieldDAO.getall(conditions.getCondition_sub3(), 0);
      model.addAttribute("eformfields4",eformfields4);
      
      
      
      condition conditionssub2 = ConditionDAO.getbyid(conditions.getCondition_sub2());
      condition conditionssub3 = ConditionDAO.getbyid(conditions.getCondition_sub3());
		model.addAttribute("conditionssub",conditionssub); 
		model.addAttribute("conditionssub2",conditionssub2); 
		model.addAttribute("conditionssub3",conditionssub3); 
		
	condition conditionold = ConditionDAO.getbysub(conditions.getCondition_id());	
	model.addAttribute("conditionold",conditionold); 	
	
	if(conditionold != null) {	
	condition conditionold2 = ConditionDAO.getbysub(conditionold.getCondition_id());	
	model.addAttribute("conditionold2",conditionold2); 
	}
		
	if(conditionssub != null) {	
		  condition conditionssubs = ConditionDAO.getbyid(conditionssub.getCondition_sub());
		    condition conditionssub2s = ConditionDAO.getbyid(conditionssub.getCondition_sub2());
		    condition conditionssub3s = ConditionDAO.getbyid(conditionssub.getCondition_sub3());
			model.addAttribute("conditionssubs",conditionssubs); 
			model.addAttribute("conditionssub2s",conditionssub2s); 
			model.addAttribute("conditionssub3s",conditionssub3s); 	
	}
	if(conditionssub2 != null) {	
		condition conditionssubss = ConditionDAO.getbyid(conditionssub2.getCondition_sub());
	    condition conditionssub2ss = ConditionDAO.getbyid(conditionssub2.getCondition_sub2());
	    condition conditionssub3ss = ConditionDAO.getbyid(conditionssub2.getCondition_sub3());
		model.addAttribute("conditionssubss",conditionssubss); 
		model.addAttribute("conditionssub2s",conditionssub2ss); 
		model.addAttribute("conditionssub3ss",conditionssub3ss); 	
	}
	if(conditionssub3 != null) {		
		condition conditionssubsss = ConditionDAO.getbyid(conditionssub3.getCondition_sub());
	    condition conditionssub2sss = ConditionDAO.getbyid(conditionssub3.getCondition_sub2());
	    condition conditionssub3sss = ConditionDAO.getbyid(conditionssub3.getCondition_sub3());
		model.addAttribute("conditionssubsss",conditionssubsss); 
		model.addAttribute("conditionssub2sss",conditionssub2sss); 
		model.addAttribute("conditionssub3sss",conditionssub3sss); 	
	}
		
		
		return  "e-form4";
	}
	
	
	
	@RequestMapping(value="/editgroup-condition",method = RequestMethod.POST)
	public String relationplan(
			ModelMap model
			,@RequestParam(value = "id1",required=false) String id1
			,@RequestParam(value = "id2",required=false) String id2
			,@RequestParam(value = "id3",required=false) String id3
			,@RequestParam(value = "idrefer",required=false) String idrefer
			,@RequestParam(value = "condition_group",required=false) String condition_group
			){
	
	    	//ConditionDAO.setGroup(condition_group,id1,id2,id3);
	    	 Upload_pnDAO.setGroup(condition_group,id1,id2,id3, 0);
	    	
		 return "redirect:/relationplan-condition/"+idrefer;
	}
	
	
	@RequestMapping(value="/relationplan-condition/{id}",method = RequestMethod.GET)
	public String relationplan(ModelMap model, @PathVariable int id) throws IOException {

		
		upload_pn upload_pns = Upload_pnDAO.getbyId(id);
		
		 

		 if(upload_pns != null) {
			 model.addAttribute("upload_pns",upload_pns); 
			 
						condition conditions = ConditionDAO.getbyidandplan((int)upload_pns.getUpload_pn_monitor());
						List<dataset> datasets  = DatasetDAO.getall();
						
						List<user> users  =  UserDAO.getall();
				        model.addAttribute("users",users); 
				
				   
				         
				       
				        if(conditions != null) {
				        	 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(), 0);
						    	model.addAttribute("eformfields",eformfields); 
						    
						    	model.addAttribute("condition",conditions); 
				        	
				   		 List<eformfield> eformfields2s = EformfieldDAO.getall(conditions.getCalculation_port1(), 0);
				       	 model.addAttribute("eformfields2s",eformfields2s); 
				  
				       	 List<eformfield> eformfields3s = EformfieldDAO.getall(conditions.getCalculation_port2(), 0);
				       	 model.addAttribute("eformfields3s",eformfields3s); 
				       	 
				       	System.out.println("port1 "+conditions.getCalculation_port1());
				       	System.out.println("port2 "+conditions.getCalculation_port2()); 
				       	System.out.println("eformfields2s"+eformfields2s); 
				       	System.out.println("eformfields3s"+eformfields3s); 
				       	 
				        model.addAttribute("port1",conditions.getCalculation_port1());  
				        model.addAttribute("port2",conditions.getCalculation_port2()); 
				
				        
				        
				        List<mainfield> textfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","text");  
				        model.addAttribute("textfield",textfield); 
				        List<mainfield> numberfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","number");  
				        model.addAttribute("numberfield",numberfield);
				        List<mainfield> datefield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","date");  
				        model.addAttribute("datefield",datefield);
				        List<mainfield> selectfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","select");  
				        model.addAttribute("selectfield",selectfield); 
				        List<mainfield> checkboxfield  =  MainfieldDAO.getall(conditions.getCondition_type(),"primary","checkbox");  
				        model.addAttribute("checkboxfield",checkboxfield); 
				        
				    
						
						model.addAttribute("datasets",datasets); 
					
						condition conditions2 = ConditionDAO.getbyid(conditions.getCalculation_port1());
						condition conditions3 = ConditionDAO.getbyid(conditions.getCalculation_port2());
						model.addAttribute("condition2",conditions2); 
						model.addAttribute("condition3",conditions3); 
						
						
						
					   condition conditionssub = ConditionDAO.getbyidandplan(conditions.getCondition_sub());
					  List<eformfield> eformfields2 = EformfieldDAO.getall(conditions.getCondition_sub(), 0);
				      model.addAttribute("eformfields2",eformfields2);
				      
					  List<eformfield> eformfields3 = EformfieldDAO.getall(conditions.getCondition_sub2(), 0);
				      model.addAttribute("eformfields3",eformfields3);
				      
				      
					  List<eformfield> eformfields4 = EformfieldDAO.getall(conditions.getCondition_sub3(), 0);
				      model.addAttribute("eformfields4",eformfields4);
				      
				      
				      if(upload_pns != null) {
				      upload_pn upload_pnssub = Upload_pnDAO.getbyId(upload_pns.getUpload_pn_send1());
				  	  model.addAttribute("upload_pnssub",upload_pnssub); 
				  	  
				  	  
				  	  if(upload_pnssub != null) {
					      upload_pn upload_other1 = Upload_pnDAO.getbyId(upload_pnssub.getUpload_pn_send1());
					  	  model.addAttribute("upload_other1",upload_other1); 
					     upload_pn upload_other1s = Upload_pnDAO.getbyId(upload_pnssub.getUpload_pn_send2());
					     model.addAttribute("upload_other1s",upload_other1s); 
					     upload_pn upload_other1ss = Upload_pnDAO.getbyId(upload_pnssub.getUpload_pn_send3());
					     model.addAttribute("upload_other1ss",upload_other1ss); 
					      }
					
				  	  
				      }
				      
				      if(upload_pns != null) {
				          upload_pn upload_pnssub2 = Upload_pnDAO.getbyId(upload_pns.getUpload_pn_send2());
				      	model.addAttribute("upload_pnssub2",upload_pnssub2); 
				      	
				      	
				    	  if(upload_pnssub2 != null) {
				    	      upload_pn upload_other2 = Upload_pnDAO.getbyId(upload_pnssub2.getUpload_pn_send1());
				    	  	  model.addAttribute("upload_other2",upload_other2); 
				    	     upload_pn upload_other2s = Upload_pnDAO.getbyId(upload_pnssub2.getUpload_pn_send2());
				    	     model.addAttribute("upload_other2s",upload_other2s); 
				    	     upload_pn upload_other2ss = Upload_pnDAO.getbyId(upload_pnssub2.getUpload_pn_send3());
				    	     model.addAttribute("upload_other2ss",upload_other2ss); 
				    	      }
				      	
				      }
				      if(upload_pns != null) {
				          upload_pn upload_pnssub3 = Upload_pnDAO.getbyId(upload_pns.getUpload_pn_send3());
				      	model.addAttribute("upload_pnssub3",upload_pnssub3); 
				      	
				    	  if(upload_pnssub3 != null) {
				    	      upload_pn upload_other3 = Upload_pnDAO.getbyId(upload_pnssub3.getUpload_pn_send1());
				    	  	  model.addAttribute("upload_other3",upload_other3); 
				    	     upload_pn upload_other3s = Upload_pnDAO.getbyId(upload_pnssub3.getUpload_pn_send2());
				    	     model.addAttribute("upload_other3s",upload_other3s); 
				    	     upload_pn upload_other3ss = Upload_pnDAO.getbyId(upload_pnssub3.getUpload_pn_send3());
				    	     model.addAttribute("upload_other3ss",upload_other3ss); 
				    	      }
				      }
				             
				      
				      
				      
				      condition conditionssub2 = ConditionDAO.getbyidandplan(conditions.getCondition_sub2());
				      condition conditionssub3 = ConditionDAO.getbyidandplan(conditions.getCondition_sub3());
				      
				      
				      if(conditionssub2 != null) {       model.addAttribute("port1",conditions.getCondition_sub2());   }
				      if(conditionssub3 != null) {       model.addAttribute("port2",conditions.getCondition_sub3());  }
				     
				
				      
						model.addAttribute("conditionssub",conditionssub); 
						model.addAttribute("conditionssub2",conditionssub2); 
						model.addAttribute("conditionssub3",conditionssub3); 
						
					condition conditionold = ConditionDAO.getbysubandplan(conditions.getCondition_id());	
					model.addAttribute("conditionold",conditionold); 	
					
					if(conditionold != null) {	
					condition conditionold2 = ConditionDAO.getbysubandplan(conditionold.getCondition_id());	
					model.addAttribute("conditionold2",conditionold2); 
					}
						
					

			    	if(conditionssub != null) { 
						map_key map_key1 = ConditionDAO.getMap_key(conditions.getCondition_id(),conditionssub.getCondition_id());
						model.addAttribute("map_key1",map_key1); 
					}
					if(conditionssub2 != null) { 
						map_key map_key2 = ConditionDAO.getMap_key(conditions.getCondition_id(),conditionssub2.getCondition_id()); 
						model.addAttribute("map_key2",map_key2); 
					}
					if(conditionssub3 != null) { 
						map_key map_key3 = ConditionDAO.getMap_key(conditions.getCondition_id(),conditionssub3.getCondition_id()); 
						model.addAttribute("map_key3",map_key3); 
					}
					
					
					
					
					if(conditionssub != null) {	
						   condition conditionssubs = ConditionDAO.getbyidandplan(conditionssub.getCondition_sub());
						   
						    condition conditionssub2s = ConditionDAO.getbyidandplan(conditionssub.getCondition_sub2());
						    
						    condition conditionssub3s = ConditionDAO.getbyidandplan(conditionssub.getCondition_sub3());
						    
							model.addAttribute("conditionssubs",conditionssubs); 
							model.addAttribute("conditionssub2s",conditionssub2s); 
							model.addAttribute("conditionssub3s",conditionssub3s); 	
					
					}
					if(conditionssub2 != null) {	
						condition conditionssubss = ConditionDAO.getbyidandplan(conditionssub2.getCondition_sub());
					    condition conditionssub2ss = ConditionDAO.getbyidandplan(conditionssub2.getCondition_sub2());
					    condition conditionssub3ss = ConditionDAO.getbyidandplan(conditionssub2.getCondition_sub3());
						model.addAttribute("conditionssubss",conditionssubss); 
						model.addAttribute("conditionssub2ss",conditionssub2ss); 
						model.addAttribute("conditionssub3ss",conditionssub3ss); 	
					}
					if(conditionssub3 != null) {		
						condition conditionssubsss = ConditionDAO.getbyidandplan(conditionssub3.getCondition_sub());
					    condition conditionssub2sss = ConditionDAO.getbyidandplan(conditionssub3.getCondition_sub2());
					    condition conditionssub3sss = ConditionDAO.getbyidandplan(conditionssub3.getCondition_sub3());
						model.addAttribute("conditionssubsss",conditionssubsss); 
						model.addAttribute("conditionssub2sss",conditionssub2sss); 
						model.addAttribute("conditionssub3sss",conditionssub3sss); 	
					}
				        }
				   

				        
    

	}
	    


	    
    
		return  "e-form5";
	}
	
	
	
	@RequestMapping(value="/form-condition",method = RequestMethod.GET)
	public String formlist(ModelMap model){
		MainServices.addNotificationAttributes(model);
		 List<form> forms = FormDAO.getall();
		model.addAttribute("forms",forms); 
		 return "form_list";
		
	}   
	
	@RequestMapping(value="/editform-condition/{id}",method = RequestMethod.GET)
	public String formediter(ModelMap model, @PathVariable int id){
		MainServices.addNotificationAttributes(model);
		 form forms = FormDAO.getbyid(id);
		 model.addAttribute("forms",forms); 
		 return "/form/form";
		
	}   
	@RequestMapping(value="/editformsave-condition",method = RequestMethod.POST)
	public String editformsave(ModelMap model,form forms){
		
		 FormDAO.edit(forms);
		 return "redirect:/renderforms-condition//"+forms.getForm_id();
		
	}   
	
	@RequestMapping(value="/renderform-condition/{id}",method = RequestMethod.GET)
	public String renderform(ModelMap model, @PathVariable int id){
		
		 form forms = FormDAO.getbyid(id);
		 model.addAttribute("forms",forms); 
		 
		 
		 String tag1 = "#tag1";
		 String tag2 = "#tag2";
		 String tag3 = "#tag3";
		 String tag4 = "#tag4";
		 String tag5 = "#tag5";
		 String tag6 = "#tag6";
		 String tag7 = "#tag7";
		 String tag8 = "#tag8";
		 String tag9 = "#tag9";
		 String tag10 = "#tag10";
		 String tag11 = "#tag11";
		 String tag12 = "#tag12";
		 String tag13 = "#tag13";
		 String tag14 = "#tag14";
		 String tag15 = "#tag15";
		 String tag16 = "#tag16";
		 String tag17 = "#tag17";
		 String tag18 = "#tag18";
		 String tag19 = "#tag19";
		 String tag20 = "#tag20";
		 String tag21 = "#tag21";
		 String tag22 = "#tag22";
		 String tag23 = "#tag23";
		 String tag24 = "#tag24";
		 String tag25 = "#tag25";
		 String tag26 = "#tag26";
		 String tag27 = "#tag27";
		 String tag28 = "#tag28";
		 String tag29 = "#tag29";
		 String tag30 = "#tag30";
		 String tag31 = "#tag31";
		 String tag32 = "#tag32";
		 String tag33 = "#tag33";
		 String tag34 = "#tag34";
		 String tag35 = "#tag35";
		 String tag36 = "#tag36";
		 String tag37 = "#tag37";
		 String tag38 = "#tag38";
		 String tag39 = "#tag39";
		 String tag40 = "#tag40";
		 
		 
		 model.addAttribute("tag1",tag1); 
		 model.addAttribute("tag2",tag2); 
		 model.addAttribute("tag3",tag3); 
		 model.addAttribute("tag4",tag4); 
		 model.addAttribute("tag5",tag5); 
		 model.addAttribute("tag6",tag6); 
		 model.addAttribute("tag7",tag7); 
		 model.addAttribute("tag8",tag8); 
		 model.addAttribute("tag9",tag9); 
		 model.addAttribute("tag10",tag10); 
		 model.addAttribute("tag11",tag11); 
		 model.addAttribute("tag12",tag12); 
		 model.addAttribute("tag13",tag13); 
		 model.addAttribute("tag14",tag14); 
		 model.addAttribute("tag15",tag15); 
		 model.addAttribute("tag16",tag16); 
		 model.addAttribute("tag17",tag17); 
		 model.addAttribute("tag18",tag18); 
		 model.addAttribute("tag19",tag19); 
		 model.addAttribute("tag20",tag20); 
		 model.addAttribute("tag21",tag21); 
		 model.addAttribute("tag22",tag22); 
		 model.addAttribute("tag23",tag23); 
		 model.addAttribute("tag24",tag24); 
		 model.addAttribute("tag25",tag25); 
		 model.addAttribute("tag26",tag26); 
		 model.addAttribute("tag27",tag27); 
		 model.addAttribute("tag28",tag28); 
		 model.addAttribute("tag29",tag29); 
		 model.addAttribute("tag30",tag30); 
		 model.addAttribute("tag31",tag31); 
		 model.addAttribute("tag32",tag32);
		 model.addAttribute("tag33",tag33); 
		 model.addAttribute("tag34",tag34); 
		 model.addAttribute("tag35",tag35); 
		 model.addAttribute("tag36",tag36); 
		 model.addAttribute("tag37",tag37); 
		 model.addAttribute("tag38",tag38); 
		 model.addAttribute("tag39",tag39); 
		 model.addAttribute("tag40",tag40); 
		 
		 
		 if(forms.getForm_id() == 1 ) {
			 return "/form/bill";
		 }else {
			 return "/form/bill2";
		 }

	}   
	
	
	@RequestMapping(value="/renderforms-condition/{id}",method = RequestMethod.GET)
	public String renderforms(ModelMap model, @PathVariable int id){
		
		 form forms = FormDAO.getbyid(id);
		 model.addAttribute("forms",forms); 

		List<condition> allconditions = ConditionDAO.getall();
		model.addAttribute("allconditions",allconditions); 
		 
		 
		condition conditions = ConditionDAO.getbyid(forms.getForm_condition());
		 
		if(conditions != null ) {
			 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(), 0); 
			 model.addAttribute("eformfields",eformfields);  
		 
				
			 
				condition subconditions = ConditionDAO.getbysub(conditions.getCondition_id());
				if(subconditions != null ) {
					 
					List<eformfield> eformfields2 = EformfieldDAO.getall(subconditions.getCondition_id(), 0);
					model.addAttribute("eformfields2",eformfields2);   
				}   
		}    
		 return "/form/formediter";

	}   
	
	

	@RequestMapping(value="/eformfield-condition/{id}",method = RequestMethod.GET)
	public String eformfields(ModelMap model, @PathVariable int id){
		String data = "-";
		MainServices.addNotificationAttributes(model);
		   List<eformfield> eformfields = EformfieldDAO.getall(id, 0);
		   for(eformfield datas : eformfields){
			   
			 
				   data = data+","+datas.getField_label()+"|"+datas.getField_id();
			
			 
		   }
		   
		   model.addAttribute("data",data);   
		   return "/eformparts/eformfielddata";
	}   
	
	
	
	
	
	
	 
}//class end

