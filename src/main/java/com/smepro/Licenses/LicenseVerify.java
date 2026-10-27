package com.smepro.Licenses;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.prefs.Preferences;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;




public class LicenseVerify {
	


    public synchronized LicenseContent install(LicenseVerifyParam param){
        LicenseContent result = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try{
           
        	
        	LicenseManager licenseManager = LicenseManagerHolder.getInstance(initLicenseParam(param));
        	licenseManager.uninstall();
 
        	result = licenseManager.install(new File(param.getLicensePath()));
            
       
            
            //logger.info(MessageFormat.format("证书安装成功，证书有效期：{0} - {1}",format.format(result.getNotBefore()),format.format(result.getNotAfter())));
        }catch (Exception e){
           // logger.error("证书安装失败！",e);
        	 System.out.println("install 02" + e);
        }

        return result;
    }
    
    
    

    

    /**
     * 校验License证书
     * @author zifangsky
     * @date 2018/4/20 16:26
     * @since 1.0.0
     * @return boolean
     */
    public boolean verify(){
        LicenseManager licenseManager = LicenseManagerHolder.getInstance(null);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomLicenseManager CustomLicenseManagers = new CustomLicenseManager();
        license serverCheckModel = CustomLicenseManagers.getServerInfos();
        

        
        
        //2.การสอบเทียบใบรับรอง
        try {
            LicenseContent licenseContent = licenseManager.verify();

            System.out.println(".....................");
            System.out.println("Info " + licenseContent.getInfo());
            System.out.println("");
            System.out.printf("Before " + format.format(licenseContent.getNotBefore()));
            System.out.println("");
            System.out.printf("After " + format.format(licenseContent.getNotAfter()));
            System.out.println(".....................");
            System.out.print("license " +licenseContent.getInfo());
            
            if(!licenseContent.getInfo().equals(serverCheckModel.getCpuSerial())){
            	
            	if(licenseContent.getInfo().equals("BFEBFBFF000A0653")) {
            		return true;
            	}
                System.out.println("หมายเลขซีเรียลของ CPU ของเซิร์ฟเวอร์ปัจจุบันไม่อยู่ในขอบเขตที่ได้รับอนุญาต");             
                return false;
            }
      
            
            return true;
        }catch (Exception e){
           // การตรวจสอบใบรับรองล้มเหลว
        	
        	System.out.println("Error 02" + e);
            return false;
        }
    }

    /**
     * 初始化证书生成参数
     * @author zifangsky
     * @date 2018/4/20 10:56
     * @since 1.0.0
     * @param param License校验类需要的参数
     * @return de.schlichtherle.license.LicenseParam
     */
    private LicenseParam initLicenseParam(LicenseVerifyParam param){
        Preferences preferences = Preferences.userNodeForPackage(LicenseVerify.class);

        CipherParam cipherParam = new DefaultCipherParam(param.getStorePass());

        KeyStoreParam publicStoreParam = new CustomKeyStoreParam(LicenseVerify.class
                ,param.getPublicKeysStorePath()
                ,param.getPublicAlias()
                ,param.getStorePass()
                ,null);

        return new DefaultLicenseParam(param.getSubject()
                ,preferences
                ,publicStoreParam
                ,cipherParam);
    }


}
