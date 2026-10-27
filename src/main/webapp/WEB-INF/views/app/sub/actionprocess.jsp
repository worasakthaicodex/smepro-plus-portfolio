<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <form method="POST" id="Createapp" modelAttribute="process" action="/savestepprocess-workflow">
            
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
                                <spring:message code="app.CodeGeneration"  text="-​" />
                                                
                                                </a>
                                            </li>
                                           <li class="nav-item">
                                                <a class="nav-link" id="baseVerticalLeft-tab2" data-bs-toggle="tab" aria-controls="tabVerticalLeft2" href="#tabVerticalLeft2" role="tab" aria-selected="false">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path></svg>
                                    การกดรับ
                                                 
                                                 </a>
                                            </li>
                                      
                                            
                                        </ul>
                                        <div class="tab-content">

                                       <div class="tab-pane active" id="tabVerticalLeft1" role="tabpanel" aria-labelledby="baseVerticalLeft-tab1">   
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
               <div class="tab-pane" id="tabVerticalLeft2" role="tabpanel" aria-labelledby="baseVerticalLeft-tab2">
                                        
                                        
                                        
                                        การกดรับ :
                                               <select name="accepting" class="form-control">
                                       		  		 <option value="0" <c:if test="${sub_processes.accepting == 0 }">selected</c:if >  >ไม่ใช้งาน</option>
                                           			  <option value="1" <c:if test="${sub_processes.accepting == 1 }">selected</c:if > >กดรับ</option>
                                           		   	 <option value="2" <c:if test="${sub_processes.accepting == 2 }">selected</c:if > >กดรับเเละสามารถตีกลับได้</option>
                                          </select>
                                          <br> 
                                          
                                      
                                          Input ที่เข้าถึงไม่ได้  :
                                        <select name="prohibited2" class="select2"  multiple="multiple">
                                       		  			<c:forEach var="item" items="${eformfields}">
                                           		   			 <option value="${item.field_id }"     
                                           		   			  <c:if test='${fn:contains(sub_processes.prohibited2,item.field_id.toString()) }'>selected</c:if >>
                                           		   			  ${item.field_label }</option>
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
                                                                                           