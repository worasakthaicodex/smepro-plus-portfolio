<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form method="POST"   action="/savelinksline-workflow">
    <div class="card-body"> 
    
      <input type="hidden"  name="workflow_id" value="${workflow_id }">
     <input type="hidden"  name="id_refer" value="${id_refer }">
          
          
        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     <c:if test="${data.id > 0 }">
     <input type="hidden"  name="id" value="${data.id }">
     </c:if>
    
    		 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Link</div>
                                                         
                                          
                                                         
                                                                   <div class="col-sm-9">
                                           <input type="text" min="0" name="link_name" class="form-control" value="${data.link_name }">
                                                        </div>
                                                           
                                                            </div>
                                   					 <div class="mb-1 row">
                                               
                                                       
                                                         <div class="col-sm-3">Link to</div>
                                                         
                                                                   <div class="col-sm-3">
                                                      <select class="form-select select2"  name="link_to" id="link_to" >
								                               <option value="0">-</option>
								                                 <optgroup label="${workflows.workflow_name}">
								                               <c:forEach items="${Linkprocess }" var="rows" varStatus="count">
								                                        <option value="${rows.id}" <c:if test="${data.link_to == rows.id }">selected</c:if>>${rows.name }</option>
								                                 </c:forEach>   
								                                   </optgroup>
								                                   
								                                   
								                                  <c:set var="previousNote" value="" />
																	<c:forEach items="${Linkprocess2}" var="row" varStatus="count">
																	    <c:if test="${row.note != previousNote}">
																	        <c:if test="${not empty previousNote}">
																	            </optgroup>
																	        </c:if>
																	        <optgroup label="${row.note}">
																	        <c:set var="previousNote" value="${row.note}" />
																	    </c:if>
																	    
																	    <option value="${row.id}" <c:if test="${data.link_to == row.id }">selected</c:if> >${row.name}</option>
																	</c:forEach>
																	<c:if test="${not empty previousNote}">
																	    </optgroup>
																	    </c:if>
								                                        </select> 
                                                        </div>
                                                        
                                                        
                                                        
                                                                <div class="col-sm-3">Mandatory Constraint (การบังคับเลือกต้นทาง)</div>
                                                         
                                                                   <div class="col-sm-3">
                                             <select class="form-control" name="relationship">
                                                <option <c:if test="${data.relationship == '0' }">selected</c:if> value="0">Option</option>
                                                <option <c:if test="${data.relationship == '1' }">selected</c:if> value="1">Requirement</option>
                                                     
                                                         </select>
                                                        </div>
                                                           
                                                            </div>
                                                            
                                             
                                                         	
 </div>      
                     <div class="modal-footer" >
                                                              <a  href="/docworkflowapp-workflow"  target="_blank" class="btn btn-light waves-effect waves-float waves-light" >Doc</a>
                                                                <input type="Submit"  value="Save"  class="btn btn-primary waves-effect waves-float waves-light">
                                                            </div>                                                                                           
</form>                                                              
                                            