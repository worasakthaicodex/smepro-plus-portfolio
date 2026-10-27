package com.smepro.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class msofficeservice {

	
	
	public boolean msofficetopdf( String templateFile,String pdfFile,String type) {
		
		
		 unfile(pdfFile);
		 System.out.println("type" + templateFile);
		 
	    if(type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") || type.equals("application/vnd.ms-excel")) {
	    	 System.out.println("Isexcel ");
	    	 return  Isexcel(templateFile,pdfFile);
		} 
		else if(type.equals("application/msword") || type.equals("application/octet-stream") || type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
			 System.out.println("Isword ");
			 return Isword(templateFile,pdfFile);		
				} 
		else if(type.equals("application/vnd.ms-powerpoint") || type.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")  ) {
			 System.out.println("IsPowerpoint ");
			 return IsPowerpoint(templateFile,pdfFile);
		} else {
			return false;
		}
	
	}
	
	
		public void unfile( String pdfFile) {
		
			File destFile = new File(pdfFile);
		
		       if(destFile.exists()) {
				     boolean success = destFile.delete();
		         }
		 
		}
	
	
	public boolean Isexcel( String templateFile,String pdfFile) {
		
		 try {
 		    //create a temporary file and grab the path for it
 		    Path tempScript = Files.createTempFile("script", ".vbs");

 		    
 		    File file2 = ResourceUtils.getFile("classpath:xl2pdf.vbs");
 		    System.out.println("templateFile is: " + file2);
 		    
 		    //read all the lines of the .vbs script into memory as a list
 		    //here we pull from the resources of a Gradle build, where the vbs script is stored
 		    System.out.println("Path for vbs script is: '" +file2.getCanonicalPath()+ "'");
 		    List<String> script = Files.readAllLines(Paths.get(file2.getCanonicalPath()));

 		    // append test.xlsm for file name. savePath was passed to this function
 		   //templateFile = "C:/Users/User/Desktop/New folder (3)/case1.xls";
 		    templateFile = templateFile.replace("\\", "/");
 		   // pdfFile = "C:/server/smepro/WorkbookToPdf.pdf";
 		    pdfFile = pdfFile.replace("\\", "/");
 		    System.out.println("templateFile is: " + templateFile);
 		    System.out.println("pdfFile is: " + pdfFile);

 		    //replace the placeholders in the vbs script with the chosen file paths
 		    for (int i = 0; i < script.size(); i++) {
 		        script.set(i, script.get(i).replaceAll("XL_FILE", templateFile));
 		        script.set(i, script.get(i).replaceAll("PDF_FILE", pdfFile));
 		        System.out.println("Line " + i + " is: " + script.get(i));
 		    }

 		    //write the modified code to the temporary script
 		    Files.write(tempScript, script);

 		    //create a processBuilder for starting an operating system process
 		    ProcessBuilder pb = new ProcessBuilder("wscript", tempScript.toString());

 		    //start the process on the operating system
 		    Process process = pb.start();

 	
 		    
 		    //tell the process how long to wait for timeout
 		    Boolean success = process.waitFor(50L, TimeUnit.MILLISECONDS);
 		    
 		   if(!success) {
		        System.out.println("Error: Could not print PDF within ");
		    } else {
		        System.out.println("Process to run visual basic script for pdf conversion succeeded.");
		    }
 		    return success;
 		    
 		} catch (Exception e) {
 		
 		    System.out.println("ERROR: Error converting to pdf.");
 		    System.out.println("Exception message is:");
 		    System.out.println(e.getMessage());
 			  return false;
 		}
 	   
		
	}
	
	
	
	public boolean Isword( String templateFile,String pdfFile) {
		
		 try {
		    //create a temporary file and grab the path for it
		    Path tempScript = Files.createTempFile("script", ".vbs");

		    
		    File file2 = ResourceUtils.getFile("classpath:doc2pdf.vbs");
		    System.out.println("templateFile is: " + file2);
		    
		    //read all the lines of the .vbs script into memory as a list
		    //here we pull from the resources of a Gradle build, where the vbs script is stored
		    System.out.println("Path for vbs script is: '" +file2.getCanonicalPath()+ "'");
		    List<String> script = Files.readAllLines(Paths.get(file2.getCanonicalPath()));

		    // append test.xlsm for file name. savePath was passed to this function
		   // templateFile = "C:/Users/User/Desktop/New folder (3)/idoc.docx";
		    templateFile = templateFile.replace("\\", "\\\\");
		    //pdfFile = "C:/server/smepro/idoc.pdf";
		    pdfFile = pdfFile.replace("\\", "\\\\");
		    System.out.println("templateFile is: " + templateFile);
		    System.out.println("pdfFile is: " + pdfFile);

		    //replace the placeholders in the vbs script with the chosen file paths
		    for (int i = 0; i < script.size(); i++) {
		        script.set(i, script.get(i).replaceAll("DOC_FILE", templateFile));
		        script.set(i, script.get(i).replaceAll("PDF_FILE",pdfFile));
		        System.out.println("Line " + i + " is: " + script.get(i));
		    }

		    System.out.println("script " +  script);
		    
		    
		    
		    //write the modified code to the temporary script
		    Files.write(tempScript, script);

		    //create a processBuilder for starting an operating system process
		    ProcessBuilder pb = new ProcessBuilder("wscript", tempScript.toString());

		    //start the process on the operating system
		    Process process = pb.start();

	
		    
		    //tell the process how long to wait for timeout
		    Boolean success = process.waitFor(50L, TimeUnit.MILLISECONDS);
		    
		    if(!success) {
 		        System.out.println("Error: Could not print PDF within ");
 		    } else {
 		        System.out.println("Process to run visual basic script for pdf conversion succeeded.");
 		    }
		    return success;
		    
		} catch (Exception e) {
		  
		    System.out.println("ERROR: Error converting to pdf.");
		    System.out.println("Exception message is:");
		    System.out.println(e.getMessage());
		    return false;
		}
	   
		
	}
	
	
	
	
	
	public boolean IsPowerpoint( String templateFile,String pdfFile) {
		
		 try {
		    //create a temporary file and grab the path for it
		    Path tempScript = Files.createTempFile("script", ".vbs");

		    
		    File file2 = ResourceUtils.getFile("classpath:ppt2pdf.vbs");
		    System.out.println("templateFile is: " + file2);
		    
		    //read all the lines of the .vbs script into memory as a list
		    //here we pull from the resources of a Gradle build, where the vbs script is stored
		    System.out.println("Path for vbs script is: '" +file2.getCanonicalPath()+ "'");
		    List<String> script = Files.readAllLines(Paths.get(file2.getCanonicalPath()));

		    // append test.xlsm for file name. savePath was passed to this function
		   // templateFile = "C:/Users/User/Desktop/New folder (3)/ippt.pptx";
		   templateFile = templateFile.replace("\\", "\\\\");
		  //  pdfFile = "C:/server/smepro/ippt.pdf";
		   pdfFile = pdfFile.replace("\\", "\\\\");
		    System.out.println("templateFile is: " + templateFile);
		    System.out.println("pdfFile is: " + pdfFile);

		    //replace the placeholders in the vbs script with the chosen file paths
		    for (int i = 0; i < script.size(); i++) {
		        script.set(i, script.get(i).replaceAll("PTT_FILE",templateFile));
		        script.set(i, script.get(i).replaceAll("PDF_FILE", pdfFile));
		        System.out.println("Line " + i + " is: " + script.get(i));
		    }

		    System.out.println("ippt " +  script);
		    
		    
		    //write the modified code to the temporary script
		    Files.write(tempScript, script);

		    //create a processBuilder for starting an operating system process
		    ProcessBuilder pb = new ProcessBuilder("wscript", tempScript.toString());

		    //start the process on the operating system
		    Process process = pb.start();

	
		    
		    //tell the process how long to wait for timeout
		    Boolean success = process.waitFor(50L, TimeUnit.MILLISECONDS);
		    
		    if(!success) {
 		        System.out.println("Error: Could not print PDF within ");
 		    } else {
 		        System.out.println("Process to run visual basic script for pdf conversion succeeded.");
 		    }
		    return success;
		    
		} catch (Exception e) {
		   
		    System.out.println("ERROR: Error converting to pdf.");
		    System.out.println("Exception message is:");
		    System.out.println(e.getMessage());
		    return false;
		}
	   
		
	}
	
}
