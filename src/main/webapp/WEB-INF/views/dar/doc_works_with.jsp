<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

 <c:if test="${file.size() < 1  }"> 

     <font color="red"> <spring:message code="file.notfound"  /></font>
                    
    </c:if >       

   <c:forEach items="${file }" var="row" varStatus="count">

    <font color="green"><spring:message code="file.workswith"  /> ID: ${row.file_no } REV ${row.file_rev } , ${row.file_name_original }</font> 
                    
    </c:forEach> 
  <br> <br> 