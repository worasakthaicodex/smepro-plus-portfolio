package com.smepro.controller;

import java.util.List;

import com.smepro.models.registers;

public class CustomerScoringService {
	

					public void evaluateHighSpender(registers customer) {
				        
				    double estimatedBudget = 0.0;

				        // --- 1. ประเมินสเกลบริษัทจาก "ทุนจดทะเบียน (Capital)" ---
				        // ใช้เป็นฐาน (Base) ว่าบริษัทใหญ่แค่ไหน (หน่วยเป็นบาท)
				        if (customer.getCapital() != 0) {
				            if (customer.getCapital() >= 10000000) { // ทุน 10 ล้านขึ้นไป (บริษัทค่อนข้างใหญ่/มหาชน)
				                estimatedBudget += 40000;
				            } else if (customer.getCapital() >= 5000000) { // ทุน 5 ล้านขึ้นไป (SME ขนาดใหญ่)
				                estimatedBudget += 30000;
				            } else if (customer.getCapital() >= 1000000) { // ทุน 1 ล้าน (มาตรฐานบริษัทจำกัดทั่วไป)
				                estimatedBudget += 20000;
				            } else { // ต่ำกว่าล้าน เช่น หจก. เล็กๆ หรือบุคคลธรรมดา
				                estimatedBudget += 5000;
				            }
				        }

				        // --- 2. บวกมูลค่าจาก "ประเภทสินค้า (Register Groups)" ---
				        String group = customer.getRegister_groups() != null ? customer.getRegister_groups() : "อื่นๆ";
				        switch (group) {
				            case "ยา":
				            case "เครื่องมือแพทย์":
				                estimatedBudget += 50000; // มาตรฐานสูง บังคับใช้ของดี
				                break;
				            case "เครื่องสำอาง":
				            case "วัตถุอันตราย":
				                estimatedBudget += 30000;
				                break;
				            case "อาหาร":
				            case "food chain":
				                estimatedBudget += 20000;
				                break;
				            case "ขนส่ง":
				                estimatedBudget += 10000;
				                break;     
				            default: // , อื่นๆ
				                estimatedBudget += 5000;
				                break;
				        }

				        // --- 3. บวกมูลค่าจาก "ประเภทการผลิต (Business Type)" ---
				        String type = customer.getBusiness_type() != null ? customer.getBusiness_type() : "ไม่ระบุ";
				        switch (type) {
				            case "ผลิต":
				                estimatedBudget += 40000; // โรงงานผลิต มีงบซ่อมบำรุง/ปรับปรุงเยอะกว่า
				                break;
				            case "แปรรูป":
				                estimatedBudget += 20000;
				                break;
				            case "แปรรูปขั้นต้น":
				            case "คัดบรรจุ":
				            case "แบ่งบรรจุ":
				                estimatedBudget += 10000;
				                break;
				            // ซื้อมาขายไป, ร้านอาหาร / โรงเเรม, งานบริการ, ตัดแต่ง ให้ 0 เพราะเน้นซื้อถูก
				        }

				        // --- 4. ตัวเร่งขนาด (Scale Boosters) ---
				        // ถ้าเซลล์สืบจนรู้ว่าพนักงานเยอะ หรือเครื่องจักรแรงม้าสูง ก็บวกเข้าไปอีก
				        if (customer.getHorsepower() >= 100) {
				            estimatedBudget += 30000; // เครื่องจักรใหญ่ กำลังซื้อสูงแน่
				        }
				        if (customer.getHeadcount() >= 100) {
				            estimatedBudget += 20000; // คนเยอะ พื้นที่เยอะ
				        }

				        // --- สรุปผล ---
				        // ถ้าคำนวณรวมกันแล้วทะลุ 100,000 ให้ติ๊กเป็น "กำลังซื้อสูง" (true)
				        customer.setHighSpender(estimatedBudget >= 100000);
				    }
					
					
			
					    public void evaluateBehaviorTags(registers customer, List<String> selectedInquiries, List<String> selectedConcerns) {
					        
					        // กำหนดคะแนนเริ่มต้น
					        int controllerHits = 0; // เงื่อนไขมาก
					        int dataHits = 0;       // สนใจข้อมูล
					        int priceHits = 0;      // สนใจราคา

					     // --- 1. เช็คจาก Tag คำถาม (Inquiry) ---
					        if (selectedInquiries != null) {
					            for (String tag : selectedInquiries) {
					                switch (tag) {
					                    case "ถามความต่างของเวอร์ชันใหม่,ข้อกำหนดใหม่":
					                    case "ถามหลักสูตรการฝึกอบรม,การพัฒนาพนักงาน":
					                    case "ถามถึงประโยชน์ระยะยาว,ความคุ้มค่าของการลงทุน_(ROI)": // ++(เพิ่มใหม่)++
					                        dataHits++;
					                        break;
					                        
					                    case "ถามขั้นตอนการทำงาน,ขอบข่ายงานที่จะได้รับ":
					                        controllerHits++;
					                        dataHits++;
					                        break;
					                        
					                    case "ถามหาผลงานเก่า,ประสบการณ์ของที่ปรึกษา":
					                    case "ถามระยะเวลาตั้งแต่เริ่มจนจบงาน":
					                    case "ถามหาการรับประกันผลงาน,การดูแลหลังการตรวจประเมิน":
					                        controllerHits++;
					                        break;
					                        
					                    case "ถามถึงผลกระทบถ้าไม่ทำระบบ,ความเสี่ยงทางกฎหมายหรือค่าปรับ": // ++(เพิ่มใหม่)++
					                        controllerHits++; // กังวลเรื่องความเสี่ยง
					                        priceHits++;      // ประเมินว่าค่าปรับ กับ ค่าจ้าง อันไหนแพงกว่า
					                        break;

					                    case "ถามเรื่องราคา,โครงสร้างการแบ่งจ่าย,โปรโมชั่น":
					                    case "ถามถึงข้อกฎหมายบังคับ,ขอบเขตขั้นต่ำที่ต้องทำ_(เน้นแค่ผ่านเกณฑ์)": // ++(เพิ่มใหม่)++
					                        priceHits++; // บ่งบอกชัดเจนว่าต้องการประหยัดงบที่สุด
					                        break;
					                }
					            }
					        }

					        // --- 2. เช็คจาก Tag ข้อกังวล (Concern) ---
					        if (selectedConcerns != null) {
					            for (String tag : selectedConcerns) {
					                switch (tag) {
					                    case "กังวลเรื่องราคาแพง,งบประมาณมีจำกัด":
					                    case "ทำเพราะโดนบังคับ,ไม่ได้อยากทำแต่จำเป็นต้องมีเอกสาร": // ++(เพิ่มใหม่)++
					                        priceHits++;
					                        break;
					                        
					                    case "กังวลเรื่องเวลา,กลัวกระทบเวลาการผลิต":
					                    case "กังวลเรื่องคน,พนักงานไม่ทำตาม,กลัวเอกสารเยอะ":
					                    case "กังวลความเข้มงวดของAuditor,กลัวทำแล้วไม่ผ่าน":
					                    case "กังวลเรื่องความสามารถของที่ปรึกษา,ไม่แน่ใจว่าเราจะทำสำเร็จไหม": // ++(เพิ่มใหม่ แทงใจเซลล์ 555)++	
					                        controllerHits++;
					                        break;
					                        
					                    case "ติดปัญหาเรื่องสุขลักษณะหน้างานที่แก้ยาก":
					                        controllerHits++;
					                        dataHits++;
					                        break;
					                        
					                    case "ลังเลเรื่องความคุ้มค่า,กำลังเปรียบเทียบข้อมูลกับเจ้าอื่น":
					                    case "ลังเลว่าทำแล้วจะได้ประโยชน์กับธุรกิจจริงไหม": 
					                    case "กังวลว่าระบบจะเกินความจำเป็น,มองว่าสิ่งที่มีอยู่ปัจจุบันเพียงพอแล้ว":
					                        dataHits++;
					                        priceHits++;
					                        break;
					                }
					            }
					        }

					        // --- 3. สรุปผลการวิเคราะห์ (Threshold) ---
					        // แค่เจอ 1 Tag ที่ตรงกับกลุ่มนั้น ก็ถือว่ามีพฤติกรรมนั้นทันที (ตั้งเป็น true)
					        customer.setController(controllerHits > 0);
					        customer.setDataOriented(dataHits > 0);
					        customer.setDataOriented(priceHits > 0);

					        // --- 4. กฎเหล็ก (Business Rule: High Spender Override) ---
					        // ถ้าลูกค้าเป็น "กำลังซื้อสูง" เราจะตัดความสนใจด้านราคาออกไปเลย
					        if (customer.isHighSpender()) {
					            customer.setDataOriented(false);
					        }
					    }
				
}
