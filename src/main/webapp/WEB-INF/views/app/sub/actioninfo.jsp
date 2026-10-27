<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <form method="POST" id="Createapp" modelAttribute="process" action="/savestepinfo-workflow">
            
<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">

                                    
                                    
                                    
                                    <div class="tab-content">
                               
                                         <div class="tab-pane active" id="actiontab" aria-labelledby="action-tab" role="tabpanel">
                                        <br>    
                                     <div class="nav-vertical">
                                        <ul class="nav nav-tabs nav-left flex-column" role="tablist" style="height: 98px;">
                                           
                                           
                                            <li class="nav-item">
                                                <a class="nav-link active" id="baseVerticalLeft-tab1" data-bs-toggle="tab" aria-controls="tabVerticalLeft1" href="#tabVerticalLeft1" role="tab" aria-selected="true">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check-square"><polyline points="9 11 12 14 22 4"></polyline><path d="M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"></path></svg>    
                                     Input ที่ไม่ใช้
                                                
                                                </a>
                                            </li>
                                           <li class="nav-item">
                                                <a class="nav-link" id="baseVerticalLeft-tab2" data-bs-toggle="tab" aria-controls="tabVerticalLeft2" href="#tabVerticalLeft2" role="tab" aria-selected="false">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path></svg>
                                      Input ที่อ่านอย่างเดียว
                                                 
                                                 </a>
                                            </li>
                                               <li class="nav-item">
                                                <a class="nav-link" id="baseVerticalLeft-tab2" data-bs-toggle="tab" aria-controls="tabVerticalLeft3" href="#tabVerticalLeft3" role="tab" aria-selected="false">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path></svg>
                                    ประเภทข้อมูลที่เข้าถึงได้
                                                 
                                                 </a>
                                            </li> 
                                            
                                        </ul>
                                        <div class="tab-content">

                                       <div class="tab-pane active" id="tabVerticalLeft1" role="tabpanel" aria-labelledby="baseVerticalLeft-tab1">   
                                 
                                           <select name="visibility" class="select2"  multiple="multiple">
                                       		  			<c:forEach var="item" items="${eformfields}">
                                           		   			 <option value="${item.field_id }"     
                                           		   			  <c:if test='${fn:contains(sub_processes.visibility,item.field_id.toString()) }'>selected</c:if >>
                                           		   			  ${item.field_label }</option>
                                           		   	 </c:forEach>
                                           		   	 
                                           		   	 
                                           		   	 
                                          </select>
                                          
                                                  <br> <br> <br>
                                               
                                                    <br> <br> <br>
                                                        <br> <br> <br>
                                               
                                                    <br> <br> <br>
                                            </div>              
               <div class="tab-pane" id="tabVerticalLeft2" role="tabpanel" aria-labelledby="baseVerticalLeft-tab2">
                                        
                                        
                                        <select name="prohibited1" class="select2"  multiple="multiple">
                                       		  			<c:forEach var="item" items="${eformfields}">
                                           		   			 <option value="${item.field_id }"     
                                           		   			  <c:if test='${fn:contains(sub_processes.prohibited1,item.field_id.toString()) }'>selected</c:if >>
                                           		   			  ${item.field_label }</option>
                                           		   	 </c:forEach>
                                           		   	 
                                           		   	 
                                           		   	 
                                          </select>
                                          
                                               <br> <br> <br>
                                               
                                                    <br> <br> <br>
                                                        <br> <br> <br>
                                               
                                                    <br> <br> <br>
               </div>
                                            
                              
                      
                      
                      
                      
<div class="tab-pane" id="tabVerticalLeft3" role="tabpanel" aria-labelledby="baseVerticalLeft-tab3">
                                        
                                        
                                        <select name="enums" class="select2"  multiple="multiple">
                                       		  			<c:forEach var="item" items="${enums}">
                                           		   			 <option value="${item.dataset_data_title }"     
                                           		   			  <c:if test='${fn:contains(processes.enums,item.dataset_data_title) }'>selected</c:if >>
                                           		   			  ${item.dataset_data_title }</option>
                                           		   	 </c:forEach> 
                                          </select>
                                          
                                               <br> <br> <br>
                                               
                                                    <br> <br> <br>
                                                        <br> <br> <br>
                                               
                                                    <br> <br> <br>
 </div>
                                
                                        
                                            </div>
                                            
                                            
               
                                            
                                            
                                            
                                        </div>
                                    </div>
                                         </div>  
                                        
                                        
                                        
                                        
                                   
                                        
                              
    
	      <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>      
  
      <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">         
                                                     <input type="hidden"  name="sub_processes_id" id="sub_processes_id" value="${sub_processes.id }"  class="form-control">            
                                                     <input type="hidden"  name="processe_id" id="processe_id"value="${processes.id }"  class="form-control">
                                                     <input type="hidden" class="form-select"  name="workflow_id" value="${workflows.workflow_id }"> 
                                                     
                                                    	
                                                            <div class="modal-footer" >
                                                              <a  href="/docworkflowapp-workflow"  target="_blank" class="btn btn-light waves-effect waves-float waves-light" >Doc</a>
                                                                <input type="Submit"  value="Save"  class="btn btn-primary waves-effect waves-float waves-light">
                                                            </div>
                                                </form>
                                                                                           