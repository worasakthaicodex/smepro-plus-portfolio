package com.smepro.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.smepro.service.fileservice;

@Controller  
public class uploadcontroller {

	@Autowired
	fileservice Fileservice;
	
	
	    private static final String UPLOAD_DIRECTORY2 ="/filesuploads/temporary";  
	      
		@RequestMapping(value="/uploadform" , method = RequestMethod.GET)
		//@ResponseBody
		public String loginpage() {
			return "upload";
		}
		      
	    @RequestMapping(value="savefile",method=RequestMethod.POST)  
	    public ModelAndView saveimage( @RequestParam CommonsMultipartFile file,  
	           HttpSession session) throws Exception{  
	  
	    ServletContext context = session.getServletContext();  
	    String path = context.getRealPath(UPLOAD_DIRECTORY2);  
	    String filenameoriginal = file.getOriginalFilename();  
	    String filename = Fileservice.assertEquals(file.getContentType());
	    long imageFileSize = file.getSize();
	    System.out.print(imageFileSize);
	    
        if(filename != null) {
    	    byte[] bytes = file.getBytes();  
    	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
    	         new File(path + File.separator + filename)));  
    	    stream.write(bytes);  
    	    stream.flush();  
    	    stream.close();  
    	           
    	     return new ModelAndView("upload-success","filename",path+"/"+filename);    	   
       }else {

	    
	    byte[] bytes = file.getBytes();  
	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
	         new File(path + File.separator + filename)));  
	    stream.write(bytes);  
	    stream.flush();  
	    stream.close();  
	           
	     return new ModelAndView("upload-success","filename",path+"/"+filename);  
	    
       }
       
       }  
	
}
