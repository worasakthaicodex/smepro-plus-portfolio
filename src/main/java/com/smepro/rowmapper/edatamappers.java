package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.file;


public class edatamappers implements RowMapper<edata> {

	@Override
	public  edata mapRow(ResultSet rs, int rowNum) throws SQLException {
		 edata  edatas = new  edata();
		 
		 
		 if (hasColumn(rs, "data_id")) {
			 edatas.setData_id(rs.getInt("data_id"));
	        }
		  if (hasColumn(rs, "data_planid")) {
				edatas.setData_planid(rs.getInt("data_planid"));
	        } 
		  if (hasColumn(rs, "data_eformid")) {
				edatas.setData_eformid(rs.getInt("data_eformid"));
	        } 
		  if (hasColumn(rs, "data_eformrv")) {
				edatas.setData_eformrv(rs.getInt("data_eformrv"));
	        } 
		  if (hasColumn(rs, "data_idadd")) {
				edatas.setData_idadd(rs.getInt("data_idadd"));
	        } 
		  if (hasColumn(rs, "data_nameadd")) {
				edatas.setData_nameadd(rs.getString("data_nameadd"));
	        } 
		  if (hasColumn(rs, "data_dateadd")) {
				edatas.setData_dateadd(rs.getString("data_dateadd"));
	        } 
		  if (hasColumn(rs, "data_dateplan")) {
				edatas.setData_dateplan(rs.getString("data_dateplan"));
	        } 
		  if (hasColumn(rs, "data_plantype")) {
				edatas.setData_plantype(rs.getString("data_plantype"));
	        }  
		  if (hasColumn(rs, "data_filetype")) {
				edatas.setData_filetype(rs.getString("data_filetype"));
	        }  	 
		  if (hasColumn(rs, "data_filepath")) {
				edatas.setData_filepath(rs.getString("data_filepath"));
	        }  	 
		  if (hasColumn(rs, "data_idapp")) {
				edatas.setData_idapp(rs.getInt("data_idapp"));
	        }  	 

		  if (hasColumn(rs, "data_nameapp")) {
				edatas.setData_nameapp(rs.getString("data_nameapp"));
	        }  	
		  if (hasColumn(rs, "data_comment")) {
				edatas.setData_comment(rs.getString("data_comment"));
	        }  	
		  if (hasColumn(rs, "data_analyst")) {
				edatas.setData_analyst(rs.getString("data_analyst"));
	        }  	
		 
		  if (hasColumn(rs, "data_title1")) {
				edatas.setData_title1(rs.getString("data_title1"));
	        }  	
		  if (hasColumn(rs, "data_value1")) {
				edatas.setData_value1(rs.getString("data_value1"));
	        }  	

		  if (hasColumn(rs, "data_title2")) {
				edatas.setData_title2(rs.getString("data_title2"));
	        }  	
		  if (hasColumn(rs, "data_value2")) {
				edatas.setData_value2(rs.getString("data_value2"));
	        }  	

		  if (hasColumn(rs, "data_title3")) {
				edatas.setData_title3(rs.getString("data_title3"));
	        }  	
		  if (hasColumn(rs, "data_value3")) {
				edatas.setData_value3(rs.getString("data_value3"));
	        }  	
		  if (hasColumn(rs, "data_title4")) {
				edatas.setData_title4(rs.getString("data_title4"));
	        }  	
		  if (hasColumn(rs, "data_value4")) {
				edatas.setData_value4(rs.getString("data_value4"));
	        }  	

		  if (hasColumn(rs, "data_title5")) {
				edatas.setData_title5(rs.getString("data_title5"));
	        }  	
		  if (hasColumn(rs, "data_value5")) {
				edatas.setData_value5(rs.getString("data_value5"));
	        }  	

		  if (hasColumn(rs, "data_title6")) {
				edatas.setData_title6(rs.getString("data_title6"));
	        }  	
		  if (hasColumn(rs, "data_value6")) {
				edatas.setData_value6(rs.getString("data_value6"));
	        }  	 

		  if (hasColumn(rs, "data_title7")) {
				edatas.setData_title7(rs.getString("data_title7"));
	        }  	
		  if (hasColumn(rs, "data_value7")) {
				edatas.setData_value7(rs.getString("data_value7"));
	        }  	
		 
		  if (hasColumn(rs, "data_title8")) {
				edatas.setData_title8(rs.getString("data_title8"));
	        }  	
		  if (hasColumn(rs, "data_value8")) {
				edatas.setData_value8(rs.getString("data_value8"));
	        }  	
		  if (hasColumn(rs, "data_title9")) {
				edatas.setData_title9(rs.getString("data_title9"));
	        }  	
		  if (hasColumn(rs, "data_value9")) {
				edatas.setData_value9(rs.getString("data_value9"));
	        }  	
		  if (hasColumn(rs, "data_title10")) {
				edatas.setData_title10(rs.getString("data_title10"));
	        }  	
		  if (hasColumn(rs, "data_value10")) {
				edatas.setData_value10(rs.getString("data_value10"));
	        }  	

		  if (hasColumn(rs, "data_title11")) {
				edatas.setData_title11(rs.getString("data_title11"));
	        }  	
		  if (hasColumn(rs, "data_value11")) {
				edatas.setData_value11(rs.getString("data_value11"));
	        }  
		 
		 
		  if (hasColumn(rs, "data_title12")) {
				edatas.setData_title12(rs.getString("data_title12"));
	        }  	
		  if (hasColumn(rs, "data_value12")) {
				edatas.setData_value12(rs.getString("data_value12"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title13")) {
				edatas.setData_title13(rs.getString("data_title13"));
	        }  	
		  if (hasColumn(rs, "data_value13")) {
				edatas.setData_value13(rs.getString("data_value13"));
	        }  
		 
		 
		  if (hasColumn(rs, "data_title14")) {
				edatas.setData_title14(rs.getString("data_title14"));
	        }  	
		  if (hasColumn(rs, "data_value14")) {
				edatas.setData_value14(rs.getString("data_value14"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title15")) {
				edatas.setData_title15(rs.getString("data_title15"));
	        }  	
		  if (hasColumn(rs, "data_value15")) {
				edatas.setData_value15(rs.getString("data_value15"));
	        }  
		 
		 
		  if (hasColumn(rs, "data_title16")) {
				edatas.setData_title16(rs.getString("data_title16"));
	        }  	
		 	 if (hasColumn(rs, "data_value16")) {
				edatas.setData_value16(rs.getString("data_value16"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title17")) {
				edatas.setData_title17(rs.getString("data_title17"));
	        }  	
		  if (hasColumn(rs, "data_value17")) {
				edatas.setData_value17(rs.getString("data_value17"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title18")) {
				edatas.setData_title18(rs.getString("data_title18"));
	        }  	
		  if (hasColumn(rs, "data_value18")) {
				edatas.setData_value18(rs.getString("data_value18"));
	        }  	
		 
		  if (hasColumn(rs, "data_title19")) {
				edatas.setData_title19(rs.getString("data_title19"));
	        }  	
		  if (hasColumn(rs, "data_value19")) {
				edatas.setData_value19(rs.getString("data_value19"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_title20")) {
				edatas.setData_title20(rs.getString("data_title20"));
	        }  	
		  if (hasColumn(rs, "data_value20")) {
				edatas.setData_value20(rs.getString("data_value20"));
	        }  	
		 
		 
		  if (hasColumn(rs, "data_due")) {
				edatas.setData_due(rs.getString("data_due"));
	        }  	
		  if (hasColumn(rs, "data_department")) {
				edatas.setData_department(rs.getString("data_department"));
	        }  	
		  if (hasColumn(rs, "data_status")) {
				edatas.setData_status(rs.getString("data_status"));
	        }  	
		  if (hasColumn(rs, "auto_read")) {
				edatas.setAuto_read(rs.getInt("auto_read"));
	        }  	
		 
		  if (hasColumn(rs, "alert")) {
				edatas.setAlert(rs.getInt("alert"));
	        }  
		 
		  if (hasColumn(rs, "creator")) {
				edatas.setCreator(rs.getInt("creator"));
	        }  
		  if (hasColumn(rs, "sender")) {
				edatas.setSender(rs.getInt("sender"));
	        }   
		  if (hasColumn(rs, "receiver")) {
				edatas.setReceiver(rs.getInt("receiver"));
	        }   
		 
		  if (hasColumn(rs, "current_step")) {
				edatas.setCurrent_step(rs.getString("current_step"));
	        }   
		 
		  if (hasColumn(rs, "data_namerefer")) {
				edatas.setData_namerefer(rs.getString("data_namerefer"));
	        }   
		 
		  if (hasColumn(rs, "data_title")) {
				edatas.setData_title(rs.getString("data_title"));
	        }   
		 
		  if (hasColumn(rs, "date_clear")) {
				edatas.setDate_clear(rs.getString("date_clear"));
	        }    
		 
		  if (hasColumn(rs, "cleared")) {
				edatas.setCleared(rs.getInt("cleared"));
	        }   
		  if (hasColumn(rs, "data_flow")) {
				edatas.setData_flow(rs.getInt("data_flow"));
	        }    
	
		 
		  if (hasColumn(rs, "production_directive")) {
				edatas.setProduction_directive(rs.getString("production_directive"));
	        }    
		  if (hasColumn(rs, "batch")) {
				edatas.setBatch(rs.getString("batch"));
	        }    
		  if (hasColumn(rs, "equipment")) {
				edatas.setEquipment(rs.getString("equipment"));
	        }    
		  if (hasColumn(rs, "inspection_result")) {
				edatas.setInspection_result(rs.getString("inspection_result"));
	        }    
		  
		  if (hasColumn(rs, "product")) {
				edatas.setProduct(rs.getString("product"));
	        }    
		  if (hasColumn(rs, "start")) {
				edatas.setStart(rs.getString("start"));
	        }    
		  if (hasColumn(rs, "end")) {
				edatas.setEnd(rs.getString("end"));
	        }    
		  if (hasColumn(rs, "quality_control")) {
				edatas.setQuality_control(rs.getString("quality_control"));
	        }    
		  if (hasColumn(rs, "good_production_output")) {
				edatas.setGood_production_output(rs.getString("good_production_output"));
	        }    
		  if (hasColumn(rs, "title")) {
				edatas.setTitle(rs.getString("title"));
	        }    

		  if (hasColumn(rs, "customer")) {
				edatas.setCustomer(rs.getString("customer"));
	        }    
		  if (hasColumn(rs, "equipment")) {
				edatas.setEquipment(rs.getString("equipment"));
	        }  
		  if (hasColumn(rs, "quality_control")) {
				edatas.setQuality_control(rs.getString("quality_control"));
	        }  
		  if (hasColumn(rs, "inspection_result")) {
				edatas.setInspection_result(rs.getString("inspection_result"));
	        }  
		  if (hasColumn(rs, "deviations")) {
				edatas.setDeviations(rs.getString("deviations"));
	        } 
		  if (hasColumn(rs, "start")) {
				edatas.setStart(rs.getString("start"));
	        }  
		  if (hasColumn(rs, "end")) {
				edatas.setEnd(rs.getString("end"));
	        }
		  if (hasColumn(rs, "incoming")) {
				edatas.setIncoming(rs.getString("incoming"));
	        }
		  if (hasColumn(rs, "deliver")) {
				edatas.setDeliver(rs.getString("deliver"));
	        }   
		  if (hasColumn(rs, "material")) {
				edatas.setMaterial(rs.getString("material"));
	        }
		  if (hasColumn(rs, "manufacturer")) {
				edatas.setManufacturer(rs.getString("manufacturer"));
	        }
		  if (hasColumn(rs, "batch_material")) {
				edatas.setBatch_material(rs.getString("batch_material"));
	        }
		  if (hasColumn(rs, "unit")) {
				edatas.setUnit(rs.getString("unit"));
	        }
		  if (hasColumn(rs, "storage_space")) {
				edatas.setStorage_space(rs.getString("storage_space"));
	        }
		  if (hasColumn(rs, "report_pn_datecheck")) {
				edatas.setReport_pn_datecheck(rs.getString("report_pn_datecheck"));
	        }
		  if (hasColumn(rs, "data_datecheck")) {
			  edatas.setData_datecheck(rs.getString("data_datecheck"));
	        }
		  
		 
		  if (hasColumn(rs, "purchase_order")) {
			  edatas.setPurchase_order(rs.getString("purchase_order"));
	        }
		  if (hasColumn(rs, "delivery_date")) {
			  edatas.setDelivery_date(rs.getString("delivery_date"));
	        }
		  if (hasColumn(rs, "lead_time")) {
			  edatas.setLead_time(rs.getString("lead_time"));
	        }
		  if (hasColumn(rs, "defective_production_output")) {
			  edatas.setDefective_production_output(rs.getString("defective_production_output"));
	        }
		  if (hasColumn(rs, "customer_info")) {
			  edatas.setCustomer_info(rs.getString("customer_info"));
	        }
		  if (hasColumn(rs, "wip_incoming")) {
			  edatas.setWip_incoming(rs.getString("wip_incoming"));
	        }
		  if (hasColumn(rs, "wip_deliver")) {
			  edatas.setWip_deliver(rs.getString("wip_deliver"));
	        }
		  if (hasColumn(rs, "fg_incoming")) {
			  edatas.setFg_incoming(rs.getString("fg_incoming"));
	        }
		  if (hasColumn(rs, "fg_deliver")) {
			  edatas.setFg_deliver(rs.getString("fg_deliver"));
	        }
		  if (hasColumn(rs, "material_cost")) {
			  edatas.setMaterial_cost(rs.getString("material_cost"));
	        }
		  if (hasColumn(rs, "material_usage")) {
			  edatas.setMaterial_usage(rs.getString("material_usage"));
	        }
		  if (hasColumn(rs, "material_batch_usage")) {
			  edatas.setMaterial_batch_usage(rs.getString("material_batch_usage"));
	        }
		  if (hasColumn(rs, "wip")) {
			  edatas.setWip(rs.getString("wip"));
	        }
		  if (hasColumn(rs, "batch_wip")) {
			  edatas.setBatch_wip(rs.getString("batch_wip"));
	        }
		  if (hasColumn(rs, "fg")) {
			  edatas.setFg(rs.getString("fg"));
	        }
		  if (hasColumn(rs, "batch_fg")) {
			  edatas.setBatch_fg(rs.getString("batch_fg"));
	        }
		  
		  if (hasColumn(rs, "attachdocuments")) {
			  edatas.setAttachdocuments(rs.getInt("attachdocuments"));
	        }
		  if (hasColumn(rs, "attach_type")) {
			  edatas.setAttach_type(rs.getString("attach_type"));
	        }
		  if (hasColumn(rs, "attachdocuments")) {
			  edatas.setAttachdocuments(rs.getInt("attachdocuments"));
	        }
		  if (hasColumn(rs, "attach_type")) {
			  edatas.setAttach_type(rs.getString("attach_type"));
	        }
		  if (hasColumn(rs, "reference")) {
			  edatas.setReference(rs.getString("reference"));
	        }
		  if (hasColumn(rs, "id_refer")) {
			  edatas.setId_refer(rs.getInt("id_refer"));
	        }
		  if (hasColumn(rs, "process_id")) {
			  edatas.setProcesses_id(rs.getInt("process_id"));
			  edatas.setProcessId(rs.getInt("process_id"));
	        }
		
		  
		  if (hasColumn(rs, "edata_index_action_type")) {
			  edatas.setEdata_index_action_type(rs.getInt("edata_index_action_type"));
	        }
		  if (hasColumn(rs, "problem")) {
			  edatas.setProblem(rs.getString("problem"));
	        }
		  
		  if (hasColumn(rs, "data_dateupdate")) {
			  edatas.setData_dateupdate(rs.getString("data_dateupdate"));
	        }  
		  if (hasColumn(rs, "soft_delete")) {
			  edatas.setSoft_delete(rs.getString("soft_delete"));
	        }  
		  
		  if (hasColumn(rs, "followup")) {
			  edatas.setFollowup(rs.getInt("followup"));
	        }  
		  
		  if (hasColumn(rs, "acknowledge")) {
			  edatas.setAcknowledge(rs.getInt("acknowledge"));
	        }  
		  
		  
		  
		  if (hasColumn(rs, "reference_oldsys	")) {
			  edatas.setReference_oldsys(rs.getString("reference_oldsys	"));
	        }  
		  
		  if (hasColumn(rs, "id_oldsys")) {
			  edatas.setId_oldsys(rs.getString("id_oldsys"));
	        }  
		  
		  if (hasColumn(rs, "data_refer")) {
			  edatas.setData_refer(rs.getString("data_refer"));
	        }  
		  
		  
		  if (hasColumn(rs, "closing_status")) {
			  edatas.setClosing_status(rs.getString("closing_status"));
	        }  

			if (hasColumn(rs, "acknowledge_user")) {
				edatas.setAcknowledge_user(rs.getInt("acknowledge_user"));
		       }
			if (hasColumn(rs, "data_date_receiver")) {
				edatas.setData_date_receiver(rs.getString("data_date_receiver"));
		       }
		  
			if (hasColumn(rs, "id_customer_oldsys")) {
				edatas.setId_customer_oldsys(rs.getString("id_customer_oldsys"));
		       }
			if (hasColumn(rs, "id_refer_oldsys")) {
				edatas.setId_refer_oldsys(rs.getString("id_refer_oldsys"));
		       }
		  
			
			 if (hasColumn(rs, "doc_id")) {
				 
				 if(rs.getInt("doc_id") > 0) {
				 	String rv ="";
			 		String DOC ="";
			 		if(rs.getInt("doc_rv") > 0) {
			 			rv ="-R"+rs.getInt("doc_rv"); 
			 		}
			 			DOC = rs.getString("doc_type")+rs.getString("doc_date")+"-"+rs.getInt("doc_autoid");
			 			edatas.setDoc(DOC+rv);
			    }
		 }
		 
		  
			 
			 

			edatas.setData_value21(rs.getString("data_value21"));
			edatas.setData_value22(rs.getString("data_value22"));
			edatas.setData_value23(rs.getString("data_value23"));
			edatas.setData_value24(rs.getString("data_value24"));
			edatas.setData_value25(rs.getString("data_value25"));
			edatas.setData_value26(rs.getString("data_value26"));
			edatas.setData_value27(rs.getString("data_value27"));
			edatas.setData_value28(rs.getString("data_value28"));
			edatas.setData_value29(rs.getString("data_value29"));
			edatas.setData_value30(rs.getString("data_value30"));
		  
		return edatas;
	
	}

	
	
	   private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
	        ResultSetMetaData rsMetaData = rs.getMetaData();
	        int columnCount = rsMetaData.getColumnCount();
	        for (int i = 1; i <= columnCount; i++) {
	            if (columnName.equalsIgnoreCase(rsMetaData.getColumnName(i))) {
	                return true;
	            }
	        }
	        return false;
	    }
}
