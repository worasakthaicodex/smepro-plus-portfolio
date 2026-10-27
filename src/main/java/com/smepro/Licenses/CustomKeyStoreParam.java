package com.smepro.Licenses;

import de.schlichtherle.license.AbstractKeyStoreParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CustomKeyStoreParam extends AbstractKeyStoreParam {

	 private String storePath;
	    private String alias;
	    private String storePwd;
	    private String keyPwd;

	    public CustomKeyStoreParam(Class clazz, String resource,String alias,String storePwd,String keyPwd) {
	        super(clazz, resource);
	        this.storePath = resource;
	        this.alias = alias;
	        this.storePwd = storePwd;
	        this.keyPwd = keyPwd;
	    }


	    @Override
	    public String getAlias() {
	        return alias;
	    }

	    @Override
	    public String getStorePwd() {
	        return storePwd;
	    }

	    @Override
	    public String getKeyPwd() {
	        return keyPwd;
	    }

	    /**
	     * 复写de.schlichtherle.license.AbstractKeyStoreParam的getStream()方法<br/>
	     * 用于将公私钥存储文件存放到其他磁盘位置而不是项目中
	     * @author zifangsky
	     * @date 2018/4/26 18:28
	     * @since 1.0.0
	     * @param
	     * @return java.io.InputStream
	     */
	    @Override
	    public InputStream getStream() throws IOException {
	        final InputStream in = new FileInputStream(new File(storePath));
	        if (null == in){
	            throw new FileNotFoundException(storePath);
	        }

	        return in;
	    }
	
	
}
