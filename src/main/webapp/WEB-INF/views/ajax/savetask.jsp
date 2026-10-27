<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
                

                                    <div class="card" id="card${tasks.task_id}">
                                   <div class="card-body">
                                        <h4 class="card-title">${tasks.task_name }</h4>
                                        <div class="d-flex">
                                            <div class="avatar me-50">
                                                <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="Avatar" width="24" height="24">
                                            </div>
                                            <div class="author-info">
                                                <small class="text-muted me-25">by</small>
                                                <small><a href="#" class="text-body">${useradd.user_name }</a></small>
                                                <span class="text-muted ms-50 me-25">|</span>
                                                <small class="text-muted"> ${tasks.task_datestart } - ${tasks.task_dateend } </small><br>
                                     
                                                
                                                                                                                 
                              
                          
                                            </div>
                                        </div>
                                        <div class="my-1 py-25">
                                        
                                        
                                        
                                           <c:if test="${tasks.task_status == 1 }">
                                            <span class="badge rounded-pill badge-light-info"><spring:message code="status.inprocess" /></span>
                                            </small>
                                   </c:if>
                                       <c:if test="${tasks.task_status == 2 }">
                                            <span class="badge rounded-pill badge-light-success"><spring:message code="status.success" /></span>
                                            </small>
                                   </c:if>   
                                            <c:if test="${tasks.task_status == -1 }">
                                            <span class="badge rounded-pill badge-light-danger"><spring:message code="status.lated" /></span>
                                            </small>
                                   </c:if>      
                                        </div>
                                  
                                    
                                        <p class="card-text mb-2">
                                         ${note }
                                        </p>
                                        
                                  <br>
                                     
                                         
                                             <br>  
                                  
                                       
                                     
                                        
                                        
                                        
                                      <b class="mb-50">-<spring:message code="task.documents" /></b>
                                        <ul class="p-0 mb-2">
                                            
                                                <c:forEach var="row" items="${files}" begin="0" end="5">
		                                            <li class="d-block">
		                                                <span class="me-25">-</span>
		                                                <span>	<a href="/render2-filemanager?file=${row.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">${row.file_name_original }</a>
									     
									     
									     </span>
									     
					  
									      <span class="me-25"><a  href="/document-filemanager/${Pathfulls}/${row.file_name}" 
									       onclick="filedownload('${row.file_id }')" class="btn btn-sm btn-success" download>โหลดไฟล์</a></span>
		                                            </li>
		                                            <br>
		                                         </c:forEach>  
                                            
                                    
                                        </ul>
                                        
                                         <b class="mb-50">- <spring:message code="task.responsible" /></b>
                                        <ul class="p-0 mb-2">
                                            
                                              <c:if test='${tasks.task_iduser != null && not empty tasks.task_iduser }'>
                                            <c:set var="namesArray" value="${fn:split(tasks.task_iduser, ',')}" />

												<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5">
											
												    
												        <li class="d-block">
                                                <span class="me-25">-</span>
                                                <span>${nameItem}</span>
                                            </li>
                                           
												</c:forEach>
                                         </c:if>
                                            
                                    
                                        </ul>
                                        
                                        
                                        <div class="accordion accordion-border" id="accordionBorder" data-toggle-hover="true">
                                            <div class="accordion-item">
                                                <h2 class="accordion-header" id="headingBorderOne">
                                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordionBorderOne" aria-expanded="false" aria-controls="accordionBorderOne">
                                                   <spring:message code="project.key_activity"  />
                                                    </button>
                                                </h2>
                                                <div id="accordionBorderOne" class="accordion-collapse collapse" aria-labelledby="headingBorderOne" data-bs-parent="#accordionBorder">
                                                    <div class="accordion-body">
                                                      <c:forEach var="row" items="${key_activity}" >
		                                            <li class="d-block">
		                                                <span class="me-25">-</span>
		                                                <span>${row.expected_results} </span>
		                                            </li>
		                                      
		                                         </c:forEach>  
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="accordion-item">
                                                <h2 class="accordion-header" id="headingBorderTwo">
                                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordionBorderTwo" aria-expanded="false" aria-controls="accordionBorderTwo">
                                                   <spring:message code="task.take_action" />
                                                    </button>
                                                </h2>
                                                <div id="accordionBorderTwo" class="accordion-collapse collapse" aria-labelledby="headingBorderTwo" data-bs-parent="#accordionBorder">
                                                    <div class="accordion-body">
                                                    <c:forEach var="row" items="${milestones_plan}" begin="0" end="5">
		                                            <li class="d-block">
		                                                <span class="me-25">-</span>
		                                                <span>	${row.detail} [${row.date_due }]
									     				 </span>
				
		                                            </li>
		                                        
		                                         </c:forEach>  
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="accordion-item">
                                                <h2 class="accordion-header" id="headingBorderThree">
                                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordionBorderThree" aria-expanded="false" aria-controls="accordionBorderThree">
                                                   <spring:message code="task.budget" />
                                                    </button>
                                                </h2>
                                                <div id="accordionBorderThree" class="accordion-collapse collapse" aria-labelledby="headingBorderThree" data-bs-parent="#accordionBorder">
                                                    <div class="accordion-body">
                                                           <c:forEach var="row" items="${budget_plan1}" begin="0" end="5">
		                                            <li class="d-block">
		                                                <span class="me-25">-</span>
		                                                <span>${row.detail} : ${tasks.task_budget1 } [<spring:message code="${row.category }" text="-"/>]</span>
		                                            </li>
		                                         </c:forEach>  
                                                <c:forEach var="row" items="${budget_plan2}" begin="0" end="5">
		                                            <li class="d-block">
		                                                <span class="me-25">-</span>
		                                                <span>${row.detail} : ${tasks.task_budget2 } [<spring:message code="${row.category }" text="-"/>]</span>
		                                            </li>
		                                         </c:forEach>  
                                               <c:forEach var="row" items="${budget_plan3}" begin="0" end="5">
		                                            <li class="d-block">
		                                                <span class="me-25">-</span>
		                                                <span>${row.detail} : ${tasks.task_budget3 } [<spring:message code="${row.category }" text="-"/>]</span>
		                                            </li>
		                                         </c:forEach>  
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="accordion-item">
                                                <h2 class="accordion-header" id="headingBorderFour">
                                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordionBorderFour" aria-expanded="false" aria-controls="accordionBorderFour">
                                                     <spring:message code="work.manage_resources"  />
                                                    </button>
                                                </h2>
                                                <div id="accordionBorderFour" class="accordion-collapse collapse" aria-labelledby="headingBorderFour" data-bs-parent="#accordionBorder">
                                                    <div class="accordion-body" id="tasks_resources">
                                   
                                             <table  style="font-size:16px">
                                    <thead>
                                        <tr>
<th class="form-label fixhead" >#</th>    
<th class="form-label fixhead" ><spring:message code="resources.name"  /></th>     	                          
<th class="form-label fixhead" ><spring:message code="resources.resourceType"  /></th>     
<th class="form-label fixhead" ><spring:message code="resources.PlannedQty"  /></th>    
<th class="form-label fixhead" ><spring:message code="resources.estimatedCost"  /></th>   
<th class="form-label fixhead" ><spring:message code="resources.actual"  /></th>    
<th class="form-label fixhead" ><spring:message code="resources.actual_cost"  /></th> 
<th class="form-label fixhead" ><spring:message code="resources.unit"  /></th>    
<th class="form-label fixhead" ><spring:message code="tables.action"  /></th>  
                                        </tr>
                                    </thead>
                                    <tbody>
	
	
  	
<c:set var="actual" value="0" />  	
<c:set var="plan" value="0" />  	 				
				                           <c:forEach items="${resourceUse }" var="row" varStatus="count">  
                          
                                              <tr> 
            	 <td class="form-label fixhead" style='padding-left:5px'> ${count.count }</td>   
            	 
<td class="form-label fixhead">${row.name}</td>     
<td class="form-label fixhead">${row.resourceType }</td>   
<td class="form-label fixhead">${row.plannedQty }</td> 
<td class="form-label fixhead">${row.estimatedCost }</td> 
<td class="form-label fixhead">${row.actual }</td> 
<td class="form-label fixhead">${row.actualCost }</td> 
<td class="form-label fixhead">${row.unit }</td>   
 <td class="form-label fixhead">
   <button  onclick='getdata2("/viewresource-filemanager/${row.id}/${upload_pns.upload_pn_id }","edit_id")'  class="btn btn-light btn-sm"><i data-feather='eye' ></i></button>
    </td>

 
                               </tr>
								 <c:set var="actual" value="${ actual + ( row.actual * row.actualCost) }" />  				                  
				               <c:set var="plan" value="${ Total + ( row.plannedQty * row.estimatedCost) }" />  		
                          </c:forEach>                
				                                      
   
                                    </tbody>
                                    
                                     <tfoot>
                                                        <tr>
                                        
            	 <td class="form-label fixhead" style='padding-left:5px'> </td>   
            	 
<td class="form-label fixhead" ></td>     
<td class="form-label fixhead" ></td>   
<td class="form-label fixhead" > </td> 
<td class="form-label fixhead" > </td> 
<td class="form-label fixhead" > </td> 
<td class="form-label fixhead" > </td> 
<td class="form-label fixhead"  ><spring:message code="resources.actual"  /></td>   
 <td class="form-label fixhead">${actual} / ${plan}</td>

 
                               </tr>                        
                                    </tfoot>               
                                    
                                    </table>   
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <hr class="my-2">
                                        <div class="d-flex align-items-center justify-content-between">
                                            <div class="d-flex align-items-center">
                                         
                                              
                                            </div>
                                            <div class="dropdown blog-detail-share">
                                                        
                                                           <c:if test="${tasks.task_idcheck  == Member_id || tasks.task_idadd == Member_id}" >    
                                                        
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <spring:message code="task.action" />
                                </button>
                                <div class="dropdown-menu" style="">
                             
                                
                                     
                                
                  
                                 
                               <c:if test='${tasks.task_status == 1 || tasks.task_status == -1}'>
                                    <a class="dropdown-item" href="/endtask-filemanager/${tasks.task_id}/2"    class="btn btn-sm btn-danger"  
                                 class="btn btn-primary"><spring:message code="btn.changestatus1" />
                              
                                 </a>
                                 
  
                            
                            
                                 </c:if>
<c:if test='${tasks.task_status == 2}'>
     <a class="dropdown-item" href="/endtask-filemanager/${tasks.task_id}/1"  class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" ><spring:message code="btn.changestatus2" />
     </a>
</c:if>
                               
          
                        <a class="dropdown-item" href="javascript:void(0)"  class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard6">    <spring:message code="task.increaseprogress" />    </a>      
                       
                              <div class="dropdown-divider"></div>    
                              
                                 <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard2"><spring:message code="btn.add" /> <spring:message code="task.responsible" />
                              
                                 </a>
                                    
                                    <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard3"><spring:message code="btn.add" /> <spring:message code="task.documents" />
                              
                                 </a>     
                                     <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" onclick="reset()" data-bs-toggle="modal" data-bs-target="#addNewCard5"><spring:message code="btn.add" /> <spring:message code="work.manage_resources"  />
                              
                                 </a>   
                            
                                                     <div class="dropdown-divider"></div>  
                                                <a class="dropdown-item" href="javascript:void(0)" onclick="getdata('/edittask-filemanager/${tasks.task_id}','${tasks.task_id}')" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard"> <spring:message code="btn.edit" />
                              
                                 </a>
                                     <a class="dropdown-item" href="/deltask-filemanager/${tasks.task_id }"
        onclick="return confirm('คุณเเน่ใจว่าจะลบ ${tasks.task_name } ?');" class="btn btn-sm btn-danger" ><spring:message code="btn.delete" /> <spring:message code="task.action" />
                              
                                 </a>
                                 
                                 
                                     </div>
                            </div>
                            
                   
                                           </c:if>
                            
                                           
                                            </div>
                                        </div>
                                    </div>
                                </div>
    

    
    
     <link href="<c:url value="/lib/multiselect/css/jquery.multiselect.css" />" rel="stylesheet">   
  <link href="<c:url value="/lib/multiselect/css/app.css" />" rel="stylesheet">                                
 <script src="<c:url value="/lib/multiselect/js/jquery.multiselect.js" />"></script>
 <script src="<c:url value="/lib/multiselect/js/app.js" />"></script>
 
     <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>
 
 
  	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
            
                 
                           
