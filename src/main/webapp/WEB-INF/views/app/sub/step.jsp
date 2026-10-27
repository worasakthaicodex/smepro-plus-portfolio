<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
 
 
 <c:if test="${sub_processes.step == 'Create'  }">
   <%@ include file="Create.jspf" %>   
 </c:if>
  <c:if test="${sub_processes.step == 'Review'   }">
   <%@ include file="Review.jspf" %>   
 </c:if>
   <c:if test="${  sub_processes.step == 'Approval'  }">
   <%@ include file="Approval.jspf" %>   
 </c:if>
    <c:if test="${  sub_processes.step == 'Clear'  }">
   <%@ include file="Clear.jspf" %>   
 </c:if>
  <c:if test="${  sub_processes.step == 'Completion'  }">
   <%@ include file="Completion.jspf" %>   
 </c:if>
 
 
 
