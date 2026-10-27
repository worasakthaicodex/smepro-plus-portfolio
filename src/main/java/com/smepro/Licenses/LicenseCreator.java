package com.smepro.Licenses;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;
import javax.security.auth.x500.X500Principal;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.prefs.Preferences;




public class LicenseCreator {

    private final static X500Principal DEFAULT_HOLDER_AND_ISSUER = new X500Principal("CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN");
    private licenseparam param;
    private File file;
    private String pathfile;
    
    
    public LicenseCreator(licenseparam param) throws IOException {
     this.param = param;
	 file = ResourceUtils.getFile("classpath:keystore/privateKeys.keystore");
	 pathfile = file.getCanonicalPath().replace("target\\classes","src/main/resources").replace("\\","/");
     }

    /**
     * 生成License证书
     * @author zifangsky
     * @date 2018/4/20 10:58
     * @since 1.0.0
     * @return boolean
     * @throws FileNotFoundException 
     */
    public boolean generateLicense() {
    	System.out.println("initLicenseParam run1"); 

    	
        try {
        	System.out.println("initLicenseParam run2");
            LicenseManager licenseManager = new CustomLicenseManager(initLicenseParam());
            LicenseContent licenseContent = initLicenseContent();
            
            File files = ResourceUtils.getFile("classpath:keystore/publicCerts.keystore");
            String licensePath = files.getCanonicalPath().replace("target\\classes","src/main/resources").replace("\\","/").replace("publicCerts.keystore",param.getLicensePath());
            System.out.println("licensePath" + licensePath);
            licenseManager.store(licenseContent,new File(licensePath));

            return true;
        }catch (Exception e){
        	 System.out.println("证书生成失败：{0}"+e);

            
            return false;
        }
    }

    /**
     * 初始化证书生成参数
     * @author zifangsky
     * @date 2018/4/20 10:56
     * @since 1.0.0
     * @return de.schlichtherle.license.LicenseParam
     */
    private LicenseParam initLicenseParam(){
    	
    	 System.out.println("initLicenseParam run" + pathfile);
    	 System.out.println("initLicenseParam run" + param.getPrivateAlias());
    	 System.out.println("initLicenseParam run" + param.getKeyPass());
    	 System.out.println("initLicenseParam run" + param.getStorePass());
    	 
        Preferences preferences = Preferences.userNodeForPackage(LicenseCreator.class);
     
        //设置对证书内容加密的秘钥
        CipherParam cipherParam = new DefaultCipherParam(param.getStorePass());

        KeyStoreParam privateStoreParam = new CustomKeyStoreParam(LicenseCreator.class,pathfile,param.getPrivateAlias(),param.getStorePass(),param.getKeyPass());
        LicenseParam licenseParam = new DefaultLicenseParam(param.getSubject(),preferences,privateStoreParam,cipherParam);
        return licenseParam;
    }

    /**
     * 设置证书生成正文信息
     * @author zifangsky
     * @date 2018/4/20 10:57
     * @since 1.0.0
     * @return de.schlichtherle.license.LicenseContent
     */
    private LicenseContent initLicenseContent(){
        LicenseContent licenseContent = new LicenseContent();
        licenseContent.setHolder(DEFAULT_HOLDER_AND_ISSUER);
        licenseContent.setIssuer(DEFAULT_HOLDER_AND_ISSUER);

        licenseContent.setSubject(param.getSubject());
        licenseContent.setIssued(param.getIssuedTime());
        licenseContent.setNotBefore(param.getIssuedTime());
        licenseContent.setNotAfter(param.getExpiryTime());
        licenseContent.setConsumerType(param.getConsumerType());
        licenseContent.setConsumerAmount(param.getConsumerAmount());
        licenseContent.setInfo(param.getDescription());
        
        
        System.out.println("initLicenseParam run2" + licenseContent.toString());
        
        
        //扩展校验服务器硬件信息
        licenseContent.setExtra(param.getLicense());
        return licenseContent;
    }


}
