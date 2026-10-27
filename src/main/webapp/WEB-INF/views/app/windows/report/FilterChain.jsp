<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>	   
	   
	      <div class="pane-header">
                            <span>Filter Chain</span> <span>»</span>
                        </div>
                        <div class="search-field">
                            <input type="text" id="Fields" onkeyup="Filterlist(this)"  value="">
                        </div>
                        <div class="field-list" id="field-list">
                        
                 
                     <c:forEach items="${queryChains }" var="row" varStatus="count" >
                      <div class="reporttable-item">
                      
                <c:if test="${count.count == 1}">    <input type="checkbox" style="margin-right:5px;"  checked onclick="return false;" onkeydown="return false;">     		<span> ${row.type }</span></c:if> 
              
              
                <c:if test="${count.count > 1}">    <input type="checkbox" style="margin-right:5px;"   
             
                <c:if test="${row.status == 1}">  checked </c:if>
                	onchange="getData('/saveSetupFilterChain-filemanager/${row.id}/${row.id_refer}/${app_id_refer}','jsonbody')" >
                		<span onclick="renderSubTable('${count.count-1}','table-container2')"> ${row.type }</span>
                 </c:if> 
               
				    </div>
				     </c:forEach>
                        </div>
	    
