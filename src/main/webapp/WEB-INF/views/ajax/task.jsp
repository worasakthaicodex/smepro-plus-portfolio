<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>






    <c:if test="${keyactivitys.size() == 0 }">
                              
                                <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span><spring:message code="error.notfound"  /> <strong><spring:message code="project.key_activity"  /></strong>.   .</span>
                                            </div>
                                        </div>
                                </c:if>


 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
        <input  type="hidden" name="task_id" id="task_id" value="${tasks.task_id }">
                                
                                <div  class="row">
                                    <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_name" /></label>
                                        <input type="text"  name="task_name"  id="task_name"  class="form-control" value="${tasks.task_name }" required >
                                    </div>
                                    <br>
                                    <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_note" /></label>
                                        <textarea  class="form-control" name="task_note" id="task_note"  rows="5"  required>${tasks.task_note }</textarea>
                                    </div>
                                    
                                    
                                                               
                                                      <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="project.key_activity"  /></label>
                                     
                                       
                      <select class=" form-select" name="keyactivity_id"  > 
                              
                                          	 <c:forEach items="${keyactivitys }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" <c:if test="${row.id == tasks.keyactivity_id}">selected</c:if>>${row.expected_results}</option>
                                     		  </c:forEach>
                                  
                                       </select>
                                       
                                    </div>
                                         <div class="col-12 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.outcome" /></label>
                                     
                                       
                      <select class="select2 form-select" name="outcome"  multiple="multiple"> 
                                       		 <option value="0">-</option>
                                          	 <c:forEach items="${outcomes }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" <c:if test="${tasksoutcome.contains(row.id.toString())}">selected</c:if>>${row.expected_results}</option>
                                     		  </c:forEach>
                                  
                                       </select>
                                       
                                    </div>
                                         <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.take_action" /></label>
                                       
                                        <select  class="form-select" name="task_payment">
                                       		 <option value="0">-</option>
                                             <c:forEach items="${milestones }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" <c:if test="${row.id == tasks.task_payment}">selected</c:if>>[${row.date_due }] ${row.detail} </option>
                                     		  </c:forEach>
                                  
                                       </select>
                                       
                                       
                                    </div>
                                    
                                    
                                         <div class="col-6 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_datestart" /></label>
                                       <input type="date"  name="task_datestart" id="task_datestart"   value="${tasks.task_datestart }" class="form-control" required >
                                    </div>
                                        <div class="col-6 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_dateend" /></label>
                                         <input type="date" name="task_dateend" id="task_dateend"    value="${tasks.task_dateend }" class="form-control" required >
                                    </div>
                                    
                                        
                                            <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.budget_source" /></label>
                                      
                                       <select  class="form-select" name="budget_source1">
                                       		 <option value="0">-</option>
                                          	 <c:forEach items="${budgets }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" <c:if test="${row.id == tasks.budget_source1}">selected</c:if>> ${row.detail} <spring:message code="project.budget" /> ${row.budget }</option>
                                     		  </c:forEach>
                                      
                                       </select>
                                       
                                    </div> 
                                   
                                 <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.budget" /></label>
                                       <input type="number" id="modalRoleName" name="task_budget1" class="form-control" min="0.0" value="${tasks.task_budget1 }"  >
                                    </div>
                                
                                           <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName">2.<spring:message code="task.budget_source" /></label>
                                      
                                       <select  class="form-select"  name="budget_source2">
                                       		 <option value="0">-</option>
                                          	 <c:forEach items="${budgets }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" <c:if test="${row.id == tasks.budget_source2}">selected</c:if>> ${row.detail} <spring:message code="project.budget" /> ${row.budget }</option>
                                     		  </c:forEach>
                                    
                                       </select>
                                       
                                    </div> 
                                 <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.budget" /></label>
                                       <input type="number" id="modalRoleName" name="task_budget2" class="form-control" min="0.0" value="${tasks.task_budget2 }"  >
                                    </div>
                                    
                                    
                                    
                                          <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName">3.<spring:message code="task.budget_source" /></label>
                                              <select  class="form-select" name="budget_source3">
                                       		 <option value="0">-</option>
                                          	 <c:forEach items="${budgets }" var="row" varStatus="count">  
                                     		  <option value="${row.id}"  <c:if test="${row.id == tasks.budget_source3}">selected</c:if>> ${row.detail} <spring:message code="project.budget" /> ${row.budget }</option>
                                     		  </c:forEach>
                                       
                                       </select>
                                    </div>
                                 <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.budget" /></label>
                                       <input type="number" id="modalRoleName" name="task_budget3" class="form-control" min="0.0" value="${tasks.task_budget3 }"  >
                                    </div>
                                   
                                 
                                    
                                    
                                  </div>  
     <link href="<c:url value="/lib/multiselect/css/jquery.multiselect.css" />" rel="stylesheet">   
  <link href="<c:url value="/lib/multiselect/css/app.css" />" rel="stylesheet">                                
 <script src="<c:url value="/lib/multiselect/js/jquery.multiselect.js" />"></script>
 <script src="<c:url value="/lib/multiselect/js/app.js" />"></script>
 
     <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>
 
 
  	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	  
  	    	  <script>
  	    		 $('.select2').select2();
  	    	  </script>
  	    
  	  