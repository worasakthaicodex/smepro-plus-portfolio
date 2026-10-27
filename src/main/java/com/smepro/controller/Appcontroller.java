package com.smepro.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.smepro.dao.IdocDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.edataDAO;
import com.smepro.entity.BiReportSetting;
import com.smepro.entity.CampaignPromotion;
import com.smepro.entity.sales_followup;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Idoc;
import com.smepro.models.Segmentation;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.process;
import com.smepro.repository.CampaignPromotionRepository;
import com.smepro.repository.Sales_followupRepository;
import com.smepro.service.ScheduleService;


@Controller
@SessionAttributes({"Member_name","name","user_img","Member_id","USERNAME"})
public class Appcontroller {

	private CampaignPromotionRepository campaignPromotionRepository;
	private ScheduleService scheduleService;
	private  edataDAO EdataDAO;
	private  Sales_followupRepository sales_followupRepository;
	private  datasetDAO DatasetDAO;
	private  dataset_dataDAO Dataset_dataDAO;
	private  IdocDAO idocDAO;
	
	
    @Autowired
    public void setMyRepository( 
    		CampaignPromotionRepository campaignPromotionRepository,
    		ScheduleService scheduleService,edataDAO EdataDAO,
    		Sales_followupRepository sales_followupRepository,
    		 datasetDAO DatasetDAO,
    		 dataset_dataDAO Dataset_dataDAO,
    		 IdocDAO idocDAO
    		) {
    	
		this.campaignPromotionRepository = campaignPromotionRepository;
		this.scheduleService = scheduleService;
		this.EdataDAO = EdataDAO;
		this.sales_followupRepository = sales_followupRepository;
		this.Dataset_dataDAO = Dataset_dataDAO;
		this.DatasetDAO = DatasetDAO;
		this.idocDAO = idocDAO;
	}
    
	
	 @RequestMapping(value="/appPromotion-app/{appid}", method = RequestMethod.GET)
		public String Promosapp(ModelMap model,@PathVariable int appid) { 	
		 
		 			model.addAttribute("appid",appid); 
	 				return "app/Promosapp/appPromotion";
		   }
	 
	 

	 @RequestMapping(value="/Promos-app/{appid}",method = RequestMethod.GET)
	 public String appPromotion(
	         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	         ModelMap model, @PathVariable int appid) {   
	     
	     List<CampaignPromotion> allPromos = campaignPromotionRepository.findAll();
	     List<CampaignPromotion> filteredPromos = new ArrayList<>();

	     // ลอจิกการกรองวันที่
	     for (CampaignPromotion promo : allPromos) {
	         boolean isMatch = true;
	         
	         // เช็คว่าแคมเปญนี้อยู่ในช่วงเวลาที่เลือกหรือไม่ (อิงจากวันที่สร้าง หรือวันที่เริ่มแคมเปญ)
	         if (startDate != null && promo.getStartDate() != null && promo.getStartDate().isBefore(startDate)) {
	             isMatch = false;
	         }
	         if (endDate != null && promo.getEndDate() != null && promo.getEndDate().isAfter(endDate)) {
	             isMatch = false;
	         }
	         
	         if (isMatch) {
	             filteredPromos.add(promo);
	         }
	     }

	     
	     BigDecimal totalRevenue = BigDecimal.ZERO;
	     int totalRedemptions = 0;
	     int activePromosCount = 0;
	     
	     // ตัวแปรสำหรับแยกกลุ่มเป้าหมาย (Audience)
	     int salesSelectedRedemptions = 0;
	     int customCondRedemptions = 0;

	     // 2. วนลูปคำนวณ KPI รวม
	     for (CampaignPromotion promo : allPromos) {
	         if ("ACTIVE".equals(promo.getStatus())) {
	             activePromosCount++;
	         }
	         
	         if (promo.getActualRevenue() != null) {
	             totalRevenue = totalRevenue.add(promo.getActualRevenue());
	         }
	         
	         if (promo.getActualRedemptions() != null) {
	             int redemptions = promo.getActualRedemptions();
	             totalRedemptions += redemptions;
	             
	             // แยกยอดตามกลุ่มเป้าหมาย
	             if ("SALES_SELECTED".equals(promo.getTargetAudience())) {
	                 salesSelectedRedemptions += redemptions;
	             } else if ("CUSTOM_CONDITIONS".equals(promo.getTargetAudience())) {
	                 customCondRedemptions += redemptions;
	             }
	         }
	     }

	     // 3. คำนวณรายได้เฉลี่ยต่อสิทธิ์ (Avg. Revenue / Redemption)
	     BigDecimal avgRevenue = BigDecimal.ZERO;
	     if (totalRedemptions > 0) {
	         avgRevenue = totalRevenue.divide(new BigDecimal(totalRedemptions), 2, RoundingMode.HALF_UP);
	     }
	     
	     // 4. จัดอันดับ Top 4 แคมเปญที่รายได้สูงสุด
	     List<CampaignPromotion> topCampaigns = new ArrayList<>(allPromos);
	     topCampaigns.sort((p1, p2) -> {
	         BigDecimal r1 = p1.getActualRevenue() != null ? p1.getActualRevenue() : BigDecimal.ZERO;
	         BigDecimal r2 = p2.getActualRevenue() != null ? p2.getActualRevenue() : BigDecimal.ZERO;
	         return r2.compareTo(r1); // เรียงจากมากไปน้อย (DESC)
	     });
	     if (topCampaigns.size() > 4) {
	         topCampaigns = topCampaigns.subList(0, 4);
	     }

	     // โยนค่าทั้งหมดลง Model ไปแสดงผล
	     model.addAttribute("totalRevenue", totalRevenue);
	     model.addAttribute("totalRedemptions", totalRedemptions);
	     model.addAttribute("avgRevenue", avgRevenue);
	     model.addAttribute("activePromosCount", activePromosCount);
	     
	     model.addAttribute("salesSelectedRedemptions", salesSelectedRedemptions);
	     model.addAttribute("customCondRedemptions", customCondRedemptions);
	     
	     model.addAttribute("topCampaigns", topCampaigns);
	     model.addAttribute("appid", appid); 

	     
	  // เตรียม Array สำหรับกราฟ 6 เดือน (ม.ค. - มิ.ย.)
	     BigDecimal[] monthlyRevenue = new BigDecimal[6];
	     String[] monthLabels = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
	     for(int i = 0; i < 6; i++) {
	         monthlyRevenue[i] = BigDecimal.ZERO;
	     }
	     
	     BigDecimal maxMonthlyRevenue = BigDecimal.ZERO;

	     for (CampaignPromotion promo : allPromos) {
	         // ... (โค้ดดึง KPI ของเดิม) ...
	         
	         // ---------------- ส่วนของกราฟ ----------------
	         // จัดกลุ่มรายได้จริงตามเดือนที่เริ่มแคมเปญ (StartDate)
	         if (promo.getActualRevenue() != null && promo.getStartDate() != null) {
	             int monthValue = promo.getStartDate().getMonthValue(); // ได้ค่า 1-12
	             
	             // กรองมาเฉพาะเดือน 1-6
	             if (monthValue >= 1 && monthValue <= 6) {
	                 int index = monthValue - 1;
	                 monthlyRevenue[index] = monthlyRevenue[index].add(promo.getActualRevenue());
	                 
	                 // เก็บค่าเดือนที่รายได้สูงที่สุดไว้เป็นเกณฑ์ 100% ของกราฟ
	                 if (monthlyRevenue[index].compareTo(maxMonthlyRevenue) > 0) {
	                     maxMonthlyRevenue = monthlyRevenue[index];
	                 }
	             }
	         }
	     }

	     // ปรับสเกลกราฟให้สวยงาม (บวกพื้นที่ว่างด้านบนอีก 20% เพื่อไม่ให้กราฟทะลุเพดาน)
	     if (maxMonthlyRevenue.compareTo(BigDecimal.ZERO) == 0) {
	         maxMonthlyRevenue = new BigDecimal(100000); // ถ้าไม่มียอดเลย เซ็ต Default สเกล
	     } else {
	         maxMonthlyRevenue = maxMonthlyRevenue.multiply(new BigDecimal("1.2"));
	     }

	     // โยนเข้า Model
	     model.addAttribute("monthlyRevenue", monthlyRevenue);
	     model.addAttribute("monthLabels", monthLabels);
	     model.addAttribute("maxMonthlyRevenue", maxMonthlyRevenue);
	     model.addAttribute("startDate", startDate);
	     model.addAttribute("endDate", endDate);
	     
	     return "app/Promosapp/Promosapp";
	 }
	 
	 @RequestMapping(value="/ReportsPromos-app/{appid}",method = RequestMethod.GET)
		public String ReportsPromos(ModelMap model,@PathVariable int appid) { 	
					model.addAttribute("appid",appid); 
	 				return "app/Promosapp/ReportsPromos";
		   }
	 @RequestMapping(value="/AIPromos-app/{appid}",method = RequestMethod.GET)
		public String AIPromos(ModelMap model,@PathVariable int appid) { 	
					model.addAttribute("appid",appid); 
					
					 List<CampaignPromotion> allPromos = campaignPromotionRepository.findAll();
					 model.addAttribute("activePromotions",allPromos); 
						model.addAttribute("active", "AIPromos");
	 				return "app/Promosapp/AIPromos";
		   }
	 @RequestMapping(value="/CustomersPromos-app/{appid}",method = RequestMethod.GET)
		public String CustomersPromos(ModelMap model,@PathVariable int appid) { 	
					model.addAttribute("appid",appid); 
	 				return "app/Promosapp/CustomersPromos";
		   }
	 
	 
	 
	 @RequestMapping(value="/DetailPromotion-app/{appid}/{id}",method = RequestMethod.GET)
		public String DetailPromotion(ModelMap model,@PathVariable int appid,@PathVariable long id) { 	
		
					
					  List<CampaignPromotion> promotions = campaignPromotionRepository.findAll();
				        model.addAttribute("ListPromotion", promotions);

				        if (id > 0l) {
				            CampaignPromotion selectedPromo = campaignPromotionRepository.findById(id).orElse(null);
				            
				            if (selectedPromo != null) {
				                // ดึง Follow up ที่ผูกกับโปรแกรม (LossLink)
				                List<sales_followup> followupList = sales_followupRepository.findAllByProcessId(selectedPromo.getMeasurementProcessId());
				                
				                // เตรียม Array ของสเตตัสเป้าหมาย (ตัด String ด้วยคอมม่า)
				                List<String> successStatuses = new ArrayList<>();
				                if (selectedPromo.getSuccessWorkflowStatusIds() != null && !selectedPromo.getSuccessWorkflowStatusIds().isEmpty()) {
				                    successStatuses = Arrays.asList(selectedPromo.getSuccessWorkflowStatusIds().split(","));
				                }

				                // ตัวแปรสำหรับสรุปผล
				                int actualRedemptions = 0;
				                BigDecimal actualRevenue = BigDecimal.ZERO;
				                Set<String> uniqueCustomers = new HashSet<>(); // ใช้ Set เพื่อให้ customer_code ไม่ซ้ำ
				                List<sales_followup> interestedList = new ArrayList<>();
				                List<sales_followup> successList = new ArrayList<>();
				                
				                
				                for (sales_followup followup : followupList) {
				                    // 1. เช็คว่าเป็นของโปรโมชั่นนี้ใช่หรือไม่
				                    if (followup.getPromotionCode()!= null && followup.getPromotionCode().equals(String.valueOf(selectedPromo.getId()))) {

				                      	interestedList.add(followup); // เก็บรายชื่อผู้สนใจทั้งหมดที่ผูกกับโปรนี้
				              
				                    	
				                        // เก็บคนสนใจโดย Group ตาม customer_code
				                        if (followup.getCustomerCode() != null) {
				                            uniqueCustomers.add(followup.getCustomerCode());
				                  
				                        }

				                        // 2. ไปหา idoc โดยใช้ edata_id (สมมติว่าคุณมี idocRepository)
				                        Idoc idocData = idocDAO.getDocument(followup.getEdataId());
				                        
				                        // 3. ตรวจสอบว่ามี idoc และ status ตรงกับที่ตั้งไว้หรือไม่
				                        if (idocData != null && idocData.getClosing_status() != null) {
				                            if (successStatuses.contains(idocData.getClosing_status().trim())) {
				                            	successList.add(followup); // เก็บรายชื่อคนที่ปิดการขายได้จริง
				                                // นับเป็นผู้ใช้สิทธิ์
				                                actualRedemptions++;
				                                
				                             // รวมรายได้จริง (แปลงจาก String เป็น BigDecimal)
				                                String netAmountStr = followup.getNet_amount();

				                                if (netAmountStr != null && !netAmountStr.trim().isEmpty()) {
				                                    try {
				                                        // ทำความสะอาด String ก่อน (ลบลูกน้ำ และช่องว่างซ้ายขวา)
				                                        String cleanAmount = netAmountStr.replace(",", "").trim();
				                                        
				                                        // แปลงเป็น BigDecimal แล้วจับบวก
				                                        BigDecimal amount = new BigDecimal(cleanAmount);
				                                        actualRevenue = actualRevenue.add(amount);
				                                        
				                                    } catch (NumberFormatException e) {
				                                        // กรณีที่แปลงตัวเลขไม่ได้ (เช่น มีตัวอักษรปนมา) ข้ามไปก่อน หรือจะพิมพ์ Log ไว้ตรวจสอบได้
				                                        System.out.println("Invalid net_amount format for followup ID " + followup.getId() + " : " + netAmountStr);
				                                    }
				                                }
				                            }
				                        }
				                    }
				                }

				                // 4. นำค่าที่คำนวณได้อัปเดตกลับเข้า Entity
				                selectedPromo.setInterestedCount(uniqueCustomers.size());
				                selectedPromo.setActualRedemptions(actualRedemptions);
				                selectedPromo.setActualRevenue(actualRevenue);
				                
				                // เซฟค่าที่อัปเดตแล้วลง DB จะได้ไม่ต้องคำนวณใหม่ทุกครั้งที่เปิดหน้า
				                campaignPromotionRepository.save(selectedPromo);

				                
				             // นอกลูป ส่งเข้า Model
				                model.addAttribute("interestedList", interestedList);
				                model.addAttribute("successList", successList);
				                // ส่งไปแสดงผลที่หน้าจอ
				                model.addAttribute("Promotion", selectedPromo);
				            }
				        }
				        
				        model.addAttribute("appid", appid); 
					
					
					
					
					
	 				return "app/Promosapp/Detail";
		   }
	 
	 
	 @RequestMapping(value="/editPromotion-app/{appid}/{id}",method = RequestMethod.GET)
		public String editPromotion(ModelMap model,@PathVariable int appid,@PathVariable long id) { 	
					model.addAttribute("appid",appid); 
					Optional<CampaignPromotion>  Promotion = campaignPromotionRepository.findById(id);
					if(Promotion.isPresent()) {
						model.addAttribute("Promotion", Promotion.get());
						
					List<process> app = EdataDAO.findAllProcessAllowLooseLink();
					model.addAttribute("app", app);
					

					 dataset dataset = DatasetDAO.getMain("Workflow_status");
				     List<dataset_data> actionType = Dataset_dataDAO.getall(dataset.getDataset_id());
				      model.addAttribute("actionType", actionType);
					}
					
					
					
	 				return "app/Promosapp/appPromotion";
		   }
	 
	 @RequestMapping(value="/myPromos-app/{appid}",method = RequestMethod.GET)
		public String myPromos(ModelMap model,@PathVariable int appid) { 	

					List<CampaignPromotion>  Promotion = campaignPromotionRepository.findAll();
					model.addAttribute("ListPromotion", Promotion);
					
					model.addAttribute("active", "myPromos");
					
	 				return "app/Promosapp/myPromos";
		   }
	 

	    @PostMapping("/updatePromos-app/{appid}")
	    public String updatePromotionStatus(@RequestParam("id") Long id, 
	                                        @RequestParam("status") String status,@PathVariable int appid) {
	        CampaignPromotion promotion = campaignPromotionRepository.findById(id).orElse(null);
	        if (promotion != null) {
	            promotion.setStatus(status); // อัปเดตสถานะ
	            campaignPromotionRepository.save(promotion); // JPA Save (Update)
	        }
	        return "redirect:/myPromos-app/"+appid; 
	    }
	   

	    @PostMapping("/deletePromos-app/{appid}")
	    public String deletePromotion(@RequestParam("id") Long id,@PathVariable int appid) {
	        campaignPromotionRepository.deleteById(id);
	        return "redirect:/myPromos-app/"+appid; 
	    }
	 
	 
	 
	 
	    @GetMapping("/showpromotions-app/{appid}")
	    public String showPromotions(@RequestParam(value = "id", required = false) Long id, ModelMap model, @PathVariable int appid) {
	        
	        List<CampaignPromotion> promotions = campaignPromotionRepository.findAll();
	        model.addAttribute("ListPromotion", promotions);

	        if (id != null) {
	            CampaignPromotion selectedPromo = campaignPromotionRepository.findById(id).orElse(null);
	            
	            if (selectedPromo != null) {
	            	System.out.println("ProcessId.."+ selectedPromo.getMeasurementProcessId());
	            	System.out.println("PromotionCode..."+ id);
	            	
	            	
	                // ดึง Follow up ที่ผูกกับโปรแกรม (LossLink)
	                List<sales_followup> followupList  = sales_followupRepository.findAllByProcessIdAndPromotionCode(selectedPromo.getMeasurementProcessId(), String.valueOf(id));
	                // เตรียม Array ของสเตตัสเป้าหมาย (ตัด String ด้วยคอมม่า)
	                List<String> successStatuses = new ArrayList<>();
	                if (selectedPromo.getSuccessWorkflowStatusIds() != null && !selectedPromo.getSuccessWorkflowStatusIds().isEmpty()) {
	                    successStatuses = Arrays.asList(selectedPromo.getSuccessWorkflowStatusIds().split(","));
	                }

	                // ตัวแปรสำหรับสรุปผล
	                int actualRedemptions = 0;
	                BigDecimal actualRevenue = BigDecimal.ZERO;
	                Set<String> uniqueCustomers = new HashSet<>(); // ใช้ Set เพื่อให้ customer_code ไม่ซ้ำ
	                List<sales_followup> interestedList = new ArrayList<>();
	                List<sales_followup> successList = new ArrayList<>();
	                
	                
	                for (sales_followup followup : followupList) {
	                	
	                	System.out.println("getPromotionCode..."+ followup.getPromotionCode());
	                	
	                    // 1. เช็คว่าเป็นของโปรโมชั่นนี้ใช่หรือไม่
	                    if (followup.getPromotionCode()!= null ) {

	                      	interestedList.add(followup); // เก็บรายชื่อผู้สนใจทั้งหมดที่ผูกกับโปรนี้
	              
	                    	
	                        // เก็บคนสนใจโดย Group ตาม customer_code
	                        if (followup.getCustomerCode() != null) {
	                            uniqueCustomers.add(followup.getCustomerCode());
	                  
	                        }

	                        // 2. ไปหา idoc โดยใช้ edata_id (สมมติว่าคุณมี idocRepository)
	                        Idoc idocData = idocDAO.getDocument(followup.getEdataId());
	                        
	                        // 3. ตรวจสอบว่ามี idoc และ status ตรงกับที่ตั้งไว้หรือไม่
	                        if (idocData != null && idocData.getClosing_status() != null) {
	                            if (successStatuses.contains(idocData.getClosing_status().trim())) {
	                            	successList.add(followup); // เก็บรายชื่อคนที่ปิดการขายได้จริง
	                                // นับเป็นผู้ใช้สิทธิ์
	                                actualRedemptions++;
	                                
	                             // รวมรายได้จริง (แปลงจาก String เป็น BigDecimal)
	                                String netAmountStr = followup.getNet_amount();

	                                if (netAmountStr != null && !netAmountStr.trim().isEmpty()) {
	                                    try {
	                                        // ทำความสะอาด String ก่อน (ลบลูกน้ำ และช่องว่างซ้ายขวา)
	                                        String cleanAmount = netAmountStr.replace(",", "").trim();
	                                        
	                                        // แปลงเป็น BigDecimal แล้วจับบวก
	                                        BigDecimal amount = new BigDecimal(cleanAmount);
	                                        actualRevenue = actualRevenue.add(amount);
	                                        
	                                    } catch (NumberFormatException e) {
	                                        // กรณีที่แปลงตัวเลขไม่ได้ (เช่น มีตัวอักษรปนมา) ข้ามไปก่อน หรือจะพิมพ์ Log ไว้ตรวจสอบได้
	                                        System.out.println("Invalid net_amount format for followup ID " + followup.getId() + " : " + netAmountStr);
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }

	                // 4. นำค่าที่คำนวณได้อัปเดตกลับเข้า Entity
	                selectedPromo.setInterestedCount(uniqueCustomers.size());
	                selectedPromo.setActualRedemptions(actualRedemptions);
	                selectedPromo.setActualRevenue(actualRevenue);
	                
	                // เซฟค่าที่อัปเดตแล้วลง DB จะได้ไม่ต้องคำนวณใหม่ทุกครั้งที่เปิดหน้า
	                campaignPromotionRepository.save(selectedPromo);

	                
	             // นอกลูป ส่งเข้า Model
	                model.addAttribute("interestedList", interestedList);
	                model.addAttribute("successList", successList);
	                // ส่งไปแสดงผลที่หน้าจอ
	                model.addAttribute("selectedPromo", selectedPromo);
	            }
	        }
	        
	        model.addAttribute("appid", appid); 
	        return "app/Promosapp/showpro";
	    }
		 
		 
		 
		 
	 @RequestMapping(value="/addPromos-app",method = RequestMethod.POST)
		public String addPromos(ModelMap model,@RequestParam int appid,CampaignPromotion campaignPromotion) { 	
					model.addAttribute("appid",appid); 
					CampaignPromotion  Promotion = campaignPromotionRepository.save(campaignPromotion);
					return "redirect:/editPromotion-app/"+appid+"/"+Promotion.getId();
		   }
	 

	 
	 
	 @ResponseBody
	 @RequestMapping(value="/Promosforuse-app/{edata_id}/{id}", method = RequestMethod.GET)
	 public Object Promosforuse(@PathVariable int edata_id, @PathVariable int id) { 			

	     sales_followup followup = sales_followupRepository.findByEdataId(edata_id);
	     int PromotionCode = followup != null && followup.getPromotionCode() != null 
	                         ? Integer.parseInt(followup.getPromotionCode()) : 0;
	     
	     // สร้าง List กลางเพื่อเก็บผลลัพธ์ที่จะส่งกลับ
	     List<CampaignPromotion> finalPromotions = new ArrayList<>();

	     // 1. ตรวจสอบ PromotionCode (ถ้า > 0 ดึงมาแค่ตัวเดียว / ถ้า <= 0 ดึงทั้งหมด)
	     if (PromotionCode > 0) {
	         // ดึงเฉพาะของ PromotionCode (สมมติว่า id ใน repository เป็น Long)
	         Optional<CampaignPromotion> specificPromo = campaignPromotionRepository.findById((long) PromotionCode);
	         specificPromo.ifPresent(finalPromotions::add);
	     } else {
	         // ถ้าไม่มี PromotionCode บังคับ ให้ดึงรายการทั้งหมด (หรือรายการที่ ACTIVE)
	         finalPromotions.addAll(campaignPromotionRepository.findByTargetAudienceAndStatus("SALES_SELECTED", "ACTIVE"));
	     }

	     // 2. ตรวจสอบ ID ที่เคยเลือกไว้ (ต้องมาด้วยเสมอ)
	     if (id > 0) {
	         // เช็คก่อนว่าใน finalPromotions มี ID นี้อยู่แล้วหรือยัง จะได้ไม่ซ้ำ
	         boolean alreadyExists = finalPromotions.stream()
	                 .anyMatch(promo -> promo.getId() == id);
	         
	         if (!alreadyExists) {
	             // ถ้ายังไม่มี (เช่น หมดอายุไปแล้ว หรือโดนกรองออก) ให้ไปดึงจากฐานข้อมูลมาแปะเพิ่ม
	             Optional<CampaignPromotion> selectedPromo = campaignPromotionRepository.findById((long) id);
	             selectedPromo.ifPresent(finalPromotions::add);
	         }
	     } 							

	     // 3. ส่งข้อมูลไปแปลงเป็น JSON
	     return scheduleService.createPromotionJson(finalPromotions);
	 }
	
	
	 
	
	 
	
	 
	
}
