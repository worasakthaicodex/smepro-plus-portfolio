 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

           
 
 									<table>
                                    <tr style="font-size:12px">
                                        <th><spring:message code="actionplan.problems" /></th>
                                    <th><spring:message code="actionplan.description" /></th>
                                     <th><spring:message code="actionplan.solving" /></th>
                                      <th><spring:message code="actionplan.fixedyet" /></th>
                                         <th><spring:message code="btn.action" /></th>
                                    </tr>
                                    
                                <c:forEach items="${problem_list }" var="row" varStatus="count">
                                   <tr style="font-size:11px">
                                    <td>
                                 	  <c:if test="${ row.title == 0}"> 
                                   		 <spring:message code="actionplan.unassessed" />
                                  	  </c:if>
                                  	  
                                    </td>
                               <td>
  <div style="max-width:200px; 
              white-space: nowrap; 
              overflow: hidden; 
              text-overflow: ellipsis;">
    ${row.description}
  </div>
</td>
<td>
  <div style="max-width:200px; 
              white-space: nowrap; 
              overflow: hidden; 
              text-overflow: ellipsis;">
    ${row.mitigation}
  </div>
</td>
                                         <td>
                                          <c:if test="${ row.status == 1}"> 
                                   		<spring:message code="actionplan.inprogress" />
                                  	  </c:if>
                                  	        <c:if test="${ row.status == 2}"> 
                                   	<spring:message code="actionplan.closed" />
                                  	  </c:if>
                                     
                                         
                                         </td>
                                                <td>
                                                <a href="#" 
                                                onclick="getdatas('/editproblems-filemanager/${row.id}','subproblems_form',0);">
                                                <spring:message code="btn.view" />
                                                </a>
                                                </td>
                                    </tr>
                                
                                 </c:forEach>
                                    
                                    
                                    </table>