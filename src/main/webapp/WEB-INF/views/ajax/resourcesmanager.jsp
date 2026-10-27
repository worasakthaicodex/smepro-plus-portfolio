<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

  <div class="card mb-20">
                    <div class="card-header">
                        <h3 class="card-title">
                        <c:if test="${type == 'labor' }"><spring:message code="work.labor"  /></c:if>
                         <c:if test="${type == 'equipment' }"><spring:message code="work.equipment"  /></c:if>
                           <c:if test="${type == 'Material' }"><spring:message code="work.materials"  /></c:if> 
                         <c:if test="${type == 'Facility' }"><spring:message code="work.facilities"  /></c:if> 
                        </h3>
                        
                        
                        <div>
                         <button class="btn btn-primary" onclick="getdatas('/addresourcesmanager-filemanager/${type}/${upload_id}','saveresourcesmanage',0)"
                          data-bs-target="#addresourcesModal1" data-bs-toggle="modal" >
                         <spring:message code="btn.add"  />
                         </button>
                    	<a class="btn btn-light" href="/projectmanager-filemanager?id=${upload_id}&&step=0&&group=1">กลับ</a>
                        </div>
                    </div>
                    
                         </div>


<div class="card card-app-design " style="padding:20px;">
	<div class="mail-attachments">         
	
	   

	      <table  style="font-size:16px">
                                    <thead>
                                        <tr>
<th class="form-label fixhead" >#</th>    
<th class="form-label fixhead" ><spring:message code="resources.name"  /></th>     
<th class="form-label fixhead" ><spring:message code="resources.description"  /></th>        	                          
<th class="form-label fixhead" ><spring:message code="resources.resourceType"  /></th>     
<th class="form-label fixhead" ><spring:message code="resources.estimatedCost"  /></th>    
<th class="form-label fixhead" ><spring:message code="resources.unit"  /></th>    
<th class="form-label fixhead" ><spring:message code="resources.remarks"  /></th>  
<th class="form-label fixhead" ><spring:message code="tables.action"  /></th>  
                                        </tr>
                                    </thead>
                                    <tbody>
						 <c:set var="Total" value="0" />  
				                           <c:forEach items="${resource }" var="row" varStatus="count">  
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" style='padding-left:5px'> ${count.count }</td>   
            	 
<td class="form-label fixhead" >${row.name}</td>     
<td class="form-label fixhead"  >${row.description}</td>     
<td class="form-label fixhead" >${row.resourceType }</td>   
<td class="form-label fixhead" > ${row.estimatedCost }</td> 
<td class="form-label fixhead"  >${row.unit }</td>   
<td class="form-label fixhead"  >${row.remarks }</td>   
 <td class="form-label fixhead">
       <form action="/deleteresource-filemanager" method="post">
              <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <!-- hidden ส่งค่า id ของ outcome -->
        <input type="hidden" name="id" value="${row.id}">
        <input type="hidden" name="taskId" value="${row.taskId}">
        <button type="submit" class="btn btn-danger btn-sm"  onclick="return confirm('<spring:message code="policy.deleteconfirm"  />?');"><i data-feather='trash' ></i></button>
         
      </form>
   <a action="/viewresource-filemanager/${row.id}" style="display:none"  class="btn btn-light btn-sm"><i data-feather='eye' ></i></button>
    </td>

 
                               </tr>
												                  
				              
                          </c:forEach>                
				                                      
        
                                        
                                    </tbody>
                                    </table>   
	</div>                                   
</div>             

    <script>
    
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
     
 </script>                               