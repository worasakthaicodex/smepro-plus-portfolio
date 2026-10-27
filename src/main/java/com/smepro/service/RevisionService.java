package com.smepro.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smepro.dao.IdocDAO;
import com.smepro.dao.edataDAO;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Idoc;
import com.smepro.models.Relations;
import com.smepro.models.edata;
import com.smepro.models.process;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.repository.Edata_annual_planRepository;
import com.smepro.repository.SalesFollowupCopyRepository;
import com.smepro.repository.Sales_followupRepository;


@Service
public class RevisionService {
	//รับค่า edata_id mี่จะทำการ Revision
	//iับค่า process_id 9รวจ  process.canReviseConfirmed  ต้องเป็น 1 หรือ ไม่ถูก Confirm Order 
	//dาร Confirm Order   ในระบบเช็คจาก relations.relations_id 9้องไม่มีเเถวนี้เิด โดยใช้ edata_id เช็คถ้ามีนับว่ามีการ  Confirm Order  เบื้องต้นเเล้ว
	
	//การ Copy จะมี  sales_followup ที่ต้องสำเนา โดย edata_id คือ ตัวเอกสาร eform  sales_followup l่วนหัว sales_followup.edata_id  มากกว่า 0 ส่วนลูกคือ 
	//tempId(อันนี้เป็น String เเต่ก็คือรหัส  edata_id นั้นละ) > 0

	//เเต่ต้องสำเนา edata ก่อน ตรงนี้จะต่างกันหน่อย คือ ส่วนหัว ก็ edata.data_id = edata_id   l่วน  tempId เปฌนตัวลูกจะ  edata.tempId gท่ากับ  edata.data_id  -องส่วนหัว 
	
	private edataDAO EdataDAO;
	private SalesFollowupCopyRepository Sales_followup;
	private LogService logService;
	private IdocDAO idocDAO;
	
	@Autowired
	public void setMyRepository(
	    		edataDAO EdataDAO,
	    		SalesFollowupCopyRepository Sales_followup,
	    		LogService logService,
	    		IdocDAO idocDAO
			) { 
			this.EdataDAO = EdataDAO;
			this.Sales_followup = Sales_followup;
			this.logService = logService;
			this.idocDAO = idocDAO;
        }
	
	@Transactional
    public int  executeRevision(int oldEdataId, int processId,user usersed ) throws Exception {
		
		
		System.out.println("oldEdataId "+oldEdataId);
		System.out.println("processId "+processId);
		
	  	process process = EdataDAO.getProcess(processId);
	  	Idoc docs = idocDAO.getDocument(oldEdataId);
	  	
	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	    String date = localDate.format(formatter);
	  	
	  	
        // 1. Validation
        if (!canProcessRevise(oldEdataId, process,docs)) {
        	logService.notice(401,"Error  (Validation / Missing field) : End Unable to revision: The document has been confirmed, or processing permissions do not allow it.", "RevisionService/executeRevision",oldEdataId);	
            throw new Exception("ไม่สามารถ Revision ได้: เอกสารมีการ Confirm แล้ว หรือสิทธิ์ Process ไม่อนุญาต");
        }

        // เริ่ม Transaction (สำคัญมาก เพราะถ้าลูกมาไม่ครบต้อง Rollback ทั้งหมด)
        try {
            // 2. Copy edata (Header)
        	System.out.println("2. Copy edata (Header) ");
            int newEdataId = EdataDAO.copyEdataHeader(oldEdataId);

            
            System.out.println(" newEdataId ==  "+newEdataId);
            
            // 3. Copy edata (Lines/Children)
            // ค้นหาทุกแถวที่ edata.tempId == oldEdataId (String conversion if needed)
         	System.out.println("3. Copy edata (Lines/Children)");
            EdataDAO.copyEdataChildren(oldEdataId, newEdataId);

            
         	System.out.println("4. checkMainData");
            checkMainData(oldEdataId,newEdataId);

        	System.out.println("5. Crevision");
            EdataDAO.revision(oldEdataId,newEdataId);

   
            
            
        	System.out.println("6. end)");
   
            
        	List<sub_processes> sub_process = EdataDAO.getAllSubProcess(processId);
        	sub_processes lastSubProcess = sub_process.get(sub_process.size() - 1);
         	idocDAO.endAfterRevis(oldEdataId, process.getClosingStatusAfterRevise(),lastSubProcess.getId());
         	
         	
        	/*
        	System.out.println("7. new Flow");
	 		Idoc doc = new Idoc();
	 		doc.setCurrent_step("Create");
	 		doc.setCurrent_step_int(sub_process.get(0).getId());
	 		doc.setEform_id(newEdataId);
	 		doc.setDocument_id(docs.getDocument_id());
	 		doc.setDocument_rv(docs.getDocument_rv());
	 		doc.setReceiver(usersed.getUser_id());
	 		doc.setCreator(usersed.getUser_id());
	 		doc.setDate(date);
	 		idocDAO.save(doc);
        	 */
	 		System.out.println("8. newEdataId");
            return newEdataId; // คืนค่า ID ใหม่เพื่อไปเปิดหน้าจอ Revision

        } catch (Exception e) {
            // Rollback Logic
            throw e;
        }
    }

    private void checkMainData(int oldEdataId, int newEdataId) {
		// TODO Auto-generated method stub
    	Edata_maindata maindata = EdataDAO.getEdatamainData(oldEdataId);

    	switch(maindata.getEntity()) {
    		case "sales_followup"  ->{
    		      // 4. Copy sales_followup (Header)
		        // ค้นหา sales_followup.edata_id == oldEdataId
    			Sales_followup.copySalesFollowupHeader(oldEdataId, newEdataId);
		        // 5. Copy sales_followup (Lines/Children)
		        // ค้นหา sales_followup.tempId == oldEdataId (String conversion)
    			Sales_followup.copySalesFollowupChildren(oldEdataId, newEdataId);
    		}
    		}
		
	}

	private boolean canProcessRevise(int edataId, process process, Idoc doc) {
        // TODO: Logic เช็ค process.canReviseConfirmed 
        // และเช็ค COUNT ใน relations_id โดยใช้ edataId

  
    	if(process.getClosingStatusAfterRevise() == null ) {return false; }
    	if(process.getClosingStatusCanRevise() == null ) {return false; }
    	
    	
    	if(!doc.getClosing_status().equals(process.getClosingStatusCanRevise())){return false; }
   

    	
    	if(process.getCanReviseConfirmed() == 1) {
    		return true; 
    	}else {
    		Relations count = EdataDAO.getRelationsParent(edataId);
    		if(count == null )
    			return true; 
    	}
    	return false; 
    }
}