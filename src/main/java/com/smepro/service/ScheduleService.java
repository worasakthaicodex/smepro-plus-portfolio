package com.smepro.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.smepro.dao.App_Component;
import com.smepro.entity.CampaignPromotion;
import com.smepro.models.Calendar;
import com.smepro.models.Segmentation;
import com.smepro.models.dataset_data;
import com.smepro.models.product_master;
import com.smepro.models.registers;

@Service
public class ScheduleService {
	

	
	public Map<Integer, Map<Integer, Map<Integer, List<Map<String, String>>>>> createCalendarDataJson(List<Calendar> calendars) {

	    Map<Integer, Map<Integer, Map<Integer, List<Map<String, String>>>>> data = new LinkedHashMap<>();

	    for (Calendar c : calendars) {

	        int year = c.getYear();
	        int month = c.getMonth();
	        int day = c.getDay();

	        // เตรียมชั้น year
	        data.putIfAbsent(year, new LinkedHashMap<>());

	        // เตรียมชั้น month
	        data.get(year).putIfAbsent(month, new LinkedHashMap<>());

	        // เตรียมชั้น day
	        data.get(year).get(month).putIfAbsent(day, new ArrayList<>());

	        // event object
	        Map<String, String> event = new LinkedHashMap<>();
	        event.put("startTime", c.getStartTime());
	        event.put("endTime", c.getEndTime());
	        event.put("text", c.getEvent());
	        event.put("by", c.getBy_name());
	        event.put("to", c.getTo_name());
	        event.put("status", String.valueOf(c.getStatus()) );
	        event.put("edata_id", String.valueOf(c.getEform_id()) );
	        data.get(year).get(month).get(day).add(event);
	    }

	    return data;
	}

	
	
	
	public List<Map<String, String>> createRegisterDataJson(List<registers> registersList) {

	    // สร้าง List เพื่อเก็บข้อมูลแต่ละแถว
	    List<Map<String, String>> data = new ArrayList<>();

	    for (registers reg : registersList) {
	        Map<String, String> item = new LinkedHashMap<>();

	        // ป้องกันค่า null ก่อนนำมาต่อ String
	        String code = reg.getRegister_code() != null ? reg.getRegister_code() : "";
	        String dateRun = reg.getRegister_daterun() != null ? reg.getRegister_daterun() : "";
	        
	        // ประกอบร่าง optVal1 (เช่น CUST202403-1)
	        String optVal1 = code + dateRun + "-" + reg.getRegister_id();
	        
	        // ดึงค่า optVal2
	        String optVal2 = reg.getRegister_name() != null ? reg.getRegister_name() : "";

	        // ใส่ค่าลง Map
	        item.put("optVal1", optVal1);
	        item.put("optVal2", optVal2);
	        
	        // 💡 แถม: ถ้าจะเอาไปใช้กับบาง Frontend Framework (เช่น Select2, Vue, React) 
	        // นิยมใช้ key ว่า "id" กับ "text" หรือ "value" กับ "label" สามารถใส่เพิ่มเผื่อไว้ได้เลยครับ
	        item.put("value", optVal1);
	        item.put("text", optVal2);

	        data.add(item);
	    }

	    return data;
	}
	
	
	

	
	public List<Map<String, String>> createProduct_masterJson(List<product_master> registersList) {

	    // สร้าง List เพื่อเก็บข้อมูลแต่ละแถว
	    List<Map<String, String>> data = new ArrayList<>();

	    for (product_master reg : registersList) {
	        Map<String, String> item = new LinkedHashMap<>();

	        // ป้องกันค่า null ก่อนนำมาต่อ String
	        String code = reg.getProduct_code() != null ? reg.getProduct_code() : "";
	        String dateRun = reg.getProduct_daterun() != null ? reg.getProduct_daterun() : "";
	        
	        // ประกอบร่าง optVal1 (เช่น CUST202403-1)
	        String optVal1 = code + dateRun + "-" + reg.getProduct_id();
	        
	        // ดึงค่า optVal2
	        String optVal2 = reg.getProduct_names() != null ? reg.getProduct_names() : "";

	        // ใส่ค่าลง Map
	        item.put("optVal1", optVal1);
	        item.put("optVal2", optVal2);
	        
	        // 💡 แถม: ถ้าจะเอาไปใช้กับบาง Frontend Framework (เช่น Select2, Vue, React) 
	        // นิยมใช้ key ว่า "id" กับ "text" หรือ "value" กับ "label" สามารถใส่เพิ่มเผื่อไว้ได้เลยครับ
	        item.put("value", optVal1);
	        item.put("text", optVal2);

	        data.add(item);
	    }

	    return data;
	}
	// 2. ส่วน Method จัดการข้อมูล (ประกอบ String ให้เสร็จ)
	public List<Map<String, String>> createSegmentationJson(List<Segmentation> segmentList) {
	    List<Map<String, String>> data = new ArrayList<>();

	    for (Segmentation seg : segmentList) {
	        Map<String, String> item = new LinkedHashMap<>();

	        String id = String.valueOf(seg.getId()); 
	        String name = seg.getName() != null ? seg.getName() : "";
	        String dateAdd = seg.getDate_add() != null ? seg.getDate_add() : ""; 
	        String totalData = String.valueOf(seg.getTotal_data_select());

	        // ส่ง "value" และ "text" ให้ TomSelect เอาไปใช้ได้เลยตรงๆ
	        item.put("value", id);
	        
	        // ประกอบร่างให้ออกมาเป็น เช่น "กลุ่ม VIP 2024-03-15 (จำนวน: 150 รายการ)"
	        item.put("text", name + " " + dateAdd + " (จำนวน: " + totalData + " รายการ)");

	        data.add(item);
	    }
	    return data;
	}
	    
	public List<Map<String, String>> createPromotionJson(List<CampaignPromotion> promotionList) {
	    List<Map<String, String>> data = new ArrayList<>();

	    for (CampaignPromotion promo : promotionList) {
	        Map<String, String> item = new LinkedHashMap<>();

	        // 1. จัดการ รหัส และ ชื่อสิทธิ์ (ป้องกันค่า null)
	        String id = String.valueOf(promo.getId());
	        String name = promo.getCampaignName() != null ? promo.getCampaignName() : "ไม่มีชื่อแคมเปญ";

	        // 2. คำนวณสิทธิ์คงเหลือ (เป้าหมาย - ที่ใช้ไปแล้ว)
	        int target = promo.getTargetRedemptions() != null ? promo.getTargetRedemptions() : 0;
	        int actual = promo.getActualRedemptions() != null ? promo.getActualRedemptions() : 0;
	        int remaining = target - actual;

	        // ในระบบ ERP ที่ดี ควรป้องกันตัวเลขติดลบ กรณีระบบปล่อยให้ใช้สิทธิ์เกินไปแล้ว
	        if (remaining < 0) {
	            remaining = 0;
	        }

	        // 3. กำหนดค่าสำหรับส่งให้หน้าจอ (value และ text)
	        item.put("value", id);
	        
	        // ประกอบร่างให้ออกมาเป็น เช่น "[1] โปรโมชั่นลูกค้าใหม่ (คงเหลือ: 50 สิทธิ์)"
	        item.put("text",  name + " (คงเหลือ: " + remaining + " สิทธิ์)");

	        data.add(item);
	    }
	    
	    return data;
	}
	public List<Map<String, String>> createDataset_data(List<dataset_data> datasetList) {

	    // สร้าง List เพื่อเก็บข้อมูลแต่ละแถว
	    List<Map<String, String>> data = new ArrayList<>();

	    for (dataset_data reg : datasetList) {
	        Map<String, String> item = new LinkedHashMap<>();

	        // ป้องกันค่า null ก่อนนำมาใช้งาน
	        String title = reg.getDataset_data_title() != null ? reg.getDataset_data_title() : "";
	        
	        // เนื่องจากเป็นข้อมูลที่อยู่ เราเลยใช้ title เป็นทั้ง Value และ Text ได้เลย
	        item.put("dataset_data_title", title); // เผื่อเอาไปใช้อ้างอิงชื่อคอลัมน์เดิม
	        
	        // ใส่ optVal1 และ optVal2 ให้โครงสร้างเหมือนตัว Product
	        item.put("optVal1", title);
	        item.put("optVal2", title);
	        
	        // 💡 แถม: ใส่ value กับ text เพื่อให้ TomSelect หรือ Frontend เอาไปใช้งานได้ทันที
	        item.put("value", title);
	        item.put("text", title);

	        data.add(item);
	    }

	    return data;
	}
	
	
}
