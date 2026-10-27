<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
/* === HEADING STYLE #3 === */
.three {
width:100%;
;
}


.three h1 {
  font-size: 16px;
  list-style-type: none;
  margin: 0;
 padding: 6px 8px;
  overflow: hidden;
  border: 1px solid #e7e7e7;
  background-color: #f3f3f3;
}

 
</style>

<div class="mb-1 row">
<div class="col-sm-3"></div>
</div>  
	    <c:set var="reference" value="reference" />
 <c:set var="action_batch" value="${action_batch_insert},${action_batch_modify},${action_batch_continue}" />	
 
<%@ include file="views/listview.jspf" %> 

          
<div id="subdata">           
		<c:if test="${action_batch != null }">  
				<c:set var="typeshow" value="5" />
				<%@ include file="../app/general_system.jspf" %>                                    
		</c:if>     
</div>	                      
</div> 