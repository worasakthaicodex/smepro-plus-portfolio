 package com.smepro.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.IdocDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.reportDAO;
import com.smepro.models.Idoc;
import com.smepro.models.Measurement_rules;
import com.smepro.models.Measurement_values;
import com.smepro.models.Relations;
import com.smepro.models.edata;



@Service
public class MeasurementRulesService {

	
	private reportDAO ReportDAO;
	private edataDAO EdataDAO;
	private IdocDAO idocDAO;
	
	 @Autowired
	 private void setMyRepository(
			 reportDAO ReportDAO,
			 edataDAO EdataDAO,
			 IdocDAO idocDAO
			 ) {
	        	this.ReportDAO = ReportDAO;
	        	this.EdataDAO = EdataDAO;
	        	this.idocDAO = idocDAO;
	 		}
	
			/*//Simulation 
			1. When an FO operation occurs, 
			2. Verify whether the FO is referenced in Measurement Rules by matching relationships.
			3. If a reference exists, determine which documents reference the FO.
			4. Retrieve all FO documents that share the same relationship as the original FO,
			   using the CL code linked to that relationship.
			5. Run Measurement processing by passing all related FO documents and the
			   associated CL code to the Measurement engine.
			 */
	         public void IsRules(edata data) {
	        	 
	        	 
	        	 Idoc flow = idocDAO.getDocument(data.getProcesses_id());
	        	 data.setFlow(flow);
	        	 
	        	 // 1. หาว่าได้ตั้งค่ารายงานพื้นหลังไหม
	        	 List<Measurement_rules> Rules =  ReportDAO.getMeasurement_rules(data.getProcesses_id());
	        	 verifyReferenced(Rules,data);
			}
	         
	         public void verifyReferenced(List<Measurement_rules> rules, edata data) {
	        	 
	        	   // 1. ไม่มีรายงานพื้นหลัง
	        	    if (rules == null || rules.isEmpty()) { return; }

	        	    // 1. ดึง destination_process ออกมาเป็น List<Integer>
	        	    // และใช้ .distinct() เพื่อตัดตัวซ้ำออก (ช่วยลดโหลด SQL)
	        	    List<Integer> targetIds = rules.stream()
	        	            .map(Measurement_rules::getThis_process) 
	        	            .distinct()
	        	            .collect(Collectors.toList());

	        	    if (targetIds.isEmpty()) { return; }

	        	    // 2.ดึง ความสัมพันธ์ทั้งหมดที่อยู่ในรายงานพื้นหลังออกมา เเต่ออกมาเฉพาะตัวล่าสุด
	        	    List<Relations> relations = EdataDAO.getRelationsMain(data.getData_id(), targetIds);
	        	   
	        	    // ... ทำงานต่อ ... 
	        	    measurementProcessing(rules,relations,data);
	        	}

	         
			private void measurementProcessing(List<Measurement_rules> rules, List<Relations> listRelations, edata data) {
				// TODO Auto-generated method stub
				System.out.println(" measurementProcessing .... ");
				System.out.println(" listRelations  .... "+listRelations.size());
				
				listRelations.forEach(row->{
					
					System.out.println(" processing  .... "+row.getProcesses_id());
				});
				
				
				if (listRelations.size() == 0) { return; }
				
					// ค่อยๆ ทำไปที่ละ งานพื้นหลัง
					for(Measurement_rules processing: rules) {
						
						Optional<Relations> rel = listRelations.stream().filter(row-> row.getProcesses_id() == processing.getThis_process()).findFirst();
						if(rel.isPresent()) {
							System.out.println(" measurementProcessing isPresent ");
							Relations relation = rel.get();
							if(checkClosingStatus(processing,data.getFlow(),relation)) {
								processingByRules(processing,data,relation); 
								}		
						}
						
					}
				
				
			}


			private boolean checkClosingStatus(Measurement_rules processing, Idoc flow, Relations relation) {
			    
			    // 1. เงื่อนไข Flow: ถ้า processing... เป็น null ให้ถือว่าผ่าน (true) หรือถ้ามีค่าค่อยไปเช็ค hasCommonValue
			    boolean isFlowMatched = (processing.getThis_closing_status() == null) 
			                         || hasCommonValue(flow.getClosing_status(), processing.getThis_closing_status());

			    // 2. เงื่อนไข Relation: ถ้า processing... เป็น null ให้ถือว่าผ่าน (true) หรือถ้ามีค่าค่อยไปเช็ค hasCommonValue
			    boolean isRelationMatched = (processing.getDestination_closing_status() == null) 
			                             || hasCommonValue(relation.getClosing_status(), processing.getDestination_closing_status());

			    

			    System.out.println(" isFlowMatched....... "+isFlowMatched);
			    System.out.println(" isRelationMatched....... "+isRelationMatched);
			    
			    // 3. ต้องผ่านทั้งคู่ (ถ้าตัวไหนเป็น null ก็ถือว่าผ่านไปแล้ว)
			    return isFlowMatched && isRelationMatched;
			}
			
			
			// --- Helper Method สำหรับเช็คค่าใน CSV String ---
			// รับค่า string เช่น "1,2,3" และ "3,4,5" -> คืนค่า true เพราะมี "3" เหมือนกัน
			private boolean hasCommonValue(String str1, String str2) {
			    if (str1 == null || str2 == null) return false;

			    // แปลง String เป็น List โดยตัดด้วย "," และตัดช่องว่างทิ้ง (trim)
			    List<String> list1 = Arrays.stream(str1.split(","))
			                               .map(String::trim)
			                               .collect(Collectors.toList());

			    List<String> list2 = Arrays.stream(str2.split(","))
			                               .map(String::trim)
			                               .collect(Collectors.toList());

			    
			    
			    // ใช้ Stream เช็คว่ามีตัวไหนใน list1 ไปโผล่ใน list2 ไหม (Intersection)
			    return list1.stream().anyMatch(list2::contains);
			}

			private void processingByRules(Measurement_rules processing, edata data, Relations relation) {
			    System.out.println(" processingByRules....... ");

			    int resultValue = 0; 
			    
			    // Switch Expression (Java 14+)
			    switch(processing.getRules()) {
			        case 0 -> resultValue = processingCount(processing, data, relation);
			        case 1 -> resultValue = processingSum(processing, data, relation);
			        case 2 -> resultValue = processingAvg(processing, data, relation);
			        case 3 -> resultValue = processingMax(processing, data, relation);
			        case 4 -> resultValue = processingMin(processing, data, relation);
			        default -> System.out.println("Unknown Rule ID: " + processing.getRules());
			    }
			}

			// ---------------------------------------------------------
			// Specific Methods (แยกกันตามต้องการ)
			// ---------------------------------------------------------

			private int processingCount(Measurement_rules processing, edata data, Relations relation) {
			    System.out.println(" processingCount ");
			    
			    int processing_id = data.getProcesses_id(); 
			    int data_id = relation.getRelations_id();
			    
			    // Logic เฉพาะของ Count: เรียก measurementRulesCount
			    int count = EdataDAO.measurementRulesCount(processing_id, data_id, processing.getDestination_closing_status());
			    
			    // ส่งต่อให้ส่วนกลางจัดการ Save
			    return saveMeasurementWithBucketLogic(processing, data_id, (double) count);
			}

			private int processingSum(Measurement_rules processing, edata data, Relations relation) {
			    return executeOperation(processing, data, relation,1); // 1 = SUM
			}

			private int processingAvg(Measurement_rules processing, edata data, Relations relation) {
			    return executeOperation(processing, data, relation,2); // 2 = AVG
			}

			private int processingMax(Measurement_rules processing, edata data, Relations relation) {
			    return executeOperation(processing, data, relation,3); // 3 = MAX
			}

			private int processingMin(Measurement_rules processing, edata data, Relations relation) {
			    return executeOperation(processing, data, relation,4); // 4 = MIN
			}

			// Helper สำหรับ Sum/Avg/Max/Min เพราะ logic เหมือนกันเป๊ะ ต่างแค่ operation code
			private int executeOperation(Measurement_rules processing, edata data, Relations relation, int operationCode) {
			    // เช็ค column ก่อน
			    if(processing.getDestination_column_operation() == null || processing.getDestination_column_operation().isEmpty()) { 
			        return 0;
			    }

			    int processing_id = data.getProcesses_id(); 
			    int data_id = relation.getRelations_id();
			    
			    // เรียกใช้ measurementRulesOperation (ตัวใหม่ที่ทำไว้)
			    double result = EdataDAO.measurementRulesOperation(
			        processing_id, 
			        data_id, 
			        processing.getDestination_closing_status(), 
			        processing.getDestination_column_operation(),
			        operationCode
			    );

			    // ส่งต่อให้ส่วนกลางจัดการ Save
			    return saveMeasurementWithBucketLogic(processing, data_id, result);
			}

			
			
			// ---------------------------------------------------------
			// Common Logic (ส่วนที่ซ้ำกัน ดึงออกมาไว้ตรงนี้)
			// ---------------------------------------------------------
			private int saveMeasurementWithBucketLogic(Measurement_rules processing, int data_id, double resultValue) {
			    
			    // 1. เตรียม Object
			    Measurement_values value = ReportDAO.getMeasurement_values(processing.getId(), data_id);
			    
			    value.setMeasurement_rules_id(processing.getId());
			    value.setEdata_id(data_id);
			    // แปลง double เป็น String (ถ้าเป็นจำนวนเต็ม .0 อาจจะติดมา ถ้าไม่ชอบต้อง format เพิ่มเติม)
			    value.setCurrent_value(String.valueOf(resultValue)); 

	
			    // 2. เตรียมตัวแปรสำหรับคำนวณหา "ช่อง" (Bucket)
			    // -----------------------------------------------------------------------
			    // TODO: [PENDING] รอเขียน Logic ดึงค่า Goal และ Current Progress จาก Report อื่น
			    // ตอนนี้ Hardcode ไว้ทดสอบระบบคำนวณ Bucket ก่อน
			    // -----------------------------------------------------------------------
			    double Starting_value = 10.0;    // Goal
			    double Processing_values = 2.0;  // Current Progress (จาก Rule อื่น)

			    // 3. คำนวณ % เพื่อหาช่องลง
			    if (Starting_value > 0) {
			        
			        double percentage = (Processing_values / Starting_value) * 100.0;
			        
			        int bucket = (int) (percentage / 10) * 10;
			        if (bucket > 100) bucket = 100;
			        if (bucket < 0) bucket = 0;     

			        String resultToStore = String.valueOf(resultValue);

			        // Switch แบบใหม่ (Java 12+) สั้นลง
			        switch (bucket) {
			            case 0 -> value.setPercentage_value0(resultToStore);
			            case 10 -> value.setPercentage_value10(resultToStore);
			            case 20 -> value.setPercentage_value20(resultToStore);
			            case 30 -> value.setPercentage_value30(resultToStore);
			            case 40 -> value.setPercentage_value40(resultToStore);
			            case 50 -> value.setPercentage_value50(resultToStore);
			            case 60 -> value.setPercentage_value60(resultToStore);
			            case 70 -> value.setPercentage_value70(resultToStore);
			            case 80 -> value.setPercentage_value80(resultToStore);
			            case 90 -> value.setPercentage_value90(resultToStore);
			            default -> value.setPercentage_value100(resultToStore); 
			        }
			    }

			    // 4. บันทึก
			    return ReportDAO.saveMeasurement_values(value);
			}
}
