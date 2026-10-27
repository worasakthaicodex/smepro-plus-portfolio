<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:choose>
	   <c:when test="${config_type == 'dataset'}">
	         <%@ include file="config/setdataset.jsp" %>   
	   </c:when>  	
	     <c:when test="${config_type == 'workflow'}">
	            <%@ include file="config/setworkflow.jsp" %>   
	   </c:when>  	
	   <c:when test="${config_type == 'dataaccessinterface'}">
	          <%@ include file="config/setDataAccessInterface.jsp" %>   
	   </c:when> 
	      <c:when test="${config_type == 'revision'}">
	          <%@ include file="config/setRevision.jsp" %>   
	   </c:when> 
	      <c:when test="${config_type == 'renamefile'}">
	          <%@ include file="config/setRenamefile.jsp" %>   
	   </c:when> 
	        <c:when test="${config_type == 'extension'}">
	          <%@ include file="config/setExtension.jsp" %>   
	   </c:when> 
</c:choose>	   
	   