<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Add Member</title>
</head>
<body>

<form:form action ="add-members" modelAttribute="member" method="post">
<input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
User <form:input path="name" /><br>
Date <form:input type="date" path="date" /><br>

<button type="submit"><spring:message code="btn.add" text="เพิ่ม" /></button>

</form:form>
<br><br>
</body>
</html>