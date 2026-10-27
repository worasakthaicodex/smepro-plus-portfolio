<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

 <c:if test="${file.size() < 1  }"> 

     <font color="green"><spring:message code="file.pass"  /></font>
                    
    </c:if >       



   <c:forEach items="${file }" var="row" varStatus="count">

    <font color="red"><spring:message code="file.duplicate"  /> ID: ${row.file_no } REV ${row.file_rev } , ${row.file_name_original } ,Responsible : ${row.dar_jd }</font>
                    
    </c:forEach>      
   <br>    <br>  