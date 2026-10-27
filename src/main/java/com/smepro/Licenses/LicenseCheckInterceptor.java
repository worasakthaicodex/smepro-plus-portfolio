package com.smepro.Licenses;



public class LicenseCheckInterceptor {


	    public boolean preHandle() {
	        LicenseVerify licenseVerify = new LicenseVerify();

	        System.out.println("verifyResult :  run...");
	        boolean verifyResult = licenseVerify.verify();

	        if(verifyResult){
	        	System.out.println("verifyResult :  true..");
	            return true;
	        }else{
	        	System.out.println("verifyResult :  false..");
	            //response.setCharacterEncoding("utf-8");
	           // Map<String,String> result = new HashMap<>(1);
	          //  result.put("result","ใบรับรองของคุณไม่ถูกต้อง โปรดตรวจสอบว่าเซิร์ฟเวอร์ได้รับอนุญาตหรือสมัครใบรับรองใหม่！");

	           // response.getWriter().write(JSON.toJSONString(result));
	        	
	            return false;
	        }


	
	 }
}
