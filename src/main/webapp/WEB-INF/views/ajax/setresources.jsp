<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

		 <c:set var="Total" value="0" />  
		 
		 <c:set var="actual" value="0" />  	
<c:set var="plan" value="0" />  	
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
					
<c:forEach items="${resource }" var="row" varStatus="count">  
	<tr>
	<td class="form-label fixhead" style='padding-left:5px'>${count.count }</td>              	 
	<td class="form-label fixhead">${row.name}</td>     
	<td class="form-label fixhead">${row.resourceType }</td>   
	<td class="form-label fixhead">${row.plannedQty }</td> 
	<td class="form-label fixhead">${row.estimatedCost }</td> 
	<td class="form-label fixhead">${row.actual }</td> 
	<td class="form-label fixhead">${row.actualCost }</td> 
	<td class="form-label fixhead">${row.unit }</td>   
 <td class="form-label fixhead">
   <button  onclick='getdata2("/viewresource-filemanager/${row.id}/${upload_pn_id }","edit_id")'  class="btn btn-light btn-sm"><i data-feather='eye' ></i></button>
    </td>
	</tr>												                  
	 <c:set var="actual" value="${ actual + ( row.actual * row.actualCost) }" />  				                  
				               <c:set var="plan" value="${ Total + ( row.plannedQty * row.estimatedCost) }" /> 
</c:forEach>                
				                                      
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
                                        
                                    </tbody>
                                    </table>
                                    
        <script>
    
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
     
 </script>                                   
                                    