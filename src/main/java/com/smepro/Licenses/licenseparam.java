package com.smepro.Licenses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;


public class licenseparam implements Serializable {

	
	private static final long serialVersionUID = -7793154252684580872L;
    private String subject;
    private String privateAlias;
    private String keyPass;
    private String storePass;
    private String licensePath;
    private String privateKeysStorePath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issuedTime = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiryTime;
    private String consumerType;
    private Integer consumerAmount = 1;
    private String description;
    private license license;



    public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPrivateAlias() {
		return privateAlias;
	}

	public void setPrivateAlias(String privateAlias) {
		this.privateAlias = privateAlias;
	}

	public String getKeyPass() {
		return keyPass;
	}

	public void setKeyPass(String keyPass) {
		this.keyPass = keyPass;
	}

	public String getStorePass() {
		return storePass;
	}

	public void setStorePass(String storePass) {
		this.storePass = storePass;
	}

	public String getLicensePath() {
		return licensePath;
	}

	public void setLicensePath(String licensePath) {
		this.licensePath = licensePath;
	}

	public String getPrivateKeysStorePath() {
		return privateKeysStorePath;
	}

	public void setPrivateKeysStorePath(String privateKeysStorePath) {
		this.privateKeysStorePath = privateKeysStorePath;
	}

	public Date getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(String consumerType) {
		this.consumerType = consumerType;
	}

	public Integer getConsumerAmount() {
		return consumerAmount;
	}

	public void setConsumerAmount(Integer consumerAmount) {
		this.consumerAmount = consumerAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public license getLicense() {
		return license;
	}

	public void setLicense(license license) {
		this.license = license;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
    public String toString() {
        return "LicenseCreatorParam{" +
                "subject='" + subject + '\'' +
                ", privateAlias='" + privateAlias + '\'' +
                ", keyPass='" + keyPass + '\'' +
                ", storePass='" + storePass + '\'' +
                ", licensePath='" + licensePath + '\'' +
                ", privateKeysStorePath='" + privateKeysStorePath + '\'' +
                ", issuedTime=" + issuedTime +
                ", expiryTime=" + expiryTime +
                ", consumerType='" + consumerType + '\'' +
                ", consumerAmount=" + consumerAmount +
                ", description='" + description + '\'' +
                ", license=" + license +
                '}';
    }




}
