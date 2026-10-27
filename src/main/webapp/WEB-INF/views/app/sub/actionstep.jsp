
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
 <form method="POST" id="Createapp" modelAttribute="process" action="/savestepaction-workflow">
                                  
                                                
<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">

   <ul class="nav nav-tabs" role="tablist">
                                
                                          <li class="nav-item">
                                            <a class="nav-link active" id="profileIcon-tab" data-bs-toggle="tab" href="#actiontab" aria-controls="profile" role="tab" aria-selected="false">
                                              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-database"><ellipse cx="12" cy="5" rx="9" ry="3"></ellipse><path d="M21 12c0 1.66-4 3-9 3s-9-1.34-9-3"></path><path d="M3 5v14c0 1.66 4 3 9 3s9-1.34 9-3V5"></path></svg>
                                                         <spring:message code="app.action" text="-" /></a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="profileIcon-tab2" data-bs-toggle="tab" href="#actiontab2" aria-controls="profile" role="tab" aria-selected="false">
                                               <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-key"><path d="M21 2l-2 2m-7.61 7.61a5.5 5.5 0 1 1-7.778 7.778 5.5 5.5 0 0 1 7.777-7.777zm0 0L15.5 7.5m0 0l3 3L22 7l-3-3m-3.5 3.5L19 4"></path></svg>
                                         <spring:message code="app.CodeGeneration"  text="-​" />
                                                         </a>
                                        </li>
                                      
                                    </ul>
     
                                    <div class="tab-content">
                                        <div class="tab-pane " id="homeIcon" aria-labelledby="homeIcon-tab" role="tabpanel"></div>
                                         <div class="tab-pane active" id="actiontab" aria-labelledby="action-tab" role="tabpanel">
                                       <%@ include file="action.jspf" %>  
                                         </div>  

                                          <div class="tab-pane" id="actiontab3" aria-labelledby="action-tab3" role="tabpanel"></div>
                                        
                                      <div class="tab-pane" id="actiontab2" aria-labelledby="action-tab2" role="tabpanel"> 
                                      
                                        <div class="modal-body">
                                           
                                           <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" name="activate_autoid"  value="1"
                                             <c:if test="${sub_processes.activate_autoid== 1 }">checked</c:if> >
                                            <label class="form-check-label" for="inlineCheckbox1"><spring:message code="app.activate"  text="-​" /></label>
                                        </div> 
                                        
                                              <br><br>          
                                    <small class="text-muted"><spring:message code="app.autoid_info"  text="-​" /> .</small>
                                   <br><br>
                            
                                                             
                                       <div id="step2_1">                      
                                                       <div class="mb-1 row">
                                                
                                                           <div class="col-sm-6"><spring:message code="app.e-formcolumn"  text="-​" />  </div>
                                                             <div class="col-sm-6"><spring:message code="app.prefixes"  text="-​" /></div>
                                                            </div>
                                                             
                                                               <div class="mb-1 row">  
                                                             
		                                       
                                                        <div class="col-sm-6">
                                                        
                                                        
                                                        	


                                                        
                                                             <select class="form-select" name="autoiddatacolumn">
											                                   <option value="0">-</option>
											                            
											                              <c:forEach items="${eformfields }" var="fields" >      
										 <option value="${fields.field_id}" <c:if test="${sub_processes.autoiddatacolumn== fields.field_id }">selected</c:if> >${fields.field_label}</option>
											                               </c:forEach>
											                                   
										 	                               </select>
										 	                   
										 	                               
                                                        </div>
                                                         <div class="col-sm-6">
                                                     <input type="text" class="form-control" name="code_app" value="${sub_processes.code_app}">
                                                                  
                                                         </div>
                                               
                                                         
                                                        
                                             
                                                                   
 
                                        
                                        </div>
                                
                                            </div>
                                                 </div>
                                                 
                                      
                                      </div>
                                      
                                      
                                      
                                      
                           			    </div>
                                    
                                     
                        
                                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">         
                                                     <input type="hidden"  name="sub_processes_id" id="sub_processes_id" value="${sub_processes.id }"  class="form-control">            
                                                     <input type="hidden"  name="processe_id" id="processe_id"value="${processes.id }"  class="form-control">
                                                     <input type="hidden" class="form-select"  name="workflow_id" value="${workflows.workflow_id }"> 
                                                     
                                                    	
                                                            <div class="modal-footer" >
                                                              <a  href="/docworkflowapp-workflow"  target="_blank" class="btn btn-light waves-effect waves-float waves-light" >Doc</a>
                                                                <input type="Submit"  value="Save"  class="btn btn-primary waves-effect waves-float waves-light">
                                                            </div>
                                                </form>
                                                                                 