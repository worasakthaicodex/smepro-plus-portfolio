package com.smepro.dao;



import java.util.List;

import com.smepro.models.Idoc;
import com.smepro.models.flow;
import com.smepro.models.sub_processes;



public interface IdocDAO {

	/**
	 * Method สำหรับสร้าง ดึง Idoc  <br>
	 * 
	 * @param 
     * @return Idoc
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	Idoc getDocument(int eformId,int documentId,int documentrv);
	
	
	
	/**
	 * Method สำหรับ save Idoc  <br>
	 * 
	 * 
	 * @param 
     * @return Idoc
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	 int save(Idoc Idoc);
	
	
	
	/**
	 * Method สำหรับ delete  Idoc  <br>
	 * 
	 * 
	 * @param  int eform_id
     * @return int
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	int delete(int eform_id);
	
	
	List<Idoc> getALLReceiver(int eform_id,int document_id,int document_rv,String step,int userid);
	List<Idoc> getALLSender(int eform_id,int document_id,int document_rv,String step,int userid);
	List<Idoc> getALLCreator(int eform_id,int document_id,int document_rv,String step,int userid);
	List<Idoc> getALL(int eform_id,int document_id,int document_rv,String step,int userid);
	
	
	

	
	/**
	 * Method สำหรับ transition ส่งเอกสาร  <br>
	 * 
	 * 
	 * @param  int eformId,String transition,int send,int to
     * @return int
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	int transition(int eformId,int cleared);
	
	 
	/**
	 * Method สำหรับลบ transition   <br>
	 * 
	 * 
	 * @param  int eformId
     * @return int
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	int transition_del(int eformId);
	
	

	
	/**
	 * Method สำหรับsetRemarks  transition   <br>
	 * 
	 * 
	 * @param  int eformId
     * @return int
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	 int setRemarks(int eformId,String note);



	Idoc getDocument(int eform_id);


	/**
	 * Method สำหรับ transition ส่งเอกสาร  <br>
	 * 
	 * 
	 * @param  int eformId,String transition,int send,int to
     * @return int
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	int transition(int eformId, int send, int to, String next);



	int transition(int eformId, int send, int to, String next, int flow);



	int transition(int eformId, String next);



	int backtransition(int eformId, int send, int to, String next, int flow);



	Idoc getDocumentById(int eform_id);



	void commit(int data_id, String type, String string,int action_id);



	void end(int eform_id, String label,String clear ,String remarks_rejected ,String date_end );



	flow getFlow(int data_id, String string);



	void SaveAllIdocExtension(List<Idoc> idocList);



	void UpdateIdocBatch(List<Idoc> idocList);



	void end(int eform_id, String label);



	int recallClosing_status(int eformId);



	void update(Idoc flow);



	void updateRequestToEdit(int dataId, int flagValue, String closing_status, int returnToUser,int current_step_int);



	void endAfterRevis(int eform_id, String closing_status, int current_step_int);



	void setExpires(int data_id, String closing_status_expired, String sub_process_expired);

}
