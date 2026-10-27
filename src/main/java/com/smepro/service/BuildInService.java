package com.smepro.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.IdocDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.reportDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.entity.CampaignPromotion;
import com.smepro.entity.sales_followup;
import com.smepro.models.BuildIn;
import com.smepro.models.Calendar;
import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Idoc;
import com.smepro.models.Measurement_rules;
import com.smepro.models.Measurement_values;
import com.smepro.models.Segmentation;
import com.smepro.models.edata;
import com.smepro.models.flow;
import com.smepro.models.product_master;
import com.smepro.models.registers;
import com.smepro.models.user;
import com.smepro.repository.CampaignPromotionRepository;
import com.smepro.repository.Sales_followupRepository;

@Service
public class BuildInService {

	
	private reportDAO ReportDAO;
	private edataDAO EdataDAO;
	private IdocDAO idocDAO;
	private userDAO UserDAO;
	private  Sales_followupRepository Sales_followup;
	private upload_pnDAO Upload_pnDAO;
	private CampaignPromotionRepository campaignPromotionRepository;
	
	 
	
	 @Autowired
	 private void setMyRepository(
			 reportDAO ReportDAO,
			 edataDAO EdataDAO,
			 IdocDAO idocDAO,
			 userDAO UserDAO,
			  Sales_followupRepository Sales_followup,
			  upload_pnDAO Upload_pnDAO,
			CampaignPromotionRepository campaignPromotionRepository
			  
			 ) {
	        	this.ReportDAO = ReportDAO;
	        	this.EdataDAO = EdataDAO;
	        	this.idocDAO = idocDAO;
	        	this.UserDAO = UserDAO;
	        	this.Sales_followup = Sales_followup;
	        	this.Upload_pnDAO = Upload_pnDAO;
	           	this.campaignPromotionRepository = campaignPromotionRepository;
	 		}
	
	 

	 
	 
	 
	 public void executeBuildIn(edata data) {

		    System.out.println(" executeBuildIn data " + data.getProcesses_id());
		    
		    // 1. ดึง Config Job ทั้งหมด
		    List<BuildIn> buildinall = ReportDAO.getAllBuildInByPprocessIdAndManagementId(data.getProcesses_id());
		    
		    System.out.println(" buildinall " + (buildinall != null ? buildinall.size() : 0));
		    
		    if (buildinall == null || buildinall.isEmpty()) {
		        return;
		    }

		    // 2. Prepare Context (ดึงข้อมูลหลักครั้งเดียว)
		    BuildInContext ctx = new BuildInContext(data.getData_id());
		    
		    if (ctx.workflow == null) {
		        return; 
		    }

		    // 3. Prepare Report Buffer (Map เก็บ Object Report แยกตะกร้าตาม id_refer)
		    Map<Integer, EdataReportbuildIn> reportBuffer = new HashMap<>();

		    // 4. Start Loop Processing
		    for (BuildIn config : buildinall) {

		        // 4.1 ตรวจสอบเงื่อนไข
		        if (!isPassCondition(ctx.workflow, config)) {
		            continue;
		        }

		        int currentReferId = config.getId_refer(); // จะได้ค่าเช่น 4 หรือ 6

		        // 4.2 ล้วงหา Report Object จาก Buffer ก่อน
		        EdataReportbuildIn report = reportBuffer.get(currentReferId);

		        // 4.3 ถ้ายังไม่มีใน Buffer (เพิ่งเคยรันเจอ id_refer นี้ครั้งแรก)
		        if (report == null) {
		            // ลองหาจาก Database ดูก่อนเผื่อเคยมีคนบันทึกไว้แล้ว
		            report = ReportDAO.getedataReportBuildin(currentReferId, data.getData_id());
		            
		            // ถ้าใน Database ก็ยังไม่มีอีก ให้สร้าง Object เปล่าขึ้นมารอเลย
		            if (report == null) {
		                report = new EdataReportbuildIn();
		                report.setBuildin_id_refer(currentReferId); // เซ็ต FK ไปหาตัวแม่ (เช่น 4 หรือ 6)
		                report.setBuildin_edata(data.getData_id()); // เซ็ต FK ไปหาเอกสาร (edata)
		            }
		            
		            // 🔥 หัวใจสำคัญ: เอา Object ที่ได้ยัดเก็บไว้ใน Buffer 
		            // เพื่อที่ลูปรอบหน้า ถ้าเจอ id_refer เบอร์เดิม มันจะได้เอาตัวนี้ไปเติมคอลัมน์ต่อได้เลย
		            reportBuffer.put(currentReferId, report);
		        }

		        // 4.4 ประมวลผลหาค่า Value (ส่ง Context เข้าไป)
		        String value = runJobOptimized(data, config, ctx);

		        // 4.5 Set ค่าลง Column (ข้อมูลจะสะสมรวมกันอยู่ใน Object ภายใน Buffer)
		        if (value != null) {
		            fillReportColumn(report, config.getTocolumn(), value);
		        }
		    }

		    // 5. Batch Save (เซฟรวดเดียวตอนจบ)
		    if (!reportBuffer.isEmpty()) {
		        // วนเอา Object ทุกก้อนใน Buffer (เช่น ก้อนของ 4 และก้อนของ 6) ส่งไปบันทึก
		        for (EdataReportbuildIn finalReport : reportBuffer.values()) {
		            ReportDAO.saveEdataReportbuildIn(finalReport);
		        }
		    }
		}

	    // --- Helper Methods ---

	    private boolean isPassCondition(Idoc workflow, BuildIn config) {
	        // Whitelist
	        String configStep = config.getCurrent_step();
	        String currentStep = workflow.getCurrent_step();
	        if (!"-".equals(configStep) && !configStep.equals(currentStep)) {
	            return false;
	        }

	        // Blacklist
	        String configStopStatus = config.getClosing_status_stop();
	        String currentStatus = workflow.getClosing_status();
	        if (!"-".equals(configStopStatus) && currentStatus != null && configStopStatus.contains(currentStatus)) {
	            return false;
	        }

	        return true;
	    }

	    private void fillReportColumn(EdataReportbuildIn report, int columnNo, String value) {
	        switch (columnNo) {
	            case 1 -> report.setColumns_value1(value);
	            case 2 -> report.setColumns_value2(value);
	            case 3 -> report.setColumns_value3(value);
	            case 4 -> report.setColumns_value4(value);
	            case 5 -> report.setColumns_value5(value);
	            case 6 -> report.setColumns_value6(value);
	            case 7 -> report.setColumns_value7(value);
	            case 8 -> report.setColumns_value8(value);
	            case 9 -> report.setColumns_value9(value);
	            case 10 -> report.setColumns_value10(value);
	            case 11 -> report.setColumns_value11(value);
	            case 12 -> report.setColumns_value12(value);
	            case 13 -> report.setColumns_value13(value);
	            case 14 -> report.setColumns_value14(value);
	            case 15 -> report.setColumns_value15(value);
	            case 16 -> report.setColumns_value16(value);
	            case 17 -> report.setColumns_value17(value);
	            case 18 -> report.setColumns_value18(value);
	            case 19 -> report.setColumns_value19(value);
	            case 20 -> report.setColumns_value20(value);
	        }
	    }

	    public String runJobOptimized(edata data, BuildIn config, BuildInContext ctx) {
	        String value = null;
	        
	        System.out.println("runJobOptimized "+config.getSource_type());
	        
	        switch (config.getSource_type()) {
	            case 1 -> value = jobWorkflow(ctx, config, data.getData_id());
	            case 2 -> value = jobSegment(ctx, config);
	            case 4 -> value = jobMeasure(ctx, config, data.getData_id());
	            case 5 -> value = jobProfile(ctx, config);
	            case 6 -> value = jobProduct(ctx, config);
	            case 7 -> value = jobCalendar(ctx, config);
	            case 8 -> value = jobExCustomerCode(ctx, config);
	            default -> System.out.println("Unknown Source Type: " + config.getSource_type());
	        }
	        return value;
	    }

	




		private String jobWorkflow(BuildInContext ctx, BuildIn config, int dataId) {
	        Idoc workflow = ctx.workflow;
	        String param = config.getSource_param();
	        flow flowObj;
	        
	        if(param == null) {
	        	  System.out.println(" jobWorkflow param is null ");
			        return "-";
	        }

	        // หมายเหตุ: workflow.getCreator() ต้อง return int ตามที่ระบุมา
	        // หาก Database เก็บเป็น String ให้ใช้ Integer.parseInt(workflow.getCreator())

	        return switch (param) {
	            case "workflow_system_date" -> calculateDaysDiff(workflow.getDate());
	            case "workflow_create_date" -> workflow.getDate();
	            
	            case "workflow_approve_date" -> {
	                flowObj = idocDAO.getFlow(dataId, "Approval");
	                yield (flowObj == null) ? "-" : flowObj.getFlow_date();
	            }
	            case "workflow_close_date" -> {
	                flowObj = idocDAO.getFlow(dataId, "Completion");
	                yield (flowObj == null) ? "-" : flowObj.getFlow_date();
	            }
	            case "workflow_closer_day_total" -> {
	            	
	                // 2. เช็คกัน Null
	                if (workflow.getCurrent_step().equals("Completion")) {
	                    yield "-";
	                }
	                // 3. ดึงวันที่มาเทียบกัน (สมมติว่า getter ของ flowObj คือ getDate_update() ตามที่บอก)
	                // เช็คชื่อ getter ให้ตรงกับ Entity ของท่านนะครับ (บางทีอาจเป็น getFlow_date() หรือ getDate_update())
	                String startDate = workflow.getDate(); 
	                String endDate = workflow.getDate_update(); // หรือ flowObj.getFlow_date();

	                // 4. ส่งไปคำนวณ
	                yield calDateDiff(startDate, endDate);
	            }
	            
	            
	            // --- User Related (ส่ง int เข้าไป) ---
	            case "workflow_creator_name" -> {
	                // สมมติว่า getCreator() return int
	                user u = UserDAO.getbyId2(workflow.getCreator()); 
	                yield (u == null) ? "-" : u.getUser_name();
	            }
	            case "workflow_creator_role" -> {
	                user u = UserDAO.getbyId2(workflow.getCreator());
	                yield (u == null) ? "-" : u.getGroup_name();
	            }
	            case "workflow_closing_status" -> {
	            	String u = workflow.getClosing_status();
	                yield (u == null) ? "-" : u;
	            }
	            case "workflow_closing_status_rejected" -> {
	            	String u = workflow.getClosing_status_rejected();
	                yield (u == null) ? "-" : u;
	            }
	            case "workflow_remarks" -> {
	            	String u = workflow.getRemarks();
	                yield (u == null) ? "-" : u;
	            }
	            case "workflow_approver_name" -> {
	                flowObj = idocDAO.getFlow(dataId, "Approval");
	                if (flowObj == null) yield "-";
	                // สมมติว่า getFlow_send() return int
	                user u = UserDAO.getbyId2(flowObj.getFlow_send()); 
	                yield (u == null) ? "-" : u.getUser_name();
	            }
	            case "workflow_approver_role" -> {
	                flowObj = idocDAO.getFlow(dataId, "Approval");
	                if (flowObj == null) yield "-";
	                user u = UserDAO.getbyId2(flowObj.getFlow_send());
	                yield (u == null) ? "-" : u.getGroup_name();
	            }

	            case "workflow_closer_name" -> {
	                flowObj = idocDAO.getFlow(dataId, "Completion");
	                if (flowObj == null) yield "-";
	                user u = UserDAO.getbyId2(flowObj.getFlow_send());
	                yield (u == null) ? "-" : u.getUser_name();
	            }
	            case "workflow_closer_role" -> {
	                flowObj = idocDAO.getFlow(dataId, "Completion");
	                if (flowObj == null) yield "-";
	                user u = UserDAO.getbyId2(flowObj.getFlow_send());
	                yield (u == null) ? "-" : u.getGroup_name();
	            }

	            default -> "-";
	        };
	    }
	    
	    
		private String jobCalendar(BuildInContext ctx, BuildIn config) {
		    System.out.println(" executeBuildIn jobCalendar ");
		    
		    if (ctx.salesObj == null || config == null || config.getSource_param() == null) {
		        return "-";
		    }
		    if(config.getSource_param() == null) {
	        	  System.out.println(" jobCalendar param is null ");
			        return "-";
	        }

		    String sourceParam = config.getSource_param();
		    int edata_id = ctx.salesObj.getEdataId();

		    // ==============================================================
		    // ขาที่ 1: กลุ่มดึงวันที่ (ใชัแค่ edata_id ไม่ต้องสนใจ Segmentation)
		    // ==============================================================
		    if (sourceParam.startsWith("latest_scheduled_")) {
		        Calendar latest_scheduled = null;

		        if ("latest_scheduled_all".equals(sourceParam)) {
		            // ดึงล่าสุดทั้งหมด (ไม่สน status)
		            latest_scheduled = Upload_pnDAO.CalendarLatest(edata_id, 0); 
		        } 
		        else if ("latest_scheduled_pending".equals(sourceParam)) {
		            // ดึงล่าสุดเฉพาะที่ยังไม่เสร็จ (status = 1)
		            latest_scheduled = Upload_pnDAO.CalendarLatest(edata_id, 1); 
		        }
		
		        
		        

		        if (latest_scheduled != null) {
		            return latest_scheduled.getDay() + "-" + 
		                   latest_scheduled.getMonth() + "-" + 
		                   latest_scheduled.getYear();
		        }
		        return "-"; // ถ้าไม่มีแผนในระบบเลย ให้คืน "-"
		    }

		    // ==============================================================
		    // ขาที่ 2: กลุ่มนับจำนวน (ต้องดึง idall จาก Segmentation ก่อน)
		    // ==============================================================
		    String ruleIdStr = ctx.salesObj.getSegmentation_profile_static_snapshot();
		    if (ruleIdStr == null || !ruleIdStr.matches("\\d+")) {
		        return "-";
		    }

		    int ruleId = Integer.parseInt(ruleIdStr);
		    System.out.println(" ruleId " + ruleId);

		    Segmentation segmentation = EdataDAO.getSegmentationById(ruleId);
		    if (segmentation == null || segmentation.getSegmentation_member_id() == null) {
		        return "-";
		    }
		    
		    String idall = segmentation.getSegmentation_member_id();
		    System.out.println(" executeBuildIn jobCalendar run");
		    long count = 0;

		    // ใช้ Switch-Case ให้อ่านง่ายและทำงานเร็วกว่า if-else ยาวๆ
		    switch (sourceParam) {
		        case "tracking_schedule":
		            // เงื่อนไข: status อะไรก็ได้ (นับทั้งหมดที่ดึงมา)
		            count = Upload_pnDAO.countCalendar(idall);
		            break;
		            
		        case "tracking_is_due":
		            // เงื่อนไข: alert = 1 และ status = 0
		            count = Upload_pnDAO.countCalendar(idall, 1, 0);
		            break;
		            
		        case "missed_scheduled_followup":
		            // เงื่อนไข: alert = 2 และ status = 0
		            count = Upload_pnDAO.countCalendar(idall, 2, 0);
		            break;
		            
		        case "followed":
		            // เงื่อนไข: status = 2 (alert อะไรก็ได้)
		            count = Upload_pnDAO.countCalendar(idall, 2);
		            break;
		            
		        default:
		            return "-"; // ไม่ตรงเงื่อนไขใดๆ เลย
		    }

		    // ส่งค่า count กลับเป็น String
		    return String.valueOf(count);
		}
	    
		// --- Jobs Implementation ---

		private String jobExCustomerCode(BuildInContext ctx, BuildIn config) {
		    if (ctx.salesObj == null) return "-";
		    
		    if(config.getSource_param() == null) {
	        	  System.out.println(" jobExCustomerCode param is null ");
			        return "-";
	        }

		    
		    String customerCode = ctx.salesObj.getCustomerCode();
		    int process_id = config.getEx_process_id();
		    
		    // 1. ดึงเงื่อนไขเวลา (เช่น "doc_count_1_year")
		    String timeCondition = config.getSource_param(); 
		    
		    // 2. ดึงชื่อคอลัมน์จริงๆ ที่จะใช้ค้นหา (เช่น "data_value1")
		    // ⚠️ ตรงนี้คุณต้องเปลี่ยนเป็น method ใน BuildIn ที่เก็บชื่อคอลัมน์ไว้นะครับ
		    String targetColumn = config.getTargetColumn(); // <--- แก้ตรงนี้ให้ตรงกับ Object ของคุณ
		    
		    if (targetColumn == null || targetColumn.isEmpty()) {
		        return "0"; // กันเหนียวเผื่อไม่ได้ตั้งค่าคอลัมน์ไว้
		    }

		    int count = 0;

		    // 3. เอาเงื่อนไขเวลามาเข้า Switch
		    switch (timeCondition) {
		        case "doc_count_1_year" -> {
		            // ส่ง targetColumn เข้าไปแทน
		            count = EdataDAO.countDoc(customerCode, process_id, targetColumn, 1);
		        }
		        case "doc_count_2_years" -> {
		            count = EdataDAO.countDoc(customerCode, process_id, targetColumn, 2);
		        }
		        case "doc_count_3_years" -> {
		            count = EdataDAO.countDoc(customerCode, process_id, targetColumn, 3);
		        }
		        case "doc_count_all" -> {
		            count = EdataDAO.countDoc(customerCode, process_id, targetColumn, 30);
		        }
		        default -> {
		            return "-"; // กรณีที่เลือก Option อื่นหลุดเข้ามา
		        }
		    }

		    return String.valueOf(count);
		}
	    

	    private String jobSegment(BuildInContext ctx, BuildIn config) {
	        if (ctx.salesObj == null) return "-";
	        String ruleIdStr = ctx.salesObj.getSegmentation_profile_static_snapshot();
	        
	        if (ruleIdStr == null || !ruleIdStr.matches("\\d+")) return "-";

	        
	        if(config.getSource_param() == null) {
	        	  System.out.println(" jobSegment param is null ");
			        return "-";
	        }

	        
	        
	        
	        try {
	            int ruleId = Integer.parseInt(ruleIdStr);
	            
	            System.out.println(" ruleId "+ruleId);
	            
	            Segmentation segmentation = EdataDAO.getSegmentationById(ruleId);
	            
	            if (segmentation != null) {
	            	
	                if ("segment_factor".equals(config.getSource_param())) {
	                     return segmentation.getSegment_factor() != null ? segmentation.getSegment_factor() : "-";
	                } else if ("segment_count".equals(config.getSource_param())) {
	                     return String.valueOf(segmentation.getTotal_data_select());
			            } else if ("segment_call_unique".equals(config.getSource_param())) {
		                    return String.valueOf(segmentation.getCall_unique_count());
			        } else if ("segment_call".equals(config.getSource_param())) {
		                return String.valueOf(segmentation.getCall_total());
			        } else if ("segment_promotion".equals(config.getSource_param())) {
			        	
			        	
			        	  if(segmentation.getPromotion() > 0) {
			   			   Optional<CampaignPromotion> userPromotion =  campaignPromotionRepository.findById((long)segmentation.getPromotion() );
			   				   if(userPromotion.isPresent()) {
			   					   return String.valueOf(userPromotion.get().getCampaignName());
			   				   }
			   		   }
			        	
		        
		           }
	                
	            }
	        } catch (NumberFormatException e) {
	            System.err.println("Error Parsing Segment ID: " + ruleIdStr);
	        }
	        return "-";
	    }

	    private String jobMeasure(BuildInContext ctx, BuildIn config, int dataId) {
	        int ruleId = config.getMeasurement_rules_id();
	        Measurement_values measure = ctx.getMeasureCached(ruleId, dataId);

	        if (measure == null) return "-";

	        if(config.getSource_param() == null) {
	        	  System.out.println(" jobMeasure param is null ");
			        return "-";
	        }

	        
	        
	        return switch (config.getSource_param()) {
	            case "measure_current" -> measure.getCurrent_value();
	            case "measure_0"   -> measure.getPercentage_value0();
	            case "measure_10"  -> measure.getPercentage_value10();
	            case "measure_20"  -> measure.getPercentage_value20();
	            case "measure_30"  -> measure.getPercentage_value30();
	            case "measure_40"  -> measure.getPercentage_value40();
	            case "measure_50"  -> measure.getPercentage_value50();
	            case "measure_60"  -> measure.getPercentage_value60();
	            case "measure_70"  -> measure.getPercentage_value70();
	            case "measure_80"  -> measure.getPercentage_value80();
	            case "measure_90"  -> measure.getPercentage_value90();
	            case "measure_100" -> measure.getPercentage_value100();
	            default -> "-";
	        };
	    }

	    private String jobProfile(BuildInContext ctx, BuildIn config) {
	    	
	    	
	    	   System.out.println(" jobProfile.... ");
	    	
	        if (ctx.salesObj == null) return "-";

	        if(config.getSource_param() == null) {
	        	  System.out.println(" jobProfile param is null ");
			        return "-";
	        }

	        
	        String customerCode = ctx.salesObj.getCustomerCode();
	
	        
	        if (customerCode == null) return "-";

	        registers profile = EdataDAO.getProfileByCode(customerCode);
	        
	        if (profile != null && config.getSource_param() != null) {
	            try {
	                Class<?> clazz = profile.getClass();
	                Field field = clazz.getDeclaredField(config.getSource_param());
	                field.setAccessible(true);
	                Object resultObj = field.get(profile);
	                return (resultObj != null) ? resultObj.toString() : "-";
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return "-";
	    }

	    
	    
	    private String jobProduct(BuildInContext ctx, BuildIn config) {
	    	
	    	
	    	   System.out.println(" jobProfile.... ");
	    	
	        if (ctx.salesObj == null) return "-";
	        
	        if(config.getSource_param() == null) {
	        	  System.out.println(" jobProduct param is null ");
			        return "-";
	        }

	        
	        
	        String productCode = ctx.salesObj.getProductCode();
	        
	        System.out.println(" productCode "+productCode);
	        
	        
	        if (productCode == null) return "-";

	        product_master product = EdataDAO.getProduct_masterCode(productCode);
	        
	        if (product != null && config.getSource_param() != null) {
	            try {
	                Class<?> clazz = product.getClass();
	                Field field = clazz.getDeclaredField(config.getSource_param());
	                field.setAccessible(true);
	                Object resultObj = field.get(product);
	                return (resultObj != null) ? resultObj.toString() : "-";
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return "-";
	    }

	    
	    
	    private String calculateDaysDiff(String dateStr) {
	        if (dateStr == null || dateStr.isEmpty()) return "0";
	        try {
	            String cleanDate = dateStr.length() >= 10 ? dateStr.substring(0, 10) : dateStr;
	            LocalDate startDate = LocalDate.parse(cleanDate);
	            LocalDate today = LocalDate.now();
	            long days = ChronoUnit.DAYS.between(startDate, today);
	            return String.valueOf(days);
	        } catch (Exception e) {
	            return "0";
	        }
	    }

	    // ========================================================================
	    // INNER CLASS: Context for Optimization
	    // ========================================================================
	    class BuildInContext {
	        public Idoc workflow;
	        public Edata_maindata entity;
	        public sales_followup salesObj;

	        // [แก้ไข 1] เปลี่ยน Key เป็น Integer
	        private Map<Integer, user> userCache = new HashMap<>(); 
	        
	        private Map<String, flow> flowCache = new HashMap<>();
	        private Map<Integer, Measurement_values> measureCache = new HashMap<>();

	        public BuildInContext(int dataId) {
	            this.workflow = idocDAO.getDocument(dataId);
	            this.entity = EdataDAO.getEdatamainData(dataId);
	            
                System.out.println(" dataId "+  dataId);
             //   System.out.println(" BuildInContext "+this.entity.getEntity());
                
                
	            if (this.entity != null && "sales_followup".equals(this.entity.getEntity())) {
	            
	                this.salesObj = Sales_followup.findById(this.entity.getMain_data_id());
	       
	             //   131209
	                
	                System.out.println(" salesObj "+  this.salesObj.getEdataId());
	            }
	        }

	   



	        public Measurement_values getMeasureCached(int ruleId, int dataId) {
	            return measureCache.computeIfAbsent(ruleId, k -> ReportDAO.getMeasurement_values(k, dataId));
	        }
	        
	
	        
	    }
	    
	    public String calDateDiff(String start, String end) {
	        try {
	            // 1. เช็คค่าว่าง
	            if (start == null || start.trim().isEmpty() || 
	                end == null || end.trim().isEmpty()) {
	                return "-";
	            }

	            // 2. ตัดเวลาทิ้ง เอาแค่ "2026-02-23" (แยกด้วยช่องว่าง)
	            // ไม่ว่าเวลาจะเป็น 21:22 หรือ 21:22:00 ก็จะโดนตัดทิ้งหมด
	            String sDate = start.split(" ")[0];
	            String eDate = end.split(" ")[0];

	            // 3. แปลงเป็น LocalDate
	            LocalDate d1 = LocalDate.parse(sDate);
	            LocalDate d2 = LocalDate.parse(eDate);

	            // 4. คำนวณส่วนต่างวัน (Day Difference)
	            long days = ChronoUnit.DAYS.between(d1, d2);

	            // 5. คืนค่า (ถ้าวันเท่ากันจะเป็น 0)
	            return String.valueOf(days);

	        } catch (Exception e) {
	            // กันเหนียว กรณี Format วันที่ผิด
	            return "-";
	        }
	    }
	
}


