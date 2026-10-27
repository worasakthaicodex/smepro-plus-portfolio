<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



 <c:if test='${Sys_Error == "Sys_Failed_PD"}'>

<script>      
	 alert("บันทึกไม่สำเร็จ ไม่พบค่า PD"); 
</script>  
</c:if>

 <c:if test='${Sys_Error == "Sys_Failed_BATCH"}'>

<script>      
	 alert("บันทึกไม่สำเร็จ ไม่พบค่า BATCH"); 
</script>  
</c:if>


 <c:if test='${Sys_Error == "Sys_Failed_production_Null"}'>

<script>      
	 alert("บันทึกไม่สำเร็จ ไม่พบค่า production"); 
</script>  
</c:if>

 <c:if test='${Sys_Error == "Sys_Failed_customer_Null"}'>

<script>      
	 alert("บันทึกไม่สำเร็จ ไม่พบลูกค้ารายนี้"); 
</script>  
</c:if>

 <c:if test='${Sys_Error == "Sys_Failed_material_Null"}'>

<script>      
	 alert("บันทึกไม่สำเร็จ ไม่พบค่า material"); 
</script>  
</c:if>





