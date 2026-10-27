package com.smepro.Licenses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.smepro.dto.mainsettingDTO;



public class issuelicense {

	

	mainsettingDTO system =new mainsettingDTO();
	public boolean issue(HttpServletRequest request,String token) throws IOException {
		
		System.out.println("issue  worked system");
		
	
		String url = request.getServerName()+":"+request.getServerPort();                    		   
		URL obj = new URL(system.getMainlink()+"/generateLicense?token="+token+"&&url="+url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent","Mozilla/5.0");
		System.out.println(request.getServerName());
		
		
		
		
	
		int responseCode = con.getResponseCode();
		String  responses = con.getResponseMessage();
		int status2 = 0;
			StringBuffer responsebody = new StringBuffer();
			
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
	
	
			while ((inputLine = in.readLine()) != null) {
				responsebody.append(inputLine);
			}
			in.close();
	
		
		} else {
			System.out.println("GET request not worked");
		}
		
		 /**
		  * กรองข้อมูลที่ต้องการ
		  */
		
		if(responsebody.toString() != null) {
		
			
			
			
			JSONObject mainObject = new JSONObject( responsebody.toString() );
						
			 if(!system.getLicense().equals(mainObject.getString("licensePath"))) {
				 
						 															
					
					JSONArray  ip = mainObject.getJSONObject("license").getJSONArray("ipAddress");
					JSONArray  mac = mainObject.getJSONObject("license").getJSONArray("macAddress");
					String  cpuSerial = mainObject.getJSONObject("license").getString("cpuSerial");
					String  mainBoardSerial = mainObject.getJSONObject("license").getString("mainBoardSerial");
					
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("en", "EN"));
					Date issued = null;
					Date expiry = null;
					
					try {
						issued = formatter.parse(mainObject.getString("issuedTime"));
						expiry = formatter.parse(mainObject.getString("expiryTime"));
					} catch (JSONException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
					List<String> ipAddress = new ArrayList<String>();
					for(int i = 0; i < ip.length(); i++){
						ipAddress.add(ip.getString(i));
					}
					List<String> macAddress = new ArrayList<String>();
					for(int i = 0; i < mac.length(); i++){
						macAddress.add(mac.getString(i));
					}	
					
					license licenses = new license();	
					licenses.setCpuSerial(cpuSerial);
					licenses.setIpAddress(ipAddress);
					licenses.setMainBoardSerial(mainBoardSerial);
					licenses.setMacAddress(macAddress);
					
					
					licenseparam  mainlicenses = new licenseparam();
					mainlicenses.setSubject(mainObject.getString("subject"));
					mainlicenses.setPrivateAlias(mainObject.getString("privateAlias"));
					mainlicenses.setKeyPass(mainObject.getString("keyPass"));
					mainlicenses.setStorePass(mainObject.getString("storePass"));
					mainlicenses.setLicensePath(mainObject.getString("licensePath"));
					mainlicenses.setPrivateKeysStorePath(mainObject.getString("privateKeysStorePath"));
					mainlicenses.setIssuedTime(issued);
					mainlicenses.setExpiryTime(expiry);
					mainlicenses.setConsumerType(mainObject.getString("consumerType"));
					mainlicenses.setDescription(cpuSerial);
					mainlicenses.setConsumerAmount(mainObject.getInt("consumerAmount"));
					mainlicenses.setLicense(licenses);
				

					system.setLicense(mainlicenses.getSubject());
					
					LicenseCreator licenseCreator = new LicenseCreator(mainlicenses);
					boolean result = licenseCreator.generateLicense();

					
					if(result) {
						system.setLicense(mainlicenses.getLicensePath());
						LicenseCheckListener License  = new LicenseCheckListener();
						License.installLicense();
						return true;
					}
					
					
			 }
			 
			 
		 }
		
		return false;
	}
	
	
	
	
}
