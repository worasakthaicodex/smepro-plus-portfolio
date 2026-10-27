<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<table style="font-size:12px">
<tr>
        <th>#</th>
		<th><spring:message code="submitted.submitter" /></th>
		<th><spring:message code="submitted.lastmodified" /></th>
			 <c:if test="${viewmode != 1}">    
		<th><spring:message code="submitted.status" /></th>
			 </c:if>
		
		
		<th> <spring:message code="project.unresolvedissues" /></th>
		<th><spring:message code="submitted.inspection_status" /></th>
		<th> <spring:message code="tables.action" /></th>

    </tr>
       <c:set var="total" value="0" />
       <c:forEach items="${allplan }" var="row" varStatus="count">  
       <tr>
        <td>${count.count }</td>
		<td>${row.actionplan_responsible }</td>
		<td>${row.actionplan_dateadd}</td>
		   
		
			
				 <c:if test="${viewmode != 1}">    
	
		
		            <c:if test="${row.actionplan_result != 'late'}">    
                          <td class="status-submitted"> 
       
       <spring:message code="submitted.notlate" />
        </td>
        </c:if>
              <c:if test="${row.actionplan_result == 'late'}">    
                <td class="error-submitted">
       
       <spring:message code="submitted.lates" />
         </td>
        </c:if>
		
	         <c:if test="${row.actionplan_result  == null}">    
                <td >
       
    
         </td>
        </c:if>
        	 </c:if>
        
        
        
           <c:if test="${row.count_problems > 0}">    
		    <td class="error-submitted">
		    ${row.count_problems} 
		      </td>
			</c:if>
			 <c:if test="${row.count_problems < 1 && row.actionplan_dateadd != null}">    
		          <td class="status-submitted"> 
		         -
		      </td>
			</c:if>
        
        
	 <c:if test="${row.submission == 1}">    
                    <td class="status-submitted">
        <spring:message code="submitted.checked" />
           </td>
        </c:if>
        
             <c:if test="${row.submission != 1 && row.submission != -1  && row.submission != -2 && row.actionplan_dateadd != null}">   
                  <td >
             <spring:message code="submitted.notchecked" />
           </td>
        </c:if>
        
              <c:if test="${row.submission == -1}">   
                  <td class="error-submitted">

           <spring:message code="submitted.failed" />
           </td>
        </c:if>
              <c:if test="${row.submission == -2}">   
                  <td class="warm-submitted">
           <spring:message code="submitted.rechecked" />
           </td>
        </c:if>
                <c:if test="${row.actionplan_dateadd == null}">   
                  <td >
   
           </td>
        </c:if>
        
        
        
		<td>
		
		             <c:if test="${row.actionplan_id > 0}">   
		             
		<a class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#addNewCard4"
		onclick="getdatas('/checktask-filemanager/${row.actionplan_id }','viewtask','0');"
		>
				 <c:if test="${viewmode != 1}">
				 	<spring:message code="submitted.inspection" />
				   </c:if>
	
				 <c:if test="${viewmode == 1}">
				 	<spring:message code="btn.view" />
		  </c:if>
		</a>
	
		
		 <c:if test="${actionplan.actionplan_id ==  row.actionplan_id  && (row.submission != 1 )}">  
			<a class="btn btn-light btn-sm" onclick="tab('dataplan','post');closetab('blogComment')" ><spring:message code="btn.edit" /></a>
			
		</c:if>

		</c:if>
				 <c:if test="${row.actionplan_id   == 0 && row.actionplan_responsible  == Member_name}">  
			<a class="btn btn-light btn-sm" onclick="tab('dataplan','post')" ><spring:message code="btn.add" /></a>
			
		</c:if>
		  
		</td>
    </tr>
       </c:forEach>
 
</table>

