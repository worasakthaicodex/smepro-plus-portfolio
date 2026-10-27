package com.smepro.Licenses;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;



public abstract  class mainserverinfo {
	

    /**
     * ข้อมูล Server
     * @date 2022/4/23 14:23
     * @since 1.0.0
     * @return demo.LicenseCheckModel
     */
    public license getServerInfos(){
    	license result = new license();

    	 System.out.println("Run ..... getServerInfos");
    	
        try {
            result.setIpAddress(this.getIpAddress());
            result.setMacAddress(this.getMacAddress());
            result.setCpuSerial(this.getCPUSerial());
            result.setMainBoardSerial(this.getMainBoardSerial());
        }catch (Exception e){
            System.out.println("ไม่สามารถรับข้อมูลฮาร์ดแวร์ของเซิร์ฟเวอร์");
        }
        System.out.println("result" + result);
        return result;
    }

    /**
     * ดึงค่า IP
     * @date 2022/4/23 11:32
     * @since 1.0.0
     * @return java.util.List<java.lang.String>
     */
    protected abstract List<String> getIpAddress() throws Exception;

    /**
     * ดึงค่า Mac Address
     * @date 2022/4/23 11:32
     * @since 1.0.0
     * @return java.util.List<java.lang.String>
     */
    protected abstract List<String> getMacAddress() throws Exception;

    /**
     *  ดึงค่า Serial CPU
     * @date 2022/4/23 11:35
     * @since 1.0.0
     * @return java.lang.String
     */
    protected abstract String getCPUSerial() throws Exception;

    /**
     *  ดึงค่า Serial MainBoard
     * @date 2022/4/23 11:35
     * @since 1.0.0
     * @return java.lang.String
     */
    protected abstract String getMainBoardSerial() throws Exception;

    /**
     * ดึงค่า InetAddress
     * @date 2022/4/23 17:38
     * @since 1.0.0
     * @return java.util.List<java.net.InetAddress>
     */
    protected List<InetAddress> getLocalAllInetAddress() throws Exception {
        List<InetAddress> result = new ArrayList<>(4);

        // สำรวจอินเทอร์เฟซเครือข่ายทั้งหมด
        for (Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
            NetworkInterface iface = (NetworkInterface) networkInterfaces.nextElement();
            //สำรวจ IP ภายใต้อินเทอร์เฟซทั้งหมด
            for (Enumeration inetAddresses = iface.getInetAddresses(); inetAddresses.hasMoreElements(); ) {
                InetAddress inetAddr = (InetAddress) inetAddresses.nextElement();

           
                if(!inetAddr.isLoopbackAddress() 
                        && !inetAddr.isLinkLocalAddress() && !inetAddr.isMulticastAddress()){
                    result.add(inetAddr);
                }
            }
        }

        return result;
    }

    /**
     * รับที่อยู่ Mac Address ของอินเทอร์เฟซเครือข่าย
     * @date 2022/4/23 18:08
     * @since 1.0.0
     * @param
     * @return void
     */
    protected String getMacByInetAddress(InetAddress inetAddr){
        try {
            byte[] mac = NetworkInterface.getByInetAddress(inetAddr).getHardwareAddress();
            StringBuffer stringBuffer = new StringBuffer();

            for(int i=0;i<mac.length;i++){
                if(i != 0) {
                    stringBuffer.append("-");
                }

                String temp = Integer.toHexString(mac[i] & 0xff);
                if(temp.length() == 1){
                    stringBuffer.append("0" + temp);
                }else{
                    stringBuffer.append(temp);
                }
            }

            return stringBuffer.toString().toUpperCase();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }


}
