package com.smepro.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.IdocDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.userDAO;
import com.smepro.enums.flow;
import com.smepro.models.Idoc;
import com.smepro.models.LoadApp;
import com.smepro.models.Transition;
import com.smepro.models.department;
import com.smepro.models.group_members;
import com.smepro.models.groups;
import com.smepro.models.policies;
import com.smepro.models.sub_processes;
import com.smepro.models.user;


@Service
public class FlowServices {



	private  IdocDAO idocDAO;
	private group_membersDAO Group_membersDAO;
	private groupsDAO GroupsDAO;
	private departmentDAO DepartmentDAO;
	private edataDAO EdataDAO;
	private userDAO UserDAO;
	private userservice Userservice;
	

    @Autowired
    public void setMyRepository(
    		group_membersDAO Group_membersDAO
    		,groupsDAO GroupsDAO
    		,departmentDAO DepartmentDAO
    		,edataDAO EdataDAO
    		,userDAO UserDAO
    		,IdocDAO idocDAO
    		,userservice Userservice
    		) {
    	
        this.Group_membersDAO = Group_membersDAO;
        this.GroupsDAO = GroupsDAO;
        this.DepartmentDAO = DepartmentDAO;
        this.EdataDAO = EdataDAO;
        this.UserDAO = UserDAO;
        this.idocDAO = idocDAO;
        this.Userservice = Userservice;
    }

    
    
    
    private DefinitionBuilder transitions;

    public FlowServices() {
        // Initialize Transition Definitions
        DefinitionBuilder definitionBuilder = new DefinitionBuilder();
        
       
            this.transitions = definitionBuilder
                    .addPlaces(new String[]{"draft","cleared","reviewed", "rejected","partial","canceled","published"})
                    .addTransition(new Transition("to_review", "draft", "reviewed"))
                    .addTransition(new Transition("un_review", "reviewed", "draft"))
                    .addTransition(new Transition("reject", "reviewed", "rejected"))
                    .addTransition(new Transition("to_review_edit", "rejected", "reviewed"))
                    .addTransition(new Transition("to_cleared", "reviewed", "cleared") )
                    .addTransition(new Transition("to_cleared_for_system", "draft", "cleared") )
                   
                
                    .addTransition(new Transition("to_review_cleared", "cleared", "reviewed") )
                    .addTransition(new Transition("publish_cleared", "reviewed", "published") )
                    .addTransition(new Transition("reject_cleared", "reviewed", "cleared") )
                    
                    .addTransition(new Transition("publish", "reviewed", "published"))
                    .addTransition(new Transition("unpublish", "published", "reviewed"))
                    .addTransition(new Transition("unpublish_cleared", "cleared", "reviewed"))
                    
                    .addTransition(new Transition("clear", "cleared", "published") )
                    .addTransition(new Transition("partial", "cleared", "partial") )
                    .addTransition(new Transition("cancel", "cleared", "canceled") )
                    
                    .addTransition(new Transition("unclear", "published", "cleared") )
                    .addTransition(new Transition("unpartial", "partial", "cleared") )
                    .addTransition(new Transition("uncancel", "canceled", "cleared") )
                    .build();
            
            
          /*  
            this.transitions = definitionBuilder
                    .addPlaces(new String[]{"pr","po","gr"})
                    .addTransition(new Transition("publish_pr", "pr", "po"))
                    .addTransition(new Transition("publish_po", "po", "gr"))
                    .build();
            */

    		}

			    public Idoc checkAndCreateWorkflow(int sub_process_id,int eformId, int documentId, int documentrv,int currentUserId) {
			    	
			    	Idoc document = idocDAO.getDocument(eformId,documentId,documentrv); 
			    	  
			        
			        LocalDateTime localDate = LocalDateTime.now();//For reference
			    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			    	String date = localDate.format(formatter);
			    	
			    	
			    	 	if (document == null) {
			    	 		Idoc doc = new Idoc();
			    	 		doc.setCurrent_step( String.valueOf(sub_process_id));
			    	 		doc.setEform_id(eformId);
			    	 		doc.setDocument_id(documentId);
			    	 		doc.setDocument_rv(documentrv);
			    	 		doc.setReceiver(currentUserId);
			    	 		doc.setCreator(currentUserId);
			    	 		doc.setDate(date);
			    	 		idocDAO.save(doc);
			    	 		document = idocDAO.getDocument(eformId,documentId,documentrv); 
			    	  }
			    	return document;
			    }
    
				public Idoc getDocument(int eformId) {
				 	Idoc document = idocDAO.getDocument(eformId); 
				 	return document;
				}
			    
			    
			public Idoc checkAndCreateWorkflow(int eformId,int currentUserId) {
			    	
			    		
			    		System.err.println("eformId ..."+eformId);		
			    		
			    	Idoc document = idocDAO.getDocument(eformId); 
			    	  
			        
			        LocalDateTime localDate = LocalDateTime.now();//For reference
			    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			    	String date = localDate.format(formatter);
			    	
			    	
			    	 	if (document == null) {
			    	 		Idoc doc = new Idoc();
			    	 		doc.setCurrent_step("Create");
			    	 		doc.setCurrent_step_int(0);
			    	 		doc.setEform_id(eformId);
			    	 		doc.setDocument_id(0);
			    	 		doc.setDocument_rv(0);
			    	 		doc.setReceiver(currentUserId);
			    	 		doc.setCreator(currentUserId);
			    	 		doc.setDate(date);
			    	 		idocDAO.save(doc);
			    	 		document = idocDAO.getDocument(eformId); 
			    	  }
			    	return document;
			    }
    
			    
			    
			    
			    
			    
		    public String transitionDocument(int eformId, int documentId, int documentrv, String transitionName, int currentUserId, int to) {
		
		    
		    	Idoc document = idocDAO.getDocument(eformId,documentId,documentrv); 
		      
		
		    	if (document == null) {
		            return "Error:Not found document";
		        }
		
		        
		        
		    	 String currentStep = document.getCurrent_step();
		        Transition transition = getTransition(transitionName,currentStep);
		
		
		        if (transition == null) {
		            return "Error: Invalid workflow path";
		        }
		

		        //idocDAO.transition(eformId,transition.getTo(),currentUserId,to); 
		        
		        // Logic to apply the transition can go here
		        return "Transition successful";
		    }

		    
		    
		    
		    public String transitionDocument(int documentId,String transition, int currentUserId, int to) {  
		    	Idoc document = idocDAO.getDocument(documentId); 

		    	if (document == null) {
		            return "Error:Not found document";
		        }
         
	
		       // idocDAO.transition(documentId,transition,currentUserId,to); 
		        
		        // Logic to apply the transition can go here
		        return "Transition successful";
		    }

		    
		    public String transitionDocument(int documentId, int currentUserId, int to,String next, int flow) {  
		    	Idoc document = idocDAO.getDocument(documentId); 

		    System.out.println("next ---"+next);
			System.out.println("document ---to "+to);
			System.out.println("document ---getId  "+document.getId());
			   
			
		    	if (to == 0) {   return "Transition error"; } 
		    	if (document == null) {return "Transition error";}

		    	 idocDAO.transition(documentId,currentUserId,to,next,flow); 
		    	 
		 		System.out.println("transitionDocument --successful  ");
		    	 
		        return "Transition successful";
		    }

		    
		    public String backtransitionDocument(int documentId, int currentUserId, int to,String next, int flow) {  
		    	Idoc document = idocDAO.getDocument(documentId); 

		    System.out.println("next ---"+next);
			System.out.println("document ---to "+to);
			System.out.println("document ---getId  "+document.getId());
			   
			
		    	if (to == 0) {   return "Transition error"; } 
		    	if (document == null) {return "Transition error";}

		    	 idocDAO.backtransition(documentId,currentUserId,to,next,flow); 
		        return "Transition successful";
		    }

		    
		    
		    
		    public String transitionDocumentAuto(int documentId,String next) {  
		    	Idoc document = idocDAO.getDocument(documentId); 


		    	if (document == null) {return "Transition error";}

		    	 idocDAO.transition(documentId,next); 
		        return "Transition successful";
		    }
		    
		    public String cleared(int eformId, int documentId, int documentrv,int tocleared) {
				
			    
		    	Idoc document = idocDAO.getDocument(eformId,documentId,documentrv); 
		      
		
		    	if (document == null) {
		            return "Error:Not found document";
		        }
		


		        idocDAO.transition(eformId,tocleared); 
		        
		        // Logic to apply the transition can go here
		        return "Transition successful";
		    }

		    
		    
		    public void del(int eformId) {
		    	idocDAO.transition_del(eformId); 
		    }

		    public void setRemarks_Rejected(int eformId,String note) {
		    	idocDAO.setRemarks(eformId,note); 
		    } 
		    
    
		    
		    private Transition getTransition(String transitionName, String currentStep) {
		        // Use the transitions to fetch the appropriate transition
		        Transition transition = transitions.getTransition(transitionName, currentStep);
		
		        if (transition != null) {
		            return transition;
		        }
		
		        return null;
		    }
		    
    
    
    
		    public List<Idoc> getAll(int eformId, int documentId, int documentrv,String currentStep,String by,int currentUserId) {
		    	
		    	if(by.equals(flow.Receiver.getLabel())) {
		    		  List<Idoc> docs =  idocDAO.getALLReceiver(eformId,documentId,documentrv,currentStep,currentUserId); 
		    		  return docs;
		    	}else if(by.equals(flow.Sender.getLabel())) {
		    		  List<Idoc> docs =   idocDAO.getALLSender(eformId,documentId,documentrv,currentStep,currentUserId); 
		    		  return docs;
		    	}else if(by.equals(flow.Creator.getLabel())) {
		   
		    		  List<Idoc> docs = idocDAO.getALLCreator(eformId,documentId,documentrv,currentStep,currentUserId); 
		    		  return docs;
		    	}else {
		    		List<Idoc> docs = idocDAO.getALL(eformId,documentId,documentrv,currentStep,currentUserId); 
		    		return docs;
		    	}

		    }

		    
		    
		    
				public policies getPolicies(int step) {
					 policies policies = EdataDAO.getPolicies(step);
					return policies;
			}
		
		
				public user getRecipient(int step, List<sub_processes> sub_process,int Creator) {
					
					int nextId =step;
					Optional<sub_processes> currentStep = sub_process.stream()
						    .filter(row -> row.getId() == step)           // Match the id
						    .filter(row -> row.getStep().equals("Completion"))  // Match the "Test" step
						    .findFirst();       
                     
					
					if (currentStep.isPresent()) {
					    int currentId = currentStep.get().getId();
					    System.out.println("Current ID: " + currentId);

					    // Find the next ID in the list
					    int currentIndex = sub_process.indexOf(currentStep.get());
					    int nextIndex = currentIndex + 1;

					    if (nextIndex < sub_process.size()) {
					        nextId = sub_process.get(nextIndex).getId();
					        System.out.println("Next ID: " + nextId);
					    } else {
					        System.out.println("No next ID—end of list!");
					    }
					} else {
					    System.out.println("No match found for id " + step + " with step 'Test'");
					}
					
					
				    policies policies = EdataDAO.getPolicies(nextId);
				    if (policies == null || policies.getRespon() == null) {
				    	
				    	
				    	System.err.println(" policies null");
				    	
				        return null; // หรือจะโยน Exception ตามที่เหมาะสม
				        
				        
				        
				    }

				    int respon = 0;
				    
				    if(policies.getRespon().equals("Creator")) {
				    	respon = Creator;
				    }else {
							    String responStr = policies.getRespon().replace("user_", "").trim();
							    try {
							        respon = Integer.parseInt(responStr);
							    } catch (NumberFormatException e) {
							        return null; // หรือจะล็อก Error ไว้ด้วยก็ได้
							    }
				    }
				    
				    return UserDAO.getbyId2(respon);
				}


			

			
		

  
}
