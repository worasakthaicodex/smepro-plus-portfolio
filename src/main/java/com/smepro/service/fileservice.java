package com.smepro.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.smepro.models.drives;
import com.smepro.models.edata;
import com.smepro.models.folder;

@Service
public class fileservice {
	
	private static DecimalFormat oneDecimal = new DecimalFormat("0.0");
	
	
	private static String generateRandom(String aToZ) {
	    Random rand=new Random();
	    StringBuilder res=new StringBuilder();
	    for (int i = 0; i < 17; i++) {
	       int randIndex=rand.nextInt(aToZ.length()); 
	       res.append(aToZ.charAt(randIndex));            
	    }
	    return res.toString();
	}
	
	public String assertEquals(String  type) {
		
		 String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		 String generatedString= generateRandom(SALTCHARS);

		 
	    if(type.equals("image/jpeg")) {
	    	type = ".jpeg";
	    }
	    else if(type.equals("image/png")) {
	    	type = ".png";
	    }	    
	    else if(type.equals("image/jpg")) {
	    	type = ".jpg";
	    }		    
	    else if(type.equals("application/pdf")) {
	    	type = ".pdf";
	    }		    
	    else if(type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
	    	type = ".xlsx";
	    }	
	    else if(type.equals("application/vnd.ms-excel")) {
	    	type = ".xls";
	    }	
	    else if(type.equals("application/json")) {
	    	type = ".json";
	    }
	    else if(type.equals("application/msword")) {
	    	type = ".doc";
	    }	
	    else if(type.equals("application/pdf")) {
	        type = ".pdf";
	    }
	    else if(type.equals("application/octet-stream")) {
	    	type = ".cheese";
	    }	 
	    else if(type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
	    	type = ".docx";
	    }	
	    else if(type.equals("application/vnd.ms-powerpoint")) {
	    	type = ".ppt";
	    }	
	    else if(type.equals("image/gif")) {
	    	type = ".gif";
	    }	
	    else if(type.equals("text/plain")) {
	    	type = ".txt";
	    }	
	    else if(type.equals("image/webp")) {
	    	type = ".webp";
	    }	
	    else if(type.equals("text/csv")) {
	    	type = ".csv";
	    }	
	    else if(type.equals("video/mp4")) {
	    	type = ".mp4";
	    }	
	    else if(type.equals("video/vnd.dlna.mpeg-tts")) {
	    	type = ".ts";
	    }	
	    else if(type.equals("audio/mpeg")) { 
	    	type = ".mp3";
	    }	    
	    else if(type.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")) {
	    	type = ".pptx";
	    }else {
	    	return null;
	    }		
	      
		return generatedString+type;
	}
	public String assertEquals(String  type,String  type2) {
		
		 String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		 String generatedString= generateRandom(SALTCHARS);

		 
	    if(type.equals("image/jpeg")) {
	    	type = ".jpeg";
	    }
	    else if(type.equals("image/png")) {
	    	type = ".png";
	    }	    
	    else if(type.equals("image/jpg")) {
	    	type = ".jpg";
	    }		    
	    else if(type.equals("application/pdf")) {
	    	type = ".pdf";
	    }		    
	    else if(type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
	    	type = ".xlsx";
	    }	
	    else if(type.equals("application/vnd.ms-excel")) {
	    	type = ".xls";
	    }	
	    else if(type.equals("application/json")) {
	    	type = ".json";
	    }
	    else if(type.equals("application/msword")) {
	    	type = ".doc";
	    }	
	    else if(type.equals("application/octet-stream")) {
	    	type = type2;
	    }	 
	    else if(type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
	    	type = ".docx";
	    }	
	    else if(type.equals("application/vnd.ms-powerpoint")) {
	    	type = ".ppt";
	    }	
	    else if(type.equals("image/gif")) {
	    	type = ".gif";
	    }	
	    else if(type.equals("text/plain")) {
	    	type = ".txt";
	    }	
	    else if(type.equals("image/webp")) {
	    	type = ".webp";
	    }	
	    else if(type.equals("text/csv")) {
	    	type = ".csv";
	    }	
	    else if(type.equals("video/mp4")) {
	    	type = ".mp4";
	    }	
	    else if(type.equals("video/vnd.dlna.mpeg-tts")) {
	    	type = ".ts";
	    }	
	    else if(type.equals("audio/mpeg")) { 
	    	type = ".mp3";
	    }	    
	    else if(type.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")) {
	    	type = ".pptx";
	    }else {
	    	return null;
	    }		
	      
		return generatedString+type;
	}
	  public String printFileSize(long size) {


		  /**
		   * Given an integer, return a string that is in an approximate, but human 
		   * readable format. 
		   * It uses the bases 'k', 'm', and 'g' for 1024, 1024**2, and 1024**3.
		   * @param number the number to format
		   * @return a human readable form of the integer
		   */
		    long absNumber = Math.abs(size);
		    double result = size;
		    String suffix = "";
		    if (absNumber < 1024) {
		      // nothing
		    } else if (absNumber < 1024 * 1024) {
		      result = size / 1024.0;
		      suffix = "Kb";
		    } else if (size < 1024 * 1024 * 1024) {
		      result = size / (1024.0 * 1024);
		      suffix = "Mb";
		    } else {
		      result = size / (1024.0 * 1024 * 1024);
		      suffix = "Gb";
		    }
		    return oneDecimal.format(result) + suffix;
	        
	  }
	  
	  
	  public long printSystemSize(HttpSession session) {
		  
		  String UPLOAD_DIRECTORY_1 = "/filesuploads/user/";
			 String UPLOAD_DIRECTORY_2 = "/filesuploads/temporary/";
			 long totalSizeBytes = 0;
			 long maxSizeBytes = 100 * 1024 * 1024; 
			 
			 
			 ServletContext context = session.getServletContext();

			 String path1 = context.getRealPath(UPLOAD_DIRECTORY_1);
			 String path2 = context.getRealPath(UPLOAD_DIRECTORY_2);

			 Path folderPath1 = Paths.get(path1);
			 Path folderPath2 = Paths.get(path2);

			 try {
			     long size1 = Files.walk(folderPath1)
			             .filter(p -> p.toFile().isFile())
			             .mapToLong(p -> p.toFile().length())
			             .sum();

			     long size2 = Files.walk(folderPath2)
			             .filter(p -> p.toFile().isFile())
			             .mapToLong(p -> p.toFile().length())
			             .sum();

			     totalSizeBytes = size1 + size2;
			     System.out.println("ขนาดทั้งสองโฟลเดอร์รวมกัน: " + totalSizeBytes + " bytes");
			     System.out.println("ขนาดทั้งสองโฟลเดอร์รวมกัน: " + (totalSizeBytes / 1024) + " KB");
			     System.out.println("ขนาดทั้งสองโฟลเดอร์รวมกัน: " + (totalSizeBytes / (1024 * 1024)) + " MB");
			 } catch (IOException e) {
			     e.printStackTrace();
			 }
			return maxSizeBytes - totalSizeBytes;
			
	  }
	  
	  

	  
	  
	  
}

