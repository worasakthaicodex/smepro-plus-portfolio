package com.smepro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.smepro.dto.edataDTO;
import com.smepro.models.edata;
import com.smepro.models.map_key;

@Service
public class eformservice {
	
	
	
	
	
	
	public  String Mapcal(edata edata,long row) {
		
		   
		
		
	       String data = null;
			
			if(row == 1) {  data = edata.getData_value1(); }
			else if(row == 2) {  data = edata.getData_value2(); }
			else if(row == 3) {  data = edata.getData_value3(); }
			else if(row == 4) {  data = edata.getData_value4(); }
			else if(row == 5) {  data = edata.getData_value5(); }
			else if(row == 6) {  data = edata.getData_value6(); }
			else if(row == 7) {  data = edata.getData_value7(); }
			else if(row == 8) {  data = edata.getData_value8(); }
			else if(row == 9) {  data = edata.getData_value9(); }
			else if(row == 10) {  data = edata.getData_value10(); }
			else if(row == 11) {  data = edata.getData_value11(); }
			else if(row == 12) {  data = edata.getData_value12(); }
			else if(row == 13) {  data = edata.getData_value13(); }
			else if(row == 14) {  data = edata.getData_value14(); }
			else if(row == 15) {  data = edata.getData_value15(); }
			else if(row == 16) {  data = edata.getData_value16(); }
			else if(row == 17) {  data = edata.getData_value17(); }
			else if(row == 18) {  data = edata.getData_value18(); }
			else if(row == 19) {  data = edata.getData_value19(); }
			else if(row == 20) {  data = edata.getData_value20(); }

		
		    return data;
		}
	
	
	public  String getstock(edata edata,int no) {
		
	       String data = null;
			
			if(no == 1) {  data = edata.getData_value1(); }
			else if(no == 2) {  data = edata.getData_value2(); }
			else if(no == 3) {  data = edata.getData_value3(); }
			else if(no == 4) {  data = edata.getData_value4(); }
			else if(no == 5) {  data = edata.getData_value5(); }
			else if(no == 6) {  data = edata.getData_value6(); }
			else if(no == 7) {  data = edata.getData_value7(); }
			else if(no == 8) {  data = edata.getData_value8(); }
			else if(no == 9) {  data = edata.getData_value9(); }
			else if(no == 10) {  data = edata.getData_value10(); }

		    return data;
		}
		
	
	
	
	
	public  String getdata(edata edata,String header) {
		
       String data = null;
		
		if(myequals(edata.getData_title1(),header)) {  data = edata.getData_value1(); }
		else if(myequals(edata.getData_title2(),header)) {  data = edata.getData_value2(); }
		else if(myequals(edata.getData_title3(),header)) {  data = edata.getData_value3(); }
		else if(myequals(edata.getData_title4(),header)) {  data = edata.getData_value4(); }
		else if(myequals(edata.getData_title5(),header)) {  data = edata.getData_value5(); }
		else if(myequals(edata.getData_title6(),header)) {  data = edata.getData_value6(); }
		else if(myequals(edata.getData_title7(),header)) {  data = edata.getData_value7(); }
		else if(myequals(edata.getData_title8(),header)) {  data = edata.getData_value8(); }
		else if(myequals(edata.getData_title9(),header)) {  data = edata.getData_value9(); }
		else if(myequals(edata.getData_title10(),header)) {  data = edata.getData_value10(); }
		else if(myequals(edata.getData_title11(),header)) {  data = edata.getData_value11(); }
		else if(myequals(edata.getData_title12(),header)) {  data = edata.getData_value12(); }
		else if(myequals(edata.getData_title13(),header)) {  data = edata.getData_value13(); }
		else if(myequals(edata.getData_title14(),header)) {  data = edata.getData_value14(); }
		else if(myequals(edata.getData_title15(),header)) {  data = edata.getData_value15(); }
		else if(myequals(edata.getData_title16(),header)) {  data = edata.getData_value16(); }
		else if(myequals(edata.getData_title17(),header)) {  data = edata.getData_value17(); }
		else if(myequals(edata.getData_title18(),header)) {  data = edata.getData_value18(); }
		else if(myequals(edata.getData_title19(),header)) {  data = edata.getData_value19(); }
		else if(myequals(edata.getData_title20(),header)) {  data = edata.getData_value20(); }
		else if(("ผู้เพิ่ม").equals(header)) {  data = edata.getData_nameadd(); }
		else if(("ผู้อ้างอิง").equals(header)) {  data = edata.getData_namerefer(); }
		else if(("ผู้ตรวจ").equals(header)) {  data = edata.getLog_app(); }
		else if(("วันที่ตรวจ").equals(header)) {  data = edata.getReport_pn_datecheck(); }
		else if(("บันทึกผู้ตรวจ").equals(header)) {  data = edata.getLog_note(); }
		else if(("วันที่เพิ่ม").equals(header)) {  data = edata.getData_due(); }
	
	    return data;
	}
	

	
	public  String getdata(edata edata,edata edata2,String header) {
		
	       String data = null;
			
		   	if(myequals(edata.getData_title1(),header)) {  data = edata.getData_value1(); }
			else if(myequals(edata.getData_title2(),header)) {  data = edata.getData_value2(); }
			else if(myequals(edata.getData_title3(),header)) {  data = edata.getData_value3(); }
			else if(myequals(edata.getData_title4(),header)) {  data = edata.getData_value4(); }
			else if(myequals(edata.getData_title5(),header)) {  data = edata.getData_value5(); }
			else if(myequals(edata.getData_title6(),header)) {  data = edata.getData_value6(); }
			else if(myequals(edata.getData_title7(),header)) {  data = edata.getData_value7(); }
			else if(myequals(edata.getData_title8(),header)) {  data = edata.getData_value8(); }
			else if(myequals(edata.getData_title9(),header)) {  data = edata.getData_value9(); }
			else if(myequals(edata.getData_title10(),header)) {  data = edata.getData_value10(); }
			else if(myequals(edata.getData_title11(),header)) {  data = edata.getData_value11(); }
			else if(myequals(edata.getData_title12(),header)) {  data = edata.getData_value12(); }
			else if(myequals(edata.getData_title13(),header)) {  data = edata.getData_value13(); }
			else if(myequals(edata.getData_title14(),header)) {  data = edata.getData_value14(); }
			else if(myequals(edata.getData_title15(),header)) {  data = edata.getData_value15(); }
			else if(myequals(edata.getData_title16(),header)) {  data = edata.getData_value16(); }
			else if(myequals(edata.getData_title17(),header)) {  data = edata.getData_value17(); }
			else if(myequals(edata.getData_title18(),header)) {  data = edata.getData_value18(); }
			else if(myequals(edata.getData_title19(),header)) {  data = edata.getData_value19(); }
			else if(myequals(edata.getData_title20(),header)) {  data = edata.getData_value20(); }

			else if(myequals(edata2.getData_title1(),header)) {  data = edata2.getData_value1(); }
			else if(myequals(edata2.getData_title2(),header)) {  data = edata2.getData_value2(); }
			else if(myequals(edata2.getData_title3(),header)) {  data = edata2.getData_value3(); }
			else if(myequals(edata2.getData_title4(),header)) {  data = edata2.getData_value4(); }
			else if(myequals(edata2.getData_title5(),header)) {  data = edata2.getData_value5(); }
			else if(myequals(edata2.getData_title6(),header)) {  data = edata2.getData_value6(); }
			else if(myequals(edata2.getData_title7(),header)) {  data = edata2.getData_value7(); }
			else if(myequals(edata2.getData_title8(),header)) {  data = edata2.getData_value8(); }
			else if(myequals(edata2.getData_title9(),header)) {  data = edata2.getData_value9(); }
			else if(myequals(edata2.getData_title10(),header)) {  data = edata2.getData_value10(); }
			else if(myequals(edata2.getData_title11(),header)) {  data = edata2.getData_value11(); }
			else if(myequals(edata2.getData_title12(),header)) {  data = edata2.getData_value12(); }
			else if(myequals(edata2.getData_title13(),header)) {  data = edata2.getData_value13(); }
			else if(myequals(edata2.getData_title14(),header)) {  data = edata2.getData_value14(); }
			else if(myequals(edata2.getData_title15(),header)) {  data = edata2.getData_value15(); }
			else if(myequals(edata2.getData_title16(),header)) {  data = edata2.getData_value16(); }
			else if(myequals(edata2.getData_title17(),header)) {  data = edata2.getData_value17(); }
			else if(myequals(edata2.getData_title18(),header)) {  data = edata2.getData_value18(); }
			else if(myequals(edata2.getData_title19(),header)) {  data = edata2.getData_value19(); }
			else if(myequals(edata2.getData_title20(),header)) {  data = edata2.getData_value20(); }
			
			else if(("ผู้เพิ่ม").equals(header)) {  data = edata.getData_nameadd(); }
			else if(("ผู้อ้างอิง").equals(header)) {  data = edata.getData_namerefer(); }
			else if(("ผู้ตรวจ").equals(header)) {  data = edata.getLog_app(); }
			else if(("วันที่ตรวจ").equals(header)) {  data = edata.getReport_pn_datecheck(); }
			else if(("บันทึกผู้ตรวจ").equals(header)) {  data = edata.getLog_note(); }
			else if(("วันที่เพิ่ม").equals(header)) {  data = edata.getData_due(); }
			
			else if(("ผู้เพิ่ม(ข้อมูลที่ส่งมา)").equals(header)) {  data = edata2.getData_nameadd(); }
			else if(("ผู้อ้างอิง(ข้อมูลที่ส่งมา)").equals(header)) {  data = edata2.getData_namerefer(); }
			else if(("ผู้ตรวจ(ข้อมูลที่ส่งมา)").equals(header)) {  data = edata2.getLog_app(); }
			else if(("วันที่ตรวจ(ข้อมูลที่ส่งมา)").equals(header)) {  data = edata2.getReport_pn_datecheck(); }
			else if(("บันทึกผู้ตรวจ(ข้อมูลที่ส่งมา)").equals(header)) {  data = edata2.getLog_note(); }
			else if(("วันที่เพิ่ม(ข้อมูลที่ส่งมา)").equals(header)) {  data = edata2.getData_due(); }
	       
		    return data;
		}
		

    
		public  boolean myequals(Object object1, Object object2) {
		    if (object1 == object2) {
		        return true;
		    }
		    if ((object1 == null) || (object2 == null)) {
		        return false;
		    }
		    return object1.equals(object2);
		}
		
		
		
	
		public  edata SetDataPosition(edata edata,String value,int row) {
			
			
			System.out.println("-----SetDataPosition working------");
			System.out.println("row " + row);
			System.out.println("value " + value);

			
			   if(row ==1 ) {  edata.setData_value1(value);}
			   if(row ==2) {  edata.setData_value2(value);  }
			   if(row ==3) {  edata.setData_value3(value);  }
			   if(row ==4) {  edata.setData_value4(value);}
			   if(row ==5) {  edata.setData_value5(value);  }
			   if(row ==6) {  edata.setData_value6(value);  }
			   if(row ==7) {  edata.setData_value7(value); }
			   if(row ==8) {  edata.setData_value8(value); }
			   if(row ==9) {  edata.setData_value9(value);  }
			   if(row ==10) {  edata.setData_value10(value);  }
			   if(row ==11) {  edata.setData_value11(value); }
			   if(row ==12) {  edata.setData_value12(value); }
			   if(row ==13) {  edata.setData_value13(value);  }
			   if(row ==14) {  edata.setData_value14(value);  }
			   if(row ==15) {  edata.setData_value15(value);  }
			   if(row ==16) {  edata.setData_value16(value);  }
			   if(row ==17) {  edata.setData_value17(value);  }
			   if(row ==18) {  edata.setData_value18(value);  }
			   if(row ==19) {  edata.setData_value19(value);  }
			   if(row ==20) {  edata.setData_value20(value); }
			return  edata;
	
			
		}

		public  boolean CheckPostionRange(int Postion,map_key map_key) {
		
			
			System.out.println("Postion "+Postion);	
		 System.out.println("main1 "+map_key.getMap_key_main1());
		 System.out.println("main2 "+map_key.getMap_key_main2());
		 System.out.println("main3 "+map_key.getMap_key_main3());
		 System.out.println("main4 "+map_key.getMap_key_main4());
		 
		 
			if(map_key.getMap_key_main1() != null) {
				
				if(Postion == Integer.valueOf(map_key.getMap_key_main1())) {
					return true;
				}
			}
			
			else if(map_key.getMap_key_main2() != null) {
				
				if(Postion == Integer.valueOf(map_key.getMap_key_main2())) {
					return true;
				}
			}
			else if(map_key.getMap_key_main3() != null) {
						
						if(Postion == Integer.valueOf(map_key.getMap_key_main3())) {
							return true;
						}
					}
			else if(map_key.getMap_key_main4() != null) {
				
				if(Postion == Integer.valueOf(map_key.getMap_key_main4())) {
					return true;
				}
			}else {
				return  false;
			}
			
			return  false;
		}
		
		
		
		   public  <T> List<edata> filterUniqueValues(List<edata> edatas, Function<edata, T> keyExtractor) {
		        return edatas.stream()
		            .collect(Collectors.collectingAndThen(
		                Collectors.toMap(keyExtractor, Function.identity(), (e1, e2) -> e1),
		                map -> new ArrayList<>(map.values())
		            ));
		    }
}
