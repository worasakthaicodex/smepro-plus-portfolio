<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


  <select class="form-select select2" multiple="multiple" name="${listname }">
				                                            
				                                           <c:forEach items="${users }" var="row" varStatus="count">
				                                                        <c:set var="listcheck" value="${row.user_name }" />
				                                                        <option value="${row.user_name }" <c:if test='${fn:contains(listdata,listcheck)}'>selected</c:if>>${row.user_name }</option>
				                                           </c:forEach> 
	</select>