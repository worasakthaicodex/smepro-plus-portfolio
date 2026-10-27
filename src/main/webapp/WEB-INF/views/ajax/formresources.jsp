<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


    <input type="hidden"  name="id"  id="use_id"  class="form-control" value="${resource_use.id}"  >

            <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.name"  /></label>
                               <select name="resources_id" class="form-select">
                            
                           <c:forEach items="${resource }" var="row" varStatus="count">  
                               	<option value="${row.id }"    <c:if test="${resource_use.resources_id == row.id }">selected</c:if> >${row.name } ,${row.resourceType } ${resource_use.resources_id}[<spring:message code="resources.estimatedCost"  /> :${row.estimatedCost }]</option>
                             </c:forEach>  	
                               </select>
                                    </div>
                                    <br>
                            
         			   <div class="col-4 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.PlannedQty" /></label>
                                                             <input type="number"  name="plannedQty"  id="plannedQty" value="${resource_use.plannedQty}"    class="form-control"  required >
                                                                  <br> <br>
                                    </div>
                                 <div class="col-4 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.actual" /></label>
                                                             <input type="number"  name="actual"  id="actual"  value="${resource_use.actual}"  class="form-control"   >
                                                                  <br> <br>
                                    </div>
                                 <div class="col-4 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="resources.actual_cost" /></label>
                                                             <input type="number"  name="actualCost"  id="actualCost" value="${resource_use.actualCost}"   class="form-control"   >
                                                                  <br> <br>
                                    </div>
                                            <input type="hidden"  name="estimatedCost"  id="estimatedCost" value="${resource_use.estimatedCost}"   class="form-control"   >
                              
                                    
      		
       						     <button type="button"  class="btn btn-primary me-1 waves-effect waves-float waves-light" style="float:left" 
     onclick='postdatatask("/saveresources-filemanager?${_csrf.parameterName}=${_csrf.token}","formresources","maindataresources")'>
     <spring:message code="btn.add" />
     </button> 
 
                               