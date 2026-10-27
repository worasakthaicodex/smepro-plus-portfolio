package com.smepro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smepro.dao.IdocDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.reportDAO;
import com.smepro.models.Measurement_rules;
import com.smepro.models.Measurement_values;
import com.smepro.models.Segmentation;
import com.smepro.models.UseExtension;
import com.smepro.models.edata;

@Controller
public class ExtensionController {

	private  edataDAO EdataDAO;
	private  reportDAO ReportDAO;
	
    @Autowired
    public void setMyRepository(edataDAO EdataDAO,reportDAO ReportDAO) {
    	this.EdataDAO = EdataDAO;
    	this.ReportDAO = ReportDAO;
    }
	
	
    @RequestMapping(value="/ResultsSegmentation-extension/{process_id}/{segmentation_id}/{data_id}", method = RequestMethod.GET)
    public String ResultsSegmentation(ModelMap model, @PathVariable int process_id, @PathVariable int segmentation_id, @PathVariable int data_id) {     
        
        Segmentation Segmentation = EdataDAO.getSegmentationById(segmentation_id);
        model.addAttribute("Segmentation", Segmentation);
        
        List<Measurement_rules> rulesList = EdataDAO.getAllMeasurementRulesByThisProcess(process_id);
        rulesList.forEach(row->{
            Measurement_values values = ReportDAO.getMeasurement_values(row.getId(), data_id);
            row.setValues(values);
        });
        model.addAttribute("rulesList", rulesList);
        
        edata edatas = EdataDAO.getbyid(data_id);
        model.addAttribute("edatas", edatas);

        int countScheduledCalls = EdataDAO.countScheduledCalls(Segmentation.getSegmentation_member_id(), Segmentation.getDate_add());
        model.addAttribute("countScheduledCalls", countScheduledCalls);
        
     // 🚀 ส่วนที่ดึงค่าคอนฟิกแบบ Dynamic
        UseExtension use_extension = EdataDAO.getActiveSegmentIssueConfig();
        
        String userSelectedColumn = "data_value1"; // ค่า Default กรณีหาคอนฟิกไม่เจอ
        int problemExtensionId = 0;

        if (use_extension != null) {
            // สร้างชื่อคอลัมน์ เช่น "data_value" + 3 => "data_value3"
            userSelectedColumn = "data_value" + use_extension.getSegment_issue_column();
            problemExtensionId = use_extension.getId();
        }
        Map<String, Integer> problemStats = EdataDAO.getProblemStatistics(data_id, problemExtensionId, userSelectedColumn);
        model.addAttribute("problemStats", problemStats);

        // =========================================================
        // 🚀 STEP สรุปผล: Pack ข้อมูลทุกอย่างเป็น JSON เพื่ออัปเดตกลับไปที่ Segmentation
        // =========================================================
        try {
            Map<String, Object> summaryPayload = new HashMap<>();

            // 1. Pack ข้อมูล KPI (วนลูปเก็บ ชื่อ, เป้า, ทำได้จริง)
            List<Map<String, Object>> kpiMetrics = new ArrayList<>();
            for (Measurement_rules rule : rulesList) {
                Map<String, Object> kpi = new HashMap<>();
                kpi.put("title", rule.getTitle()); // ชื่อกราฟ/ผล
                kpi.put("actual", rule.getValues() != null ? rule.getValues().getCurrent_value() : 0);
                
                // หาค่า Target (เทียบเท่า edatas[arr_ex] ใน JSP)
                // *หมายเหตุ: คุณต้องมี method ดึงค่า target จาก edatas ตามชื่อ Field ใน Java
                // สมมติว่าดึงมาได้เก็บไว้ในตัวแปร targetValue
                double targetValue = getTargetValueFromEdata(edatas, rule.getThis_target()); 
                kpi.put("target", targetValue);
                
                kpiMetrics.add(kpi);
            }
            summaryPayload.put("kpi_metrics", kpiMetrics);

            // 2. Pack ข้อมูลแผนรอโทร
            summaryPayload.put("pending_calls", countScheduledCalls);

            // 3. Pack ข้อมูลปัญหาแต่ละด้าน
            summaryPayload.put("problem_stats", problemStats);

            // แปลง Map เป็น JSON String
            ObjectMapper mapper = new ObjectMapper();
            String jsonSummary = mapper.writeValueAsString(summaryPayload);

            // 4. บันทึกกลับลง Database
            EdataDAO.updateSegmentationSummary(segmentation_id, jsonSummary);

            System.out.println("✅ Saved Summary JSON: " + jsonSummary);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error saving summary to Segmentation");
        }
        // =========================================================

        return "app/Segmentation/Results";
    }

    // Helper Method สมมติ สำหรับดึงค่า Target จาก Object edata ด้วยชื่อ Field (String)
    private double getTargetValueFromEdata(edata edatas, String targetFieldName) {
        try {
            // ใช้ Reflection ดึงค่าจากตัวแปรใน edata ตามชื่อที่อยู่ใน rule.getThis_target()
            java.lang.reflect.Field field = edatas.getClass().getDeclaredField(targetFieldName);
            field.setAccessible(true);
            Object value = field.get(edatas);
            return value != null ? Double.parseDouble(value.toString()) : 0;
        } catch (Exception e) {
            return 0; // ถ้าหาไม่เจอหรือ Error ให้คืนค่า 0
        }
    }
	
}
