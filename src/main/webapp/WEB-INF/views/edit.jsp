<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Add Member</title>
</head>
<body>

<form:form method="POST" modelAttribute="member" action="edit-members">
<input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<input  type="hidden"  name ="id" value="${member.id}">
User <form:input path="name"/><br>
Date <form:input type="date" path="date"/><br>
<button type="submit">แก้ไข</button>

</form:form>
<br><br>
</body>
</html>