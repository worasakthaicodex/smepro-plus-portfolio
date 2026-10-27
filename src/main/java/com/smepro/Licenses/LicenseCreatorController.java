package com.smepro.Licenses;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smepro.dao.backupDAO;
import com.smepro.dao.setmailDAO;
import com.smepro.models.notification;
import com.smepro.models.backup;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LicenseCreatorController {

	@Autowired
	backupDAO BackupDAO;  
	
	
	
	 @Value("${license.licensePath}")
	    private String licensePath;

	    /**
	     * 获取服务器硬件信息
	     * @author zifangsky
	     * @date 2018/4/26 13:13
	     * @since 1.0.0
	     * @param osName 操作系统类型，如果为空则自动判断
	     * @return com.ccx.models.license.LicenseCheckModel
	     */
	 
		@RequestMapping(value="/backup" ,  method=RequestMethod.GET)    
	    public String backup(ModelMap model){ 
	   
				List<backup>  backups = BackupDAO.getall();
				model.addAttribute("backup",backups);  
	      	 	 return "formbackup"; 
	    }
	 
		@RequestMapping(value="/backups" ,  method=RequestMethod.POST)    
	    public String backups(backup backups){ 
	   
				    BackupDAO.save(backups);
					return "redirect:/backup"; 
	    }
	 
		@RequestMapping(value="/delbackup" ,  method=RequestMethod.GET)    
	    public String delbackup(@RequestParam int id){ 
	   
				    BackupDAO.delete(id);
					return "redirect:/backup"; 
	    }
	 
		
		
	    @RequestMapping(value = "/getServerInfos")
	    public String getServerInfos(@RequestParam(value = "osName",required = false) String osName,license result,ModelMap model) {
	       
	    	
	    	System.out.println("getServer");
	    	
	        if(StringUtils.isBlank(osName)){
	            osName = System.getProperty("os.name");
	        }
	        osName = osName.toLowerCase();

	        mainserverinfo abstractServerInfos = null;

	      
	        if (osName.startsWith("windows")) {
	            abstractServerInfos = new windowsserverinfo();
	            System.out.println("windows");
	        } else if (osName.startsWith("linux")) {
	            abstractServerInfos = new linuxserverInfo();
	            System.out.println("linux");
	        }else{
	        	 abstractServerInfos = new windowsserverinfo();
	        	 System.out.println("windows 10");
	        }
	        
	        result = abstractServerInfos.getServerInfos();
	        
	        model.addAttribute("server",result); 
	        return "serverinfo";
	     
	    }

	    /**
	     * 生成证书
	     * @author zifangsky
	     * @date 2018/4/26 13:13
	     * @since 1.0.0
	     * @param param 生成证书需要的参数，如：{"subject":"ccx-models","privateAlias":"privateKey","keyPass":"5T7Zz5Y0dJFcqTxvzkH5LDGJJSGMzQ","storePass":"3538cef8e7","licensePath":"C:/Users/zifangsky/Desktop/license.lic","privateKeysStorePath":"C:/Users/zifangsky/Desktop/privateKeys.keystore","issuedTime":"2018-04-26 14:48:12","expiryTime":"2018-12-31 00:00:00","consumerType":"User","consumerAmount":1,"description":"这是证书描述信息","licenseCheckModel":{"ipAddress":["192.168.245.1","10.0.5.22"],"macAddress":["00-50-56-C0-00-01","50-7B-9D-F9-18-41"],"cpuSerial":"BFEBFBFF000406E3","mainBoardSerial":"L1HF65E00X9"}}
	     * @return java.util.Map<java.lang.String,java.lang.Object>
	     * @throws IOException 
	     */
	    @RequestMapping(value = "/generateLicense",produces = {MediaType.APPLICATION_JSON_VALUE})
	    @ResponseBody
	    public Map<String,Object> generateLicense(@RequestBody(required = true) licenseparam param) throws IOException {
	    	
	    	System.out.println("generateLicense" + param);
	    	
	        Map<String,Object> resultMap = new HashMap<>(2);

	        if(StringUtils.isBlank(param.getLicensePath())){
	            param.setLicensePath(licensePath);
	        }

	        
	        System.out.println("generateLicense2");
	        
	       LicenseCreator licenseCreator = new LicenseCreator(param);
	        
	       //LicenseCreator licenseCreator = new LicenseCreator();
	        
	        
	        System.out.println("generateLicense3");
	        
	       boolean result = licenseCreator.generateLicense();
	    // boolean result = true;
	        if(result){
	            resultMap.put("result","ok");
	            resultMap.put("msg",param);
	        }else{
	            resultMap.put("result","error");
	            resultMap.put("msg","证书文件生成失败！");
	        }

	        return resultMap;
	    }


	
}




