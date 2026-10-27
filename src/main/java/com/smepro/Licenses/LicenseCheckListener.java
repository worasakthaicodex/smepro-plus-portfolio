package com.smepro.Licenses;

import java.io.File;
import java.io.IOException;

import org.springframework.util.ResourceUtils;


import com.smepro.dto.mainsettingDTO;

public class LicenseCheckListener {

	
	
	mainsettingDTO system =new mainsettingDTO();
	public void installLicense() throws IOException {
		
		
		 File file2 = ResourceUtils.getFile("classpath:keystore/publicCerts.keystore");
		

		 
		 
		 if(system.getLicense().equals("license.lic")) {
		 
			 String licensePath = file2.getCanonicalPath().replace("target\\classes","src/main/resources").replace("\\","/").replace("publicCerts.keystore","license.lic");
			 String publicKeysStorePath = file2.getCanonicalPath().replace("target\\classes","src/main/resources").replace("\\","/").replace("\\","/");
			 LicenseVerifyParam param = new LicenseVerifyParam();
	         param.setSubject("amptron");
	         param.setPublicAlias("publicCert");
	         param.setStorePass("public_passwordAmptron07");
	         param.setLicensePath(licensePath);
	         param.setPublicKeysStorePath(publicKeysStorePath);
	         LicenseVerify licenseVerify = new LicenseVerify();
	        licenseVerify.install(param);

	         
	         System.out.println("licensePath " + licensePath);
	         System.out.println("publicKeysStorePath " + publicKeysStorePath);
		 }
         
	}
		 
		 

	
}
