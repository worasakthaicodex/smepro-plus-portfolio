package com.smepro.Licenses;
import java.io.Serializable;
import java.util.List;



public class license implements Serializable{

	
	 private static final long serialVersionUID = 8600137500316662317L;
	 private List<String> ipAddress;
	 private List<String> macAddress;
	 private String cpuSerial;
	 private String mainBoardSerial;

	 
	 public List<String> getIpAddress() {
		return ipAddress;
	}
	 
	public void setIpAddress(List<String> ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public List<String> getMacAddress() {
		return macAddress;
	}
	
	public void setMacAddress(List<String> macAddress) {
		this.macAddress = macAddress;
	}
	
	public String getCpuSerial() {
		return cpuSerial;
	}

	public void setCpuSerial(String cpuSerial) {
		this.cpuSerial = cpuSerial;
	}

	public String getMainBoardSerial() {
		return mainBoardSerial;
	}

	public void setMainBoardSerial(String mainBoardSerial) {
		this.mainBoardSerial = mainBoardSerial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


		@Override
		public String toString() {
			return String.format("license [ipAddress=%s, macAddress=%s, cpuSerial=%s, mainBoardSerial=%s]", ipAddress,
					macAddress, cpuSerial, mainBoardSerial);
		}


	
}
