<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ page session ="false"%> 
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>Member Pages</title>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
Hello ${name} pass  <spring:message code="welcome.springmvc" text="ยินดีต้อนรับสู่สปิง" />
<br>

<table>
  <tr>
    <th>ID</th>
    <th>name</th>
    <th>date</th>
    <th>action</th>
  </tr>

  <c:forEach items="${member}" var="row">
         <tr>
    <td> ${row.id}</td>
    <td>${row.name}</td>
    <td>${row.date}</td>
      <td><a href="delete-members/<c:out value='${row.id}'/>">ลบ</a> <a href="/edit-members?id=${row.id}">แก้ไข</a></td>
  </tr>
</c:forEach>


</table>





<br><br>
<a href="/add-members">เพิ่มรายชื่อ</a><br>
<a href="/uploadform">อัพโหลดรูป</a>
</body>
</html>