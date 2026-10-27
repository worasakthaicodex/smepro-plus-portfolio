  <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


    
	
<c:set var="linkId" value="${edatas[key]}" />
<c:if test="${ edatas['customer'] != null && not empty edatas['customer'] && row.keymap.mainkey == 'customerCode' }">
<c:set var="linkId" value="${edatas['customer']}" />
</c:if>	
						   
                 
		           <input type="text" class="form-control" name="edata.${key}"  value="${linkId}"  id="${row.keymap.mainkey}"   required="required">
		            

		               <c:if test="${row.display == null && row.process != null && linkId != null}">                            
		       
		        <span class="input-group-text cursor-pointer" style="cursor: pointer;"
		           data-id="${row.process.id}"
    					data-name="${row.process.name} "
    					data-link="/indexapp-filemanager/"
    					data-from="${edatas.data_id}"
  					    data-logo="/resources/app-assets/images/icons/APP/${row.process.process_logo}"
             		    data-box=" [<spring:message code="btn.InProgress"  text="-​" />]"
             		   onclick="App2(this)">
		        <a target="_blank" > 
		         <img  src="/app/system/icon/search database.ico" />
		        </a>
		            </span> 	</c:if>
		            
		            
		            
           