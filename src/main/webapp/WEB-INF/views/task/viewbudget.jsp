<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

                <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                               <div class="text-center mb-4">
                                    <h1 class="role-title">${budget.detail }</h1>
                     
                                </div>
                   
                     <table  style="font-size:16px">
                                    <thead>
                                        <tr>
                                        
            	 <th class="form-label fixhead" >#</th>   
            	 
<th class="form-label fixhead" ><spring:message code="task.task_name" /></th>         	                          
<th class="form-label fixhead" ><spring:message code="task.task_datestart" /> </th>  
<th class="form-label fixhead" ><spring:message code="task.task_dateend" /></th>       
<th class="form-label fixhead" >   <spring:message code="project.actual" /></th>    
<th class="form-label fixhead" >   <spring:message code="tables.status" /></th>    




    
                                        </tr>
                                    </thead>
                                    <tbody>

 	<c:set var="actual" value="0" />	
													<c:set var="success" value="0" />	
												<c:set var="total" value="0" />	
				                           <c:forEach items="${tasks }" var="row" varStatus="count">  
				                           
				                           			<c:set var="total" value="${total+1 }" />	
				                           		
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" >&nbsp; ${count.count }</td>   
            	 
<td class="form-label fixhead" >${row.task_name}</td>       
  
<td class="form-label fixhead" >${row.task_datestart}</td>   
<td class="form-label fixhead" >${row.task_dateend}</td> 
<td class="form-label fixhead" >${row.use_budget}

 	<c:set var="actual" value="${actual + row.use_budget}" />	
</td> 
<td class="form-label fixhead" >
 		 <c:if test="${row.task_status == 1 }">
                                            <span class="badge rounded-pill badge-light-info"><spring:message code="status.inprocess" /></span>
                                            </small>
                                   </c:if>
                                       <c:if test="${row.task_status == 2 }">
                                       	<c:set var="success" value="${success+1 }" />	
                                            <span class="badge rounded-pill badge-light-success"><spring:message code="status.success" /></span>
                                            </small>
                                   </c:if>   

</td>  

 
                               </tr>
												                  
				              
                          </c:forEach>                
				                                      
       <tr>
                                        
            	 <td class="form-label fixhead" ></td>   
            	 
<td class="form-label fixhead" ></td>       
  
<td class="form-label fixhead" ></td>   
<td class="form-label fixhead" ></td> 
<td class="form-label fixhead" >${actual }
<td class="form-label fixhead" ></td> 

</td>  

 
                               </tr>                                  
                                    </tbody>
                                    </table> 
 
           
                       
                            </div>