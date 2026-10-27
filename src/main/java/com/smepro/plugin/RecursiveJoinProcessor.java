package com.smepro.plugin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;

import com.smepro.dao.Identifiable;
import com.smepro.dto.FieldMetaDTO;
import com.smepro.models.eformfield;
import com.smepro.models.keymap;
import com.smepro.service.otherservice;



public class RecursiveJoinProcessor {
	
	
					public List<Map<String, String>> buildData(
					        List<Object> transactions,
					        List<String> mainFields,
					        List<String> chainFields,
					        List<String> extraFields, List<String> extraFields2
					) {
					    List<Map<String, String>> rows = new ArrayList<>();
					    for (Object row : transactions) {
					    	
					    	if (row instanceof Identifiable joinable) {
						    	
							    System.out.println(" size "+joinable.getJoin1().size());
				
					    	}   
						    	
					        processRecursive(row, mainFields, chainFields, extraFields,extraFields2, rows, 0, Map.of(), Map.of());
					    }
					    return rows;
					}
		
					
					
					private void processRecursive(
						    Object row,
						    List<String> mainFields,
						    List<String> chainFields,
						    List<String> extraFields,
						    List<String> extraFields2,
						    List<Map<String, String>> rows,
						    int level,
						    Map<String, String> parentMainMap,
						    Map<String, String> extraParentMap
						) {
						    Map<String, String> resultRow = new LinkedHashMap<>();

						    if (level == 0) {
						
						        // แถวแม่: mainFields และ extraFields จาก row ของแม่
						        for (String field : mainFields) {
						            resultRow.put(field, getFieldValue(row, field));
						        }
						        for (String field : extraFields) {
						            resultRow.put(field, getFieldValue(row, field));
						        }
						        for (String field : extraFields2) {
						            resultRow.put(field, getFieldValue(row, field));
						        }
						  
						        // **ไม่ใส่ chainFields** เพราะ chainFields เป็นข้อมูลลูก
						    } else {
						  
						        // แถวลูก: mainFields, extraFields, chainFields จาก row ลูกเอง
						        for (String field : mainFields) {
						            resultRow.put(field, getFieldValue(row, field));
						        }

						        for (String field : chainFields) {
						            resultRow.put(field, getFieldValue(row, field));
						        }
						        for (String field : extraFields) {
						            resultRow.put(field, getFieldValue(row, field));
						        }
						   
						        for (String field : extraFields2) {
						            resultRow.put(field, getFieldValue(row, field));
						        }
						    }

	
						    rows.add(resultRow);

						    // เตรียมข้อมูล mainFields, extraFields สำหรับลูก
						    Map<String, String> currentMainMap = new HashMap<>();
						    for (String field : mainFields) {
						        currentMainMap.put(field, getFieldValue(row, field));
						    }

						    Map<String, String> currentExtraMap = new HashMap<>();
						    for (String field : extraFields) {
						        currentExtraMap.put(field, getFieldValue(row, field));
						    }

						    List<Object> joinList = getJoinList(row);
						    if (joinList != null && !joinList.isEmpty()) {
						        for (Object child : joinList) {
						            processRecursive(child, mainFields, chainFields, extraFields, extraFields2, rows, level + 1, currentMainMap, currentExtraMap);
						        }
						    }
						}
			  
			  
		
		// ดึงค่าฟิลด์จาก Object ผ่าน reflection
		private String getFieldValue(Object obj, String fieldName) {
				try {
				String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
				Method m = obj.getClass().getMethod(methodName);
				Object val = m.invoke(obj);
				return val != null ? val.toString() : "";
				} catch (Exception e) {
				return "";
				}
		}
		
		// พยายามหาค่า join1 จาก getter หรือ field
		@SuppressWarnings("unchecked")
		private List<Object> getJoinList(Object obj) {
				try {
						// ลองเรียก getJoin1()
						Method m = obj.getClass().getMethod("getJoin1");
						Object val = m.invoke(obj);
						if (val instanceof List<?>) {
						return (List<Object>) val;
						}
						} catch (Exception e) {
						// ไม่มี method getJoin1
						}
						return null;
						}



		public List<FieldMetaDTO> AddFieldsToPrint(otherservice otherservices,List<FieldMetaDTO> fieldsToPrint, List<eformfield> eformfields, Map<String, List<keymap>> keymapChainMap, List<String> extraFields, List<String> extraFields2) {

			
	
			// mainFields จาก eformfields
			for (eformfield field : eformfields) {
			    if (field.getKeymap() != null) {
			        fieldsToPrint.add(new FieldMetaDTO(
			            field.getKeymap().getMainkey(),
			            field.getField_label(), // title
			            field.getKeymap().getChange_format() // format
			        ));
			    }
			}
	
			for (List<keymap> keymaps : keymapChainMap.values()) {
			    for (keymap km : keymaps) {
			    	
			    	System.out.println( km.getMainkey()+ "   "+km.getTitle());
			    	
			    	
			        if (otherservices.IntegerValueOf(km.getEformkey()) > 0) {
			            fieldsToPrint.add(new FieldMetaDTO(
			                km.getMainkey(),
			                km.getTitle(), // ชื่อ field จาก keymap
			                km.getChange_format()
			            ));
			        }
			    }
			    
			    
			}		// extraFields (เช่น lot_batch, inspection, etc)
			
			for (String key : extraFields) {
			    fieldsToPrint.add(new FieldMetaDTO(key, key, "Label")); // default เป็น text
			}
			
			for (String key : extraFields2) {
			    fieldsToPrint.add(new FieldMetaDTO(key, key, "Label")); // default เป็น text
			}
			
			
			Set<String> seen = new HashSet<>();
			List<FieldMetaDTO> uniqueFields = fieldsToPrint.stream()
			    .filter(f -> seen.add(f.getKey()))  // add() คืน true เฉพาะถ้า key ยังไม่เคยเจอ
			    .collect(Collectors.toList());
			
			return uniqueFields;
			
			/*
			// ค่าคงที่
			for (String key : List.of("count", "year", "summarize", "variance", "standard_deviation", "rsd", "title", "binning", "frequency")) {
			    fieldsToPrint.add(new FieldMetaDTO(key, key, "number")); // หรือกำหนดชื่อ title และ format ตามต้องการ
			}
			*/
			
		}
		
		
		
		
}

