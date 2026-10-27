<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${Http_status_code != 200 }">
<div   id ="notify" style="color:red">
 ${Http_status_code }  : ${Callback } ...</div><br>
</c:if>
<c:if test="${Http_status_code == 200 }">
<div style="color:green"  id ="notify" data-notification-status="success">
 ${Http_status_code }  : ${Callback } ...</div><br>
</c:if>